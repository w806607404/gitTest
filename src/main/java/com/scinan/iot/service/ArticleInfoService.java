package com.scinan.iot.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.s1000.dao.domain.ArticleInfoBean;
import com.scinan.mybatis.support.service.GenericService;

/**
 * 通知公告接口类
 * 
 * @project datacenter
 * @class com.scinan.iot.service.ArticleInfoService
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年7月23日
 * @description
 */
public interface ArticleInfoService extends GenericService<ArticleInfoBean, Long>{

	PageBean<ArticleInfoBean> fetchParamsByPage(HttpServletRequest request,Map<String, String> params, String type) throws Exception;

	DataCenterResult addArticle(ArticleInfoBean articleInfoBean,
			HttpServletRequest request);

	DataCenterResult updateAtticle(ArticleInfoBean articleInfoBean,HttpServletRequest request);

	DataCenterResult deleteArticles(List<String> strSplitToList);

	void setModel(HttpServletRequest request,Model model, String type);

	DataCenterResult checkArticles(ArticleInfoBean articleInfoBean);

}