/**
 * @Description:
 * @Package: com.scinan.iot.service 
 * @author: 吴广   
 * @date: 2018年7月31日 下午3:31:09 
 */
package com.scinan.iot.service;

import java.util.Map;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.NotifySend;
import com.scinan.mybatis.support.service.GenericService;

/**
 * @Description: 发货通知
 * @author: 吴广
 * @date: 2018年7月31日 下午3:31:09 
 */
public interface NotifySendService extends GenericService<NotifySend, Long> {
	
	PageBean<NotifySend> fetchParamsByPage(Map<String,String> params);
	
	DataCenterResult deleteNotifySend(Long id)throws Exception ;
	
	DataCenterResult addNotifySend(NotifySend notifySend)throws Exception ;
	
	DataCenterResult updateNotifySend(NotifySend notifySend)throws Exception ;
	
	DataCenterResult updateNotifySendStatus(NotifySend notifySend)throws Exception ;

	Integer countByTransfer(Long user_id);
}
