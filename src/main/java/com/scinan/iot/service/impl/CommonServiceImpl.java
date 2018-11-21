		package com.scinan.iot.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.iot.s9000.dao.CityMapper;
import com.scinan.iot.s9000.dao.CountryMapper;
import com.scinan.iot.s9000.dao.ProvinceMapper;
import com.scinan.iot.s9000.dao.domain.CityBean;
import com.scinan.iot.s9000.dao.domain.CountryBean;
import com.scinan.iot.s9000.dao.domain.ProvinceBean;
import com.scinan.iot.service.CommonService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;

/**
 * 
 * 
 * @project datacenter
 * @class com.scinan.iot.service.impl.CommonServiceImpl
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年6月19日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("commonService")
public class CommonServiceImpl extends GenericServiceImpl<CountryBean, Long> implements CommonService {
	static Logger logger = Logger.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private CountryMapper  countryMapper;
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private CityMapper cityMapper;
	
	@Override
	protected GenericMapper<CountryBean, Long> getGenericMapper() {
		return countryMapper;
	}

	@Override
	public List<CountryBean> fetchByCountryOfList(){
		return countryMapper.fetchByCountryOfList();
	}
	
	@Override
	public List<ProvinceBean> fetchByProvinceOfList(){
		return provinceMapper.fetchByProvinceOfList();
	}
	
	@Override
	public List<CityBean> fetchByCityOfList(){
		return cityMapper.fetchByCityOfList();
	}
	
	
	
	
}
