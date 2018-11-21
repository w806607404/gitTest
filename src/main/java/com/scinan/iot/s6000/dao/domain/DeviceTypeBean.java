package com.scinan.iot.s6000.dao.domain;

import java.io.Serializable;
import java.util.Date;

import com.scinan.iot.s1000.dao.domain.FactoryBean;

/**
 * 设备类型实体类
 * 
 * @project datacenter
 * @class com.scinan.iot.s6000.dao.domain.DeviceTypeBean
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月20日
 * @description
 */
public class DeviceTypeBean implements Serializable{

	private static final long serialVersionUID = 8949064594944376421L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 厂商id
	 */
	private String company_id;
	
	/**
	 * 厂商类型
	 */
	private Integer type;
	
	/**
	 * 设备类型名称
	 */
	private String device_name;
	
	/**
	 * app分类id
	 */
	private Long app_category_id;

	private Date create_time;
	
	private Date update_time;
	
	//=================业务关联字段=====================
	
	private FactoryBean factoryBean;
	
	
	private Long modify_id;
	
	private String factory_name;
	
	private String code;
	
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public Long getApp_category_id() {
		return app_category_id;
	}

	public void setApp_category_id(Long app_category_id) {
		this.app_category_id = app_category_id;
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

	public FactoryBean getFactoryBean() {
		return factoryBean;
	}

	public void setFactoryBean(FactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}

	
	@Override
	public String toString() {
		return "DeviceTypeBean [id=" + id + ", company_id=" + company_id
				+ ", type=" + type + ", device_name=" + device_name
				+ ", app_category_id=" + app_category_id + ", create_time="
				+ create_time + ", update_time=" + update_time
				+ ", factoryBean=" + factoryBean + "]";
	}

	public Long getModify_id() {
		return modify_id;
	}

	public void setModify_id(Long modify_id) {
		this.id = modify_id;
		this.modify_id = modify_id;
	}

	public String getFactory_name() {
		return factory_name;
	}

	public void setFactory_name(String factory_name) {
		this.factory_name = factory_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}