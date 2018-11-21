package com.scinan.push;

import java.util.List;

/**
 * 推送消息
 * @project SNParseTopicServer
 * @class com.scinan.iot.push.PushService
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年1月28日
 * @description 
 */
public interface PushService {

	public void push(List<Long> users, PublishTopicMessage publishMessage);
}
