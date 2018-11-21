package com.scinan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import com.scinan.iot.ddeddo.dao.ResourcesMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.Resource;
import com.scinan.iot.service.SystemResourcesService;
import com.scinan.utils.Common;
import com.scinan.utils.MessageSourceUtil;

/**
 * 控制器基类
 * 
 * @project VtlDatacenter
 * @class com.scinan.controller.BaseController
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年01月01日
 * @description
 */
public class BaseController {
	
	@Autowired
	SystemResourcesService systemResourcesService;
	@Autowired
	private  ResourcesMapper vtlResourcesMapper;
	@Autowired
    private LocaleResolver localeResolver;
	
	
	/**
	 * 
	 * @return
	 */
	public List<Resource> fetchButtons() throws Exception{
		List<Resource> resourcesBeanList = new ArrayList<Resource>();
		// 资源ID
		String id = getPara("id");
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parent_id", id);
		List<Resource> rse = null;
		if(accountInfoBean.getRole_type()!=AccountInfo.ACCOUNT_TYPE_ADMIN){
			params.put("role_id", accountInfoBean.getRole_id());
			rse = vtlResourcesMapper.findUserRoleResources(params);
		}else{
			if(Long.parseLong(accountInfoBean.getRole_id()+"")==1 && accountInfoBean.getRole_type()==AccountInfo.ACCOUNT_TYPE_ADMIN){
				rse = vtlResourcesMapper.fetchSystemResourcesBySupperRole(params);   //Super 超级管理员
			}else{
				params.put("role_id", accountInfoBean.getRole_id());
				rse = vtlResourcesMapper.findUserRoleResources(params);
			}
		}
		Locale  locale = localeResolver.resolveLocale(request);
		String language = locale.getLanguage();
		if("en".equals(language)){
			for(Resource bean:rse){
				bean.setRes_name(getMessageValue(bean.getRes_key()));
				resourcesBeanList.add(bean);
			}
			return resourcesBeanList;
		}
		return rse;
	}
	
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getPara(String key){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		return request.getParameter(key);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String[] getParaValues(String key){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		return request.getParameterValues(key);
	}
	
	public Map<String, String> getParamsMap(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return Common.getRequestParameters(request);
	}
	
	
	
	/**
	 * 功能说明：获取国际化内容
	 * @param key
	 * @return
	 */
	public String getMessageValue(String key) { 
		 // 获取request
		 HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
	     Locale  locale = localeResolver.resolveLocale(request);
        return MessageSourceUtil.getApplicationContext().getMessage(key, null, locale);  
    } 
	
	
}