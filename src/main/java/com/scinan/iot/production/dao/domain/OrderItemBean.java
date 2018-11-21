package com.scinan.iot.production.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单条目实体类
 * 
 * @project datacenter
 * @class com.scinan.iot.production.dao.domain.OrderItemBean
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月20日
 * @description
 */
public class OrderItemBean implements Serializable{

	private static final long serialVersionUID = 4657665938845273858L;
	
	/**
	 * 主键id
	 */
	private Integer id;
	
	/**
	 * 订单id
	 */
	private Integer order_id;
	
	/**
	 * mac地址
	 */
	private String mac;
	
	/**
	 * 流水号
	 */
	private String serialno;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 条码（扫码用）
	 */
	private String bar_code;
	
	/**
	 * 设备id
	 */
	private String device_id;
	
	/**
	 * 生成时间
	 */
	private Date create_time;
	
	
	//++++++++++++++++业务字段++++++++++++++++++++
	/**
	 * 物料编号
	 */
	private String material_sn;
	
	/**
	 * 版本号
	 */
	private String version_code;
	
	/**
	 * 芯片型号
	 */
	private String code;
	
	/**
	 * 厂商名称
	 */
	private String name;
	
	/**
	 * 厂商id
	 */
	private String factory_id;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBar_code() {
		return bar_code;
	}

	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
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

	public String getMaterial_sn() {
		return material_sn;
	}

	public void setMaterial_sn(String material_sn) {
		this.material_sn = material_sn;
	}

	public String getVersion_code() {
		return version_code;
	}

	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFactory_id() {
		return factory_id;
	}

	public void setFactory_id(String factory_id) {
		this.factory_id = factory_id;
	}

	@Override
	public String toString() {
		return "OrderItemBean [id=" + id + ", order_id=" + order_id + ", mac="
				+ mac + ", serialno=" + serialno + ", password=" + password
				+ ", bar_code=" + bar_code + ", device_id=" + device_id
				+ ", create_time=" + create_time + ", material_sn="
				+ material_sn + ", version_code=" + version_code + ", code="
				+ code + ", name=" + name + ", factory_id=" + factory_id + "]";
	}
	
}