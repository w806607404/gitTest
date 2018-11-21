package com.scinan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaterOrderUtil {
	private static Logger logger = LoggerFactory.getLogger(WaterOrderUtil.class);  
	static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    // 生成订单号
	public static String generateOrderId(String company_id) {
        // 取出redis中记录的该厂商下的订单流水号
        String keyOrderNo = "order_no2_" + company_id;
        long tmp = RedisUtil.incr(keyOrderNo, 1);
        String valueOrderNo = StringUtil.fillZero(String.valueOf(tmp), 8);
        logger.debug(keyOrderNo + " : " + valueOrderNo);
        
        // 订单号 = 厂商ID + 日期(yyyyMMdd) + 订单流水号(从0累加,与日期无关)
        String order_id = company_id + format.format(new Date()) + valueOrderNo;
        return order_id;
	}
}
