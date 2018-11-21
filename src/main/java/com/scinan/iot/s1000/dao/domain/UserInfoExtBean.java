package com.scinan.iot.s1000.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息实体类
 * 
 * @project datacenter
 * @class com.scinan.iot.s1000.dao.domain.UserInfoBean
 * @copyright www.scinan.com
 * @author Zola
 * @date 2016年7月22日
 * @description
 */

public class UserInfoExtBean implements Serializable{

	private static final long serialVersionUID = 3306630774329002371L;
	
	private Long id;
	
	private String userid;
	
	private String regip;
	
	private String currip;
	
	private Date update_time;
	
	private Date create_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRegip() {
		return regip;
	}

	public void setRegip(String regip) {
		this.regip = regip;
	}

	public String getCurrip() {
		return currip;
	}

	public void setCurrip(String currip) {
		this.currip = currip;
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
	
}