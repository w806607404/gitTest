/**
 * @Description:
 * @Package: com.scinan.controller.business 
 * @author: 吴广   
 * @date: 2018年7月9日 下午2:46:39 
 */
package com.scinan.controller.business;

import java.util.Date;
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
import com.scinan.iot.ddeddo.dao.AccountRatioMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.AccountRatio;
import com.scinan.iot.ddeddo.dao.domain.Purchase;
import com.scinan.iot.ddeddo.dao.domain.PurchaseBean;
import com.scinan.iot.s6000.dao.DeviceTypeMapper;
import com.scinan.iot.s6000.dao.domain.DeviceTypeBean;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.PurchaseService;
import com.scinan.utils.Common;

/**
 * @Description: 购货记录控制类
 * @author: 吴广
 * @date: 2018年7月9日 下午2:46:39 
 */
@Controller
@RequestMapping("/purchase/")
public class PurchaseController extends BaseController{

	final static Logger logger = Logger.getLogger(PurchaseController.class);
	
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private DeviceTypeMapper deviceTypeMapper;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private AccountRatioMapper accountRatioMapper;
	
	
	/**
	 * 购货记录初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model,HttpServletRequest request) throws Exception {
		AccountInfo ai = Common.getAccountInfo(request);
		String jsp = "/order/purchase/list";
		model.addAttribute("ai",ai);
		model.addAttribute("systemResourcesBeans",fetchButtons());
		return jsp;
	}
	
	/**
	 * 销售记录分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchPurchaseByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchPurchaseByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			return purchaseService.fetchParamsByPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchPurchaseByPage is error ",e);
		}
		return null;
	}
	
	
	
	/**
	 * 设置物流单号页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifyShipping")
	public String modifySoldInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/order/purchase/modify";
		try {
			String id = request.getParameter("id");			
			PurchaseBean purchase = purchaseService.fetchById(Long.parseLong(id));
			model.addAttribute("purchase",purchase);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("modifyShipping is error " + e.getMessage());
		}
		return jsp;
	}
	
	
	/**
	 * 更新状态
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateStatus", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="订单管理",methods="修改物流状态")
	public Object updateStatus(Model model,HttpServletRequest request,Purchase purchase) throws Exception {
		try{
			DataCenterResult dataCenterResult = purchaseService.upadateStatus(purchase);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("update is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	/**
	 * 更新状态
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateStatus2", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="订单管理",methods="完成购货")
	public Object updateStatus2(Model model,HttpServletRequest request,Long id) throws Exception {
		try{
			PurchaseBean purchase = purchaseService.fetchById(id);
			Purchase bean = new Purchase();
			bean.setId(purchase.getId());
			bean.setStatus(purchase.getStatus());
			bean.setShipping(purchase.getShipping());
			bean.setPay_type(purchase.getPay_type());
			bean.setAmount(purchase.getAmount());
			bean.setParent_id(purchase.getParent_id());
			DataCenterResult dataCenterResult = purchaseService.upadateStatus(bean);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("update is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	/**
	 * 购货记录初始化页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addPurchaseInit")
	public String addPurchaseInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/order/purchase/add";
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			AccountInfo user = accountInfoService.fetchById(accountInfoBean.getId());
			
			List<DeviceTypeBean>deviceList = deviceTypeMapper.getDeviceTypeAndComapnyId(accountInfoBean.getCompany_id());
			model.addAttribute("user",user);
			model.addAttribute("deviceList",deviceList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addPurchaseInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	
	/**
	 * 更新状态
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addPurchase", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="财务管理",methods="添加购货记录")
	public Object add(Model model,HttpServletRequest request,PurchaseBean purchaseBean) throws Exception {
		try{
			DataCenterResult dataCenterResult = purchaseService.addPurchase(purchaseBean);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addPurchase is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	/**
	 * 获取产品单价
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchProductPrice", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="财务管理",methods="获取产品单价")
	public Object fetchProductPrice(Model model,HttpServletRequest request,Long id) throws Exception {
		try{
			AccountRatio accountRatio = accountRatioMapper.fetchByDevice_type(id);
			return accountRatio.getProduct_price();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchProductPrice is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
}
