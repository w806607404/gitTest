package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户购货实体类
 * @project datacenter
 * @class com.scinan.iot.zhengshang.dao.domain.Purchase
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class Purchase implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3396914401001271233L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 购货人id
	 */
	private Long user_id;
	
	/**
	 * 父id
	 */
	private Long parent_id;
	
	/**
	 * 设备类型
	 */
	private Integer device_type;
	
	/**
	 * 订单数量
	 */
	private Integer count;
	
	/**
	 * 订单金额
	 */
	private BigDecimal amount;
	
	/**
	 * 是否开具发票（0：否，1：是）
	 */
	private Integer invoice;
	
	/**
	 * 物流信息
	 */
	private String shipping;
	
	/**
	 * 状态（0：代发货，1：已发货，2：已完成）
	 */
	private Integer status;
	
	/**
	 * 支付类型（0：线下转账，1：分红金额，2：补贴金额）
	 */
	private Integer pay_type;
	
	/**
	 * 操作时间
	 */
	private Date create_time;
	
	
	/**
	 * 更新时间
	 */
	private Date update_time;
	
	/**
	 * app_key
	 */
	private Long app_key;
	
	//额外信息
	/**
	 * 购货人姓名
	 */
	private String purchase_name;
	
	public String getPurchase_name() {
		return purchase_name;
	}


	public void setPurchase_name(String purchase_name) {
		this.purchase_name = purchase_name;
	}


	public String getPurchase_account() {
		return purchase_account;
	}


	public void setPurchase_account(String purchase_account) {
		this.purchase_account = purchase_account;
	}


	public String getDevice_name() {
		return device_name;
	}


	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}


	/**
	 * 购货人账号
	 */
	private String purchase_account;
	
	/**
	 * 产品名称
	 */
	private String device_name;
	
	
	
	public Purchase(){}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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

	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public Integer getInvoice() {
		return invoice;
	}


	public void setInvoice(Integer invoice) {
		this.invoice = invoice;
	}


	public String getShipping() {
		return shipping;
	}


	public void setShipping(String shipping) {
		this.shipping = shipping;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getPay_type() {
		return pay_type;
	}


	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
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


	public Integer getDevice_type() {
		return device_type;
	}


	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}


	public Long getApp_key() {
		return app_key;
	}


	public void setApp_key(Long app_key) {
		this.app_key = app_key;
	}

}

