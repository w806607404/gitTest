package com.scinan.iot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.UserOperationLogMapper;
import com.scinan.iot.ddeddo.dao.domain.UserOperationLogBean;
import com.scinan.iot.service.UserOperationLogService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;

@Transactional(propagation = Propagation.REQUIRED)
@Service("userOperationLogService")
public class UserOperationLogServiceImpl extends GenericServiceImpl<UserOperationLogBean, Long> implements UserOperationLogService {
	static Logger logger = Logger.getLogger(UserOperationLogServiceImpl.class);
	
	@Autowired
	private UserOperationLogMapper userOperationLogMapper;
	
	@Override
	protected GenericMapper<UserOperationLogBean, Long> getGenericMapper() {
		return userOperationLogMapper;
	}

	public PageBean<UserOperationLogBean> fetchParamsByPage(
			Map<String, String> params) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		Conds conds = new Conds();
		
		if(StringUtils.isNotEmpty(params.get("search_account_name"))){
			conds.like("t1.account_name", params.get("search_account_name"));
		}
		
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		
		List<UserOperationLogBean> userOperationLogBeans = userOperationLogMapper.fetchByPage(map);
		Integer count = userOperationLogMapper.count(map);
		return new PageBean<UserOperationLogBean>(count,userOperationLogBeans);
		
	}
	
}
