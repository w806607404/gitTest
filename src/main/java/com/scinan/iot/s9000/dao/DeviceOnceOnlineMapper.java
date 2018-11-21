package com.scinan.iot.s9000.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.s9000.dao.domain.DeviceOnceOnlineBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface DeviceOnceOnlineMapper extends GenericMapper<DeviceOnceOnlineBean, Long> {

	Integer countDevicesConnectionByMap(Map<String, Object> params);
	
	List<DeviceOnceOnlineBean> fetchCompany();

	List<DeviceOnceOnlineBean> fetchDeviceOnlineHistoryByPage(
			Map<String, Object> params);

	Integer fetchDeviceOnlineHistoryCount(Map<String, Object> params);
	
	List<DeviceOnceOnlineBean>  fetchDeviceOnlineHistoryByList(Map<String, Object> params);

    List<DeviceOnceOnlineBean>  fetchByCompanyList(Map<String,Object> params);

    Integer countByMouth(Map<String, Object> params);
}
