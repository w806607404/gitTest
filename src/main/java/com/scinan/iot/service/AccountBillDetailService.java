/**
 * @Description:
 * @Package: com.scinan.iot.service 
 * @author: 吴广   
 * @date: 2018年7月27日 下午2:24:48 
 */
package com.scinan.iot.service;

import java.util.Map;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.AccountBillDetail;
import com.scinan.mybatis.support.service.GenericService;

/**
 * @Description: 该类的功能描述
 * @author: 吴广
 * @date: 2018年7月27日 下午2:24:48 
 */
public interface AccountBillDetailService extends GenericService<AccountBillDetail, Long> {
	
	public PageBean<AccountBillDetail> fetchParamsByPage(Map<String, String> params);
	
	public DataCenterResult deleteBillDetail(Long id) throws Exception;
}
