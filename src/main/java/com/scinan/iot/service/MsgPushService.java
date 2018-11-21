package com.scinan.iot.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.AccountRatio;
import com.scinan.mybatis.support.service.GenericService;
/**
 * 
 * 
 * @project datacenter
 * @class com.scinan.iot.service.impl.BillDetailsServiceImpl
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月06日
 * @description
 */
public interface MsgPushService {
	static Logger logger = Logger.getLogger(MsgPushService.class);
	
	
	/**
	 * 根据device_type查询分成比例信息
	 * @param type
	 * @param userId
	*/
	public void msgPush(int type , String userId);
	
	
}
