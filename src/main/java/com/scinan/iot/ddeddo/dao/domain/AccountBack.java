package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家退货表
 * @project datacenter
 * @class com.scinan.iot.zhengshang.dao.domain.AccountBack
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class AccountBack implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5910541321793447778L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 设备id
	 */
	private String device_id;
	
	
	/**
	 * 退货人ID
	 */
	private Long user_id;
	
	
	/**
	 * 父id
	 */
	private Long parent_id;
	
	
	/**
	 * 物流信息
	 */
	private String shipping;
	
	
	/**
	 * 退换货原因
	 */
	private String cause;
	
	/**
	 * 0:退货，1：换货
	 */
	private Integer back_type;
	
	/**
	 * 0:申请中，1：已确认，2：已完成
	 */
	private Integer status;
	
	
	
	/**
	 * 产品名称
	 */
	private String device_name;
	
	/**
	 * 产品型号
	 */
	private String device_type;
	

		
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
	 * 申请时间
	 */
	private Date create_time;
	
	
	public AccountBack(){}


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


	public String getDevice_id() {
		return device_id;
	}


	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}


	public Long getParent_id() {
		return parent_id;
	}


	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}


	public String getShipping() {
		return shipping;
	}


	public void setShipping(String shipping) {
		this.shipping = shipping;
	}


	public String getCause() {
		return cause;
	}


	public void setCause(String cause) {
		this.cause = cause;
	}


	public Integer getBack_type() {
		return back_type;
	}


	public void setBack_type(Integer back_type) {
		this.back_type = back_type;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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

