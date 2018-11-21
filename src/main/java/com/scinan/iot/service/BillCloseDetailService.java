/**
 * @Description:
 * @Package: com.scinan.iot.service 
 * @author: 吴广   
 * @date: 2018年7月20日 下午3:34:40 
 */
package com.scinan.iot.service;

import java.util.Map;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.BillCloseDetail;
import com.scinan.mybatis.support.service.GenericService;

/**
 * @Description: 体现申请结算
 * @author: 吴广
 * @date: 2018年7月20日 下午3:34:40 
 */
public interface BillCloseDetailService extends GenericService<BillCloseDetail, Long> {
	
	PageBean<BillCloseDetail> fetchParamsByPage(Map<String,String> params);
	
	DataCenterResult addBillClose(BillCloseDetail billCloseDetail);
	
	DataCenterResult updateBillClose(BillCloseDetail billCloseDetail);
	
	DataCenterResult updateBillClose(Long id);

	int countByTransfer(Long user_id);
}
