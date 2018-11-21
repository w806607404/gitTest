package com.scinan.iot.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scinan.base.cache.impl.RedisSimpleCacheServiceImpl;
import com.scinan.base.constant.CacheConstantBase;
import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.AccountBackMapper;
import com.scinan.iot.ddeddo.dao.AccountBillDetailMapper;
import com.scinan.iot.ddeddo.dao.AccountInfoMapper;
import com.scinan.iot.ddeddo.dao.AccountRepealMapper;
import com.scinan.iot.ddeddo.dao.AccountSoldMapper;
import com.scinan.iot.ddeddo.dao.BillCloseDetailMapper;
import com.scinan.iot.ddeddo.dao.DeviceBelongMapper;
import com.scinan.iot.ddeddo.dao.LoginLogMapper;
import com.scinan.iot.ddeddo.dao.NotifySendMapper;
import com.scinan.iot.ddeddo.dao.PurchaseMapper;
import com.scinan.iot.ddeddo.dao.RoleMapper;
import com.scinan.iot.ddeddo.dao.UserBackMapper;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.AccountRepeal;
import com.scinan.iot.ddeddo.dao.domain.LoginLogBean;
import com.scinan.iot.ddeddo.dao.domain.Role;
import com.scinan.iot.s1000.dao.UserInfoMapper;
import com.scinan.iot.s1000.dao.domain.UserInfo;
import com.scinan.iot.s6000.dao.DeviceInfoMapper;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.mybatis.support.mapper.GenericMapper;
import com.scinan.mybatis.support.service.impl.GenericServiceImpl;
import com.scinan.mybatis.support.sql.Conds;
import com.scinan.utils.Common;
import com.scinan.utils.CookieUtils;
import com.scinan.utils.IpAddressUtil;
import com.scinan.utils.JsonUtils;
import com.scinan.utils.MD5HashUtil;
import com.scinan.utils.RedisUtil;
import com.scinan.utils.SC;
import com.scinan.utils.StringUtil;

