
 
package com.wch.x;

import java.util.HashMap;
import java.util.Map;



 
public class MapContainer {
 
	private final static Map<String, Object> map =   new  HashMap<String, Object>();
	
	
	public static void registerDriver(String  object ){
		map.put("xx", object);
	}
	
	public static void print() {
		System.out.println(map);
	}
	
}
