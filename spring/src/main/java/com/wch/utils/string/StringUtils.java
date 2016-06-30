package com.wch.utils.string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.wch.utils.date.InputStringFilter;

/**
 * The Class StringUtils.
 * 
 * @author gdnwxf
 * @version 1.0
 * @since 2014
 * @email gdnwxf@qq.com
 * @date 2015-1-22 18:49:29
 */
public class StringUtils {
	
	/**
	 * Sub begin string.
	 *
	 * @param string the string
	 * @param begin the begin
	 * @return the string
	 */
	public static String subBeginString( String  string, int begin ){
		return subString(string, begin, 0);
	}
	
	/**
	 * Sub string.
	 *
	 * @param string the string
	 * @param offset the offset
	 * @return the string
	 */
	public static String subString( String  string, int offset ){
		 return subString(string, 0, offset);
	}
	
	/**
	 * Sub string.
	 *
	 * @param string the string
	 * @param begin the begin
	 * @param offset the offset
	 * @return the string
	 */
	public static String subString( String  string , int begin, int offset ){
		if(string == null ||string.length() == 0 ) return "";
		if(begin>string.length() || offset > string.length()) return string;
		if(begin<0 || offset < string.length()) return string.substring(0, offset);
		return "";
	}
	
	
	/**
	 * 转换编码集.
	 *
	 * @param string the string
	 * @param srcEncoding the src encoding
	 * @param tgtEncoding the tgt encoding
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public static String changeEncoding(String string,String srcEncoding, String tgtEncoding) throws UnsupportedEncodingException {
		return new String(string.getBytes(srcEncoding),tgtEncoding);
	}

	/**
	 * 去除字符串第一个字符 然后将第二个字符设置成大写.
	 * 
	 * @param str the str
	 * @return the string
	 */
	public static String remove1Upper2(String str) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length() - 1);
		sb.append(str.substring(1));
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * Join.
	 * 
	 * @param str the str
	 * @param middle the middle
	 * @return the string
	 */
	public static String join(String[] str, String middle) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			if (i < str.length - 1) {
				sBuffer.append(str[i] + middle);
			}
			sBuffer.append(str[i]);
		}
		return sBuffer.toString();
	}

	/**
	 * Join.
	 * 
	 * @param objects the objects
	 * @param middle the middle
	 * @return the string
	 */
	public static String join(Object[] objects, String middle) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < objects.length; i++) {
			if (i < objects.length - 1) {
				sBuffer.append(objects[i].toString() + middle);
			}
			sBuffer.append(objects[i]);
		}
		return sBuffer.toString();
	}

	/**
	 * 判断字符串是否是空白.
	 * 
	 * @param string the string
	 * @return true, if is blank
	 */
	public static boolean isBlank(String string) {
		if (string != null && string.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 改变第一个字符的大小写的状态.
	 * 
	 * @param str the str
	 * @param capitalize the capitalize
	 * @return the string
	 */
	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		} else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}

	/**
	 * Capitalize.
	 * 
	 * @param str the str
	 * @return the string
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/**
	 * 获取字符串的扩展名.
	 * 
	 * @param str the str
	 * @return the extension
	 */
	public static String getExtension(String str) {
		if (str == null)
			return null;
		int i = str.lastIndexOf('.');
		if (i >= 0) {
			return str.substring(i + 1);
		}
		return null;
	}

	/**
	 * 将字符串中的某些字符转换成大写.
	 * 
	 * @param str the str
	 * @param a the a
	 * @return the string
	 */
	public static String char2Upper(String str, Integer... a) {
		StringBuilder sb = new StringBuilder(str);
		for (int i = 0; i < a.length; i++) {
			if (a[i] > str.length()) {
				throw new ArrayIndexOutOfBoundsException(a[i] + " 数组越界");
			}
			sb.setCharAt(a[i], Character.toUpperCase(sb.charAt(a[i])));
		}
		return sb.toString();

	}

	/**
	 * 将字符串中的某些字符转换成小写.
	 * 
	 * @param str the str
	 * @param a the a
	 * @return the string
	 */
	public static String char2Lower(String str, Integer... a) {
		StringBuilder sb = new StringBuilder(str);
		for (int i = 0; i < a.length; i++) {
			if (a[i] > str.length()) {
				throw new ArrayIndexOutOfBoundsException(a[i] + " 数组越界");
			}
			sb.setCharAt(a[i], Character.toLowerCase(sb.charAt(a[i])));
		}
		return sb.toString();

	}

	/**
	 * 判断一个字符串是否为空.
	 * 
	 * @param str the str
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		if (str.trim().length() == 0)
			return true;
		return false;
	}

	/**
	 * Parses the to integer.
	 * 
	 * @param str the str
	 * @return the integer
	 */
	public static Integer parseToInteger(String str) {
		if (isEmpty(str)) {
			throw new NullPointerException(str + "is null");
		}
		return Integer.parseInt(str);
	}

	/**
	 * Parses the to float.
	 * 
	 * @param str the str
	 * @return the float
	 */
	public static Float parseToFloat(String str) {
		if (isEmpty(str)) {
			throw new NullPointerException(str + "is null");
		}
		return Float.parseFloat(str);
	}

	/**
	 * Parses the to double.
	 * 
	 * @param str the str
	 * @return the double
	 */
	public static Double parseToDouble(String str) {
		if (isEmpty(str)) {
			throw new NullPointerException(str + "is null");
		}
		return Double.parseDouble(str);
	}

	/**
	 * Formate string.
	 * 
	 * @param str the str
	 * @param params the params
	 * @return the string
	 */
	public static String formateString(String str, String... params) {
		for (int i = 0; i < params.length; i++) {
			str = str.replace("{" + i + "}", params[i] == null ? "" : params[i]);
		}
		return str;
	}

	/**
	 * 去除字符串中的.后面 的字符（即去掉字符串的扩展名）
	 * 
	 * @param str the str
	 * @return the string
	 */
	public static String removeExtension(String str) {
		if (str == null)
			return null;
		int i = str.lastIndexOf('.');
		if (i >= 0) {
			return str.substring(0, i);
		}
		return null;
	}

	/**
	 * Equals ignore case.
	 * 
	 * @param operator the operator
	 * @param string the string
	 * @return true, if successful
	 */
	public static boolean equalsIgnoreCase(String operator, String string) {
		if (string.equalsIgnoreCase(operator)) {
			return true;
		}
		return false;
	}

	/**
	 * 在index 处插入.
	 *
	 * @param content the content
	 * @param index the index
	 * @param indexContent the index content
	 * @return the string
	 */
	public static String insertStrAndReplaceChar(String content, int index, String indexContent) {
		return new StringBuilder(content).insert(index, indexContent).toString();
	}

	/**
	 * you should end with $; Gets the string array.
	 * 
	 * @return the string array
	 */
	public static String[] getStringArray() {
		Scanner scanner = new Scanner(System.in);
		try {
			List<String> strings = new ArrayList<String>();
			while (scanner.hasNextLine()) {
				String temp = scanner.nextLine();
				if ("$".equals(temp)) {
					break;
				} else {
					strings.add(temp);
				}
			}
			return strings.toArray(new String[1]);
		} finally {
			scanner.close();
		}
	}
	
	/**
	 * you should end with $; Gets the string array.
	 *
	 * @param inputStringFilter the input string filter
	 * @return the string array
	 */
	public static String[] getStringArray(InputStringFilter inputStringFilter) {
		Scanner scanner = new Scanner(System.in);
		try {
			List<String> inputList = new ArrayList<String>();
			while (scanner.hasNextLine()) {
				String temp = scanner.nextLine();
				if ("$".equals(temp)) {
					break;
				} else {
					if(!inputStringFilter.handle(inputList,temp)){
						continue;
					}else {
						break;
					}
				}
			}
			return inputList.toArray(new String[1]);
		} finally {
			scanner.close();
		}
	}
	
	/**
	 * Gets the string list.
	 *
	 * @param inputStringFilter the input string filter
	 * @return the string list
	 */
	public static List<String> getStringList(InputStringFilter inputStringFilter) {
		Scanner scanner = new Scanner(System.in);
		try {
			List<String> inputList = new ArrayList<String>();
			while (scanner.hasNextLine()) {
				String temp = scanner.nextLine();
				if ("$".equals(temp)) {
					break;
				} else {
					if(!inputStringFilter.handle(inputList,temp)){
						continue;
					}else {
						break;
					}
				}
			}
			return  inputList;
		} finally {
			scanner.close();
		}
	}
	
}
