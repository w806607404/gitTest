package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 销售实体类
 * @project datacenter
 * @class com.scinan.iot.ddeddo.dao.domain.AccountSold
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class AccountSold implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1454979091442173723L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 设备id
	 */
	private String device_id;
	
	
	/**
	 * 销售人id
	 */
	private Long user_id;
	
	
	/**
	 * 接收人姓名
	 */
	private String receive_name;

	/**
	 * 接收人联系方式
	 */
	private String receive_contact;

	/**
	 * 厂商id
	 */
	private String company_id;

	/**
	 * app_key
	 */
	private Long app_key;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 省份ID
	 */
	private String province_id;

	/**
	 * 省份名称
	 */
	private String province_name;
	
	/**
	 * 城市ID
	 */
	private String city_id;
	/**
	 * 城市名称
	 */
	private String city_name;	
	/**
	 * 区县ID
	 */
	private String district_id;
	/**
	 * 区县名称
	 */
	private String district_name;
	/**
	 * 激活状态(0:未激活，1:激活)
	 */
	private int _join;
	/**
	 * 激活时间
	 */
	private Date _join_time;
	
	
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


	public String getCity_id() {
		return city_id;
	}


	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}


	public String getCity_name() {
		return city_name;
	}


	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}


	public String getDistrict_id() {
		return district_id;
	}


	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}


	public String getDistrict_name() {
		return district_name;
	}


	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}


	public AccountSold(){}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDevice_id() {
		return device_id;
	}


	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}


	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public String getReceive_name() {
		return receive_name;
	}


	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}


	public String getReceive_contact() {
		return receive_contact;
	}


	public void setReceive_contact(String receive_contact) {
		this.receive_contact = receive_contact;
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


	public Long getApp_key() {
		return app_key;
	}


	public void setApp_key(Long app_key) {
		this.app_key = app_key;
	}


	public int get_join() {
		return _join;
	}


	public void set_join(int _join) {
		this._join = _join;
	}


	public Date get_join_time() {
		return _join_time;
	}


	public void set_join_time(Date _join_time) {
		this._join_time = _join_time;
	}

	
}

