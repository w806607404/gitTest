package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 登录日志实体类
 * @project vtldatacenter
 * @class com.scinan.iot.vitalong.dao.domain.VtlLoginLogBean
 * @copyright www.scinan.com
 * @author Kim
 * @date 2016年12月7日
 * @description
 */
public class LoginLogBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4690222747498383057L;

	private Long id;
	
	/**
	 * 用户主键id
	 */
	private Long user_id;
	
	/**
	 * 用户名称
	 */
	private String user_name;
	
	
	/**
	 * 登录ip
	 */
	private String login_ip;
	
	private AccountInfo accountInfoBean;
	
	/**
	 * 使用终端
	 */
	private String user_agent;
	
	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}

	/**
	 * 登录时间
	 */
	private Date login_time;
	
	public LoginLogBean(Long user_id,String ip,String user_agent){
		this.user_id = user_id;
		this.login_ip = ip;
		this.user_agent = user_agent;
	}
	
	public LoginLogBean(){}

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

	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

	public AccountInfo getAccountInfoBean() {
		return accountInfoBean;
	}

	public void setAccountInfoBean(AccountInfo accountInfoBean) {
		this.accountInfoBean = accountInfoBean;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
	
}
