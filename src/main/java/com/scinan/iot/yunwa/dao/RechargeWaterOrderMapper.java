package com.scinan.iot.yunwa.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.yunwa.dao.domain.RechargeWaterOrder;
import com.scinan.mybatis.support.mapper.GenericMapper;

/**
 * RechargeWaterOrderDao接口类
 * @author yangkun
 *
 */
public interface RechargeWaterOrderMapper extends GenericMapper<RechargeWaterOrder, Long> {

	RechargeWaterOrder fetchByOrderid(String order_id);
	
	List<RechargeWaterOrder> fetchByRechargePage(Map<String,Object> map);
	
	Integer countRecharge(Map<String,Object> map);
	
	
	
	
	
	
	
	

}
