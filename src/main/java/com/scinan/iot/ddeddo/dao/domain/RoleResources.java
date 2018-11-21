package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;


/**
 * 角色信息实体类
 * @project vtldatacenter
 * @class com.scinan.iot.vitalong.dao.domain.VtlRole
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年01月04日
 * @description
 */
public class RoleResources implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7367585791986892325L;


	/**
	 * 角色id
	 */
	private Integer role_id;
	
	
	/**
	 * 菜单ID
	 */
	private String res_id;
	
	
	

	public Integer getRole_id() {
		return role_id;
	}



	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}



	public String getRes_id() {
		return res_id;
	}



	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}




	@Override
	public String toString() {
		return "VtlRoleResources [role_id=" + role_id + ", res_id=" + res_id+ "]";
	}


	
}
