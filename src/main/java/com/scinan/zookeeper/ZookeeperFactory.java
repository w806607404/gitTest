package com.scinan.zookeeper;

import java.util.ArrayList;
import java.util.List;

import com.scinan.base.zookeeper.ZookeeperClient;
import com.scinan.base.zookeeper.watcher.ScinanWatcher;
import com.scinan.base.zookeeper.watcher.SubSystemWatcher;
import com.scinan.constants.ConfigConstant;
import com.scinan.zookeeper.watcher.MyPropWatcher;

/**
 * 
 * @project ScinanBase_new
 * @class com.scinan.base.zookeeper.ZookeeperFactory
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年4月29日
 * @description 
 */
public class ZookeeperFactory {
	public static void init(){
		
		List<ScinanWatcher> list = new ArrayList<ScinanWatcher>();
		list.add(new MyPropWatcher(ConfigConstant.ZK_PATH_COMMON_PROP));
		list.add(new MyPropWatcher(ConfigConstant.ZK_PATH_DATECENTER_PROP));
		//list.add(new TestDevcieMacWatcher(ConfigConstant.ZK_PATH_DATA_TESTMAC));//测试的设备mac
		
		SubSystemWatcher subSystemWatcher =  new SubSystemWatcher(list);
		ZookeeperClient.init(null, subSystemWatcher);
		ZookeeperClient.syn();
	}
}
