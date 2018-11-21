/**
 * @Description:
 * @Package: com.scinan.iot.service 
 * @author: 吴广   
 * @date: 2018年7月17日 下午3:13:53 
 */
package com.scinan.iot.service;

import java.util.Map;

import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.UserBack;
import com.scinan.mybatis.support.service.GenericService;

/**
 * @Description: 用户退换货记录
 * @author: 吴广
 * @date: 2018年7月17日 下午3:13:53 
 */
public interface UserBackService extends GenericService<UserBack, Long> {
	
	PageBean<UserBack> fetchParamsByPage(Map<String,String> params);
	
}
