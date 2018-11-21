/**
 * @Description:
 * @Package: com.scinan.controller.business 
 * @author: 吴广   
 * @date: 2018年7月17日 下午3:30:32 
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

import com.scinan.controller.BaseController;
import com.scinan.iot.service.AreasService;
import com.scinan.iot.service.UserBackService;
import com.scinan.iot.yunwa.dao.domain.Areas;

/**
 * @Description: 用户退换货控制类
 * @author: 吴广
 * @date: 2018年7月17日 下午3:30:32 
 */
@Controller
@RequestMapping("/userBack/")
public class UserBackController extends BaseController {
	
	final static Logger logger = Logger.getLogger(UserBackController.class);
	
	@Autowired
	private UserBackService userBackService;
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
		String jsp = "/order/userback/list";
		List<Areas> provinceList =areasService.fetchProvinceList();
		model.addAttribute("systemResourcesBeans",fetchButtons());
		model.addAttribute("provinceList", provinceList);
		return jsp;
	}
	
	/**
	 * 退换货记录分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchUserBackByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchUserBackByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			return userBackService.fetchParamsByPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchUserBackByPage is error ",e);
		}
		return null;
	}
}
