/**
 * @Description:
 * @Package: com.scinan.controller.business 
 * @author: 吴广   
 * @date: 2018年7月17日 上午10:29:33 
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
import com.scinan.bean.DataCenterResult;
import com.scinan.controller.BaseController;
import com.scinan.iot.ddeddo.dao.domain.AccountBack;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.AccountSold;
import com.scinan.iot.ddeddo.dao.domain.AccountSoldBean;
import com.scinan.iot.service.AccountBackService;
import com.scinan.iot.service.AccountSoldService;
import com.scinan.iot.service.AreasService;
import com.scinan.iot.yunwa.dao.domain.Areas;
import com.scinan.utils.Common;
import com.scinan.utils.StringUtil;

/**
 * @Description: 商家退换货控制类
 * @author: 吴广
 * @date: 2018年7月17日 上午10:29:33 
 */
@Controller
@RequestMapping("/back/")
public class AccountBackController extends BaseController{
	
	final static Logger logger = Logger.getLogger(AccountBackController.class);
	
	@Autowired
	private AccountBackService accountBackService;
	@Autowired
	private AreasService areasService;
	
	
	/**
	 * 退换货记录初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/order/back/list";
		AccountInfo accountInfo = Common.getAccountInfo(request);
		model.addAttribute("accountInfo", accountInfo);
		model.addAttribute("systemResourcesBeans",fetchButtons());		
		return jsp;
	}
	
	/**
	 * 退换货记录分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchBackByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchBackByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			AccountInfo accountInfo = Common.getAccountInfo(request);
			model.addAttribute("accountInfo", accountInfo);
			return accountBackService.fetchParamsByPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchBackByPage is error ",e);
		}
		return null;
	}
	
	
	/**
	 * 退换货初始化页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("accountBackInit")
	public String accountBackInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/order/back/add";
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);						
			model.addAttribute("user", accountInfoBean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("accountBackInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	
	/**
	 * 添加退换货记录
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addBack", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="订单管理",methods="添加退换货记录")
	public Object add(Model model,HttpServletRequest request,AccountBack accountBack) throws Exception {
		try {
			logger.info("accountBack bean :" + accountBack.toString());
			DataCenterResult dataCenterResult = accountBackService.addAccountBack(accountBack);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addBack is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	/**
	 * 删除退换货记录
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delBack", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="订单管理",methods="删除退换货记录")
	public Object auditAccount(Model model,HttpServletRequest request,Long id) throws Exception {
		try {			
			DataCenterResult dataCenterResult = accountBackService.deleteAccountBack(id);
				 
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("delBack is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
		
	}
	
	
	/**
	 * 修改退换货记录页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifyBackInit")
	public String modifyBackInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/order/back/modify";
		try {
			String id = request.getParameter("id");			
			AccountBack accountBack = accountBackService.fetchById(Long.parseLong(id));
			model.addAttribute("back", accountBack);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("modifyBackInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	/**
	 * 更新退换货记录
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateBack", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="订单管理",methods="修改退换货记录")
	public Object update(Model model,HttpServletRequest request,AccountBack ab) throws Exception {
		try{			
			accountBackService.update(ab);
			return DataCenterResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateBack is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	/**
	 * 更新退换货记录状态 0:申请中，1：已确认，2：已完成
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateBackStatus", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="订单管理",methods="更新退换货记录状态")
	public Object updateBackStatus(Model model,HttpServletRequest request,Long id) throws Exception {
		try{		
			DataCenterResult dataCenterResult = accountBackService.updateAccountBackStatus(id);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateBackStatus is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
}
