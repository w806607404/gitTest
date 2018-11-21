package com.scinan.iot.service;

import java.util.Map;

import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.LoginLogBean;
import com.scinan.mybatis.support.service.GenericService;
/**
 * 登录log日志相关接口
 * 
 * @project datacenter
 * @class com.scinan.iot.service.LoginLogService
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月6日
 * @description
 */
public interface LoginLogService extends GenericService<LoginLogBean, Long>{
	
	/**
	 * 获取分页数据
	 * @param params
	 * @return
	 */
	PageBean<LoginLogBean> fetchParamsByPage(Map<String, String> params)throws Exception ;
	
    
}
