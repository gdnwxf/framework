package com.wch.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wch.core.AbstractInterceptor;

public class MyInterceptor implements AbstractInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	 
	
}
