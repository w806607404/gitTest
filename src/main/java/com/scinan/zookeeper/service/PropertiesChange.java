package com.scinan.zookeeper.service;


/**
 * 事件驱动器， 实现自己感兴趣的事件。
 * @project SNConnectServer_dev
 * @class com.scinan.zookeeper.service.PropertiesChange
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年5月5日
 * @description 
 */
public interface PropertiesChange {

	public void doProcess(String node_path);

}
