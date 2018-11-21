/**
 * @Description:
 * @Package: com.scinan.iot.service.impl 
 * @author: 吴广   
 * @date: 2018年7月17日 上午10:01:49 
 */
package com.scinan.iot.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.itextpdf.text.log.SysoLogger;
import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountBackMapper;
import com.scinan.iot.ddeddo.dao.AccountRatioMapper;
import com.scinan.iot.ddeddo.dao.AccountSoldMapper;
import com.scinan.iot.ddeddo.dao.DeviceBelongMapper;
import com.scinan.iot.ddeddo.dao.RoleMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountBack;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.AccountRatio;
import com.scinan.iot.ddeddo.dao.domain.DeviceBelong;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.service.AccountBackService;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;

/**
 * @Description: 商家退换货记录
 * @author: 吴广
 * @date: 2018年7月17日 上午10:01:49 
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("accountBackService")
public class AccountBackServiceImpl extends GenericServiceImpl<AccountBack, Long> implements
		AccountBackService {
    static Logger logger = Logger.getLogger(AccountBackServiceImpl.class);
	
    @Autowired
	private AccountBackMapper accountBackMapper;
    @Autowired 
    private RoleMapper roleMapper;
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private DeviceBelongMapper deviceBelongMapper;
    @Autowired
    private AccountSoldMapper accountSoldMapper;
    @Autowired
    private AccountRatioMapper accountRatioMapper;
    
    
    
	@Override
	protected GenericMapper<AccountBack, Long> getGenericMapper() {
		
		return accountBackMapper;
	}

	/**
	 * 商家退换货记录分页
	 */
	@Override
	public PageBean<AccountBack> fetchParamsByPage(Map<String, String> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		//获得当前用户角色对象
		Role role = roleMapper.fetchById(accountInfoBean.getRole_id());
		Conds conds = new Conds();
		//查询条件
		if(StringUtils.isNotEmpty(params.get("device_id"))){
			conds.like("a.device_id",params.get("device_id"));
		}
		if(StringUtils.isNotEmpty(params.get("status"))){
			conds.equal("a.status",params.get("status"));
		}
		
		
		
		if (role.getRole_type()<1 && role.getParent_id()==AccountInfo.ACCOUNT_TYPE_ADMIN) {//判断为超管
			
		}else if (role.getRole_type() == AccountInfo.ACCOUNT_TYPE_COMMON) {//厂商
			conds.equal("b.company_id", role.getCompany_id());			
			
		}else if (role.getRole_type() == AccountInfo.ACCOUNT_TYPE_OTHER) {//代理商
			List<AccountInfo> list = accountInfoService.fetchLowerDealerByUserInfo(accountInfoBean.getId().intValue(), role.getCompany_id());
			List<String> str = new ArrayList<String>();
			str.add(accountInfoBean.getId().toString());
			if (list!=null && list.size()>0) {
				for (AccountInfo accountInfo : list) {				
					str.add(accountInfo.getId().toString());				
				}
			}			
			Object[] strs = str.toArray();		
			conds.in("a.user_id", strs);
			
		}else {
			conds.equal("a.user_id", accountInfoBean.getId());		
		}
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		List<AccountBack> beans = accountBackMapper.fetchByPage(map);
		if(beans != null && beans.size()>0){
			for(AccountBack bean : beans){
				bean.setDevice_name(Common.getDeviceName(bean.getDevice_id()));
			}
		}
		return new PageBean<AccountBack>(accountBackMapper.count(map),beans);
	}

	/**
	 * 添加退换货记录
	 */
	@Override
	public DataCenterResult addAccountBack(AccountBack accountBack)
			throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		 params.put("device_id", accountBack.getDevice_id());
         params.put("user_id", accountBack.getUser_id());
         if(accountBackMapper.fetchByUser(params) != null){
        	 return DataCenterResult.build(-104); 
         }
         DeviceBelong bean = deviceBelongMapper.fetchByDevice(params);
         if(bean == null){
			return DataCenterResult.build(-101);
		}
         if(accountSoldMapper.fetchByDeviceIdOrUserId(params) != null){
     	  	return DataCenterResult.build(-103);
      }
		 //如果是代理商退货
   	 if(bean.getDevice_level() == 2){
   		 params.clear();
        	 params.put("device_id", bean.getDevice_id());
            params.put("device_level", 3);
            //查一下下级有没有绑定此产品
            if(deviceBelongMapper.fetchByDevice(params) != null){
            	return DataCenterResult.build(-102);
            }
   	 }
		accountBack.setParent_id(accountInfoService.fetchById(accountBack.getUser_id()).getParent_user_id());
		accountBackMapper.save(accountBack);
		return DataCenterResult.ok();
	}

	/**
	 * 修改退换货记录
	 */
	@Override
	public DataCenterResult updateAccountBack(AccountBack accountBack)
			throws Exception {
		accountBackMapper.update(accountBack);
		return DataCenterResult.ok();
	}

	/**
	 * 删除退换货记录
	 */
	@Override
	public DataCenterResult deleteAccountBack(Long id) throws Exception {
		accountBackMapper.delete(id);
		return DataCenterResult.ok();
	}

	/**
	 * 修改退换货记录状态
	 */
	@Override
	public DataCenterResult updateAccountBackStatus(Long id)
			throws Exception {
		AccountBack accountBack = accountBackMapper.fetchById(id);
		if (accountBack.getStatus()==0) {
			Map<String,Object> params = new HashMap<String, Object>();
			 params.put("device_id", accountBack.getDevice_id());
	         params.put("user_id", accountBack.getUser_id());
	         DeviceBelong bean = deviceBelongMapper.fetchByDevice(params);
	         if(bean == null){
				return DataCenterResult.build(-102);
			}
	     	deviceBelongMapper.delete(bean.getId());
			accountBack.setStatus(1);
		}	
		if(accountBack.getStatus() == 2){
			accountBack.setStatus(3);
			AccountRatio accountRatio = accountRatioMapper.fetchByDevice_type(Common.getDeviceTypeBean(accountBack.getDevice_id()).getId());
			AccountInfo backUser = accountInfoService.fetchById(accountBack.getUser_id());
        	backUser.setSubsidy_amount(backUser.getSubsidy_amount().add(new BigDecimal(accountRatio.getProduct_price()+"")));
//        	增加补贴金额
        	accountInfoService.update(backUser);
		}
		accountBackMapper.update(accountBack);
		return DataCenterResult.ok();
	}

	@Override
	public Integer countByTransfer(Long user_id) {
		return accountBackMapper.countByTransfer(user_id);
	}

	@Override
	public AccountBack fetchByUser(Map<String, Object> map) {
		return accountBackMapper.fetchByUser(map);
	}
	
	

}
