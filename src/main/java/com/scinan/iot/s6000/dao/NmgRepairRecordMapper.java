package com.scinan.iot.s6000.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.s6000.dao.domain.NmgRepairRecord;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface NmgRepairRecordMapper extends GenericMapper<NmgRepairRecord, Long> {

	List<NmgRepairRecord>  fetchByExportPage(Map<String,Object>   map);
	
	
	

}