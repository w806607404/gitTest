package com.scinan.iot.service;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.scinan.bean.DataCenterResult;
import com.scinan.iot.ddeddo.dao.domain.Resource;
import com.scinan.mybatis.support.service.GenericService;

/**
 * 系统菜单资源业务接口相关操作
 * @project datacenter
 * @class com.scinan.iot.service.SystemResourcesService
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月6日
 * @description
 */
public interface SystemResourcesService extends GenericService<Resource, Long>{
	

	/**
	 * 根据角色id获取菜单资源
	 * @param role_id
	 * @param flag dao 返回结果就是否需要二次加工
	 * @return
	 */
	List<Resource> fetchSystemResourcesByRoleId(Long role_id,boolean flag)throws Exception ;


	/**
	 * 设置首页需要的相关数据
	 * @param model
	 * @param request
	 * @param locale
	 */
	void setIndexModel(Model model, HttpServletRequest request,Locale  locale);

	/**
	 * 设置系统资源列表页面需要的相关数据
	 * @param model
	 */
	void setResourcesModel(Model model);

	/**
	 * 设备权限列表页面需要的相关数据
	 * @param request
	 * @param model
	 * @param id 
	 */
	void setPermissionsModel(HttpServletRequest request,Model model, String id,Locale  locale)throws Exception;

	/**
	 * 给角色分配菜单
	 * @param request 
	 * @param roleId
	 * @param resIdArr
	 * @return
	 */
	DataCenterResult addRoleRes(HttpServletRequest request, String roleId, String[] resIdArr);

	
	
    
}
