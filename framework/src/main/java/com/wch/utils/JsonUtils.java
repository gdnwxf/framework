package com.wch.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * The Class JsonUtils.
 *
 * @author gdnwxf
 * @version 1.0
 * @since 2014
 * @email gdnwxf@qq.com
 * @date 2015-1-22 18:50:16
 */
public class JsonUtils {

	/** The Constant DATE_PATTERN. */
	private final static String DATE_PATTERN = "yyyy-MM-dd";
	
	/**
	 * Write json to page.
	 *
	 * @param response the response
	 * @param obj the obj
	 */
	public static void writeJsonToPage(HttpServletResponse response,Object obj)   {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		writeJsonToPage(response, obj, null);
	}
	
	/**
	 * Write json to page.
	 *
	 * @param response the response
	 * @param obj the obj
	 * @param datePartten the date partten
	 */
	public static void writeJsonToPage(HttpServletResponse response,Object obj,String datePartten)   {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		if(datePartten==null){
			datePartten  = DATE_PATTERN;
		}
		String s = JSON.toJSONStringWithDateFormat(obj,datePartten,SerializerFeature.WriteDateUseDateFormat);
		
		try {
			response.getWriter().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
