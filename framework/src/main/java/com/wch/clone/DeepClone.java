package com.wch.clone;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import com.wch.pojo.User;
import com.wch.utils.BeanUtils;

public class DeepClone {
		public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
			User user2 = null;
		/*	User user = new User();
			user.setGender("nan ");
			byte [] bytes = new byte[32]; 
			ByteArrayOutputStream baos = new ByteArrayOutputStream(30); //将对象读取到内存中
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(user);
 			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			
			user2 = (User) ois.readObject();
			 
			user.setEmail("dsadas");
			
			user2.setEmail("xxxxxxxxxxxxxx");
		
			BeanUtils.printObj(user);
			
			BeanUtils.printObj(user2);
			
			
			User user3 = new User();
			user3.setPassword("2131");
			
			User user4 = user3 ;
			user4.setPassword("123231321321");
			
			BeanUtils.printObj(user3);
			BeanUtils.printObj(user4);
			
			*/
			Class zl = Class.forName("com.wch.pojo.User")	;
			Constructor<?> [] constructor = zl.getConstructors();
			System.out.println(constructor.length+"  长度是   ::  ");
			 for (int i = 0; i < constructor.length; i++) {
				Parameter [] parameters =  constructor[i].getParameters() ;
				for (int j = 0; j < parameters.length; j++) {
					System.out.println(parameters[j]);
				}
			}
			
			User  user5 = (User) zl.newInstance();
			user5.setPassword("21231321321");
			
			User user6 =  (User) zl.newInstance();
			user6.setPassword("dadasdas");
			
			
			
			BeanUtils.printObj(user5);
			BeanUtils.printObj(user6);
			
		} 
}
