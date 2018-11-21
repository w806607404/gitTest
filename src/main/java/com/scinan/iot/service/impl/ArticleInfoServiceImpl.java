package com.scinan.iot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.controller.UploadController;
import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.s1000.dao.AppInfoMapper;
import com.scinan.iot.s1000.dao.ArticleInfoMapper;
import com.scinan.iot.s1000.dao.domain.AppInfoBean;
import com.scinan.iot.s1000.dao.domain.ArticleInfoBean;
import com.scinan.iot.service.ArticleInfoService;
import com.scinan.iot.service.MsgPushService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;

/**
 * 文章管理业务实现类
 * 
 * @project datacenter
 * @class com.scinan.iot.service.impl.ArticleInfoServiceImpl
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年7月1日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("articleInfoService")
public class ArticleInfoServiceImpl extends GenericServiceImpl<ArticleInfoBean, Long> implements ArticleInfoService {
	static Logger logger = Logger.getLogger(ArticleInfoServiceImpl.class);
	@Autowired
	private ArticleInfoMapper articleInfoMapper;
	@Autowired
	private AppInfoMapper appInfoMapper;
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	@Autowired
	private MsgPushService msgPushService;
	
	@Override
	protected GenericMapper<ArticleInfoBean, Long> getGenericMapper() {
		return articleInfoMapper;
	}
	
	public PageBean<ArticleInfoBean> fetchParamsByPage(HttpServletRequest request,Map<String, String> params,String type) throws Exception {
		AccountInfo accountInfo = Common.getAccountInfo(request);
		Map<String, Object> map = new HashMap<String, Object>();
		Conds conds = new Conds();
		if(StringUtils.isNotEmpty(params.get("search_state"))){
		}
		
		if(StringUtils.isNotEmpty(params.get("article_type"))){
			conds.equal("article_type", params.get("article_type"));
		}
		
		//List<RoleCompanyBean> roleCompanyBeans = accountInfoBean.getRoleCompanyBeans();
		//RoleCompanyBean roleCompanyBean = roleCompanyBeans.get(0);
		//String company_id = roleCompanyBean.getCompany_id();
		conds.equal("company_id", accountInfoMapper.fetchById(accountInfo.getId()).getCompany_id());
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		List<ArticleInfoBean> articleInfoBeans = articleInfoMapper.fetchByPage(map);
		if (null != articleInfoBeans && articleInfoBeans.size() > 0) {
			for (int i = 0; i < articleInfoBeans.size(); i++) {
				ArticleInfoBean articleInfoBean = articleInfoBeans.get(i);
				Long app_key = articleInfoBean.getApp_key();
				String app_name = appInfoMapper.fetchByAppKey(app_key);
				articleInfoBean.setApp_name(app_name);
			}
		}
		Integer count = articleInfoMapper.count(map);
		return new PageBean<ArticleInfoBean>(count,articleInfoBeans);
		
	}

	public DataCenterResult addArticle(ArticleInfoBean articleInfoBean,
			HttpServletRequest request) {
		String detailUrl = UploadController.doProcessStaticHtml(request);
		articleInfoBean.setDetail_url(detailUrl);
		articleInfoBean.setState(2);
		articleInfoMapper.save(articleInfoBean);
		if(articleInfoBean.getArticle_type() == 6){
			msgPushService.msgPush(0, null);
		}
		return DataCenterResult.ok();
	}

	public DataCenterResult updateAtticle(ArticleInfoBean articleInfoBean,HttpServletRequest request) {
		Integer state = articleInfoBean.getState();
		if (state == 3) {
			state = 1;
			articleInfoBean.setState(state);
		}
		String detailUrl = UploadController.doProcessStaticHtml(request);
		articleInfoBean.setDetail_url(detailUrl);
		articleInfoMapper.update(articleInfoBean);
		return DataCenterResult.ok();
	}

	public DataCenterResult deleteArticles(List<String> ids) {
		articleInfoMapper.deleteId(ids);
		return DataCenterResult.ok();
	}

	public DataCenterResult checkArticles(ArticleInfoBean articleInfoBean) {
		articleInfoMapper.check(articleInfoBean);
		return DataCenterResult.ok();
	}

	@Override
	public void setModel(HttpServletRequest request, Model model, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		Conds conds = new Conds();
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		String company_id = accountInfoMapper.fetchById(accountInfoBean.getId()).getCompany_id();
		conds.equal("company_id", company_id);
		conds.like("app_name", "技工版");
		map.put("conds", conds);
		List<AppInfoBean> appInfoBeans = appInfoMapper.fetchByCompanyId(map);
		if (type.equals("update") || type.equals("check")) {
			Map<String, String> map1 = Common.getRequestParameters(request);
			String id = map1.get("id");
			if(!Common.isEmpty(id)){
				ArticleInfoBean articleInfoBean = articleInfoMapper.fetchById(Long.valueOf(id));
				model.addAttribute("articleInfoBean", articleInfoBean);
			}
		}
		model.addAttribute("company_id", company_id);
		model.addAttribute("appInfoBeans", appInfoBeans);
		
	}
}