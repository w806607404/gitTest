/**
 * @Description:
 * @Package: com.scinan.iot.service.impl 
 * @author: 吴广   
 * @date: 2018年7月31日 下午3:34:43 
 */
package com.scinan.iot.service.impl;

import java.util.HashMap;
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
import com.scinan.iot.ddeddo.dao.NotifySendMapper;
import com.scinan.iot.ddeddo.dao.RoleMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.NotifySend;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.DeviceTypeService;
import com.scinan.iot.service.MsgPushService;
import com.scinan.iot.service.NotifySendService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;

/**
 * @Description: 该类的功能描述
 * @author: 吴广
 * @date: 2018年7月31日 下午3:34:43 
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("notifySendService")
public class NotifySendServiceImpl extends GenericServiceImpl<NotifySend, Long> implements
		NotifySendService {
	static Logger logger = Logger.getLogger(NotifySendServiceImpl.class);
	
	@Autowired
	private NotifySendMapper notifySendMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	@Autowired
	private MsgPushService msgPushService;
	@Autowired
	private DeviceTypeService deviceTypeService;
	
	@Override
	protected GenericMapper<NotifySend, Long> getGenericMapper() {		
		return notifySendMapper;
	}
	
	/**
	 * 发货通知记录分页
	 */
	@Override
	public PageBean<NotifySend> fetchParamsByPage(Map<String, String> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		//获得当前用户角色对象
		Role role = roleMapper.fetchById(accountInfoBean.getRole_id());
		
		Conds conds = new Conds();
		//查询条件
		if(StringUtils.isNotEmpty(params.get("user_nickname"))){
			conds.like("b2.agent_name",params.get("user_nickname"));
		}
		if(StringUtils.isNotEmpty(params.get("status"))){
			conds.equal("a.status",params.get("status"));
		}
		
		if (role.getRole_type()<1 && role.getParent_id()==AccountInfo.ACCOUNT_TYPE_ADMIN) {
			
		}else if (role.getRole_type()==AccountInfo.ACCOUNT_TYPE_COMMON) {//判断为厂商
			
			conds.equal("receive_userId", accountInfoBean.getId());
			conds.equal_or("send_userId", accountInfoBean.getId());			
		}else if (role.getRole_type()==AccountInfo.ACCOUNT_TYPE_OTHER) {//代理商
			
			conds.equal("receive_userId", accountInfoBean.getId());
			conds.equal_or("send_userId", accountInfoBean.getId());
		}else {//经销商
			conds.equal("receive_userId", accountInfoBean.getId());		
		}
		if(StringUtils.isNotEmpty(params.get("status"))){
			conds.equal("a.status",params.get("status"));
		}
		if(StringUtils.isNotEmpty(params.get("user_nickname"))){
			conds.like("b2.agent_name",params.get("user_nickname"));
		}
		map.put("conds", conds);
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		
		
		return new PageBean<NotifySend>(notifySendMapper.count(map),notifySendMapper.fetchByPage(map));
	}

	/**
	 * 删除发货通知记录
	 */
	@Override
	public DataCenterResult deleteNotifySend(Long id) throws Exception {
		notifySendMapper.delete(id);
		return DataCenterResult.ok();
	}

	/**
	 * 添加发货通知记录
	 */
	@Override
	public DataCenterResult addNotifySend(NotifySend notifySend)
			throws Exception {
		AccountInfo accountInfo = accountInfoService.fetchById(notifySend.getSend_userId());
		//获得当前用户角色对象
		Role role = roleMapper.fetchById(accountInfo.getRole_id());
		if (role.getRole_type()==AccountInfo.ACCOUNT_TYPE_COMMON) {//判断为厂商角色
			Conds conds = new Conds();
			conds.equal("province_id", notifySend.getProvince_id());
			conds.equal("city_id", notifySend.getCity_id());
			conds.equal("district_id", notifySend.getDistrict_id());
			conds.equal("role_id", 3);
			conds.equal("status", 1);
			//获取代理商信息
			AccountInfo aInfo = accountInfoService.fetchByConds(conds);
			notifySend.setReceive_userId(aInfo.getId());
		}
		notifySend.setDevice_type(deviceTypeService.fetchById(new Long(notifySend.getDevice_type())).getType());  
		notifySendMapper.save(notifySend);
		//正常通知
		if(notifySend.getNotify_type() == 1){
			//下级通知上级
			if(accountInfo.getParent_user_id().equals(notifySend.getReceive_userId())){
				 //进行验证
	   			 if(accountInfo.getSubsidy_amount().compareTo(notifySend.getAmount()) == -1){
	   				 return DataCenterResult.build(-103);
	   			 }
	   			 //扣钱了。。。
	   			 accountInfo.setSubsidy_amount(accountInfo.getSubsidy_amount().subtract(notifySend.getAmount()));
	   			 accountInfoService.update(accountInfo);
			}
		}
		msgPushService.msgPush(1, accountInfoMapper.fetchById(notifySend.getReceive_userId()).getUser_info_id()+"");
		return DataCenterResult.ok();
	}

	/**
	 * 修改发货通知记录
	 */
	@Override
	public DataCenterResult updateNotifySend(NotifySend notifySend)
			throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		AccountInfo aInfo = Common.getAccountInfo(request);
		AccountInfo bean = accountInfoService.fetchById(aInfo.getId());
		//完成时对应扣除和补贴金额
		if(notifySend.getStatus() == 2 && notifySend.getNotify_type()== 1){
			//下级(我)通知上级发货，扣除补下级（我）贴金额
			if (bean.getParent_user_id().equals(notifySend.getReceive_userId())) {
//				if (bean.getSubsidy_amount().compareTo(notifySend.getAmount()) == -1) {
//					return DataCenterResult.build(-102);
//				}
//				bean.setSubsidy_amount(bean.getSubsidy_amount().subtract(notifySend.getAmount()));
//				//更新余额
//				accountInfoService.updateAccountInfo(bean);
			}else {//上级（我）通知下级发货，增加下级金额
				AccountInfo accountInfo = accountInfoService.fetchById(notifySend.getReceive_userId());
				accountInfo.setSubsidy_amount(accountInfo.getSubsidy_amount().add(notifySend.getAmount()));
				accountInfoService.updateAccountInfo(accountInfo);
			}
		}
		
		//缺货
		if(notifySend.getStatus() == 5 && notifySend.getNotify_type()== 1){
			//通知人信息
			AccountInfo sendUserInfo = accountInfoService.fetchById(notifySend.getSend_userId());
			//下级通知上级
			if(sendUserInfo.getParent_user_id().equals(notifySend.getReceive_userId())){
				//补还扣除的金额
				sendUserInfo.setSubsidy_amount(sendUserInfo.getSubsidy_amount().add(notifySend.getAmount()));
				accountInfoService.update(sendUserInfo);
			}
		}
		notifySendMapper.update(notifySend);
		return DataCenterResult.ok();
	}

	/**
	 * 
	 * 
	 */
	@Override
	public DataCenterResult updateNotifySendStatus(NotifySend notifySend)
			throws Exception {
		notifySendMapper.update(notifySend);
		return DataCenterResult.ok();
	}

	@Override
	public Integer countByTransfer(Long user_id) {
		return notifySendMapper.countByTransfer(user_id);
	}

	

	

}
