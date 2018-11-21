package com.scinan.push.thrift.message;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublishMessageServiceHandler implements
		PublishMessageService.Iface {
	private static AtomicLong sendCount = new AtomicLong(0L);
	private final static Logger logger = LoggerFactory.getLogger(PublishMessageServiceHandler.class);
	@Override
	public void publish(ThriftPublishMessage publishMessage) throws TException {
//		logger.info("publishMessage:" + publishMessage.getId() + " "
//				+ publishMessage.getDeviceId());
		long tempCount = sendCount.incrementAndGet();
		if (tempCount % 10000 == 0) {
			sendCount.set(0L);
			logger.info(new Date() + " publish count : " + tempCount);
		}
	}
}
