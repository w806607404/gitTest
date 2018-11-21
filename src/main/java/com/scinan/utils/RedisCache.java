package com.scinan.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.scinan.base.constant.CacheConstantBase;
import com.scinan.iot.service.SystemResourcesService;
import com.scinan.spring.SpringUtil;

/**
 * 缓存数据处理类
 * 
 * @project datacenter
 * @class com.scinan.utils.RedisCache
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月23日
 * @description
 */
@Component
public class RedisCache {
	
	final static Logger logger = Logger.getLogger(RedisCache.class);

	//private static RedisSimpleCacheServiceImpl redisSimpleService;
	
	
	public RedisCache(){
		new Thread(new Runnable() {
			
			public void run() {
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//redisSimpleService = RedisUtil.getRedis();
				init();
				
			}
		}).start();
	}

	public void init() {
		try {
//			logger.info("加载系统配置数据...");
//			SysPropertyService sysPropertyService = (SysPropertyService)SpringUtil.getBean("sysPropertyService");
//			List<SysPropertyBean> props = sysPropertyService.fetchByPage(null, null, 0, 0);
//			for (SysPropertyBean prop : props) {
//				redisSimpleService.set(CacheConstantBase.TIME_FOREVER,SC.CACHE_PROPERTY_KEY_PREFIX + prop.getProp_key(), prop.getProp_value());
//			}
//			
//			logger.info("加载厂商订单项流水号数据...");
//			FactoryService factoryService = (FactoryService)SpringUtil.getBean("factoryService");
//			List<FactoryBean> factories = factoryService.fetchByPage(null, null, 0, 0);
//			for(FactoryBean factory : factories) {
//				redisSimpleService.set(CacheConstantBase.TIME_FOREVER,SC.FACTORY_ORDERITEM_SN_PREFIX + factory.getId(), factory.getOrder_item_sn());
//			}
			
			SystemResourcesService systemResourcesService = (SystemResourcesService)SpringUtil.getBean("systemResourcesService");
			
			logger.info("数据库缓存数据加载完成...");
		} catch (Exception e) {
			logger.error("初始化数据缓存失败...", e);
			System.exit(-1);
		}
	}

	/**
	 * 获取订单项MAC地址流水号
	 * @return
	 */
	public static String getOrderItemMac() {
		String mac = (String)RedisUtil.get(SC.CACHE_PROPERTY_KEY_PREFIX + SC.SYS_PROP_MAC_SN);
		return mac;
	}

	/**
	 * 设置订单项MAC地址流水号
	 * @param mac
	 */
	public static void setOrderItemMac(String mac) {
		RedisUtil.set(CacheConstantBase.TIME_FOREVER,SC.CACHE_PROPERTY_KEY_PREFIX + SC.SYS_PROP_MAC_SN, mac);
	}

	/**
	 * 获取物料流水号
	 * @return
	 */
	public static String getMaterialSerialNo() {
		String mac = (String)RedisUtil.get(SC.CACHE_PROPERTY_KEY_PREFIX + SC.SYS_PROP_MATERIAL_SN);
		return mac;
	}

	/**
	 * 设置物料流水号
	 * @return
	 */
	public static void setMaterialSerialNo(String sn) {
		RedisUtil.set(CacheConstantBase.TIME_FOREVER,SC.CACHE_PROPERTY_KEY_PREFIX + SC.SYS_PROP_MATERIAL_SN, sn);
	}

	/**
	 * 获取厂商订单项流水号
	 * @param factoryCode
	 * @return
	 */
	public static String getOrderItemSn(String factoryCode) {
		String mac = (String)RedisUtil.get(SC.FACTORY_ORDERITEM_SN_PREFIX + factoryCode);
		return mac;
	}

	/**
	 * 设置厂商订单项流水号
	 * @param factoryCode
	 * @param sn
	 * @return
	 */
	public static void setOrderItemSn(String factoryCode, String sn) {
		RedisUtil.set(CacheConstantBase.TIME_FOREVER,SC.FACTORY_ORDERITEM_SN_PREFIX + factoryCode, sn);
	}
}
