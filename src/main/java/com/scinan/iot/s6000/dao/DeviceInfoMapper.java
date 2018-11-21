package com.scinan.iot.s6000.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.scinan.bean.AddressNumber;
import com.scinan.iot.s6000.dao.domain.DeviceInfo;
import com.scinan.iot.s6000.dao.domain.DeviceInfoBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface DeviceInfoMapper extends GenericMapper<DeviceInfoBean, Long>{

	Integer countDeviceInfo(Map<String, Object> params);

	Integer countDevicesByCompanyIdAndDeviceType(@Param("company_id") String companyId,
			@Param("device_type") Integer deviceType);

	Integer countDevicesByMap(Map<String, Object> params);

	List<DeviceInfoBean> fetchActivationDeviceByPage(Map<String, Object> map);

	Integer countActivationDevice(Map<String, Object> map);

	List<DeviceInfoBean> fetchOnlineDeviceByPage(Map<String, Object> map);

	Integer countOnlineDevice(Map<String, Object> map);
	
	DeviceInfoBean fetchById(String device_id);

	List<DeviceInfoBean> fetchUserDevicesByPage(Map<String, Object> map);

	Integer countUserDevices(Map<String, Object> map);

	List<AddressNumber> fetchIndustryDistribution(Map<String, Object> params);

	List<DeviceInfoBean> fetchDomesticDevicesByPage(Map<String, Object> params);

	Integer fetchDomesticDevicesCount(Map<String, Object> params);

	List<DeviceInfoBean> fetchForeignDevicesByPage(Map<String, Object> params);

	Integer fetchForeignDevicesCount(Map<String, Object> params);

	List<DeviceInfoBean> fetchAllTimeDevicesByPage(Map<String, Object> params);

	Integer fetchAllTimeDevicesCount(Map<String, Object> params);

	List<DeviceInfoBean> fetchDeviceBindUserByPage(Map<String, Object> params);

	Integer fetchDeviceBindUserCount(Map<String, Object> params);

	List<DeviceInfoBean> fetchDevicesFactoryNames(Map<String, Object> params);

	List<DeviceInfoBean> fetchOnlineDeviceCountrys(Map<String, Object> params);

	List<DeviceInfoBean> fetchDomesticDeviceFactoryNames(Map<String, Object> params);

	List<DeviceInfoBean> fetchForeignDeviceFactoryNames(Map<String, Object> params);
	
	List<DeviceInfoBean> qryDeviceInfoList(Map<String, Object> params);
	
	Integer updateDeviceInfo(DeviceInfoBean bean);
	
	Integer insertDeviceInfoBatch(List<DeviceInfo> list);
	
	List<DeviceInfoBean> fetchByPage(Map<String, Object> params);

	Integer countOffline(Map<String, Object> map);

	Integer countOnline(Map<String, Object> map);

	Integer countJoin(Map<String, Object> map);

	Integer countUnJoin(Map<String, Object> map);

	String fetchDeviceName(String device_id);

	void initDeviceStatus(List<String> deviceList);
	
	
	
	/**
	 * 功能说明：根据厂商，展示不同类型的设备列表
	 * @param map
	 * @return  DeviceInfoBean
	 */
	List<DeviceInfoBean> fetchDeviceBindUserByInfoPage(Map<String, Object> map);
	
	/**
	 * 功能说明：根据厂商，展示不同类型的设备总数
	 * @param map
	 * @return  DeviceInfoBean
	 */
    Integer countDeviceBindUserByInfoPage(Map<String, Object> map);
    
    
    /**
     * 功能说明：获取设备总数
     * @param map
     * @return
     */
    Integer countAllDevicesNum(Map<String, Object> map);
    
    /**
     * 功能说明：设备在线总数
     * @param map
     * @return
     */
    Integer countOnlineNum(Map<String, Object> map);
    
    /**
     * 功能说明：设备离线总数
     * @param map
     * @return
     */
    Integer countOfflineNum(Map<String, Object> map);
    
    
    /**
     * 功能说明：已激活设备
     * @param map
     * @return
     */
    Integer countJoinNum(Map<String, Object> map);
    
    
    /**
     * 功能说明:未激活设备列表
     * @param map
     * @return
     */
    List<DeviceInfoBean> fetchNotActiveDeviceByPage (Map<String, Object> map);
    
    
    /**
     * 功能说明:未激活设备总数
     * @param map
     * @return
     */
    Integer countNotActiveDevice(Map<String, Object> map);
    
    
    /**
     * 功能说明:设备总数、激活设备总数
     * @param map
     * @return
     */
    Integer countDevice(Map<String, Object> params);
   
    /**
     * 功能说明:日设备总数、日激活设备总数
     * @param map
     * @return
     */
    Integer countDeviceDay(Map<String, Object> params);
    
	List<DeviceInfo>  fetchRoleDeviceByPage(Map<String, Object> params);
	
	
	DeviceInfo fetchByExitsId(Map<String, Object> params);
    
    
}