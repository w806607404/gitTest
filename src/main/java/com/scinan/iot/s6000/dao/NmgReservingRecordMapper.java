package com.scinan.iot.s6000.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.s6000.dao.domain.NmgReservingRecord;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface NmgReservingRecordMapper extends GenericMapper<NmgReservingRecord, Long> {

	List<NmgReservingRecord>   fetchByExportPage(Map<String,Object>  map);
	
	

}