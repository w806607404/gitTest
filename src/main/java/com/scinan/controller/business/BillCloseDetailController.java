/**
 * @Description:
 * @Package: com.scinan.controller.business 
 * @author: 吴广   
 * @date: 2018年7月20日 下午3:55:09 
 */
package com.scinan.controller.business;

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
import com.scinan.iot.ddeddo.dao.domain.BillCloseDetail;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.BillCloseDetailService;
import com.scinan.utils.Common;


/**
 * @Description: 提现结算控制类
 * @author: 吴广
 * @date: 2018年7月20日 下午3:55:09 
 */
@Controller
@RequestMapping("/bill/")
public class BillCloseDetailController extends BaseController {
	
	final static Logger logger = Logger.getLogger(BillCloseDetailController.class);
	
	@Autowired
	private BillCloseDetailService billCloseDetailService;
	@Autowired
	private AccountInfoService accountInfoService;
	
	
	/**
	 * 提现记录初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/finance/bill/list";
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		AccountInfo user = accountInfoService.fetchById(accountInfoBean.getId());
		model.addAttribute("user", user);
		model.addAttribute("systemResourcesBeans",fetchButtons());
		return jsp;
	}
	
	/**
	 * 提现记录分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchBillCloseByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchBillCloseByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			return billCloseDetailService.fetchParamsByPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchBillCloseByPage is error ",e);
		}
		return null;
	}
	
	
	/**
	 * 申请提现页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("billCloseInit")
	public String billCloseInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/finance/bill/add";
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			AccountInfo user = accountInfoService.fetchById(accountInfoBean.getId());
			model.addAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("billCloseInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	
	/**
	 * 添加提现记录
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addBill", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="财务管理",methods="申请提现记录")
	public Object add(Model model,HttpServletRequest request,BillCloseDetail billCloseDetail) throws Exception {
		try {
			logger.info("billCloseDetail bean :" + billCloseDetail.toString());
			DataCenterResult dataCenterResult = billCloseDetailService.addBillClose(billCloseDetail);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addBill is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	/**
	 * 
	 * @Description: 结算提现记录
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 * @author: 吴广
	 * @date: 2018年7月23日 下午2:05:30
	 */
	@RequestMapping(value="settlement", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="财务管理",methods="结算提现")
	public Object settlement(Model model,HttpServletRequest request,BillCloseDetail billCloseDetail) throws Exception {			
		try {			
			DataCenterResult dataCenterResult = billCloseDetailService.updateBillClose(billCloseDetail);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("settlement is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	/**
	 * 
	 * @Description: 更新提现状态为已完成
	 * @param:
	 * @return：返回结果描述
	 * @throws：异常描述
	 * @author: 吴广
	 * @date: 2018年7月23日 下午2:05:30
	 */
	@RequestMapping(value="completed", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="财务管理",methods="已完成提现")
	public Object completed(Model model,HttpServletRequest request,Long id) throws Exception {			
		try {			
			DataCenterResult dataCenterResult = billCloseDetailService.updateBillClose(id);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("completed is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
}
