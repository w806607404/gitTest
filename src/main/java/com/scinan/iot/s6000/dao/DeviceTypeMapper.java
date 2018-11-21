package com.scinan.iot.s6000.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.scinan.iot.s6000.dao.domain.DeviceTypeBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface DeviceTypeMapper extends GenericMapper<DeviceTypeBean, Long> {

	void deleteIDs(List<String> ids);

	DeviceTypeBean fetchDeviceTypeByCompanyIdAndTypeId(@Param("company_id")String factoryId,@Param("device_type_id")
			Long deviceType);

	DeviceTypeBean fetchDeviceTypeByCompanyIdAndType(String valueOf);

	DeviceTypeBean fetchDeviceTypeByDeviceId(@Param("device_id")String device_id);

	List<DeviceTypeBean> fetchDeviceTypeFactoryNames();
	
	
	List<DeviceTypeBean> getDeviceTypeAndComapnyId(@Param("company_id")String company_id);
	
	List<DeviceTypeBean>  fetchDeviceTypeOfList();
	
	/**
	   * 通过类型和厂商获取设备类型
	   * @param map
	   * @return DeviceType
	   */
	  DeviceTypeBean fetchByTypeAndCompanyId(Map<String,Object>  map);
	
	

}