package com.scinan.utils;

import java.util.Set;

import com.scinan.base.cache.impl.RedisSimpleCacheServiceImpl;
import com.scinan.base.constant.CacheConstantBase;
import com.scinan.spring.SpringUtil;

/**
 * 
 * @project SNAPIServer_V2.0
 * @class com.scinan.base.util.RedisUtils
 * @copyright www.scinan.com
 * @author Eric
 * @date 2015年10月27日
 * @description 
 */
public class RedisUtil {
	public static RedisSimpleCacheServiceImpl getRedis() {
		return (RedisSimpleCacheServiceImpl)SpringUtil.getBean("redisSimpleService");
	}
	
	
	/**
	 * 缓存数据，不过期
	 * @param key
	 * @param value
	 */
	public static void set(String key , Object value){
		getRedis().set(CacheConstantBase.TIME_FOREVER, key, value);
	}
	
	public static void set(String time_key, String key , Object value){
		getRedis().set(time_key, key, value);
	}
	
	public static String get(String key){
		return (String)getRedis().get(key);
	}
	
	/**
	 * 带过期时间
	 * @param key
	 * @param expireTime  单位：秒
	 */
	public static void expire(String key, int expireTime){
		getRedis().set(expireTime, key, null);
	}
	
	/**
	 * 带过期时间
	 * @param key
	 * @param expireTime  单位：秒
	 */
	public static void expire(String key,String value, int expireTime){
		getRedis().set(expireTime, key, value);
	}
	public static void remove(String key){
		getRedis().remove(key);
	}
	
	/**
	 * key的集合
	 * @param pattern
	 * @return
	 */
	public static Set<String> keys(String pattern) {
		return getRedis().keys(pattern);
	}


	public static String getString(String key) {
		return (String)getRedis().get(key);
	}
	
	/**
	 * 自动增长序列
	 * @param key
	 * @param by  步长
	 * @return
	 */
	public static long incr(String key, long by) {
		return getRedis().incr(key, by);
	}

	public static boolean exits(String key) {
		return getRedis().exists(key);
	}
	
}
