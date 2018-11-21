package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 角色信息实体类
 * @project vtldatacenter
 * @class com.scinan.iot.vitalong.dao.domain.VtlRole
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年01月04日
 * @description
 */
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2123923501344631920L;


	/**
	 * 主键id
	 */
	private Long id;
	
	
	/**
	 * 角色名称
	 */
	private String name;
	
	
	/**
	 * 父级角色ID
	 */
	private Integer parent_id;
	

	/**
	 * 父级角色ID
	 */
	private String parent_role_path;
	
	
	/**
	 * 角色类型
	 */
	private Integer role_type;
	
	/**
	 * 角色名称
	 */
	private String role_name;
	
	
	/**
	 * 创建时间.
	 */
	private Date create_time;
	/**
	 * 更新时间.
	 */
	private Date update_time;
	
	/**
	 * 备注.
	 */
	private String note;
	
	
	/**
	 * 厂商ID
	 */
	private String company_id;
	
	/**
	 * 上级用户ID
	 */
	private Integer parent_userid;
	
	
	public Role(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent_role_path() {
		return parent_role_path;
	}

	public void setParent_role_path(String parent_role_path) {
		this.parent_role_path = parent_role_path;
	}

	public Integer getRole_type() {
		return role_type;
	}

	public void setRole_type(Integer role_type) {
		this.role_type = role_type;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}


	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	
	
	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
	

	public Integer getParent_userid() {
		return parent_userid;
	}

	public void setParent_userid(Integer parent_userid) {
		this.parent_userid = parent_userid;
	}

	@Override
	public String toString() {
		return "VtlRole [id=" + id + ", name=" + name + ",parent_id="+parent_id
				+ ", parent_role_path=" + parent_role_path + ", role_type=" + role_type
				+ ",parent_id="+parent_id+"]";
	}


	
}
