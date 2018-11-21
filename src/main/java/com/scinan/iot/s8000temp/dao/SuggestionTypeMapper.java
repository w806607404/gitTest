package com.scinan.iot.s8000temp.dao;

import java.util.List;

import com.scinan.iot.s8000temp.dao.domain.SuggestionType;
import com.scinan.mybatis.support.mapper.GenericMapper;

/**
 * SuggestionTypeDao接口类
 * @author yangkun
 *
 */
public interface SuggestionTypeMapper extends GenericMapper<SuggestionType, Integer> {

	List<SuggestionType> fetchAll();

}
