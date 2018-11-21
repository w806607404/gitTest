package com.scinan.iot.s9000.dao.domain;

import java.io.Serializable;
/**
 * 地市
 * 
 * @project datacenter
 * @class com.scinan.iot.s9000.dao.domain.CityBean
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年8月1日
 * @description
 */
public class CityBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2977799797662962265L;

	private Long id;
	
	private String province_code;
	
	private String city_code;
	
	private String name_cn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince_code() {
		return province_code;
	}

	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	

	
	
}
