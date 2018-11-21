package com.scinan.iot.ddeddo.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.ddeddo.dao.domain.AccountSold;
import com.scinan.iot.ddeddo.dao.domain.AccountSoldBean;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：用户销售记录
 * @author vinson
 *
 */
public interface AccountSoldMapper extends GenericMapper<AccountSold, Long> {
    
	/**
	  * 功能说明:日销售量
	  * @param map
	  * @return
	  */
	 Integer countDay(Map<String,Object>  map);
	 
	 /**
	  * 功能说明:日销售量
	  * @param map
	  * @return
	  */
	 Integer countMouth(Map<String,Object>  map);
	 
	 /**
	  * 功能说明:分页销售量
	  * @param map
	  * @return
	  */
	 Integer countPage(Map<String,Object>  map);
	 
	 
	 AccountSold fetchById(String id);

	 // 主键为数字型
	 AccountSold fetchByIdLong(Long id);

	 int delete(String id);

	 // 主键为数字型
	 int deleteByPK(Long id);
	 
	 List<AccountSoldBean> fetchAccountSoldBeanList(Map<String,Object>  map);
	 
	 int update(AccountSoldBean accountSoldBeans);
	 
	 int save(AccountSoldBean accountSoldBeans);
	 
	 /**
	  * 功能说明:日激活量
	  * @param map
	  * @return
	  */
	 Integer countJoinDay(Map<String,Object>  map);
	 
	 /**
	  * 功能说明:总激活量
	  * @param map
	  * @return
	  */
	 Integer countJoinAll(Map<String,Object>  map);
	 
	 /**
	  * 功能说明:根据设备ID或者用户ID获取销售订单
	  * @param map
	  * @return
	  */
	 AccountSold fetchByDeviceIdOrUserId(Map<String,Object>  map);
	 
	 int deleteByUser_id(Long user_id);
}
