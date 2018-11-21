package com.scinan.push.mqtt.message.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

@SuppressWarnings("all")
public class Function {
	private static final Logger logger = LoggerFactory.getLogger(Function.class);

	public static byte[] intToByteArray4(int input) {
		byte[] output = new byte[4];
		output[0] = (byte) (0xFF & (input >> 24));
		output[1] = (byte) (0xFF & (input >> 16));
		output[2] = (byte) (0xFF & (input >> 8));
		output[3] = (byte) (0xFF & input);
		return output;
	}

	public static int byteArrayToInt4(byte[] input) {
		int result;
		result = (input[0] & 0xFF) << 24;
		result |= (input[1] & 0xFF) << 16;
		result |= (input[2] & 0xFF) << 8;
		result |= (input[3] & 0xFF);
		return result;
	}

	public static byte[] intToByteArray3(int input) {
		byte[] output = new byte[3];
		output[0] = (byte) (0xFF & (input >> 16));
		output[1] = (byte) (0xFF & (input >> 8));
		output[2] = (byte) (0xFF & input);
		return output;
	}

	public static int byteArrayToInt3(byte[] input) {
		int result;
		result = (input[0] & 0xFF) << 16;
		result |= (input[1] & 0xFF) << 8;
		result |= (input[2] & 0xFF);
		return result;
	}

	public static byte[] intToByteArray2(int input) {
		byte[] output = new byte[2];
		output[0] = (byte) (0xFF & (input >> 8));
		output[1] = (byte) (0xFF & input);
		return output;
	}

	public static int byteArrayToInt2(byte[] input) {
		int result;
		result = (input[0] & 0xFF) << 8;
		result |= (input[1] & 0xFF);
		return result;
	}

	public static byte intToByteArray1(int input) {
		return (byte) (0xFF & input);
	}

	public static int byteArrayToInt1(byte input) {
		int result;
		result = (input & 0xFF);
		return result;
	}

