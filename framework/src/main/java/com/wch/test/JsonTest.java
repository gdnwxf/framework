package com.wch.test;

import com.alibaba.fastjson.JSON;

public class JsonTest {

	
	public static void main(String[] args) {
		System.out.println(JSON.toJSON(new Person(false)));;
		Person javaObject = JSON.toJavaObject(JSON.parseObject("{\"flag\":1}"), Person.class);
		System.out.println(javaObject);;
	}
}
class Person {
	
	private Boolean flag ;
	
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(Boolean flag) {
		super();
		this.flag = flag;
	}

	public Boolean getFlag() {
		return flag;
	}
	
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	
}
