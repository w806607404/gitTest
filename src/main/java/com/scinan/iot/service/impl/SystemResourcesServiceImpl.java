package com.scinan.iot.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.scinan.base.cache.impl.RedisSimpleCacheServiceImpl;
import com.scinan.bean.DataCenterResult;
import com.scinan.iot.ddeddo.dao.ResourcesMapper;
import com.scinan.iot.ddeddo.dao.RoleMapper;
import com.scinan.iot.ddeddo.dao.RoleResourcesMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.Resource;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.ddeddo.dao.domain.RoleResources;
import com.scinan.iot.service.SystemResourcesService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;
import com.scinan.utils.JsonUtils;
import com.scinan.utils.MessageSourceUtil;
import com.scinan.utils.SystemResourcesUtil;
/**
 * 系统资源业务接口相关操作
 * @project datacenter
 * @class com.scinan.iot.service.impl.SystemResourcesServiceImpl
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月6日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("systemResourcesService")
public class SystemResourcesServiceImpl extends GenericServiceImpl<Resource, Long> implements SystemResourcesService {
	
	final static Logger logger = Logger.getLogger(SystemResourcesServiceImpl.class);

	@Autowired
	private RoleResourcesMapper vtlRoleResourcesMapper;
	@Autowired
	private RoleMapper vtlRoleMapper;
	@Autowired
	private ResourcesMapper vtlResourcesMapper;
	@Autowired
	private RedisSimpleCacheServiceImpl redisSimpleService;
	
	@Override
	protected GenericMapper<Resource, Long> getGenericMapper() {
		return vtlResourcesMapper;
	}

	public List<Resource> fetchSystemResourcesByRoleId(Long role_id,boolean flag) throws Exception {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("role_id", role_id);
			List<Resource> lists = vtlResourcesMapper.fetchSystemResourcesByRoleId(params);
			if(flag){
				lists = new SystemResourcesUtil().getChildTreeObjects(lists, 0);
			}
			return lists;
	}


	public void setIndexModel(Model model, HttpServletRequest request,Locale locale){
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		Map<String, Object> params = new HashMap<String, Object>();
	
		List<Resource> lists = null;
		if("1".equals(accountInfoBean.getRole_id()+"") &&  accountInfoBean.getRole_type()==AccountInfo.ACCOUNT_TYPE_ADMIN){
			lists = vtlResourcesMapper.fetchSystemResourcesBySupperRole(params);
		}else{
			params.clear();
			params.put("role_id", accountInfoBean.getRole_id());
			lists = vtlResourcesMapper.fetchSystemResourcesByRoleId(params);
		}
		
		lists = new SystemResourcesUtil().getChildTreeObjects(lists, 0);
		List<Resource> parentList = new ArrayList<Resource>();
		List<Resource> clidMenuList = new ArrayList<Resource>();
		//重新封装，实现菜单国际化
		for(int i=0;i<lists.size();i++){
			Resource bean = lists.get(i);
			bean.setRes_name(getMessageValue(bean.getRes_key(),locale));
			
			List<Resource> childList = bean.getNodes();
			if(null!=childList && childList.size()>0){
				for(int j=0;j<childList.size();j++){
					Resource childBean = childList.get(j);
					childBean.setRes_name(getMessageValue(childBean.getRes_key(),locale));
					clidMenuList.add(childBean);
				}
			}
			parentList.add(bean);
		}
		model.addAttribute("lists", parentList);
		model.addAttribute("account", accountInfoBean);
	}


	public void setResourcesModel(Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Resource> lists = vtlResourcesMapper.fetchSystemResourcesBySupperRole(map);
		SystemResourcesUtil treeUtil = new SystemResourcesUtil();
		List<Resource> ns = treeUtil.getChildTreeObjects(lists, 0);
		String json = JsonUtils.objectToJson(ns);
		json = json.replace("\"nodes\":[],", "");
		model.addAttribute("json", json);
	}


	/**
	 * 功能说明：功能权限设置
	 */
	public void setPermissionsModel(HttpServletRequest request,Model model,String role_id,Locale locale) throws Exception{
		
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		
		String roleType = request.getParameter("role_type");    //当前角色的类型
		
		if(StringUtils.isEmpty(role_id)){
			logger.error("setPermissionsModel params role_id is empty,account_name is " + accountInfoBean.getUser_name());
			return;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<Resource> lists = null;
		
		if(accountInfoBean.getRole_type() != AccountInfo.ACCOUNT_TYPE_ADMIN){//不是管理员
			params.put("role_id", accountInfoBean.getRole_id());
			lists= vtlResourcesMapper.fetchSystemResourcesByRoleId(params);
		}else{
			//硬编码过滤一些司南权限的通用功能
			if("3".equals(roleType)){
				//查询当前觉得所对应的上级角色ID
				Role vtlRole =  vtlRoleMapper.fetchById(Long.parseLong(role_id));
				if(null!=vtlRole && vtlRole.getParent_id()!=AccountInfo.ACCOUNT_TYPE_ADMIN){
					params.put("role_id", vtlRole.getParent_id());
					lists= vtlResourcesMapper.fetchSystemResourcesByRoleId(params);
				}else{
					params.clear();
					params.put("role_id", accountInfoBean.getRole_id());
					lists = vtlResourcesMapper.fetchByPage(params);
				}
			}else{
				params.clear();
				params.put("role_id", accountInfoBean.getRole_id());
				lists = vtlResourcesMapper.fetchByPage(params);
			}
		}
		
		List<Resource> resourcesBeanList = new ArrayList<Resource>();
		//List<SystemResourcesBean> beanNodesLists = new ArrayList<SystemResourcesBean>();
		lists = new SystemResourcesUtil().getChildTreeObjects(lists, 0);
		
		//childBean.setName();
		if(null!=lists && lists.size()>0){
			for(Resource bean:lists){
				bean.setRes_name(getMessageValue(bean.getRes_key(),locale));
				List<Resource> beanNodesList = bean.getNodes();
				if(null!=beanNodesList && beanNodesList.size()>0){
					for(int i=0;i<beanNodesList.size();i++){
						Resource childResourceBean = beanNodesList.get(i);
						childResourceBean.setRes_name(getMessageValue(childResourceBean.getRes_key(),locale));
						//按钮国际化
						List<Resource> btnResourceList = childResourceBean.getNodes();
						if(null!=btnResourceList && btnResourceList.size()>0){
							for(int j=0;j<btnResourceList.size();j++){
								Resource btnChildResourceBean = btnResourceList.get(j);
								btnChildResourceBean.setRes_name(getMessageValue(btnChildResourceBean.getRes_key(),locale));
							}
						}
					}
				}
				//bean.setNodes(beanNodesLists);
				resourcesBeanList.add(bean);
			}
		}
		model.addAttribute("permissions", resourcesBeanList);
	}

	
	/**
	 * 添加该角色所归属的功能菜单
	 */
	public DataCenterResult addRoleRes(HttpServletRequest request,String role_id, String[] resIdArr) {
		
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		
		//+++++++++++++++验证提交角色id+++++++++++++++++++++++
		String roleId = ""+accountInfoBean.getRole_id();//父角色id
		Map<String, Object> params = new HashMap<String, Object>();		
		
		/*Conds conds = new Conds();
		conds.equal("creator_role_id", roleId);//父角色id
		conds.equal("id", role_id);
		
		params.put("conds", conds);
		List<RoleBean> roleBeans = roleMapper.fetchByPage(params);
		if(null == roleBeans || roleBeans.size() != 1){
			logger.error("setPermissionsModel fetchByPage is empty or size > 1,operation account is " + accountInfoBean.getUser_name());
			return DataCenterResult.build(500);
		}
		params.clear();*/
		//+++++++++++++++验证提交角色id+++++++++++++++++++++++
		
		List<RoleResources> roleResBeans = new ArrayList<RoleResources>();
		
		/***********角色关联多个资源id,先删除后保存*********/
		vtlRoleResourcesMapper.deleteByRoleId(Long.valueOf(role_id));
		if(null != resIdArr && resIdArr.length > 0){
			
			//+++++++++++++++验证菜单资源+++++++++++++++++++++++
			params.clear();
			Conds conds = new Conds();
			conds.equal("role_id", roleId);
			conds.in("res_id", resIdArr);
			params.put("conds", conds);
			List<RoleResources> roleResList = vtlRoleResourcesMapper.fetchByPage(params);
			if(null == roleResList/* || roleResList.size() != resIdArr.length*/){
				logger.error("addRoleRes is error,operation account is " + accountInfoBean.getUser_name());
				return DataCenterResult.build(500);
			}
			//+++++++++++++++验证菜单资源+++++++++++++++++++++++
			
			for (String res_id : resIdArr) {
				RoleResources roleResBean = new RoleResources();
				roleResBean.setRes_id(res_id);
				roleResBean.setRole_id(Integer.valueOf(role_id));
				roleResBeans.add(roleResBean);
			}
			vtlRoleResourcesMapper.batchSave(roleResBeans);
			
			//List<String> list = systemResourcesMapper.fetchResourcesUrlByRoleResBeans(roleResBeans);
			//redisSimpleService.set(CacheConstantBase.TIME_FOREVER, "ROLE_" + role_id, JsonUtils.objectToJson(list));
		}
		return DataCenterResult.ok();
	}
	
	/**
	 * 功能说明：获取国际化内容
	 * @param key
	 * @return
	 */
	private String getMessageValue(String key,Locale  locale) { 
		 // 获取request
        return MessageSourceUtil.getApplicationContext().getMessage(key, null, locale);  
    }

	 

	
}
