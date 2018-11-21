package com.scinan.iot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.s1000.dao.FactoryMapper;
import com.scinan.iot.s1000.dao.domain.FactoryBean;
import com.scinan.iot.service.FactoryService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
@Transactional(propagation = Propagation.REQUIRED)
@Service("factoryService")
public class FactoryServiceImpl extends GenericServiceImpl<FactoryBean, String> implements FactoryService {
	static Logger logger = Logger.getLogger(FactoryServiceImpl.class);
    
	@Autowired
	private FactoryMapper factoryMapper;
	
	@Override
	protected GenericMapper<FactoryBean, String> getGenericMapper() {
		return factoryMapper;
	}
	
	public PageBean<FactoryBean> fetchParamsByPage(Map<String, String> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		Conds conds = new Conds();
		
		if(StringUtils.isNotEmpty(params.get("search_name"))){
			conds.like("name", params.get("search_name"));
		}
		if(StringUtils.isNotEmpty(params.get("search_code"))){
			conds.like("code", params.get("search_code"));
		}
		
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		
		List<FactoryBean> factoryBeans = factoryMapper.fetchByPage(map);
		if(null != factoryBeans && factoryBeans.size() >0){
			for (int i = 0; i < factoryBeans.size(); i++) {
				
				FactoryBean factoryBean = factoryBeans.get(i);
				
				String industryId = factoryBean.getIndustry_id();
				
			}
		}
		
		
		Integer count = factoryMapper.count(map);
		return new PageBean<FactoryBean>(count,factoryBeans);
	}

	public DataCenterResult deleteIds(List<String> ids) throws Exception  {
		//TODO 校验
		
		factoryMapper.deleteIds(ids);
		return DataCenterResult.ok();
	}

	public DataCenterResult checkFactoryName(String name, String id,String code) throws Exception {
		List<FactoryBean> lists = factoryMapper.fetchByIdAndFactoryName(name,id,code);
		if(null != lists && lists.size() > 0){
			return DataCenterResult.build(201);
		}else{
			return DataCenterResult.build(200);
		}
	}

	public List<FactoryBean> fetchByRoleId(Long role_id) throws Exception  {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("role_id", role_id);
		List<FactoryBean> factoryBeans = factoryMapper.fetchByRoleId(params);
		return factoryBeans;
		
	}


	public DataCenterResult saveFactory(FactoryBean factoryBean) throws Exception{
		// TODO 校验
		
		factoryBean.setId(factoryBean.getCode());
		factoryMapper.save(factoryBean);
		return DataCenterResult.ok();
	}

	public DataCenterResult updateFactory(FactoryBean factoryBean) throws Exception {
		// TODO 校验
		
		factoryMapper.update(factoryBean);
		return DataCenterResult.ok();
	}
	
	
	/**
	 * 功能说明:查询厂商列表
	 * @param params 归属参数
	 * @return list
	 */
	public List<FactoryBean> findFactoryManagePage(String name,String code,String id){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(StringUtils.isNotEmpty(name)){
			map.put("name", name);
		}
		if(StringUtils.isNotEmpty(code)){
			map.put("code", code);
		}
		if(StringUtils.isNotEmpty(id)){
			map.put("id", id);
		}
		return factoryMapper.findFactoryManagePage(map);
	}
	
	
	
	/**
	 * 功能说明：查询不在范围内的厂商信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<FactoryBean> findFactoryManageNotExistByCompany(Map<String, Object> params) throws Exception{
		return factoryMapper.findFactoryManageNotExistByCompany(params);
	}
	
	
	/**
	 * 功能说明：查询厂商对应的数据信息
	 * @param id 唯一标识
	 * @return FactoryBean
	 */
	public FactoryBean fetchFactoryById(String id){
		return factoryMapper.fetchById(id);
	}

	
	
	
	
}
