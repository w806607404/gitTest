/**
 * @Description:
 * @Package: com.scinan.iot.ddeddo.dao.domain 
 * @author: 吴广   
 * @date: 2018年7月10日 下午4:02:36 
 */
package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 该类的功能描述
 * @author: 吴广
 * @date: 2018年7月10日 下午4:02:36 
 */
public class DeviceBelongBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6047629587028975033L;

	/**
	 * 主键id
	 */
	private Long id;
	
	private String device_id;
	
	private Long user_id;
	
	private Long parent_id;
	
	private String user_nickname;
	
	private Date create_time;
	
	private Integer device_level;
	
	/**
	 * 厂商ID
	 */
	private String company_id;
	
	/**
     * 省ID
     */
    private String province_id;
    
    /**
     * .
     */
    private String province_name;
    
    /**
     * .
     */
    private String city_id;
    
    /**
     * .
     */
    private String city_name;
    
    /**
     * .
     */
    private String district_id;
    
    /**
     * .
     */
    private String district_name;
    
    
    public DeviceBelongBean(){}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the device_id
	 */
	public String getDevice_id() {
		return device_id;
	}

	/**
	 * @param device_id the device_id to set
	 */
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	/**
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the parent_id
	 */
	public Long getParent_id() {
		return parent_id;
	}

	/**
	 * @param parent_id the parent_id to set
	 */
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	/**
	 * @return the user_nickname
	 */
	public String getUser_nickname() {
		return user_nickname;
	}

	/**
	 * @param parent_name the user_nickname to set
	 */
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	/**
	 * @return the create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * @return the device_level
	 */
	public Integer getDevice_level() {
		return device_level;
	}

	/**
	 * @param device_level the device_level to set
	 */
	public void setDevice_level(Integer device_level) {
		this.device_level = device_level;
	}

	/**
	 * @return the company_id
	 */
	public String getCompany_id() {
		return company_id;
	}

	/**
	 * @param company_id the company_id to set
	 */
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	/**
	 * @return the province_id
	 */
	public String getProvince_id() {
		return province_id;
	}

	/**
	 * @param province_id the province_id to set
	 */
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}

	/**
	 * @return the province_name
	 */
	public String getProvince_name() {
		return province_name;
	}

	/**
	 * @param province_name the province_name to set
	 */
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	/**
	 * @return the city_id
	 */
	public String getCity_id() {
		return city_id;
	}

	/**
	 * @param city_id the city_id to set
	 */
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	/**
	 * @return the city_name
	 */
	public String getCity_name() {
		return city_name;
	}

	/**
	 * @param city_name the city_name to set
	 */
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	/**
	 * @return the district_id
	 */
	public String getDistrict_id() {
		return district_id;
	}

	/**
	 * @param district_id the district_id to set
	 */
	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}

	/**
	 * @return the district_name
	 */
	public String getDistrict_name() {
		return district_name;
	}

	/**
	 * @param district_name the district_name to set
	 */
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
    
    
}
