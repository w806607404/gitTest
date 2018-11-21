/**
 * @Description:
 * @Package: com.scinan.iot.service.impl 
 * @author: 吴广   
 * @date: 2018年7月2日 上午11:38:21 
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

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountSoldMapper;
import com.scinan.iot.ddeddo.dao.RoleMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.AccountSold;
import com.scinan.iot.ddeddo.dao.domain.AccountSoldBean;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.AccountSoldService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;
import com.scinan.utils.StringUtil;

/**
 * @Description: 销售记录实体类
 * @author: 吴广
 * @date: 2018年7月2日 上午11:38:21 
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("accountSoldService")
public class AccountSoldServiceImpl extends GenericServiceImpl<AccountSold,Long> implements AccountSoldService {
	static Logger logger = Logger.getLogger(AccountSoldServiceImpl.class);
	
	@Autowired
	private AccountSoldMapper accountSoldMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AccountInfoService accountInfoService;
	
	
	protected GenericMapper<AccountSold, Long> getGenericMapper() {
		return accountSoldMapper;
	}
	
	/**
	 * 添加销售记录
	 */
	@Override
	public DataCenterResult addAccountSold(AccountSold accountSold)
			throws Exception {
		accountSoldMapper.save(accountSold);
		return DataCenterResult.ok();
	}

	/**
	 * 修改销售记录
	 */
	@Override
	public DataCenterResult updateAccountSold(AccountSoldBean accountSoldBean)
			throws Exception {
		int num = accountSoldMapper.update(accountSoldBean);
		if (num>0) {
			return DataCenterResult.ok();
		}else {
			return DataCenterResult.build(-101);
		}
	}

	/**
	 * 删除销售记录
	 */
	@Override
	public DataCenterResult deleteAccountSold(Long id) throws Exception {
		int num = accountSoldMapper.deleteByPK(id);
		if (num==1) {
			return DataCenterResult.ok();
		}else {
			return DataCenterResult.build(-101);
		}
		
	}

	/**
	 * 销售记录分页列表
	 */
	@Override
	public PageBean<AccountSoldBean> fetchParamsByPage(Map<String, String> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		//获得当前用户角色对象
		Role role = roleMapper.fetchById(accountInfoBean.getRole_id());
		
		Conds conds = new Conds();
		//查询条件
		if(StringUtils.isNotEmpty(params.get("device_id"))){
			conds.like("device_id",params.get("device_id"));
		}
		if(StringUtils.isNotEmpty(params.get("province_id"))){
			conds.equal("t.province_id",params.get("province_id"));
		}
		if(StringUtils.isNotEmpty(params.get("city_id"))){
			conds.equal("t.city_id",params.get("city_id"));
		}
		if(StringUtils.isNotEmpty(params.get("district_id"))){
			conds.equal("t.district_id",params.get("district_id"));
		}
		
		
		if (role.getRole_type()<1 && role.getParent_id()==AccountInfo.ACCOUNT_TYPE_ADMIN) {//判断为超管
			
		}else if (role.getRole_type() == AccountInfo.ACCOUNT_TYPE_COMMON) {//厂商
			conds.equal("t.company_id", role.getCompany_id());			
			
		}else if (role.getRole_type() == AccountInfo.ACCOUNT_TYPE_OTHER) {//代理商
			List<AccountInfo> list = accountInfoService.fetchLowerDealerByUserInfo(accountInfoBean.getId().intValue(), role.getCompany_id());
			List<String> str = new ArrayList<String>();
			str.add(accountInfoBean.getId().toString());
			if (list!=null && list.size()>0) {
				for (AccountInfo accountInfo : list) {				
					str.add(accountInfo.getId().toString());				
				}
			}			
			Object[] strs = str.toArray();		
			conds.in("t.user_id", strs);			
		}else {
			conds.equal("t.user_id", accountInfoBean.getId());		
		}
		
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		map.put("user_id", accountInfoBean.getId());
		
		return new PageBean<AccountSoldBean>(accountSoldMapper.countPage(map),accountSoldMapper.fetchAccountSoldBeanList(map));
	}

	
	/**
	 * 单个对象
	 */
	@Override
	public AccountSoldBean fetAccountSoldBean(Long id) {
		Map<String,Object> map = new HashMap<String, Object>();
		Conds conds = new Conds();
		conds.equal("t.id", id);
		map.put("conds", conds);
		List<AccountSoldBean> list = accountSoldMapper.fetchAccountSoldBeanList(map);
		return list.get(0);
	}

	

}
