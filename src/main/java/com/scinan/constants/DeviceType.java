package com.scinan.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 设备类型定义
 * 
 * @project SNDataCenterServer
 * @class com.scinan.constant.DeviceType
 * @copyright www.scinan.com
 * @author Eric
 * @date Jan 16, 2016
 * @description
 */
public class DeviceType {
	// 智科
	public static final String KEY_1036_TEMPERATURE_CONTROLLER = "temperature_controller";// 温控器
	public static final String KEY_1036_AIR_CONDITION_CONTROLLER = "air_condition_controller";// 空调
	public static final String KEY_1036_RECORDER = "recorder";// 记录仪
	
	public static final int TYPE_1036_TEMPERATURE_CONTROLLER = 5;// 温控器
	public static final int TYPE_1036_AIR_CONDITION_CONTROLLER = 6;// 空调
	public static final int TYPE_1036_RECORDER = 7;// 记录仪
	
	// 全状态数据长度(以“,”分隔)
	public static final Map<String, Integer> LENGTH_1036_S00 = new HashMap<String, Integer>();
	static {
		LENGTH_1036_S00.put(KEY_1036_TEMPERATURE_CONTROLLER, 35);
		LENGTH_1036_S00.put(KEY_1036_AIR_CONDITION_CONTROLLER, 20);
//		LENGTH_1036_S00.put(KEY_1036_RECORDER, );
	}
}
