package com.scinan.push.rocket.process;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * 
 * @project SNParseTopicServer
 * @class com.scinan.rocket.RocketMQProccess
 * @copyright www.scinan.com
 * @author cavin
 * @date 2015年11月9日
 * @description 
 */
public interface RocketMQProcess {

	void doProcess(MessageExt msg, ConsumeConcurrentlyContext context) throws Exception;
}
