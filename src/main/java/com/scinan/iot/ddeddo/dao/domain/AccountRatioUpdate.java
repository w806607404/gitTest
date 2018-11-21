package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账号信息分配比例
 * @project datacenter
 * @class com.scinan.iot.ddeddo.dao.domain.AccountRatio
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class AccountRatioUpdate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6324921489706045259L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 产品类型
	 */
	private Long device_type_id;
	
	/**
	 * 产品类型名称
	 */
	private String device_name;
	
	/**
	 * 产品价格
	 */
	private Double product_price;
	
	/**
	 * 新产品价格
	 */
	private Double product_price_new;
	
	
	/**
	 * 代理商分成比例
	 */
	private Double agent_ratio;
	
	/**
	 * 新代理商分成比例
	 */
	private Double agent_ratio_new;

	/**
	 * 经销商分成比例
	 */
	private Double dealer_ratio;
	
	/**
	 * 新经销商分成比例
	 */
	private Double dealer_ratio_new;

	/**
	 * 运费补贴比例
	 */
	private Double primage_ratio;

	/**
	 * 新运费补贴比例
	 */
	private Double primage_ratio_new;
	/**
	 * 状态(0:未读,1:已读)
	 */
	private Integer read_status;

	public Double getPrimage_ratio() {
		return primage_ratio;
	}


	public void setPrimage_ratio(Double primage_ratio) {
		this.primage_ratio = primage_ratio;
	}


	public Double getPrimage_ratio_new() {
		return primage_ratio_new;
	}


	public void setPrimage_ratio_new(Double primage_ratio_new) {
		this.primage_ratio_new = primage_ratio_new;
	}


	/**
	 * 厂商id
	 */
	private String company_id;

	/**
	 * 创建时间
	 */
	private Date create_time;
	
	
	public AccountRatioUpdate(){}


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

	public String getCompany_id() {
		return company_id;
	}


	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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


	public Double getProduct_price_new() {
		return product_price_new;
	}


	public void setProduct_price_new(Double product_price_new) {
		this.product_price_new = product_price_new;
	}


	public Double getAgent_ratio_new() {
		return agent_ratio_new;
	}


	public void setAgent_ratio_new(Double agent_ratio_new) {
		this.agent_ratio_new = agent_ratio_new;
	}


	public Double getDealer_ratio_new() {
		return dealer_ratio_new;
	}


	public void setDealer_ratio_new(Double dealer_ratio_new) {
		this.dealer_ratio_new = dealer_ratio_new;
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


	public Integer getRead_status() {
		return read_status;
	}


	public void setRead_status(Integer read_status) {
		this.read_status = read_status;
	}


	
	
}

