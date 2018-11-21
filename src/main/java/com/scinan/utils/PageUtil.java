package com.scinan.utils;

import java.util.HashMap;
import java.util.Map;

import com.scinan.mybatis.support.sql.Sort;

/**
 * 
 * @project SNPushIOS
 * @class com.scinan.util.PageUtil
 * @copyright www.scinan.com
 * @author cavin
 * @date 2016年3月3日
 * @description 
 */
public class PageUtil {

	public static Map<String, Object> getPageMap(Sort sort, int page, int pageSize){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", page > 0 ? ((page-1) * pageSize) : 0);
        params.put("limit", pageSize > 0 ? pageSize : 0);
        params.put("sort", sort);
        params.put("sortType", 2);
		return params;
    }
	public static Map<String, Object> getPageMap(Map<String, String> parameterMap){
		Page pageObj = new Page(parameterMap);
		int page = pageObj.getPage();
		int pageSize = pageObj.getPage_size();
		
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", page > 0 ? ((page-1) * pageSize) : 0);
        params.put("limit", pageSize > 0 ? pageSize : 0);
		return params;
    }
}
