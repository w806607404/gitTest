/**
 * @Description:
 * @Package: com.scinan.controller.business 
 * @author: 吴广   
 * @date: 2018年8月9日 下午4:15:10 
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

import com.scinan.bean.PageBean;
import com.scinan.controller.BaseController;
import com.scinan.iot.ddeddo.dao.domain.NotifySend;
import com.scinan.iot.service.AreasService;
import com.scinan.iot.service.NotifySendService;
import com.scinan.iot.service.SubsidyAmountBillDetailService;
import com.scinan.iot.yunwa.dao.domain.Areas;
import com.scinan.utils.Common;

/**
 * @Description: 补贴余额账单明细控制类
 * @author: 吴广
 * @date: 2018年8月9日 下午4:15:10 
 */
@Controller
@RequestMapping("/subsidyAmount/")
public class SubsidyAmountBillDetailController extends BaseController{
	
	final static Logger logger = Logger.getLogger(SubsidyAmountBillDetailController.class);
	
	@Autowired
	private SubsidyAmountBillDetailService subsidyAmountService;
	@Autowired
	private AreasService areasService;
	
	/**
	 * 补贴余额账单明细初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String noticeList(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/finance/subsidyAmount/list";
		List<Areas> provinceList = areasService.fetchProvinceList();
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("systemResourcesBeans",fetchButtons());
		return jsp;
	}
	
	/**
	 * 补贴余额账单明细记录分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchSubsidyAmountByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchSubsidyAmountByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = Common.getRequestParameters(request);			
			PageBean<NotifySend> pageBean = subsidyAmountService.fetchParamsByPage(params);
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchSubsidyAmountByPage  is error ",e);
		}
		return null;
	}
}
