package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;


/**
 * 角色类型实体类
 * @project vtldatacenter
 * @class com.scinan.iot.vitalong.dao.domain.VtlRole
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年01月04日
 * @description
 */
public class RoleType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -928239771672596605L;

	/**
	 * 主键id
	 */
	private Integer id;
	
	
	/**
	 * 主键前置id
	 */
	private Integer bid;
	
	
	/**
	 * 角色类型名称
	 */
	private String name;

	
	public RoleType(){}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "VtlRoleType [id=" + id + ", name=" + name+ "]";
	}


	public Integer getBid() {
		return bid;
	}


	public void setBid(Integer bid) {
		this.bid = bid;
	}

	

	
}
