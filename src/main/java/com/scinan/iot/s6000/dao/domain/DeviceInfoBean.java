package com.scinan.iot.s6000.dao.domain;

import java.io.Serializable;
import java.util.Date;

import com.scinan.iot.s1000.dao.domain.FactoryBean;

/**
 * 设备信息实体类
 * 
 * @project datacenter
 * @class com.scinan.iot.s6000.dao.domain.DeviceInfoBean
 * @copyright www.scinan.com
 * @author Zola
 * @date 2016年7月22日
 * @description
 */
public class DeviceInfoBean implements Serializable{
	
	private static final long serialVersionUID = 8949064594944376421L;
    
	/**
	 * 主键id
	 */
	private String id;	
	
	/**
	 * 设备ID
	 */
	private String device_id;	
	
	/**
	 * 设备名称
	 */
	private String title;	
	/**
	 * 
	 */
	private String about;
	/**
	 * 
	 */
	private String tags;
	/**
	 * 
	 */
	private String gps_name;
	/**
	 * 
	 */
	private String lat;
	/**
	 * 
	 */
	private String lon;
	/**
	 * 
	 */
	private Short door_type;
	/**
	 * 
	 */
	private Short public_type;
	/**
	 * 更新时间
	 */
	private Date update_time;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 
	 */
	private Integer type;
	/**
	 * 
	 */
	private String product_id;
	/**
	 * 
	 */
	private String device_key;
	/**
	 * 厂商id
	 */
	private String company_id;
	/**
	 * 
	 */
	private String materials_id;
	
	private String version;
	
	private String _join;
	
	private Date join_time;
	
	private String chip_type;
	
	private String model;
	
	private String hardware_version;
	
	//业务字段
	private FactoryBean factoryBean;
	
	private DeviceTypeBean deviceTypeBean;
	
	private String online;
	
	private String name;
	
	private Integer value;
	
	private String province;
	
	private String city;
	
	private String country;
	
	private String mac;
	
	private String device_name;
	
	private Long online_time;
	
	private Integer online_stat;
	
	private Integer bind_user;
	
	private Integer mstype;
	
	private String user_mobile;
	
	private String user_nickname;
	
	private String sex;
	
	private String user_email;
	
	private Long user_digit;
	
	private String code;
	
	private String model_desc;
	
	/**
	 * 类型名称
	 */
	private String type_name;
	
	/**
	 * 类型总数
	 */
	private Integer num;
	
	/**
	 * 离线标志
	 */
	private String offline;
	
	/**
	 *激活标志
	 */
	private String activation;
	
	/**
	 * 未激活标志
	 */
	private String noactive;
	
	private String merchant_id;
	
	private String merchant_name;
	
	private String country_code;
	
	private String province_code;
	
	/**
	 * 上下线时间
	 */
	private String on_off_time;
	
	/**
	 * 设备归属用户路径
	 */
	private String user_path;
	
	/**
	 * 归属经销商
	 */
	private String dealer;
	
	/**
	 * 经销商名称
	 */
	private String dealer_name;
	
	/**
	 * 运行开始时间
	 */
	private String run_start_time;
	
	/**
	 * 运行结束时间
	 */
	private String run_end_time;
	
	/**
	 * 总运行时间
	 */
	private Integer pay_time;
	
	/**
	 * PM2.5
	 */
	private String pm25;
	
	
	/**
	 * 滤芯更换次数
	 */
	private Integer lvxin_num;
	
	/**
	 * 滤芯剩余百分比
	 */
	private String lv_percentage;
	
	
	/**
	 * 甲醛值
	 */
	private String formaldehyde;
	
	/**
	 * 剩余时间
	 */
	private String re_time;
	
	
	/**
	 * 已使用时间
	 */
	private String use_time;
	
	
	/**
	 * 房间号
	 */
	private String room_number;
	
	/**
	 * 水位
	 */
	private String water_level;
	
	/**
	 * CCID
	 */
	private String ccid;
	
	
	/**
	 * 设备出水量
	 */
	private String out_water;
	
	/**
	 * 剩余租赁天数
	 */
	private Integer lease_day;
	
	/**
	 * 设备运行状态
	 */
	private String run_status;
	
	/**
	 * 开关机状态
	 */
	private String open_c_status;
	
	
	/**
	 * 滤芯剩余寿命
	 */
	private String lx_residual_life;
	
