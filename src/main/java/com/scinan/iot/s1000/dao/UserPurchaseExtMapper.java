package com.scinan.iot.s1000.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.s1000.dao.domain.UserPurchaseExt;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：设备购买信息
 * @author kimsun
 * @date 2016-12-05
 */
public interface UserPurchaseExtMapper extends GenericMapper<UserPurchaseExt, Long> {
    
	public UserPurchaseExt fetchById(Integer id);
	public List<UserPurchaseExt> fetchByUserInfoList(Map<String,Object>  map);
	
	public int count(Map<String,Object>  map);
	
	
}
