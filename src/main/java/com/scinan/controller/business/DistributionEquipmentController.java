package com.scinan.controller.business;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import com.scinan.base.cache.impl.RedisCacheServiceImpl;
import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.controller.BaseController;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.DeviceBelong;
import com.scinan.iot.s6000.dao.domain.DeviceInfo;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.DeviceDistributionService;
import com.scinan.utils.Common;
/**
 * 设备分配控制器
 * @project ZhengshangDataCenter
 * @class com.scinan.controller.business.DistributionEquipmentController
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年08月14日
 * @description
 */
@Controller
@RequestMapping("/distribution/")
public class DistributionEquipmentController extends BaseController{
	
	final static Logger logger = Logger.getLogger(DistributionEquipmentController.class);
	
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private DeviceDistributionService deviceDistributionService;
	
	@Autowired
    private LocaleResolver localeResolver;  
	
	@Autowired
	private RedisCacheServiceImpl redisCacheServiceImpl;
	
	
	
	/**
	 * 设备分配初始化页面
	 * @param request
	 * @param redirect
	 * @param model
	 * @return
	 */
	@RequestMapping("/device/init")
	public String deviceInit(HttpServletRequest request, HttpServletResponse response,String redirect, Model model) {
		try {
			//AccountInfo accountInfoBean = Common.getAccountInfo(request);
			model.addAttribute("systemResourcesBeans", fetchButtons());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/distribution/list";
	}
	
	/**
	 * 设备分配列表
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchByPage(Model model,HttpServletRequest request) throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		try {
			Map<String,String> params = Common.getRequestParameters(request);
			params.put("company_id", accountInfoBean.getCompany_id());
			params.put("deviceId", "1"); //支持为下级用户分配设备
			if(accountInfoBean.getRole_type()==AccountInfo.ACCOUNT_TYPE_COMMON){  //厂商管理员
				if(accountInfoBean.getCompany_id().equals("1044")){
					params.put("type", "4");
				}
				PageBean<DeviceInfo> pageBean = deviceDistributionService.fetchParamsByPage(params);
				return pageBean;
			}else{
				params.put("company_id", accountInfoBean.getCompany_id());
				params.put("user_path", accountInfoBean.getParent_user_path());
				params.put("greater_than_device_level", accountInfoBean.getRole_type().intValue()+"");
				PageBean<DeviceBelong> pageBean = deviceDistributionService.fetchDistributionParamsByPage(accountInfoBean,params);
				return pageBean;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchByPage is error ",e);
		}
		return null;
	}
	
	
	
	/**
	 * 功能说明：为下级代理商或终端用户分配设备信息初始化
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setUserOfDevice")
	public String setRoleOfDevice(HttpServletRequest request,Model model) throws Exception{
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("parent_user_id", accountInfoBean.getId());
			params.put("ids_arr", String.valueOf(request.getParameter("ids_arr")));
			params.put("roleType", accountInfoBean.getRole_type().intValue());
			deviceDistributionService.setModuleInfo(model, params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("setRoleOfDevice is error " + e.getMessage());
		}
		return "/distribution/distributiondevice";
	}
	
	
	
	/**
	 * 设备分配操作
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="distribution", method=RequestMethod.POST)
	@ResponseBody
	public Object distributionOperation(Model model,HttpServletRequest request) throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		try {
			Map<String,String> params = Common.getRequestParameters(request);
			params.put("company_id", accountInfoBean.getCompany_id());
			params.put("device_level", accountInfoBean.getRole_type()+"");
			params.put("user_path", accountInfoBean.getParent_user_path());
			DataCenterResult pageBean = null;
			if(accountInfoBean.getRole_type()==AccountInfo.ACCOUNT_TYPE_COMMON){  //厂商管理员
				pageBean = deviceDistributionService.batchSave(params);
			}
			else{
				pageBean = deviceDistributionService.batchUpdate(params);
			}
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("distribution is error ",e);
		}
		return null;
	}
	
	
	/************************************************设备分配操作**********************************************************/
	/*********************************************************************************************************************/
	
	
	
	/**
	 * 取消设备分配初始化页面
	 * @param request
	 * @param redirect
	 * @param model
	 * @return
	 */
	@RequestMapping("/cancel/device/init")
	public String showLogin(HttpServletRequest request, HttpServletResponse response,String redirect, Model model) {
		try {
			//AccountInfo accountInfoBean = Common.getAccountInfo(request);
			model.addAttribute("systemResourcesBeans", fetchButtons());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/distribution/unlist";
	}
	
	
	/**
	 * 取消设备分配列表
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchCancelParamsByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchCancelParamsByPage(Model model,HttpServletRequest request) throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		try {
			Map<String,String> params = Common.getRequestParameters(request);
			params.put("company_id", accountInfoBean.getCompany_id());
			params.put("less_than_device_level", (accountInfoBean.getRole_type().intValue())+"");
			params.put("user_path", accountInfoBean.getParent_user_path());
			PageBean<DeviceBelong> pageBean = deviceDistributionService.fetchCancelParamsByPage(accountInfoBean,params);
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchCancelParamsByPage is error ",e);
		}
		return null;
	}
	
	
	
	/**
	 * 取消设备分配操作
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="unDistribution", method=RequestMethod.POST)
	@ResponseBody
	public Object unDistributionOperation(Model model,HttpServletRequest request) throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		try {
			Map<String,String> params = Common.getRequestParameters(request);
			DataCenterResult pageBean = null;
			if(accountInfoBean.getRole_type()==AccountInfo.ACCOUNT_TYPE_COMMON){  //厂商管理员
				 params.put("company_id", accountInfoBean.getCompany_id());
				 params.put("less_than_device_level", (accountInfoBean.getRole_type().intValue()+1)+"");
				 params.put("user_path", accountInfoBean.getParent_user_path());
			     pageBean = deviceDistributionService.batchDel(params);
			}else{
				params.put("company_id", accountInfoBean.getCompany_id());
				params.put("device_level", (accountInfoBean.getRole_type().intValue())+"");
				params.put("less_than_device_level", (accountInfoBean.getRole_type().intValue()+1)+"");
				params.put("user_path", accountInfoBean.getParent_user_path());
				pageBean = deviceDistributionService.subCancelAllocation(params);
			}
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("distribution is error ",e);
		}
		return null;
	}
	
	
	
	
	
}
