package com.scinan.iot.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.iot.s1000.dao.UserInfoExtMapper;
import com.scinan.iot.s1000.dao.domain.UserInfoExtBean;
import com.scinan.iot.service.UserInfoExtService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
/**
 * 
 * 
 * @project datacenter
 * @class com.scinan.iot.service.impl.AccountInfoServiceImpl
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月6日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("userInfoExtService")
public class UserInfoExtServiceImpl extends GenericServiceImpl<UserInfoExtBean, Long> implements UserInfoExtService {
	static Logger logger = Logger.getLogger(UserInfoExtServiceImpl.class);
	
	@Autowired
	private UserInfoExtMapper userInfoExtMapper;
	
	
	@Override
	protected GenericMapper<UserInfoExtBean, Long> getGenericMapper() {
		return userInfoExtMapper;
	}

	
	
	
	
	
}
