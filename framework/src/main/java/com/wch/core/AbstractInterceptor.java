package com.wch.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义一个自己的拦截器
 * 
 * @author GDNWXF
 * @date 2014年7月6日 下午4:39:43
 */
public interface AbstractInterceptor {

	  boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception;

	  void afterCompletion(HttpServletRequest request, HttpServletResponse response)  throws Exception;

}
