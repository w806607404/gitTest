package com.scinan.iot.yunwa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.iot.yunwa.dao.domain.Areas;

/**
 * AreasDao接口类
 * @author yangkun
 *
 */
public interface AreasMapper extends GenericMapper<Areas, String> {

	List<Areas> fetchSearchAreas();

	List<Areas> fetchCityAreasByProvinceId(String id);

	List<Areas> fetchDistrictAreasByProvinceId(String id);

	List<Areas> fetchDistrictAreasByCityId(String id);
	
	List<Areas> fetchProvinceList();
	
	List<Areas> fetchCityList(@Param("parent_id")String parent_id);
	
	List<Areas> fetchAreaList(@Param("parent_id")String parent_id);
	
	
	List<Areas>  fetchAreaNameOfList();
	
	List<Areas> fetchAreasByParent_id(@Param("parent_id")String parent_id);

}
