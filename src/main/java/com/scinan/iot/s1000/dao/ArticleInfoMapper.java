package com.scinan.iot.s1000.dao;

import java.util.List;

import com.scinan.iot.s1000.dao.domain.ArticleInfoBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface ArticleInfoMapper extends GenericMapper<ArticleInfoBean, Long> {

	void deleteId(List<String> ids);

	void check(ArticleInfoBean articleInfoBean);
	
}