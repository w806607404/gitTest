package com.scinan.iot.s9000.dao.domain;

import java.io.Serializable;
/**
 * 国家
 * 
 * @project datacenter
 * @class com.scinan.iot.s9000.dao.domain.CountryBean
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年8月1日
 * @description
 */
public class CountryBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2087461085234154964L;

	/**
	 * 
	 */

	private Long id;
	
	private String country_code;
	
	private String name_cn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

	
	

	
	
}
