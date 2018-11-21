package com.scinan.push.bean;


/**
 * 
 * @project SNCmdConsumeServer
 * @class com.scinan.TopicMessage
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年3月15日
 * @description 
 */
public class TopicMessage {
	private String ip;// add by eric 2016.10.13

    public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 
	 */

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


	private String topic;
	private String deviceId;
	private String data;
	/**
     * .
     */
    private String token;
    
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

    
    /**
     * 发送时间.
     */
    private Long createDate;
    public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	private String id;
}
