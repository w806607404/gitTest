package com.scinan.push.rocket;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.scinan.spring.SpringUtil;
import com.scinan.push.rocket.process.RocketMQProcess;

public class RocketMQConsumer {
	private final static Logger logger = LoggerFactory.getLogger(RocketMQConsumer.class);
	/**
	 * MQ服务器地址
	 */
	String address ;
	
	String topic;
	
	/**
	 * topic 业务处理接口
	 */
	RocketMQProcess rocketMQProcess;
	public RocketMQConsumer(String address, String topic, RocketMQProcess rocketMQProcess){
		this.address = address;
		this.topic = topic;
		this.rocketMQProcess = rocketMQProcess;
	}
	
	/**
	 * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
	 * 但是实际PushConsumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法<br>
	 */
	public  void start() {
		/**
		 * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
		 * 注意：ConsumerGroupName需要由应用来保证唯一
		 */
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("MQParseConsumer1"+topic );
		consumer.setNamesrvAddr(address);
		
		//使用集群方式，每台机器使用不同名称的消费实例
		consumer.setMessageModel(MessageModel.CLUSTERING);
		consumer.setInstanceName(topic+consumer.getClientIP());
		try {

			/**
			 * 订阅指定topic下的所有tags
			 */
			consumer.subscribe(topic, "*");
		} catch (MQClientException e) {
			logger.error("RocketMQ consumer connection error :", e);
			return;
		}
		consumer.setConsumeMessageBatchMaxSize(100);
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			/**
			 * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
			 */
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for(MessageExt msg : msgs){
					try {
						rocketMQProcess.doProcess(msg, context);
					} catch (Exception e) {
						logger.error("rocketMQProcess.doProcess error :", e);
						
//						if(e.getMessage().contains("TooManyResultsException")
//								|| e.getMessage().contains("DuplicateKeyException")){
//							
//						}else{
							//writeException(msg, e);
//						}
						
					}
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		/**
		 * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 */
		try {
			consumer.start();
			logger.info("Consumer Topic:" + topic +" is start..................");
		} catch (MQClientException e) {
			logger.error("RocketMQ consumer start error :", e);
		}
	}
	
	/**
	 * 处理异常的消息写入数据库；以便后续分析。
	 * @param msg
	 * @param stackTraceElements
	 */
//	private void writeException(MessageExt msg, Exception exception) {
//		RocketmqMsg4err bean = new RocketmqMsg4err();
//		try {
//			bean.setContent(new String(msg.getBody(), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//		}
//		StringWriter writer = new StringWriter();
//		PrintWriter pw = new PrintWriter(writer);
//		exception.printStackTrace(pw);
//		bean.setException_msg(writer.getBuffer().toString());
//		bean.setMq_keys(msg.getKeys());
//		bean.setTopic(topic);
//		RocketmqMsg4errService.RocketmqMsg4err_queue.add(bean);
//		try {
//			writer.close();
//			pw.close();
//		} catch (IOException e) {
//		}
//	}
	
	public static void main(String[] args) {
		//SpringUtil.i();
		RocketMQConsumer cons = new RocketMQConsumer(null ,"testTopic", null);
		MessageExt msg = new MessageExt();
		msg.setKeys("ewrewrwerewrwerf");
		msg.setBody("sss".getBytes());
		//cons.writeException(msg, new Exception("test .."));
	}
}
