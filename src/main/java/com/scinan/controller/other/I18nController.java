package com.scinan.controller.other;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.scinan.utils.Common;
import com.scinan.utils.IpAddressUtil;

/**
 * 国际化控制类
 * @project datacenter
 * @class com.scinan.controller.other.I18nController
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月7日
 * @description
 */
@Controller
public class I18nController {
	static Logger logger = Logger.getLogger(IpAddressUtil.class);
	@Autowired
    private LocaleResolver localeResolver;  

	@RequestMapping(value="/changeI18n/{i18nType}")
	public String changeI18n(@PathVariable String i18nType,HttpServletRequest req,HttpServletResponse resp) throws Exception{
		String redirect_url = "redirect:";	
		logger.info("-------------->>>>>>>>>>>>>>>>>>>>>>>i18nType= " + i18nType);

		try {
			if(i18nType.equals("cn")){
	            Locale locale = new Locale("zh", "CN"); 
	            (new CookieLocaleResolver()).setLocale (req, resp, locale);
	        }else if(i18nType.equals("us")){
	            Locale locale = new Locale("en", "US"); 
	            (new CookieLocaleResolver()).setLocale (req, resp, locale);
	        }else{
	        	(new CookieLocaleResolver()).setLocale (req, resp, LocaleContextHolder.getLocale());
	        }
			redirect_url += Common.isLogin(req) ? "/index.shtml" : "/login.shtml";
			return redirect_url;
		} catch (Exception e) {
			logger.error("changeI18n is error " + e.getMessage());
			return "404";
		}
		
	}
}
