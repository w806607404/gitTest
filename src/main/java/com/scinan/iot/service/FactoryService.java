package com.scinan.iot.service;

import java.util.List;
import java.util.Map;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.s1000.dao.domain.FactoryBean;
import com.scinan.mybatis.support.service.GenericService;
/**
 * 厂商管理业务相关接口
 * 
 * @project datacenter
 * @class com.scinan.iot.service.FactoryService
 * @copyright www.scinan.com
 * @author Zola
 * @date 2016年7月13日
 * @description
 */
public interface FactoryService extends GenericService<FactoryBean, String>{
	
	/**
	 * 获取分页信息
	 * @param params
	 * @return
	 */
	PageBean<FactoryBean> fetchParamsByPage(Map<String, String> params) throws Exception ;
	
	/**
	 * 批量删除厂商信息
	 * @param ids
	 * @return 
	 */
	DataCenterResult deleteIds(List<String> ids) throws Exception ;
	
	/**
	 * 保存修改时检查厂商名称的唯一性
	 * @param factory_name
	 * @param id
	 * @param code 
	 * @return
	 */
	DataCenterResult checkFactoryName(String factory_name,String id, String code)throws Exception ;
	
	/**
	 * 根据角色id获取厂商集合
	 * @param role_id
	 * @return
	 */
	List<FactoryBean> fetchByRoleId(Long role_id)throws Exception ;


	/**
	 * 保存厂商信息
	 * @param factoryBean
	 * @return
	 */
	DataCenterResult saveFactory(FactoryBean factoryBean) throws Exception;

	/**
	 * 修改厂商信息
	 * @param factoryBean
	 * @return
	 */
	DataCenterResult updateFactory(FactoryBean factoryBean)throws Exception ;
	
	
	/**
	 * 功能说明:查询厂商列表
	 * @param params 归属参数
	 * @return list
	 */
	List<FactoryBean> findFactoryManagePage(String name,String code,String id);
	
	
	/**
	 * 功能说明：查询不在范围内的厂商信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<FactoryBean> findFactoryManageNotExistByCompany(Map<String, Object> params) throws Exception;
	
	
	/**
	 * 功能说明：查询厂商对应的数据信息
	 * @param id 唯一标识
	 * @return FactoryBean
	 */
	public FactoryBean fetchFactoryById(String id);
	
}
