package com.scinan.zookeeper.watcher;

import java.io.UnsupportedEncodingException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scinan.base.zookeeper.ZookeeperClient;
import com.scinan.base.zookeeper.watcher.ScinanWatcher;
import com.scinan.constants.ConfigConstant;

public class TestDevcieMacWatcher implements ScinanWatcher, Watcher{
	private static final Logger logger = LoggerFactory
			.getLogger(TestDevcieMacWatcher.class);
	String node;
	public TestDevcieMacWatcher(String node){
		this.node = node;
	}
	@Override
	public void process(WatchedEvent event) {
		//节点里面的内容变化了; 并且回复连接/建立连接
		if(event.getType() == Watcher.Event.EventType.NodeDataChanged
				&& event.getState() == Watcher.Event.KeeperState.SyncConnected){
			logger.info("Zookeeper Status: "+event.getState()+", Node: "+event.getPath()+", EventType: " + event.getType());
			
			load();
		}		
	}
	// 启动初始化时会调用
	@Override
	public void load() {
		if(null != ZookeeperClient.exists(node)){
			byte[] data = ZookeeperClient.getData(node, this);
			String s = null;
			try {
				s = new String(data, "UTF-8");
			} catch (UnsupportedEncodingException e) { }
			logger.info(node + ":" + s);
			ConfigConstant.MAC4TEST  = s;
		}else{
			logger.info(node + " not exist...");
		}
	}
	
}
