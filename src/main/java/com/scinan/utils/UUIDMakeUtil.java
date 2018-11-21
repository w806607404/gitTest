package com.scinan.utils;

import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

import com.scinan.push.mqtt.message.util.Function;


public class UUIDMakeUtil {
	public static String MACHINE_PIECE = "";
	public static String PROCESS_PIECE = "";
	
	public static String makeRegisterActivationCode() {
		if (MACHINE_PIECE.equals("")) {
			// 4bytes
			MACHINE_PIECE = Function.bytesToHexString(Function.intToByteArray4(getMachinePiece())).toLowerCase();
		}
		if (PROCESS_PIECE.equals("")) {
			// 2bytes
			PROCESS_PIECE = Function.bytesToHexString(Function.intToByteArray2(getProcessPiece())).toLowerCase();
		}
		StringBuilder sb = new StringBuilder();

		sb.append(MACHINE_PIECE).append(PROCESS_PIECE);
		// 2bytes 随即数字
		sb.append(Function.bytesToHexString(Function.intToByteArray2(new Random().nextInt(65535))));

		UUID uuid = UUID.randomUUID();
		sb.append(uuid.toString().replace("-", ""));
		return sb.toString();
	}

	public static String digits = "0123456789";
	public static String makeMessageValidCode(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<6;i++){
			int rand=(int)(Math.random()*10);
			sb.append(digits.charAt(rand));
		}
		return sb.toString();
	}
	static DateFormat DF = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public static String makeOrderId(String company_id){
        String keyOrderNo = "busi_order_" + company_id;
        long tmp = RedisUtil.incr(keyOrderNo, 1);
        String valueOrderNo = StringUtil.fillZero(String.valueOf(tmp), 6);
		return DF.format(new Date()) + valueOrderNo;
	}
	public static String makeOrderIdPay(String company_id){
		return "P"+ makeOrderId(company_id);
	}
	public static String makeUUID(){
		StringBuilder sb = new StringBuilder();
		UUID uuid = UUID.randomUUID();
		sb.append(uuid.toString().replace("-", ""));
		return sb.toString();
	}
	
	public static String[] makeApiKeyAndSecretCode() {
		String[] result = new String[2];
		if (MACHINE_PIECE.equals("")) {
			// 4bytes
			MACHINE_PIECE = Function.bytesToHexString(Function.intToByteArray4(getMachinePiece()));
		}
		if (PROCESS_PIECE.equals("")) {
			// 2bytes
			PROCESS_PIECE = Function.bytesToHexString(Function.intToByteArray2(getProcessPiece()));
		}
		StringBuilder sb = new StringBuilder();

		sb.append(MACHINE_PIECE).append(PROCESS_PIECE);
		// 2bytes 随即数字
		sb.append(Function.bytesToHexString(Function.intToByteArray2(new Random().nextInt(65535))));

		UUID uuid = UUID.randomUUID();
		sb.append(uuid.toString().replace("-", "").toUpperCase());
		result[0] = sb.toString();
		result[1] = RandomStringUtils.randomAlphanumeric(12);
		return result;
	}
	
	public static String makeExportUID(){
		if (MACHINE_PIECE.equals("")) {
			// 4bytes
			MACHINE_PIECE = Function.bytesToHexString(Function.intToByteArray4(getMachinePiece()));
		}
		if (PROCESS_PIECE.equals("")) {
			// 2bytes
			PROCESS_PIECE = Function.bytesToHexString(Function.intToByteArray2(getProcessPiece()));
		}
		StringBuilder sb = new StringBuilder();

		sb.append(MACHINE_PIECE).append("-").append(PROCESS_PIECE).append("-");
		sb.append(Function.bytesToHexString(Function.intToByteArray2(new Random().nextInt(65535)))).append("-");

		UUID uuid = UUID.randomUUID();
		sb.append(uuid.toString().replace("-", ""));
		return sb.toString();
	}
	
	private static int getMachinePiece() {
		try {
			StringBuilder sb = new StringBuilder();
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();// NetworkInterface此类表示一个由名称和分配给此接口的
			while (e.hasMoreElements()) {
				NetworkInterface ni = e.nextElement();
				sb.append(ni.toString());
			}
			return sb.toString().hashCode() << 16; // 将得到所有接口的字符串进行取散列值
		} catch (Exception e) {
			String tempName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
			return tempName.hashCode() << 16;
		}
	}

	private static int getProcessPiece() {
		int processId = new java.util.Random().nextInt();
		try {
			processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode();// RuntimeMXBean是Java虚拟机的运行时系统的管理接口，这里是返回表示正在运行的
																										// Java
																										// 虚拟机的名称，并进行取散列值。
		} catch (Throwable t) {
		}

		ClassLoader loader = UUIDMakeUtil.class.getClassLoader();
		int loaderId = loader != null ? System.identityHashCode(loader) : 0;

		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toHexString(processId));
		sb.append(Integer.toHexString(loaderId));
		return sb.toString().hashCode() & 0xFFFF;
	}
	
	
	public static void main(String[] args){
		System.out.println(makeOrderId("1234"));

	}
}
