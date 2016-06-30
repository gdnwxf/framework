package com.wch.connection;

import java.sql.Connection;
import java.sql.DriverManager;


public class Con1 {
	@SuppressWarnings("unused")
	private static Connection connection = null;
	
	static {
		try {
			Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
			connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
	}
	
	
}
