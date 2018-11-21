/**
 * @Description:
 * @Package: com.scinan.controller.business 
 * @author: 吴广   
 * @date: 2018年7月31日 下午4:03:13 
 */
package com.scinan.controller.business;

import java.util.Date;
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
import com.scinan.iot.ddeddo.dao.domain.AccountSold;
import com.scinan.iot.ddeddo.dao.domain.NotifySend;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.s6000.dao.DeviceTypeMapper;
import com.scinan.iot.s6000.dao.domain.DeviceTypeBean;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.AreasService;
import com.scinan.iot.service.NotifySendService;
import com.scinan.iot.service.RoleService;
import com.scinan.iot.yunwa.dao.domain.Areas;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;

/**
 * @Description: 发货通知控制类
 * @author: 吴广
 * @date: 2018年7月31日 下午4:03:13 
 */
@Controller
@RequestMapping("/notifySend/")
public class NotifySendController extends BaseController {
	
	final static Logger logger = Logger.getLogger(NotifySendController.class);
	
	@Autowired
	private NotifySendService notifySendService; 
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AreasService areasService;
	@Autowired
	private DeviceTypeMapper deviceTypeMapper;
	
	
	/**
	 * 发货通知初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("notice/list")
	public String noticeList(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/notify/noticeSend/list";
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		model.addAttribute("user",accountInfoBean);
		model.addAttribute("systemResourcesBeans",fetchButtons());
		return jsp;
	}
	
	/**
	 * 发货通知记录分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchNoticeSendByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchNoticeSendByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = Common.getRequestParameters(request);
			PageBean<NotifySend> pageBean = notifySendService.fetchParamsByPage(params);
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchNoticeSendByPage  is error ",e);
		}
		return null;
	}
	
	
	/**
	 * 删除记录操作
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delNotifySend", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="删除发货通知记录")
	public Object delNotifySend(Model model,HttpServletRequest request,Long id) throws Exception {
		try {			
			DataCenterResult dataCenterResult = notifySendService.deleteNotifySend(id);				 
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("delNotifySend is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
		
	}
	
	
	/**
	 * 发货通知初始化页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addNotifySendInit")
	public String addNotifySendInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/notify/noticeSend/add";
		try {
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			AccountInfo user = accountInfoService.fetchById(accountInfoBean.getId());
			//获得当前用户角色对象
			Role role = roleService.fetchById(accountInfoBean.getRole_id());
			List<Areas> provinceList =areasService.fetchProvinceList();
			List<DeviceTypeBean>deviceList = deviceTypeMapper.getDeviceTypeAndComapnyId(accountInfoBean.getCompany_id());			
			model.addAttribute("deviceList",deviceList);
			model.addAttribute("provinceList", provinceList);
			model.addAttribute("role", role);
			model.addAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addNotifySendInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	
	/**
	 * 添加发货通知订单记录
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addNotifySend", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="添加发货订单记录")
	public Object add(Model model,HttpServletRequest request,NotifySend notifySend) throws Exception {
		try {
			logger.info("notifySendBean bean :" + notifySend.toString());
			//Map<String, String> params = getParamsMap();
			DataCenterResult dataCenterResult = notifySendService.addNotifySend(notifySend);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addNotifySend is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	@RequestMapping(value="fetchAccountInfoList", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="根据用户类型获得对应的用户集合")
	public List<AccountInfo> fetchAccountInfoList(Model model,HttpServletRequest request,Integer role_type) throws Exception {
		try{
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			String company_id = accountInfoBean.getCompany_id();
			List<Integer> list = roleService.fetchByRoleType(role_type, company_id);
			Conds conds = new Conds();
			Object[] strs = list.toArray();
			conds.in("role_id", strs);
			conds.equal("status", 1);
			if (role_type == 3) { //找经销商列表
				conds.equal("parent_user_id", accountInfoBean.getId());
			}			
			List<AccountInfo> accountInfos = accountInfoService.fetchByRoleIdList(conds);
			return accountInfos;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchAccountInfoList is error " + e.getMessage());
			return null;
		}
		
		
	}
	
	/**
	 * 修改发货通知页面初始化
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifyNotifySendInit")
	public String modifyNotifySendInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/notify/noticeSend/modify";
		try {
			String id = request.getParameter("id");	
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			NotifySend notifySend = notifySendService.fetchById(Long.parseLong(id));
			List<DeviceTypeBean>deviceList = deviceTypeMapper.getDeviceTypeAndComapnyId(accountInfoBean.getCompany_id());			
			model.addAttribute("deviceList",deviceList);
			model.addAttribute("notifySend", notifySend);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("modifyNotifySendInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	
	/**
	 * 修改发货通知订单记录
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateNotifySend", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="修改发货订单记录")
	public Object update(Model model,HttpServletRequest request,NotifySend notifySend) throws Exception {
		try {
			logger.info("notifySendBean bean :" + notifySend.toString());
			//Map<String, String> params = getParamsMap();
			DataCenterResult dataCenterResult = notifySendService.updateNotifySend(notifySend);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateNotifySend is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	/**
	 * 修改发货通知状态为已确认
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateStatus", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="修改发货通知状态")
	public Object updateStatus(Model model,HttpServletRequest request,Long id) throws Exception {
		try {
			AccountInfo accountInfo = Common.getAccountInfo(request);
			NotifySend notifySend = notifySendService.fetchById(id);
			logger.info("notifySendBean bean :" + notifySend.toString());
			//只有状态等于3为待确认的记录,以及接收通知的人,才能点击已确认，状态为0是已确认
			if (notifySend.getStatus() == 3 && accountInfo.getId().equals(notifySend.getReceive_userId())) {
				notifySend.setStatus(0);
			}else if (notifySend.getStatus() == 1 && accountInfo.getId().equals(notifySend.getSend_userId())) {
				notifySend.setStatus(2);
			}else {
				return DataCenterResult.build(-101);
			}
			notifySend.setReceipt_time(new Date());
			DataCenterResult dataCenterResult = notifySendService.updateNotifySend(notifySend);
			return dataCenterResult;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateStatus is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	/**
	 * 修改发货通知状态为缺货
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="stockout", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="发货通知状态更新为缺货")
	public Object stockout(Model model,HttpServletRequest request,Long id) throws Exception {
		try {
			AccountInfo accountInfo = Common.getAccountInfo(request);
			NotifySend notifySend = notifySendService.fetchById(id);
			logger.info("notifySendBean bean :" + notifySend.toString());
			//只有状态为待确认或待发货的记录,以及接收通知的人,才能点击缺货，状态为5是缺货
			if (notifySend.getStatus() == 3 && accountInfo.getId().equals(notifySend.getReceive_userId())) {
				notifySend.setStatus(5);
				notifySend.setReceipt_time(new Date());
				DataCenterResult dataCenterResult = notifySendService.updateNotifySend(notifySend);
				return dataCenterResult;
			}else {
				return DataCenterResult.build(-101);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("stockout is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	/**
	 * 设置物流信息页面初始化
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setShippingInit")
	public String setShippingInit(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/notify/noticeSend/setShipping";
		try {
			String id = request.getParameter("id");	
			NotifySend notifySend = notifySendService.fetchById(Long.parseLong(id));
			model.addAttribute("notifySend", notifySend);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("setShippingInit is error " + e.getMessage());
		}
		return jsp;
	}
	
	/**
	 * 设置物流信息,且状态更新为已发货
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="setShipping", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="设置物流信息")
	public Object setShipping(Model model,HttpServletRequest request,NotifySend notifySend) throws Exception {
		try {
			AccountInfo accountInfo = Common.getAccountInfo(request);
			logger.info("notifySendBean bean :" + notifySend.toString());
			//只有状态等于0为待发货的记录,以及接收通知的人,才能点击完成，状态为1是已发货
			if (notifySend.getStatus() == 0 && accountInfo.getId().equals(notifySend.getReceive_userId())) {
				notifySend.setStatus(1);
				notifySend.setReceipt_time(new Date());
				DataCenterResult dataCenterResult = notifySendService.updateNotifySendStatus(notifySend);
				return dataCenterResult;
			}else {
				return DataCenterResult.build(-101);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("setShipping is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	/**
	 * 根据省市区ID验证该地区有无代理商
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchAccountInfoByAgent", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="验证地区有无代理商")
	public Object fetchAccountInfoByAgent(Model model,HttpServletRequest request,AccountInfo accountInfo) throws Exception {
		try {			
			logger.info("accountInfoBean bean :" + accountInfo.toString());
			Conds conds = new Conds();
			conds.equal("province_id", accountInfo.getProvince_id());
			conds.equal("city_id", accountInfo.getCity_id());
			conds.equal("district_id", accountInfo.getDistrict_id());
			conds.equal("role_id", 3);
			conds.equal("status", 1);
			AccountInfo user = accountInfoService.fetchByConds(conds);
			if (user!=null) {
				return user;
			}else {
				return 1;
			}					
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchAccountInfoByAgent is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
}
