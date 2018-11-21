package com.scinan.iot.ddeddo.dao;

import java.util.Map;

import com.scinan.iot.ddeddo.dao.domain.UserBasicInfo;
import com.scinan.mybatis.support.mapper.GenericMapper;

/**
 * UserBasicInfoMapper接口类
 * @author Kim
 */
public interface UserBasicInfoMapper extends GenericMapper<UserBasicInfo, Long> {
	
	   Integer saveInfo(UserBasicInfo userBasicInfo);
	
	   Integer updateInfo(UserBasicInfo userBasicInfo);
	   
	   UserBasicInfo fetchById(Map<String,Object> map);
	   
	   UserBasicInfo fetchByMtlInfo(Map<String,Object> map);
	
}
