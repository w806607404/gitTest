package com.scinan.controller.system;


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
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.utils.Common;
/**
 * 登录登出控制类
 * @project datacenter
 * @class com.scinan.controller.system.LoginController
 * @copyright www.scinan.com
 * @author Kim
 * @date 2016年12月07日
 * @description
 */
@Controller
public class LoginController{
	
	final static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
    private LocaleResolver localeResolver;  
	
	@Autowired
	private RedisCacheServiceImpl redisCacheServiceImpl;
	
	/**
	 * 管理登录
	 * @param request
	 * @param redirect
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String showLogin(HttpServletRequest request, HttpServletResponse response,String redirect, Model model) {
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			if(null != accountInfoBean)return "redirect:/index.shtml";
			//accountInfoService.logout(request,response);//进入登录页面先执行下退出操作
			
			Map<String, String> map = Common.getRequestParameters(request);
			String is_show = map.get("is_show");	
			
			String login_state = (String)redisCacheServiceImpl.get("login_state");
			if(Common.isEmpty(login_state)){
				login_state = "1";
			}
			
			model.addAttribute("login_state", login_state);
			model.addAttribute("is_show", is_show);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}
	
	/**
	 * 用户登录
	 * @param loginpwd
	 * @param loginname
	 * @param verifycode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody	
	public DataCenterResult userLogin(String loginpwd, String loginname,String verifycode,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			DataCenterResult result = accountInfoService.userLogin(loginname,loginpwd, verifycode,request, response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("login is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			accountInfoService.logout(request,response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("logout is error " + e.getMessage());
		}
		return "redirect:/login.shtml?is_show=0";
	}
	
	/**
	 * 跳转权限页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/denied")
	public String denied(HttpServletRequest request, HttpServletResponse response) {
		return "denied";
	}
	
}
