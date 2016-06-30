package com.wch.test;

import com.wch.pojo.Animal;
import com.wch.pojo.User;
import com.wch.utils.BeanUtils;

public class TestUser {
	public static void main(String[] args) {
		User user = new User();
		user.setEmail("32131");
		Animal animal  = new Animal();
		animal.setName("xxxxx");
		user.setAnimal(animal);
		BeanUtils.printObj(user);
		String [] aStrings  = {};
		System.out.println(aStrings.getClass() == String[].class);
	}
	
	private String xixi(int a ,String [] c){
		return "";
	} 
}
