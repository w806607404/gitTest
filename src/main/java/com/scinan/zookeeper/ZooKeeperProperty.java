package com.scinan.zookeeper;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.scinan.base.config.ConfigureFile;
import com.scinan.base.spring.SpringFactory;
import com.scinan.base.util.PropertiesUtil;

/**
 * 
 * @project SNParseTopicServer
 * @class com.scinan.zookeeper.ZooKeeperPropertyPlaceholderConfigurer
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年4月19日
 * @description 
 */
public class ZooKeeperProperty extends PropertyPlaceholderConfigurer {
	private static final Logger logger = LoggerFactory
			.getLogger(ZookeeperFactory.class);
		ConfigurableListableBeanFactory renameFactory;
		
	 	@Override
	    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
	            throws BeansException {
	 		ZookeeperFactory.init();
	 		logger.info("img_url_prefix="+ConfigureFile.getInstance().getProperty("img_url_prefix"));
	        this.renameFactory = beanFactoryToProcess;
	        super.processProperties(this.renameFactory, PropertiesUtil.getProperties());
	    }
	 	
	 	public void loadProperties(){
			super.processProperties(this.renameFactory, PropertiesUtil.getProperties());
	 	}
	 	
}
