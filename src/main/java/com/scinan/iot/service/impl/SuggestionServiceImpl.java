package com.scinan.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.iot.s8000temp.dao.SuggestionDataMapper;
import com.scinan.iot.s8000temp.dao.domain.SuggestionData;
import com.scinan.iot.service.SuggestionService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;

/**
 * 
 * 意见反馈service
 * @project SNAPIServer_V2.0
 * @class com.scinan.iot.service.impl.SuggestionServiceImpl
 * @copyright www.scinan.com
 * @author Eric
 * @date Jan 30, 2016
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("suggestionService")
public class SuggestionServiceImpl  extends GenericServiceImpl<SuggestionData, Long> implements SuggestionService {
	
	@Autowired
	private SuggestionDataMapper suggestionDataMapper;
	

	@Override
	protected GenericMapper<SuggestionData, Long> getGenericMapper() {
		return suggestionDataMapper;
	}
	
	
}
