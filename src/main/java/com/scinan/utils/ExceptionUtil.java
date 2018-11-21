package com.scinan.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 * @project datacenter
 * @class com.scinan.utils.ExceptionUtil
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月10日
 * @description
 */
public class ExceptionUtil {

	/**
	 * 获取异常的堆栈信息
	 * 
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}
}
