package com.scinan.iot.s1000.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.scinan.iot.s1000.dao.domain.FactoryBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface FactoryMapper extends GenericMapper<FactoryBean, String> {

	void deleteIds(List<String> ids);

	List<FactoryBean> fetchByIdAndFactoryName(@Param("name")String name, @Param("id")String id, @Param("code")String code);

	List<FactoryBean> fetchByRoleId(Map<String, Object> params);

	List<FactoryBean> fetchByIdsList(List<String> factoryIds);
	
	/**
	 * 功能说明:查询厂商列表
	 * @param params 归属参数
	 * @return list
	 */
	List<FactoryBean> findFactoryManagePage(Map<String, Object> params);

	List<FactoryBean> findFactoryManageNotExistByCompany(Map<String, Object> params);
	
	List<FactoryBean> fetchBySubId(Map<String, Object> params);
	
	
	List<FactoryBean>  fetchByComapnyIdList(Map<String, Object> params);
	
	FactoryBean fetchByCompanyOfName(String id);
	
	
}
