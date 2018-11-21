package com.scinan.bean.s00;

import com.scinan.constants.DeviceType;

/**
 * 智科温控器全状态Bean
 * 
 * @project SNDataCenterServer
 * @class com.scinan.bean.s00.Device10365Bean
 * @copyright www.scinan.com
 * @author Eric
 * @date Jan 19, 2016
 * @description
 */
public class Device10365Bean extends Device1036Bean {
	private String temprature;// 当前温度
	private String compressor_state;// 压缩机状态
	private String compressor_delayed_state;// 压缩机延时状态
	private String defrosting_state;// 化霜状态
	private String defrosting_delayed_state;// 化霜延时状态
	private String fan_state;// 风机状态
	private String fan_delayed_state;// 风机延时状态
//	private String s20;// 制冷开机温度 -20~70摄氏度
//	private String s21;// 制冷停机温度 -20~70℃
//	private String s22;// 制热开机温度 -20~70℃
//	private String s23;// 制热停机温度 -20~70℃
	private String s03;//设置温度
	private String s24;// 控制回差 0.5℃～+9.0℃
	private String s25;// 压缩机开机延时 0～15分钟
	private String s26;// 仪表通电超温报警延时 0～24.0小时
	private String s27;// 超温报警值 0～50.0℃
	private String s28;// 超温报警延时 0～120分钟
	private String s29;// 库温传感器校正 -10.0℃～+10.0℃
	private String s30;// 数位显示 1:小数；0:整数
	private String s31;// 比例输出停止时间 1～60分钟
	private String s32;// 比例输出开启时间 0～60分钟
	private String s33;// 超上限报警值 超下限报警值～85.0℃
	private String s34;// 超下限报警值 -40.0℃～超上限报警值
	private String s35;// 超温报警模式 0：绝对温度；1：相对温度
	private String s36;// 报警音 0：关；1：开
	private String s38;// 化霜周期 0～120小时
	private String s40;// 化霜后滴水时间 1～120分钟
	private String s41;// 除霜时段显示模式 0：库温 1：dEF 2：除霜开始时库温
	private String s42;// 化霜类型 0：电热化霜 1：热气化霜 2：风除霜
	private String s43;// 化霜停止温度 -40.0℃～+50.0℃
	private String s44;// 蒸发器传感器选择 0：禁用；1：启用
	private String s45;// 化霜传感器温度校正 -10.0℃～+10.0℃
	private String s46;// 风机工作模式 -180～300秒
	private String s47;// 化霜滴水后风机延时 0～300秒 
	private String s48;// 风机受控模式 0：压缩机时间 1：化霜探头温度2：库温与化霜温差
	private String s49;// 风机开启温度 -40.0℃～风机停止温度
	private String s50;// 风机停止温度 风机开启温度～50.0℃
	private String s51;// 风机开启温差值 0℃～+50.0℃
	private String s37;// 化霜时间 1～120分钟
	private String s39;// 化霜周期计时方式 0：控制器累积 1：压缩机累积工作
	
	public Device10365Bean() {
		this.device_type = DeviceType.TYPE_1036_TEMPERATURE_CONTROLLER;
	}

	/*
	 /S00/1/温度，压缩机开，压缩机延时开，化霜开，化霜延时开，风机开，风机延时开，
	 制冷开机温度，制冷停机温度，制热开机温度，制热停机温度，控制回差，压缩机开机延时，
	 仪表通电超温报警延时，超温报警值，超温报警延时，库温传感器校正，数位显示小数，
	 比例输出停止时间，比例输出开启时间，超上限报警值，超下限报警值，超温报警模式，报警音，
	 化霜周期，化霜后滴水时间，除霜时段显示模式，化霜类型，化霜停止温度，
	 蒸发器传感器选择，化霜传感器温度校正，风机工作模式，化霜滴水后风机延时，
	 风机受控模式，风机开启温度，风机停止温度，风机开启温差值，工作模式，化霜时间，化霜周期计时方式	 
	修改为 ------------------------> 
	 /S00/1/温度，压缩机开，化霜开，风机开，设置温度，控制回差，压缩机开机延时，超温报警延时，
	  超温报警值，仪表通电超温报警延时，库温传感器校正，数位显示小数，比例输出停止时间，比例输出开启时间，
	  超上限报警值，超下限报警值，超温报警模式绝对温度，报警音开，化霜周期，化霜后滴水时间，除霜时段显示模式库温，
	  化霜类型电热化霜，化霜停止温度，蒸发器传感器选择，化霜传感器温度校正，风机工作模式，化霜滴水后风机延时，
	  风机受控模式压缩机时间，风机开启温度，风机停止温度，风机开启温差值，工作模式制热，化霜时间，化霜周期计时方式，当前是否存在故障
	 */
	public Device10365Bean(String s00) {
		String[] tmp = s00.split(",");
		if(tmp.length != DeviceType.LENGTH_1036_S00.get(DeviceType.KEY_1036_TEMPERATURE_CONTROLLER).intValue()) {
			throw new RuntimeException("设备(device_id=" + device_id //
					+ ", device_type=" + device_type + ")s00数据(" + s00 + ")异常");
		}
		this.device_type = DeviceType.TYPE_1036_TEMPERATURE_CONTROLLER;
		int idx = 0;// 数组索引
		this.temprature = trim0(tmp[idx++]);
		this.compressor_state = trim0(tmp[idx++]);
		this.defrosting_state = trim0(tmp[idx++]);
		this.fan_state = trim0(tmp[idx++]);
		this.s03 = trim0(tmp[idx++]);
		this.s24 = trim0(tmp[idx++]);
		this.s25 = trim0(tmp[idx++]);
		this.s28 = trim0(tmp[idx++]);
		this.s27 = trim0(tmp[idx++]);
		this.s26 = trim0(tmp[idx++]);
		this.s29 = trim0(tmp[idx++]);
		this.s30 = trim0(tmp[idx++]);
		this.s31 = trim0(tmp[idx++]);
		this.s32 = trim0(tmp[idx++]);
		this.s33 = trim0(tmp[idx++]);
		this.s34 = trim0(tmp[idx++]);
		this.s35 = trim0(tmp[idx++]);
		this.s36 = trim0(tmp[idx++]);
		this.s38 = trim0(tmp[idx++]);
		this.s40 = trim0(tmp[idx++]);
		this.s41 = trim0(tmp[idx++]);
		this.s42 = trim0(tmp[idx++]);
		this.s43 = trim0(tmp[idx++]);
		this.s44 = trim0(tmp[idx++]);
		this.s45 = trim0(tmp[idx++]);
		this.s46 = trim0(tmp[idx++]);
		this.s47 = trim0(tmp[idx++]);
		this.s48 = trim0(tmp[idx++]);
		this.s49 = trim0(tmp[idx++]);
		this.s50 = trim0(tmp[idx++]);
		this.s51 = trim0(tmp[idx++]);
		this.s02 = trim0(tmp[idx++]);
		this.s37 = trim0(tmp[idx++]);
		this.s39 = trim0(tmp[idx++]);
		this.is_default = trim0(tmp[idx++]);
	}

