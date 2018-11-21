package com.scinan.iot.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.AccountSoldMapper;
import com.scinan.iot.ddeddo.dao.DeviceBelongMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.s1000.dao.FactoryMapper;
import com.scinan.iot.s1000.dao.domain.FactoryBean;
import com.scinan.iot.s6000.dao.DeviceInfoMapper;
import com.scinan.iot.s6000.dao.DeviceTypeMapper;
import com.scinan.iot.s6000.dao.domain.DeviceInfoBean;
import com.scinan.iot.s6000.dao.domain.DeviceTypeBean;
import com.scinan.iot.service.DeviceInfoService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;

/**
 * @project datacenter
 * @class com.scinan.iot.service.impl.DeviceInfoServiceImpl
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年6月28日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("deviceInfoService")
public class DeviceInfoServiceImpl extends GenericServiceImpl<DeviceInfoBean, Long> implements DeviceInfoService {
	static Logger logger = Logger.getLogger(DeviceInfoServiceImpl.class);
	static ExecutorService executorService = Executors.newCachedThreadPool();
	@Autowired
	private DeviceInfoMapper deviceInfoMapper;
	@Autowired
	private FactoryMapper factoryMapper;
	@Autowired
	private DeviceTypeMapper deviceTypeMapper;
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	@Autowired
	private AccountSoldMapper accountSoldMapper;
	@Autowired
	private DeviceBelongMapper deviceBelongMapper;

	@Override
	protected GenericMapper<DeviceInfoBean, Long> getGenericMapper() {
		return deviceInfoMapper;
	}

	public Integer countDeviceInfo(Map<String, Object> params) {
		return deviceInfoMapper.countDeviceInfo(params);
	}

	

	public PageBean<DeviceInfoBean> fetchParamsByPage(HttpServletRequest request,Map<String, String> params) {
		
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Conds conds = new Conds();
		
		
		if(StringUtils.isNotEmpty(params.get("search_device_id"))){
			conds.like("id",params.get("search_device_id"));
		}
		
		conds.equal("company_id", accountInfoBean.getCompany_id());
		if(StringUtils.isNotEmpty(params.get("search_date"))){ 
			conds.equal("DATE_FORMAT(join_time,'%Y-%m-%d')",params.get("search_date"));
		}else{
			conds.equal("DATE_FORMAT(join_time,'%Y-%m-%d')", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		}
		
		conds.equal("_join", "1");
		
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		
		List<DeviceInfoBean> deviceInfoBeans = deviceInfoMapper.fetchByPage(map);
		
		if(null != deviceInfoBeans && deviceInfoBeans.size() > 0){
			for(int i = 0; i< deviceInfoBeans.size();i++){
				FactoryBean factoryBean = factoryMapper.fetchById(deviceInfoBeans.get(i).getCompany_id());
				deviceInfoBeans.get(i).setFactoryBean(null == factoryBean ? new FactoryBean():factoryBean);
				if(null != deviceInfoBeans.get(i) && deviceInfoBeans.get(i).getType() != null){
					DeviceTypeBean deviceTypeBean = deviceTypeMapper.fetchDeviceTypeByCompanyIdAndTypeId(deviceInfoBeans.get(i).getCompany_id(), Long.valueOf(deviceInfoBeans.get(i).getType()+""));
					deviceInfoBeans.get(i).setDeviceTypeBean(null == deviceTypeBean? new DeviceTypeBean() : deviceTypeBean);
				}else{
					deviceInfoBeans.get(i).setDeviceTypeBean(new DeviceTypeBean());
				}
			}
		}
		Integer count = deviceInfoMapper.count(map);
		return new PageBean<DeviceInfoBean>(count,deviceInfoBeans);
	}

	
	
	
	public String setModel(HttpServletRequest request, Model model) {
		
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(accountInfoBean.getAccount_type() == AccountInfo.ACCOUNT_TYPE_ADMIN){//超级管理员
			//设备总数
			Integer count = deviceInfoMapper.countDevicesByMap(null);
			model.addAttribute("count", count);
			
			//List<DeviceInfoBean> deviceInfoBeans = deviceInfoMapper.fetchDevicesFactoryNames(params);
			//model.addAttribute("deviceInfoBeans", deviceInfoBeans);
			
			//List<DeviceInfoBean> onlineDeviceCountrys = deviceInfoMapper.fetchOnlineDeviceCountrys(params);
			//model.addAttribute("onlineDeviceCountrys", onlineDeviceCountrys);
			
		}else{//司南普通管理员
			
			//====================================================================================
			
			params.clear();
			
			//List<DeviceInfoBean> deviceInfoBeans = deviceInfoMapper.fetchDevicesFactoryNames(params);
			//model.addAttribute("deviceInfoBeans", deviceInfoBeans);
			
			//List<DeviceInfoBean> onlineDeviceCountrys = deviceInfoMapper.fetchOnlineDeviceCountrys(params);
			//model.addAttribute("onlineDeviceCountrys", onlineDeviceCountrys);
			
			
		}
		return null;
	}

	

	
	
	
	
	/**
	 * 首页相关数据
	 * @param request
	 * @param params
	 * @param model
	 */
	public void setIndexValue(HttpServletRequest request,Model model){
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		
		accountInfoBean = accountInfoMapper.fetchById(accountInfoBean.getId());
		model.addAttribute("accountInfo", accountInfoBean);
		if(accountInfoBean.getRole_type().intValue()<=1){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("company_id", accountInfoBean.getCompany_id());	
			
			//超级管理员和厂商管理员,可以查看设备总数
			int deviceAllNum = deviceInfoMapper.countDevice(params);    
			model.addAttribute("deviceAllNum", deviceAllNum);
			
			//分配给代理商的设备总数
			params.put("parent_id", accountInfoBean.getId());
			int dbNum = deviceBelongMapper.count(params);
			//一天卖给下级的数量
			int dbNumDay = deviceBelongMapper.countDay(params);
			
			//公司卖给用户的设备总数
			params.clear();
			params.put("user_id", accountInfoBean.getId());
			int soldNum = accountSoldMapper.count(params);
			//日销售给用户的量
			int soldNumDay = accountSoldMapper.countDay(params);
			//即时库存量
			model.addAttribute("inventoryNum", deviceAllNum-dbNum-soldNum <= 0 ? 0 : deviceAllNum-dbNum-soldNum);
			
			//总销售量
			model.addAttribute("soldNum", soldNum+dbNum);
			
			//日销售量
			model.addAttribute("soldNumDay", soldNumDay+dbNumDay);
			
			//激活设备
			params.clear();
			params.put("_join", "1");
			params.put("company_id", accountInfoBean.getCompany_id());
			int countJoin = accountSoldMapper.countJoinAll(params);
			model.addAttribute("countJoin", countJoin);
			
			//日激活量
			int activedNumDay = accountSoldMapper.countJoinDay(params);
			model.addAttribute("countJoinDay", activedNumDay);

			//未激活
			model.addAttribute("countUnJoin", (deviceAllNum-countJoin <= 0 ? 0 : deviceAllNum-countJoin));
			
		}else{
			//其它的情况
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("company_id", accountInfoBean.getCompany_id());
			params.put("user_id", accountInfoBean.getId());
			
			//代理商和经销商的设备总数
			int deviceAllNum = deviceBelongMapper.countSelf(params);
			model.addAttribute("deviceAllNum", deviceAllNum);

			//销售给用户的数量
			int soldNum = accountSoldMapper.count(params);
			//日销售给用户的量
			int soldNumDay = accountSoldMapper.countDay(params);
			
			//卖给下级的数量
			params.clear();
			params.put("parent_id", accountInfoBean.getId());
			int dbNum = deviceBelongMapper.count(params);
			//一天卖给下级的数量
			int dbNumDay = deviceBelongMapper.countDay(params);
			
			//即时库存量
			model.addAttribute("inventoryNum", deviceAllNum-dbNum-soldNum <= 0 ? 0 : deviceAllNum-dbNum-soldNum);
			
			//总销售量
			model.addAttribute("soldNum", soldNum+dbNum);
			
			//日销售量
			model.addAttribute("soldNumDay", soldNumDay+dbNumDay);
			
			//激活设备
			params.clear();
			params.put("user_id", accountInfoBean.getId());
			params.put("_join", 1);
			//自己的总激活量
			int countJoinAll = accountSoldMapper.countJoinAll(params);
			//自己的日激活量
			int countJoinDay = accountSoldMapper.countJoinDay(params);
			if(accountInfoBean.getRole_type() == 2){//代理商要加上下级的数量
				//获取所有下级
				List<AccountInfo> sons = accountInfoMapper.getListByParent(params);
				for(AccountInfo son : sons){
					params.clear();
					params.put("user_id", son.getId());
					params.put("_join", 1);
					countJoinAll += accountSoldMapper.countJoinAll(params);
					countJoinDay += accountSoldMapper.countJoinDay(params);
				}
			}
			
			//总激活量
			model.addAttribute("countJoin", countJoinAll);
			
			//日激活量
			model.addAttribute("countJoinDay", countJoinDay);
			
			//未激活
			model.addAttribute("countUnJoin", (deviceAllNum-countJoinAll)>=0?(deviceAllNum-countJoinAll):0);
			
		}
		
		
	}
	
	
	 
	 
	
	
}
