package com.scinan.iot.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.scinan.iot.service.MsgPushService;
import com.scinan.push.bean.PushSourceBean;
import com.scinan.push.rocket.RocketMQFactory;
import com.scinan.utils.RedisUtil;
import com.scinan.utils.StringUtil;
/**
 * 
 * 
 * @project datacenter
 * @class com.scinan.iot.service.impl.BillDetailsServiceImpl
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年08月10日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("msgPushService")
public class MsgPushServiceImpl implements MsgPushService {
	static Logger logger = Logger.getLogger(MsgPushServiceImpl.class);
	private static final String  KEY = "DDEDDO_MANAGE_APP_KEY";
	
	public static void main(String[] args) {
		new MsgPushServiceImpl().msgPush(2, "2");
	}
	
	
	/**
	 * 推送基本方法
	 * @param type 0：通知公告 ，1：通知发货 ， 2：分红账单，3：购货列表，4：分红比例变更列
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	public void msgPush(int type , String userId) {
		String app_key = RedisUtil.get(KEY);
//		String app_key = "500119";
		PushSourceBean bean = new PushSourceBean();
		long ids = System.currentTimeMillis();
		bean.setApp_key(app_key);
		if(StringUtil.isNotEmpty(userId)){
			bean.setTargetId(userId);
		}else{
			bean.setTargetId(app_key);
		}
		if(type == 0){
			bean.setTargetType("1");         //消息目标类型，1 ：App， 2 ：User，3 ：Device，4：厂商
			bean.setIos_data("发布了新公告快去看看吧~");
			bean.setMsgType("0");
		}else if(type == 1){
			bean.setTargetType("2");         //消息目标类型，1 ：App， 2 ：User，3 ：Device，4：厂商
			bean.setIos_data("您接收到一条发货通知，请赶紧确认哦~");
			bean.setMsgType("1");
		}else if(type == 4){
			bean.setTargetType("1");         //消息目标类型，1 ：App， 2 ：User，3 ：Device，4：厂商
			bean.setIos_data("产品价格和分红比例有新的变更，快去看看吧~");
			bean.setMsgType("4");
		}
		bean.setTopic("邻居零售");
		bean.setSourceType("ddeddo");
		bean.setCreateTime(System.currentTimeMillis());
		bean.setForward("1");            //1：发送给APP；2：发送给device
		bean.setSourceId(ids+"");
		bean.setNotify_bar_msg(true);
		bean.setNotify_type(1);
		try{
			 sendDataToApp(bean);
			}catch(Exception ex){
				ex.printStackTrace();
			}
	}
			
	
	/**
	 * 功能说明:web平台广告推送至平台
	 * @param bean
	 */
    private static void sendDataToApp(PushSourceBean bean){
    	//推送广告信息至APP，通过MQ中转
		RocketMQFactory.sendDataToApp(bean);
    }

	
	
}
