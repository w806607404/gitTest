package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 分红账单详情
 * @project datacenter
 * @class com.scinan.iot.ddeddo.dao.domain.AccountBillDetail
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class AccountBillDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3807280065544921425L;
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 出售人id
	 */
	private Long sale_userId;
	
	/**
	 * 受益人id
	 */
	private Long benefit_userId;
	
	/**
	 * 设备id
	 */
	private String device_id;
	
	/**
	 * 分红金额
	 */
	private BigDecimal amount;

	/**
	 * 产品价格
	 */
	private BigDecimal product_price;
	
	/**
	 * 明细类型(0:扣款，1：分红)
	 */
	private Integer type;
	
	/**
	 * 厂商id
	 */
	private String company_id;
	
	/**
	 * 分成时间
	 */
	private Date create_time;
	
	//额外信息
	/**
	 * 出售人昵称
	 */
	private String sale_user_nickname;
	
	/**
	 * 出售人类型
	 */
	private String sale_user_type;
	
	/**
	 * 销售区域省
	 */
	private String sale_province_name;
	
	/**
	 * 销售区域市
	 */
	private String sale_city_name;
	
	/**
	 * 销售区域区
	 */
	private String sale_district_name;
	
	/**
	 * 受益人账号
	 */
	private String benefit_user_name;

	/**
	 * 受益人名称
	 */
	private String benefit_user_nickname;
	
	/**
	 * 受益人类型
	 */
	private String benefit_user_type;

	
	
	public String getSale_user_nickname() {
		return sale_user_nickname;
	}

	public void setSale_user_nickname(String sale_user_nickname) {
		this.sale_user_nickname = sale_user_nickname;
	}

	public String getSale_user_type() {
		return sale_user_type;
	}

	public void setSale_user_type(String sale_user_type) {
		this.sale_user_type = sale_user_type;
	}

	public String getSale_province_name() {
		return sale_province_name;
	}

	public void setSale_province_name(String sale_province_name) {
		this.sale_province_name = sale_province_name;
	}

	public String getSale_city_name() {
		return sale_city_name;
	}

	public void setSale_city_name(String sale_city_name) {
		this.sale_city_name = sale_city_name;
	}

	public String getSale_district_name() {
		return sale_district_name;
	}

	public void setSale_district_name(String sale_district_name) {
		this.sale_district_name = sale_district_name;
	}

	public String getBenefit_user_name() {
		return benefit_user_name;
	}

	public void setBenefit_user_name(String benefit_user_name) {
		this.benefit_user_name = benefit_user_name;
	}

	public String getBenefit_user_nickname() {
		return benefit_user_nickname;
	}

	public void setBenefit_user_nickname(String benefit_user_nickname) {
		this.benefit_user_nickname = benefit_user_nickname;
	}

	public String getBenefit_user_type() {
		return benefit_user_type;
	}

	public void setBenefit_user_type(String benefit_user_type) {
		this.benefit_user_type = benefit_user_type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSale_userId() {
		return sale_userId;
	}

	public void setSale_userId(Long sale_userId) {
		this.sale_userId = sale_userId;
	}

	public Long getBenefit_userId() {
		return benefit_userId;
	}

	public void setBenefit_userId(Long benefit_userId) {
		this.benefit_userId = benefit_userId;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getProduct_price() {
		return product_price;
	}

	public void setProduct_price(BigDecimal product_price) {
		this.product_price = product_price;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	
	
	
}

