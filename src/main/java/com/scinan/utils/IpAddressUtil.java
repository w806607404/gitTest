package com.scinan.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
/**
 * 获取ip工具类
 * @project datacenter
 * @class com.scinan.utils.IpAddressUtil
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月10日
 * @description
 */
public class IpAddressUtil {
	static Logger logger = Logger.getLogger(IpAddressUtil.class);
	public static String getIpAddress(HttpServletRequest request) throws IOException {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址X-Real-IP
//		String ip = request.getHeader("X-Forwarded-For");
//		logger.info("X-Forwarded-For - ip=" + ip);
		String ip = request.getHeader("X-Real-IP");
		logger.info("X-Real-IP - ip=" + ip);

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				logger.info("Proxy-Client-IP - ip=" + ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				logger.info("WL-Proxy-Client-IP - ip=" + ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				logger.info("HTTP_CLIENT_IP - ip=" + ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				logger.info("HTTP_X_FORWARDED_FOR - ip=" + ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				logger.info("getRemoteAddr - ip=" + ip);
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}

}
