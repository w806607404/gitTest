package com.scinan.iot.s1000.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息实体类
 * 
 * @project datacenter
 * @class com.scinan.iot.s1000.dao.domain.UserInfoBean
 * @copyright www.scinan.com
 * @author Zola
 * @date 2016年7月22日
 * @description
 */

public class UserInfo implements Serializable{
	
	private static final long serialVersionUID = 8949064594944376421L;

	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 用户邮箱
	 */
	private String user_email;
	/**
	 * 用户昵称
	 */
	private String user_nickname;
	/**
	 * 用户名
	 */
	private String user_name;
	/**
	 * 
	 */
	private String user_phone;
	/**
	 * 地址
	 */
	private String user_address;
	/**
	 * 密码
	 */
	private String user_passwd;
	/**
	 * 
	 */
	private String user_operationcode;
	/**
	 * 
	 */
	private Short user_level;
	/**
	 * 最近登录时间
	 */
	private Date latest_logintime;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 
	 */
	private Short user_type;
	/**
	 * 注册时间
	 */
	private Date login_time;
	/**
	 * 
	 */
	private Boolean is_developer;
	/**
	 * 数字账号
	 */
	private Long user_digit;
	/**
	 * 手机号码
	 */
	private String user_mobile;
	
	/**
	 * 用户QQ
	 */
	private String user_qq;
	
	/**
	 * 
	 */
	private Short email_flag;
	/**
	 * 
	 */
	private String qq_openid;
	/**
	 * 
	 */
	private String img;
	/**
	 * 
	 */
	private String sex;
	/**
	 * 
	 */
	private String source;
	/**
	 * 
	 */
	private String area_code;
	/**
	 * 
	 */
	private String user_passwd_bak;
	
	//业务字段
	private Integer device_count;//用户绑定设备数量
	
	private String name;
	
	private Integer value;
	
	private FactoryBean factoryBean;
	
	private Integer deviceUsedTimesMouth;
	
	private BigDecimal redEnvelopeAmount;
	
	private int cur_ranking;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_passwd() {
		return user_passwd;
	}
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}
	public String getUser_operationcode() {
		return user_operationcode;
	}
	public void setUser_operationcode(String user_operationcode) {
		this.user_operationcode = user_operationcode;
	}
	public Short getUser_level() {
		return user_level;
	}
	public void setUser_level(Short user_level) {
		this.user_level = user_level;
	}
	public Date getLatest_logintime() {
		return latest_logintime;
	}
	public void setLatest_logintime(Date latest_logintime) {
		this.latest_logintime = latest_logintime;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Short getUser_type() {
		return user_type;
	}
	public void setUser_type(Short user_type) {
		this.user_type = user_type;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public Boolean getIs_developer() {
		return is_developer;
	}
	public void setIs_developer(Boolean is_developer) {
		this.is_developer = is_developer;
	}
	public Long getUser_digit() {
		return user_digit;
	}
	public void setUser_digit(Long user_digit) {
		this.user_digit = user_digit;
	}
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	public Short getEmail_flag() {
		return email_flag;
	}
	public void setEmail_flag(Short email_flag) {
		this.email_flag = email_flag;
	}
	public String getQq_openid() {
		return qq_openid;
	}
	public void setQq_openid(String qq_openid) {
		this.qq_openid = qq_openid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getUser_passwd_bak() {
		return user_passwd_bak;
	}
	public void setUser_passwd_bak(String user_passwd_bak) {
		this.user_passwd_bak = user_passwd_bak;
	}
	public Integer getDevice_count() {
		return device_count;
	}
	public void setDevice_count(Integer device_count) {
		this.device_count = device_count;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public FactoryBean getFactoryBean() {
		return factoryBean;
	}
	public void setFactoryBean(FactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}
	public String getUser_qq() {
		return user_qq;
	}
	public void setUser_qq(String user_qq) {
		this.user_qq = user_qq;
	}
	public Integer getDeviceUsedTimesMouth() {
		return deviceUsedTimesMouth;
	}
	public void setDeviceUsedTimesMouth(Integer deviceUsedTimesMouth) {
		this.deviceUsedTimesMouth = deviceUsedTimesMouth;
	}
	public BigDecimal getRedEnvelopeAmount() {
		return redEnvelopeAmount;
	}
	public void setRedEnvelopeAmount(BigDecimal redEnvelopeAmount) {
		this.redEnvelopeAmount = redEnvelopeAmount;
	}
	public int getCur_ranking() {
		return cur_ranking;
	}
	public void setCur_ranking(int cur_ranking) {
		this.cur_ranking = cur_ranking;
	}
	
}