package com.scinan.push.thrift;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.scinan.push.thrift.message.ThriftPublishMessage;
import com.scinan.push.thrift.thread.ThriftThread;
import com.scinan.utils.RedisUtil;
import com.scinan.utils.StringUtil;

/**
 * 
 * @project SNParseTopicServer
 * @class com.scinan.thrift.ThriftFactory
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年2月2日
 * @description 
 */
public class ThriftFactory {

	//<IP, Queue<ThriftPublishMessage>>：每个ip，启动一个线程消费队列
	static Map<String, Queue<ThriftPublishMessage>> map = 
			new ConcurrentHashMap<String, Queue<ThriftPublishMessage>>();
	
	public static void init(){
		String str = RedisUtil.get("publish_thrift_server");
		if(StringUtil.isNotEmpty(str)){
			String[] servers = str.split(",");
			String ip = null;
			for(String address: servers){
				ip = address.split(":")[0];
				addNewIp(ip);
			}
		}
	}
	
	private static void addNewIp(String ip){
		Queue<ThriftPublishMessage> queue = new ConcurrentLinkedQueue<ThriftPublishMessage>();
		map.put(ip, queue);
		new ThriftThread(queue, ip).start();
	}
	
	/**
	 * 根据不同的IP， 放入不同的队列
	 * @param ip
	 * @param message
	 */
	public static void send(String ip, ThriftPublishMessage message){
		Queue<ThriftPublishMessage> queue =  map.get(ip);
		if(null == queue){// 是新IP 来了
			addNewIp(ip);
			queue =  map.get(ip);
		}
		queue.add(message);
	}
	
	/**
	 * 老的推送
	 * @param message
	 */
	public static void sendOld(ThriftPublishMessage message){
		Queue<ThriftPublishMessage> queue =  map.get(
				PublishServerSendService.getOldPushAddress());
		if(null != queue){
			queue.add(message);
		}
	}
}
