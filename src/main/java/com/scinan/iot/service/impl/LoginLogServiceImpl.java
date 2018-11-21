package com.scinan.iot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.LoginLogMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.LoginLogBean;
import com.scinan.iot.service.LoginLogService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
/**
 * 登录log日志相关接口
 * 
 * @project datacenter
 * @class com.scinan.iot.service.impl.LoginLogServiceImpl
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月6日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("loginLogService")
public class LoginLogServiceImpl extends GenericServiceImpl<LoginLogBean, Long> implements LoginLogService {

	@Autowired
	private LoginLogMapper vtlLoginLogMapper;
	@Autowired
	private AccountInfoMapper vtlAccountInfoMapper;
	
	@Override
	protected GenericMapper<LoginLogBean, Long> getGenericMapper() {
		return vtlLoginLogMapper;
	}

	public PageBean<LoginLogBean> fetchParamsByPage(Map<String, String> params) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		
		List<LoginLogBean> loginLogBeans = vtlLoginLogMapper.fetchByPage(map);
		if(null != loginLogBeans && loginLogBeans.size() > 0){
			for(int i = 0; i<loginLogBeans.size();i++){
				AccountInfo accountInfoBean = vtlAccountInfoMapper.fetchById(loginLogBeans.get(i).getUser_id());
				if(null == accountInfoBean){
					accountInfoBean = new AccountInfo();
				}else{
					accountInfoBean.setUser_password(null);
				}
				//loginLogBeans.get(i).setUser_name(accountInfoBean.getUser_name());
			}
		}
		Integer count = vtlLoginLogMapper.count(map);
		return new PageBean<LoginLogBean>(count,loginLogBeans);
	}
	
}
