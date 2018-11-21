package com.scinan.iot.s6000.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class DeviceUser implements Serializable {
	private static final long serialVersionUID = 1730794290695693519L;
	private String device_id;
	private Long user_id;
	private Long m_user_id;
	private Date create_time;
	// 主从关系类型 master slave type 0:主，1从
	// 主授权用户可以分享设备给其他用户，则其他用户为从授权用户
	// 主授权用户一般从AP模式添加，且只能存在一个，后添加的主授权用户会取代先添加的主授权用户
	private Integer mstype;
	private String mobile;
	private String email;
	private String pwd;
	private String company_id;
	
	private String user_nickname;
	
	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public DeviceUser() {
		
	}
	
	public DeviceUser(String device_id, Long user_id, Integer mstype, String mobile) {
		this.device_id = device_id;
		this.user_id = user_id;
		this.mstype = mstype;
		this.mobile = mobile;
	}
	
	public DeviceUser(String device_id, Long user_id, Integer mstype, String mobile,Long m_user_id) {
        this.device_id = device_id;
        this.user_id = user_id;
        this.mstype = mstype;
        this.mobile = mobile;
        this.m_user_id = m_user_id;
    }
	
	public DeviceUser(String device_id, Long user_id, Integer mstype, String mobile,String email) {
        this.device_id = device_id;
        this.user_id = user_id;
        this.mstype = mstype;
        this.mobile = mobile;
        this.email= email;
    }
	
	public DeviceUser(String device_id, Long user_id, String pwd) {
        this.device_id = device_id;
        this.user_id = user_id;
        this.pwd = pwd;
    }

	@Override
	public String toString() {
		return "DeviceUser [device_id=" + device_id + ", user_id=" + user_id //
				+ ", create_time=" + create_time + ", mstype=" + mstype + ", mobile=" + mobile + ",m_user_id="+m_user_id+",pwd="+pwd+"]";
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getMstype() {
		return mstype;
	}

	public void setMstype(Integer mstype) {
		this.mstype = mstype;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getM_user_id() {
        return m_user_id;
    }

    public void setM_user_id(Long m_user_id) {
        this.m_user_id = m_user_id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
