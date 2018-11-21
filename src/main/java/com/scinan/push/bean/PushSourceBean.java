package com.scinan.push.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @project SNParseTopicServer
 * @class com.scinan.bean.PushSourceBean
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年4月5日
 * @description 
 */
public class PushSourceBean {
	
	public static String TOPIC = "T_Comm_Cluster";
	//接收公共的集群消息
	public static String Topic_Comm_Cluster = "T_Comm_Cluster";

//	sourceType：消息源类型，如哪个业务系统等等
//	sourceId：消息源ID
//	forward：1：发送给APP；2：发送给device
//	targetType：消息目标类型，1 ：App， 2 ：User，3 ：Device，4：厂商
//	targetId：消息目标ID：多个ID使用分号分割 
//	ios_data;：消息内容
	public String sourceType;
	public String sourceId;
	/**
	 * forward：1：发送给APP；2：发送给device
	 */
	public String forward;
	public String targetType;
	public String targetId;
	public String data;
	public String app_key;
	public String ios_data;
	

	private JSONObject transmission_content;
	
	//getui
	boolean notify_bar_msg;//通知栏点击弹出消息是否需要
	Integer notify_type; //通知类型：默认接收到推送后通知栏显示并且app弹出消息，1：只在通知栏显示，2：不需要显示只弹消息（一般情况估计不会使用）
	public String msgType;
	
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public boolean isNotify_bar_msg() {
		return notify_bar_msg;
	}
	public void setNotify_bar_msg(boolean notify_bar_msg) {
		this.notify_bar_msg = notify_bar_msg;
	}
	public Integer getNotify_type() {
		return notify_type;
	}
	public void setNotify_type(Integer notify_type) {
		this.notify_type = notify_type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String topic;
	
	public Long createTime;
	public Long getCreateTime() {
		return createTime;
	}
	
	
	public String getIos_data() {
		return ios_data;
	}
	public void setIos_data(String ios_data) {
		this.ios_data = ios_data;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("sourceType=").append(sourceType)
			.append(",sourceId=").append(sourceId)
			.append(",forward=").append(forward)
			.append(",targetType=").append(targetType)
			.append(",targetId=").append(targetId)
			.append(",topic=").append(topic)
			.append(",app_key=").append(app_key)
			.append(",data=").append(data);
		return sb.toString();
	}
	public JSONObject getTransmission_content() {
		return transmission_content;
	}
	public void setTransmission_content(JSONObject transmission_content) {
		this.transmission_content = transmission_content;
	}
}

