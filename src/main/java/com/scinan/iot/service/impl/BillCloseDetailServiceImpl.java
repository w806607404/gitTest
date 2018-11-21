/**
 * @Description:
 * @Package: com.scinan.iot.service.impl 
 * @author: 吴广   
 * @date: 2018年7月20日 下午3:36:17 
 */
package com.scinan.iot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.BillCloseDetailMapper;
import com.scinan.iot.ddeddo.dao.RoleMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.BillCloseDetail;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.BillCloseDetailService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.scope.OrderType;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.mybatis.support.sql.Sort;
import com.scinan.utils.Common;

/**
 * @Description: 申请提现结算
 * @author: 吴广
 * @date: 2018年7月20日 下午3:36:17 
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("billCloseDetailService")
public class BillCloseDetailServiceImpl extends GenericServiceImpl<BillCloseDetail, Long>
		implements BillCloseDetailService {
	static Logger logger = Logger.getLogger(BillCloseDetailServiceImpl.class);
	
	
	@Autowired
	private BillCloseDetailMapper billCloseDetailMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	
	
	@Override
	protected GenericMapper<BillCloseDetail, Long> getGenericMapper() {
		// TODO Auto-generated method stub
		return billCloseDetailMapper;
	}
	
	
	/**
	 * 购货记录分页
	 */
	@Override
	public PageBean<BillCloseDetail> fetchParamsByPage(Map<String, String> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		//获得当前用户角色对象
		Role role = roleMapper.fetchById(accountInfoBean.getRole_id());
		
		Conds conds = new Conds();		
		//查询条件
		if(StringUtils.isNotEmpty(params.get("user_name"))){
			conds.like("user_name",params.get("user_name"));
		}
		if(StringUtils.isNotEmpty(params.get("status"))){
			conds.equal("a.status",params.get("status"));
		}
		
		if (role.getRole_type()<1 && role.getParent_id()==AccountInfo.ACCOUNT_TYPE_ADMIN) {
			
		}else if (role.getRole_type()==AccountInfo.ACCOUNT_TYPE_COMMON) {//判断为厂商
//			List<AccountInfo> list = accountInfoService.fetchLowerDealerByUserInfo(accountInfoBean.getId().intValue(), role.getCompany_id());
//			List<String> str = new ArrayList<String>();			
//			if (list!=null ||list.size()>0) {
//				for (AccountInfo accountInfo : list) {				
//					str.add(accountInfo.getId().toString());				
//				}
//				Object[] strs = str.toArray();		
//				conds.in("user_id", strs);
//			}else {
//				str.add(accountInfoBean.getId().toString());
//				Object[] strs = str.toArray();		
//				conds.in("user_id", strs);
//			}
			conds.equal("a.parent_id", 2);	
			
		}else if (role.getRole_type()==AccountInfo.ACCOUNT_TYPE_OTHER) {//代理商	
//			List<AccountInfo> list = accountInfoService.fetchLowerDealerByUserInfo(accountInfoBean.getId().intValue(), role.getCompany_id());
//			List<String> str = new ArrayList<String>();
//			str.add(accountInfoBean.getId().toString());
//			if (list!=null && list.size()>0) {
//				for (AccountInfo accountInfo : list) {				
//					str.add(accountInfo.getId().toString());				
//				}
//			}
//			Object[] strs = str.toArray();		
//			conds.in("user_id", strs);
			conds.equal("a.user_id", accountInfoBean.getId());
			conds.equal_or("a.parent_id",accountInfoBean.getId());	
			if(StringUtils.isNotEmpty(params.get("user_name"))){
				conds.like("user_name",params.get("user_name"));
			}
			if(StringUtils.isNotEmpty(params.get("status"))){
				conds.equal("a.status",params.get("status"));
			}
		}else {//经销商
			conds.equal("a.user_id", accountInfoBean.getId());		
		}
		Sort sort = new Sort("a.create_time", OrderType.DESC);
		map.put("sort", sort);
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));		
		
		
		return new PageBean<BillCloseDetail>(billCloseDetailMapper.count(map),billCloseDetailMapper.fetchByPage(map));
	}


	/**
	 * 申请提现记录
	 */
	@Override
	public DataCenterResult addBillClose(BillCloseDetail billCloseDetail) {
		//提现人
		AccountInfo ai = accountInfoMapper.fetchById(billCloseDetail.getUser_id());
		if(billCloseDetail.getClose_type() == 0){
			billCloseDetail.setParent_id(new Long(2));
			 //验证余额
	   		 if(billCloseDetail.getAmount().compareTo(ai.getRatio_amount()) == 1){
	       	     return DataCenterResult.build(-101);
	       	 }
	    	ai.setRatio_amount(ai.getRatio_amount().subtract(billCloseDetail.getAmount()));
		}else{
			//验证余额
    		 if(ai.getSubsidy_amount().compareTo(billCloseDetail.getAmount()) == -1){
    			 return DataCenterResult.build(-101);
    		 }
    		ai.setSubsidy_amount(ai.getSubsidy_amount().subtract(billCloseDetail.getAmount()));
			billCloseDetail.setParent_id(ai.getParent_user_id());
		}
		accountInfoMapper.update(ai);
		billCloseDetailMapper.save(billCloseDetail);
		return DataCenterResult.ok();
	}


	/**
	 * 结算更新状态
	 */
	@Override
	public DataCenterResult updateBillClose(BillCloseDetail billCloseDetail) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		AccountInfo accountInfoBean = Common.getAccountInfo(request);		
		BillCloseDetail billCloseDetail2 = billCloseDetailMapper.fetchById(billCloseDetail.getId());
		if (billCloseDetail2.getStatus()==0 && accountInfoBean.getId().equals(billCloseDetail2.getParent_id())) {//表示结算操作申请中的提现记录
			billCloseDetail2.setStatus(1);
			billCloseDetail2.setClose_time(new Date());
		}
		billCloseDetailMapper.update(billCloseDetail2);
		return DataCenterResult.ok();
	}


	/**
	 * 更新状态已完成
	 */
	public DataCenterResult updateBillClose(Long id) {
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		BillCloseDetail billCloseDetail = billCloseDetailMapper.fetchById(id);
		if (billCloseDetail.getStatus() == 1 && accountInfoBean.getId().equals(billCloseDetail.getUser_id())) {
			billCloseDetail.setStatus(2);
			
		}else {
			return DataCenterResult.build(-101);
		}
		billCloseDetailMapper.update(billCloseDetail);
		return DataCenterResult.ok();
	}


	@Override
	public int countByTransfer(Long user_id) {
		return billCloseDetailMapper.countByTransfer(user_id);
	}


	
}
