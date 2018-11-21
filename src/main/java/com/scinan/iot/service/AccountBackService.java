/**
 * @Description:
 * @Package: com.scinan.iot.service 
 * @author: 吴广   
 * @date: 2018年7月17日 上午9:48:56 
 */
package com.scinan.iot.service;

import java.util.Map;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.AccountBack;
import com.scinan.iot.ddeddo.dao.domain.AccountBackBean;
import com.scinan.iot.ddeddo.dao.domain.AccountSold;
import com.scinan.iot.ddeddo.dao.domain.AccountSoldBean;
import com.scinan.mybatis.support.service.GenericService;

/**
 * @Description: 该类的功能描述
 * @author: 吴广
 * @date: 2018年7月17日 上午9:48:56 
 */
public interface AccountBackService extends GenericService<AccountBack,Long> {
	
	PageBean<AccountBack> fetchParamsByPage(Map<String,String> params);
	
	DataCenterResult addAccountBack(AccountBack accountBack)throws Exception ;
	
	DataCenterResult updateAccountBack(AccountBack accountBack)throws Exception ;
	
	DataCenterResult deleteAccountBack(Long id)throws Exception ;
	
	DataCenterResult updateAccountBackStatus(Long id)throws Exception ;
	
	Integer countByTransfer(Long user_id);
	
	/**
	  * 功能说明:获取商户退货信息
	  * @param map
	  * @return
	  */
	 AccountBack fetchByUser(Map<String,Object>  map);
	
}
