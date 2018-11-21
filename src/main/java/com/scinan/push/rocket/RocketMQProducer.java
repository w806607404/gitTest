/**
 * 
 */
package com.scinan.push.rocket;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.scinan.base.config.ConfigureFile;

/**
 * <p>Title:RocketMQClient</p>
 * <p>Description: MQ生产者</p>
 * @author Cavin
 * @date 2015年10月16日 上午10:55:23
 */
public class RocketMQProducer {
	private final static Logger logger = LoggerFactory.getLogger(RocketMQProducer.class);
	
	private  DefaultMQProducer producer = null;
	
	private RocketMQProducer(){
		String address = ConfigureFile.getInstance().getProperty(RocketMQConstants.RocketMQ_address); //PropsUtil.get(RocketMQConstants.RocketMQ_address);
		logger.info("rockeMq address:" + address);
		producer = new DefaultMQProducer("Producer");
		producer.setNamesrvAddr(address);
		producer.setInstanceName("MQProducer");
		producer.setSendMsgTimeout(6000);
		try {
			producer.start();
			logger.info("RocketMQ Producer is start..................");
		} catch (Exception e) {  //
			// TODO Auto-generated catch block
			logger.error("Producer connection error :", e);
		}
		this.shutdown();
	}
	private static RocketMQProducer rocketMQProducer = new RocketMQProducer();
	public static RocketMQProducer getInstance(){
		
		return rocketMQProducer;
	}
	/**
	 * 生产者发送数据 到MQ
	 * @param topic  主题
	 * @param tags   关键字
	 * @param keys   消息唯一标识
	 * @param data   数据
	 * @return SendResult
	 */
	public SendResult send(String topic, 
			String tags, String keys, byte[] data){
		Message msg = new Message(topic, tags, keys, data);
		try {
			SendResult result = producer.send(msg);
			logger.debug("Send topic:"+topic+" success, keys=" + keys + ". result:" + result.getSendStatus().toString());
			return result;
		} catch (Exception e) {
			try {
				logger.error("Producer send data:"+new String(data, "utf-8"), e);
			} catch (UnsupportedEncodingException e1) {
			}
		}
		return null;
	}
	
	/**
	 * 异步方式消息，消息没有保障
	 * @param msg
	 */
	public void sendOneway(String topic, 
			String tags, String keys, byte[] data){
		Message msg = new Message(topic, tags, keys, data);
		try {
			producer.sendOneway(msg);
			logger.info("Send topic:"+topic+" success, keys:" + keys+ ", data:"+new String(data));
		} catch (Exception e) {
			try {
				logger.error("Producer send data:"+new String(msg.getBody(), "utf-8"), e);
			} catch (UnsupportedEncodingException e1) {
			}
		}
		data = null;
		msg = null;
	} 
	
	
    /**  
     * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己  
     * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法  
     */  
	private void shutdown(){
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		     public void run() {
		        producer.shutdown();
		     }
		  }));
	}

}

