package com.scinan.iot.service;

import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.s1000.dao.domain.UserInfo;
import com.scinan.mybatis.support.service.GenericService;
/**
 * 用户信息业务接口类
 * 
 * @project datacenter
 * @class com.scinan.iot.service.UserInfoService
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年07月28日
 * @description
 */
public interface UserInfoService extends GenericService<UserInfo, Long>{

	
	PageBean<UserInfo> getUserListBySource(AccountInfo bean);
    
    
}
