package com.scinan.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("all")
public class StringUtil {
	/*
	 * 生成随机字符串使用到的字符, 去除易混淆字符
	 * 出现不明确字符, 辨识顺序如下
	 * 数字 -> 大写字母 -> 小写字母
	 * 如出现 o 则为 数字 0
	 * 如出现 c 则为 大写字母c
	 */
	private final static char[] chars = new char[]{
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
		'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
		'W', 'X', 'Y', 'Z', 'a', 'b', 'd', 'e', 'h', 'i',
		'j', 'm', 'n', 'r', 't', 'u', 'y'
	};

	public static final String AMPERSAND = "&";

	public static final String APOSTROPHE = "'";

	public static final String AT = "@";

	public static final String BACK_SLASH = "\\";

	public static final String BLANK = "";

	public static final String CDATA_OPEN = "<![CDATA[";

	public static final String CDATA_CLOSE = "]]>";

	public static final String CLOSE_BRACKET = "]";

	public static final String CLOSE_CURLY_BRACE = "}";

	public static final String COLON = ":";

	public static final String COMMA = ",";

	public static final String DASH = "-";

	public static final String EQUAL = "=";

	public static final String GREATER_THAN = ">";

	public static final String FORWARD_SLASH = "/";

	public static final String LESS_THAN = "<";

	public static final String NBSP = "&nbsp;";

	public static final String NEW_LINE = "\n";

	public static final String NOT_EQUAL = "!=";

	public static final String NULL = "null";

	public static final String OPEN_BRACKET = "[";

	public static final String OPEN_CURLY_BRACE = "{";

	public static final String PERCENT = "%";

	public static final String PERIOD = ".";

	public static final String PIPE = "|";

	public static final String PLUS = "+";

	public static final String POUND = "#";

	public static final String QUESTION = "?";

	public static final String QUOTE = "\"";

	public static final String SEMICOLON = ";";

	public static final String SLASH = FORWARD_SLASH;

	public static final String SPACE = " ";

	public static final String STAR = "*";

	public static final String TILDE = "~";

	public static final String UNDERLINE = "_";

	public static final String EMPTY_STRING = "";

	public static boolean contains(String s, String text) {
		return contains(s, text, COMMA);
	}

	public static boolean contains(String s, String text, String delimiter) {
		if ((s == null) || (text == null) || (delimiter == null)) {
			return false;
		}

		if (!s.endsWith(delimiter)) {
			s += delimiter;
		}

		int pos = s.indexOf(delimiter + text + delimiter);

		if (pos == -1) {
			if (s.startsWith(text + delimiter)) {
				return true;
			}

			return false;
		}

		return true;
	}

	public static int count(String s, String text) {
		if ((s == null) || (text == null)) {
			return 0;
		}

		int count = 0;

		int pos = s.indexOf(text);

		while (pos != -1) {
			pos = s.indexOf(text, pos + text.length());
			count++;
		}

		return count;
	}

	public static boolean endsWith(String s, char end) {
		return startsWith(s, (new Character(end)).toString());
	}

	public static boolean endsWith(String s, String end) {
		if ((s == null) || (end == null)) {
			return false;
		}

		if (end.length() > s.length()) {
			return false;
		}

		String temp = s.substring(s.length() - end.length(), s.length());

		if (temp.equalsIgnoreCase(end)) {
			return true;
		} else {
			return false;
		}
	}

	public static String extractFirst(String s, String delimiter) {
		if (s == null) {
			return null;
		} else {
			String[] array = split(s, delimiter);

			if (array.length > 0) {
				return array[0];
			} else {
				return null;
			}
		}
	}

	public static String extractLast(String s, String delimiter) {
		if (s == null) {
			return null;
		} else {
			String[] array = split(s, delimiter);

			if (array.length > 0) {
				return array[array.length - 1];
			} else {
				return null;
			}
		}
	}

	public static String lowerCase(String s) {
		if (s == null) {
			return null;
		} else {
			return s.toLowerCase();
		}
	}

	public static String merge(List list) {
		return merge(list, COMMA);
	}

	public static String merge(List list, String delimiter) {
		return merge((String[]) list.toArray(new String[0]), delimiter);
	}

	public static String merge(String[] array) {
		return merge(array, COMMA);
	}

	public static String merge(String[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < array.length; i++) {
			sb.append(array[i].trim());

			if ((i + 1) != array.length) {
				sb.append(delimiter);
			}
		}

		return sb.toString();
	}

	public static String read(ClassLoader classLoader, String name)
			throws IOException {

		return read(classLoader.getResourceAsStream(name));
	}

	public static String read(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sb = new StringBuffer();
		String line = null;

		while ((line = br.readLine()) != null) {
			sb.append(line).append('\n');
		}

		br.close();

		return sb.toString().trim();
	}

	public static String replace(String s, char oldSub, char newSub) {
		return replace(s, oldSub, new Character(newSub).toString());
	}

