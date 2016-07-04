package com.wch.pojo.parser;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class GenericType {

	
	public static void main(String[] args) {
		  
	}
	
	public void add(List<? extends Date> list, Date date) {
		list.forEach(new Consumer<Date>() {
			public void accept(Date t) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
