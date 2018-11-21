package com.scinan.controller.system;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import com.itextpdf.text.log.SysoLogger;
import com.scinan.base.cache.impl.RedisSimpleCacheServiceImpl;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.UserBasicInfo;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.DeviceInfoService;
import com.scinan.iot.service.SystemResourcesService;
import com.scinan.iot.service.UserBasicInfoService;
import com.scinan.utils.Common;
import com.scinan.utils.StringUtil;

/**
 * 首页相关操作控制类
 * @project datacenter
 * @class com.scinan.controller.system.IndexController
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年5月22日
 * @description
 */
@Controller
public class IndexController{
	
	final static Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	RedisSimpleCacheServiceImpl redisSimpleService;
	@Autowired
    private LocaleResolver localeResolver;
	@Autowired
	private DeviceInfoService deviceInfoService;
	@Autowired
	SystemResourcesService systemResourcesService;
	@Autowired
	UserBasicInfoService userBasicInfoService;
	@Autowired
	private AccountInfoService accountInfoService;
	
	/**
	 * 进入首页
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			Locale  locale = localeResolver.resolveLocale(request);
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			if(accountInfoBean == null){
				accountInfoService.logout(request,response);//为空执行下退出操作
			}
			String language = locale.getLanguage();
			systemResourcesService.setIndexModel(model,request,locale);
			model.addAttribute("language", language);
			//初始化一级代理商基本信息
			UserBasicInfo userBasicInfo = userBasicInfoService.fetchById(accountInfoBean.getId(),accountInfoBean.getCompany_id());
			if(null!=userBasicInfo){
				model.addAttribute("companyInfoBean",userBasicInfo);
			}else{
				UserBasicInfo _userBasicInfo = getuserBasicInfo(accountInfoBean,userBasicInfoService);
				model.addAttribute("companyInfoBean",_userBasicInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("setModel is error " + e.getMessage());
		}
		return "index";
	}
	
	/**
	 * 进入首页	
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/indexWelcome", method = RequestMethod.GET)
	public String indexWelcome(HttpServletRequest request, HttpServletResponse response, Model model) {
		Locale  locale = localeResolver.resolveLocale(request);
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		String language = locale.getLanguage();
		model.addAttribute("language", language);
		//初始化一级代理商基本信息
		UserBasicInfo userBasicInfo = userBasicInfoService.fetchById(accountInfoBean.getId(),accountInfoBean.getCompany_id());
		if(null!=userBasicInfo){
			model.addAttribute("companyInfoBean",userBasicInfo);
		}else{
			UserBasicInfo _userBasicInfo = getuserBasicInfo(accountInfoBean,userBasicInfoService);
			model.addAttribute("companyInfoBean",_userBasicInfo);
		}
		/*UserBasicInfo userBasicInfo = userBasicInfoService.fetchById(accountInfoBean.getId(),accountInfoBean.getCompany_id());
		if(null!=userBasicInfo){
			model.addAttribute("companyInfoBean",userBasicInfo);
		}*/
		deviceInfoService.setIndexValue(request,model);
		
		
		return "index_v1";
	}
	
	
	/**
	 * 功能说明：获取上一级的用户信息
	 * @param accountInfoBean
	 * @param userBasicInfoService
	 * @return UserBasicInfo
	 */
	private static UserBasicInfo getuserBasicInfo(AccountInfo accountInfoBean,UserBasicInfoService userBasicInfoService){
		String userPath = accountInfoBean.getParent_user_path();
		try{
			UserBasicInfo userBasicInfo = null;
			if(!StringUtil.isNull(userPath)){
				String[] userids = userPath.split("\\/");
				if(null!=userids && userids.length>0){
					int _i = userids.length;
					for(int i=_i;i>=1;i--){
						if(!StringUtil.isNull(userids[i-1])){
						     userBasicInfo = userBasicInfoService.fetchById(Long.parseLong(userids[i-1]),accountInfoBean.getCompany_id());
						     if(null!=userBasicInfo){
						    	 return userBasicInfo;
						     }
						}
					}
				}
			}
		}catch(Exception ex){
			logger.error("IndexController getuserBasicInfo is error : "+ex.getMessage());
		}
		return null;
	}  
	
}
