package com.scinan.iot.s1000.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.scinan.iot.s1000.dao.domain.AppInfoBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface AppInfoMapper extends GenericMapper<AppInfoBean, Long> {

	void deleteIDs(List<String> ids);

	String fetchByAppKey(@Param("app_key")Long app_key);

	List<AppInfoBean> fetchByCompanyId(Map<String, Object> map);

	List<AppInfoBean>  fetchByAppkeyList(Map<String, Object> map);
}
