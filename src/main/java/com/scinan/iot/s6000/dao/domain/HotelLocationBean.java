package com.scinan.iot.s6000.dao.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * HotelLocationBean对象.
 * @author kimsun
 *
 */
public class HotelLocationBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7894095821261823234L;

	/**
     * 主键.
     */
    private Long user_id;
    
    /**
     * 厂商ID
     */
    private String company_id;
    
    
    /**
     * 商店名称.
     */
    private String  name;
    
    
    /**
     * 电话.
     */
    private String phone;
    
    /**
     * 详细地址.
     */
    private String address;
    
    /**
     * 省份ID
     */
    private String province_id;
    
    /**
     * 城市id
     */
    private String city_id;
    
    /**
     * 经度
     */
    private String lng;
    
    /**
     * 纬度
     */
    private String lat;

    /**
     * 操作时间
     */
    private Date create_time;
    
    
    /**
     * 位置
     */
    private String postion;
    
    
    /**
     * 城市名称
     */
    private String city_name;
    

	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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


	public String getLng() {
		return lng;
	}


	public void setLng(String lng) {
		this.lng = lng;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	
	

}
