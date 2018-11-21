package com.scinan.utils;

import java.util.Map;

/**
 * 
 * @project SNAPIServer_V2.0
 * @class com.scinan.iot.web.bean.Page
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年4月7日
 * @description 
 */
public class Page {
	/**
	 * 第N页
	 */
	Integer page = 1;
	public Integer getPage() {
		return page;
	}
	public Integer getPage_size() {
		return page_size;
	}
	/**
	 * 每页多少条
	 */
	Integer page_size = 10;
	
	public Page(Map<String, String> paraMap){
		String temp = paraMap.get("limit");
		if(!isEmpty(temp)){
			try{
				page_size = new Integer(temp);
			}catch(Exception e){	}
		} 
		
		String temp2 = paraMap.get("offset");
		if(!isEmpty(temp2)){
			try{
			page =  (Integer.parseInt(temp2)/page_size) + 1;
			}catch(Exception e){	}
		} 
	}
	private boolean isEmpty(String value){
		return null==value || "".equals(value);
	}
}
