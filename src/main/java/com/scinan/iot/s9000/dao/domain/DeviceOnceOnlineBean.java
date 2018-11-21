package com.scinan.iot.s9000.dao.domain;

import java.io.Serializable;
import java.util.Date;

import com.scinan.iot.s1000.dao.domain.FactoryBean;
import com.scinan.iot.s6000.dao.domain.DeviceTypeBean;
/**
 * 
 * 
 * @project datacenter
 * @class com.scinan.iot.s9000.dao.domain.DeviceOnceOnlineBean
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月25日
 * @description
 */
public class DeviceOnceOnlineBean implements Serializable{

	private static final long serialVersionUID = -751827123692688235L;

	private Long id;
	
	private String device_id;
	
	private Long online_time;
	
	private Long offline_time;
	
	private Long diff_time;
	
	private String company_id;
	
	private String type;
	
	private String begin_hour;
	
	private String end_hour;
	
	private Date create_time;
	
	private String reason;
	
	private String reason_desc;
	
	//=============业务属性==================
	private String name;
	
	private FactoryBean factoryBean;
	
	private String company_name;
	
	private String device_name;

	private DeviceTypeBean deviceTypeBean;
	
	private String date;
	
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

	public Long getOnline_time() {
		return online_time;
	}

	public void setOnline_time(Long online_time) {
		this.online_time = online_time;
	}

	public Long getOffline_time() {
		return offline_time;
	}

	public void setOffline_time(Long offline_time) {
		this.offline_time = offline_time;
	}

	public Long getDiff_time() {
		return diff_time;
	}

	public void setDiff_time(Long diff_time) {
		this.diff_time = diff_time;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "DeviceOnceOnlineBean [id=" + id + ", device_id=" + device_id
				+ ", online_time=" + online_time + ", offline_time="
				+ offline_time + ", diff_time=" + diff_time + ", company_id="
				+ company_id + ", type=" + type + ", create_time="
				+ create_time + ", name=" + name + ", device_name=" + device_name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FactoryBean getFactoryBean() {
		return factoryBean;
	}

	public void setFactoryBean(FactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public DeviceTypeBean getDeviceTypeBean() {
		return deviceTypeBean;
	}

	public void setDeviceTypeBean(DeviceTypeBean deviceTypeBean) {
		this.deviceTypeBean = deviceTypeBean;
	}

	public String getBegin_hour() {
		return begin_hour;
	}

	public void setBegin_hour(String begin_hour) {
		this.begin_hour = begin_hour;
	}

	public String getEnd_hour() {
		return end_hour;
	}

	public void setEnd_hour(String end_hour) {
		this.end_hour = end_hour;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		if(null != reason){
			if(reason.contains("1")){
				this.reason_desc = "TCP连接 正常断开";
			}else if(reason.contains("2")){
				this.reason_desc = "未发送connect消息断开";
			}else if(reason.contains("3")){
				this.reason_desc = "设备id和密码验证未通过断开";
			}else if(reason.contains("4")){
				this.reason_desc = "设备id安全检查未通过断开";
			}else if(reason.contains("5")){
				this.reason_desc = "收到模块的断开连接请求断开";
			}else if(reason.contains("6")){
				this.reason_desc = "TCP连接异常断开";
			}else if(reason.contains("7")){
				this.reason_desc = "业务处理异常断开";
			}else if(reason.contains("8")){
				this.reason_desc = "心跳中断断开";
			}else if(reason.contains("9")){
				this.reason_desc = "消息解码错误断开";
			}
		}
		this.reason = reason;
	}

	public String getReason_desc() {
		return reason_desc;
	}

	public void setReason_desc(String reason_desc) {
		this.reason_desc = reason_desc;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
