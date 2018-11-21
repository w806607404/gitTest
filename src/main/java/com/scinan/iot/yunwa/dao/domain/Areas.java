package com.scinan.iot.yunwa.dao.domain;


import java.io.Serializable;

/**
 * Areas对象.
 * @author yangkun
 *
 */
public class Areas implements Serializable{
	private static final long serialVersionUID = -7153093930057331235L;

    
    /**
     * 省市区（县）编号.
     */
    private String id;
    
    /**
     * 省市区（县）名称.
     */
    private String area_name;
    
    /**
     * 父级省市区（县）编号，第一级为0.
     */
    private String parent_id;
    
    /**
     * 省市区（县）简称.
     */
    private String short_name;
    
    /**
     * 经度.
     */
    private String lng;
    
    /**
     * 纬度.
     */
    private String lat;
    
    /**
     * 层级.
     */
    private Integer level;
    
    /**
     * 区域定位.
     */
    private String position;
    
    /**
     * 区域排序.
     */
    private Integer sort;
    
    
    //业务字段
    private String district_id;
    private String district;
    
    private String city_id;
    private String city;
    
    private String province_id;
    private String province;

    
    public String getId() {
        return this.id;
    }	
  
    public void setId(String id) {
        this.id = id;
    }
    
    public String getArea_name() {
        return this.area_name;
    }	
  
    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }
    
    public String getParent_id() {
        return this.parent_id;
    }	
  
    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
    
    public String getShort_name() {
        return this.short_name;
    }	
  
    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }
    
    public String getLng() {
        return this.lng;
    }	
  
    public void setLng(String lng) {
        this.lng = lng;
    }
    
    public String getLat() {
        return this.lat;
    }	
  
    public void setLat(String lat) {
        this.lat = lat;
    }
    
    public Integer getLevel() {
        return this.level;
    }	
  
    public void setLevel(Integer level) {
        this.level = level;
    }
    
    public String getPosition() {
        return this.position;
    }	
  
    public void setPosition(String position) {
        this.position = position;
    }
    
    public Integer getSort() {
        return this.sort;
    }	
  
    public void setSort(Integer sort) {
        this.sort = sort;
    }

	public String getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince_id() {
		return province_id;
	}

	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
