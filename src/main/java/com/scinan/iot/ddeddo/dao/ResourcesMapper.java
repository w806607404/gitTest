package com.scinan.iot.ddeddo.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.ddeddo.dao.domain.Resource;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明:菜单权限信息
 * @author Kim
 *
 */
public interface ResourcesMapper extends GenericMapper<Resource, Long> {

	void deleteSystemResources(String id);

	List<Resource> fetchSystemResourcesByRoleId(Map<String, Object> params);

	List<String> fetchAllUrl();
	
	/**
	 * 功能说明：超级管理员查询菜单列表
	 * @param params
	 * @return List<VtlResource>
	 */
	List<Resource>   fetchSystemResourcesBySupperRole(Map<String, Object> map);
	
	
	/**
	 * 功能说明：查询当前用户所对应的操作权限
	 * @param params
	 * @return
	 */
	List<Resource>  findUserRoleResources(Map<String, Object> params);
	
	

}
