package com.scinan.controller.business;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import com.scinan.base.cache.impl.RedisCacheServiceImpl;
import com.scinan.bean.DataCenterResult;
import com.scinan.controller.BaseController;
import com.scinan.controller.business.util.TransferInfoThread;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.AccountRatioService;
import com.scinan.iot.service.DeviceDistributionService;
import com.scinan.utils.Common;


/**
 * 一键迁移
 * @project ZhengshangDataCenter
 * @class com.scinan.controller.business.TransferBusinessController
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年09月18日
 * @description
 */
@Controller
@RequestMapping("/transfer/")
public class TransferBusinessController extends BaseController{
	
	final static Logger logger = Logger.getLogger(TransferBusinessController.class);
	
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private DeviceDistributionService deviceDistributionService;
	@Autowired
	private AccountRatioService accountRatioService;
	@Autowired
    private LocaleResolver localeResolver;  
	@Autowired
	private RedisCacheServiceImpl redisCacheServiceImpl;
	
	// 线程池
	ExecutorService executorService = Executors.newCachedThreadPool();

	
	
	
	/**
	 * 一键迁移初始化页面
	 * @param request
	 * @param redirect
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpServletResponse response,String redirect, Model model) {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		//不是指定的用户,不允许执行设备导入操作
		try{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("company_id", accountInfoBean.getCompany_id());
			params.put("roleType", accountInfoBean.getRole_type().intValue());
			params.put("parent_user_id", accountInfoBean.getParent_user_id()); //只允许迁移当前经销商的下级经销商
			deviceDistributionService.setModuleSubUser(model, params);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("device_of_import is error " + ex.getMessage());
		}
		return "/transfer/transfer";
	}
	
	
	
	/**
	 * <一句话功能简述>用户权限迁移操作
	 * <功能详细描述>
	 * @param file
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "transfer")
	@ResponseBody
	public Object transfer(HttpServletRequest request,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		String p_user_id = request.getParameter("p_user_id");   //要迁移用户
		String n_user_id = request.getParameter("n_user_id");   //当前用户
		
		try {
			if(accountInfoBean.getRole_type().intValue()<=0){   //如果不是厂商或经销商，不允许执行一键迁移操作
				return "-8";
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("p_user_id", p_user_id);
			params.put("n_user_id", n_user_id);
			
	        //由于执行的数据比较大，采用异步处理的方式
			executorService.execute(new TransferInfoThread(accountInfoService,params,accountRatioService,accountInfoBean));
		} catch (Exception e) {
			logger.error("TransferBusinessController is transfer operation DataBase " + e.getMessage());
			e.printStackTrace();
		}
		return DataCenterResult.build(200);
	}
	
	
	
	
	
	
	
	
	
	
	
}
