package com.scinan.iot.s1000.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * app授权认证bean
 * @author shen
 * 2015-07-08 9:16:57 
 */
public class UserToken2 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7428532774555812584L;
	/**
	 * 
	 */
	
	/**
	 * 访问token
	 */
	private String token;
	/**
	 * 应用key
	 */
	private Long app_key;
	/**
	 * 用户id
	 */
	private Long user_id;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "AppAuthorize [token=" + token + ", app_key=" + app_key + ", user_id=" + user_id + ",create_time=" + create_time + "]";
	}
	
	
}
