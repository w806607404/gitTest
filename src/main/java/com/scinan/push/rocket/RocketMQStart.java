package com.scinan.push.rocket;

import com.scinan.base.config.ConfigureFile;
import com.scinan.push.rocket.process.impl.DeviceStatusProcess;
import com.scinan.push.rocket.process.impl.ParseTopicProcess;

/**
 * MQ的生产者、消费者 初始化。
 * @project SNParseTopicServer
 * @class com.scinan.rocket.RocketServerStart
 * @copyright www.scinan.com
 * @author cavin
 * @date 2015年11月9日
 * @description 
 */
public class RocketMQStart {

	public static void init(){
		String address = ConfigureFile.getInstance().getProperty(RocketMQConstants.RocketMQ_address); //PropsUtil.get(RocketMQConstants.RocketMQ_address);
		//PropsUtil.get(RocketMQConstants.RocketMQ_address);
		
		// 订阅日志消息
//		new RocketMQConsumer(address,
//				RocketMQConstants.Topic_communicationlog,
//				new CommunicationLogPocess()).start();
		
		// 订阅设备状态消息
		new RocketMQConsumer(address,
				RocketMQConstants.Topic_device_status,
				new DeviceStatusProcess()).start();
		
		// 定义消息解析
		new RocketMQConsumer(address,
				RocketMQConstants.Topic_parse_topic,
				new ParseTopicProcess()).start();
		
		
		//初始化生产者
		RocketMQProducer.getInstance();
		
	}
}
