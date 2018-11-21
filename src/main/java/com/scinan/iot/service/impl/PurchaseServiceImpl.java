/**
 * @Description:
 * @Package: com.scinan.iot.service.impl 
 * @author: 吴广   
 * @date: 2018年7月9日 下午2:17:23 
 */
package com.scinan.iot.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.ognl.InappropriateExpressionException;
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
import com.scinan.iot.ddeddo.dao.PurchaseMapper;
import com.scinan.iot.ddeddo.dao.RoleMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.Purchase;
import com.scinan.iot.ddeddo.dao.domain.PurchaseBean;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.PurchaseService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;

/**
 * @Description: 购货记录控制类
 * @author: 吴广
 * @date: 2018年7月9日 下午2:17:23 
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("purchaseService")
public class PurchaseServiceImpl extends GenericServiceImpl<PurchaseBean,Long> implements PurchaseService{
	static Logger logger = Logger.getLogger(PurchaseServiceImpl.class);
	
	
	@Autowired
	private PurchaseMapper purchaseMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	
	
	@Override
	protected GenericMapper<PurchaseBean, Long> getGenericMapper() {
		// TODO Auto-generated method stub
		return purchaseMapper;
	}
	
	/**
	 * 购货记录分页
	 */
	@Override
	public PageBean<PurchaseBean> fetchParamsByPage(Map<String, String> params) {
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
		conds.in("a.status", new Object[]{0,1,2});
		if (role.getRole_type()<1 && role.getParent_id()==AccountInfo.ACCOUNT_TYPE_ADMIN) {
			
		}else if (role.getRole_type()==AccountInfo.ACCOUNT_TYPE_COMMON) {//判断为厂商
			List<AccountInfo> list = accountInfoService.fetchLowerDealerByUserInfo(accountInfoBean.getId().intValue(), role.getCompany_id());
			List<String> str = new ArrayList<String>();			
			if (list!=null && list.size()>0) {
				for (AccountInfo accountInfo : list) {				
					str.add(accountInfo.getId().toString());				
				}
				Object[] strs = str.toArray();		
				conds.in("user_id", strs);
			}else {
				str.add(accountInfoBean.getId().toString());
				Object[] strs = str.toArray();		
				conds.in("user_id", strs);
			}
			
			
		}else if (role.getRole_type()==AccountInfo.ACCOUNT_TYPE_OTHER) {//代理商	
			List<AccountInfo> list = accountInfoService.fetchLowerDealerByUserInfo(accountInfoBean.getId().intValue(), role.getCompany_id());
			List<String> str = new ArrayList<String>();
			str.add(accountInfoBean.getId().toString());
			if (list!=null ||list.size()>0) {
				for (AccountInfo accountInfo : list) {				
					str.add(accountInfo.getId().toString());				
				}
			}
			Object[] strs = str.toArray();		
			conds.in("user_id", strs);
		}else {//经销商
			conds.equal("user_id", accountInfoBean.getId());		
		}
		
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		
		
		return new PageBean<PurchaseBean>(purchaseMapper.count(map),purchaseMapper.fetchByPurchasePage(map));
	}

	
	/**
	 * 修改物流状态
	 */
	@Override
	public DataCenterResult upadateStatus(Purchase purchase) {
			 if(purchase.getStatus()==1) {
				if(purchase.getPay_type() == 1){
					//增加发货人补贴余额
        			AccountInfo parentInfo = accountInfoService.fetchById(purchase.getParent_id());
        			parentInfo.setSubsidy_amount(parentInfo.getSubsidy_amount().add(purchase.getAmount()));
        			accountInfoService.update(parentInfo);
				}
				purchase.setStatus(2);
			}else if(purchase.getStatus()==0){
				purchase.setStatus(1);
			}
		int num = purchaseMapper.updateStatus(purchase);
		if (num>0) {
			return DataCenterResult.ok();
		}else {
			return DataCenterResult.build(-101);
		}
	}

	/**
	 * 添加购货记录,同时根据付款方式扣除账户余额
	 */
	@Override
	public DataCenterResult addPurchase(PurchaseBean purchaseBean) {
		try {
			// 获取request
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			AccountInfo user = accountInfoMapper.fetchById(accountInfoBean.getId());
			if (purchaseBean.getPay_type()==1) { //分红余额购货，扣除对应账户分红余额
				BigDecimal bigDecimal = user.getRatio_amount().subtract(purchaseBean.getAmount());
				user.setRatio_amount(bigDecimal);				
			}else if (purchaseBean.getPay_type()==2) {
				BigDecimal bigDecimal = user.getSubsidy_amount().subtract(purchaseBean.getAmount());
				user.setSubsidy_amount(bigDecimal);				
			}
			purchaseMapper.save(purchaseBean);
			accountInfoMapper.update(user);
			return DataCenterResult.ok();
		} catch (Exception e) {
			return DataCenterResult.build(-101);
		}
		
				
	}

	@Override
	public Integer countByTransfer(Long user_id) {
		return purchaseMapper.countByTransfer(user_id);
	}

}
