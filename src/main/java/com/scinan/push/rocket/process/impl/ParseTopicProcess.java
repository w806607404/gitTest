package com.scinan.push.rocket.process.impl;

import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.scinan.push.PublishTopicMessage;
import com.scinan.push.PushServiceFactory;
import com.scinan.push.rocket.process.RocketMQProcess;
import com.scinan.utils.RedisUtil;
import com.scinan.utils.StringUtil;

/**
 * 处理topic： "parse_topic";
 * 
 * @project SNParseTopicServer
 * @class com.scinan.rocket.process.impl.ParseTopicProcess
 * @copyright www.scinan.com
 * @author cavin
 * @date 2015年11月9日
 * @description
 */
public class ParseTopicProcess implements RocketMQProcess {

	private final static Logger logger = LoggerFactory
			.getLogger(ParseTopicProcess.class);


	@Override
	public void doProcess(MessageExt msg, ConsumeConcurrentlyContext context)
			throws Exception {
		// TODO Auto-generated method stub
		PublishTopicMessage tempPublishMessage = JSON.parseObject(new String(
				msg.getBody(), "UTF-8"), PublishTopicMessage.class);

		//消息存储的时间，当作 消息的接收时间
		tempPublishMessage.setCreateDate(msg.getStoreTimestamp());
		
//		redisCacheS00(tempPublishMessage.getTopic());
		
		// 具体处理每个 topic 数据，详细的topic格式定了之后，在做处理 TODO
		if (this.isNeedParse(tempPublishMessage)) {
			
			//消息先推送出去
			PushServiceFactory.send(tempPublishMessage);
			
			// 根据不同厂商，不同类型的设别，进行不同的处理
			String deviceId = tempPublishMessage.getDeviceId();
			String company = deviceId.substring(0, 4);
			//SNDeviceBase device = SNDeviceFactory.getDeviceInstance(company);
//			if (device != null) {
//				device.doProcess(tempPublishMessage);
//			}
			
			//this.do360(tempPublishMessage);
		}
//		ProcessConstantTest.counterRocket_parse.getAndIncrement();
	}
	
	/**
	 * 处理360业务
	 * @param tempPublishMessage
	 */
//	private void do360(PublishTopicMessage tempPublishMessage){
//		PublishMessage	publishMessage = new PublishMessage(
//					tempPublishMessage.getTopic(), "".getBytes());
//		publishMessage.setClientId(tempPublishMessage.getDeviceId());
//		publishMessage.setRemoteIp(tempPublishMessage.getIp());
//		// 只有少量厂商需要处理
//		if (publishMessage.getClientId().startsWith("1001")) {
//			ProcessMessageConstants.process360PublishMessageLinkedQueue
//					.add(publishMessage);
//		}
//	}

	
	private boolean isNeedParse(PublishTopicMessage publishMessage){
		 if (publishMessage.getTopic().indexOf("/update/") != -1) {
             // 设备固件升级
//             ProcessMessageConstants.HARDWARE_UPDATE_PROCESS_PUBLISH_QUEUE.add(publishMessage);
         } else if (publishMessage.getTopic().indexOf("/systemtime/") != -1) {
             // 请求系统时间
             //ProcessMessageConstants.SYSTEMTIME_PROCESS_PUBLISH_QUEUE.add(publishMessage);
         } else if (publishMessage.getTopic().indexOf("/type/") != -1) {
             // 更新设备类型
             //ProcessMessageConstants.DEVICE_TYPE_PROCESS_PUBLISH_QUEUE.add(publishMessage);
         } else if (publishMessage.getTopic().indexOf("/version/") != -1) {
             // 更新设备类型
             //ProcessMessageConstants.MATERIALS_VERSION_PROCESS_PUBLISH_QUEUE.add(publishMessage);
		}else{
			return true;
		}
		 
		return false;
	}
	
	private void redisCacheS00(String topic){
		if (topic.indexOf("/S00") != -1) {
			// 判断全状态时间，如果状态改变，则更新时间  (使用StringTokenizer，提高性能)
			 StringTokenizer token=new StringTokenizer(topic,"/");
			
			// 获取最近一次状态，判断是否相等
			if (token.countTokens() == 4) {
				String deviceId = token.nextToken();
				String sensor = token.nextToken();
				String sensor_type = token.nextToken();
				String sensor_data = token.nextToken();
				
				StringBuilder sbKey = new StringBuilder();
				sbKey.append(deviceId).append(StringUtil.UNDERLINE).append(sensor).append(StringUtil.UNDERLINE).append(sensor_type).append("_0");
				
//				String data = RedisUtil.get(sbKey.toString());
//				if(data!=null){
//					String realData = data.substring(data.indexOf(",") + 1);
//					if (!realData.equals(sensor_data)) {
						StringBuilder sballStatusKey = new StringBuilder();
						//全状态时间
						sballStatusKey.append(deviceId).append(StringUtil.UNDERLINE).append(sensor).append(StringUtil.UNDERLINE).append(sensor_type).append("_AS");
						RedisUtil.set(sballStatusKey.toString(), String.valueOf(System.currentTimeMillis()));
//					}
//				}
				StringBuilder value = new StringBuilder();
				value.append(System.currentTimeMillis()).append(StringUtil.COMMA).append(sensor_data);
				RedisUtil.set(sbKey.toString(), value.toString());
			}
		}
		
	}
}