	public static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}
	
	public static String bytesToHexStringWithEmpty(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
			sb.append(" ");
		}
		return sb.toString();
	}
	

	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

    public static byte[] byteArrayReverse(byte[] byteData) {
        if (byteData == null) {
            return null;
        }
        byte[] result = new byte[byteData.length];
        int resultI = 0;
        for (int i = byteData.length - 1; i >= 0; i--) {
            result[resultI++] = byteData[i];
        }
        return result;
    }
	
	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	public static byte[] intToByteArray(int input) {
		byte[] output = new byte[4];
		output[0] = (byte) (0xFF & (input >> 24));
		output[1] = (byte) (0xFF & (input >> 16));
		output[2] = (byte) (0xFF & (input >> 8));
		output[3] = (byte) (0xFF & input);
		return output;
	}

	public static byte[] makeRijndealBlock(byte[] plainByteArray) {
		int length = plainByteArray.length;
		int mod = length % 16;

		byte[] sendplainByteArray = null;
		if (mod == 0) {
			sendplainByteArray = plainByteArray;
		} else {
			sendplainByteArray = new byte[length + (16 - mod)];
			System.arraycopy(plainByteArray, 0, sendplainByteArray, 0, length);
		}
		return sendplainByteArray;
	}

	public static byte[] makeRijndealSendPackage(int cipherLength,
			int plainLength, byte[] cipherByteArray, byte protocolType) {
		byte[] packageByteArray = new byte[cipherByteArray.length + 9];
		packageByteArray[0] = protocolType;
//		packageByteArray[1] = (byte)cipherLength;
//		packageByteArray[2] = (byte)plainLength;
		System.arraycopy(Function.intToByteArray4(cipherLength), 0,
				packageByteArray, 1, 4);
		System.arraycopy(Function.intToByteArray4(plainLength), 0,
				packageByteArray, 5, 4);
		System.arraycopy(cipherByteArray, 0, packageByteArray, 9,
				cipherByteArray.length);
		return packageByteArray;
	}

	public static int byteArrayToInt(byte[] input) {
		int result;
		result = (input[0] & 0xFF) << 24;
		result |= (input[1] & 0xFF) << 16;
		result |= (input[2] & 0xFF) << 8;
		result |= (input[3] & 0xFF);
		return result;
	}

	/**
	 * user this function you must sure the stream have not reach the end .in a
	 * other word you must sure the stream's byte can fill the buffer
	 *
	 * @param in
	 *            read data from the Stream
	 * @param n
	 *            how may byte will to read
	 * @return return the bytearray
	 */
	public static byte[] readStream(InputStream in, int n) {
		byte[] be = new byte[n];
		readStream(in, be);
		return be;
	}

	/**
	 * user this function you must sure the stream have not reach the end .in a
	 * other word you must sure the stream's byte can fill the buffer
	 *
	 * @param in
	 *            read data from the Stream
	 * @param buffer
	 *            used the Stream fill the buffer
	 */
	public static void readStream(InputStream in, byte[] buffer) {
		readStream(in, buffer, 0);
	}

	/**
	 * @param in
	 *            read data from the Stream
	 * @param buffer
	 *            used the Stream fill the buffer
	 * @param off
	 *            the byte read from the stream is fill from the off is the
	 *            buffer.
	 */
	public static void readStream(InputStream in, byte[] buffer, int off) {

		int numBytesToRead = buffer.length - off;

		int iByte = 0;
		int numBytesHadRead = off;
		while (numBytesToRead > 0) {
			try {
				iByte = in.read(buffer, numBytesHadRead, numBytesToRead);
			} catch (IOException e) {
				logger.error("the socket may be closed", e);
				return;
			}
			if (iByte == -1)
				break;
			numBytesHadRead += iByte;
			numBytesToRead -= iByte;
		}

		Assert.isTrue(numBytesHadRead == buffer.length);
	}

	/**
	 * @param byte1
	 *            the first of the bytearray
	 * @param byte2
	 *            the second of the byteArray
	 * @return combine the two byteArray
	 */
	public static byte[] addByte(byte[] byte1, byte[] byte2) {
		byte[] be = new byte[byte1.length + byte2.length];
		System.arraycopy(byte1, 0, be, 0, byte1.length);
		System.arraycopy(byte2, 0, be, byte1.length, byte2.length);
		return be;
	}

	public static byte[] addByte(byte[] byte1, byte[] byte2, byte[] byte3) {
		return addByte(addByte(byte1, byte2), byte3);
	}

	public static byte[] addByte(byte[] byte1, byte[] byte2, byte[] byte3,
			byte[] byte4) {
		return addByte(addByte(addByte(byte1, byte2), byte3), byte4);
	}

	public static byte[] addByte(byte[] byte1, byte[] byte2, byte[] byte3,
			byte[] byte4, byte[] byte5) {
		return addByte(addByte(addByte(addByte(byte1, byte2), byte3), byte4),
				byte5);
	}

	/*
	 * public static void savePicture(byte[] pkg) { Assert.notNull(pkg, "the
	 * input byte[] is null"); HashMap<String, Object> mapModel = new
	 * HashMap<String, Object>(); InputStream inlocal = new
	 * ByteArrayInputStream(pkg); DataInputStream reader = new
	 * DataInputStream(inlocal); String line; String[] splitLine; try {
	 * inlocal.skip(2); byte[] length_byte = new byte[4];
	 *
	 * logger.info("the total length exclude head is " + reader.readInt());
	 *
	 * do {
	 *
	 * line = reader.readLine(); logger.error("the getting line is " + line); if
	 * (line == null || line.length() == 0) { break; } splitLine =
	 * line.trim().split(":");
	 *
	 * if (line.startsWith("filelength")) { byte[] file = new
	 * byte[Integer.parseInt(splitLine[1])]; logger.debug("the length of the
	 * pitcure is :" + Integer.parseInt(splitLine[1]));
	 * Function.readStream(inlocal, file); mapModel.put("file", file); continue;
	 * } if (splitLine.length == 2) { logger.error("the getting line is " +
	 * line); mapModel.put(splitLine[0], splitLine[1]); } else { if
	 * (splitLine.length > 2) { logger.error("the getting line is " + line);
	 * logger .error("the package may be wrong, " + "the line split with : but
	 * not get 2 element"); } } } while (true); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 *
	 * logger.debug("the map is :" + mapModel.get("hsb"));
	 *
	 * logger.debug("the map is :" + mapModel.get("time"));
	 *
	 * logger.debug("begin to store picture to local"); try { FileOutputStream
	 * file = new FileOutputStream("G:/tmp/" + new Date().getTime() + ".jpg");
	 * file.write((byte[]) mapModel.get("file")); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * logger.debug("picture may have store to local"); }
	 */

	public static void saveFileToLocal(byte[] file, String dir) {

		File fe = new File(dir);
		fe.mkdirs();

		String path = dir + "/" + System.currentTimeMillis() + ".jpg";
		try {
			FileOutputStream out = new FileOutputStream(path);
			out.write(file);
			out.flush();
			out.close();
			logger.debug("代码处于测试状态,已经有图片保存到了" + path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static InputStream getStream(String path) {
		InputStream in;
		in = ClassLoader.getSystemResourceAsStream(path);
		if (in == null) {
			try {
				in = new FileInputStream(path);
			} catch (FileNotFoundException e) {
				logger.debug("the file cann't find", e);
				System.exit(0);
			}
		}
		return in;
	}

	public static void main(String[] args) {
		String abc = "abc";
		String def = "def";
		logger
				.info(new String(Function.addByte(abc.getBytes(), def
						.getBytes())));

	}

	public static Map byteToMap(byte[] pkg) {
		Assert.notNull(pkg, "the input byte[] is null");
		HashMap<String, Object> mapModel = new HashMap<String, Object>();
		InputStream in = new ByteArrayInputStream(pkg);
		DataInputStream reader = new DataInputStream(in);
		String line;
		String[] splitLine;
		try {
			in.skip(6);
			do {
				line = reader.readLine();
				if (line == null || line.length() == 0) {
					break;
				}
				splitLine = line.trim().split(":");
				if (line.startsWith("filelength")) {
					logger.debug("splitLine[1]=" + splitLine[1]);
					byte[] file = Function.readStream(in, Integer
							.parseInt(splitLine[1]));
					mapModel.put("file", file);
					continue;
				}
				if (splitLine.length == 2) {
					mapModel.put(splitLine[0], splitLine[1]);
				} else {
					if (splitLine.length > 2) {
						throw new RuntimeException(
								"the formate of the data is not right" + line);
					}
				}
			} while (true);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertTimeInMap(mapModel);
	}

	private static Map convertTimeInMap(Map map) {
		if (map.containsKey("time")) {
			String oldTime = (String) map.get("time");
			String newTime = oldTime + "000";
			map.put("time", newTime);
			return map;
		} else {
			return map;
		}
	}
}
