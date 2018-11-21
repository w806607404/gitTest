/**
 * @Description:
 * @Package: com.scinan.controller.business 
 * @author: 吴广   
 * @date: 2018年7月2日 上午11:56:24 
 */
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
import com.scinan.utils.StringUtil;
import com.scinan.bean.DataCenterResult;
import com.scinan.controller.BaseController;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.AccountSold;
import com.scinan.iot.ddeddo.dao.domain.AccountSoldBean;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.AccountSoldService;
import com.scinan.iot.service.AreasService;
import com.scinan.iot.yunwa.dao.domain.Areas;
import com.scinan.utils.Common;

/**
 * @Description: 销售记录控制类
 * @author: 吴广
 * @date: 2018年7月2日 上午11:56:24 
 */
@Controller
@RequestMapping("/sold/")
public class AccountSoldController extends BaseController {
	
	final static Logger logger = Logger.getLogger(AccountSoldController.class);
	
	@Autowired
	private AccountSoldService accountSoldService;	
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private AreasService areasService;
	
	/**
	 * 销售记录初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/order/sold/list";
		List<Areas> provinceList =areasService.fetchProvinceList();
		model.addAttribute("systemResourcesBeans",fetchButtons());
		model.addAttribute("provinceList", provinceList);
		return jsp;
	}
	
	/**
	 * 销售记录分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchSoldByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchAuditPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			return accountSoldService.fetchParamsByPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchSoldByPage is error ",e);
		}
		return null;
	}
	
	
	/**
	 * 添加初始化页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addSoldInit")
	public String addRoleInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/order/sold/add";
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);			
			List<Areas> provinceList =areasService.fetchProvinceList();		
			model.addAttribute("provinceList", provinceList);
			model.addAttribute("user", accountInfoBean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addSoldInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	
	/**
	 * 添加销售记录
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addSold", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="订单管理",methods="添加销售记录")
	public Object add(Model model,HttpServletRequest request,AccountSold accountSold) throws Exception {
		try {
			logger.info("accountInfoBean bean :" + accountSold.toString());
			//Map<String, String> params = getParamsMap();
			DataCenterResult dataCenterResult = accountSoldService.addAccountSold(accountSold);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addSold is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	/**
	 * 删除销售记录操作
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delSold", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="订单管理",methods="删除销售记录")
	public Object auditAccount(Model model,HttpServletRequest request,Long id) throws Exception {
		try {			
			DataCenterResult dataCenterResult = accountSoldService.deleteAccountSold(id);
				 
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteAccount is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
		
	}
	
	
	/**
	 * 修改销售记录页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifySoldInit")
	public String modifySoldInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/order/sold/modify";
		try {
			String id = request.getParameter("id");			
			AccountSoldBean accountSold = accountSoldService.fetAccountSoldBean(Long.parseLong(id));
			List<Areas> provinceList =areasService.fetchProvinceList();
			model.addAttribute("sold", accountSold);
			model.addAttribute("provinceList", provinceList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("modifySoldInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	/**
	 * 更新销售记录
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateSold", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="订单管理",methods="修改销售记录")
	public Object update(Model model,HttpServletRequest request,AccountSoldBean accountSoldBean) throws Exception {
		try{
			if (StringUtil.isNotEmpty(accountSoldBean.getProvince_id())) {
				Areas areas = areasService.fetchById(accountSoldBean.getProvince_id());
				accountSoldBean.setProvince_name(areas.getArea_name());
			}
			if (StringUtil.isNotEmpty(accountSoldBean.getCity_id())) {
				Areas areas = areasService.fetchById(accountSoldBean.getCity_id());
				accountSoldBean.setCity_name(areas.getArea_name());
			}
			if (StringUtil.isNotEmpty(accountSoldBean.getDistrict_id())) {
				Areas areas = areasService.fetchById(accountSoldBean.getDistrict_id());
				accountSoldBean.setDistrict_name(areas.getArea_name());
			}
			
			DataCenterResult dataCenterResult = accountSoldService.updateAccountSold(accountSoldBean);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateSold is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	@RequestMapping(value="cityList", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="订单管理",methods="根据省ID获得对应的市集合")
	public List<Areas> fetchCityList(Model model,HttpServletRequest request,String parent_id) throws Exception {
		try{			
			List<Areas> cityList = areasService.fetchCityList(parent_id);
			return cityList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("cityList is error " + e.getMessage());
			return null;
		}
		
		
	}
	
	@RequestMapping(value="districtList", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="订单管理",methods="根据市ID获得对应的区集合")
	public List<Areas> fetchDistrictList(Model model,HttpServletRequest request,String parent_id) throws Exception {
		try{			
			List<Areas> districtList = areasService.fetchAreaList(parent_id);
			return districtList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("districtList is error " + e.getMessage());
			return null;
		}
	}
}
