package com.scinan.controller.system;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import com.scinan.annotation.SystemLog;
import com.scinan.bean.DataCenterResult;
import com.scinan.controller.BaseController;
import com.scinan.iot.ddeddo.dao.domain.Resource;
import com.scinan.iot.service.RoleService;
import com.scinan.iot.service.SystemResourcesService;

/**
 * 资源操作相关控制器
 * @project ddeddo
 * @class com.scinan.controller.system.ResourcesController
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月10日
 * @description
 */
@Controller
@RequestMapping("/resources/")
public class ResourcesController extends BaseController{
	
	final static Logger logger = Logger.getLogger(ResourcesController.class);
	
	@Autowired
	private SystemResourcesService systemResourcesService;
	@Autowired
	private RoleService roleService;
	@Autowired
    private LocaleResolver localeResolver;
	
	@RequestMapping("list")
	public String list(Model model) throws Exception {
		systemResourcesService.setResourcesModel(model);
		return "/system/resources/list";
	}
	
	/**
	 * 权限分配页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("permissions")
	public Object permissions(Model model,HttpServletRequest request,String id) {
		try {
			if(StringUtils.isEmpty(id)){
				logger.error("permissions params role_id is empty");
				return null;
			}
			Locale  locale = localeResolver.resolveLocale(request);
			systemResourcesService.setPermissionsModel(request,model,id,locale);
			model.addAttribute("role_id", id);
			return "/system/resources/permissions";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("permissions is error " + e.getMessage());
		}
		return null;
	
	}
	
	/**
	 * 查找用户相关菜单资源
	 * @param roleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findRes")
	public List<Resource> findUserRes(Long roleId) {
		List<Resource> resQ = null;
		try {
			resQ = systemResourcesService.fetchSystemResourcesByRoleId(roleId,false);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchSystemResourcesByRoleId is error " + e.getMessage());
		}
		return resQ;
	}
	
	/**
	 * 添加角色对应资源关联关系
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("addRoleRes")
	@SystemLog(module="菜单资源管理",methods="添加角色对应资源关联关系")
	public Object addRoleRes(HttpServletRequest request) throws Exception {
		try {
			//角色对应多个资源id
			String roleId = getPara("roleId");
			String[] resIdArr = getParaValues("resId[]");
			DataCenterResult dataCenterResult = systemResourcesService.addRoleRes(request,roleId,resIdArr);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addRoleRes is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
}
