package com.scinan.iot.s1000.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * user_Account实体类
 * 
 * @project datacenter
 * @class com.scinan.iot.s1000.dao.domain.UserAccountBean
 * @copyright www.scinan.com
 * @author Zola
 * @date 2016年11月1日
 * @description
 */

public class UserAccountBean implements Serializable{
	
	private static final long serialVersionUID = 8949064594944376421L;
	
	private Long id;
	
	private Long app_key;
	
	private Long user_id;
	
	private Long inviter_id;
	
	private String invit_code;
	
	private BigDecimal balance_pay;
	
	private BigDecimal balance_present;
	
	private Date create_time;
	
	private Date update_time;
	
	private String user_name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApp_key() {
		return app_key;
	}

	public void setApp_key(Long app_key) {
		this.app_key = app_key;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getInviter_id() {
		return inviter_id;
	}

	public void setInviter_id(Long inviter_id) {
		this.inviter_id = inviter_id;
	}

	public String getInvit_code() {
		return invit_code;
	}

	public void setInvit_code(String invit_code) {
		this.invit_code = invit_code;
	}

	public BigDecimal getBalance_pay() {
		return balance_pay;
	}

	public void setBalance_pay(BigDecimal balance_pay) {
		this.balance_pay = balance_pay;
	}

	public BigDecimal getBalance_present() {
		return balance_present;
	}

	public void setBalance_present(BigDecimal balance_present) {
		this.balance_present = balance_present;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}