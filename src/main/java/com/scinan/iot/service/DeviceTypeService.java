package com.scinan.iot.service;

import java.util.List;
import java.util.Map;

import com.scinan.iot.s6000.dao.domain.DeviceTypeBean;
import com.scinan.mybatis.support.service.GenericService;

public interface DeviceTypeService extends GenericService<DeviceTypeBean, Long>{
	
	
	/**
	 * 获取设备类型
	 * @param params
	 * @return List<DeviceTypeBean>
	 */
	 List<DeviceTypeBean> getDeviceTypeList(Long id); 
	  
	 /**
	   * 通过类型和厂商获取设备类型
	   * @param map
	   * @return DeviceType
	   */
	  DeviceTypeBean fetchByTypeAndCompanyId(Map<String,Object>  map);
}

