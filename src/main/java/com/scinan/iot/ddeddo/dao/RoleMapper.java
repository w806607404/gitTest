package com.scinan.iot.ddeddo.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：用户角色信息
 * @author Kim
 *
 */
public interface RoleMapper extends GenericMapper<Role, Long> {

	
	void deleteSystemResources(String id);

	List<String> fetchAllUrl();
	
	List<Role>  fetchByRoleListPage(Map<String, Object> params);
	
	Integer countRoleNum(Map<String, Object> params);
	
	Integer countRoleNameOfExits(Map<String,Object> params);
	
	Integer deleteInfo(Map<String, Object> params);
	
	List<Role>  fetchRoleByList(Map<String, Object> params);
	
	Role fetchRoleById(Map<String,Object>  params);
	
	List<Integer> fetchByRoleType(Map<String, Object> params);
	

}
