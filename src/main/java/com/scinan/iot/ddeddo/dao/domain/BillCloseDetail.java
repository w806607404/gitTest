package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 申请结算信息
 * @project datacenter
 * @class com.scinan.iot.ddeddo.dao.domain.BillCloseDetail
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class BillCloseDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6772648589633856850L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 用户ID
	 */
	private Long user_id;
	
	
	/**
	 * 父id
	 */
	private Long parent_id;
	
	
	/**
	 * 结算金额种类(0:分红金额，1：补贴金额)
	 */
	private Integer close_type;
	
	/**
	 * 结算金额
	 */
	private BigDecimal  amount;
	
	/**
	 * 结算状态(0:申请中，1：已结算，2：已完成)
	 */
	private Integer status;
	
	/**
	 * 申请时间
	 */
	private Date create_time;
	
	/**
	 * 结算时间
	 */
	private Date close_time;
	/**
	 * 提现到(0:支付宝，1：微信，2：银行卡)
	 */
	private Integer target;
	 /**
     * 申请人名称
     */
	private String user_nickname;
	 /**
     * 申请人账号
     */
	private String user_name;
	 /**
     * 申请人类型
     */
	private String role_name;
	
	
	
	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_nickname() {
		return user_nickname;
	}


	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}


	public BillCloseDetail(){}


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


	public Integer getClose_type() {
		return close_type;
	}


	public void setClose_type(Integer close_type) {
		this.close_type = close_type;
	}

	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	public Date getClose_time() {
		return close_time;
	}


	public void setClose_time(Date close_time) {
		this.close_time = close_time;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public Integer getTarget() {
		return target;
	}


	public void setTarget(Integer target) {
		this.target = target;
	}


	public String getRole_name() {
		return role_name;
	}


	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	
}

