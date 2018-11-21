/**
 * @Description:
 * @Package: com.scinan.iot.ddeddo.dao.domain 
 * @author: 吴广   
 * @date: 2018年7月12日 下午4:48:48 
 */
package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 该类的功能描述
 * @author: 吴广
 * @date: 2018年7月12日 下午4:48:48 
 */
public class AccountBackBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7653184439077361462L;

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
	 * 退换货人ID
	 */
	private Long user_id;
	
	
	/**
	 * 退换货人名称
	 */
	private String user_nickname;
	
	/**
	 * 退换货人账号
	 */
	private String user_name;
	/**
	 * 退换货人类型
	 */
	private String user_type;
	
	
	/**
	 * 父id
	 */
	private Long parent_id;
	
	/**
	 * 0:退货，1：换货
	 */
	private Integer back_type;
	
	
	/**
	 * 物流单号信息
	 */
	private String shipping;
	
	
	/**
	 * 退换货原因
	 */
	private String cause;
	
	
	
	/**
	 * 0:申请中，1：已确认，2：已完成
	 */
	private Integer status;
	
	
	/**
	 * 申请时间
	 */
	private Date create_time;
	
	/**
	 * 省
	 */
	private String province_name;
	    	    	    
	 /**
     * 市
     */
	private String city_name;
	    	    	    
	 /**
     * 区
     */
	private String district_name;
	
	
	public AccountBackBean(){}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}


	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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


	/**
	 * @return the parent_id
	 */
	public Long getParent_id() {
		return parent_id;
	}


	/**
	 * @param parent_id the parent_id to set
	 */
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}


	/**
	 * @return the back_type
	 */
	public Integer getBack_type() {
		return back_type;
	}


	/**
	 * @param back_type the back_type to set
	 */
	public void setBack_type(Integer back_type) {
		this.back_type = back_type;
	}


	/**
	 * @return the shipping
	 */
	public String getShipping() {
		return shipping;
	}


	/**
	 * @param shipping the shipping to set
	 */
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}


	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}


	/**
	 * @param cause the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}


	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	/**
	 * @return the create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}


	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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

