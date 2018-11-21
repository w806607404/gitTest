/**
 * @Description:
 * @Package: com.scinan.iot.service.impl 
 * @author: 吴广   
 * @date: 2018年8月9日 下午5:01:27 
 */
package com.scinan.iot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.NotifySendMapper;
import com.scinan.iot.ddeddo.dao.RoleMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.NotifySend;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.SubsidyAmountBillDetailService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.mybatis.support.sql.Sort;
import com.scinan.utils.Common;

/**
 * @Description: 该类的功能描述
 * @author: 吴广
 * @date: 2018年8月9日 下午5:01:27 
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("subsidyAmountBillDetailService")
public class SubsidyAmountBillDetailServiceImpl extends
		GenericServiceImpl<NotifySend, Long> implements SubsidyAmountBillDetailService {

	static Logger logger = Logger.getLogger(SubsidyAmountBillDetailServiceImpl.class);
	
	@Autowired
	private NotifySendMapper notifySendMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	
	
	@Override
	protected GenericMapper<NotifySend, Long> getGenericMapper() {		
		return notifySendMapper;
	}
	
	
	/**
	 * 补贴余额账单明细记录分页
	 */
	@Override
	public PageBean<NotifySend> fetchParamsByPage(Map<String, String> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		//获得当前用户角色对象
		Role role = roleMapper.fetchById(accountInfoBean.getRole_id());
		
		Conds conds = new Conds();
		conds.equal("notify_type",1 );
		conds.equal("a.status",2);
		//查询条件
		if(StringUtils.isNotEmpty(params.get("user_name"))){
			conds.like("user_name",params.get("user_name"));
		}
		if(StringUtils.isNotEmpty(params.get("province_id"))){
			conds.equal("b2.province_id",params.get("province_id"));
		}
		if(StringUtils.isNotEmpty(params.get("city_id"))){
			conds.equal("b2.city_id",params.get("city_id"));
		}
		if(StringUtils.isNotEmpty(params.get("district_id"))){
			conds.equal("b2.district_id",params.get("district_id"));
		}
		if(StringUtils.isNotEmpty(params.get("notify_type"))){
			conds.equal("notify_type",params.get("notify_type"));
		}
		
		if (role.getRole_type()<1 && role.getParent_id()==AccountInfo.ACCOUNT_TYPE_ADMIN) {
			
		}else if (role.getRole_type()==AccountInfo.ACCOUNT_TYPE_COMMON) {//判断为厂商
			conds.equal("receive_userId", accountInfoBean.getId());
			conds.equal_or("send_userId", accountInfoBean.getId());			
			conds.equal("notify_type",1 );
			conds.equal("a.status", 2);
		}else if (role.getRole_type()==AccountInfo.ACCOUNT_TYPE_OTHER) {//代理商
			conds.equal("receive_userId", accountInfoBean.getId());
			conds.equal_or("send_userId", accountInfoBean.getId());
			conds.equal("notify_type",1 );
			conds.equal("a.status", 2);
		}else {//经销商
			conds.equal("receive_userId", accountInfoBean.getId());		
		}
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		List<NotifySend> list = notifySendMapper.fetchBySubsidyAmountPage(map);
		for (NotifySend notifySend : list) {
			if (notifySend.getRole_type()==AccountInfo.ACCOUNT_TYPE_COMMON) {//如果接收人为厂商，则显示通知人的区域地址
				AccountInfo accountInfo = accountInfoService.fetchById(notifySend.getSend_userId());
				notifySend.setProvince_name(accountInfo.getProvince_name());
				notifySend.setCity_name(accountInfo.getCity_name());
				notifySend.setDistrict_name(accountInfo.getDistrict_name());
			}
		}
				
		return new PageBean<NotifySend>(notifySendMapper.count(map),list);
	}
	
}
