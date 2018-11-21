package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 申请撤销代理/经销实体类
 * @project datacenter
 * @class com.scinan.iot.ddeddo.dao.domain.AccountRepeal
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class AccountRepeal implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4612793725804003529L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 申请人
	 */
	private Long user_id;
	
	/**
	 * 父id
	 */
	private Long parent_id;

	/**
	 * 撤销原因
	 */
	private String reason;

	private Date create_time;
	
	/**
	 * 省份名称
	 */
	private String province_name;
	/**
	 * 城市名称
	 */
	private String city_name;	
	/**
	 * 区县名称
	 */
	private String district_name;
	
	/**
	 * 代理商真实姓名
	 */
	private String agent_name;
	
	/**
	 * 用户账号
	 */
	private String user_name;
	
	/**
	 * 用户昵称
	 */
	private String user_nickname;
	
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


	public String getProvince_name() {
		return province_name;
	}


	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}


	public String getCity_name() {
		return city_name;
	}


	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}


	public String getDistrict_name() {
		return district_name;
	}


	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}


	public String getAgent_name() {
		return agent_name;
	}


	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}


	public String getAgent_phone() {
		return agent_phone;
	}


	public void setAgent_phone(String agent_phone) {
		this.agent_phone = agent_phone;
	}


	public String getRole_name() {
		return role_name;
	}


	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}


	/**
	 * 代理商电话
	 */
	private String agent_phone;
	
	private String role_name;
	
	public AccountRepeal(){}


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


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	
}

