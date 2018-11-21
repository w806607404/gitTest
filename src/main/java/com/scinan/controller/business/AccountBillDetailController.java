/**
 * @Description:
 * @Package: com.scinan.controller.business 
 * @author: 吴广   
 * @date: 2018年7月27日 下午2:48:09 
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
import com.scinan.iot.service.AccountBillDetailService;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.AreasService;
import com.scinan.iot.yunwa.dao.domain.Areas;

/**
 * @Description: 分红账单记录控制类
 * @author: 吴广
 * @date: 2018年7月27日 下午2:48:09 
 */
@Controller
@RequestMapping("/billDetail/")
public class AccountBillDetailController extends BaseController {
	final static Logger logger = Logger.getLogger(AccountBillDetailController.class);
	
	@Autowired
	private AccountBillDetailService accountBillDetailService;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private AreasService areasService;
	
	/**
	 * 分红账单记录初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/finance/billDetail/list";
		List<Areas> provinceList =areasService.fetchProvinceList();
		model.addAttribute("systemResourcesBeans",fetchButtons());
		model.addAttribute("provinceList", provinceList);
		return jsp;
	}
	
	/**
	 * 分红账单记录分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchBillDetailByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchBillDetailByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			params.put("type", "1");
			return accountBillDetailService.fetchParamsByPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchBillDetailByPage is error ",e);
		}
		return null;
	}
	
	
	/**
	 * 删除销售记录操作
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delBillDetail", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="财务管理",methods="删除分红账单记录")
	public Object delBillDetail(Model model,HttpServletRequest request,Long id) throws Exception {
		try {			
			DataCenterResult dataCenterResult = accountBillDetailService.deleteBillDetail(id);
				 
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("delBillDetail is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
		
	}
}
