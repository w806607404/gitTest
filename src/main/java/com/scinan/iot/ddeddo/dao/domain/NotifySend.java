package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 发货通知实体类
 * @project datacenter
 * @class com.scinan.iot.ddeddo.dao.domain.NotifySend
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class NotifySend implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8521290624304621459L;

	private Long id;

	/**
	 * 发送人id
	 */
	private Long send_userId;
	
	/**
	 * 发送人名称
	 */
	private String send_user_nickname;	
	/**
	 * 接收人id
	 */
	private Long receive_userId;
	
	/**
	 * 接收人名称
	 */
	private String user_nickname;
	
	/**
	 * 接收人账号
	 */
	private String receive_user_name;
	/**
	 * 设备类型
	 */
	private Integer device_type;
	
	/**
	 * 设备名称
	 */
	private String device_name;
	
	/**
	 * 发货数量
	 */
	private Integer count;
	
	/**
	 * 订单总金额
	 */
	private BigDecimal amount;

	/**
	 * 收货人姓名
	 */
	private String user_name;
	
	/**
	 * 收货人联系方式
	 */
	private String user_contact;

	/**
	 * 收货人详细地址
	 */
	private String user_address;

	/**
	 * 通知类型(0:线下转账，1:正常通知)
	 */
	private Integer notify_type;

	/**
	 * 物流信息
	 */
	private String shipping;

	/**
	 * 状态(0:待发货,1:已发货,2:已完成,3:待确认,4:未确认,5:缺货)
	 */
	private Integer status;
	
	/**
	 * 厂商ID
	 */
	private String company_id;
	
	/**
	 * 通知时间
	 */
	private Date create_time;
	
	/**
	 * 响应时间
	 */
	private Date receipt_time;
	
	/**
	 * app_key
	 */
	private Long app_key;

	
	//扩展字段
	/**
	 * 省份ID
	 */
	private String province_id;
	
	/**
	 * 城市ID
	 */
	private String city_id;
		
	/**
	 * 区县ID
	 */
	private String district_id; 
	
	/**
	 * 省份ID
	 */
	private String province_name;
	
	/**
	 * 城市ID
	 */
	private String city_name;
		
	/**
	 * 区县ID
	 */
	private String district_name;
	
	private Integer role_type;
	
	public NotifySend(){}





	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public Long getSend_userId() {
		return send_userId;
	}





	public void setSend_userId(Long send_userId) {
		this.send_userId = send_userId;
	}





	public Long getReceive_userId() {
		return receive_userId;
	}





	public void setReceive_userId(Long receive_userId) {
		this.receive_userId = receive_userId;
	}





	public Integer getDevice_type() {
		return device_type;
	}





	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}





	public Integer getCount() {
		return count;
	}





	public void setCount(Integer count) {
		this.count = count;
	}





	public String getUser_name() {
		return user_name;
	}





	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}





	public String getUser_contact() {
		return user_contact;
	}





	public void setUser_contact(String user_contact) {
		this.user_contact = user_contact;
	}





	public String getUser_address() {
		return user_address;
	}





	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}





	public Integer getNotify_type() {
		return notify_type;
	}





	public void setNotify_type(Integer notify_type) {
		this.notify_type = notify_type;
	}





	public String getShipping() {
		return shipping;
	}





	public void setShipping(String shipping) {
		this.shipping = shipping;
	}





	public Integer getStatus() {
		return status;
	}





	public void setStatus(Integer status) {
		this.status = status;
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


	public Date getReceipt_time() {
		return receipt_time;
	}


	public void setReceipt_time(Date receipt_time) {
		this.receipt_time = receipt_time;
	}





	public BigDecimal getAmount() {
		return amount;
	}





	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}





	public Long getApp_key() {
		return app_key;
	}





	public void setApp_key(Long app_key) {
		this.app_key = app_key;
	}





	/**
	 * @return the send_user_nickname
	 */
	public String getSend_user_nickname() {
		return send_user_nickname;
	}





	/**
	 * @param send_user_nickname the send_user_nickname to set
	 */
	public void setSend_user_nickname(String send_user_nickname) {
		this.send_user_nickname = send_user_nickname;
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





	/**
	 * @return the receive_user_name
	 */
	public String getReceive_user_name() {
		return receive_user_name;
	}





	/**
	 * @param receive_user_name the receive_user_name to set
	 */
	public void setReceive_user_name(String receive_user_name) {
		this.receive_user_name = receive_user_name;
	}





	/**
	 * @return the role_type
	 */
	public Integer getRole_type() {
		return role_type;
	}





	/**
	 * @param role_type the role_type to set
	 */
	public void setRole_type(Integer role_type) {
		this.role_type = role_type;
	}
	
	
	
}

