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
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.DeviceBelongMapper;
import com.scinan.iot.ddeddo.dao.RoleMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.AccountSoldBean;
import com.scinan.iot.ddeddo.dao.domain.DeviceBelong;
import com.scinan.iot.ddeddo.dao.domain.DeviceBelongBean;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.production.dao.OrderItemMapper;
import com.scinan.iot.production.dao.domain.OrderItemBean;
import com.scinan.iot.s6000.dao.DeviceInfoMapper;
import com.scinan.iot.s6000.dao.domain.DeviceInfo;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.DeviceDistributionService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;
import com.scinan.utils.RedisUtil;
import com.scinan.utils.StaticCommad;
import com.scinan.utils.StringUtil;
/**
 * 
 * 
 * @project datacenter
 * @class com.scinan.iot.service.impl.DeviceDistributionServiceImpl
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年08月14日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("deviceDistributionService")
public class DeviceDistributionServiceImpl extends GenericServiceImpl<DeviceBelong, Long> implements DeviceDistributionService {
	static Logger logger = Logger.getLogger(DeviceDistributionServiceImpl.class);
	
	@Autowired
	private DeviceBelongMapper deviceBelongMapper;
	@Autowired
	private DeviceInfoMapper deviceInfoMapper;
	@Autowired
	private AccountInfoMapper accountInfoMapper ;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private AccountInfoService accountInfoService;
	
	
	@Override
	protected GenericMapper<DeviceBelong, Long> getGenericMapper() {
		return deviceBelongMapper;
	}
	
	
     /**
      * 获取分页设备信息
      */
	 @Override
	 public PageBean<DeviceInfo> fetchParamsByPage(Map<String, String> params) {
	        Map<String, Object> sqlMap = new HashMap<String,Object>();
	        
	        if(StringUtil.isNotEmpty(params.get("company_id"))){
	        	sqlMap.put("company_id", String.valueOf(params.get("company_id")));
	        }
	        if(StringUtil.isNotEmpty(params.get("device_id"))){
	        	sqlMap.put("id", params.get("device_id"));
	        }
	        if(StringUtil.isNotEmpty(params.get("type"))){
	        	sqlMap.put("type", params.get("type"));
	        }
	        if(StringUtil.isNotEmpty(params.get("deviceId"))){
	        	sqlMap.put("device_id", params.get("deviceId"));
	        }
	        
	        sqlMap.put(StaticCommad.OFFSET, Integer.parseInt(params.get(StaticCommad.OFFSET)));
	        sqlMap.put(StaticCommad.LIMIT, Integer.parseInt(params.get(StaticCommad.LIMIT)));

	        int count = deviceInfoMapper.countDevice(sqlMap);
	        List<DeviceInfo> deviceList = deviceInfoMapper.fetchRoleDeviceByPage(sqlMap);
	        return new PageBean<DeviceInfo>(count, deviceList);
	    }
	
	
	 
	  /**
	   * 功能说明：下级用户
	   * @param model
	   * @param params
	   */
	  public void setModuleInfo(Model model,Map<String, Object> params){
		  List<AccountInfo> nList = new ArrayList<AccountInfo>();
		  if(!StringUtil.isNull(String.valueOf(params.get("parent_user_id")))){
			  List<AccountInfo> list = accountInfoMapper.fetchUserByOneKey(params);
			  if(null!=list){
				  for(AccountInfo bean:list){
					  Map<String,Object> roleMap = new HashMap<String,Object>();
					  roleMap.put("id", bean.getRole_id());
					  Role role = roleMapper.fetchRoleById(roleMap);
					  if(null!=role){
						  /*int _nRoleType =  Integer.parseInt(String.valueOf(params.get("roleType")));   //当前用户的用户组类型
						  if(_nRoleType!=Integer.parseInt(role.getRole_type())){
							  nList.add(bean);
						  }*/
						  if(role.getParent_userid()<=0){   //下级代理商角色
							  nList.add(bean);
						  }
					  }
				  }
				  model.addAttribute("list", nList);
				  model.addAttribute("ids_arr", params.get("ids_arr"));
			  }
		  }
	  }
	
	
	  /**
	   * 功能说明：批量分配设备
	   * @param params
	   * @return DataCenterResult
	   */
	  public DataCenterResult batchSave(Map<String, String> params){
		  
		  List<DeviceBelong>   list = new ArrayList<DeviceBelong>();
		  String _ids = params.get("ids");
		  if(StringUtil.isNotEmpty(_ids)){
			  String[] _id = _ids.split(",");
			  if(null!=_id && _id.length>0){
				  for(String deviceId:_id){
					  DeviceBelong bean = new DeviceBelong();
					  bean.setDevice_id(deviceId);
					  bean.setCreate_time(new Date());
					  bean.setCompany_id(params.get("company_id"));
					  bean.setDevice_level(Integer.parseInt(params.get("device_level").toString())+1);
					  bean.setUser_id(new Long(params.get("user_id")));
					  
					  //设置设备归属的经销商
					  RedisUtil.set("MTL_DEV_USER_" + deviceId, (params.get("user_path")+"/"+params.get("user_id")).replaceFirst("/1", ""));
					  list.add(bean);
				  }
			  }
		  }
		  deviceBelongMapper.batchSave(list);
		  return DataCenterResult.ok();
	  }
	  
	  
	  /**
	   * 功能说明：为下级经销商批量分配设备
	   * @param params
	   * @return DataCenterResult
	   */
	  public DataCenterResult batchUpdate(Map<String, String> params){
		  //List<DeviceBelong>   list = new ArrayList<DeviceBelong>();
		  String _ids = params.get("ids");
		  if(StringUtil.isNotEmpty(_ids)){
			  String[] _id = _ids.split(",");
			  if(null!=_id && _id.length>0){
				  for(String id:_id){
					  //DeviceBelong bean = new DeviceBelong();
					  //bean.setId(Long.parseLong(id));
					  Map<String,Object> map = new HashMap<String,Object>();
					  map.put("id", id);
					  map.put("user_path", params.get("user_path")+"/"+params.get("user_id"));
					  map.put("device_level", Integer.parseInt(params.get("device_level").toString())+1);
					  //设置设备归属的经销商
					  DeviceBelong deviceBelong = deviceBelongMapper.fetchById(Long.parseLong(id));
					  if(null!=deviceBelong){
					      RedisUtil.set("MTL_DEV_USER_" + deviceBelong.getDevice_id(), (params.get("user_path")+"/"+params.get("user_id")).replaceFirst("/1", ""));
					  }
					  deviceBelongMapper.batchUpdate(map);
				  }
			  }
		  }
		  return DataCenterResult.ok();
	  }
	 
	 
	    /**
	      * 获取设备分配信息
	      */
		 @Override
		 public PageBean<DeviceBelong> fetchDistributionParamsByPage(AccountInfo accountInfoBean,Map<String, String> params) {
		        Map<String, Object> sqlMap = new HashMap<String,Object>();
		        
		        if(StringUtil.isNotEmpty(params.get("company_id"))){
		        	sqlMap.put("company_id", String.valueOf(params.get("company_id")));
		        }
		        if(StringUtil.isNotEmpty(params.get("device_id"))){
		        	sqlMap.put("device_id", params.get("device_id"));
		        }
		        if(StringUtil.isNotEmpty(params.get("device_level"))){
		        	sqlMap.put("device_level", params.get("device_level"));
		        }
		        if(StringUtil.isNotEmpty(params.get("greater_than_device_level"))){
		        	sqlMap.put("greater_than_device_level", params.get("greater_than_device_level"));
		        }
		        if(StringUtil.isNotEmpty(params.get("less_than_device_level"))){
		        	sqlMap.put("less_than_device_level", params.get("less_than_device_level"));
		        }
		        if(StringUtil.isNotEmpty(params.get("user_path"))){
		        	sqlMap.put("user_path", params.get("user_path"));
		        }
		        sqlMap.put(StaticCommad.OFFSET, Integer.parseInt(params.get(StaticCommad.OFFSET)));
		        sqlMap.put(StaticCommad.LIMIT, Integer.parseInt(params.get(StaticCommad.LIMIT)));

		        int count = deviceBelongMapper.count(sqlMap);
		        List<DeviceBelong> deviceList = deviceBelongMapper.fetchDistributionOfDeviceByPage(sqlMap);
		        return new PageBean<DeviceBelong>(count, deviceList);
		  }
	 
	 
	     /**
	      * 获取设备取消分配信息
	      */
		 @Override
		 public PageBean<DeviceBelong> fetchCancelParamsByPage(AccountInfo accountInfoBean,Map<String, String> params) {
		        Map<String, Object> sqlMap = new HashMap<String,Object>();
		        
		        if(StringUtil.isNotEmpty(params.get("company_id"))){
		        	sqlMap.put("company_id", String.valueOf(params.get("company_id")));
		        }
		        if(StringUtil.isNotEmpty(params.get("device_id"))){
		        	sqlMap.put("device_id", params.get("device_id"));
		        }
		        if(StringUtil.isNotEmpty(params.get("device_level"))){
		        	sqlMap.put("device_level", params.get("device_level"));
		        }
		        if(StringUtil.isNotEmpty(params.get("greater_than_device_level"))){
		        	sqlMap.put("greater_than_device_level", params.get("greater_than_device_level"));
		        }
		        if(StringUtil.isNotEmpty(params.get("less_than_device_level"))){
		        	sqlMap.put("less_than_device_level", params.get("less_than_device_level"));
		        }
		        if(StringUtil.isNotEmpty(params.get("user_path"))){
		        	sqlMap.put("user_path", params.get("user_path"));
		        }
		        sqlMap.put(StaticCommad.OFFSET, Integer.parseInt(params.get(StaticCommad.OFFSET)));
		        sqlMap.put(StaticCommad.LIMIT, Integer.parseInt(params.get(StaticCommad.LIMIT)));

		        int count = deviceBelongMapper.count(sqlMap);
		        List<DeviceBelong> deviceList = deviceBelongMapper.fetchDistributionOfDeviceByPage(sqlMap);
		        if(null!=deviceList &&  deviceList.size()>0){
		        	for(DeviceBelong bean:deviceList){
		        		Long userId = bean.getUser_id();
		        			Map<String,Object> userMap = new HashMap<String,Object>();
		        			userMap.put("id", userId);
		        			AccountInfo accountInfo = accountInfoMapper.fetchByUserInfo(userMap);
		        	}
		        }
		        return new PageBean<DeviceBelong>(count, deviceList);
		    }
	 
	 
	
		 /**
		   * 功能说明：取消批量分配设备
		   * @param params
		   * @return DataCenterResult
		  */
		  @Override
		  public DataCenterResult batchDel(Map<String, String> params){
			  String _ids = params.get("ids");
			  //查询当前经销商是否已分配设备给下级经销商
			  Map<String, Object> sqlMap = new HashMap<String,Object>();
			  if(StringUtil.isNotEmpty(params.get("company_id"))){
		        	sqlMap.put("company_id", String.valueOf(params.get("company_id")));
		        }
		        if(StringUtil.isNotEmpty(params.get("less_than_device_level"))){
		        	sqlMap.put("less_than_device_level", params.get("less_than_device_level"));
		        }
		        if(StringUtil.isNotEmpty(params.get("user_path"))){
		        	sqlMap.put("user_path", params.get("user_path"));
		        }
		        if(!StringUtil.isNull(_ids)){
		        	sqlMap.put("ids", StringUtil.getSpecialUtilJoinValue(_ids));
		        }
			  int count = deviceBelongMapper.count(sqlMap);
			  if(count>0){
				  return DataCenterResult.build(-1);
			  }
			  
			  List<DeviceBelong>   list = new ArrayList<DeviceBelong>();
			  if(StringUtil.isNotEmpty(_ids)){
				  String[] _id = _ids.split(",");
				  if(null!=_id && _id.length>0){
					  for(String id:_id){
						  DeviceBelong bean = new DeviceBelong();
						  bean.setId(Long.parseLong(id));
						  
						  //设置设备归属的经销商
						  DeviceBelong deviceBelong = deviceBelongMapper.fetchById(Long.parseLong(id));
						  if(null!=deviceBelong){
						     RedisUtil.set("MTL_DEV_USER_" + deviceBelong.getDevice_id(), "");
						  }
						  list.add(bean);
					  }
				  }
			  }
			  deviceBelongMapper.batchDel(list);
			  return DataCenterResult.ok();
		  }
	
		  
		  
		  /**
		   * 功能说明：为下级取消批量分配设备
		   * @param params
		   * @return DataCenterResult
		  */
		  @Override
		  public DataCenterResult subCancelAllocation(Map<String, String> params){
			  String _ids = params.get("ids");
			  
			  //查询当前经销商是否已分配设备给下级经销商
			  Map<String, Object> sqlMap = new HashMap<String,Object>();
			  if(StringUtil.isNotEmpty(params.get("company_id"))){
		        	sqlMap.put("company_id", String.valueOf(params.get("company_id")));
		        }
		        if(StringUtil.isNotEmpty(params.get("less_than_device_level"))){
		        	sqlMap.put("less_than_device_level", params.get("less_than_device_level"));
		        }
		        if(StringUtil.isNotEmpty(params.get("user_path"))){
		        	sqlMap.put("user_path", params.get("user_path"));
		        }
		        if(!StringUtil.isNull(_ids)){
		        	sqlMap.put("ids", StringUtil.getSpecialUtilJoinValue(_ids));
		        }
			  int count = deviceBelongMapper.count(sqlMap);
			  if(count>0){
				  return DataCenterResult.build(-1);
			  }
			  
			  if(StringUtil.isNotEmpty(_ids)){
				  String[] _id = _ids.split(",");
				  if(null!=_id && _id.length>0){
					  for(String id:_id){
						  Map<String,Object> map = new HashMap<String,Object>();
						  map.put("id", id);
						  map.put("user_path", params.get("user_path"));
						  map.put("device_level", Integer.parseInt(params.get("device_level").toString()));
						  
						  //设置设备归属的经销商
						  DeviceBelong deviceBelong = deviceBelongMapper.fetchById(Long.parseLong(id));
						  if(null!=deviceBelong){
						     RedisUtil.set("MTL_DEV_USER_" + deviceBelong.getDevice_id(), (params.get("user_path")).replaceFirst("/1", ""));
						  }
						  deviceBelongMapper.batchUpdate(map);
					  }
				  }
			  }
			  return DataCenterResult.ok();
		  }
	
	
		 public int countDeviceNum(String device_id,int device_level){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("device_id", device_id);
			map.put("greater_than_device_level", device_level);
			return deviceBelongMapper.count(map);
		 }
	
		 
		 public Integer batchDeviceSave(List<DeviceBelong>   list){
			 return deviceBelongMapper.batchSave(list);
		 }
		 
		 public DeviceBelong fecthDeviceBelongList(String deviceId,String user_path){
			 Map<String,Object>  map = new HashMap<String,Object>();
			 if(!StringUtil.isNull(deviceId)){
			    map.put("device_id", deviceId);
			 }
			 if(!StringUtil.isNull(user_path)){
				    map.put("user_path", user_path);
			 }
			 return deviceBelongMapper.fecthDeviceBelongList(map);
		 }
		 
		 
		 public Integer batchDeviceUpdate(Long id,Integer device_level,String user_path){
			 Map<String,Object>  map = new HashMap<String,Object>();
			 map.put("id", id);
			 map.put("device_level", device_level);
			 map.put("user_path", user_path);
			 return deviceBelongMapper.batchUpdate(map);
		 }
	
		 
		 
		 
		 /**
		   * 功能说明：一键迁移当前用户下的所有下级用户
		   * @param model
		   * @param params
		   */
		  public void setModuleSubUser(Model model,Map<String, Object> params){
			  List<AccountInfo> nList = new ArrayList<AccountInfo>();
			  if(!StringUtil.isNull(String.valueOf(params.get("company_id")))){
				  List<AccountInfo> list = accountInfoMapper.fetchUserByRoleTypeList(params);
				  if(null!=list){
					  for(AccountInfo bean:list){
						  if(!StringUtil.isNull(bean.getParent_userid()+"") && bean.getParent_userid().intValue()==0){
							  Map<String,Object> roleMap = new HashMap<String,Object>();
							  roleMap.put("id", bean.getRole_id());
							  Role role = roleMapper.fetchRoleById(roleMap);
							  if(null!=role){
								  int _nRoleType =  Integer.parseInt(String.valueOf(params.get("roleType")));   //当前用户的用户组类型
								  if(_nRoleType<role.getRole_type()){
									  nList.add(bean);
								  }
							  }
						  }
					  }
					  model.addAttribute("list", nList);
				  }
			  }
		  }
		  
		  
		  
		  /**
		   * 功能说明:通过bar_code 找到对应的MAC 地址
		   * @param bar_code
		   * @return OrderItemBean
		   */
		  @Override
		  public OrderItemBean fetchByBarCode(String bar_code){
			  Map<String,Object>  map = new HashMap<String,Object>();
			  map.put("bar_code", bar_code);
			  return orderItemMapper.fetchByBarCode(map);
		  }


		/**
		 * 设备分配记录分页
		 */
		@Override
		public PageBean<DeviceBelongBean> fetchByDeviceBelongPage(Map<String, String> params) {
			Map<String,Object> map = new HashMap<String, Object>();
			// 获取request
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
			// 通过工具类获取当前登录的bean
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			//获得当前用户角色对象
			Role role = roleMapper.fetchById(accountInfoBean.getRole_id());
			
			
//			Conds conds = new Conds();
//			//查询条件
			if(StringUtils.isNotEmpty(params.get("device_id"))){
				map.put("device_id",params.get("device_id"));
			}
			if(StringUtils.isNotEmpty(params.get("province_id"))){
				map.put("province_id",params.get("province_id"));
			}
			if(StringUtils.isNotEmpty(params.get("city_id"))){
				map.put("city_id",params.get("city_id"));
			}
			if(StringUtils.isNotEmpty(params.get("district_id"))){
				map.put("district_id",params.get("district_id"));
			}
//			
//			
			if (role.getRole_type()>1) {//判断非管理员
				map.put("user_id", accountInfoBean.getId());
//			}else if (role.getRole_type() == AccountInfo.ACCOUNT_TYPE_COMMON) {//厂商
//				conds.equal("a.company_id", role.getCompany_id());			
//				
//			}else if (role.getRole_type() == AccountInfo.ACCOUNT_TYPE_OTHER) {//代理商
//				List<AccountInfo> list = accountInfoService.fetchLowerDealerByUserInfo(accountInfoBean.getId().intValue(), role.getCompany_id());
//				List<String> str = new ArrayList<String>();
//				str.add(accountInfoBean.getId().toString());
//				if (list!=null && list.size()>0) {
//					for (AccountInfo accountInfo : list) {				
//						str.add(accountInfo.getId().toString());				
//					}
//				}			
//				Object[] strs = str.toArray();		
//				conds.in("a.user_id", strs);			
//			}else {
//				conds.equal("a.user_id", accountInfoBean.getId());		
			}else{
				map.put("company_id", accountInfoBean.getCompany_id());
			}
//			
//			map.put("conds", conds);
			map.put("offset", Integer.parseInt(params.get("offset")));
			map.put("limit", Integer.parseInt(params.get("limit")));
			List<DeviceBelongBean> beans = deviceBelongMapper.fetchByDeviceBelongPage(map);
			if(beans !=null && beans.size()>0){
				for(DeviceBelongBean bean :beans){
					AccountInfo ai = accountInfoMapper.fetchById(bean.getUser_id());
					bean.setCity_name(ai.getCity_name());
					bean.setProvince_name(ai.getProvince_name());
					bean.setDistrict_name(ai.getDistrict_name());
					bean.setUser_nickname(ai.getUser_nickname());
				}
			}
			return new PageBean<DeviceBelongBean>(deviceBelongMapper.countPage(map),beans);
		}
		  
		  
		  
	
	
	
}
