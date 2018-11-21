package com.scinan.bean.s00;

import com.scinan.constants.DeviceType;

/**
 * 智科空调控制器全状态Bean
 * 
 * @project SNDataCenterServer
 * @class com.scinan.bean.s00.Device10366Bean
 * @copyright www.scinan.com
 * @author Eric
 * @date Jan 19, 2016
 * @description
 */
public class Device10366Bean extends Device1036Bean {
	private String temprature;// 当前温度
	private String s60;// 风量大小 1：高，2：中，3：低，4：自动	
	private String s61;// 按键锁 0：关, 1：开	
	private String s62;// 温度单位 0：摄氏, 1：华氏	
	private String s63;// 低温保护开关 0：关, 1：开	
	private String s64;// 低温保护设置温度 0℃～30℃ （需要出厂设置温度）	
	private String s65;// 高温保护开关 0：关，1：开	
	private String s66;// 高温保护设置温度	35℃～70℃（需要出厂设置温度）	
	private String s67;// 温度校正差值	-9℃～9℃（需要出厂设置温度）	
	private String s68;// 可编程时段设定温度	定时器设置时间温度1(预约开关；预约类型；预约时间8:00；预约温度），定时器设置时间温度2(预约开关1；预约类型2；预约时间17:00；预约温度），定时器设置时间温度3(预约开关0；预约类型1；预约时间8:00；预约温度22），定时器设置时间温度4(预约开关1；预约类型3；预约时间；预约温度22），定时器设置时间温度5(预约开关0；预约类型2；预约时间8:00；预约温度），定时器设置时间温度6(预约开关0；预约类型3；预约时间8:00；预约温度22）	
	private String s69;// 电磁阀模式 0：二线四管，1：三线二管	
	private String s03;// 空调设置温度
	
	public Device10366Bean() {
		this.device_type = DeviceType.TYPE_1036_AIR_CONDITION_CONTROLLER;
	}

	/*
	 	/S00/1/温度，控制器状态，风量，模式，
	 	定时器设置时间温度1(预约开关1；预约类型1；预约时间8:30；预约温度22），
	 	定时器设置时间温度2(预约开关1；预约类型2；预约时间17:30；预约温度），
	 	定时器设置时间温度3(预约开关0；预约类型1；预约时间8:30；预约温度22），
	 	定时器设置时间温度4(预约开关1；预约类型3；预约时间；预约温度22），
	 	定时器设置时间温度5(预约开关0；预约类型2；预约时间8:30；预约温度），
	 	定时器设置时间温度6(预约开关0；预约类型3；预约时间；预约温度22），
	 	低温保护开关，低温保护温度，高温保护开关，高温保护温度，按键锁，
	 	单位，温度校正，电磁阀模式，空调设置温度，当前是否存在故障
	 */
	public Device10366Bean(String s00)  {
		String[] tmp = s00.split(",");
		if (tmp.length != DeviceType.LENGTH_1036_S00.get(DeviceType.KEY_1036_AIR_CONDITION_CONTROLLER).intValue()) {
			throw new RuntimeException("设备(device_id=" + device_id //
					+ ", device_type=" + device_type + ")s00数据(" + s00 + ")异常");
		}
		this.device_type = DeviceType.TYPE_1036_TEMPERATURE_CONTROLLER;
		int idx = 0;// 数组索引
		this.temprature = trim0(tmp[idx++]);
		this.s01 = trim0(tmp[idx++]);
		this.s60 = trim0(tmp[idx++]);
		this.s02 = trim0(tmp[idx++]);
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<6;i++) {
			if (i>0)  sb.append(",");
			sb.append(trim0(tmp[idx++]));
		}
		this.s68 = sb.toString();
		this.s63 = trim0(tmp[idx++]);
		this.s64 = trim0(tmp[idx++]);
		this.s65 = trim0(tmp[idx++]);
		this.s66 = trim0(tmp[idx++]);
		this.s61 = trim0(tmp[idx++]);
		this.s62 = trim0(tmp[idx++]);
		this.s67 = trim0(tmp[idx++]);
		this.s69 = trim0(tmp[idx++]);
		this.s03 = trim0(tmp[idx++]);
		this.is_default = trim0(tmp[idx++]);
	}

	public String getTemprature() {
		return temprature;
	}

	public void setTemprature(String temprature) {
		this.temprature = temprature;
	}

	public String getS03() {
		return s03;
	}

	public void setS03(String s03) {
		this.s03 = s03;
	}

	public String getS60() {
		return s60;
	}

	public void setS60(String s60) {
		this.s60 = s60;
	}

	public String getS61() {
		return s61;
	}

	public void setS61(String s61) {
		this.s61 = s61;
	}

	public String getS62() {
		return s62;
	}

	public void setS62(String s62) {
		this.s62 = s62;
	}

	public String getS63() {
		return s63;
	}

	public void setS63(String s63) {
		this.s63 = s63;
	}

	public String getS64() {
		return s64;
	}

	public void setS64(String s64) {
		this.s64 = s64;
	}

	public String getS65() {
		return s65;
	}

	public void setS65(String s65) {
		this.s65 = s65;
	}

	public String getS66() {
		return s66;
	}

	public void setS66(String s66) {
		this.s66 = s66;
	}

	public String getS67() {
		return s67;
	}

	public void setS67(String s67) {
		this.s67 = s67;
	}

	public String getS68() {
		return s68;
	}

	public void setS68(String s68) {
		this.s68 = s68;
	}

	public String getS69() {
		return s69;
	}

	public void setS69(String s69) {
		this.s69 = s69;
	}

}
