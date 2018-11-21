package com.scinan.iot.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.scinan.bean.PageBean;
import com.scinan.iot.s6000.dao.domain.DeviceInfoBean;
import com.scinan.mybatis.support.service.GenericService;


/**
 * 设备信息相关业务操作接口类
 * 
 * @project datacenter
 * @class com.scinan.iot.service.DeviceInfoService
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年6月28日
 * @description
 */
public interface DeviceInfoService extends GenericService<DeviceInfoBean, Long>{


	PageBean<DeviceInfoBean> fetchParamsByPage(HttpServletRequest request, Map<String, String> params);

	
	/**
	 * 首页相关数据
	 * @param request
	 * @param params
	 * @param model
	 */
	public void setIndexValue(HttpServletRequest request,Model model);
	
	
	
	
}
