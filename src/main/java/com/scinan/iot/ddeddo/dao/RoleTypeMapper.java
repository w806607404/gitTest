package com.scinan.iot.ddeddo.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.ddeddo.dao.domain.RoleType;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：用户角色类型信息
 * @author Kim
 *
 */
public interface RoleTypeMapper extends GenericMapper<RoleType, Long> {

	/**
	 * 功能说明：角色类型列表
	 * @return
	 */
	List<RoleType> fetchByRoleTypeList(Map<String,Object> map);
	
	
	List<RoleType>  fetchByRoleTypeALL(Map<String,Object> map);

	
	
	
	
	
	

}
