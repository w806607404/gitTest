package com.scinan.iot.ddeddo.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.ddeddo.dao.domain.DeviceBelong;
import com.scinan.iot.ddeddo.dao.domain.DeviceBelongBean;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：设备分配
 * @author vinson
 *
 */
public interface DeviceBelongMapper extends GenericMapper<DeviceBelong, Long> {

	
	 Integer batchSave(List<DeviceBelong>   list);
	 
	 List<DeviceBelong>  fetchDistributionOfDeviceByPage(Map<String,Object>  map);
	 
	 Integer batchDel(List<DeviceBelong>   list);
	 
	 Integer batchUpdate(Map<String,Object>  map);
	 
	 DeviceBelong fecthDeviceBelongList(Map<String,Object>  map);
	 
	 
	 DeviceBelong fetchById(Long id);
	 
	 List<DeviceBelong>  fetchDeviceByList(Map<String,Object>  map);
	 
	 
	 DeviceBelong fetchByDevice(Map<String,Object>  map);
	 
	 Integer updateRoomNumber(Map<String,Object>  map);
	 
	 /**
	  * 功能说明:根据user_id获取设备激活数量
	  * @param map
	  * @return
	  */
	 Integer countJoinByUserId(Long user_id);
	 
	 /**
	  * 功能说明:根据user_id获取设备日激活数量
	  * @param map
	  * @return
	  */
	 Integer countJoinDay(Long user_id);
	 
	 /**
	  * 功能说明:获取设备日绑定数量
	  * @param map
	  * @return
	  */
	 Integer countDay(Map<String,Object>  map);
	 
	 /**
	  * 功能说明:获取设备月绑定数量
	  * @param map
	  * @return
	  */
	 Integer countMouth(Map<String,Object>  map);
	 
	 /**
	  * 功能说明:获取当前用户自己的绑定量
	  * @param map
	  * @return
	  */
	 Integer countSelf(Map<String,Object>  map);
	 
	 /**
	  * 
	  * @Description: web端设备分配记录
	  * @param:描述1描述
	  * @return：返回结果描述
	  * @throws：异常描述
	  * @author: 吴广
	  * @date: 2018年7月11日 上午10:06:56
	  */
	 List<DeviceBelongBean> fetchByDeviceBelongPage(Map<String,Object>  map);
	 
	 /**
	  * 功能说明:获取设备分页量
	  * @param map
	  * @return
	  */
	 Integer countPage(Map<String,Object>  map);
	 
	 int deleteByUser_id(Long user_id);
}
