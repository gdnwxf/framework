package com.wch.test;

import java.io.IOException;
import java.util.Properties;

public class StaticTest {
	
	final   Properties properties = new Properties();
	
	  {
		try {
			properties.load(MyClassloader.class.getClassLoader().getResourceAsStream("wch.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public   void load() {
		try {
		     properties.load(MyClassloader.class.getClassLoader().getResourceAsStream("wch.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public StaticTest() {
		System.out.println("触发实例化");
	}



	private String id  = "100";
	private String name = "zs" ;
	
	
	private   String prefix = "prefix";
	public       String  abc = properties.getProperty("wch") + "abc";
	
	private   String xixi = prefix + "xixi" ;
	public   String getPrefix() {
		return prefix;
	}
 
	public  String getAbc() {
		return abc;
	}
	public  String getXixi() {
		return xixi;
	}
 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
