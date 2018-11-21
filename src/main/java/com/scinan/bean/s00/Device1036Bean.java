package com.scinan.bean.s00;

/**
 * 智科设备全状态bean
 * 
 * @project SNDataCenterServer
 * @class com.scinan.bean.s00.Device1036Bean
 * @copyright www.scinan.com
 * @author Eric
 * @date Jan 18, 2016
 * @description
 */
public class Device1036Bean {
	protected String device_id;// 设备ID
	protected String company_id;// 厂商ID
	protected int device_type;// 设备类型, DeviceType中定义
	protected String s00;// 全状态
	protected String s01;// 开关
	protected String s02;// 工作模式
	protected String is_default;// 当前是否存在故障

	protected String trim0(String str) {
		int idx = 0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i) != '0') {
				idx = i;
				break;
			}
		}
		String tmp = str.substring(idx);
		try {
			if(tmp.indexOf(".") > -1) {
				return String.valueOf(Double.parseDouble(tmp));
			} else {
				return String.valueOf(Integer.parseInt(tmp));
			}
		} catch (Exception e) {
			return tmp;
		}
	}
	
	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public int getDevice_type() {
		return device_type;
	}

	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}

	public String getS00() {
		return s00;
	}

	public void setS00(String s00) {
		this.s00 = s00;
	}

	public String getS01() {
		return s01;
	}

	public void setS01(String s01) {
		this.s01 = s01;
	}

	public String getS02() {
		return s02;
	}

	public void setS02(String s02) {
		this.s02 = s02;
	}

	public String getIs_default() {
		return is_default;
	}

	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}

}
