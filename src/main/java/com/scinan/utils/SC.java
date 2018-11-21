package com.scinan.utils;

/**
 * 系统常量
 * @author Eric
 * @date Jun 3, 2015
 * @description ScinanConstant
 */
public class SC {
	// 页面数据显示数量
	public static final int PAGE_SIZE = 20;
	
	// 订单项密码长度
	public static final int ORDER_ITEM_PASSWORD_LENGTH = 32;
	
	// 缓存数据前缀规则定义
	public static final String CACHE_PROPERTY_KEY_PREFIX = "SYSTEM_PROP_";// 配置项
	
	// 系统配置项key
	public static final String SYS_PROP_MAC_SN = "MAC_SN";// MAC地址流水号
	public static final String SYS_PROP_MATERIAL_SN = "MATERIAL_SN";// 订单项流水号
	
	// 厂家订单项流水号前缀
	public static final String FACTORY_ORDERITEM_SN_PREFIX = "FACTORY_ORDERITEM_SN_";
	public static final String FACTORY_ORDERITEM_SN_BEGINNING = "0000001";
	
	// 错误信息key
	public static final String ERR_MSG = "err_msg";

	// Session信息key
	public static final String SESSION_USER_ID = "sess_user_id";// 用户id
	public static final String SESSION_USER_LOGINID = "sess_user_loginid";// 用户名
	public static final String SESSION_VERIFYCODE = "sess_verify_code";// 验证码
	
	//威特龙系统用户唯一标识
	public static final String VTL_SYSTEM_USER_TOKEN = "VTL_DATACENTER_";
	
}
