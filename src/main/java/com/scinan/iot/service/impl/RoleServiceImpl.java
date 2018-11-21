package com.scinan.iot.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.base.cache.impl.RedisSimpleCacheServiceImpl;
import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.RoleMapper;
import com.scinan.iot.ddeddo.dao.RoleTypeMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.ddeddo.dao.domain.RoleType;
import com.scinan.iot.s1000.dao.FactoryMapper;
import com.scinan.iot.service.RoleService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.utils.Common;
import com.scinan.utils.StringUtil;
/**
 * 角色管理业务相关操作接口
 * @project datacenter
 * @class com.scinan.iot.service.impl.RoleServiceImpl
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年01月05日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("roleService")
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {
	
	final static Logger logger = Logger.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleMapper vtlRoleMapper;
	@Autowired
	private RoleTypeMapper vtlRoleTypeMapper;
	@Autowired
	private AccountInfoMapper vtlAccountInfoMapper;
	@Autowired
	private FactoryMapper factoryMapper;
	
	@Autowired
	private RedisSimpleCacheServiceImpl redisSimpleService;
	
	
	
	@Override
	protected GenericMapper<Role, Long> getGenericMapper() {
		return vtlRoleMapper;
	}

	public PageBean<Role> fetchParamsByPage(Map<String, String> params) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(!StringUtil.isNull(params.get("search_role_name"))){
			map.put("name", params.get("search_role_name"));
		}
		if(!StringUtil.isNull(params.get("parent_id"))){
			map.put("parent_id", params.get("parent_id"));
		}
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		
		List<Role> roles = vtlRoleMapper.fetchByRoleListPage(map);
		
		Integer count = vtlRoleMapper.countRoleNum(map);
		return new PageBean<Role>(count,roles);
	}
	
	

	
	/**
	 * 功能说明：添加角色信息
	 * @param vtlRole
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DataCenterResult addRole(Role vtlRole,HttpServletRequest request) throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		
		int roleType = accountInfoBean.getRole_type().intValue();   //当前用户的角色级别
		
		//判断该角色名称是否存在
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", vtlRole.getName());
		if(roleType>=1){
		   params.put("company_id", accountInfoBean.getCompany_id());
		}
		int result = vtlRoleMapper.countRoleNameOfExits(params);
		if(result>0){
			return DataCenterResult.build(-1);
		}
		
		vtlRole.setParent_id(Integer.parseInt(accountInfoBean.getParent_role_id()+""));
		vtlRole.setParent_role_path(accountInfoBean.getParent_role_path());
		if(accountInfoBean.getRole_type().intValue()==0){
			vtlRole.setRole_type(1);
		}else{
			vtlRole.setRole_type(vtlRole.getRole_type());
		}
		
		vtlRole.setCreate_time(new Date());
		vtlRole.setUpdate_time(new Date());
		vtlRole.setCompany_id(vtlRole.getCompany_id());
		
		if(accountInfoBean.getRole_type().intValue()>0){
			if(roleType==vtlRole.getRole_type()){
				vtlRole.setParent_userid(accountInfoBean.getId().intValue());
			}else{
				vtlRole.setParent_userid(0);
			}
			vtlRole.setCompany_id(accountInfoBean.getCompany_id());
		}else{
			vtlRole.setCompany_id(vtlRole.getCompany_id());
			vtlRole.setParent_userid(0);
		}
		
		vtlRoleMapper.save(vtlRole);
		return DataCenterResult.ok();
	}
	
	/**
	 * 功能说明：修改角色信息
	 */
	public DataCenterResult updateRole(Role vtlRole,HttpServletRequest request) throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		vtlRole.setParent_id(Integer.parseInt(accountInfoBean.getParent_role_id()+""));
		vtlRole.setParent_role_path(null);
		vtlRole.setUpdate_time(new Date());
		vtlRoleMapper.update(vtlRole);
		return DataCenterResult.ok();
	}
	
	
	/**
	 * 功能说明：删除角色信息
	 */
	public DataCenterResult deleteRoles(HttpServletRequest request,String ids) throws Exception {
		
		//VtlAccountInfo accountInfoBean = Common.getVtlAccountInfo(request);
		if(!StringUtil.isNull(ids)){
			Map<String,Object> map = new HashMap<String, Object>();
			
			map.put("role_id", ids);
			int count = vtlAccountInfoMapper.countUserByNum(map);
			if(count>0){
				return DataCenterResult.build(600);
			}
			/***********该角色下没有绑定的用户，方可进行删除***************/
			map.clear();
			map.put("id", ids);
			vtlRoleMapper.deleteInfo(map);
		}
		return DataCenterResult.ok();
	}
	
	
	/**
	 * 功能说明：查询角色类型列表
	 * @return List<VtlRoleType>
	 */
	public List<RoleType>  fetchVtlRoleTypeList(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtil.isNull(id)){
			map.put("id", Integer.parseInt(id));
		}
		return vtlRoleTypeMapper.fetchByRoleTypeList(map);
	}
	
	
	
	/**
	 * 功能说明：查询角色类型列表，包含当前用户的角色类型
	 * @return List<VtlRoleType>
	 */
	public List<RoleType>  fetchVtlRoleTypeALLList(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtil.isNull(id)){
			map.put("id", Integer.parseInt(id));
		}
		return vtlRoleTypeMapper.fetchByRoleTypeList(map);
	}
	
	
	
	/**
	 * 功能说明：查询角色类型列表，包含当前用户的角色类型
	 * @return List<VtlRoleType>
	 */
	public List<RoleType>  fetchByRoleTypeALL(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtil.isNull(id)){
			map.put("id", Integer.parseInt(id));
		}
		return vtlRoleTypeMapper.fetchByRoleTypeALL(map);
	}
	
	
	
	
	
	/**
	 * 功能说明：查询角色详情
	 */
	public Role fetchById(Long id){
		return vtlRoleMapper.fetchById(id);
	}
	
	
	/**
	 * 功能说明：查询用户归属角色列表
	 * @parameter parent_id
	 * @return VtlRole
	 */
	@Override
	public List<Role> fetchRoleByList(String parent_id){
		Map<String, Object> params = new HashMap<String,Object>();
		if(!StringUtil.isNull(parent_id)){
		    params.put("parent_id", parent_id);
		}
		return vtlRoleMapper.fetchRoleByList(params);
	}

	/**
	 * 根据角色类型查询角色集合
	 */
	@Override
	public List<Integer> fetchByRoleType(Integer role_type, String company_id) {
		Map<String, Object> params = new HashMap<String,Object>();		
		params.put("role_type", role_type);	
		params.put("company_id", company_id);
		
		return vtlRoleMapper.fetchByRoleType(params);
	}

	
	
	
	
	
}
