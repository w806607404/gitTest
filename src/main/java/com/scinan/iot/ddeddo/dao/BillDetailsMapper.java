package com.scinan.iot.ddeddo.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.ddeddo.dao.domain.BillCloseDetail;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：账单
 * @author Kim
 *
 */
public interface BillDetailsMapper extends GenericMapper<BillCloseDetail, Long> {

	
	List<BillCloseDetail>  fetchByBillPage(Map<String,Object>  map);
	
	
	Double sumBillOfMoney(Map<String,Object>  map);
          
}
