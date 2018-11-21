package com.scinan.iot.s6000.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 滤芯更换提醒实体类
 * 
 * @project datacenter
 * @class com.scinan.iot.s6000.dao.domain.FilterelementReplaceLog
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年10月24日
 * @description
 */
public class FilterelementReplaceLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3837255532730477503L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 厂商id
	 */
	private String company_id;
	
	/**
	 * 设备类型名称
	 */
	private String device_id;
	
	

	private Date create_time;
	
	
	/**
	 * 归属代理商路径
	 */
	private String user_path;
	
	
	 /**
	  * 代理商名称
	  */
	 private String dealer_name;
	 
	 
	 private String province;
		
	 private String city;
		
	 private String country;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getUser_path() {
		return user_path;
	}

	public void setUser_path(String user_path) {
		this.user_path = user_path;
	}

	public String getDealer_name() {
		return dealer_name;
	}

	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	

	
	
}