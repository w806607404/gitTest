package com.scinan.iot.ddeddo.dao;

import java.util.Map;

import com.scinan.iot.ddeddo.dao.domain.AccountBack;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：商家退换货记录
 * @author vinson
 *
 */
public interface AccountBackMapper extends GenericMapper<AccountBack, Long> {
    
	Integer countByTransfer(Long user_id);
	
	int deleteByUser_id(Long user_id);
	
	/**
	  * 功能说明:获取商户退货信息
	  * @param map
	  * @return
	  */
	 AccountBack fetchByUser(Map<String,Object>  map);
}
