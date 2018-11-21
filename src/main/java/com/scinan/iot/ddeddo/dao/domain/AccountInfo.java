package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.scinan.iot.s1000.dao.domain.UserInfo;

/**
 * 账号信息实体类
 * @project datacenter
 * @class com.scinan.iot.vitalong.dao.domain.AccountInfo
 * @copyright www.scinan.com
 * @author vinson
 * @date 2018年06月04日
 * @description
 */
public class AccountInfo implements Serializable{
	
	public final static Integer ACCOUNT_TYPE_ADMIN = 0;     //超级管理员
	public final static Integer ACCOUNT_TYPE_COMMON = 1;    //厂商管理员
	public final static Integer ACCOUNT_TYPE_OTHER = 2;     //下级经销商
	
	public final static Integer ACCOUNT_STATUS_CLOSE = 0; //账号关闭状态
	public final static Integer ACCOUNT_STATUS_OPEN = 1; //账号开启/审核通过状态
	public final static Integer ACCOUNT_STATUS_INAUDIT = 2; //账号审核中状态
	public final static Integer ACCOUNT_STATUS_UNAUDIT = 3; //账号审核不通过状态
	public final static Integer ACCOUNT_STATUS_APPOINTMENT = 4; //账号预约状态
	public final static Integer ACCOUNT_STATUS_PASS = 5; //账号审核通过状态
	public final static Integer ACCOUNT_STATUS_INIT = 6; //账号初始化状态
	private static final long serialVersionUID = -5070110831454546667L;
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 与user_info表一对一
	 */
	private Long user_info_id;
	
	/**
	 * 上级用户ID
	 */
	private Long parent_user_id;
	
	
	/**
	 * 父级用户路径
	 */
	private String parent_user_path;
	
	/**
	 * 密码
	 */
	private String user_password;
	
	/**
	 * 初始化密码
	 */
	private String init_pwd;
	
	
	/**
	 * 重置密码
	 */
	private String reset_pwd;
	
	
	
	/**
	 * 是否锁定
	 */
	private String locked;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 更新时间
	 */
	private Date update_time;
	
	/**
	 * 账号状态
	 */
	private Integer state;//  0 正常   1 删除
	/**
	 * 账号描述
	 */
	private String note;
	
	/**
	 * 当前用户所属用户组ID
	 */
	private Long role_id;
	
	private Long creator_role_id;
	
	private String role_name;
	
	private Integer role_type;
	
	private boolean isAdmin;
	
	private List<Role>  roleBeans;
	
	private Integer account_type;
	
	/**
	 * 与t_user_info表一对一
	 */
	private UserInfo userInfo;
	
	/**
	 * 用户账号
	 */
	private String user_name;
	
	/**
	 * 用户昵称
	 */
	private String user_nickname;
	
	
	/**
	 * 状态(0:关闭,1:开启/审核通过,2:审核中,3：审核不通过)
	 */
	private Integer status;
	
	/**
	 * 上级角色ID
	 */
	private Long parent_role_id;
	
	
	/**
	 * 父级角色路径
	 */
	private String parent_role_path;
	
	/**
	 * 厂商ID
	 */
	private String company_id;
	
	/**
	 * 所有用户
	 */
	private String all_user;
	
	
	/**
	 * 分成比例值
	 */
	private Double current_ratio;
	
	/**
	 * 分成比例值
	 */
	private String current_ratio_str;
	
	
	/**
	 *  当前分成金额
	 */
	private String my_current_money;
	
	
	/**
	 * 上级用户ID
	 */
	private Integer parent_userid;
	
	/**
	 * 代理商真实姓名
	 */
	private String agent_name;
	
	/**
	 * 代理商电话
	 */
	private String agent_phone;
	private String token;
	
	/**
	 * 用户注册类型
	 */
	private Integer user_type;
	
	/**
	 * 身份证号/营业执照号码
	 */
	private String identity_card;

	/**
	 * 身份证号/营业执照图片
	 */
	private String identity_img;
	
