/**
 * @Description:
 * @Package: com.scinan.iot.service 
 * @author: 吴广   
 * @date: 2018年7月9日 下午2:14:20 
 */
package com.scinan.iot.service;

import java.util.Map;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.Purchase;
import com.scinan.iot.ddeddo.dao.domain.PurchaseBean;
import com.scinan.mybatis.support.service.GenericService;

/**
 * @Description: 该类的功能描述
 * @author: 吴广
 * @date: 2018年7月9日 下午2:14:20 
 */
public interface PurchaseService extends GenericService<PurchaseBean, Long> {
	
	PageBean<PurchaseBean> fetchParamsByPage(Map<String,String> params);
	
	DataCenterResult upadateStatus(Purchase purchase);
	
	DataCenterResult addPurchase(PurchaseBean purchaseBean);
	
	Integer countByTransfer(Long user_id);
}
