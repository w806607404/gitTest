package com.scinan.push.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scinan.push.PublishTopicMessage;
import com.scinan.push.PushService;

/**
 * 推送消息
 * @project SNParseTopicServer
 * @class com.scinan.iot.push.PushService
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年1月28日
 * @description 
 */
@Component("pushService")
public class PushServiceImpl implements PushService{
	private static Logger logger = LoggerFactory.getLogger(PushServiceImpl.class); 
	
	//@Autowired
	//DeviceUserService deviceUserService ;
	@Autowired
	IOSPushService iOSPushService;
	@Autowired
	AndroidPushService androidPushService;

	private void push(PublishTopicMessage publishMessage){
		List<Long> users = this.getUsers(publishMessage.getDeviceId());
		
		if(null== users || users.size()==0){
			return ;
		}
		androidPushService.push(users, publishMessage);
		iOSPushService.push(users, publishMessage);
	}
	
	private List<Long> getUsers(String deviceId){
		return null;//deviceUserService.listUserIdByDevice(deviceId);
	}

	@Override
	public void push(List<Long> users, PublishTopicMessage publishMessage) {
		//推送之前，先写推送日志
		//this.addLog(publishMessage);
		
		this.push(publishMessage);
	}
	
//	private void addLog(PublishTopicMessage publishMessage){
//		CommandDownLog log = new CommandDownLog();
//		log.setData(publishMessage.getData());
//		log.setDeal_time(System.currentTimeMillis());
//		log.setDevice_id(publishMessage.getDeviceId());
//		log.setMsg_id(publishMessage.getId());
//		log.setReceive_time(publishMessage.getCreateDate());
//		log.setTopic(publishMessage.getTopic());
//		
//		if(StringUtil.isNotEmpty(log.getDevice_id())){
//			CommandCostLogService.CommandCostLog_queue.add(log);
//		}else{
//			logger.error("device_id is empty, topic: " + log.getTopic());
//		}
//	}
}
