package com.scinan.utils;

import java.util.UUID;

/**
 * 对象生成器
 * 
 * @project datacenter
 * @class com.scinan.utils.Generator
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月22日
 * @description
 */
public class Generator {

	/**
	 * 订单项SN编号递增
	 * @param sn
	 * @return
	 */
	private static String incrementSn(String sn) {
//		System.out.println(" 增前 : " + sn);
		long l = Long.parseLong(sn, 16);
//		System.out.println(l);
		l++;
		String r = fillZero(7, Long.toHexString(l).toUpperCase());
//		System.out.println(" 增后 : " + r);
		return r;
	}
	
	public static void main(String[] args) {
		System.out.println(incrementSn("00000cf"));
	}
	
	/**
	 * 生成物料编号
	 * @param time
	 * @param i
	 * @return
	 */
	public static String generateMaterialSn(String sn) {
		Long l = Long.parseLong(sn);
		l++;
		return fillZero(13, String.valueOf(l));
	}

	/**
	 * 生成密码
	 * @return
	 */
	private static String generatePwd() {
		return StringUtil.generate(SC.ORDER_ITEM_PASSWORD_LENGTH);
	}

	/**
	 * 给数字num前补零, 到len位长度
	 * @param len
	 * @param num
	 * @return
	 */
	private static String fillZero(int len, String num) {
		StringBuilder sb = new StringBuilder();
		int cLen = String.valueOf(num).length();
		for(int i=0;i<len-cLen;i++) {
			sb.append("0");
		}
		sb.append(num);
		return sb.toString();
	}
	
	public static String UUID(){
		StringBuilder sb = new StringBuilder();
		UUID uuid = UUID.randomUUID();
		sb.append(uuid.toString().replace("-", ""));
		return sb.toString();
	}

	/**
	 * 生成物料编号<p>
	 * 规则：厂家编号(4) + 当前年月(6) + 随机串(6)
	 * @param code
	 * @return
	 */
//	public static String getMaterialSn(String code) {
//		return code + DateUtil.getDate("yyyyMM") + StringUtil.generate(6);
//	}
}
