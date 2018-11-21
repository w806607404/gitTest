package com.scinan.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class MD5HashUtil {

	private MessageDigest md = null;

	private static MD5HashUtil md5 = null;

	private static final char[] hexChars = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * Constructor is private so you must use the getInstance method
	 */
	private MD5HashUtil() throws NoSuchAlgorithmException {
		md = MessageDigest.getInstance("MD5");
	}

	/**
	 * This returns the singleton instance
	 */
	public static MD5HashUtil getInstance() throws NoSuchAlgorithmException {

		if (md5 == null) {
			md5 = new MD5HashUtil();

		}

		return (md5);
	}

	public static String hashCode(String dataToHash)
			throws NoSuchAlgorithmException {
		return getInstance().hashData(dataToHash.getBytes());
	}

	public static String hashCode(byte[] dataToHash)
			throws NoSuchAlgorithmException {
		return getInstance().hashData(dataToHash);
	}

	public String hashData(byte[] dataToHash) {
		return hexStringFromBytes((calculateHash(dataToHash))).toLowerCase();
	}

	private byte[] calculateHash(byte[] dataToHash)

	{
		md.update(dataToHash, 0, dataToHash.length);

		return (md.digest());
	}

	public String hexStringFromBytes(byte[] b)

	{

		String hex = "";

		int msb;

		int lsb = 0;
		int i;

		// MSB maps to idx 0

		for (i = 0; i < b.length; i++)

		{

			msb = ((int) b[i] & 0x000000FF) / 16;

			lsb = ((int) b[i] & 0x000000FF) % 16;
			hex = hex + hexChars[msb] + hexChars[lsb];
		}
		return (hex);
	}
	
	
	/**
	 * 功能说明：MD5加密
	 * @param password
	 * @return
	 */
	public static String generatePassword(String password) {
		try {
			String tmp = MD5HashUtil.getInstance().hashCode(password);
			String salt = tmp.substring(8, 19);
			return MD5HashUtil.getInstance().hashCode(password + salt).toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public static void main(String[] args)throws Exception{
		String pwd = MD5HashUtil.getInstance().hashCode("123456");
		System.out.println(generatePassword(pwd));
	}
}
