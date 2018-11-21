/**
 * @Description:
 * @Package: com.scinan.iot.service 
 * @author: 吴广   
 * @date: 2018年8月9日 下午5:00:30 
 */
package com.scinan.iot.service;

import java.util.Map;

import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.NotifySend;
import com.scinan.mybatis.support.service.GenericService;

/**
 * @Description: 该类的功能描述
 * @author: 吴广
 * @date: 2018年8月9日 下午5:00:30 
 */
public interface SubsidyAmountBillDetailService extends GenericService<NotifySend, Long> {
	
	public PageBean<NotifySend> fetchParamsByPage(Map<String, String> params);
	
}
