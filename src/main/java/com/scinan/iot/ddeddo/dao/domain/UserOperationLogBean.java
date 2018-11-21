package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户操作日志实体类
 * 
 * @project datacenter
 * @class com.scinan.iot.s9000.dao.domain.UserOperationLogBean
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月11日
 * @description
 */
public class UserOperationLogBean implements Serializable{

	private static final long serialVersionUID = -2065256915126567027L;
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 用户主键id
	 */
	private Long user_id;
	/**
	 * 标题 用户执行的相关操作
	 */
	private String title;
	/**
	 * 请求url
	 */
	private String request_uri;
	/**
	 * 请求参数
	 */
	private String params;
	/**
	 * 异常信息
	 */
	private String exception;
	/**
	 * 请求ip地址
	 */
	private String remote_addr;
	/**
	 * 请求耗时
	 */
	private Long cost_time;
	/**
	 * 请求是否成功状态
	 */
	private String state;
	/**
	 * 请求模块名
	 */
	private String module;
	
	private Date create_time;
	
	
	//关联字段
	private String account_name;
	

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRequest_uri() {
		return request_uri;
	}

	public void setRequest_uri(String request_uri) {
		this.request_uri = request_uri;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getRemote_addr() {
		return remote_addr;
	}

	public void setRemote_addr(String remote_addr) {
		this.remote_addr = remote_addr;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getCost_time() {
		return cost_time;
	}

	public void setCost_time(Long cost_time) {
		this.cost_time = cost_time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	@Override
	public String toString() {
		return "UserOperationLogBean [id=" + id + ", user_id=" + user_id
				+ ", title=" + title + ", request_uri=" + request_uri
				+ ", params=" + params + ", exception=" + exception
				+ ", remote_addr=" + remote_addr + ", cost_time=" + cost_time
				+ ", state=" + state + ", module=" + module + ", create_time="
				+ create_time + ", account_name=" + account_name + "]";
	}
	
	
}
