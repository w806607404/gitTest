package com.scinan.iot.s9000.dao;

import java.util.List;

import com.scinan.iot.s9000.dao.domain.ProvinceBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface ProvinceMapper extends GenericMapper<ProvinceBean, Long> {

	ProvinceBean fetchByProvinceInfo(String province_code);
    ProvinceBean fetchByProvinceOfName(String province_code);
    List<ProvinceBean> fetchByProvinceOfList();
}
