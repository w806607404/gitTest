package com.scinan.zookeeper.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scinan.base.util.PropertiesUtil;
import com.scinan.constants.ConfigConstant;
import com.scinan.zookeeper.service.PropertiesChange;


/**
 * 事件驱动
 * @project SNConnectServer_dev
 * @class com.scinan.zookeeper.service.impl.DeviceIdCheckChange
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年5月5日
 * @description 
 */
public class DeviceIdCheckChange implements PropertiesChange{
	private static final Logger logger = LoggerFactory.getLogger(DeviceIdCheckChange.class);
	@Override
	public void doProcess(String node_path) {
		if(com.scinan.constants.ConfigConstant.ZK_PATH_DATECENTER_PROP.equals(node_path)){
			ConfigConstant.IS_TEST_SERVICE = PropertiesUtil.get("is_test");
			logger.debug(">>>>> is_test: " + ConfigConstant.IS_TEST_SERVICE);
//			boolean check_device_by_password = "password".equals(PropsUtil.get("check_device_by"));
//			logger.info("check_device_by_password: " + check_device_by_password);

			String FAE_PHONE = PropertiesUtil.get("FAE_PHONE");
			logger.debug(">>>>> FAE_PHONE: " + FAE_PHONE);
		}
	}

}
