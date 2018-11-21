package com.scinan.iot.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.scinan.bean.DataCenterResult;
import com.scinan.iot.ddeddo.dao.UserBasicInfoMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.UserBasicInfo;
import com.scinan.iot.service.UserBasicInfoService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.utils.Common;
import com.scinan.utils.RedisUtil;

/**
 * 
 * 
 * @project vtldatacenter
 * @class com.scinan.iot.service.impl.UserBasicInfoServiceImpl
 * @copyright www.scinan.com
 * @author Kim
 * @date 2016年12月8日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("userBasicInfoService")
public class UserBasicInfoServiceImpl extends GenericServiceImpl<UserBasicInfo, Long> implements UserBasicInfoService {
	static Logger logger = Logger.getLogger(UserBasicInfoServiceImpl.class);
	
	@Autowired
	private UserBasicInfoMapper userBasicInfoMapper;
	
	@Override
	protected GenericMapper<UserBasicInfo, Long> getGenericMapper() {
		return userBasicInfoMapper;
	}

	/**
	 * 功能说明：添加用户基础信息
	 * @param userBasicInfo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
    public DataCenterResult addUserBasicInfo(Model model,UserBasicInfo userBasicInfo, HttpServletRequest request) throws Exception {
		 AccountInfo accountInfoBean = Common.getAccountInfo(request);
		 
		userBasicInfo.setCreate_time(new Date());
		userBasicInfo.setUser_id(accountInfoBean.getId());
		userBasicInfo.setCompany_id(accountInfoBean.getCompany_id());
		userBasicInfoMapper.saveInfo(userBasicInfo);
		if(accountInfoBean.getRole_type().equals("2")){
			RedisUtil.set("phone", userBasicInfo.getCustomer_phone());
			RedisUtil.set("url", userBasicInfo.getCompany_website());
			RedisUtil.set("wx_account", userBasicInfo.getWx_account());
			RedisUtil.set("wx_qr_code", userBasicInfo.getWx_qr_code());
		}
		
		//相同的经销商，只有一个基础信息
		RedisUtil.set(accountInfoBean.getRole_id()+"_"+"BASIC_INFORMATION_KEY", userBasicInfo.getId()+"");
		
		UserBasicInfo userBasicDetail = fetchById(userBasicInfo.getId());
		if(null!=userBasicDetail){
			model.addAttribute("userBasicInfo",userBasicInfo);
		}
		
        return DataCenterResult.ok();
    } 
	
	
	/**
	 * 功能说明：修改用户基础信息
	 * @param userBasicInfo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
    public DataCenterResult updateUserBasicInfo(Model model,UserBasicInfo userBasicInfo, HttpServletRequest request) throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		userBasicInfo.setCreate_time(new Date());
		userBasicInfo.setUser_id(userBasicInfo.getId());
		userBasicInfoMapper.updateInfo(userBasicInfo);
		if(accountInfoBean.getRole_type().equals("2")){
			//存储对应的用户公众信息
			RedisUtil.set("phone", userBasicInfo.getCustomer_phone());
			RedisUtil.set("url", userBasicInfo.getCompany_website());
			RedisUtil.set("wx_account", userBasicInfo.getWx_account());
			RedisUtil.set("wx_qr_code", userBasicInfo.getWx_qr_code());
		}
		
		UserBasicInfo userBasicDetail = fetchById(userBasicInfo.getUser_id(),userBasicInfo.getCompany_id());
		if(null!=userBasicDetail){
			model.addAttribute("userBasicInfo",userBasicInfo);
		}
        return DataCenterResult.ok();
    } 
	
	
	
	/**
	 * 功能说明：查询当前经销商所对应的基本信息
	 */
	@Override
	public UserBasicInfo fetchById(Long user_id,String company_id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("company_id", company_id);
		return userBasicInfoMapper.fetchByMtlInfo(map);
	}
	
	
	
	
	
}
