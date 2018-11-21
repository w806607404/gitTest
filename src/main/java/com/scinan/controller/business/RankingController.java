/**
 * @Description:
 * @Package: com.scinan.controller.business 
 * @author: 吴广   
 * @date: 2018年7月31日 下午4:03:13 
 */
package com.scinan.controller.business;

import java.math.BigDecimal;
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
import com.scinan.constants.Constants;
import com.scinan.controller.BaseController;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.s1000.dao.domain.UserInfo;
import com.scinan.iot.s6000.dao.DeviceTypeMapper;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.AreasService;
import com.scinan.iot.service.NotifySendService;
import com.scinan.iot.service.RoleService;
import com.scinan.iot.service.UserInfoService;
import com.scinan.utils.Common;
import com.scinan.utils.RedisUtil;
import com.scinan.utils.StringUtil;

/**
 * @Description: 排行榜控制类
 * @author: vinson
 * @date: 2018年8月15日 下午4:03:13 
 */
@Controller
@RequestMapping("/ranking/")
public class RankingController extends BaseController {
	
	final static Logger logger = Logger.getLogger(RankingController.class);
	
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
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 发货通知初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sell/list")
	public String noticeList(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/ranking/sell/list";
		
		model.addAttribute("systemResourcesBeans",fetchButtons());
		return jsp;
	}
	
	/**
	 * 销售排名分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="sellPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchNoticeSendByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = Common.getRequestParameters(request);
			PageBean<AccountInfo> pageBean = accountInfoService.getAllByCompany(params);
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchNoticeSendByPage  is error ",e);
		}
		return null;
	}
	

	/**
	 * 使用排行榜
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("use/list")
	public String useList(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/ranking/use/list";
		model.addAttribute("systemResourcesBeans",fetchButtons());
		return jsp;
	}
	
	/**
	 * 使用次数分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="usePage", method=RequestMethod.POST)
	@ResponseBody
	public Object usePage(Model model,HttpServletRequest request) throws Exception {
		try {
			AccountInfo bean = Common.getAccountInfo(request);
			PageBean<UserInfo>	pageBean = userInfoService.getUserListBySource(accountInfoService.fetchById(bean.getId()));
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchNoticeSendByPage  is error ",e);
		}
		return null;
	}
	
	/**
	 * 奖励
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("use/award")
	public String usegive(Model model,HttpServletRequest request,Long id , BigDecimal redEnvelopeAmount) throws Exception {
		String jsp = "/ranking/use/award";
		model.addAttribute("id", id);
		model.addAttribute("redEnvelopeAmount", redEnvelopeAmount);
		model.addAttribute("systemResourcesBeans",fetchButtons());
		return jsp;
	}
	
	
	/**
	 * 添加用户
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="use/addAward", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="排行榜管理",methods="添加奖励金额")
	public Object add(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String, String> params = getParamsMap();
			String redEnvelopeAmount = RedisUtil.get(params.get("id")+"_" + Constants.DEDO_REDENVELOPE_AMOUNT);
			int redEnvAmount = Integer.parseInt(params.get("redEnvelopeAmount"));
			if(!StringUtil.isNull(redEnvelopeAmount)){
				redEnvAmount += Integer.parseInt(redEnvelopeAmount);
			}
			RedisUtil.set(params.get("id")+"_" + Constants.DEDO_REDENVELOPE_AMOUNT, redEnvAmount+"");
			return DataCenterResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("add is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
		
	}
}
