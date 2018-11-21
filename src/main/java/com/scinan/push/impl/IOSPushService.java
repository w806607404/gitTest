package com.scinan.push.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.scinan.push.PublishTopicMessage;
import com.scinan.push.PushService;
import com.scinan.push.rocket.RocketMQFactory;
import com.scinan.utils.RedisUtil;

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
public class IOSPushService implements PushService{
	static String key = "Push_ios_company_";
	public static void init(){
		//这些数据才需要 发送IOS
		RedisUtil.set(key+"1008", "S08");
		RedisUtil.set(key+"104B", "S00");
	}
	
	@Override
	public void push(List<Long> users, PublishTopicMessage publishMessage) {
		String company = publishMessage.getDeviceId().substring(0, 4);
		String[] dataArray = StringUtils.split(publishMessage.getTopic(), "/");
		
		//不是所有牛奶都是特仑苏
		String value = RedisUtil.get(key+company);
		if(null!=value && value.contains(dataArray[1])){
			StringBuilder sb = new StringBuilder();
			for (Long id : users) {
				sb.append(id).append(",");
			}
			String ids = sb.toString();
			ids = ids.substring(0, ids.length()-1);
			publishMessage.setData(ids);
			RocketMQFactory.sendIOS(publishMessage);
		}
		
	}

}
