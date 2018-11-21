package com.scinan.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.scinan.base.constant.CacheConstantBase;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.utils.CookieUtils;
import com.scinan.utils.RedisUtil;
import com.scinan.utils.StringUtil;


/**
 * 请求拦截器
 * 
 * @project datacenter
 * @class com.scinan.intercepter.LoginInterceptor
 * @copyright www.scinan.com
 * @author Kim
 * @date 2018年08月10日
 * @description
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	final static Logger logger = Logger.getLogger(LoginInterceptor.class);
	
	@Autowired
	private AccountInfoService accountInfoService;
	
	
	//在Handler执行之前处理
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String uri = request.getRequestURI();
		if(uri.contains("/login.shtml") || uri.contains("/changeI18n/") || uri.contains("/upload/file")){
			return true;
		}
		else
		{
			/***************判断用户是否登录  从cookie中取token********************/
			String token = CookieUtils.getUserName(request, "DISTRIBUTION_DC_TOKEN");
			//String token = (String)request.getSession().getAttribute("JIEROU_DC_TOKEN");
			logger.info("LoginInterceptor is token ========================="+token);
			if(StringUtil.isNull(token)){
				logger.error("token is null");
				RedisUtil.remove("REDIS_USER_SESSION_" + token);
				//CookieUtils.clearAllCookies(request, response);
				CookieUtils.delCookies(request, response, "DISTRIBUTION_DC_TOKEN",token);
				request.getSession().removeAttribute("DISTRIBUTION_DC_TOKEN");
				response.sendRedirect(request.getContextPath()+ "/login.shtml");
				return false;	
			}
			
			/*****************查看redis缓存中是否存在此数据***********************/
			String json = (String)RedisUtil.get("REDIS_USER_SESSION_" + token);
			if(StringUtils.isEmpty(json)){
				logger.error("user json is null");
				RedisUtil.remove("REDIS_USER_SESSION_" + token);
				//CookieUtils.clearAllCookies(request, response);
				CookieUtils.delCookies(request, response, "DISTRIBUTION_DC_TOKEN",token);
				request.getSession().removeAttribute("DISTRIBUTION_DC_TOKEN");
				response.sendRedirect(request.getContextPath()+ "/login.shtml");
				//返回false
				return false;
			}
			
			//该用户无效状态，退出系统 
			if(!StringUtil.isNull(json)){
				com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(json);
				String status = jsonObject.getString("status");
				if(!StringUtil.isNull(status) && "0".equals(status)){    
					logger.error("user status json is :" + status);
					RedisUtil.remove("REDIS_USER_SESSION_" + token);
					//CookieUtils.clearAllCookies(request, response);
					CookieUtils.delCookies(request, response, "DISTRIBUTION_DC_TOKEN",token);
					request.getSession().removeAttribute("DISTRIBUTION_DC_TOKEN");
					response.sendRedirect(request.getContextPath()+ "/login.shtml");
					//返回false
					return false;
				}
			}
			//更新过期时间
			RedisUtil.set(CacheConstantBase.TIME_HALFDAY,"REDIS_USER_SESSION_" + token, json);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//验证账户是否有效
		/*String accountLockStatus = (String)redisSimpleService.get("LOCK_ACC_" + accountInfoBean.getId());
		if("1".equals(accountLockStatus)){
			logger.error("accountLockStatus already locked");
			
			redisSimpleService.delete("REDIS_USER_SESSION" + ":" + token);
			CookieUtils.deleteCookie(request, response, "JIEROU_DC_TOKEN");
			
			response.sendRedirect(request.getContextPath()+ "/login.shtml");
			//返回false
			return false;
		}*/
		
		//菜单权限控制
		/*String roleAllJson = (String)redisSimpleService.get("ROLE_ALL");//所有菜单
		if(roleAllJson.contains(uri)){
			String roleJson = (String)redisSimpleService.get("ROLE_" + accountInfoBean.getRole_id());//角色下的菜单集合
			if( roleJson==null || !roleJson.contains(uri)){
				request.getRequestDispatcher("/denied.shtml").forward(request, response);//跳转无权限页面
				return false;
			}
		}*/
		//返回值决定handler是否执行。true：执行，false：不执行。
		return true;
	}

	// handler执行之后，返回ModelAndView之前
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	
	// 返回ModelAndView之后。
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//响应用户之后。

	}

}