	/**
	 * 银行卡所属银行名称
	 */
	private String bank_name;

	/**
	 * 银行卡号
	 */
	private String bank_card;
	
	/**
	 * 支付宝账号姓名
	 */
	private String alipay_name;
	
	/**
	 *	支付宝账号
	 */
	private String alipay_card;
	
	/**
	 * 微信账号
	 */
	private String user_wechat;

	/**
	 * 邮箱
	 */
	private String user_email;
	
	/**
	 * 最方便联系人姓名
	 */
	private String other_user_name;
	
	/**
	 * 最方便联系人电话
	 */
	private String other_user_contact;
	/**
	 * 省份ID
	 */
	private String province_id;
	/**
	 * 省份名称
	 */
	private String province_name;
	/**
	 * 城市ID
	 */
	private String city_id;
	/**
	 * 城市名称
	 */
	private String city_name;	
	/**
	 * 区县ID
	 */
	private String district_id;
	/**
	 * 区县名称
	 */
	private String district_name;
	
	/**
	 * 详细地址
	 */
	private String user_address;
	
	/**
	 * 分红金额
	 */
	private BigDecimal ratio_amount;
	
	/**
	 * 补贴金额
	 */
	private BigDecimal subsidy_amount;
	
	//额外信息
	/**
	 * 总设备数
	 */
	private Integer totalDeviceNum;
	
	/**
	 * 总库存量
	 */
	private Integer inventoryNum;
	
	/**
	 * 总激活量
	 */
	private Integer activedNum;
	
	/**
	 * 日激活量
	 */
	private Integer activedNumDay;
	
	

	/**
	 * 待激活量
	 */
	private Integer inactiveNum;
	
	/**
	 * 总销售量
	 */
	private Integer soldNum;
	
	/**
	 * 日销售量
	 */
	private Integer soldNumDay;
	
	/**
	 * 月销售量
	 */
	private Integer soldNumMouth;
	
	/**
	 * 名次
	 */
	private int cur_ranking;
	
	/**
	 * 头像
	 */
	private String img;
	
	/**
	 * 付费凭证
	 */
	private String pay_proof;
	
	/**
	 * 证件名称
	 */
	private String identity_name;
	
	public Integer getActivedNumDay() {
		return activedNumDay;
	}



	public void setActivedNumDay(Integer activedNumDay) {
		this.activedNumDay = activedNumDay;
	}



	public Integer getSoldNum() {
		return soldNum;
	}



	public void setSoldNum(Integer soldNum) {
		this.soldNum = soldNum;
	}



	public Integer getSoldNumDay() {
		return soldNumDay;
	}



	public void setSoldNumDay(Integer soldNumDay) {
		this.soldNumDay = soldNumDay;
	}


	
	
