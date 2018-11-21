/**
 * @Description:
 * @Package: com.scinan.iot.ddeddo.dao.domain 
 * @author: 吴广   
 * @date: 2018年7月9日 上午11:39:22 
 */
package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 用户购货记录返回结果集
 * @author: 吴广
 * @date: 2018年7月9日 上午11:39:22 
 */
public class PurchaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 634504265844828303L;
	
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 购货人id
	 */
	private Long user_id;
	
	/**
	 * 购货人id
	 */
	private String user_nickname;
	
	/**
	 * 购货人id
	 */
	private String user_name;
	
	/**
	 * 购货人id
	 */
	private String device_name;
	
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
	
	
	
	
	public PurchaseBean(){}


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


	/**
	 * @return the user_nickname
	 */
	public String getUser_nickname() {
		return user_nickname;
	}


	/**
	 * @param user_nickname the user_nickname to set
	 */
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}


	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}


	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	/**
	 * @return the device_name
	 */
	public String getDevice_name() {
		return device_name;
	}


	/**
	 * @param device_name the device_name to set
	 */
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	
	
}


