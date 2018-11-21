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

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.AccountRatioMapper;
import com.scinan.iot.ddeddo.dao.AccountRatioUpdateMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.AccountRatio;
import com.scinan.iot.ddeddo.dao.domain.AccountRatioUpdate;
import com.scinan.iot.service.AccountRatioService;
import com.scinan.iot.service.MsgPushService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.utils.Common;
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
@Transactional(propagation = Propagation.REQUIRED)
@Service("accountRatioService")
public class AccountRatioServiceImpl extends GenericServiceImpl<AccountRatio, Long> implements AccountRatioService {
	static Logger logger = Logger.getLogger(AccountRatioServiceImpl.class);
	
	@Autowired
	private AccountRatioMapper accountRatioMapper;
	@Autowired
	private AccountRatioUpdateMapper accountRatioUpdateMapper;
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	@Autowired
	private MsgPushService msgPushService;
	
	
	@Override
	protected GenericMapper<AccountRatio, Long> getGenericMapper() {
		return accountRatioMapper;
	}
	
	
	/**
	 * 根据用户查询分成比例信息
	 * @param user_id
	 * @return AccountRatio
	 */
	@Override
	public AccountRatio fetchByDevice_type(Long user_id){
		return accountRatioMapper.fetchByDevice_type(user_id);
	}
	
	 /**
     * 分成比例信息分页
     */
	public PageBean<AccountRatio> fetchParamsByPage(Map<String,String> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		if(params.get("device_name") != null){
			map.put("device_name", params.get("device_name"));
		}
		return new PageBean<AccountRatio>(accountRatioMapper.count(map),accountRatioMapper.fetchByPage(map));
	}

	/**
	 * 添加分成比例
	 * @param request 
	 * @param accountRatio
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public DataCenterResult addAccountRatio(HttpServletRequest request, AccountRatio accountRatio,
			Map<String, String> params) throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		accountInfoBean = accountInfoMapper.fetchById(accountInfoBean.getId());
		AccountRatio ar = accountRatioMapper.fetchByDevice_type(accountRatio.getDevice_type_id());
		if(ar != null){//说明添加过了
			return DataCenterResult.build(-101);
		}
		accountRatio.setCompany_id(accountInfoBean.getCompany_id());
		accountRatio.setCreate_time(new Date());
		accountRatio.setUpdate_time(new Date());
		accountRatioMapper.save(accountRatio);
		
		AccountRatioUpdate aru = new AccountRatioUpdate();
		aru.setDevice_type_id(accountRatio.getDevice_type_id());
		aru.setProduct_price(accountRatio.getProduct_price());
		aru.setProduct_price_new(accountRatio.getProduct_price());
		aru.setAgent_ratio(accountRatio.getAgent_ratio());
		aru.setAgent_ratio_new(accountRatio.getAgent_ratio());
		aru.setDealer_ratio(accountRatio.getDealer_ratio());
		aru.setDealer_ratio_new(accountRatio.getDealer_ratio());
		aru.setPrimage_ratio(accountRatio.getprimage_ratio());
		aru.setPrimage_ratio_new(accountRatio.getprimage_ratio());
		aru.setCompany_id(accountRatio.getCompany_id());
		aru.setCreate_time(new Date());
		accountRatioUpdateMapper.save(aru);//然后保存更改记录
		return DataCenterResult.ok();
	}

	/**
	 * 删除分成比例
	 * @param ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public DataCenterResult deleteAccountRatio(Long id) throws Exception {
		accountRatioMapper.delete(id);
		return DataCenterResult.ok();
	}

	/**
	 * 修改分成比例
	 * @param request 
	 * @param accountRatio
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public DataCenterResult updateAccountRatio(HttpServletRequest request, AccountRatio accountRatio,
			Map<String, String> params) throws Exception {
		AccountRatioUpdate aru = new AccountRatioUpdate();
		AccountRatio old = accountRatioMapper.fetchById(accountRatio.getId());
		aru.setDevice_type_id(old.getDevice_type_id());
		aru.setProduct_price(old.getProduct_price());
		aru.setProduct_price_new(accountRatio.getProduct_price());
		aru.setAgent_ratio(old.getAgent_ratio());
		aru.setAgent_ratio_new(accountRatio.getAgent_ratio());
		aru.setDealer_ratio(old.getDealer_ratio());
		aru.setDealer_ratio_new(accountRatio.getDealer_ratio());
		aru.setPrimage_ratio(old.getprimage_ratio());
		aru.setPrimage_ratio_new(accountRatio.getprimage_ratio());
		aru.setCompany_id(accountRatio.getCompany_id());
		aru.setCreate_time(new Date());
		
		accountRatio.setUpdate_time(new Date());
		
		accountRatioMapper.update(accountRatio);//先更新比例
		accountRatioUpdateMapper.save(aru);//然后保存更改记录
		msgPushService.msgPush(4, null);
		return DataCenterResult.ok();
	}
	  
			
	
}
