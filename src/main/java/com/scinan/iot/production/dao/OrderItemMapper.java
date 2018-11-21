package com.scinan.iot.production.dao;


import java.util.List;
import java.util.Map;

import com.scinan.iot.production.dao.domain.OrderItemBean;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface OrderItemMapper extends GenericMapper<OrderItemBean, Integer>{

	void batchSave(List<OrderItemBean> list);
    
	List<OrderItemBean>  fetchByResOrderItemPage(Map<String,Object>  map);
	
	Integer countOrderItemNum(Map<String,Object>  map);
	
	OrderItemBean fetchByBarCode(Map<String,Object>  map);
	

}