/**
 * 
 * 
 * @project ddeddo
 * @class com.scinan.iot.service.impl.AccountInfoServiceImpl
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月06日
 * @description
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("accountInfoService")
public class AccountInfoServiceImpl extends GenericServiceImpl<AccountInfo, Long> implements AccountInfoService {
	static Logger logger = Logger.getLogger(AccountInfoServiceImpl.class);
	
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	@Autowired
	private RoleMapper vtlRoleMapper;
	@Autowired
	private RedisSimpleCacheServiceImpl redisSimpleService;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private LoginLogMapper loginLogMapper;
	@Autowired
	private DeviceInfoMapper deviceInfoMapper;
	@Autowired
	private AccountSoldMapper accountSoldMapper;
	@Autowired
	private DeviceBelongMapper deviceBelongMapper;
	@Autowired
	private AccountRepealMapper accountRepealMapper;
	@Autowired
	private PurchaseMapper purchaseMapper;
	@Autowired
	private NotifySendMapper notifySendMapper;
	@Autowired
	private AccountBackMapper accountBackMapper;
	@Autowired
	private AccountBillDetailMapper accountBillDetailMapper;
	@Autowired
	private BillCloseDetailMapper billCloseDetailMapper;
	@Autowired
	private UserBackMapper userBackMapper;
	
	@Override
	protected GenericMapper<AccountInfo, Long> getGenericMapper() {
		return accountInfoMapper;
	}

	
	/**
	 * 功能说明:用户登陆入口
	 */
	public DataCenterResult userLogin(String loginName, String loginPwd,String verifyCode, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String loginIp = IpAddressUtil.getIpAddress(request);
		HttpSession session= request.getSession();
		
		//用戶名或密码不可为空
		if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPwd)){
			return DataCenterResult.build(10015);
		}
		//验证码不可为空
		if(StringUtils.isEmpty(verifyCode)){
			return DataCenterResult.build(10010);
		}
		
		String sessionCode = (String)session.getAttribute("dc_sessionSecCode");
		//验证码错误
		if(!verifyCode.equalsIgnoreCase(sessionCode)){
			return DataCenterResult.build(10011);
		}

		/**********查询该用户是否存在*********/
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_name", loginName.trim());
		AccountInfo accountBean = accountInfoMapper.fetchByUserInfo(params);
		Role vtlRoleBean = null;
		//如果没有此用户名
		if (null == accountBean) {
			return DataCenterResult.build(10012);//该用户不存在
		}else{
			params.clear();
			//查询当前用户所归属的角色信息
			if(null!=accountBean){
				vtlRoleBean = vtlRoleMapper.fetchById(accountBean.getRole_id());
			}
		}
		
		String inPwd =  MD5HashUtil.generatePassword(MD5HashUtil.getInstance().hashCode(loginPwd.trim()));
		logger.info("User Sign in loginName = "+loginName +",loginPwd = "+inPwd + ",user_pwd = "+accountBean.getUser_password());
		
		//比对密码
		if (!inPwd.equals(accountBean.getUser_password())) {
			//是否需要验证登录
			return DataCenterResult.build(10015);//用戶名或密碼錯誤
		}
		
		if(accountBean.getStatus().intValue()==0){//无效的用户
			return DataCenterResult.build(10014);//无效的用户
		}
		
		if(accountBean.getStatus().intValue()==AccountInfo.ACCOUNT_STATUS_INAUDIT || accountBean.getStatus().intValue()==AccountInfo.ACCOUNT_STATUS_APPOINTMENT){//审核、预约中
			return DataCenterResult.build(10016);//审核、预约中的用户
		}
		
		//添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效。
		String token = MD5HashUtil.generatePassword(SC.VTL_SYSTEM_USER_TOKEN+loginName);
		//用户登录之后，先清掉Redis中存的用户信息
		RedisUtil.remove("REDIS_USER_SESSION_" + token);
		
		/**************把用户信息写入redis************/
		AccountInfo accountInfoBean =  getAccountInfoBean(accountBean,vtlRoleBean);
		RedisUtil.set(CacheConstantBase.TIME_HALFDAY,"REDIS_USER_SESSION_" + token, JsonUtils.objectToJson(accountInfoBean));
		/**************把用户信息写入redis************/
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//使用request对象的getSession()获取session，如果session不存在则创建一个
		//request.getSession().setAttribute("JIEROU_DC_TOKEN", token);
		javax.servlet.http.Cookie cc=new javax.servlet.http.Cookie("DISTRIBUTION_DC_TOKEN",token);
		cc.setMaxAge(60*24*60);   //一天时间
		cc.setPath("/");
		response.addCookie(cc);
		//设置session
		/***************************************************/
		String userAgent = request.getHeader("User-Agent");
		new ProcessLoginLogThread(accountBean.getId(),loginIp,userAgent).start();
		
		return DataCenterResult.ok();
	}
	
	
	/**
	 * 功能说明：添加账户信息
	 */
    public DataCenterResult addAccount(HttpServletRequest request,AccountInfo accountInfoBean,Map<String, String> params) throws Exception {
		
		AccountInfo accountInfo = Common.getAccountInfo(request);
		                                                             //用户ID
		String user_nickname = StringUtil.isValidateStr(params.get("user_nickname"));      //用户昵称
		String user_name = StringUtil.isValidateStr(params.get("user_name"));            //用户账号
		String status = StringUtil.isValidateStr(params.get("status"));                   //用户状态
		String note = StringUtil.isValidateStr(params.get("note"));                       //备注
		String agent_name = StringUtil.isValidateStr(params.get("agent_name"));                   //代理商名称
		String agent_phone = StringUtil.isValidateStr(params.get("agent_phone"));                 //代理商电话

		/*********************提交表单映射对象非空判断******************/
		if(StringUtil.isNull(user_nickname)){
			logger.error("user_nickname : " + user_nickname + "   addAccount accountInfoBean is empty");
			return DataCenterResult.build(500);
		}
		
		if(StringUtil.isNull(user_name)){
			logger.error("user_name : " + user_name + "   addAccount accountInfoBean is empty");
			return DataCenterResult.build(500);
		}
		
		/*********************提交表单映射对象非空判断******************/
		
		/***********查询当前帐号所对应的用户信息是否存在********/
		Map<String,Object> userNameMap = new HashMap<String,Object>();
		userNameMap.put("user_name", user_name);
		AccountInfo userInfo = accountInfoMapper.fetchByUserInfo(userNameMap);
		if(null!=userInfo){//说明该用户已经存在
			return DataCenterResult.build(-101);
		}
		/***********查询当前帐号所对应的用户信息是否存在********/
		
		/**********获取当前用户的角色****************/
		Map<String,Object> roleMap = new HashMap<String,Object>();
		roleMap.put("id", accountInfoBean.getRole_id());
		Role roleBean = vtlRoleMapper.fetchRoleById(roleMap);
		
		/**************保存用户扩展信息*************/
		AccountInfo accountBean = new AccountInfo();
		if(null!=roleBean){
			accountBean.setRole_id(roleBean.getId());
		}else{
			return  DataCenterResult.build(-2);
		}
		
		/***********伯央的度--》查询当前帐号所对应的区域的用户信息是否存在********/
		if(roleBean.getRole_type() == 2){  //添加代理商伯央的度进行区域验证
			String restult = validArea(params,accountBean);
			if("500".equals(restult)){
				return DataCenterResult.build(500);
			}else if("-102".equals(restult)){
				return DataCenterResult.build(-102);
			}
		}
		/***********伯央的度--》查询当前帐号所对应的区域的用户信息是否存在********/		
		String initPwd = "123456";//StringUtil.generate(6);
		String user_password = MD5HashUtil.generatePassword(MD5HashUtil.getInstance().hashCode(initPwd));     //用户密码
		
		//验证账号是否存在
		UserInfo userInfoBase = userInfoMapper.fetchNickNameAndImgByUserMobile(user_name);
		if(userInfoBase != null){//账号已在user_info表存在
			accountBean.setUser_info_id(userInfoBase.getId());
			userInfoBase.setUser_passwd(user_password);
			userInfoMapper.update(userInfoBase);
		}else{//新账号
			UserInfo userInfoBean = new UserInfo();
			Long user_digit = Common.generate_user_digit();
			userInfoBean.setUser_digit(user_digit);
			userInfoBean.setUser_nickname(user_name);//user_info表user_nickname是账号
			userInfoBean.setUser_passwd(user_password);
			userInfoBean.setUser_mobile(user_name);
			userInfoBean.setArea_code("86");
			userInfoBean.setUser_level(new Short((short) 1));
			userInfoBean.setUser_type(new Short((short) 2));
			userInfoBean.setSource(roleBean.getCompany_id());
			userInfoBean.setCreate_time(new Date());
			userInfoMapper.save(userInfoBean);
			accountBean.setUser_info_id(userInfoBean.getId());
		}
		
		accountBean.setStatus(Integer.parseInt(status));
		accountBean.setInit_pwd(initPwd);    //用户初始化密码
		accountBean.setUser_password(user_password);
		accountBean.setUser_name(user_name);
		accountBean.setUser_nickname(user_nickname);
		accountBean.setCompany_id(roleBean.getCompany_id());
		accountBean.setNote(note);
		accountBean.setCreate_time(new Date());
		accountBean.setAgent_name(agent_name);
		accountBean.setAgent_phone(agent_phone);
		
		Long parentUserId = accountInfo.getParent_user_id();
		if(StringUtil.isNull(String.valueOf(parentUserId))){
			parentUserId = 0l;
		}
		accountBean.setParent_user_id(parentUserId);
		String parentUserPath = accountInfo.getParent_user_path();
		if(StringUtil.isNull(parentUserPath)){
			parentUserPath = "/";
		}else{
			if(null!=roleBean){
				if(roleBean.getParent_userid().intValue()>0){
					parentUserPath = accountInfo.getParent_user_path().replace("/"+accountInfo.getId(), "");
				}else{
					parentUserPath = accountInfo.getParent_user_path();
				}
			}
		}
		accountBean.setParent_user_path(parentUserPath);
		accountInfoMapper.save(accountBean);
		/**************保存用户扩展信息*************/
		
		return DataCenterResult.ok();
	}
	
	
	
    /**
	 * 功能说明：更新账户信息
	 */
    public DataCenterResult updateAccount(HttpServletRequest request,AccountInfo accountInfoBean,Map<String, String> params) throws Exception {
		
		AccountInfo accountInfo = Common.getAccountInfo(request);
		String id = StringUtil.isValidateStr(params.get("id"));     		//用户ID
		String user_nickname = StringUtil.isValidateStr(params.get("user_nickname"));      //账号
		String status = StringUtil.isValidateStr(params.get("status"));                   //用户状态
		String note = StringUtil.isValidateStr(params.get("note"));                       //备注  
		String agent_name = StringUtil.isValidateStr(params.get("agent_name"));                   //代理商名称
		String agent_phone = StringUtil.isValidateStr(params.get("agent_phone"));  

		/*********************提交表单映射对象非空判断******************/
//		if(StringUtil.isNull(user_nickname)){
//			logger.error("user_nickname : " + user_nickname + "   addAccount accountInfoBean is empty");
//			return DataCenterResult.build(500);
//		}
//		
		/*if(StringUtil.isNull(current_ratio)){
			logger.error("current_ratio : " + current_ratio + "   addAccount accountInfoBean is empty");
			return DataCenterResult.build(500);
		}*/
		/*********************提交表单映射对象非空判断******************/

		AccountInfo accountBean = new AccountInfo();
		/***********查询当前用户是否存在**************************/
		Map<String, Object> accountInfoParams = new HashMap<String, Object>();
		accountInfoParams.put("id", id);
		AccountInfo accountInfoExits = accountInfoMapper.fetchByUserInfo(accountInfoParams);
		if(null == accountInfoExits){
			logger.error("account : " + id + "   addAccount fetchById  VtlAccountInfo is empty, user_id is "  + id);
			return DataCenterResult.build(-301);
		}
		if(null != accountInfoMapper.getUserInfoByUserNickname(user_nickname)){
			if(!accountInfoMapper.getUserInfoByUserNickname(user_nickname).getUser_nickname().equals(user_nickname)){
				logger.error("account : " + id + "   addAccount fetchById  VtlAccountInfo is empty, user_id is "  + id);
				return DataCenterResult.build(-101);
			}
		}
		if(!StringUtil.isNull(status)){
			//开启账户
			if(Integer.parseInt(status) == 1 && accountInfoExits.getRole_type() == 2){
				accountInfoParams.put("district_id", accountInfoExits.getDistrict_id());
				accountInfoParams.put("role_type", 2);
				accountInfoParams.put("cur_user_id", accountInfoExits.getId());
				List<AccountInfo> userAreaInfo = accountInfoMapper.fetchUserInfoBase(accountInfoParams);
				if(userAreaInfo.size()>0 && userAreaInfo != null){//说明该区域已有用户存在
					return DataCenterResult.build(-102);
				}
			}
			
			//代理商通过审核
			if(Integer.parseInt(status) == 5 && accountInfoExits.getRole_type() == 2){
				accountInfoParams.put("district_id", accountInfoExits.getDistrict_id());
				accountInfoParams.put("role_type", 2);
				accountInfoParams.put("cur_user_id", accountInfoExits.getId());
				List<AccountInfo> userAreaInfo = accountInfoMapper.fetchUserInfoByAgent(accountInfoParams);
				if(userAreaInfo.size()>0 && userAreaInfo != null){//说明该区域已有用户存在
					return DataCenterResult.build(-102);
				}
			}
			
			//经销商通过审核
			if(Integer.parseInt(status) == 5 && accountInfoExits.getRole_type() == 3){
				//上级信息
				AccountInfo agent = accountInfoMapper.fetchById(accountInfoExits.getParent_user_id());
				if(agent == null){
					return DataCenterResult.build(-104);
				}
				//上级还在审核中或者预约状态
				if(agent.getStatus() == 2 || agent.getStatus() == 4){
					return DataCenterResult.build(-103);
				}

				//上级没有通过审核
				if(agent.getStatus()==3){
					if(null != accountInfoMapper.getUserInfoByDistrict_id(agent.getDistrict_id())){
						return DataCenterResult.build(-104);
					}
					//赋值该经销商为代理商
					accountBean.setRole_id(agent.getRole_id());
					accountBean.setParent_user_id(agent.getParent_user_id());
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("parent_user_id", agent.getId());
					map.put("company_id", "10D5");
					List<AccountInfo> pDealerList = accountInfoMapper.fetchLowerDealerByAudit(map);;
					//如果名下有经销商
					if(null!=pDealerList && pDealerList.size()>1){
						for(AccountInfo bean:pDealerList){
							//排除当前经销商,迁移所有经销商到该用户名下
							if(!bean.getId().equals(accountInfoExits.getId())){
								AccountInfo nAccountInfo = new AccountInfo();
								nAccountInfo.setId(bean.getId());
								nAccountInfo.setParent_user_id(accountInfoExits.getId());
								accountInfoMapper.update(nAccountInfo);
							}
						}
					}
				}
			}

		}

		
		/**************修改用户信息*************/
		
		accountBean.setId(Long.parseLong(id));
		if(StringUtil.isNotEmpty(user_nickname)){
			accountBean.setUser_nickname(user_nickname);
		}
		if(StringUtil.isNotEmpty(status)){
			accountBean.setStatus(Integer.parseInt(status));
		}
		accountBean.setNote(note);
		if(StringUtil.isNotEmpty(agent_name)){
			accountBean.setAgent_name(agent_name);
		}
		if(StringUtil.isNotEmpty(agent_phone)){
			accountBean.setAgent_phone(agent_phone);
		}
		accountBean.setUpdate_time(new Date());
//		accountBean.setProvince_id(province_id);
//		accountBean.setProvince_name(province_name);
//		accountBean.setCity_id(city_id);
//		accountBean.setCity_name(city_name);
//		accountBean.setDistrict_id(district_id);
//		accountBean.setDistrict_name(district_name);
		accountInfoMapper.update(accountBean);
		/**************修改用户信息*************/
		
		return DataCenterResult.ok();
	}
    
    /**
     *伯央的度用户区域验证
     *return 500:参数为空 ,-102:用户已存在, 200:正常
     *create by vinson 
     */
    public String validArea(Map<String,String> params,AccountInfo bean){
    	String province_id = StringUtil.isValidateStr(params.get("province_id"));
		String city_id = StringUtil.isValidateStr(params.get("city_id"));
		String district_id = StringUtil.isValidateStr(params.get("district_id"));
		String province_name = StringUtil.isValidateStr(params.get("province_name"));
		String city_name = StringUtil.isValidateStr(params.get("city_name"));
		String district_name = StringUtil.isValidateStr(params.get("district_name"));
		
		if(StringUtil.isNull(province_id)){
			logger.error("province_id : " + province_id + "   addAccount accountInfoBean is empty");
			return "500";
		}
		if(StringUtil.isNull(province_name)){
			logger.error("province_name : " + province_name + "   addAccount accountInfoBean is empty");
			return "500";
		}
		if(StringUtil.isNull(district_id)){
			logger.error("district_id : " + district_id + "   addAccount accountInfoBean is empty");
			return "500";
		}
		if(StringUtil.isNull(district_name)){
			logger.error("district_name : " + district_name + "   addAccount accountInfoBean is empty");
			return "500";
		}
		Map<String,Object> userAreaMap = new HashMap<String,Object>();
		userAreaMap.put("district_id", district_id);
		userAreaMap.put("role_type", 2);
		List<AccountInfo> userAreaInfo = accountInfoMapper.fetchUserInfoBase(userAreaMap);
		if(userAreaInfo.size()>0 && userAreaInfo != null){//说明该区域已有用户存在
			return "-102";
		}else{
			bean.setProvince_id(province_id);
			bean.setProvince_name(province_name);
			bean.setCity_id(city_id);
			bean.setCity_name(city_name);
			bean.setDistrict_id(district_id);
			bean.setDistrict_name(district_name);
			return "200";
		}
    }
	

	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String token = CookieUtils.getCookieValue(request, "DISTRIBUTION_DC_TOKEN"); 
		//String token = (String)request.getSession().getAttribute("JIEROU_DC_TOKEN");
		if(StringUtils.isNotEmpty(token)){
			//设置session的过期时间
			//根据token从redis中查询用户信息
			String json = (String)redisSimpleService.get("REDIS_USER_SESSION_" + token);
			if(StringUtils.isNotEmpty(json)){
				RedisUtil.remove("REDIS_USER_SESSION_" + token);
			}
			//CookieUtils.clearCookieName(request, response,"/");
			CookieUtils.delCookies(request, response, "DISTRIBUTION_DC_TOKEN",token);
			request.getSession().removeAttribute("DISTRIBUTION_DC_TOKEN");
		}
	}
	
	
	//处理登录历史记录
	class ProcessLoginLogThread extends Thread{
		private Long user_id;
		private String login_ip;
		private String user_agent;
		
		public ProcessLoginLogThread(Long user_id,String login_ip,String user_agent){
			this.user_id = user_id;
			this.login_ip = login_ip;
			this.user_agent = user_agent;
		}
		
		@Override
		public void run() {
			try {
				loginLogMapper.save(new LoginLogBean(this.user_id,this.login_ip,this.user_agent));
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("loginLog is save error " + e.getMessage());
			}
		}
	}
	
	
    /**
     * 用户账户信息分页
     */
	public PageBean<AccountInfo> fetchParamsByPage(Map<String,String> params) throws Exception {
		List<AccountInfo> accountInfoBeans = new ArrayList<AccountInfo>();
		List<AccountInfo> list = new ArrayList<AccountInfo>();
		Integer count = 0;
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(params.get("search_nickname"))){
			map.put("user_nickname", params.get("search_nickname"));
		}
		if(StringUtils.isNotEmpty(params.get("search_username"))){
			map.put("user_name", params.get("search_username"));
		}
		if(StringUtils.isNotEmpty(params.get("parent_user_id"))){
			map.put("parent_user_id", params.get("parent_user_id"));
		}
		if(StringUtils.isNotEmpty(params.get("province_id"))){
			map.put("province_id", params.get("province_id"));
		}
		if(StringUtils.isNotEmpty(params.get("city_id"))){
			map.put("city_id", params.get("city_id"));
		}
		if(StringUtils.isNotEmpty(params.get("district_id"))){
			map.put("district_id", params.get("district_id"));
		}
		
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		
		if(StringUtils.isNotEmpty(params.get("audit"))){//审核列表
			accountInfoBeans = accountInfoMapper.fetchAuditUser(map);
			count = accountInfoMapper.countAuditUser(map);
		}else{//用户列表
			accountInfoBeans = accountInfoMapper.fetchUserByPage(map);
			count = accountInfoMapper.countUserByNum(map);
		}
		if(accountInfoBeans != null && accountInfoBeans.size()>0){
			for(AccountInfo bean : accountInfoBeans){
				if(bean.getRole_id() != null && !bean.getRole_id().equals("")){
					Role role = vtlRoleMapper.fetchById(new Long(bean.getRole_id()));
					bean.setRole_type(role.getRole_type());
				}
				list.add(bean);
			}
		}
		

		return new PageBean<AccountInfo>(count,list);
	}
	
    /**	
     * 重置app端账号
     */
	public DataCenterResult resetAccount(Map<String,String> params) throws Exception {
		if(StringUtils.isEmpty(params.get("user_mobile"))){
			return DataCenterResult.build(500);
		}
		if(StringUtils.isEmpty(params.get("id"))){
			return DataCenterResult.build(500);
		}
		String id = params.get("id");//要修改新的账号id
		String user_mobile = params.get("user_mobile");//要修改新的账号
		UserInfo userInfo = userInfoMapper.fetchNickNameAndImgByUserMobile(user_mobile);
		if(userInfo != null){//账号已存在
			return DataCenterResult.build(-101);
		}
		UserInfo bean = userInfoMapper.fetchById(new Long(id));
		if(bean == null){
			return DataCenterResult.build(500);
		}
		bean.setUser_mobile(user_mobile);
		userInfoMapper.update(bean);
		return DataCenterResult.ok();
		
		//添加app端账号开始。。。。
		//验证账号是否存在
//		String account_id = params.get("account_id");//web端账号id
//		AccountInfo accountInfo = accountInfoMapper.fetchById(new Long(account_id));
//				UserInfo userInfoBase = userInfoMapper.fetchNickNameAndImgByUserMobile(user_mobile);
//				if(userInfoBase != null){//账号已在user_info表存在
//					return DataCenterResult.build(-101);
//				}else{//新账号
//					UserInfo userInfoBean = new UserInfo();
//					Long user_digit = Common.generate_user_digit();
//					userInfoBean.setUser_digit(user_digit);
//					userInfoBean.setUser_nickname(accountInfo.getUser_name());//user_info表user_nickname是账号
//					userInfoBean.setUser_passwd(accountInfo.getUser_password());
//					userInfoBean.setUser_mobile(user_mobile);
//					userInfoBean.setArea_code("86");
//					userInfoBean.setUser_level(new Short((short) 1));
//					userInfoBean.setUser_type(new Short((short) 2));
//					userInfoBean.setSource(accountInfo.getCompany_id());
//					userInfoBean.setCreate_time(new Date());
//					userInfoMapper.save(userInfoBean);
//					accountInfo.setUser_info_id(userInfoBean.getId());
//					accountInfoMapper.update(accountInfo);
//				}	

				//添加app端账号结束。。。。
	
	}
	
	
    /**
     * 功能说明：删除账户信息
     * @param userId
     * @return
     * @throws Exception
     */
	public DataCenterResult deleteAccount(HttpServletRequest request,Long id) throws Exception {
		if(null == id){
			return DataCenterResult.build(500);
		}
		
		Map<String, Object> accountInfoParams = new HashMap<String, Object>();
		accountInfoParams.put("id", id);
		AccountInfo accountInfoExits = accountInfoMapper.fetchByUserInfo(accountInfoParams);
		if(null == accountInfoExits) {
			 return DataCenterResult.build(500);
	    }
		accountInfoParams.clear();
		accountInfoParams.put("parent_user_id", accountInfoExits.getId());
		accountInfoParams.put("offset", 0);
		accountInfoParams.put("limit", 1);
		if(null == accountInfoMapper.fetchUserByPage(accountInfoParams) || accountInfoMapper.fetchUserByPage(accountInfoParams).size()>0) {
			 return DataCenterResult.build(-1);
	    }
		
		if(verify(id) != null){
			return verify(id);
		}
		
		//删除撤销记录
		accountRepealMapper.deleteByUser_id(id);
		//删除商户退换货记录
		accountBackMapper.deleteByUser_id(id);
		//删除账单
		accountBillDetailMapper.deleteByUser_id(id);
		//删除销售
		accountSoldMapper.deleteByUser_id(id);
		//删除通知
		notifySendMapper.deleteByUser_id(id);
		//删除分配
		deviceBelongMapper.deleteByUser_id(id);
		//删除购货
		purchaseMapper.deleteByUser_id(id);
		//用户退货
		userBackMapper.deleteByUser_id(id);
		//删除提现
		billCloseDetailMapper.deleteByUser_id(id);
		
	    accountInfoMapper.delete(id);   //物理删除用户信息,不删除app端信息（以免该账户在使用scinan产品）
		return DataCenterResult.ok();
	}
	
	/**
	 * 功能说明：修改用户密码
	 */
	public DataCenterResult changePwd(HttpServletRequest request, String pwd,String pwd1) throws Exception {
		//获取当前用户的相关信息
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		Map<String, Object> accountInfoParams = new HashMap<String, Object>();
		accountInfoParams.put("id", accountInfoBean.getId());
		accountInfoBean = accountInfoMapper.fetchByUserInfo(accountInfoParams);
		if(null == accountInfoBean)return DataCenterResult.build(-301);
		
		if(StringUtils.isEmpty(pwd)){
			logger.error("old pwd is empty");
			return DataCenterResult.build(-302);
		}

		String digestPwd = MD5HashUtil.generatePassword(MD5HashUtil.getInstance().hashCode(pwd));
		//旧密码校验
		if(!accountInfoBean.getUser_password().equals(digestPwd)){
			return DataCenterResult.build(-303);
		}
		//修改对应的用户密码
		accountInfoBean.setUser_password(MD5HashUtil.generatePassword(MD5HashUtil.getInstance().hashCode(pwd1)));
		accountInfoBean.setId(accountInfoBean.getId());
		accountInfoBean.setReset_pwd("reset");  //修改完成后，重置初始化密码
		accountInfoMapper.update(accountInfoBean);
		
		//修改app端密码
		UserInfo userInfo = userInfoMapper.fetchById(accountInfoBean.getUser_info_id());
		userInfo.setUser_passwd(MD5HashUtil.generatePassword(MD5HashUtil.getInstance().hashCode(pwd1)));
		userInfoMapper.update(userInfo);
		return DataCenterResult.ok();
	}



	/**
	 * 功能说明：查询用户信息详情
	 * @param id
	 * @return VtlUserExt
	 */
	@Override
	public AccountInfo  fetchVtlUserInfo(Long id) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return accountInfoMapper.fetchByUserInfo(params);
	}
	
	
	
	
	
	/**
	 * 功能说明：重新封装用户信息
	 * @param userInfo 用户信息
	 * @param vtlUserExtBean 用户扩展信息
	 * @param vtlRoleBean  用户角色相息
	 * @return VtlAccountInfoBean
	 */
	private AccountInfo getAccountInfoBean(AccountInfo accountInfoBean,Role vtlRoleBean) throws Exception{
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setId(accountInfoBean.getId());
		accountInfo.setRole_id(accountInfoBean.getRole_id());
		accountInfo.setRole_type(vtlRoleBean.getRole_type());
		accountInfo.setRole_name(vtlRoleBean.getRole_name());
		accountInfo.setUser_password(accountInfoBean.getUser_password());
		accountInfo.setUser_name(accountInfoBean.getUser_name());
		accountInfo.setUser_nickname(accountInfoBean.getUser_nickname());
		accountInfo.setStatus(accountInfoBean.getStatus());
		accountInfo.setCompany_id(vtlRoleBean.getCompany_id());
		accountInfo.setAll_user(accountInfoBean.getAll_user());
		accountInfo.setCurrent_ratio(accountInfoBean.getCurrent_ratio());
		accountInfo.setParent_userid(vtlRoleBean.getParent_userid());
		accountInfo.setMy_current_money(accountInfoBean.getMy_current_money());
		if(StringUtil.isNull(accountInfoBean.getParent_user_path())){
			accountInfo.setParent_user_path("/"+accountInfoBean.getId());
		}else{
			if(accountInfoBean.getRole_type()==AccountInfo.ACCOUNT_TYPE_ADMIN){
			    accountInfo.setParent_user_path(accountInfoBean.getParent_user_path());
			}else{
				if(!StringUtil.isNull(vtlRoleBean.getParent_userid().toString()) && vtlRoleBean.getParent_userid().intValue()>0){
					accountInfo.setParent_user_path(accountInfoBean.getParent_user_path()+"/"+vtlRoleBean.getParent_userid());
					accountInfo.setId(vtlRoleBean.getParent_userid().longValue());
				}else{
					accountInfo.setParent_user_path(accountInfoBean.getParent_user_path()+"/"+accountInfo.getId());
				}
				
			}
		}
		accountInfo.setParent_user_id(accountInfoBean.getId());
		accountInfo.setParent_role_id(Long.parseLong(String.valueOf(vtlRoleBean.getId())));
		if(StringUtil.isNull(vtlRoleBean.getParent_role_path())){
			accountInfo.setParent_role_path("/"+vtlRoleBean.getId());
		}else{
			if(accountInfoBean.getRole_type()==AccountInfo.ACCOUNT_TYPE_ADMIN){
			   accountInfo.setParent_role_path(vtlRoleBean.getParent_role_path());
			}else{
				accountInfo.setParent_role_path(vtlRoleBean.getParent_role_path()+"/"+vtlRoleBean.getId());
			}
		}
		return accountInfo;
	}
	
	
	
	/**
     * 功能说明：查询当前二级经销商信息
     * @return
     */
    public List<AccountInfo> fetchDealerByUserInfo(){
    	return accountInfoMapper.fetchDealerByUserInfo();
    }
	
	
    
    /**
     * 查询当前用户的下级经销商
     * @param parent_user_id
     * @param company_id
     * @return List<AccountInfo>
     */
    @Override
    public List<AccountInfo>  fetchLowerDealerByUserInfo(Integer parent_user_id, String company_id){
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("parent_user_id", parent_user_id);
    	params.put("company_id", company_id);
    	return accountInfoMapper.fetchLowerDealerByUserInfo(params);
    }
    
    
    /**
     * 功能说明：更新用户信息
     * @param bean
     * @return
     */
    @Override
    public Integer updateAccountInfo(AccountInfo bean){
    	return accountInfoMapper.update(bean);
    }
	
    
    
    /**
     * 查询当前厂商下的用户列表
     * @param params
     * @return List<AccountInfo>
     */
    @Override
    public List<AccountInfo>   fetchUserByRoleList(String company_id){
    	Map<String,Object>  params = new HashMap<String,Object>();
    	params.put("company_id", company_id);
    	return accountInfoMapper.fetchUserByRoleList(params);
    }
    
    /**
     * 查询下级用户列表
     * @param params
     * @return List<AccountInfo>
     */
    @Override
    public List<AccountInfo> fetchUserByParentId(Long parent_id){
    	Map<String,Object>  params = new HashMap<String,Object>();
    	if(parent_id.equals(new Long(2))){
    		params.put("status", 0);
    	}else{
    		params.put("status",1);
    	}
    	params.put("parent_user_id", parent_id);
    	return accountInfoMapper.fetchUserByOneKey(params);
    }


	/**
	 * 根据role_id查询用户集合
	 */
	@Override
	public List<AccountInfo> fetchByRoleIdList(Conds conds) {
		Map<String,Object>  params = new HashMap<String,Object>();		
		params.put("conds", conds);
		return accountInfoMapper.fetchByPage(params);
	}
	/**
	 * 首页相关数据
	 * @param request
	 * @param params
	 * @param model
	 */
	public void setIndexValue(AccountInfo bean){
		
		if(bean.getRole_type().intValue()<=1){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("company_id", bean.getCompany_id());	
			
			//超级管理员和厂商管理员,可以查看设备总数
			int deviceAllNum = deviceInfoMapper.countDevice(params);    
			bean.setTotalDeviceNum(deviceAllNum);
			
			//分配给代理商的设备总数
			params.put("parent_id", bean.getId());
			int dbNum = deviceBelongMapper.count(params);
			//一天卖给下级的数量
			int dbNumDay = deviceBelongMapper.countDay(params);
			
			//公司卖给用户的设备总数
			params.clear();
			params.put("user_id", bean.getId());
			int soldNum = accountSoldMapper.count(params);
			//日销售给用户的量
			int soldNumDay = accountSoldMapper.countDay(params);
			//即时库存量
			bean.setInventoryNum(deviceAllNum-dbNum-soldNum <= 0 ? 0 : deviceAllNum-dbNum-soldNum);
			
			//总销售量
			bean.setSoldNum(soldNum+dbNum);
			
			//日销售量
			bean.setSoldNumDay(soldNumDay+dbNumDay);
			//激活设备
			params.clear();
			params.put("_join", "1");
			params.put("company_id", bean.getCompany_id());
			int countJoin = accountSoldMapper.countJoinAll(params);
			bean.setActivedNum(countJoin);
			
			//日激活量
			int activedNumDay = accountSoldMapper.countJoinDay(params);
			bean.setActivedNumDay(activedNumDay);
			//未激活
			bean.setInactiveNum(deviceAllNum-countJoin <= 0 ? 0 : deviceAllNum-countJoin);
			
		}else{
			//其它的情况
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("company_id", bean.getCompany_id());
			params.put("user_id", bean.getId());
			
			//代理商和经销商的设备总数
			int deviceAllNum = deviceBelongMapper.count(params);
			bean.setTotalDeviceNum(deviceAllNum);
			//销售给用户的数量
			int soldNum = accountSoldMapper.count(params);
			//日销售给用户的量
			int soldNumDay = accountSoldMapper.countDay(params);
			
			//卖给下级的数量
			params.clear();
			params.put("parent_id", bean.getId());
			int dbNum = deviceBelongMapper.count(params);
			//一天卖给下级的数量
			int dbNumDay = deviceBelongMapper.countDay(params);
			
			//即时库存量
			bean.setInventoryNum(deviceAllNum-dbNum-soldNum <= 0 ? 0 : deviceAllNum-dbNum-soldNum);
			//总销售量
			bean.setSoldNum(soldNum+dbNum);
			//日销售量
			bean.setSoldNumDay(soldNumDay+dbNumDay);
			
			//激活设备
			params.clear();
			params.put("user_id", bean.getId());
			params.put("_join", 1);
			//自己的总激活量
			int countJoinAll = accountSoldMapper.countJoinAll(params);
			//自己的日激活量
			int countJoinDay = accountSoldMapper.countJoinDay(params);
			if(bean.getRole_type() == 2){//代理商要加上下级的数量
				//获取所有下级
				List<AccountInfo> sons = accountInfoMapper.getListByParent(params);
				for(AccountInfo son : sons){
					params.clear();
					params.put("user_id", son.getId());
					params.put("_join", 1);
					countJoinAll += accountSoldMapper.countJoinAll(params);
					countJoinDay += accountSoldMapper.countJoinDay(params);
				}
			}
			
			//总激活量
			bean.setActivedNum(countJoinAll);
			//日激活量
			bean.setActivedNumDay(countJoinDay);
			//未激活
			bean.setInactiveNum(deviceAllNum-countJoinAll <= 0 ? 0 : deviceAllNum-countJoinAll);
		}
		
		
	}


	@Override
	public PageBean<AccountInfo> getAllByCompany(Map<String, String> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(params.get("type") != null){
			map.put("role_type", params.get("type"));
		}else{
			map.put("role_type", 2);
		}
		map.put("company_id", "10D5");
		List<AccountInfo> list = accountInfoMapper.getAllByCompany(map);
		if(list != null && list.size()>0){
			int sold = 0;
			int bind = 0;
			for(AccountInfo bean : list){
				map.put("user_id", bean.getId());
				sold = accountSoldMapper.countMouth(map);
				map.clear();
				map.put("parent_id", bean.getId());
				bind = deviceBelongMapper.countMouth(map);
				bean.setSoldNumMouth(sold+bind);
			}
		}else{
			return null;
		}
		this.sort(list, 1);	
		 List<AccountInfo> newList = new ArrayList<AccountInfo>();
         if(list.size()>=10){
   		  newList = list.subList(0, 10);
   	   }else{
   		  newList = list;
   	   }
        for(int i=0; i<newList.size(); i++ ){
        	newList.get(i).setCur_ranking(i+1);
        }
		return new PageBean<AccountInfo>(newList.size(),newList);
	}
	
	 //根据使用次数/销量排序
    @SuppressWarnings("unchecked")
	private <T> void sort(List<T> list ,final int type){
    	Collections.sort(list, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				if(type == 1){//技工版销售量
					AccountInfo accountInfo1 = (AccountInfo)o1;
					AccountInfo accountInfo2 = (AccountInfo)o2;
					if(accountInfo1.getSoldNumMouth() < accountInfo2.getSoldNumMouth()){
						return 1;
					}else if(accountInfo1.getSoldNumMouth() == accountInfo2.getSoldNumMouth()){
						return 0;
					}else{
						return -1;
					}
				}else{//用户版使用次数
					UserInfo userInfo1 = (UserInfo)o1;
					UserInfo userInfo2 = (UserInfo)o2;
					if(userInfo1.getDeviceUsedTimesMouth() < userInfo2.getDeviceUsedTimesMouth()){
						return 1;
					}else if(userInfo1.getDeviceUsedTimesMouth() == userInfo2.getDeviceUsedTimesMouth()){
						return 0;
					}else{
						return -1;
					}
				}
			}
		});
    }

    @Override
	public DataCenterResult modifyArea(Map<String, String> params) {
    	String pUserId = String.valueOf(params.get("user_id"));//被更改的对象
    	String province_id = StringUtil.isValidateStr(params.get("province_id"));
		String city_id = StringUtil.isValidateStr(params.get("city_id"));
		String district_id = StringUtil.isValidateStr(params.get("district_id"));
		String province_name = StringUtil.isValidateStr(params.get("province_name"));
		String city_name = StringUtil.isValidateStr(params.get("city_name"));
		String district_name = StringUtil.isValidateStr(params.get("district_name"));
		AccountInfo pUser = accountInfoMapper.fetchById(new Long(pUserId));
		AccountInfo nUser = accountInfoMapper.getUserInfoByDistrict_id(district_id);
		if(pUser == null){
			return DataCenterResult.build(500);
		}else{
			pUser.setProvince_id(province_id);
			pUser.setProvince_name(province_name);
			pUser.setCity_id(city_id);
			pUser.setCity_name(city_name);
			pUser.setDistrict_id(district_id);
			pUser.setDistrict_name(district_name);
		}
		if(verify(new Long(pUserId)) != null){
			return verify(new Long(pUserId));
		}
		if(pUser!=null && pUser.getRole_type() == 2){//更改代理商
			if(nUser != null){
				return DataCenterResult.build(-101);
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parent_user_id", String.valueOf(params.get("user_id")));
			map.put("company_id", "10D5");
			List<AccountInfo> pDealerList = accountInfoMapper.fetchLowerDealerByUserInfo(map);;
			//如果名下有经销商
			if(null!=pDealerList && pDealerList.size()>0){
				for(AccountInfo bean:pDealerList){
					if(verify(bean.getId()) != null){
						return verify(bean.getId());
					}
				}
				//赋值最早注册的经销商为代理商
				AccountInfo proAgent = pDealerList.get(0);
				proAgent.setRole_id(pUser.getRole_id());
				proAgent.setParent_user_id(pUser.getParent_user_id());
				accountInfoMapper.update(proAgent);//更新目标信息
				pDealerList.remove(0);//排除准代理商
				if(pDealerList.size()>0){
					for(AccountInfo bean:pDealerList){
						AccountInfo nAccountInfo = new AccountInfo();
						nAccountInfo.setId(bean.getId());
						nAccountInfo.setParent_user_id(proAgent.getId());
						accountInfoMapper.update(nAccountInfo);
					}
				}
			}
			//更新迁移
			accountInfoMapper.update(pUser);
		}else{//更改经销商
			if(nUser == null){//此区域无代理商
				pUser.setRole_id(Long.parseLong("4"));
				pUser.setParent_user_id(Long.parseLong("2"));
				accountInfoMapper.update(pUser);//更新为该区域的代理商
			}else{//此区域有代理商
				pUser.setParent_user_id(nUser.getId());
				accountInfoMapper.update(pUser);
			}
		}
		return DataCenterResult.ok();
	}
    
    private DataCenterResult verify(Long user_id){
		if(accountBackMapper.countByTransfer(user_id) > 0){
			return DataCenterResult.build(101);
		}
		
		if(notifySendMapper.countByTransfer(user_id) > 0){
			return DataCenterResult.build(102);
		}
		
		if(purchaseMapper.countByTransfer(user_id) > 0){
			return DataCenterResult.build(103);
		}
		
		if(billCloseDetailMapper.countByTransfer(user_id) > 0){
			return DataCenterResult.build(104);
		}
		
			return null;
	}

	@Override
	public PageBean<AccountRepeal> fetchRepealByPage(Map<String,String> params, HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		AccountInfo ai = Common.getAccountInfo(request);
//		Conds c = new Conds();
		
		if(accountInfoMapper.fetchById(ai.getId()).getRole_type() == AccountInfo.ACCOUNT_TYPE_COMMON){
			map.put("parent_id", 2);
		}else{
			map.put("parent_id", ai.getId());
		}
		if(params.get("province_id") != null){
			map.put("province_id", params.get("province_id"));
		}
		if(params.get("city_id") != null){
			map.put("city_id", params.get("city_id"));
		}
		if(params.get("district_id") != null){
			map.put("district_id", params.get("district_id"));
		}
		if(params.get("user_name") != null){
			map.put("user_name", params.get("user_name"));
		}
		if(params.get("user_nickname") != null){
			map.put("user_nickname", params.get("user_nickname"));
		}
		map.put("offset", Integer.parseInt(params.get("offset")));
		map.put("limit", Integer.parseInt(params.get("limit")));
		
		List<AccountRepeal> list = accountRepealMapper.fetchByPage(map);
		
		
		return new PageBean<AccountRepeal>(accountRepealMapper.count(map),list);
	}


	@Override
	public AccountInfo getUserInfoByDistrict_id(String district_id) {
		return accountInfoMapper.getUserInfoByDistrict_id(district_id);
	}
	
}
