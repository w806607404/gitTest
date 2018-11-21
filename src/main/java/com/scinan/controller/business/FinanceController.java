package com.scinan.controller.business;

import java.util.List;
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
import com.scinan.controller.BaseController;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.AccountRatio;
import com.scinan.iot.s6000.dao.domain.DeviceTypeBean;
import com.scinan.iot.service.AccountRatioService;
import com.scinan.iot.service.DeviceTypeService;
import com.scinan.utils.Common;
/**
 * 财务管理控制类
 * 
 * @project ddeddo
 * @class com.scinan.controller.system.FinanceController
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月23日
 * @description
 */
@Controller
@RequestMapping("/finance/")
public class FinanceController extends BaseController{
	
	final static Logger logger = Logger.getLogger(FinanceController.class);
	
	@Autowired
	private AccountRatioService accountRatioService;
	@Autowired
	private DeviceTypeService deviceTypeService;
	
	
	/**
	 * 分成初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ratioList")
	public String list(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/finance/ratio/list";
		model.addAttribute("systemResourcesBeans",fetchButtons());
		return jsp;
	}
	
	/**
	 * 产品信息以及分成比例分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchRatioByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			return accountRatioService.fetchParamsByPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchByPage is error ",e);
		}
		return null;
	}
	
	
	/**
	 * 添加分成比例初始化页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addRatioInit")
	public String addRoleInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/finance/ratio/add";
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			//设备类型列表ByCompany_id
			List<DeviceTypeBean> deviceTypeBeanList = deviceTypeService.getDeviceTypeList(accountInfoBean.getId());
			model.addAttribute("typeList", deviceTypeBeanList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addUserInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	
	/**
	 * 添加分成比例
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addRatio", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="财务管理",methods="添加分成比例")
	public Object add(Model model,HttpServletRequest request,AccountRatio accountRatio) throws Exception {
		try {
			logger.info("accountInfoBean bean :" + accountRatio.toString());
			Map<String, String> params = getParamsMap();
			DataCenterResult dataCenterResult = accountRatioService.addAccountRatio(request, accountRatio, params);	
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("add is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	/**
	 * 删除操作
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delRatio", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="财务管理",methods="比例删除")
	public Object auditAccount(Model model,HttpServletRequest request,Long id) throws Exception {
		try {
			 if(accountRatioService.delete(id)){
				 return DataCenterResult.ok();
			 }
			return DataCenterResult.build(-101);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteAccount is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
		
	}
	
	
	/**
	 * 修改比例信息初始化页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifyRatioInit")
	public String modifyUserInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/finance/ratio/modify";
		try {
			String id = request.getParameter("id");
			AccountRatio ar = accountRatioService.fetchById(new Long(id));
			model.addAttribute("ratio", ar);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("modifyUserInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	/**
	 * 更新比例
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateRatio", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="财务管理",methods="修改分成比例")
	public Object update(Model model,HttpServletRequest request,AccountRatio accountRatio) throws Exception {
		try{
			Map<String, String> params = getParamsMap();
			DataCenterResult dataCenterResult = accountRatioService.updateAccountRatio(request,accountRatio,params);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("update is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
}
