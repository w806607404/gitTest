package com.scinan.iot.s6000.dao;

import java.util.List;

import com.scinan.iot.s6000.dao.domain.HotelLocationBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

/**
 * HotelLocationMapper接口类
 * @author kimsun
 *
 */
public interface HotelLocationMapper extends GenericMapper<HotelLocationBean, Long> {

	Integer deleteIds(List<String> list);
	
	
	
	
	
	
	
}
