package com.wch.otherproject.stringtemplate;

import java.util.HashMap;
import java.util.Map;

import org.antlr.stringtemplate.StringTemplate;

import com.wch.pojo.User;

public class StringTemplateTest {

	public static void main(String[] args) {
		User us = new User();
		us.setUsername("xxsssx");
		us.setUserId("321321321");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("User", "321321");
		StringTemplate st = new StringTemplate("$User.username$,$User.userId$");
		st.setAttribute("User", us);
		System.out.println(st.toString());
		
		System.out.println();

	}
}
