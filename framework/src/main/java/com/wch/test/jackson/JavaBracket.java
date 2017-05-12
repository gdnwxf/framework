package com.wch.test.jackson;

import java.util.HashMap;
import java.util.Map;

public class JavaBracket {

	public static void main(String[] args) {
		
		Map<String, Object>  map = new HashMap<String ,Object>(){ 
			{
				put("231", "321321");
				put("2321", "321321");
				put("2341", "321321");
			}
		};
		
		System.out.println(map);
	}
}
