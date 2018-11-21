package com.scinan.push.rocket;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.log.SysoLogger;
import com.scinan.push.PublishTopicMessage;
import com.scinan.push.bean.PushSourceBean;
import com.scinan.push.bean.TopicMessage;
import com.scinan.push.mqtt.message.PublishMessage;

/**
 * RocketMQ 消息生产工厂
 * @project SNMQTTServer
 * @class com.scinan.rocket.RocketMQFactory
 * @copyright www.scinan.com
 * @author cavin
 * @date 2015年11月5日
 * @description 
 */
public class RocketMQFactory {
	private final static Logger logger = LoggerFactory.getLogger(RocketMQFactory.class);
	
	public static AtomicLong rocketMQKey = new AtomicLong(0);
	private static String getKey() {
		StringBuilder sb = new StringBuilder();
		sb.append(System.currentTimeMillis()).append(":")
		.append(rocketMQKey.getAndIncrement());
		return sb.toString();
	}
	
	
	/**
	 * 业务服务调用。
	 * 数据中心推送广告到APP； 通过MQ服务器中转
	 * @param bean
	 */
	public static void sendDataToApp(PushSourceBean bean) {
		String key = getKey();
		RocketMQProducer.getInstance().sendOneway(
				PushSourceBean.TOPIC, "webPushTags", key,
				getDataToApp(bean, key));
	}
	
	
	
	
	/**
	 * 业务数据发送到MQ服务器[web端下发命令至设备]
	 * @param bean
	 */
	public static void sendDataToDevice(TopicMessage bean) {
		String key = getKey();
		RocketMQProducer.getInstance().sendOneway("T_CMD", "apiDeviceTags", key,
				getUTF8(JSONObject.toJSONString(bean)));
	}
	
	
	
	
	/**
	 * 命令数据发送到MQ服务器
	 * @param bean
	 */
	public static void sendDataToDevice(PushSourceBean bean) {
		String key = getKey();
		RocketMQProducer.getInstance().sendOneway(
				PushSourceBean.Topic_Comm_Cluster, "webPushTags", key,
				getDataToApp(bean, key));
	}
	
	
	
	
	
	/**
	 * 功能说明：数据中心推送广告至APP
	 * @param bean 信息封装
	 * @param key  接收服务
	 * @return
	 */
	private static byte[] getDataToApp(PushSourceBean bean, String key) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("'createTime':").append(System.currentTimeMillis()).append(",");
		sb.append("'sourceType':'").append(bean.getSourceType()).append("',");
		sb.append("'sourceId':'").append(bean.getSourceId()).append("',");
		sb.append("'targetType':'").append(bean.getTargetType()).append("',");
		sb.append("'targetId':'").append(bean.getTargetId()).append("',");
		sb.append("'forward':'").append(bean.getForward()).append("',");
		sb.append("'topic':'").append(bean.getTopic()).append("',");
		sb.append("'app_key':'").append(bean.getApp_key()).append("',");
		sb.append("'ios_data':'").append(bean.getIos_data()).append("',");
		sb.append("'transmission_content':{'msgType':'").append(bean.getMsgType()).append("'},");
		sb.append("'notify_type':'").append(bean.getNotify_type()).append("',");
		sb.append("'notify_bar_msg':'").append(bean.isNotify_bar_msg()).append("'");
		sb.append("}");
		System.out.println(sb.toString());
		return getUTF8(sb.toString());
	}
	
	
	
	/**
	 * 业务服务调用。
	 * 命令发送到设备中； 通过MQ服务器中转
	 * @param bean
	 */
	public static void sendPublishToDevice(PublishMessage bean) {
		String key = getKey();
		RocketMQProducer.getInstance().sendOneway(
				RocketMQConstants.Topic_Publish_To_Device, "", key,
				getData(bean, key));
	}

	private static byte[] getData(PublishMessage bean, String key) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("'createDate':").append(System.currentTimeMillis())
				.append(",");
		sb.append("'data':{'msg_from':'parse'},");
		sb.append("'deviceId':'").append(bean.getClientId()).append("',");
		sb.append("'id':'").append(key).append("',");
		sb.append("'ip':'")
				.append(bean.getRemoteIp() == null ? "" : bean.getRemoteIp())
				.append("',");
		sb.append("'topic':'").append(bean.getTopic()).append("'");
		sb.append("}");
		
		return getUTF8(sb.toString());
	}
	private static byte[] getUTF8(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
	
	/**
	 * 发送到IOS 推送服务器
	 * 
	 * @param bean
	 */
	public static void sendIOS(PublishTopicMessage bean) {
		String key = getKey();
		RocketMQProducer.getInstance().sendOneway(
				RocketMQConstants.Topic_PushIOS, "", key,
				getData(bean, key));
	}
	
	private static byte[] getData(PublishTopicMessage bean, String key) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("'createDate':").append(bean.getCreateDate()).append(",");
		sb.append("'data':'")
				.append(bean.getData() == null ? "" : bean.getData())
				.append("',");
		sb.append("'deviceId':'").append(bean.getDeviceId()).append("',");
		sb.append("'id':'").append(key).append("',");
		sb.append("'ip':'").append(bean.getIp() == null ? "" : bean.getIp())
				.append("',");
		sb.append("'topic':'").append(bean.getTopic()).append("'");
		sb.append("}");
		return getUTF8(sb.toString());
	}
}