	public static String replace(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		char[] c = s.toCharArray();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < c.length; i++) {
			if (c[i] == oldSub) {
				sb.append(newSub);
			} else {
				sb.append(c[i]);
			}
		}

		return sb.toString();
	}

	public static String replace(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		int y = s.indexOf(oldSub);

		if (y >= 0) {
			StringBuffer sb = new StringBuffer();

			int length = oldSub.length();
			int x = 0;

			while (x <= y) {
				sb.append(s.substring(x, y));
				sb.append(newSub);
				x = y + length;
				y = s.indexOf(oldSub, x);
			}

			sb.append(s.substring(x));

			return sb.toString();
		} else {
			return s;
		}
	}

	public static String replace(String s, String[] oldSubs, String[] newSubs) {
		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replace(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	public static String reverse(String s) {
		if (s == null) {
			return null;
		}

		char[] c = s.toCharArray();
		char[] reverse = new char[c.length];

		for (int i = 0; i < c.length; i++) {
			reverse[i] = c[c.length - i - 1];
		}

		return new String(reverse);
	}

	public static String shorten(String s) {
		return shorten(s, 20);
	}

	public static String shorten(String s, int length) {
		return shorten(s, length, "...");
	}

	public static String shorten(String s, String suffix) {
		return shorten(s, 20, suffix);
	}

	public static String shorten(String s, int length, String suffix) {
		if (s == null || suffix == null) {
			return null;
		}

		if (s.length() > length) {
			for (int j = length; j >= 0; j--) {
				if (Character.isWhitespace(s.charAt(j))) {
					length = j;

					break;
				}
			}

			s = s.substring(0, length) + suffix;
		}

		return s;
	}

	public static String[] split(String s) {
		return split(s, COMMA);
	}

	public static String[] split(String s, String delimiter) {
		if (s == null || delimiter == null) {
			return new String[0];
		}

		s = s.trim();

		if (!s.endsWith(delimiter)) {
			s += delimiter;
		}

		if (s.equals(delimiter)) {
			return new String[0];
		}

		List nodeValues = new ArrayList();

		if (delimiter.equals("\n") || delimiter.equals("\r")) {
			try {
				BufferedReader br = new BufferedReader(new StringReader(s));

				String line = null;

				while ((line = br.readLine()) != null) {
					nodeValues.add(line);
				}

				br.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} else {
			int offset = 0;
			int pos = s.indexOf(delimiter, offset);

			while (pos != -1) {
				nodeValues.add(s.substring(offset, pos));

				offset = pos + delimiter.length();
				pos = s.indexOf(delimiter, offset);
			}
		}

		return (String[]) nodeValues.toArray(new String[0]);
	}

	public static boolean[] split(String s, String delimiter, boolean x) {
		String[] array = split(s, delimiter);
		boolean[] newArray = new boolean[array.length];

		for (int i = 0; i < array.length; i++) {
			boolean value = x;

			try {
				value = Boolean.valueOf(array[i]).booleanValue();
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static double[] split(String s, String delimiter, double x) {
		String[] array = split(s, delimiter);
		double[] newArray = new double[array.length];

		for (int i = 0; i < array.length; i++) {
			double value = x;

			try {
				value = Double.parseDouble(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static float[] split(String s, String delimiter, float x) {
		String[] array = split(s, delimiter);
		float[] newArray = new float[array.length];

		for (int i = 0; i < array.length; i++) {
			float value = x;

			try {
				value = Float.parseFloat(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static int[] split(String s, String delimiter, int x) {
		String[] array = split(s, delimiter);
		int[] newArray = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			int value = x;

			try {
				value = Integer.parseInt(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static long[] split(String s, String delimiter, long x) {
		String[] array = split(s, delimiter);
		long[] newArray = new long[array.length];

		for (int i = 0; i < array.length; i++) {
			long value = x;

			try {
				value = Long.parseLong(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static short[] split(String s, String delimiter, short x) {
		String[] array = split(s, delimiter);
		short[] newArray = new short[array.length];

		for (int i = 0; i < array.length; i++) {
			short value = x;

			try {
				value = Short.parseShort(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static boolean startsWith(String s, char begin) {
		return startsWith(s, (new Character(begin)).toString());
	}

	public static boolean startsWith(String s, String start) {
		if ((s == null) || (start == null)) {
			return false;
		}

		if (start.length() > s.length()) {
			return false;
		}

		String temp = s.substring(0, start.length());

		if (temp.equalsIgnoreCase(start)) {
			return true;
		} else {
			return false;
		}
	}

	public static String trimLeading(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isWhitespace(s.charAt(i))) {
				return s.substring(i, s.length());
			}
		}

		return BLANK;
	}

	public static String trimTrailing(String s) {
		for (int i = s.length() - 1; i >= 0; i--) {
			if (!Character.isWhitespace(s.charAt(i))) {
				return s.substring(0, i + 1);
			}
		}

		return BLANK;
	}

	public static String upperCase(String s) {
		if (s == null) {
			return null;
		} else {
			return s.toUpperCase();
		}
	}

	public static String wrap(String text) {
		return wrap(text, 80, "\n");
	}

	public static String wrap(String text, int width, String lineSeparator) {
		if (text == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();

		try {
			BufferedReader br = new BufferedReader(new StringReader(text));

			String s = BLANK;

			while ((s = br.readLine()) != null) {
				if (s.length() == 0) {
					sb.append(lineSeparator);
				} else {
					String[] tokens = s.split(SPACE);
					boolean firstWord = true;
					int curLineLength = 0;

					for (int i = 0; i < tokens.length; i++) {
						if (!firstWord) {
							sb.append(SPACE);
							curLineLength++;
						}

						if (firstWord) {
							sb.append(lineSeparator);
						}

						sb.append(tokens[i]);

						curLineLength += tokens[i].length();

						if (curLineLength >= width) {
							firstWord = true;
							curLineLength = 0;
						} else {
							firstWord = false;
						}
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return sb.toString();
	}

	public static boolean isNotEmpty(String string) {
		return string != null && string.length() > 0 && string != "";
	}

	/**
	 * 计算真正截取的字节数
	 * 
	 * @param bytes
	 * @param byteCount
	 * @return
	 */
	public static int getRealBytes(byte[] bytes, int byteCount) {
		int realByteCount = 0;// 真正要截取的字节数
		if (bytes[byteCount - 1] < 0) {
			int chinaCharCount = 0;// 汉字字节的个数
			for (int i = 0; i < byteCount; i++) {// 计算出截取的字节中，小于0的字节个数
				// System.out.println(bytes[i]);
				if (bytes[i] < 0)
					chinaCharCount += 1;
			}
			// System.out.println(chinaCharCount);
			realByteCount = (chinaCharCount % 2 == 0) ? byteCount
					: byteCount - 1;
		} else {
			realByteCount = byteCount - 1;
		}
		return realByteCount;
	}
	
	/**
	 * 生成指定长度的随机字符串
	 * @param len
	 * @return
	 */
	public static String generate(int len) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<len;i++) {
			sb.append(chars[r.nextInt(chars.length)]);
		}
		return sb.toString();
	}
	
	/**
	 * Mac地址递增
	 * @param mac
	 * @return
	 */
	public static String incrementMac(String mac) {
		long l = Long.parseLong(mac, 16);
		l++;
		return Long.toString(l, 16).toUpperCase();
	}

	public static boolean isNull(String value) {
		return value == null || "".equals(value.trim()) || "null".equals(value.trim());
	}
	
	public static String isValidateStr(String value) {
		if( value == null || "".equals(value.trim()) || "null".equals(value.trim())){
			return "";
		}
		return value;
	}
	
	
	 /**
     * <一句话功能简述>4位数字和字母组合校验
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNumberAndLetter(String str)
    {
        java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("^[a-z0-9A-Z]{4}$");
        java.util.regex.Matcher match=pattern.matcher(str);
        if(match.matches()==false){
             return false;
        }else{
             return true;
        }
    }

	/**
	 * 给src字符串前面补0到len长度
	 * @param src
	 * @param len
	 * @return
	 */
	public static String fillZero(String src, int len) {
		if(src == null) return src;
		StringBuffer sb = new StringBuffer();
		int needZero = len - src.length();
		if(needZero > 0) {
			for(int i=0;i<needZero;i++) {
				sb.append("0");
			}
		}
		sb.append(src);
		return sb.toString();
	}
    
    public static String getOrDefault(String name, String defualtStr) {
		return "".equals(name) || name==null ? defualtStr : name;
	}


	public static boolean isNotNulls(String ... strings) {
		boolean isLeagle = true;
		for (String value :strings) {
			isLeagle = isLeagle && isNotEmpty(value);
		}
		return isLeagle;
	}
	
	public static String getUtilJoinString(String[] value){
		if(null!=value && value.length>0){
			return StringUtils.join(value, ",");
		}
		return "";
	}
	
	public static String getSpecialUtilJoinString(String strVal){
		String str = "";
		if(null!=strVal){
			String[] value =  strVal.split(",");
			for(int i=0;i<value.length;i++){
				if(i==0){
					str = "'"+value[i]+"'";
				}else{
					str += ",'"+value[i]+"'";
				}
			}
		}
		return str;
	}
	
	
	public static String getSpecialUtilJoinValue(String strVal){
		String str = "";
		if(null!=strVal){
			String[] value =  strVal.split(",");
			for(int i=0;i<value.length;i++){
				if(i==0){
					str = value[i];
				}else{
					str += ","+value[i]+"";
				}
			}
		}
		return str;
	}
	
}
