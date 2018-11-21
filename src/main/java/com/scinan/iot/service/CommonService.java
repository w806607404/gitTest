package com.scinan.iot.service;

import java.util.List;

import com.scinan.iot.s9000.dao.domain.CityBean;
import com.scinan.iot.s9000.dao.domain.CountryBean;
import com.scinan.iot.s9000.dao.domain.ProvinceBean;
import com.scinan.mybatis.support.service.GenericService;
/**
 * CommonService接口.
 * @author kimsun
 *
 */

public interface CommonService extends GenericService<CountryBean, Long> {

	public List<CountryBean> fetchByCountryOfList();
	
	public List<ProvinceBean> fetchByProvinceOfList();
	
	public List<CityBean> fetchByCityOfList();
	
	
}
