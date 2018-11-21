package com.scinan.iot.s6000.dao;

import java.util.Map;

import com.scinan.iot.s6000.dao.domain.FilterelementReplaceLog;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface FilterelementReplaceLogMapper extends GenericMapper<FilterelementReplaceLog, Long> {

	Integer countLvXinNum(Map<String,Object> map);
	
	
	

}