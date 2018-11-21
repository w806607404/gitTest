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

import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.LoginLogBean;
import com.scinan.iot.service.LoginLogService;
import com.scinan.utils.Common;
/**
 * 登录日志相关操作控制器
 * @project ddeddo
 * @class com.scinan.controller.system.LoginLogController
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月11日
 * @description
 */
@Controller
@RequestMapping("/userlogin/")
public class LoginLogController{
	
	final static Logger logger = Logger.getLogger(LoginLogController.class);
	
	@Autowired
	private LoginLogService loginLogService;
	
	/**
	 * 登录log日志页面
	 * @param redirect
	 * @param model
	 * @return
	 */
	@RequestMapping("listUI")
	public String listUI(Model model) {
		return "/system/loginlog/list";
	}
	
	/**
	 * 登录log日志分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = Common.getRequestParameters(request);
			PageBean<LoginLogBean> pageBean = loginLogService.fetchParamsByPage(params);
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("LoginLogController fetchByPage is error ",e);
		}
		return null;
	}
	
}
