package com.scinan.iot.s9000.dao.domain;

import java.io.Serializable;
/**
 * 省市
 * 
 * @project datacenter
 * @class com.scinan.iot.s9000.dao.domain.ProvinceBean
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年8月1日
 * @description
 */
public class ProvinceBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -114983414072013370L;

	private Long id;
	
	private String province_code;
	
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
	

	
	
}
