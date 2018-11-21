package com.scinan.controller.business.util;	



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.AccountRatio;
import com.scinan.iot.ddeddo.dao.domain.DeviceBelong;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.AccountRatioService;
import com.scinan.utils.RedisUtil;
import com.scinan.utils.StringUtil;


/**
 * 
 * @project DataCenter
 * @class com.scinan.thread.SimInfoImportThread
 * @copyright www.scinan.com
 * @author kim
 * @date 2017年07月21日
 * @description 
 */
public class TransferInfoThread extends Thread{

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private AccountRatioService accountRatioService;
	private Map<String,Object> params;
	private AccountInfo accountInfoBean;
	
	
	
	
	public TransferInfoThread(AccountInfoService accountInfoService,Map<String,Object> params,AccountRatioService accountRatioService,AccountInfo accountInfoBean){
		this.accountInfoService=accountInfoService;
		this.params = params;
		this.accountRatioService = accountRatioService;
		this.accountInfoBean = accountInfoBean;
	}
	
	public void run(){
		
		/**************************改变用户状态****************************************/
		String pUserId = String.valueOf(params.get("p_user_id"));
		String nUserId = String.valueOf(params.get("n_user_id"));
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setId(Long.parseLong(pUserId));
		accountInfo.setStatus(0);
		accountInfo.setUpdate_time(new Date());
		accountInfoService.update(accountInfo);     //更新迁移的用户,为不可用状态
		
		List<AccountInfo> pDealerList = accountInfoService.fetchLowerDealerByUserInfo(Integer.parseInt(pUserId), accountInfoBean.getCompany_id());
		if(null!=pDealerList){
			for(AccountInfo bean:pDealerList){
				AccountInfo nAccountInfo = new AccountInfo();
				nAccountInfo.setId(bean.getId());
				nAccountInfo.setParent_user_id(Long.parseLong(nUserId));
				nAccountInfo.setParent_user_path(userPathChod(bean.getParent_user_path(),pUserId,nUserId));
				accountInfoService.updateAccountInfo(nAccountInfo);
			}
		}
		/*************************迁移下级经销商  结束*****************************************/
		
		
		/**************当前用户下的所有设备*********************************************/
		//查询当前用户所对应的路径
		try {
			AccountInfo accountBean =  accountInfoService.fetchVtlUserInfo(Long.parseLong(pUserId));
			
			//根据user_path,查询分配给当前用户的设备列表
			Map<String,Object>  map = new HashMap<String,Object>();
			map.put("company_id", accountBean.getCompany_id());
			map.put("user_path", accountBean.getParent_user_path()+"/"+accountBean.getId());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private String userPathChod(String oldUserPath,String oUserId,String nUserId){
		  String strVal = "/";
		  if(!StringUtil.isNull(oldUserPath)){
			  String[] oUserPath = oldUserPath.split("\\/");
			  for(int i=1;i<oUserPath.length;i++){
				  String _nUserPath = oUserPath[i];
				  if(i==1){
					  strVal+=""+_nUserPath;
				  }else{
					  if(_nUserPath.equals(oUserId)){
						  strVal+="/"+nUserId;
					  }else{
						  strVal+="/"+_nUserPath;
					  }
				  }
			  }
		  }
		  return strVal;
	}
	
	

	
    
}
