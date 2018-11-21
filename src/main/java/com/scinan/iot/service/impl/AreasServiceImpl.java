package com.scinan.iot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.iot.service.AreasService;
import com.scinan.iot.yunwa.dao.AreasMapper;
import com.scinan.iot.yunwa.dao.domain.Areas;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;


@Transactional(propagation = Propagation.REQUIRED)
@Service("areasService")
public class AreasServiceImpl extends GenericServiceImpl<Areas, String> implements AreasService {

	@Autowired
	public AreasMapper areasMapper;

	@Override
	public List<Areas> fetchAreasByParent_id(String parent_id) {
		return areasMapper.fetchAreasByParent_id(parent_id);
		
	}

	@Override
	protected GenericMapper<Areas, String> getGenericMapper() {
		return areasMapper;
	}

	
	@Override
	public List<Areas> fetchProvinceList() {
		
		return areasMapper.fetchProvinceList();
	}

	
	@Override
	public List<Areas> fetchCityList(String parent_id) {
		
		return areasMapper.fetchCityList(parent_id);
	}

	
	@Override
	public List<Areas> fetchAreaList(String parent_id) {
		
		return areasMapper.fetchAreaList(parent_id);
	}
	


	
	
	
	
}
