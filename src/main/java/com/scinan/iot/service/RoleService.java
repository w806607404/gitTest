package com.scinan.iot.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.ddeddo.dao.domain.RoleType;
import com.scinan.mybatis.support.service.GenericService;
/**
 * 角色管理业务接口类
 * 
 * @project datacenter
 * @class com.scinan.iot.service.RoleService
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月7日
 * @description
 */
public interface RoleService extends GenericService<Role, Long>{
	/**
	 * 获取分页
	 * @param params
	 * @return
	 */
	PageBean<Role> fetchParamsByPage(Map<String, String> params)throws Exception ;
	

	/**
	 * 添加角色信息
	 * @param roleBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DataCenterResult addRole(Role vtlRole,HttpServletRequest request) throws Exception;

	/**
	 * 更新角色信息
	 * @param vtlRole
	 * @return
	 * @throws Exception
	 */
	DataCenterResult updateRole(Role vtlRole,HttpServletRequest request)throws Exception ;

	/**
	 * 删除角色信息
	 * @param request 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	DataCenterResult deleteRoles(HttpServletRequest request, String ids)throws Exception ;

	
	/**
	 * 功能说明：查询角色类型列表
	 * @return List<VtlRoleType>
	 */
	public List<RoleType>  fetchVtlRoleTypeList(String id);
	
	
	/**
	 * 功能说明：查询角色详情
	 */
	public Role fetchById(Long id);
	
	/**
	 * 功能说明：查询用户归属角色列表
	 * @parameter parent_id
	 * @return VtlRole
	 */
	public List<Role> fetchRoleByList(String parent_id);
	
	
	/**
	 * 功能说明：查询角色类型列表，包含当前用户的角色类型
	 * @return List<VtlRoleType>
	*/
	public List<RoleType>  fetchVtlRoleTypeALLList(String id);
	
	
	
	
	/**
	 * 功能说明：查询角色类型列表，包含当前用户的角色类型
	 * @return List<VtlRoleType>
	 */
	public List<RoleType>  fetchByRoleTypeALL(String id);
	
	
	
	
	public List<Integer> fetchByRoleType(Integer role_type,String company_id);
	

	
}