	public Integer getUser_type() {
		return user_type;
	}



	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}



	public String getIdentity_card() {
		return identity_card;
	}



	public void setIdentity_card(String identity_card) {
		this.identity_card = identity_card;
	}



	public String getIdentity_img() {
		return identity_img;
	}



	public void setIdentity_img(String identity_img) {
		this.identity_img = identity_img;
	}



	public String getBank_name() {
		return bank_name;
	}



	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}



	public String getBank_card() {
		return bank_card;
	}



	public void setBank_card(String bank_card) {
		this.bank_card = bank_card;
	}



	public String getAlipay_name() {
		return alipay_name;
	}



	public void setAlipay_name(String alipay_name) {
		this.alipay_name = alipay_name;
	}



	public String getAlipay_card() {
		return alipay_card;
	}



	public void setAlipay_card(String alipay_card) {
		this.alipay_card = alipay_card;
	}



	public String getUser_wechat() {
		return user_wechat;
	}



	public void setUser_wechat(String user_wechat) {
		this.user_wechat = user_wechat;
	}

	public AccountInfo(){}



	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getRole_id() {
		return role_id;
	}


	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}


	public String getRole_name() {
		return role_name;
	}


	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreator_role_id() {
		return creator_role_id;
	}

	public void setCreator_role_id(Long creator_role_id) {
		this.creator_role_id = creator_role_id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getRole_type() {
		return role_type;
	}

	public void setRole_type(Integer role_type) {
		this.role_type = role_type;
	}

	public Integer getAccount_type() {
		return account_type;
	}

	public void setAccount_type(Integer account_type) {
		this.account_type = account_type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getInit_pwd() {
		return init_pwd;
	}

	public void setInit_pwd(String init_pwd) {
		this.init_pwd = init_pwd;
	}


	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

	public List<Role> getRoleBeans() {
		return roleBeans;
	}

	public void setRoleBeans(List<Role> roleBeans) {
		this.roleBeans = roleBeans;
	}

	public String getParent_user_path() {
		return parent_user_path;
	}

	public void setParent_user_path(String parent_user_path) {
		this.parent_user_path = parent_user_path;
	}

	public Long getParent_user_id() {
		return parent_user_id;
	}

	public void setParent_user_id(Long parent_user_id) {
		this.parent_user_id = parent_user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	public Long getParent_role_id() {
		return parent_role_id;
	}

	public void setParent_role_id(Long parent_role_id) {
		this.parent_role_id = parent_role_id;
	}

	public String getParent_role_path() {
		return parent_role_path;
	}

	public void setParent_role_path(String parent_role_path) {
		this.parent_role_path = parent_role_path;
	}

	public String getReset_pwd() {
		return reset_pwd;
	}

	public void setReset_pwd(String reset_pwd) {
		this.reset_pwd = reset_pwd;
	}


	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getAll_user() {
		return all_user;
	}

	public void setAll_user(String all_user) {
		this.all_user = all_user;
	}

	
	public Double getCurrent_ratio() {
		return current_ratio;
	}


	public void setCurrent_ratio(Double current_ratio) {
		this.current_ratio = current_ratio;
	}



	public Integer getParent_userid() {
		return parent_userid;
	}

	public void setParent_userid(Integer parent_userid) {
		this.parent_userid = parent_userid;
	}


	public String getMy_current_money() {
		return my_current_money;
	}



	public void setMy_current_money(String my_current_money) {
		this.my_current_money = my_current_money;
	}



	public String getCurrent_ratio_str() {
		return current_ratio_str;
	}



	public void setCurrent_ratio_str(String current_ratio_str) {
		this.current_ratio_str = current_ratio_str;
	}



	public String getAgent_name() {
		return agent_name;
	}



	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}



	public String getAgent_phone() {
		return agent_phone;
	}



	public void setAgent_phone(String agent_phone) {
		this.agent_phone = agent_phone;
	}



	public String getProvince_id() {
		return province_id;
	}



	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}



	public String getCity_id() {
		return city_id;
	}



	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}



	public String getDistrict_id() {
		return district_id;
	}



	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}



	public String getProvince_name() {
		return province_name;
	}



	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}



	public String getCity_name() {
		return city_name;
	}



	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}



	public String getDistrict_name() {
		return district_name;
	}



	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}



	public UserInfo getUserInfo() {
		return userInfo;
	}



	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}



	public Long getUser_info_id() {
		return user_info_id;
	}



	public void setUser_info_id(Long user_info_id) {
		this.user_info_id = user_info_id;
	}



	public String getUser_email() {
		return user_email;
	}



	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}



	public BigDecimal getSubsidy_amount() {
		return subsidy_amount;
	}



	public void setSubsidy_amount(BigDecimal subsidy_amount) {
		this.subsidy_amount = subsidy_amount;
	}



	public BigDecimal getRatio_amount() {
		return ratio_amount;
	}



	public void setRatio_amount(BigDecimal ratio_amount) {
		this.ratio_amount = ratio_amount;
	}






	public Integer getTotalDeviceNum() {
		return totalDeviceNum;
	}



	public void setTotalDeviceNum(Integer totalDeviceNum) {
		this.totalDeviceNum = totalDeviceNum;
	}



	public Integer getInventoryNum() {
		return inventoryNum;
	}



	public void setInventoryNum(Integer inventoryNum) {
		this.inventoryNum = inventoryNum;
	}



	public Integer getActivedNum() {
		return activedNum;
	}



	public void setActivedNum(Integer activedNum) {
		this.activedNum = activedNum;
	}



	public Integer getInactiveNum() {
		return inactiveNum;
	}



	public void setInactiveNum(Integer inactiveNum) {
		this.inactiveNum = inactiveNum;
	}



	public String getUser_address() {
		return user_address;
	}



	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}



	public String getOther_user_name() {
		return other_user_name;
	}



	public void setOther_user_name(String other_user_name) {
		this.other_user_name = other_user_name;
	}



	public String getOther_user_contact() {
		return other_user_contact;
	}



	public void setOther_user_contact(String other_user_contact) {
		this.other_user_contact = other_user_contact;
	}



	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}



	public String getPay_proof() {
		return pay_proof;
	}



	public void setPay_proof(String pay_proof) {
		this.pay_proof = pay_proof;
	}



	public String getIdentity_name() {
		return identity_name;
	}



	public void setIdentity_name(String identity_name) {
		this.identity_name = identity_name;
	}



	@Override
	public String toString() {
		return "AccountInfo [id=" + id + ", user_info_id=" + user_info_id + ", parent_user_id=" + parent_user_id
				+ ", parent_user_path=" + parent_user_path + ", user_password=" + user_password + ", init_pwd="
				+ init_pwd + ", reset_pwd=" + reset_pwd + ", locked=" + locked + ", create_time=" + create_time
				+ ", update_time=" + update_time + ", state=" + state + ", note=" + note + ", role_id=" + role_id
				+ ", creator_role_id=" + creator_role_id + ", role_name=" + role_name + ", role_type=" + role_type
				+ ", isAdmin=" + isAdmin + ", roleBeans=" + roleBeans + ", account_type=" + account_type + ", userInfo="
				+ userInfo + ", user_name=" + user_name + ", user_nickname=" + user_nickname + ", status=" + status
				+ ", parent_role_id=" + parent_role_id + ", parent_role_path=" + parent_role_path + ", company_id="
				+ company_id + ", all_user=" + all_user + ", current_ratio=" + current_ratio + ", current_ratio_str="
				+ current_ratio_str + ", my_current_money=" + my_current_money + ", parent_userid=" + parent_userid
				+ ", agent_name=" + agent_name + ", agent_phone=" + agent_phone + ", token=" + token + ", user_type="
				+ user_type + ", identity_card=" + identity_card + ", identity_img=" + identity_img + ", bank_name="
				+ bank_name + ", bank_card=" + bank_card + ", alipay_name=" + alipay_name + ", alipay_card="
				+ alipay_card + ", user_wechat=" + user_wechat + ", user_email=" + user_email + ", other_user_name="
				+ other_user_name + ", other_user_contact=" + other_user_contact + ", province_id=" + province_id
				+ ", province_name=" + province_name + ", city_id=" + city_id + ", city_name=" + city_name
				+ ", district_id=" + district_id + ", district_name=" + district_name + ", user_address=" + user_address
				+ ", ratio_amount=" + ratio_amount + ", subsidy_amount=" + subsidy_amount + ", totalDeviceNum="
				+ totalDeviceNum + ", inventoryNum=" + inventoryNum + ", activedNum=" + activedNum + ", activedNumDay="
				+ activedNumDay + ", inactiveNum=" + inactiveNum + ", soldNum=" + soldNum + ", soldNumDay=" + soldNumDay
				+ ", img=" + img + ", pay_proof=" + pay_proof + ", identity_name=" + identity_name + "]";
	}



	public Integer getSoldNumMouth() {
		return soldNumMouth;
	}



	public void setSoldNumMouth(Integer soldNumMouth) {
		this.soldNumMouth = soldNumMouth;
	}



	public int getCur_ranking() {
		return cur_ranking;
	}



	public void setCur_ranking(int cur_ranking) {
		this.cur_ranking = cur_ranking;
	}
	
	
	
}

