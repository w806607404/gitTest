package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户退换货实体类
 * @project datacenter
 * @class com.scinan.iot.zhengshang.dao.domain.BillMonthDate
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class UserBack implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7589328615370408324L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 产品编号
	 */
	private String device_id;
	
	/**
	 * 产品名称
	 */
	private String device_name;
	
	/**
	 * 产品型号
	 */
	private String device_type;
	
	/**
	 * 填写人ID
	 */
	private Long user_id;
	
	
	/**
	 * 填写人账号
	 */
	private String user_name;
	
	
	/**
	 * 填写人类型（经销商/代理商/厂商）
	 */
	private String user_type;
	/**
	 * 退货人姓名
	 */
	private String back_name;
	
	
	/**
	 * 退货人联系方式
	 */
	private String back_contact;
	
	/**
	 * 退换货类型
	 */
	private String back_type;
	
	/**
	 * 退货原因
	 */
	private String cause;
	
	/**
	 * 省
	 */
	private String province_id;	
	private String province_name;
	    	    	    
	 /**
     * 市
     */
	private String city_id;
	private String city_name;
	    	    	    
	 /**
     * 区
     */
	private String district_id;
	private String district_name;
	
	/**
	 * 入账时间
	 */
	private Date create_time;
	
	
	public UserBack(){}


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


	public String getBack_name() {
		return back_name;
	}


	public void setBack_name(String back_name) {
		this.back_name = back_name;
	}


	public String getBack_contact() {
		return back_contact;
	}


	public void setBack_contact(String back_contact) {
		this.back_contact = back_contact;
	}


	public String getBack_type() {
		return back_type;
	}


	public void setBack_type(String back_type) {
		this.back_type = back_type;
	}


	public String getCause() {
		return cause;
	}


	public void setCause(String cause) {
		this.cause = cause;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	/**
	 * @return the device_id
	 */
	public String getDevice_id() {
		return device_id;
	}


	/**
	 * @param device_id the device_id to set
	 */
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}


	/**
	 * @return the device_name
	 */
	public String getDevice_name() {
		return device_name;
	}


	/**
	 * @param device_name the device_name to set
	 */
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}


	/**
	 * @return the device_type
	 */
	public String getDevice_type() {
		return device_type;
	}


	/**
	 * @param device_type the device_type to set
	 */
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}


	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}


	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	/**
	 * @return the user_type
	 */
	public String getUser_type() {
		return user_type;
	}


	/**
	 * @param user_type the user_type to set
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}


	/**
	 * @return the province_id
	 */
	public String getProvince_id() {
		return province_id;
	}


	/**
	 * @param province_id the province_id to set
	 */
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}


	/**
	 * @return the province_name
	 */
	public String getProvince_name() {
		return province_name;
	}


	/**
	 * @param province_name the province_name to set
	 */
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}


	/**
	 * @return the city_id
	 */
	public String getCity_id() {
		return city_id;
	}


	/**
	 * @param city_id the city_id to set
	 */
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}


	/**
	 * @return the city_name
	 */
	public String getCity_name() {
		return city_name;
	}


	/**
	 * @param city_name the city_name to set
	 */
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}


	/**
	 * @return the district_id
	 */
	public String getDistrict_id() {
		return district_id;
	}


	/**
	 * @param district_id the district_id to set
	 */
	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}


	/**
	 * @return the district_name
	 */
	public String getDistrict_name() {
		return district_name;
	}


	/**
	 * @param district_name the district_name to set
	 */
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	
}

