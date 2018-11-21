package com.scinan.iot.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scinan.bean.DataCenterResult;
import com.scinan.bean.PageBean;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.ddeddo.dao.domain.AccountRepeal;
import com.scinan.iot.ddeddo.dao.domain.AccountRepealBean;
import com.scinan.mybatis.support.service.GenericService;
import com.scinan.mybatis.support.sql.Conds;
/**
 * 账号信息业务接口类
 * 
 * @project datacenter
 * @class com.scinan.iot.service.AccountInfoService
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月6日
 * @description
 */
public interface AccountInfoService extends GenericService<AccountInfo, Long>{
	
	/**
	 * 用户登录
	 * @param loginname 	用户账号
	 * @param loginpwd  	用户密码
	 * @param verifycode	验证码
	 * @param request		
	 * @param response
	 * @return				
	 */
	DataCenterResult userLogin(String loginname, String loginpwd,
			String verifycode, HttpServletRequest request,
			HttpServletResponse response) throws Exception ;

	/**
	 * 退出登录
	 * @param request
	 * @param response
	 */
	void logout(HttpServletRequest request, HttpServletResponse response)throws Exception ;

	/**
	 * 获取分页
	 * @param params
	 * @return
	 */
	PageBean<AccountInfo> fetchParamsByPage(Map<String,String> params)throws Exception ;
	
	/**
	 * 删除账号
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	DataCenterResult deleteAccount(HttpServletRequest request,Long id)throws Exception ;

	/**
	 * 修改账号信息
	 * @param request 
	 * @param accountInfoBean
	 * @return
	 * @throws Exception
	 */
	DataCenterResult updateAccount(HttpServletRequest request, AccountInfo accountInfoBean,Map<String, String> params)throws Exception ;

	/**
	 * 添加账号信息
	 * @param request 
	 * @param accountInfoBean
	 * @param params
	 * @return
	 * @throws Exception
	 */
	DataCenterResult addAccount(HttpServletRequest request, AccountInfo accountInfoBean,Map<String, String> params)throws Exception ;
	
	
	
	
	/**
	 * 功能说明：查询用户信息详情
	 * @param id
	 * @return VtlUserExt
	 */
	public AccountInfo  fetchVtlUserInfo(Long id) throws Exception;
	
	
	
	
	/**
	 * 修改密码
	 * @param request
	 * @param pwd
	 * @param pwd1
	 * @return
	 * @throws Exception
	 */
	DataCenterResult changePwd(HttpServletRequest request, String pwd,String pwd1)throws Exception ;


	
	
	/**
     * 功能说明：查询当前二级经销商信息
     * @return
     */
    List<AccountInfo> fetchDealerByUserInfo();
    
    
    
    /**
     * 查询当前用户的下级经销商
     * @param parent_user_id
     * @param company_id
     * @return List<AccountInfo>
     */
    public List<AccountInfo>  fetchLowerDealerByUserInfo(Integer parent_user_id, String company_id);
    
    
    
    
    /**
     * 功能说明：更新用户信息
     * @param bean
     * @return
     */
    public Integer updateAccountInfo(AccountInfo bean);
    
    
    
    /**
     * 查询当前厂商下的用户列表
     * @param params
     * @return List<AccountInfo>
     */
    public List<AccountInfo>   fetchUserByRoleList(String company_id);
    
    /**
	 * 重置app端账号
	 * @param params
	 * @return
	 * @throws Exception
	 */
	DataCenterResult resetAccount(Map<String, String> params)throws Exception;
    
	
	public List<AccountInfo>  fetchByRoleIdList(Conds conds);
	
	public PageBean<AccountInfo>  getAllByCompany(Map<String, String> params);
    
	public void setIndexValue(AccountInfo bean);
	
	
	/**
	 * 获取分页
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	PageBean<AccountRepeal> fetchRepealByPage(Map<String, String> params, HttpServletRequest request) throws Exception;

	List<AccountInfo> fetchUserByParentId(Long parent_id);

	/**
     * 查询角色信息Bydistrict_id
     * @param params
     * @return AccountInfo
     */
    AccountInfo   getUserInfoByDistrict_id(String district_id);

	DataCenterResult modifyArea(Map<String, String> params);
    
}
