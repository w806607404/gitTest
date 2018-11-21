package com.scinan.iot.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.AccountRatio;
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
public interface AccountRatioService extends GenericService<AccountRatio, Long> {
	static Logger logger = Logger.getLogger(AccountRatioService.class);
	
	
	/**
	 * 根据device_type查询分成比例信息
	 * @param device_type
	 * @return AccountRatio
	*/
	public AccountRatio fetchByDevice_type(Long device_type);
	
	/**
	 * 根据查询分成比例分页数据信息
	 * @param device_type
	 * @return AccountRatio
	*/
	PageBean<AccountRatio> fetchParamsByPage(Map<String,String> params);		
	
	/**
	 * 添加分成比例
	 * @param request 
	 * @param accountRatio
	 * @param params
	 * @return
	 * @throws Exception
	 */
	DataCenterResult addAccountRatio(HttpServletRequest request, AccountRatio accountRatio,Map<String, String> params)throws Exception ;
	
	/**
	 * 修改分成比例
	 * @param request 
	 * @param accountRatio
	 * @param params
	 * @return
	 * @throws Exception
	 */
	DataCenterResult updateAccountRatio(HttpServletRequest request, AccountRatio accountRatio,Map<String, String> params)throws Exception ;
	
	
	/**
	 * 删除分成比例
	 * @param id
	 * @return
	 * @throws Exception
	 */
	DataCenterResult deleteAccountRatio(Long id)throws Exception ;
	
	
}
