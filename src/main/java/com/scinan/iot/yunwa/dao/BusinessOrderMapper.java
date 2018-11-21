package com.scinan.iot.yunwa.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.yunwa.dao.domain.BusinessOrder;
import com.scinan.mybatis.support.mapper.GenericMapper;

/**
 * BusinessOrderDao接口类
 * @author yangkun
 *
 */
public interface BusinessOrderMapper extends GenericMapper<BusinessOrder, String> {

	List<BusinessOrder> fetchGroupByUserId(Map<String, Object> map);

	Integer countGroupByUserId(Map<String, Object> map);

	List<BusinessOrder> fetchGroupByDeviceId(Map<String, Object> map);

	Integer countGroupByDeviceId(Map<String, Object> map);

	void changeOrderStatus(BusinessOrder businessOrder);

	BusinessOrder indexcount(Map<String, Object> map);

	Integer fetchOrderCountByUserids(Map<String, Object> map1);

	List<BusinessOrder> fetchByUserids(Map<String, Object> map1);
	
	/**
	 * 消费者消费明细
	 * @param map
	 * @return BusinessOrder
	 */
	List<BusinessOrder> fetchByUserConsumeDetailList(Map<String, Object> map);
	Integer countUserDetailNum(Map<String, Object> map);
	
	/**
	 * 设备消费明细
	 * @param map
	 * @return BusinessOrder
	 */
	List<BusinessOrder>  fetchByDeviceConsumeDetailList(Map<String, Object> map);
	Integer countDeviceDetailNum(Map<String, Object> map);
	
	
	
	
	
	

}
