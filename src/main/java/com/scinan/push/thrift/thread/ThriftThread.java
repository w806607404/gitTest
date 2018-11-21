package com.scinan.push.thrift.thread;

import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scinan.push.thrift.PublishServerSendService;
import com.scinan.push.thrift.message.ThriftPublishMessage;

/**
 * 每个IP，开辟一个线程。 thrift客户端为 非线程安全，需要轮询消费队列
 * @project SNParseTopicServer
 * @class com.scinan.thrift.thread.ThriftThread
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年2月2日
 * @description 
 */
public class ThriftThread extends Thread{
	private static final Logger logger = LoggerFactory
			.getLogger(ThriftThread.class);
	Queue<ThriftPublishMessage> queue; 
	String address;
	public ThriftThread(Queue<ThriftPublishMessage> queue, String address){
		this.queue = queue;
		this.address = address;
		this.setName("Thrift"+address);
	}
	
	public void run(){
		logger.info(this.getName() + " queue consumer start .......");
		//每个IP， 有一个独立的thrift客户端实例
		PublishServerSendService sendService = PublishServerSendService.getPushService(address);
		while(true){
			ThriftPublishMessage message = queue.poll();
			if(message != null){
				sendService.publish(message);
			}else{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
