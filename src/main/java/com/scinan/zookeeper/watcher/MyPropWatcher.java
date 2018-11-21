package com.scinan.zookeeper.watcher;
import java.util.ArrayList;
import java.util.List;
import com.scinan.base.zookeeper.watcher.PropertiesWatcher;
import com.scinan.zookeeper.service.PropertiesChange;
import com.scinan.zookeeper.service.impl.DeviceIdCheckChange;


/**
 * 
 * @project ScinanBase_new
 * @class com.scinan.base.zookeeper.watcher.ProjectPropWatcher
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年4月29日
 * @description 
 */
public class MyPropWatcher extends PropertiesWatcher{
	private void initPropertiesChange(){
		LIST.add(new DeviceIdCheckChange());
	}
	
	//配置变化的事件。
	private List<PropertiesChange> LIST = new ArrayList<PropertiesChange>();
	/**
	 * @param node_path
	 */
	public MyPropWatcher(String node_path) {
		super(node_path);
		this.initPropertiesChange();
	}

	@Override
	public void nodeDataChanged() {
//		ZooKeeperProperty config = (ZooKeeperProperty)
//				SpringUtil.getBean("zooKeeperProperty");
//		config.loadProperties();
		
		//properties 重新加载后， 有些流程也将变动。
		for(PropertiesChange PropertiesChange :LIST){
			PropertiesChange.doProcess(this.getNodePath());
		}
	}

}