	/**
	 * 滤网状态
	 */
	private String filter_status;
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
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
	public Short getDoor_type() {
		return door_type;
	}
	public void setDoor_type(Short door_type) {
		this.door_type = door_type;
	}
	public Short getPublic_type() {
		return public_type;
	}
	public void setPublic_type(Short public_type) {
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
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getDevice_key() {
		return device_key;
	}
	public void setDevice_key(String device_key) {
		this.device_key = device_key;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getMaterials_id() {
		return materials_id;
	}
	public void setMaterials_id(String materials_id) {
		this.materials_id = materials_id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String get_join() {
		return _join;
	}
	public void set_join(String _join) {
		this._join = _join;
	}
	public Date getJoin_time() {
		return join_time;
	}
	public void setJoin_time(Date join_time) {
		this.join_time = join_time;
	}
	public String getChip_type() {
		return chip_type;
	}
	public void setChip_type(String chip_type) {
		this.chip_type = chip_type;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getHardware_version() {
		return hardware_version;
	}
	public void setHardware_version(String hardware_version) {
		this.hardware_version = hardware_version;
	}
	public FactoryBean getFactoryBean() {
		return factoryBean;
	}
	public void setFactoryBean(FactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}
	public DeviceTypeBean getDeviceTypeBean() {
		return deviceTypeBean;
	}
	public void setDeviceTypeBean(DeviceTypeBean deviceTypeBean) {
		this.deviceTypeBean = deviceTypeBean;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public Long getOnline_time() {
		return online_time;
	}
	public void setOnline_time(Long online_time) {
		this.online_time = online_time;
	}
	public Integer getOnline_stat() {
		return online_stat;
	}
	public void setOnline_stat(Integer online_stat) {
		this.online_stat = online_stat;
	}
	public Integer getBind_user() {
		return bind_user;
	}
	public void setBind_user(Integer bind_user) {
		this.bind_user = bind_user;
	}
	public Integer getMstype() {
		return mstype;
	}
	public void setMstype(Integer mstype) {
		this.mstype = mstype;
	}
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public Long getUser_digit() {
		return user_digit;
	}
	public void setUser_digit(Long user_digit) {
		this.user_digit = user_digit;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "DeviceInfoBean [id=" + id + ", title=" + title + ", about="
				+ about + ", tags=" + tags + ", gps_name=" + gps_name
				+ ", lat=" + lat + ", lon=" + lon + ", door_type=" + door_type
				+ ", public_type=" + public_type + ", update_time="
				+ update_time + ", create_time=" + create_time + ", type="
				+ type + ", product_id=" + product_id + ", device_key="
				+ device_key + ", company_id=" + company_id + ", materials_id="
				+ materials_id + ", version=" + version + ", _join=" + _join
				+ ", join_time=" + join_time + ", chip_type=" + chip_type
				+ ", model=" + model + ", hardware_version=" + hardware_version
				+ ", factoryBean=" + factoryBean + ", deviceTypeBean="
				+ deviceTypeBean + ", online=" + online + ", name=" + name
				+ ", value=" + value + ", province=" + province + ", city="
				+ city + ", country=" + country + ", mac=" + mac
				+ ", device_name=" + device_name + ", online_time="
				+ online_time + ", online_stat=" + online_stat + ", bind_user="
				+ bind_user + ", mstype=" + mstype + ", user_mobile="
				+ user_mobile + ", user_nickname=" + user_nickname + ", sex="
				+ sex + ", user_email=" + user_email + ", user_digit="
				+ user_digit + ", code=" + code + "]";
	}
	public String getModel_desc() {
		return model_desc;
	}
	public void setModel_desc(String model_desc) {
		this.model_desc = model_desc;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getOffline() {
		return offline;
	}
	public void setOffline(String offline) {
		this.offline = offline;
	}
	public String getActivation() {
		return activation;
	}
	public void setActivation(String activation) {
		this.activation = activation;
	}
	public String getNoactive() {
		return noactive;
	}
	public void setNoactive(String noactive) {
		this.noactive = noactive;
	}
	public String getOn_off_time() {
		return on_off_time;
	}
	public void setOn_off_time(String on_off_time) {
		this.on_off_time = on_off_time;
	}
	public String getUser_path() {
		return user_path;
	}
	public void setUser_path(String user_path) {
		this.user_path = user_path;
	}
	public String getDealer() {
		return dealer;
	}
	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	public String getDealer_name() {
		return dealer_name;
	}
	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}
	public String getRun_start_time() {
		return run_start_time;
	}
	public void setRun_start_time(String run_start_time) {
		this.run_start_time = run_start_time;
	}
	public String getRun_end_time() {
		return run_end_time;
	}
	public void setRun_end_time(String run_end_time) {
		this.run_end_time = run_end_time;
	}
	public Integer getPay_time() {
		return pay_time;
	}
	public void setPay_time(Integer pay_time) {
		this.pay_time = pay_time;
	}
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	public Integer getLvxin_num() {
		return lvxin_num;
	}
	public void setLvxin_num(Integer lvxin_num) {
		this.lvxin_num = lvxin_num;
	}
	public String getLv_percentage() {
		return lv_percentage;
	}
	public void setLv_percentage(String lv_percentage) {
		this.lv_percentage = lv_percentage;
	}
	public String getFormaldehyde() {
		return formaldehyde;
	}
	public void setFormaldehyde(String formaldehyde) {
		this.formaldehyde = formaldehyde;
	}
	public String getRe_time() {
		return re_time;
	}
	public void setRe_time(String re_time) {
		this.re_time = re_time;
	}
	public String getRoom_number() {
		return room_number;
	}
	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}
	public String getUse_time() {
		return use_time;
	}
	public void setUse_time(String use_time) {
		this.use_time = use_time;
	}
	public String getWater_level() {
		return water_level;
	}
	public void setWater_level(String water_level) {
		this.water_level = water_level;
	}
	public String getCcid() {
		return ccid;
	}
	public void setCcid(String ccid) {
		this.ccid = ccid;
	}
	public String getOut_water() {
		return out_water;
	}
	public void setOut_water(String out_water) {
		this.out_water = out_water;
	}
	public Integer getLease_day() {
		return lease_day;
	}
	public void setLease_day(Integer lease_day) {
		this.lease_day = lease_day;
	}
	public String getRun_status() {
		return run_status;
	}
	public void setRun_status(String run_status) {
		this.run_status = run_status;
	}
	public String getOpen_c_status() {
		return open_c_status;
	}
	public void setOpen_c_status(String open_c_status) {
		this.open_c_status = open_c_status;
	}
	public String getLx_residual_life() {
		return lx_residual_life;
	}
	public void setLx_residual_life(String lx_residual_life) {
		this.lx_residual_life = lx_residual_life;
	}
	public String getFilter_status() {
		return filter_status;
	}
	public void setFilter_status(String filter_status) {
		this.filter_status = filter_status;
	}
	
	
	
	
	
}