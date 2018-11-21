package com.scinan.iot.s1000.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * app信息实体类
 * 
 * @project datacenter
 * @class com.scinan.iot.s1000.dao.domain.AppInfoBean
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月20日
 * @description
 */
public class AppInfoBean implements Serializable{

	private static final long serialVersionUID = 7124991488950527669L;
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * app唯一键
	 */
	private Long app_key;
	
	/**
	 * 操作人id
	 */
	private String user_id;
	
	/**
	 * app名称
	 */
	private String app_name;
	
	/**
	 * app描述
	 */
	private String app_description;
	
	/**
	 * app秘钥
	 */
	private String app_secret;
	
	/**
	 * 厂商id
	 */
	private String company_id;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 更新时间
	 */
	private Date update_time;
	
	private Integer app_type;
	
	private Integer app_progress;
	
	private Integer app_level;
	
	
	//业务字段
	private String account_name;//操作账号
	
	private String factory_name; //厂商名称

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_description() {
		return app_description;
	}

	public void setApp_description(String app_description) {
		this.app_description = app_description;
	}

	public String getApp_secret() {
		return app_secret;
	}

	public void setApp_secret(String app_secret) {
		this.app_secret = app_secret;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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

	@Override
	public String toString() {
		return "AppInfoBean [app_key=" + app_key + ", user_id=" + user_id
				+ ", app_name=" + app_name + ", app_description="
				+ app_description + ", app_secret=" + app_secret
				+ ", company_id=" + company_id + ", create_time=" + create_time
				+ ", update_time=" + update_time + "]";
	}

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

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getFactory_name() {
		return factory_name;
	}

	public void setFactory_name(String factory_name) {
		this.factory_name = factory_name;
	}

	public Integer getApp_type() {
		return app_type;
	}

	public void setApp_type(Integer app_type) {
		this.app_type = app_type;
	}

	public Integer getApp_progress() {
		return app_progress;
	}

	public void setApp_progress(Integer app_progress) {
		this.app_progress = app_progress;
	}

	public Integer getApp_level() {
		return app_level;
	}

	public void setApp_level(Integer app_level) {
		this.app_level = app_level;
	}
	
}