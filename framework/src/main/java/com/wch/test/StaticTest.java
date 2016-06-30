package com.wch.test;

import java.io.IOException;
import java.util.Properties;

public class StaticTest {
	
	final static Properties properties = new Properties();
	
	static {
		try {
			properties.load(StaticTest.class.getClassLoader().getResourceAsStream("wch.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void load() {
		try {
		     properties.load(StaticTest.class.getClassLoader().getResourceAsStream("wch.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static String prefix = "prefix";
	public final  static  String  abc = "wch" + "abc";
	
	private static String xixi = prefix + "xixi" ;
	public static String getPrefix() {
		return prefix;
	}
	public static void setPrefix(String prefix) {
		StaticTest.prefix = prefix;
	}
	public static String getAbc() {
		return abc;
	}
	public static String getXixi() {
		return xixi;
	}
	public static void setXixi(String xixi) {
		StaticTest.xixi = xixi;
	}
	
	
	

}
