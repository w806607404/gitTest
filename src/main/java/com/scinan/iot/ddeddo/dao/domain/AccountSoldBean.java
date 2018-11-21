package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 销售记录返回结果类
 * @project datacenter
 * @class com.scinan.iot.ddeddo.dao.domain.AccountSold
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class AccountSoldBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6852294430672808596L;

	
		

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
	 * 售出人名称
	 */
	private String user_nickname;
	
	/**
	 * 售出人账号
	 */
	private String user_name;
	
	/**
	 * 售出人类型
	 */
	private String user_type;
	
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
	 * 创建时间
	 */
	private Date create_time;
	
	 /**
     * 省ID
     */
    private String province_id;
    
    /**
     * .
     */
    private String province_name;
    
    /**
     * .
     */
    private String city_id;
    
    /**
     * .
     */
    private String city_name;
    
    /**
     * .
     */
    private String district_id;
    
    /**
     * .
     */
    private String district_name;
	
	
	public AccountSoldBean(){}


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


	/**
	 * @return the user_nickname
	 */
	public String getUser_nickname() {
		return user_nickname;
	}


	/**
	 * @param user_nickname the user_nickname to set
	 */
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
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

	
}

