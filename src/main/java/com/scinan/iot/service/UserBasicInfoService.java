package com.scinan.iot.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.scinan.bean.DataCenterResult;
import com.scinan.iot.ddeddo.dao.domain.UserBasicInfo;
import com.scinan.mybatis.support.service.GenericService;
/**
 * 账号信息业务接口类
 * 
 * @project datacenter
 * @class com.scinan.iot.service.UserBasicInfoService
 * @copyright www.scinan.com
 * @author Kim
 * @date 2016年7月6日
 * @description
 */
public interface UserBasicInfoService extends GenericService<UserBasicInfo, Long>{
	
	/**
	 * 功能说明：添加用户基础信息
	 * @param userBasicInfo
	 * @param request
	 * @return
	 * @throws Exception
	 */
    public DataCenterResult addUserBasicInfo(Model model,UserBasicInfo userBasicInfo, HttpServletRequest request) throws Exception;
    
    
    /**
	 * 功能说明：修改用户基础信息
	 * @param userBasicInfo
	 * @param request
	 * @return
	 * @throws Exception
	 */
    public DataCenterResult updateUserBasicInfo(Model model,UserBasicInfo userBasicInfo, HttpServletRequest request) throws Exception;
    
    
    
    /**
	 * 功能说明：查询当前经销商所对应的基本信息
	 */
	public UserBasicInfo fetchById(Long user_id,String company_id);
	
	
    
    
    
    
    
    
}
