package com.scinan.iot.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.s6000.dao.DeviceTypeMapper;
import com.scinan.iot.s6000.dao.domain.DeviceTypeBean;
import com.scinan.iot.service.DeviceTypeService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
/**
 * 
 * 
 * @project datacenter
 * @class com.scinan.iot.service.impl.DeviceDistributionServiceImpl
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年08月14日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("deviceTypeService")
public class DeviceTypeServiceImpl extends GenericServiceImpl<DeviceTypeBean, Long> implements DeviceTypeService {
	static Logger logger = Logger.getLogger(DeviceTypeServiceImpl.class);
	
	@Autowired
	private DeviceTypeMapper deviceTypeMapper;
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	
	
	@Override
	protected GenericMapper<DeviceTypeBean, Long> getGenericMapper() {
		return deviceTypeMapper;
	}


	@Override
	public List<DeviceTypeBean> getDeviceTypeList(Long id) {
		AccountInfo ai = accountInfoMapper.fetchById(id); 
		return deviceTypeMapper.getDeviceTypeAndComapnyId(ai.getCompany_id());
	}


	@Override
	public DeviceTypeBean fetchByTypeAndCompanyId(Map<String, Object> map) {
		return deviceTypeMapper.fetchByTypeAndCompanyId(map);
	}
	
	
	
	
	
}
