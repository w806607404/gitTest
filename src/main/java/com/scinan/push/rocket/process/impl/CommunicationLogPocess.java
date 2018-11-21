package com.scinan.push.rocket.process.impl;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.scinan.push.rocket.process.RocketMQProcess;

/**
 * 处理topic："logs"
 * 连接服务的日志信息
 * @project SNParseTopicServer
 * @class com.scinan.rocket.process.impl.CommunicationLogPocess
 * @copyright www.scinan.com
 * @author cavin
 * @date 2015年11月9日
 * @description
 */
public class CommunicationLogPocess implements RocketMQProcess {

	@Override
	public void doProcess(MessageExt msg, ConsumeConcurrentlyContext context)
			throws Exception {
		// TODO Auto-generated method stub

		String data = new String(msg.getBody(), "utf-8");
		//CommunicationLog log = JSON.parseObject(data, CommunicationLog.class);
		//CommunicationLogService.communicationLog_queue.add(log);
	}

}
