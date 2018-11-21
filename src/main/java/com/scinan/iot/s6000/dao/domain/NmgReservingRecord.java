package com.scinan.iot.s6000.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约装机实体类---糯米购
 * 
 * @project datacenter
 * @class com.scinan.iot.s6000.dao.domain.NmgReservingRecord
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年10月26日
 * @description
 */
public class NmgReservingRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3111536467374542423L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 用户ID
	 */
	private Long user_id;
	
	/**
	 * 联系人
	 */
	private String contacts;
	
	
	/**
	 * 联系电话
	 */
	private String contact_phone;
	
	
	/**
	 * 所属区域ID
	 */
	private String area_id;
	
	/**
	 * 所属区域名称	
	 */
	private String area_name;
	
	/**
	 * 详细地址
	 */
	private String address;
	
	
	/**
	 * 订单ID
	 */
	private String order_id;
	
	
	/**
	 * 厂商id
	 */
	private String company_id;
	
	/**
	 * 厂商唯一标识
	 */
	private Long app_key;
	
	/**
	 * 设备类型名称
	 */
	private String device_id;
	
	

	private Date create_time;
	
	
	private Date update_time;
	
	
	/**
	 * 归属代理商路径
	 */
	private String user_path;
	
	
	 /**
	  * 代理商名称
	  */
	 private String dealer_name;
	 
	 
	 /**
	  * 状态: 0 等待处理;1 处理中; 2 已完成; 3 已取消
	  */
	 private Integer  status;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getUser_path() {
		return user_path;
	}

	public void setUser_path(String user_path) {
		this.user_path = user_path;
	}

	public String getDealer_name() {
		return dealer_name;
	}

	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getApp_key() {
		return app_key;
	}

	public void setApp_key(Long app_key) {
		this.app_key = app_key;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	
	
}