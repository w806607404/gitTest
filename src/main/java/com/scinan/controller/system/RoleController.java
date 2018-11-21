package com.scinan.controller.system;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scinan.annotation.SystemLog;
import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.controller.BaseController;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.service.RoleService;
import com.scinan.utils.Common;
import com.scinan.utils.StringUtil;
/**
 * 角色管理控制器类相关操作
 * @project ddeddo
 * @class com.scinan.controller.system.RoleController
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月10日
 * @description
 */
@Controller
@RequestMapping("/role/")
public class RoleController extends BaseController{
	
	final static Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping("list")
	public String list(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/system/role/role_list";
		try {
			//当前用户的角色ID
			model.addAttribute("systemResourcesBeans", fetchButtons());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("setRoleModel is error " + e.getMessage());
		}
		return jsp;
	}
	
	/**
	 * 角色列表数据分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			Map<String,String> params = getParamsMap();
			PageBean<Role> pageBean = null;
			if(accountInfoBean.getRole_type()==AccountInfo.ACCOUNT_TYPE_ADMIN){
				pageBean = roleService.fetchParamsByPage(params);
			}else{
				params.put("parent_id", String.valueOf(accountInfoBean.getParent_role_id()));
				pageBean = roleService.fetchParamsByPage(params);
			}
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchByPage is error ",e);
			return new PageBean<Role>();
		}
	}
	
	
	
	
	
	@RequestMapping("addRoleInit")
	public String addRoleInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "";
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			//角色类型列表
			if(accountInfoBean.getRole_type()==AccountInfo.ACCOUNT_TYPE_ADMIN){
			   model.addAttribute("vtlRoleTypeList", roleService.fetchVtlRoleTypeList("2"));
			   jsp = "/system/role/role_add";
			}else{
				model.addAttribute("vtlRoleTypeList", roleService.fetchByRoleTypeALL((accountInfoBean.getRole_type().intValue())+""));
				jsp = "/system/role/sub_role_add";
			}
			model.addAttribute("userRoleType",accountInfoBean.getRole_type());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addRoleInit is error " + e.getMessage());
		}
		
		return jsp;
	}
	
	
	
	/**
	 * 添加角色
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="角色管理",methods="添加角色")
	public Object add(Model model,HttpServletRequest request,Role vtlRole) throws Exception {
		logger.info("role bean :" + vtlRole.toString());
		try {
			DataCenterResult dataCenterResult = roleService.addRole(vtlRole,request);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("add is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	@RequestMapping("modifyRoleInit")
	public String modifyRoleInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "";
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			//主键ID
			String id = request.getParameter("id");
			//角色类型列表
			if(accountInfoBean.getRole_type()==AccountInfo.ACCOUNT_TYPE_ADMIN){
				 model.addAttribute("vtlRoleTypeList", roleService.fetchVtlRoleTypeList("2"));
				 jsp = "/system/role/role_modify";
			}else{
			     model.addAttribute("vtlRoleTypeList", roleService.fetchVtlRoleTypeList("3"));
			     jsp = "/system/role/sub_role_modify";
			}
			model.addAttribute("userRoleType",accountInfoBean.getRole_type());
			if(!StringUtil.isNull(id)){
			  model.addAttribute("vtlRoleBean", roleService.fetchById(Long.parseLong(id)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("modifyRoleInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	
	/**
	 * 修改角色
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="角色管理",methods="修改角色")
	public Object update(Model model,HttpServletRequest request,Role vtlRole) throws Exception {
		try{	
			logger.info("role bean :" + vtlRole.toString());
			DataCenterResult dataCenterResult = roleService.updateRole(vtlRole,request);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("update is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	/**
	 * 删除角色
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="角色管理",methods="删除角色")
	public Object delete(HttpServletRequest request,String ids) throws Exception {
		try{
			logger.info("role ids :" + ids);
			DataCenterResult dataCenterResult = roleService.deleteRoles(request,ids);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("delete is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	
}
