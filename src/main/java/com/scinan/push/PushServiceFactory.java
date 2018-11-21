package com.scinan.push;

import com.scinan.spring.SpringUtil;
import com.scinan.push.impl.IOSPushService;
import com.scinan.push.thrift.ThriftFactory;

/**
 * 消息推送
 * @project SNParseTopicServer
 * @class com.scinan.thrift.ThriftSendFactory
 * @copyright www.scinan.com
 * @author cavin
 * @date 2015年11月16日
 * @description 
 */
public class PushServiceFactory {
	
		static PushService pushService ;
		public static void init(){
			IOSPushService.init();
			UserIpManage.init();
			ThriftFactory.init();
			pushService = (PushService)SpringUtil.getBean("pushService");
		}
		
		private static void push(PublishTopicMessage publishMessage){
			pushService.push(null, publishMessage);
		}

	/**
	 * 接收到MQ消息，立马推送
	 * @param pMsg
	 * @param creatTime
	 */
	public static void send(PublishTopicMessage publishMessage){
		
		final PublishTopicMessage temp = publishMessage;
		new Thread(new Runnable() {
			@Override
			public void run() {
				push(temp);
			}
		}).start();
		
	}
}
