package com.wch.utils.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 有关于时间的工具类.
 *
 * @author GDNWXF
 */
public class DateUtils {
	
	/** The Constant DEFAULT_PARTTEN. */
	public final static String DEFAULT_PARTTEN = "yyyy-MM-dd HH:mm:ss";
	
	/** The sdf. */
	private  static SimpleDateFormat sdf = new SimpleDateFormat();
	
	/**
	 * 默认解析表达式
	 */
	private final static String [] patterns = new String[] {"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm"};
 
	/**
	 * 可以判断任何为null的对象.
	 *
	 * @param source the source
	 * @return true, if is empty
	 */
	public static boolean isEmpty(Object source){
		if(source==null){
			return true;
		} 
		if(source instanceof String){
			if(((String) source).length()==0){
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Checks if is not empty.
	 *
	 * @param source the source
	 * @return true, if is not empty
	 */
	public static boolean isNotEmpty(Object source){
		return !isEmpty(source);
	}
	
	/**
	 * Parses the date.
	 *
	 * @param source the source
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date parseDate(String source) throws ParseException{
		if(isEmpty(source)){
			throw new NullPointerException(source+"is null");
		}
		return parseDate(source, patterns);
	}
	
	/**
	 * Formart date.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String formartDate(Date date){
	    if(isEmpty(date)){
	    	throw new NullPointerException(date+"is null");
	    }
		sdf.applyPattern(DEFAULT_PARTTEN);
		return sdf.format(date);
	}
	
	/**
	 * Formart date.
	 *
	 * @param date the date
	 * @param pattern the pattern
	 * @return the string
	 */
	public static String formartDate(Date date,String pattern){
		if(isEmpty(date)||isEmpty(pattern)){
			throw new NullPointerException(date+" or "+pattern+"is null");
		}
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 将Date转成Timestamp.
	 *
	 * @param date the date
	 * @return the timestamp
	 */
	public static Timestamp dateToStamp(Date date){
		return Timestamp.valueOf(formartDate(date));
	}
	
	/**
	 * 将字符串转成Timestamp.
	 *
	 * @param str the str
	 * @return the timestamp
	 */
	public static Timestamp strToStamp(String str){
		return Timestamp.valueOf(str);
	}
	
	/**
	 * 将Timestamp转成Date.
	 *
	 * @param timestamp the timestamp
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date stampToDate(Timestamp timestamp) throws ParseException{
		return parseDate(timestamp.toString());
	}

	/**
	 * 日历的加减 翻转日历 
	 *
	 * @param date the date
	 * @param strings the strings  y/M/d/H/m/s +/- num;
	 * @return the date
	 */
	public static Date changeDate(Date date, String ...strings ) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		for (String string : strings) {
			if(string.charAt(1)=='+') {
				calendar.add(parseChar(string.charAt(0)), Integer.valueOf(string.substring(2)));
			}else if(string.charAt(1)=='-'){
				calendar.add(parseChar(string.charAt(0)), -Integer.valueOf(string.substring(2)));
			}else if(string.charAt(1)=='R'){
				calendar.roll(parseChar(string.charAt(0)), Integer.valueOf(string.substring(2)));
			}
		}
		return calendar.getTime();
	}
	
	/**
	 * @param charAt
	 * @return
	 */
	private static int parseChar(char charAt) {
		int temp = -100;
		switch (charAt) {
		case 'y':
			temp = Calendar.YEAR;
			break;
		case 'M':
			temp = Calendar.MONTH;
			break;
		case 'd':
			temp = Calendar.DAY_OF_MONTH;
			break;
		case 'H':
			temp = Calendar.HOUR_OF_DAY;
			break;
		case 'm':
			temp = Calendar.MINUTE;
			break;
		case 's':
			temp = Calendar.SECOND;
			break;
		default:
			break;
		}
		
		return temp;
	}
	 
	 
	/**
	 * @param source
	 * @return 
	 * @throws ParseException
	 */
	public static Date parseDate(String source,String ...patterns) throws ParseException  {
			SimpleDateFormat sdf = new SimpleDateFormat();
			//解析的位置
			ParsePosition pos = new ParsePosition(0);
			for (String temp : patterns) {
				sdf.setLenient(true);
				sdf.applyPattern(temp);
				pos.setIndex(0);
				Date date = sdf.parse(source,pos);
				if(date!=null && pos.getIndex() == source.length()) {
					return date;
				}
			}
			throw new ParseException("无法解析", source.indexOf(pos.getIndex()));
	}
}
