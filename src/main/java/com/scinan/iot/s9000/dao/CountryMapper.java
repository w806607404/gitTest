package com.scinan.iot.s9000.dao;

import java.util.List;

import com.scinan.iot.s9000.dao.domain.CountryBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface CountryMapper extends GenericMapper<CountryBean, Long> {

	CountryBean fetchByCountryOfName(String country_code);
	
	List<CountryBean> fetchByCountryOfList();

}
