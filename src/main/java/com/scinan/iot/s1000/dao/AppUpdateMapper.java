package com.scinan.iot.s1000.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.s1000.dao.domain.AppUpdateBean;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 
 * <一句话功能简述>App更新
 * <功能详细描述>
 * 
 * @author  kim
 * @version  [版本号, 2015-7-9]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface AppUpdateMapper extends GenericMapper<AppUpdateBean, Long> {

	List<AppUpdateBean> fetchAppUpdateByPage(Map<String, Object> map);

	Integer fetchAppUpdateCount(Map<String, Object> map);
	
}