	public String getCompressor_state() {
		return compressor_state;
	}

	public void setCompressor_state(String compressor_state) {
		this.compressor_state = compressor_state;
	}

	public String getCompressor_delayed_state() {
		return compressor_delayed_state;
	}

	public void setCompressor_delayed_state(String compressor_delayed_state) {
		this.compressor_delayed_state = compressor_delayed_state;
	}

	public String getDefrosting_state() {
		return defrosting_state;
	}

	public void setDefrosting_state(String defrosting_state) {
		this.defrosting_state = defrosting_state;
	}

	public String getDefrosting_delayed_state() {
		return defrosting_delayed_state;
	}

	public void setDefrosting_delayed_state(String defrosting_delayed_state) {
		this.defrosting_delayed_state = defrosting_delayed_state;
	}

	public String getFan_state() {
		return fan_state;
	}

	public void setFan_state(String fan_state) {
		this.fan_state = fan_state;
	}

	public String getFan_delayed_state() {
		return fan_delayed_state;
	}

	public void setFan_delayed_state(String fan_delayed_state) {
		this.fan_delayed_state = fan_delayed_state;
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

	public String getS24() {
		return s24;
	}

	public void setS24(String s24) {
		this.s24 = s24;
	}

	public String getS25() {
		return s25;
	}

	public void setS25(String s25) {
		this.s25 = s25;
	}

	public String getS26() {
		return s26;
	}

	public void setS26(String s26) {
		this.s26 = s26;
	}

	public String getS27() {
		return s27;
	}

	public void setS27(String s27) {
		this.s27 = s27;
	}

	public String getS28() {
		return s28;
	}

	public void setS28(String s28) {
		this.s28 = s28;
	}

	public String getS29() {
		return s29;
	}

	public void setS29(String s29) {
		this.s29 = s29;
	}

	public String getS30() {
		return s30;
	}

	public void setS30(String s30) {
		this.s30 = s30;
	}

	public String getS31() {
		return s31;
	}

	public void setS31(String s31) {
		this.s31 = s31;
	}

	public String getS32() {
		return s32;
	}

	public void setS32(String s32) {
		this.s32 = s32;
	}

	public String getS33() {
		return s33;
	}

	public void setS33(String s33) {
		this.s33 = s33;
	}

	public String getS34() {
		return s34;
	}

	public void setS34(String s34) {
		this.s34 = s34;
	}

	public String getS35() {
		return s35;
	}

	public void setS35(String s35) {
		this.s35 = s35;
	}

	public String getS36() {
		return s36;
	}

	public void setS36(String s36) {
		this.s36 = s36;
	}

	public String getS37() {
		return s37;
	}

	public void setS37(String s37) {
		this.s37 = s37;
	}

	public String getS38() {
		return s38;
	}

	public void setS38(String s38) {
		this.s38 = s38;
	}

	public String getS39() {
		return s39;
	}

	public void setS39(String s39) {
		this.s39 = s39;
	}

	public String getS40() {
		return s40;
	}

	public void setS40(String s40) {
		this.s40 = s40;
	}

	public String getS41() {
		return s41;
	}

	public void setS41(String s41) {
		this.s41 = s41;
	}

	public String getS42() {
		return s42;
	}

	public void setS42(String s42) {
		this.s42 = s42;
	}

	public String getS43() {
		return s43;
	}

	public void setS43(String s43) {
		this.s43 = s43;
	}

	public String getS44() {
		return s44;
	}

	public void setS44(String s44) {
		this.s44 = s44;
	}

	public String getS45() {
		return s45;
	}

	public void setS45(String s45) {
		this.s45 = s45;
	}

	public String getS46() {
		return s46;
	}

	public void setS46(String s46) {
		this.s46 = s46;
	}

	public String getS47() {
		return s47;
	}

	public void setS47(String s47) {
		this.s47 = s47;
	}

	public String getS48() {
		return s48;
	}

	public void setS48(String s48) {
		this.s48 = s48;
	}

	public String getS49() {
		return s49;
	}

	public void setS49(String s49) {
		this.s49 = s49;
	}

	public String getS50() {
		return s50;
	}

	public void setS50(String s50) {
		this.s50 = s50;
	}

	public String getS51() {
		return s51;
	}

	public void setS51(String s51) {
		this.s51 = s51;
	}

}
