package com.scinan.iot.s1000.dao;

import com.scinan.iot.s1000.dao.domain.UserInfoExtBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface UserInfoExtMapper extends GenericMapper<UserInfoExtBean, Long>{

	UserInfoExtBean fetchUserInfoExts(Long id);
	
}