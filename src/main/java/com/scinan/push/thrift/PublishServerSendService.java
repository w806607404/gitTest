package com.scinan.push.thrift;

import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scinan.push.thrift.message.PublishMessageService;
import com.scinan.push.thrift.message.ThriftPublishMessage;
import com.scinan.utils.RedisUtil;
import com.scinan.utils.StringUtil;

///**
// * www.scinan.cn creater:locker creeateDate:2014年10月2日上午10:04:23 版本:V1.0
// */
public class PublishServerSendService {
	private static final Logger logger = LoggerFactory
			.getLogger(PublishServerSendService.class);



	private PublishMessageService.Client publisThriftClient;
	private TTransport transport;
	private TSocket socket;
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 根据IP 地址，获取推送客户端
	 * @param ip
	 * @return
	 */
	public static PublishServerSendService getPushService(String ip){
		String key =  ip+":9091";
		if(serviceMap.containsKey(key)){
			return serviceMap.get(key);
		}else{
			PublishServerSendService service = new PublishServerSendService();
			service.setAddress(key);
			service.initThriftTransport();
			serviceMap.put(key, service);
			return service;
		}
		
	}
	private static Map<String, PublishServerSendService> serviceMap = new HashMap<String, PublishServerSendService>();
	private static String oldPushAddress ;
	public static String getOldPushAddress() {
		return oldPushAddress;
	}

	/**
	 * 初始化thrift客户端。 默认的thrift服务器地址，配置在redis中。
	 */
	public static void init() {
		String str = RedisUtil.get("publish_thrift_server");
		if(StringUtil.isNotEmpty(str)){
			String[] servers = str.split(",");
			PublishServerSendService service = null;
			for (int i = 0; i < servers.length; i++) {
				service = new PublishServerSendService();
				service.setAddress(servers[i]);
				service.initThriftTransport();
				
				//老的push服务 端口是9090
				if("9090".equals(servers[i].split(":")[1])){
					oldPushAddress = (servers[i].split(":")[0]);
				}else{
					serviceMap.put(servers[i], service);
				}
			}
		}
	}

	public int publish(ThriftPublishMessage thriftPublishMessage) {
		int state = 1;
		if (!this.socket.isOpen() || !this.transport.isOpen()) {
			initThriftTransport();
		}

		try {
			this.publisThriftClient.publish(thriftPublishMessage);
			logger.info(this.address + " publish message to push server");
		} catch (Exception e) {
			logger.error("Thrift server "+this.address+" break：", e);
			transport.close();
			socket.close();
			publisThriftClient = null;

			// sleep 100ms
			try {
				// 如果server响应的过来的情况下，是不会存在问题的
				Thread.sleep(100);
			} catch (Exception e1) {
			}
			state = 0;
		}

		return state;
	}

	
	private boolean initThriftTransport() {
		boolean flag = false;
		try {
			String[] ip = this.address.split(":");
			TSocket socket = new TSocket(ip[0], Integer.parseInt(ip[1]));
			socket.setTimeout(1000 * 60 * 2);
			TTransport transport = new TFramedTransport(socket);
			TProtocol protocol = new TCompactProtocol(transport);
			PublishMessageService.Client publisThriftClient = new PublishMessageService.Client(
					protocol);

			this.socket = (socket);
			this.transport = (transport);
			this.publisThriftClient = (publisThriftClient);

			// The transport must be opened before you can begin using
			this.transport.open();
			logger.info("Thrift " + address + " start.......");
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(this.address + " thrift init error:", e);
		}

		return flag;
	}

}
