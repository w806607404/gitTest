package com.scinan.iot.ddeddo.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：用户账户信息
 * @author vinson
 *
 */
public interface AccountInfoMapper extends GenericMapper<AccountInfo, Long> {

	void deleteSystemResources(String id);

	
	List<String> fetchAllUrl();
	
	/**
	 * 功能说明：查询用户基本信息
	 * @param params
	 * @return VtlAccountInfo
	 */
	AccountInfo  fetchByUserInfo(Map<String, Object> params);
	
	/**
	 * 功能说明：查询用户列表通用（条件查询，获取不同级别信息列表）
	 * @param params
	 * @return AccountInfo
	 */	
	List<AccountInfo> fetchUserInfoBase(Map<String, Object> params);
	
	/**
	 * 功能说明：查询审核用户列表
	 * @param params
	 * @return AccountInfo
	 */	
	List<AccountInfo> fetchAuditUser(Map<String, Object> params);
	
	/**
	 * 功能说明：审核用户信息总数
	 * @param params
	 * @return Integer 
	 */
	Integer countAuditUser(Map<String, Object> params);
	
	/**
	 * 功能说明：用户信息列表（非审核）
	 * @param params
	 * @return AccountInfo
	 */
	List<AccountInfo>  fetchUserByPage(Map<String, Object> params);
	
	/**
	 * 功能说明：注册用户信息总数
	 * @param params
	 * @return Integer 
	 */
	Integer countUserByNum(Map<String, Object> params);
	
	
	/**
	 * 功能说明：根据单位查询对应的用户信息
	 * @param params
	 * @return List<VtlAccountInfo>
	 */
	List<AccountInfo>  fetchOrganizationByUserInfo(Map<String, Object> params);
	
	
	
	/**
	 * 功能说明：根据用户ID查询用户信息
	 * @param params
	 * @return List<VtlAccountInfo> 
	 */
	List<AccountInfo> fetchUserInfoOfOther(Map<String, Object> params);


    List<AccountInfo> fetchByOrgId(Map<String, Object> params);

    AccountInfo fetchByBuildingId(int id);
    
    /**
     * 功能说明：查询当前二级经销商信息
     * @return
     */
    List<AccountInfo> fetchDealerByUserInfo();
    
    
    /**
     * 功能说明：获取所有的一二代理商级列表
     * @param params
     * @return List<AccountInfo>
     */
    List<AccountInfo>  fetchDealerByUserList(Map<String,Object> params);
    
    
    /**
     * 功能说明：查询当前用户的下级用户
     * @param params
     * @return List<AccountInfo>
     */
    List<AccountInfo>  fetchUserByOneKey(Map<String,Object> params);
    
    
    
    /**
     * 通过用户ID获取用户类型名称
     * @param params
     * @return AccountInfo
     */
    AccountInfo fetchUserByRoleName(Map<String,Object> params);
    
    
    List<AccountInfo>  fetchLowerDealerByUserInfo(Map<String,Object> params);
    
    
    List<AccountInfo>  fetchUserByRoleTypeList(Map<String,Object> params);
    
    
    /**
     * 查询当前厂商下的用户列表
     * @param params
     * @return List<AccountInfo>
     */
    List<AccountInfo>   fetchUserByRoleList(Map<String,Object>  params);
    
    /**
     * 查询角色信息ByUserName
     * @param params
     * @return AccountInfo
     */
    AccountInfo   getUserInfoByUserNickname(String user_nickname);
    
    /**
     * 查询角色信息Bydistrict_id
     * @param params
     * @return AccountInfo
     */
    AccountInfo   getUserInfoByDistrict_id(String district_id);
    
    /**
     * 查询当前角色的下级所有用户列表
     * @param params
     * @return List<AccountInfo>
     */
    List<AccountInfo>   getListByParent(Map<String,Object>  params);
    
    /**
     * 查询当前厂商下的用户列表
     * @param params
     * @return List<AccountInfo>
     */
    List<AccountInfo>   getAllByCompany(Map<String,Object>  params);
    
    
    /**
     * 查询一个区域开启或审核通过的代理商
     * @param params
     * @return List<AccountInfo>
     */
    List<AccountInfo>   fetchUserInfoByAgent(Map<String,Object>  params);

	/**
	 * 查询一个的经销商
	 * @param params
	 * @return List<AccountInfo>
	 */
	List<AccountInfo>   fetchLowerDealerByAudit(Map<String,Object>  params);
    
}
