package com.scinan.iot.s1000.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * app授权认证bean
 * @author shen
 * 2015-07-08 9:16:57 
 */
public class AppAuthorize implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2254578722481463815L;
	/**
	 * 访问token
	 */
	private String access_token;
	/**
	 * 应用key
	 */
	private Long app_key;
	/**
	 * 用户id
	 */
	private Long user_id;
	/**
	 *  0:无权限，1：有权限
	 */
	private Integer authorize_flag;
	/**
	 * 过期
	 */
	private Integer expires_in;
	/**
	 * 更新时间
	 */
	private Date update_time;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 手机的唯一标识
	 */
	private String imei;
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
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
	public Integer getAuthorize_flag() {
		return authorize_flag;
	}
	public void setAuthorize_flag(Integer authorize_flag) {
		this.authorize_flag = authorize_flag;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
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
	@Override
	public String toString() {
		return "AppAuthorize [access_token=" + access_token + ", app_key=" + app_key + ", user_id=" + user_id + ", authorize_flag=" + authorize_flag + ", expires_in=" + expires_in + ", update_time=" + update_time + ", create_time=" + create_time + "]";
	}
	
	
}
