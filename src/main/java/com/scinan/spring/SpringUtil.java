package com.scinan.spring;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @project datacenter
 * @class com.scinan.spring.SpringUtil
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月10日
 * @description
 */
public class SpringUtil {
	private static ApplicationContext  wac ;
	public static  void init(ServletContext context){
		wac = WebApplicationContextUtils.getRequiredWebApplicationContext(context);  
	}
	
	public static Object getBean(String beanName){
		return wac.getBean(beanName);
	}
}
