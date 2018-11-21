package com.scinan.push.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.scinan.push.PublishTopicMessage;
import com.scinan.push.PushService;
import com.scinan.push.UserIpManage;
import com.scinan.push.thrift.ThriftFactory;
import com.scinan.push.thrift.message.ThriftPublishMessage;

/**
 * 
 * @project SNParseTopicServer
 * @class com.scinan.iot.push.impl.IOSPusServiceImpl
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年1月29日
 * @description 
 */
@Component
public class AndroidPushService implements PushService{
	
	@Override
	public void push(List<Long> users, PublishTopicMessage publishMessage) {

		//<IP, userIds>
		Map<String, String> map = UserIpManage.getIpUsers(users);
		if(map.size() == 0){
			return;
		}
		for(Entry<String, String> entry : map.entrySet()){
			
			ThriftPublishMessage message =
                    new ThriftPublishMessage(publishMessage.getId(), publishMessage.getDeviceId(), 
                    		publishMessage.getTopic(), entry.getValue(), 
                    		publishMessage.getCreateDate());
		
			//按IP 推送
			ThriftFactory.send(entry.getKey()+":9091", message);
		}
		
		//推送老服务
		this.pushOldService(publishMessage);
	}
	/**
	 * 有个老服务，需要推送
	 */
	private void pushOldService(PublishTopicMessage publishMessage){
		//只有1008 才需推送
		if(!publishMessage.getDeviceId().startsWith("1008")){
			return ;
		}
		ThriftPublishMessage message =
                new ThriftPublishMessage(publishMessage.getId(), publishMessage.getDeviceId(), 
                		publishMessage.getTopic(), publishMessage.getData(), 
                		publishMessage.getCreateDate());
		ThriftFactory.sendOld(message);
	}

}
