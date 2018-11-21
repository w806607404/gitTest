package com.scinan.push;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.scinan.utils.RedisUtil;

/**
 * 
 * @project SNParseTopicServer
 * @class com.scinan.iot.push.UserIpManage
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年1月30日
 * @description 
 */
public class UserIpManage {
	//缓存用户在哪个IP上连接着
	static String REDIS_KEY_USER_ADDRESS = "Push_Address_";
	//如果push服务器迁移了，那么很多是废弃的地址。
	static String REDIS_KEY_USER_ADDRESS_Dereference = "Push_Address_Dereference";
	static String ADDRESS_Dereference="";
	
	//<IP, userIds>
	public static Map<String, String> getIpUsers(List<Long> users){
		//<IP,list<userId>>
		Map<String, Set<Long>> map = new HashMap<String, Set<Long>>();
		Set<Long> list = null;
		for(Long userid: users){
			String key = REDIS_KEY_USER_ADDRESS + userid;
			String value =  RedisUtil.get(key);
			if(value != null && !"".equals(value)){// 说明用户正连接着push服务。
				value = value.substring(0, value.length()-1);//去掉最后的逗号
				String[] ips = value.split(",");
				for(String ip : ips){
					if(map.containsKey(ip)){
						map.get(ip).add(userid);
					}else{
						list = new HashSet<Long>();
						list.add(userid);
						map.put(ip, list);
					}
				}
			}
		}
		
		Map<String, String> ipUsers = new HashMap<String, String>();
		for(Entry<String, Set<Long>> entry : map.entrySet()){
			//如果是 废弃的IP
			if(ADDRESS_Dereference.contains(entry.getKey())){
				cleanUserIp(entry.getValue(), entry.getKey());
				continue;
			}
			
			StringBuilder sb = new StringBuilder();
			for(Long userid : entry.getValue()){
				sb.append(userid).append(",");
			}
			String ids = sb.toString();
			//用户id，放到 data里面
			ids = ids.substring(0, ids.length()-1);
			ipUsers.put(entry.getKey(), ids);
		}
		return ipUsers;
	}
	
	/**
	 * 清理用户下，无效的IP
	 * @param userIds
	 * @param ip
	 */
	public static void cleanUserIp(Set<Long> userIds, String ip){
		
		for(Long userId: userIds){
			removeIp(userId, ip+",");
		}
		
	}
	private static void removeIp(Long userId, String ipSplit){
	    String key = REDIS_KEY_USER_ADDRESS + userId;
	    String value = RedisUtil.get(key);
	    if(value !=null && value.contains(ipSplit)){
	    	value =  value.replace(ipSplit, "");
	    	RedisUtil.set(key, value);
	    }
	}
	
	public static void init(){
		
		//定时更新废弃的地址。
		new Thread(new Runnable() {
			@Override
			public void run() {
				String value =RedisUtil.get(REDIS_KEY_USER_ADDRESS_Dereference);
				ADDRESS_Dereference =  value==null? "":value;
				try {
					Thread.sleep(1000 * 60 * 5);
				} catch (InterruptedException e) {
				}
			}
		}).start();
	}
}
