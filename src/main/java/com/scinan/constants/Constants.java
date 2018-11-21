package com.scinan.constants;

import com.scinan.base.config.ConfigureFile;

public class Constants {
	
	public static String device_count_str = (String)ConfigureFile.getInstance().getProperty("device_count");
	public static String min_num = (String)ConfigureFile.getInstance().getProperty("min_num");
	public static String max_num = (String)ConfigureFile.getInstance().getProperty("max_num");
	
    public static final String UPLOAD_URL = "upload_url";
    
    public static final String DOWNLOAD_URL = "download_url";
    
    public static final String BASE_FILE_UPLOAD_PATH = "/home/moosefsdata/"; // 文件上传的目录

    public static final String APP_DEVICE_TYPE = "app_device_type";
    
    /**
	 * 状态——待发货/支付类型——线下转账
	 */
	public static final Integer DDEDDO_STATUS_UNSHIPPING = 0;
	/**
	 * 状态——已发货/支付类型——分红余额
	 */
	public static final Integer DDEDDO_STATUS_SHIPPINGED = 1;
	/**
	 * 状态——已完成/支付类型——补贴余额
	 */
	public static final Integer DDEDDO_STATUS_FINISHED = 2;
	/**
	 * 状态——待确认
	 */
	public static final Integer DDEDDO_STATUS_CONFIRMED = 3;
	/**
	 * 状态——未确认
	 */
	public static final Integer DDEDDO_STATUS_UNCONFIRMED = 4;
	/**
	 * 状态——缺货
	 */
	public static final Integer DDEDDO_STATUS_STOCKOUT = 5;
    
	//伯央的度：用户使用产品次数多的所获红包金额
	public static final String DEDO_REDENVELOPE_AMOUNT = "DEDO_REDENVELOPE_AMOUNT";
    
	
}
