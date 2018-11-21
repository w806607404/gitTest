package com.scinan.iot.s1000.dao;

import org.apache.ibatis.annotations.Param;

import com.scinan.iot.s1000.dao.domain.UserAccountBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface UserAccountMapper extends GenericMapper<UserAccountBean, Long>{
	Integer countAppKeyAndUserId(@Param("user_id")Long user_id,@Param("app_key")Long app_key);
	
}