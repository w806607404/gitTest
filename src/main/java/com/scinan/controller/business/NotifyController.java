package com.scinan.controller.business;

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
import com.scinan.iot.s1000.dao.domain.ArticleInfoBean;
import com.scinan.iot.service.AccountRatioUpdateService;
import com.scinan.iot.service.ArticleInfoService;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;
/**
 * 通知管理控制类
 * 
 * @project ddeddo
 * @class com.scinan.controller.business.NotifyController
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月11日
 * @description
 */
@Controller
@RequestMapping("/notify/")
public class NotifyController extends BaseController{
	
	final static Logger logger = Logger.getLogger(NotifyController.class);
	
	@Autowired
	private AccountRatioUpdateService accountRatioUpdateService;
	@Autowired
	private ArticleInfoService articleInfoService;
	
	/**
	 * 通知公告初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("notice/list")
	public String noticeList(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/notify/notice/list";
		model.addAttribute("systemResourcesBeans",fetchButtons());
		return jsp;
	}
	
	/**
	 * 通知公告分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchNoticeByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object noticeFetchByPage(Model model,HttpServletRequest request,String type) throws Exception {
		try {
			Map<String,String> params = Common.getRequestParameters(request);
			PageBean<ArticleInfoBean> pageBean = articleInfoService.fetchParamsByPage(request,params,type);
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ArticleManageController's fetchByPage is error ",e);
		}
		return null;
	}
	
	
	@RequestMapping("notice/addUI")
	public String addUI(Model model,HttpServletRequest request)throws Exception{
		try {
			String type = "add";
			articleInfoService.setModel(request,model,type);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("setModel is error " + e.getMessage());
		}
		return "/notify/notice/add";
	}
	@RequestMapping("notice/editUI")
	public String editUI(Model model,HttpServletRequest request)throws Exception{
		try {
			String type = "update";
			articleInfoService.setModel(request,model,type);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("setModel is error " + e.getMessage());
		}
		return "/notify/notice/update";
	}

	/**
	 * 添加
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="notice/add", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="公告添加")
	public Object add(Model model,HttpServletRequest request,ArticleInfoBean articleInfoBean) throws Exception {
		logger.info("articleInfo Bean :" + articleInfoBean.toString());
		try {
			DataCenterResult dataCenterResult = articleInfoService.addArticle(articleInfoBean,request);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("add is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	/**
	 * 修改
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="notice/update", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="公告修改")
	public Object update(Model model,HttpServletRequest request,ArticleInfoBean articleInfoBean) throws Exception {
		try{	
			logger.info("articleInfo Bean  :" + articleInfoBean.toString());
			DataCenterResult dataCenterResult = articleInfoService.updateAtticle(articleInfoBean,request);
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("update is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	/**
	 * 公告删除
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="notice/delete", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="公告删除")
	public Object delete(HttpServletRequest request,String ids) throws Exception {
		try{
			logger.info("notice ids :" + ids);
			DataCenterResult dataCenterResult = articleInfoService.deleteArticles(Common.strSplitToList(ids, ","));
			return dataCenterResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("delete is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	/**
	 * 分成比例变更初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ratioUpdate/list")
	public String list(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/notify/ratioUpdate/list";
		model.addAttribute("systemResourcesBeans",fetchButtons());
		return jsp;
	}
	
	/**
	 * 产品信息以及分成比例分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchRatioUpdateByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			return accountRatioUpdateService.fetchParamsByPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchByPage is error ",e);
		}
		return null;
	}
	
	
	/**
	 * 删除操作
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delRatioUpdate", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="通知管理",methods="比例变更删除")
	public Object auditAccount(Model model,HttpServletRequest request,Long id) throws Exception {
		try {
			 if(accountRatioUpdateService.delete(id)){
				 return DataCenterResult.ok();
			 }
			return DataCenterResult.build(-101);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteAccount is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
	
	
	/**
	 * 添加通知通告最大数量限制
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="notice/lockCount", method=RequestMethod.POST)
	@ResponseBody
	public Object lockCount(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = Common.getRequestParameters(request);
			Integer type = Integer.parseInt(params.get("type"));
			Long app_key = new Long(params.get("app_key"));
			Conds cond = new Conds();
			cond.equal("app_key", app_key);
			cond.equal("article_type",type);
			int count = articleInfoService.count(cond);
			if(type == 4 && count >= 1){
				return DataCenterResult.build(-101);
			}else if(type == 3 && count >= 3){
				return DataCenterResult.build(-102);
			}
			return DataCenterResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteAccount is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
	}
}
