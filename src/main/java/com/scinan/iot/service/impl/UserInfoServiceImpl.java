package com.scinan.iot.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scinan.iot.s8000log.dao.DeviceStartTimeLogMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.log.SysoLogger;
import com.scinan.bean.PageBean;
import com.scinan.constants.Constants;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.s1000.dao.UserInfoMapper;
import com.scinan.iot.s1000.dao.domain.UserInfo;
import com.scinan.iot.s6000.dao.DeviceUserMapper;
import com.scinan.iot.s6000.dao.domain.DeviceUser;
import com.scinan.iot.s9000.dao.DeviceOnceOnlineMapper;
import com.scinan.iot.service.UserInfoService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.RedisUtil;
/**
 * 
 * 
 * @project datacenter
 * @class com.scinan.iot.service.impl.AccountInfoServiceImpl
 * @copyright www.scinan.com
 * @author Kim
 * @date 2017年03月28日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("userInfoService")
public class UserInfoServiceImpl extends GenericServiceImpl<UserInfo, Long> implements UserInfoService {
	static Logger logger = Logger.getLogger(UserInfoServiceImpl.class);
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private DeviceUserMapper deviceUserMapper;
	@Autowired
	private DeviceStartTimeLogMapper deviceStartTimeLogMapper;
	
	@Override
	protected GenericMapper<UserInfo, Long> getGenericMapper() {
		return userInfoMapper;
	}


	@Override
	public PageBean<UserInfo> getUserListBySource(AccountInfo accountInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Conds conds = new Conds();
		conds.equal("source", accountInfo.getCompany_id());
		map.put("conds", conds);
		List<UserInfo> uis = userInfoMapper.fetchByPage(map);
		List<UserInfo> list = new ArrayList<UserInfo>();
		if(uis != null && uis.size()>0){
			for(UserInfo bean : uis){
				//获取设备用户关联信息（根据主授权0信息）
            	List<DeviceUser> deviceUsers = deviceUserMapper.fetchByMstype(bean.getId(), 0);
            	 if(deviceUsers != null && deviceUsers.size() > 0){
            		 //获取分到的红包金额
            		 Integer deviceUsedTimesMouth = 0;
            		 String redEnvelopeAmount = RedisUtil.get(bean.getId()+"_" + Constants.DEDO_REDENVELOPE_AMOUNT);
            		 bean.setDeviceUsedTimesMouth(deviceUsedTimesMouth);//先扔进去防止空指针
            		 bean.setRedEnvelopeAmount(new BigDecimal(redEnvelopeAmount == null ? "0" :redEnvelopeAmount));
				for(DeviceUser deviceUser : deviceUsers){
					map.clear();
					map.put("device_id", deviceUser.getDevice_id());
					//该设备的当月所用次数
             		deviceUsedTimesMouth += deviceStartTimeLogMapper.countByMouth(map);
             	}
				bean.setDeviceUsedTimesMouth(deviceUsedTimesMouth);
             	
             	//自己的信息单独再统计一遍
//             	if(user_id.equals(userInfo.getId())){
//             		ownInfo.setDeviceUsedTimesMouth(deviceUsedTimesMouth);
//             		ownInfo.setRedEnvelopeAmount(new BigDecimal(StringUtil.isEmpty(redEnvelopeAmount) ? "0" :redEnvelopeAmount));
//             	}
				list.add(bean);
             }
        }
	}
		this.sort(list);//使用次数由大到小排序
		 List<UserInfo> newList = new ArrayList<UserInfo>();
         if(list.size()>=10){
   		  newList = list.subList(0, 10);
   	   }else{
   		  newList = list;
   	   }
        for(int i=0; i<newList.size(); i++ ){
        	newList.get(i).setCur_ranking(i+1);
        }
		return new PageBean<UserInfo>(newList.size(),newList);
	}
	
	 //根据使用次数/销量排序
    @SuppressWarnings("unchecked")
	private  void sort(List<UserInfo> list){
    	Collections.sort(list, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
					UserInfo userInfo1 = (UserInfo) o1;
					UserInfo userInfo2 = (UserInfo) o2;
					if(userInfo1.getDeviceUsedTimesMouth() < userInfo2.getDeviceUsedTimesMouth()){
						return 1;
					}else if(userInfo1.getDeviceUsedTimesMouth() == userInfo2.getDeviceUsedTimesMouth()){
						return 0;
					}else{
						return -1;
					}
				}
		});
    }
    
    public static void main(String[] args) {
    	 List<UserInfo> list = new ArrayList<UserInfo>();
  	   UserInfo u1 = new UserInfo();
  	   u1.setDeviceUsedTimesMouth(10);
  	   UserInfo u2 = new UserInfo();
  	   u2.setDeviceUsedTimesMouth(11);
  	   UserInfo u3 = new UserInfo();
  	   u3.setDeviceUsedTimesMouth(11);
  	   UserInfo u4 = new UserInfo();
  	   u4.setDeviceUsedTimesMouth(8);
  	   list.add(u1);
  	   list.add(u2);
  	   list.add(u3);
  	   list.add(u4);
//  	   sort(list);
  	   System.out.println(list.size());
	}
}
