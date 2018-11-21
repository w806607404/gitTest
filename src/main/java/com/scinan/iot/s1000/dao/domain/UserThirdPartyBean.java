package com.scinan.iot.s1000.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户第三方信息实体类
 * 
 * @project datacenter
 * @class com.scinan.iot.s1000.dao.domain.UserThirdPartyBean
 * @copyright www.scinan.com
 * @author Zola
 * @date 2016年12月5日
 * @description
 */

public class UserThirdPartyBean implements Serializable{

	private static final long serialVersionUID = 3306630774329002371L;
	
	private Long id;
	
	private String user_id;
	
	private Integer third_party_type;
	
	private String third_party_openid;
	
	private Date create_time;
	
	private String third_party_nickname;
	
	private String third_party_avatar;

	private String user_name;

	private String user_email;

	private String user_phone;

	private String province_id;

	private String province_name;

	private String detail_address;
	
	private Integer device_count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getThird_party_type() {
		return third_party_type;
	}

	public void setThird_party_type(Integer third_party_type) {
		this.third_party_type = third_party_type;
	}

	public String getThird_party_openid() {
		return third_party_openid;
	}

	public void setThird_party_openid(String third_party_openid) {
		this.third_party_openid = third_party_openid;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getThird_party_nickname() {
		return third_party_nickname;
	}

	public void setThird_party_nickname(String third_party_nickname) {
		this.third_party_nickname = third_party_nickname;
	}

	public String getThird_party_avatar() {
		return third_party_avatar;
	}

	public void setThird_party_avatar(String third_party_avatar) {
		this.third_party_avatar = third_party_avatar;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getProvince_id() {
		return province_id;
	}

	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	public Integer getDevice_count() {
		return device_count;
	}

	public void setDevice_count(Integer device_count) {
		this.device_count = device_count;
	}
	
	
}