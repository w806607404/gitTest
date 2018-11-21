package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品价格分红比例实体类
 * @project datacenter
 * @class com.scinan.iot.zhengshang.dao.domain.AccountRatio
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class AccountRatio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5822747016394566008L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 产品类型
	 */
	private Long device_type_id;
	
	
	/**
	 * 产品价格
	 */
	private Double product_price;
	
	
	/**
	 * 代理商分成比例
	 */
	private Double agent_ratio;

	/**
	 * 经销商分成比例
	 */
	private Double dealer_ratio;

	/**
	 * 运费补贴比例
	 */
	private Double primage_ratio;

	/**
	 * 厂商id
	 */
	private String company_id;

	/**
	 * 操作时间
	 */
	private Date update_time;
	
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	
	/**
	 * 产品名称
	 */
	private String device_name;
	
	public AccountRatio(){}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Double product_price) {
		this.product_price = product_price;
	}


	public Double getAgent_ratio() {
		return agent_ratio;
	}


	public void setAgent_ratio(Double agent_ratio) {
		this.agent_ratio = agent_ratio;
	}


	public Double getDealer_ratio() {
		return dealer_ratio;
	}


	public void setDealer_ratio(Double dealer_ratio) {
		this.dealer_ratio = dealer_ratio;
	}


	public Double getprimage_ratio() {
		return primage_ratio;
	}


	public void setprimage_ratio(Double primage_ratio) {
		this.primage_ratio = primage_ratio;
	}


	public String getCompany_id() {
		return company_id;
	}


	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}


	public Date getUpdate_time() {
		return update_time;
	}


	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Long getDevice_type_id() {
		return device_type_id;
	}


	public void setDevice_type_id(Long device_type_id) {
		this.device_type_id = device_type_id;
	}


	public String getDevice_name() {
		return device_name;
	}


	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}


	
	
}

