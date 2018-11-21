package com.scinan.push.rocket.process.impl;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.scinan.push.rocket.process.RocketMQProcess;

/**
 * 处理topic： "device_status";
 * 
 * @project SNParseTopicServer
 * @class com.scinan.rocket.process.impl.DeviceStatusProcess
 * @copyright www.scinan.com
 * @author cavin
 * @date 2015年11月9日
 * @description
 */
public class DeviceStatusProcess implements RocketMQProcess {

	@Override
	public void doProcess(MessageExt msg, ConsumeConcurrentlyContext context)
			throws Exception {
//		DeviceStatusUpdateBean tempDeviceStatusUpdateBean = JSON.parseObject(
//				new String(msg.getBody(), "UTF-8"),
//				DeviceStatusUpdateBean.class);
//		
//		tempDeviceStatusUpdateBean.setId(msg.getKeys());
//		//根据公司id，获取公司业务处理类
//		DeviceStatusService service = DeviceStatusServiceFactory
//				.getService(tempDeviceStatusUpdateBean.getDevice_id());
//		
//		//再根据上线、或下线状态分别执行
//		if ("0".equals(tempDeviceStatusUpdateBean.getOnline())){
//			service.offline(tempDeviceStatusUpdateBean, msg.getStoreTimestamp());
//		} else if("1".equals(tempDeviceStatusUpdateBean.getOnline())){
//			service.online(tempDeviceStatusUpdateBean, msg.getStoreTimestamp());
//		}
//		//先执行公共业务
//		service.commService(tempDeviceStatusUpdateBean, msg.getStoreTimestamp());
		
		//消费计算器
		//ProcessConstantTest.counterRocket_update.getAndIncrement();
	}

}
