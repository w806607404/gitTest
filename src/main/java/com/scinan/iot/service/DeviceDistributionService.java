package com.scinan.iot.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.DeviceBelong;
import com.scinan.iot.ddeddo.dao.domain.DeviceBelongBean;
import com.scinan.iot.production.dao.domain.OrderItemBean;
import com.scinan.iot.s6000.dao.domain.DeviceInfo;
import com.scinan.mybatis.support.service.GenericService;

public interface DeviceDistributionService extends GenericService<DeviceBelong, Long>{
	
	
	/**
	 * 获取分页设备信息
	 * @param params
	 * @return PageBean<DeviceInfo>
	 */
	PageBean<DeviceInfo> fetchParamsByPage(Map<String, String> params) throws Exception ;
	
	
	
	 /**
	   * 功能说明：下级用户
	   * @param model
	   * @param params
	 */
	 public void setModuleInfo(Model model,Map<String, Object> params);
	 
	 
	 
	 /**
	   * 功能说明：批量分配设备
	   * @param params
	   * @return DataCenterResult
	   */
	  public DataCenterResult batchSave(Map<String, String> params);
	  
	  
	  
	  /**
	   * 功能说明：为下级经销商批量分配设备
	   * @param params
	   * @return DataCenterResult
	   */
	  public DataCenterResult batchUpdate(Map<String, String> params);
	 
	
	
	
	
	  /**
	    * 获取设备取消分配信息
	  */ 
	 public PageBean<DeviceBelong> fetchCancelParamsByPage(AccountInfo accountInfoBean,Map<String, String> params);
	
	
	
	 /**
	   * 功能说明：取消批量分配设备
	   * @param params
	   * @return DataCenterResult
	  */
	  public DataCenterResult batchDel(Map<String, String> params);
	  
	  
	  /**
	   * 功能说明：为下级取消批量分配设备
	   * @param params
	   * @return DataCenterResult
	  */
	  public DataCenterResult subCancelAllocation(Map<String, String> params);
	  
	  
	
	  /**
	      * 获取设备分配信息
	  */
	  public PageBean<DeviceBelong> fetchDistributionParamsByPage(AccountInfo accountInfoBean,Map<String, String> params);
	  
	  
	  
	  public int countDeviceNum(String device_id,int device_level);
	  
	  
	  public Integer batchDeviceSave(List<DeviceBelong>   list);
	  
	  
	  public DeviceBelong fecthDeviceBelongList(String deviceId,String user_path);
	  
	  
	  public Integer batchDeviceUpdate(Long id,Integer device_level,String user_path);
	  
	  
	  
	  /**
	   * 功能说明：一键迁移当前用户下的所有下级用户
	   * @param model
	   * @param params
	   */
	  public void setModuleSubUser(Model model,Map<String, Object> params);
	  
	  
	  
	  /**
	   * 功能说明:通过bar_code 找到对应的MAC 地址
	   * @param bar_code
	   * @return OrderItemBean
	   */
	  public OrderItemBean fetchByBarCode(String bar_code);
	  
	  
	  /**
	    * 获取设备分配记录分页
	  */ 
	 public PageBean<DeviceBelongBean> fetchByDeviceBelongPage(Map<String, String> params);
	  
	
}

