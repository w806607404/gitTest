/**
 * @Description:
 * @Package: com.scinan.iot.service.impl 
 * @author: 吴广   
 * @date: 2018年7月27日 下午2:26:49 
 */
package com.scinan.iot.service.impl;

import java.util.ArrayList;
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

import com.itextpdf.text.log.SysoLogger;
import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountBillDetailMapper;
import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.RoleMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountBillDetail;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.service.AccountBillDetailService;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;

/**
 * @Description: 分红账单记录处理类
 * @author: 吴广
 * @date: 2018年7月27日 下午2:26:49 
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("accountBillDetailService")
public class AccountBillDetailServiceImpl extends GenericServiceImpl<AccountBillDetail, Long>
		implements AccountBillDetailService {
	static Logger logger = Logger.getLogger(AccountBillDetailServiceImpl.class);
	
	@Autowired
	private AccountBillDetailMapper accountBillDetailMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	
	@Override
	protected GenericMapper<AccountBillDetail, Long> getGenericMapper() {
		
		return accountBillDetailMapper;
	}
	
	
	/**
	 * 分红账单明细记录分页
	 */
	@Override
	public PageBean<AccountBillDetail> fetchParamsByPage(Map<String, String> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		//获得当前用户角色对象
		Role role = roleMapper.fetchById(accountInfoBean.getRole_id());
		
		Conds conds = new Conds();
		//默认查询条件，分红列表为1，扣除分红为0
		if(StringUtils.isNotEmpty(params.get("type"))){
			conds.equal("type",params.get("type"));
		}
		//查询条件
		if(StringUtils.isNotEmpty(params.get("device_id"))){
			map.put("device_id",params.get("device_id"));
		}

		
		if (role.getRole_type()<1 && role.getParent_id()==AccountInfo.ACCOUNT_TYPE_ADMIN) {
			
		}else if (role.getRole_type()==AccountInfo.ACCOUNT_TYPE_COMMON) {//判断为厂商
			conds.equal("a.company_id", role.getCompany_id());			
			
		}else if (role.getRole_type()==AccountInfo.ACCOUNT_TYPE_OTHER) {//代理商	
			List<AccountInfo> list = accountInfoService.fetchLowerDealerByUserInfo(accountInfoBean.getId().intValue(), role.getCompany_id());
			List<String> str = new ArrayList<String>();
			str.add(accountInfoBean.getId().toString());
			if (list!=null &&list.size()>0) {
				for (AccountInfo accountInfo : list) {				
					str.add(accountInfo.getId().toString());				
				}
			}
			Object[] strs = str.toArray();		
			conds.in("a.benefit_userId", strs);
		}else {//经销商
			conds.equal("a.benefit_userId", accountInfoBean.getId());		
		}
		
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));		
		List<AccountBillDetail> list = accountBillDetailMapper.fetchByPage(map);
		if(list != null && list.size()>0){
			for(AccountBillDetail bean : list){
				if(bean.getSale_district_name() == null){
					AccountInfo saleUser = accountInfoMapper.fetchById(bean.getSale_userId());
					AccountInfo benefitUser = accountInfoMapper.fetchById(bean.getBenefit_userId());
					if(benefitUser.getRole_type() != 1){
						bean.setSale_province_name(benefitUser.getProvince_name());
						bean.setSale_city_name(benefitUser.getCity_name());
						bean.setSale_district_name(benefitUser.getDistrict_name());
					}else{
						bean.setSale_province_name(saleUser.getProvince_name());
						bean.setSale_city_name(saleUser.getCity_name());
						bean.setSale_district_name(saleUser.getDistrict_name());
					}
				}
			}
		}
		return new PageBean<AccountBillDetail>(accountBillDetailMapper.count(map),accountBillDetailMapper.fetchByPage(map));
	}
	
	
	/**
	 * 删除分红账单记录
	 */
	@Override
	public DataCenterResult deleteBillDetail(Long id) throws Exception {
		int num = accountBillDetailMapper.delete(id);
		if (num==1) {
			return DataCenterResult.ok();
		}else {
			return DataCenterResult.build(-101);
		}
		
	}
}
