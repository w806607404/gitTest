package com.scinan.iot.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.AccountRatioUpdate;
import com.scinan.mybatis.support.service.GenericService;
/**
 * 
 * 
 * @project datacenter
 * @class com.scinan.iot.service.impl.BillDetailsServiceImpl
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月06日
 * @description
 */
public interface AccountRatioUpdateService extends GenericService<AccountRatioUpdate, Long> {
	static Logger logger = Logger.getLogger(AccountRatioUpdateService.class);
	
	
	
	/**
	 * 根据查询分成比例分页数据信息
	 * @param device_type
	 * @return AccountRatio
	*/
	PageBean<AccountRatioUpdate> fetchParamsByPage(Map<String,String> params);		
	
	
	/**
	 * 删除分成比例
	 * @param id
	 * @return
	 * @throws Exception
	 */
	DataCenterResult deleteAccountRatioUpdate(Long id);
	
	
}
