package com.scinan.iot.s1000.dao;

import org.apache.ibatis.annotations.Param;

import com.scinan.iot.s1000.dao.domain.UserThirdPartyBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

import java.util.List;
import java.util.Map;

public interface UserThirdPartyMapper extends GenericMapper<UserThirdPartyBean, Long>{

	UserThirdPartyBean fetchByUserId(@Param("user_id")Long user_id);


	List<UserThirdPartyBean> getLocationCount(int i);

    List<UserThirdPartyBean> getUserInfoByPage(Map<String, Object> map);
}
