package com.scinan.iot.s9000.dao;

import java.util.List;

import com.scinan.iot.s9000.dao.domain.CityBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface CityMapper extends GenericMapper<CityBean, Long> {

	List<CityBean> fetchByCityList (String province_code);
	
	CityBean fetchByCityOfName(String city_code);
	
	List<CityBean> fetchByCityOfList();

}
