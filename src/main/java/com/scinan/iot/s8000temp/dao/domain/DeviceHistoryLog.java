package com.scinan.iot.s8000temp.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class DeviceHistoryLog implements Serializable {
	public static final int OPT_CONTROL = 0;// 手动或App控制
	public static final int OPT_TIMER = 1;// 定时操作
	public static final int OPT_COUNTDOWN = 2;// 倒计时操作
	
	public static final int MSG_SWITCH = 1;// 开关控制
	public static final int MSG_STATUS = 2;// 设备状态
	public static final int MSG_ALARM = 3;// 故障告警

	private static final long serialVersionUID = 8395132109513787715L;
	private Long id;
	private String device_id;
	private String type;// 设备类型
	private String sensor_id;
	private int msg_type;// 信息类型, 1:开关控制；2:设备状态；3:故障告警
	private int opt_type;// 操作类型, 0:手动或App控制；1:定时操作；2:倒计时操作
	private String content;
	private String source_data;
	private Date create_time;
	private String user_path;
	private String dealer_name;  //经销商归属名称

	@Override
	public String toString() {
		return "DeviceHistoryLog [id=" + id + ", device_id=" + device_id //
				+ ", type=" + type + ", sensor_id=" + sensor_id + ", msg_type=" //
				+ msg_type + ", opt_type=" + opt_type + ", content=" + content //
				+ ", source_data=" + source_data + ", create_time=" //
				+ create_time + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getSensor_id() {
		return sensor_id;
	}

	public void setSensor_id(String sensor_id) {
		this.sensor_id = sensor_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(int msg_type) {
		this.msg_type = msg_type;
	}

	public int getOpt_type() {
		return opt_type;
	}

	public void setOpt_type(int opt_type) {
		this.opt_type = opt_type;
	}

	public String getSource_data() {
		return source_data;
	}

	public void setSource_data(String source_data) {
		this.source_data = source_data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser_path() {
		return user_path;
	}

	public void setUser_path(String user_path) {
		this.user_path = user_path;
	}

	public String getDealer_name() {
		return dealer_name;
	}

	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}

	
	
}
