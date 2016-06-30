/**
 * 
 */
package com.wch.test;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wch
 * @date 2015年7月7日 下午1:29:43
 */
public class DateUtilsTest {

	
	public static void main(String[] args) throws ParseException {
			System.out.println(parseDate("2015-14-8 14:12:33"));
	}

	/**
	 * @param source
	 * @return 
	 * @throws ParseException
	 */
	public static Date parseDate(String source,String ...patterns) throws ParseException  {
			SimpleDateFormat sdf = new SimpleDateFormat();
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
