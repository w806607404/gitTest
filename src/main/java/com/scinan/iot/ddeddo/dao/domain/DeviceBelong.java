package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备分配实体类
 * @project datacenter
 * @class com.scinan.iot.ddeddo.dao.domain.DeviceBelong
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class DeviceBelong implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6357605159017632789L;

	/**
	 * 主键id
	 */
	private Long id;
	
	private String device_id;
	
	private Long user_id;
	
	private Long parent_id;
	
	private Date create_time;
	
	private Integer device_level;
	
	/**
	 * 厂商ID
	 */
	private String company_id;
	
	
	public DeviceBelong(){}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDevice_id() {
		return device_id;
	}


	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}


	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public Long getParent_id() {
		return parent_id;
	}


	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	public Integer getDevice_level() {
		return device_level;
	}


	public void setDevice_level(Integer device_level) {
		this.device_level = device_level;
	}


	public String getCompany_id() {
		return company_id;
	}


	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

}

