package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kim on 2017/03/22.
 */
public class UserBasicInfo implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3775160249215906798L;

	
	/**
     * .
     */
    private Long id;

    /**
     * 中文名称.
     */
    private String name;

    /**
     * 英文名称.
     */
    private String name_cn;
    
    /**
     * 公司信息.
     */
    private String company_info;

    /**
     * logo图片地址.
     */
    private String logo_url;

    /**
     * 官网地址.
     */
    private String company_website;

    /**
     * 客服电话.
     */
    private String customer_phone;

    /**
     * 微信公众号.
     */
    private String wx_account;

    /**
     * 微信公众号二维码.
     */
    private String wx_qr_code;

    /**
     * 操作时间.
     */
    private Date create_time;
    
    /**
     * 厂商ID
     */
    private String company_id;
    
    
    private Long user_id;
    
    /**
     * 联系人名称
     */
    private String link_name;
    
    /**
     * 联系人电话
     */
    private String phone;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

	public String getCompany_info() {
		return company_info;
	}

	public void setCompany_info(String company_info) {
		this.company_info = company_info;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public String getCompany_website() {
		return company_website;
	}

	public void setCompany_website(String company_website) {
		this.company_website = company_website;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getWx_account() {
		return wx_account;
	}

	public void setWx_account(String wx_account) {
		this.wx_account = wx_account;
	}

	public String getWx_qr_code() {
		return wx_qr_code;
	}

	public void setWx_qr_code(String wx_qr_code) {
		this.wx_qr_code = wx_qr_code;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getLink_name() {
		return link_name;
	}

	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	
	
}
