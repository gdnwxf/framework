package com.wch.test.listobject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wch.pojo.User;

public class ListTest {
	public static void main(String[] args) {
		
		List<User>  dataList = new ArrayList<User>(5);
		
		
		dataList.add(new User("1"))  ;
		dataList.add(new User("2"))  ;
		dataList.add(new User("3"))  ;
		dataList.add(new User("4"))  ;
		dataList.add(new User("5"))  ;
		
		
		
		List<User>  dataList2 = new ArrayList<User>(5);
		
		for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			System.out.println("--------------" + user);
			dataList2.add(user);
			
			if(user.getUsername().compareTo("1")>0) {
				dataList.remove(user);
			}
			
		}
		
		System.out.println(dataList2.size());
		System.out.println(dataList.size());
		
	}

}
