package com.scinan.iot.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.AccountRatioMapper;
import com.scinan.iot.ddeddo.dao.AccountRatioUpdateMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountRatioUpdate;
import com.scinan.iot.service.AccountRatioUpdateService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
/**
 * 
 * 
 * @project datacenter
 * @class com.scinan.iot.service.impl.BillDetailsServiceImpl
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月06日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("accountRatioUpdateService")
public class AccountRatioUpdateServiceImpl extends GenericServiceImpl<AccountRatioUpdate, Long> implements AccountRatioUpdateService {
	static Logger logger = Logger.getLogger(AccountRatioUpdateServiceImpl.class);
	
	@Autowired
	private AccountRatioMapper accountRatioMapper;
	@Autowired
	private AccountRatioUpdateMapper accountRatioUpdateMapper;
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	
	
	@Override
	protected GenericMapper<AccountRatioUpdate, Long> getGenericMapper() {
		return accountRatioUpdateMapper;
	}
	
	
	
	 /**
     * 分成比例信息分页
     */
	@Override
	public PageBean<AccountRatioUpdate> fetchParamsByPage(Map<String,String> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(params.get("device_name"))){
			map.put("device_name",params.get("device_name"));
		}
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		
		return new PageBean<AccountRatioUpdate>(accountRatioUpdateMapper.count(map),accountRatioUpdateMapper.fetchByPage(map));
	}


	/**
	 * 删除分成比例
	 * @param ID
	 * @return
	 * @throws Exception
	 */
	public DataCenterResult deleteAccountRatioUpdate(Long id) {
		accountRatioUpdateMapper.delete(id);
		return DataCenterResult.ok();
	}

			
	
}
