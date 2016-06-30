package com.wch.test;

import com.wch.pojo.Animal;
import com.wch.pojo.User;
import com.wch.utils.BeanUtils;


public class PublicTest {

		public static void main(String[] args) {
		 
			String [] aStrings = {"122","das"};
			int [] aa = new int [21];
			Object [] oa = null;
			User user = new User();
			user.setAnimal(new Animal());
			user.setEmail("dadasdsa");
			BeanUtils.printObj(user);
			BeanUtils.printFields(user);
		}
	 
}
