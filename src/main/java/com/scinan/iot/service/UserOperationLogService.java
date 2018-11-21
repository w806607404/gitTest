package com.scinan.iot.service;

import java.util.Map;

import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.UserOperationLogBean;
import com.scinan.mybatis.support.service.GenericService;
/**
 * 用户操作系统日志相关接口
 * 
 * @project datacenter
 * @class com.scinan.iot.service.AccountInfoService
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月6日
 * @description
 */
public interface UserOperationLogService extends GenericService<UserOperationLogBean, Long>{
	
	/**
	 * 获取分页数据
	 * @param params
	 * @return
	 */
	PageBean<UserOperationLogBean> fetchParamsByPage(Map<String, String> params)throws Exception ;
}
