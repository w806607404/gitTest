/**
 * @Description:
 * @Package: com.scinan.controller.business 
 * @author: 吴广   
 * @date: 2018年7月31日 下午4:03:13 
 */
package com.scinan.controller.business;

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

import com.itextpdf.text.log.SysoLogger;
import com.scinan.annotation.SystemLog;
import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.controller.BaseController;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.s8000temp.dao.domain.SuggestionData;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.SuggestionService;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;
import com.scinan.utils.StringUtil;

/**
 * @Description: 意见反馈控制类
 * @author: vinson
 * @date: 2018年8月21日 上午11:03:13 
 */
@Controller
@RequestMapping("/suggestion/")
public class SuggestionController extends BaseController {
	
	final static Logger logger = Logger.getLogger(SuggestionController.class);
	
	@Autowired
	private SuggestionService suggestionService; 
	@Autowired
	private AccountInfoService accountInfoService; 
	
	/**
	 * 意见反馈初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String noticeList(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/suggestion/list";
		model.addAttribute("systemResourcesBeans",fetchButtons());
		return jsp;
	}
	
	/**
	 * 意见反馈分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="suggestionPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchNoticeSendByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = Common.getRequestParameters(request);
			AccountInfo ai = Common.getAccountInfo(request);
			Conds c = new Conds();
			if(params.get("type_id") != null){
				c.equal("type_id", params.get("type_id"));
			}
			c.equal("company_id", accountInfoService.fetchById(ai.getId()).getCompany_id());
			List<SuggestionData> list = suggestionService.fetchByPage(c, null, Integer.parseInt(params.get("offset")), Integer.parseInt(params.get("limit")));
			if(list != null && list.size()>0){
				for(SuggestionData bean : list){
					if(StringUtil.isNull(bean.getMobile())){
						bean.setContact(bean.getEmail());	
					}
					if(StringUtil.isNull(bean.getEmail())){
						bean.setContact(bean.getMobile());	
					}
				}
			}
			return new PageBean<SuggestionData>(suggestionService.count(c),list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchNoticeSendByPage  is error ",e);
		}
		return null;
	}
	
	/**
	 * 删除反馈操作
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="deleteSuggestion", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(module="意见反馈",methods="删除反馈记录")
	public Object deleteAccount(Model model,HttpServletRequest request) throws Exception {
		try {
			System.out.println(request.getParameter("id"));
			if(suggestionService.delete(new Long(request.getParameter("id")))){
				return DataCenterResult.ok();
			}else{
				return DataCenterResult.build(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteAccount is error " + e.getMessage());
			return DataCenterResult.build(500);
		}
		
	}
	
}
