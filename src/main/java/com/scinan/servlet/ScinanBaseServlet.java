/**
 *
 */
package com.scinan.servlet;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import com.scinan.iot.s9000.dao.domain.CityBean;
import com.scinan.iot.s9000.dao.domain.CountryBean;
import com.scinan.iot.s9000.dao.domain.ProvinceBean;
import com.scinan.iot.service.CommonService;
import com.scinan.spring.SpringUtil;
import com.scinan.utils.Common;

/**
 * 初始化spring容器
 * @project datacenter
 * @class com.scinan.servlet.ScinanBaseServlet
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月10日
 * @description
 */
public class ScinanBaseServlet extends HttpServlet {

	protected final Logger logger = Logger.getLogger(ScinanBaseServlet.class);

    private static final long serialVersionUID = 1L;
	
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        SpringUtil.init(config.getServletContext());
        
        CommonService commonService = (CommonService) SpringUtil.getBean("commonService");
        //初始应的国家地区
        List<CountryBean> countryList = commonService.fetchByCountryOfList();
        Common.putCountry(countryList);
        
        //省份
        List<ProvinceBean> provinceList =  commonService.fetchByProvinceOfList();
        Common.putProvince(provinceList);
        /*if(null!=provinceList){
        	RedisUtil.set("COMMON_PROVINCE_LIST", JSONArray.fromObject(provinceList).toString());
        }*/
        
        //地市
        List<CityBean> cityList =  commonService.fetchByCityOfList();
        Common.putCity(cityList);
        /*if(null!=cityList){
        	RedisUtil.set("COMMON_CITY_LIST", JSONArray.fromObject(cityList).toString());
        }*/
    }

    

}
