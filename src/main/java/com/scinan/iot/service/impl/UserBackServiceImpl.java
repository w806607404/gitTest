/**
 * @Description:
 * @Package: com.scinan.iot.service.impl 
 * @author: 吴广   
 * @date: 2018年7月17日 下午3:16:07 
 */
package com.scinan.iot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.UserBackMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountBackBean;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.UserBack;
import com.scinan.iot.service.UserBackService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.mybatis.support.sql.Sort;
import com.scinan.utils.Common;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

/**
 * @Description: 用户退换货记录
 * @author: 吴广
 * @date: 2018年7月17日 下午3:16:07 
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("userBackService")
public class UserBackServiceImpl extends GenericServiceImpl<UserBack, Long> implements
		UserBackService {
	static Logger logger = Logger.getLogger(UserBackServiceImpl.class);
	
	@Autowired
	private UserBackMapper userBackMapper;
	
	@Override
	protected GenericMapper<UserBack, Long> getGenericMapper() {

		return userBackMapper;
	}

	/**
	 * 用户退换货记录分页
	 */
	@Override
	public PageBean<UserBack> fetchParamsByPage(Map<String, String> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		
		Conds conds = new Conds();
		//查询条件
		if(StringUtils.isNotEmpty(params.get("device_id"))){
			conds.like("a.device_id",params.get("device_id"));
		}
		if(StringUtils.isNotEmpty(params.get("province_id"))){
			conds.equal("a.province_id",params.get("province_id"));
		}
		if(StringUtils.isNotEmpty(params.get("city_id"))){
			conds.equal("a.city_id",params.get("city_id"));
		}
		if(StringUtils.isNotEmpty(params.get("district_id"))){
			conds.equal("a.district_id",params.get("district_id"));
		}
		
		
		if (accountInfoBean.getUser_name().equals("admin")) {
			
		}else {
			conds.equal("a.user_id", accountInfoBean.getId());
		}
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		List<UserBack> list = userBackMapper.fetchByPage(map);
		if(list != null && list.size()>0){
			for(UserBack bean : list){
				bean.setDevice_name(Common.getDeviceName(bean.getDevice_id()));
			}
		}
		return new PageBean<UserBack>(userBackMapper.count(map),list);
	}

	

}
