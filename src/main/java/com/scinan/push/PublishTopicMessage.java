package com.scinan.push;


import java.io.Serializable;

/**
 * 设备主题消息类
 * 
 * @project SNIotCommon
 * @class com.scinan.iot.common.bean.PublishTopicMessage
 * @copyright www.scinan.com
 * @author Eric
 * @date Oct 19, 2015
 * @description
 * 该类作用如下：<br>
 * 1. 封装设备上传到ConnectServer的数据<br>
 * 2. 封装ConnectServer下发到设备的命令<p>
 * 
 */
public class PublishTopicMessage implements Serializable {
	private static final long serialVersionUID = -1489984976838228397L;
	
	private String id;
	private String deviceId; // 设备ID
	private String topic; // 主题(上传数据或下发命令)
	private String data; // 大数据
	private long createDate; // 时间
	private String ip;// 源IP
	private String company_id;  // 厂商编号

	@Override
	public String toString() {
		return "PublishTopicMessage [id=" + id + ", deviceId=" + deviceId + ", topic=" + topic //
				+ ", data=" + data + ", createDate=" + createDate + ", ip=" + ip + ", company_id=" + company_id + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

}
