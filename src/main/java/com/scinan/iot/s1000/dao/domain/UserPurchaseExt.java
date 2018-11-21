package com.scinan.iot.s1000.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class UserPurchaseExt implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8777734161872338968L;
	private Long id;
	private Long user_id;
	private String purchase_time;
	private String models;
	private Date create_time;
	
	
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
	public String getPurchase_time() {
		return purchase_time;
	}
	public void setPurchase_time(String purchase_time) {
		this.purchase_time = purchase_time;
	}
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

    
	
}
