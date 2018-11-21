package com.scinan.iot.service;

import java.util.List;

import com.scinan.iot.yunwa.dao.domain.Areas;
import com.scinan.mybatis.support.service.GenericService;

public interface AreasService extends GenericService<Areas, String>{

	List<Areas> fetchAreasByParent_id(String parent_id);
	
	List<Areas> fetchProvinceList();
	
	List<Areas> fetchCityList(String parent_id);
	
	List<Areas> fetchAreaList(String parent_id);
}
