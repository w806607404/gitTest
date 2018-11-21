package com.scinan.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @since 设备信息实体类
 * @author kim
 *
 */
public class DeviceInfo implements Serializable{
	private static final long serialVersionUID = 3478406286945564609L;
	private String id;
	private String title;
	private String about;
	private String tags;
	private String gps_name;
	private String lat;
	private String lon;
	private Integer door_type;
	private Integer public_type;
	private Date update_time;
	private Date create_time;
	private Integer type;
	//extend attribute
	private String extend;
	private String online;
	private String ip;
	private String device_key;
	private String materials_id;
	private String chip_type;
	private String company_id;
	private String user_name;
	private Date join_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getGps_name() {
		return gps_name;
	}
	public void setGps_name(String gps_name) {
		this.gps_name = gps_name;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public Integer getDoor_type() {
		return door_type;
	}
	public void setDoor_type(Integer door_type) {
		this.door_type = door_type;
	}
	public Integer getPublic_type() {
		return public_type;
	}
	public void setPublic_type(Integer public_type) {
		this.public_type = public_type;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
    /**
     * @return 返回 device_key
     */
    public String getDevice_key() {
        return device_key;
    }
    /**
     * @param 对device_key进行赋值
     */
    public void setDevice_key(String device_key) {
        this.device_key = device_key;
    }
    /**
     * @return 返回 materials_id
     */
    public String getMaterials_id() {
        return materials_id;
    }
    /**
     * @param 对materials_id进行赋值
     */
    public void setMaterials_id(String materials_id) {
        this.materials_id = materials_id;
    }
    /**
     * @return 返回 chip_type
     */
    public String getChip_type() {
        return chip_type;
    }
    /**
     * @param 对chip_type进行赋值
     */
    public void setChip_type(String chip_type) {
        this.chip_type = chip_type;
    }
    /**
     * @return 返回 company_id
     */
    public String getCompany_id() {
        return company_id;
    }
    /**
     * @param 对company_id进行赋值
     */
    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Date getJoin_time() {
		return join_time;
	}
	public void setJoin_time(Date join_time) {
		this.join_time = join_time;
	}
    
}
