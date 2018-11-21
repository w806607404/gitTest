package com.scinan.iot.s6000.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.s6000.dao.domain.RepairMaintainInfo;
import com.scinan.mybatis.support.mapper.GenericMapper;

/**
 * RepairMaintainInfoMapper接口类
 * @author Kim
 *
 */
public interface RepairMaintainInfoMapper extends GenericMapper<RepairMaintainInfo, Long> {

	List<RepairMaintainInfo>   fetchByPage(Map<String,Object>  map);
	
	RepairMaintainInfo fetchById(Map<String,Object>   map);
	
	
	
	
	
	
	
	

}
