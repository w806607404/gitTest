package com.scinan.iot.s1000.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.scinan.iot.s1000.dao.domain.UserInfo;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface UserInfoMapper extends GenericMapper<UserInfo, Long>{

	List<UserInfo> fetchUserindustryDistributions(Map<String, Object> map);

	Integer countUsers(Map<String, Object> map);

	List<UserInfo> fetchUserListByPage(Map<String, Object> map);

	Integer countUserList(Map<String, Object> map);

	UserInfo fetchNickNameAndImgByUserMobile(@Param("user_mobile")String user_mobile);
	
	Long fetchUserIdByUserName(@Param("user_name")String user_name);
	
	
	/**
	 * 功能说明：通过用户名称，查询用户信息
	 * @param map
	 * @return UserInfoBean
	 */
	UserInfo fetchUserIdByUserNickName(Map<String, Object> map);
	
	
	/**
	 * 功能说明：查询用户信息是否存在
	 * @param map
	 * @return Integer
	 */
	UserInfo fetchUserByInfoExits(Map<String, Object> map);
	
	
	
}