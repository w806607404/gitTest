package com.scinan.controller.system;

import java.util.HashMap;
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
import com.scinan.bean.PageBean;
import com.scinan.controller.BaseController;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.ddeddo.dao.domain.UserBasicInfo;
import com.scinan.iot.s1000.dao.domain.UserInfo;
import com.scinan.iot.service.AccountBackService;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.AreasService;
import com.scinan.iot.service.BillCloseDetailService;
import com.scinan.iot.service.CommonService;
import com.scinan.iot.service.NotifySendService;
import com.scinan.iot.service.PurchaseService;
import com.scinan.iot.service.RoleService;
import com.scinan.iot.service.UserBasicInfoService;
import com.scinan.iot.service.UserInfoService;
import com.scinan.iot.yunwa.dao.domain.Areas;
import com.scinan.utils.Common;
import com.scinan.utils.JsonUtils;
import com.scinan.utils.StringUtil;
/**
 * 用户相关控制类
 * 
 * @project ddeddo
 * @class com.scinan.controller.system.LoginController
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月10日
 * @description
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController{
	
	final static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserBasicInfoService userBasicInfoService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private AreasService areasService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private AccountBackService accountBackService;
	@Autowired
	private NotifySendService notifySendService;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private BillCloseDetailService billCloseDetailService;
	
	
	
	
	
	/**
	 * 用户列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/system/user/list";
		model.addAttribute("systemResourcesBeans",fetchButtons());
		List<Areas> province_areas = areasService.fetchAreasByParent_id("0");
		model.addAttribute("provinces",province_areas);
		return jsp;
	}
	
	/**
	 * 用户信息分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchByPage(Model model,HttpServletRequest request) throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		System.out.println(accountInfoBean.toString());
		try {
			Map<String,String> params = getParamsMap();
			PageBean<AccountInfo> pageBean = null;
			if(accountInfoBean.getRole_type()<=AccountInfo.ACCOUNT_TYPE_COMMON){//超级管理员和厂商查看所有账号
				pageBean = accountInfoService.fetchParamsByPage(params);
			}else{
				params.put("parent_user_id", String.valueOf(accountInfoBean.getParent_user_id()));
				pageBean = accountInfoService.fetchParamsByPage(params);
			}
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchByPage is error ",e);
		}
		return null;
	}
	
	/**
	 * 用户信息分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fetchById")
	public String fetchById(Model model,HttpServletRequest request, Long id) throws Exception {
		String jsp = "/system/user/info";
		try {
			AccountInfo bean = accountInfoService.fetchById(id);
			accountInfoService.setIndexValue(bean);
			model.addAttribute("info", bean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchByPage is error ",e);
		}
		return jsp;
	}
	
	
	/**
	 * 用户审核列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("auditList")
	public String auditList(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/system/user/auditList";
		model.addAttribute("systemResourcesBeans",fetchButtons());
		List<Areas> province_areas = areasService.fetchAreasByParent_id("0");
		model.addAttribute("provinces",province_areas);
		return jsp;
	}
	
	/**
	 * 审核用户信息分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchAuditPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchAuditPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			params.put("audit", "true");
			return accountInfoService.fetchParamsByPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchByPage is error ",e);
		}
		return null;
	}
	
	/**
	 * 添加用户初始化页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addUserInit")
	public String addRoleInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/system/user/add";
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			//角色类型列表
			model.addAttribute("provinces", areasService.fetchAreasByParent_id("0"));
			if(accountInfoBean.getRole_type()==AccountInfo.ACCOUNT_TYPE_ADMIN){
			   model.addAttribute("vtlRoleList", roleService.fetchRoleByList(null));
			}else{
				//一级经销商与一二经销商
				model.addAttribute("vtlRoleList", roleService.fetchRoleByList(String.valueOf(accountInfoBean.getParent_role_id())));
				model.addAttribute("roleType",accountInfoBean.getRole_type().intValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addUserInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	//根据父节点获取区域
	@RequestMapping(value="fetchAreasByParentId" , method=RequestMethod.POST)
	@ResponseBody
	public Object areas(String parent_id){
		return areasService.fetchAreasByParent_id(parent_id);
	}
	
	
	/**
	 * 添加用户
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="用户管理",methods="添加用户")
	public Object add(Model model,HttpServletRequest request,AccountInfo accountInfoBean) throws Exception {
		try {
			logger.info("accountInfoBean bean :" + accountInfoBean.toString());
			Map<String, String> params = getParamsMap();
			DataCenterResult dataCenterResult = accountInfoService.addAccount(request,accountInfoBean,params);	
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("add is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
		
	}

	
	
	
	/**
	 * 重置密码
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="resetAccount", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="用户管理",methods="重置APP端账号")
	public Object resetPwd(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String, String> params = getParamsMap();
			DataCenterResult dataCenterResult = accountInfoService.resetAccount(params);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("resetPwd is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	/**
	 * 删除账号操作
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="deleteAccount", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="用户管理",methods="删除账号")
	public Object deleteAccount(Model model,HttpServletRequest request,Long id) throws Exception {
		try {
			DataCenterResult dataCenterResult = accountInfoService.deleteAccount(request,id);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteAccount is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
		
	}
	
	
	/**
	 * 修改用户信息初始化页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifyUserInit")
	public String modifyUserInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/system/user/modify";
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			String id = request.getParameter("id");
			
			AccountInfo AccountInfo = accountInfoService.fetchVtlUserInfo(Long.parseLong(id));
			if(null!=AccountInfo){
			    model.addAttribute("accountInfo", AccountInfo); 
			      if(AccountInfo.getRole_type().equals(accountInfoBean.getRole_type())){
			    	model.addAttribute("display", "0"); 
			    }else{
			    	model.addAttribute("display", "1"); 
			    }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("modifyUserInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	/**
	 * 状态设置页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setStatus")
	public String setStatus(Model model,HttpServletRequest request) throws Exception {
		Map<String, String> params = getParamsMap();
		String jsp = "/system/user/setStatus";
		String userId = String.valueOf(params.get("userId"));
		AccountInfo accountInfo = accountInfoService.fetchById(new Long(userId));
		model.addAttribute("accountInfo", accountInfo);
		if(Integer.parseInt(params.get("type")) == 2){//审核
			jsp = "/system/user/audit";
		}else if(Integer.parseInt(params.get("type")) == 3){//重置app端账号
			UserInfo userInfo = userInfoService.fetchById(accountInfo.getUser_info_id());
			model.addAttribute("userInfo", userInfo);
			jsp = "/system/user/resetAccount";
		}
		return jsp;
	}
	
	
	
	/**
	 * 更新账号操作
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="用户管理",methods="修改用户信息")
	public Object update(Model model,HttpServletRequest request,AccountInfo accountInfoBean) throws Exception {
		try{
			Map<String, String> params = getParamsMap();
			DataCenterResult dataCenterResult = accountInfoService.updateAccount(request,accountInfoBean,params);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("update is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	/**
	 * 修改账号密码操作changePwd
	 * 
	 * @param model
	 * @param request
	 * @param pwd
	 * @param pwd1
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="changePwd", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="用户管理",methods="修改密码")
	public Object changePwd(Model model,HttpServletRequest request,String pwd,String pwd1) throws Exception {
		try{
			DataCenterResult dataCenterResult = accountInfoService.changePwd(request,pwd,pwd1);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("changePwd is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	
	/**
	 * 厂商基础信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("companyInfo")
	public String companyInfo(Model model,HttpServletRequest request) throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		//String infoId = RedisUtil.get(accountInfoBean.getRole_id()+"_"+"BASIC_INFORMATION_KEY");
		UserBasicInfo userBasicInfo = userBasicInfoService.fetchById(accountInfoBean.getId(),accountInfoBean.getCompany_id());
		
		if(null!=userBasicInfo){
			model.addAttribute("userBasicInfo",userBasicInfo);
		}
		if(accountInfoBean.getRole_type().intValue()>1){
		  model.addAttribute("my_current_money",StringUtil.isValidateStr(accountInfoBean.getMy_current_money()));
		  model.addAttribute("current_ratio_str",StringUtil.isValidateStr(accountInfoBean.getCurrent_ratio()+""));
		}
		return "/system/companyinfo/list";
	}
	
	
	/**
	 * 修改基础信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="updateUserBasicInfo",method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="基础信息管理",methods="修改基础信息")
	public Object updateUserBasicInfo(Model model,HttpServletRequest request,UserBasicInfo userBasicInfo) throws Exception {
		try {
			logger.info("update userBasicInfo bean :" + userBasicInfo.toString());
			//Long id = userBasicInfo.getId();
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			//String infoId = RedisUtil.get(accountInfoBean.getRole_id()+"_"+"BASIC_INFORMATION_KEY");
			UserBasicInfo userBasicBean = userBasicInfoService.fetchById(accountInfoBean.getId(),accountInfoBean.getCompany_id());
			
			if (userBasicBean == null) {
				DataCenterResult dataCenterResult = userBasicInfoService.addUserBasicInfo(model,userBasicInfo,request);
				return dataCenterResult;
			} else {
				DataCenterResult dataCenterResult = userBasicInfoService.updateUserBasicInfo(model,userBasicInfo,request);
				return dataCenterResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("update is error , params is " + userBasicInfo.toString() + "  error message " + e.getMessage() );
			return DataCenterResult.build(500);
		}
	}
	
	
	@RequestMapping(value = "getRoleType", method= RequestMethod.POST)
    @ResponseBody
    public Object getDeviceId(Model model, HttpServletRequest request) {
        Map<String, String> params = getParamsMap();
        logger.info("getRoleType :" + params.toString());
        Map<String, Object> map = new HashMap<String,Object>();
        try {
        	 String role_id = params.get("role_id");
        	 if(!StringUtil.isNull(role_id)){
        		 Role roleBean =  roleService.fetchById(Long.parseLong(role_id));
        		 if(null!=roleBean){
        			 map.put("role_type", roleBean.getRole_type());
        		 }
        	 }
        	String json = JsonUtils.objectToJson(map);  
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getDeviceId is error " + e.getMessage());
            return DataCenterResult.build(500);
        }
    }
	
	
	/**
	 * 撤销记录初始化页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("repealInit")
	public String repealInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/system/user/repeal";
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			model.addAttribute("provinces", areasService.fetchAreasByParent_id("0"));
			model.addAttribute("accountInfo", accountInfoService.fetchById(accountInfoBean.getId()));
			model.addAttribute("systemResourcesBeans",fetchButtons());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addUserInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	/**
	 * 撤销记录分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchRepealPage", method=RequestMethod.POST)
	@ResponseBody
	public Object repealPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			return accountInfoService.fetchRepealByPage(params,request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchByPage is error ",e);
		}
		return null;
	}
	
	/**
	 * 一键迁移初始化页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("oneKeyMove")
	public String oneKeyMove(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/system/user/transfer";
		try {
			List<AccountInfo> list = accountInfoService.fetchUserByParentId(new Long(2));
			model.addAttribute("list",list); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("modifyUserInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	/**
	 * 一键迁移进行
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="transfer",method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="用户管理",methods="一键迁移")
	public Object transfer(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			String pUserId = String.valueOf(params.get("p_user_id"));//被迁移
			String nUserId = String.valueOf(params.get("n_user_id"));//迁移到
			AccountInfo pUser = accountInfoService.fetchById(new Long(pUserId));
			AccountInfo nUser = accountInfoService.fetchById(new Long(nUserId));
			if(verify(new Long(pUserId)) != null){
				return verify(new Long(pUserId));
			}
			if(verify(new Long(nUserId)) != null){
				return verify(new Long(nUserId));
			}
			
			List<AccountInfo> pDealerList = accountInfoService.fetchLowerDealerByUserInfo(Integer.parseInt(pUserId), pUser.getCompany_id());
			if(null!=pDealerList){
				for(AccountInfo bean:pDealerList){
					if(verify(bean.getId()) != null){
						return verify(bean.getId());
					}
				}
				for(AccountInfo bean:pDealerList){
					AccountInfo nAccountInfo = new AccountInfo();
					nAccountInfo.setId(bean.getId());
					nAccountInfo.setParent_user_id(Long.parseLong(nUserId));
					accountInfoService.updateAccountInfo(nAccountInfo);
				}
			}
			nUser.setRole_id(pUser.getRole_id());
			nUser.setParent_user_id(pUser.getParent_user_id());
			accountInfoService.update(nUser);//更新目标信息
			return DataCenterResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return DataCenterResult.build(500);
		}
	}
	
	private DataCenterResult verify(Long user_id){
		if(accountBackService.countByTransfer(user_id) > 0){
			return DataCenterResult.build(101);
		}
		
		if(notifySendService.countByTransfer(user_id) > 0){
			return DataCenterResult.build(102);
		}
		
		if(purchaseService.countByTransfer(user_id) > 0){
			return DataCenterResult.build(103);
		}
		
		if(billCloseDetailService.countByTransfer(user_id) > 0){
			return DataCenterResult.build(104);
		}
			return null;
	}
	//根据父id获取子列表
	@RequestMapping(value="getSonList" , method=RequestMethod.POST)
	@ResponseBody
	public Object sonList(Long parent_id){
		return accountInfoService.fetchUserByParentId(parent_id);
	}
	
	/**
	 * 修改区域设置页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifyArea")
	public String modifyArea(Model model,HttpServletRequest request,Long id) throws Exception {
		String jsp = "/system/user/modifyArea";
		AccountInfo accountInfo = accountInfoService.fetchById(id);
		model.addAttribute("accountInfo", accountInfo);
		model.addAttribute("provinces", areasService.fetchAreasByParent_id("0"));
		return jsp;
}
	
	
	/**
	 * 更改区域
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="doModifyArea",method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="用户管理",methods="更改区域")
	public Object doModifyArea(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			return accountInfoService.modifyArea(params);
		} catch (Exception e) {
			e.printStackTrace();
			return DataCenterResult.build(500);
		}
	}
	
	
	
}
