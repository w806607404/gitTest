/**
 * @Description:
 * @Package: com.scinan.iot.service 
 * @author: 吴广   
 * @date: 2018年7月2日 上午11:13:12 
 */
package com.scinan.iot.service;

import java.util.Map;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.AccountSold;
import com.scinan.iot.ddeddo.dao.domain.AccountSoldBean;
import com.scinan.mybatis.support.service.GenericService;

/**
 * @Description: 该类的功能描述
 * @author: 吴广
 * @date: 2018年7月2日 上午11:13:12 
 */
public interface AccountSoldService extends GenericService<AccountSold, Long> {
	
	
	DataCenterResult addAccountSold(AccountSold accountSold)throws Exception ;
	
	DataCenterResult updateAccountSold(AccountSoldBean accountSoldBean)throws Exception ;
	
	DataCenterResult deleteAccountSold(Long id)throws Exception ;
	
	PageBean<AccountSoldBean> fetchParamsByPage(Map<String,String> params);
	
	AccountSold fetchById(Long id);

	AccountSoldBean fetAccountSoldBean(Long id);
}
