package com.scinan.iot.s1000.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class FactoryBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8963132053314578448L;
	/**
	 * 厂商id
	 */
	private String id;
	/**
	 * 厂商名称
	 */
	private String name;
	/**
	 * 厂商代码
	 */
	private String code;
	/**
	 * 
	 */
	private String order_item_sn;
	/**
	 * 所属行业id
	 */
	private String industry_id;
	/**
	 * 创建时间
	 */
	private Date update_time;

	/**
	 * 所属行业名称
	 */
	private String industry_name;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrder_item_sn() {
		return order_item_sn;
	}

	public void setOrder_item_sn(String order_item_sn) {
		this.order_item_sn = order_item_sn;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getIndustry_name() {
		return industry_name;
	}

	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}

	@Override
	public String toString() {
		return "FactoryBean [id=" + id + ", name=" + name + ", code=" + code
				+ ", order_item_sn=" + order_item_sn + ", industry_id="
				+ industry_id + ", update_time=" + update_time
				+ ", industry_name=" + industry_name
				+ "]";
	}



}