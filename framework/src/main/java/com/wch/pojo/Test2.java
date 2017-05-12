package com.wch.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by wch on 2016/5/21.
 */
public class Test2 {

    @org.junit.Test
    public void test1() throws CloneNotSupportedException {

        System.out.println("你好啊啊");
        List<User>  dataList = new ArrayList<User>(5);
        List<User>  dataList2 = new ArrayList<User>(5);
        User user1 = new User("3");
        User user2 = new User("4");
        dataList.add((User) user1.clone()) ;
        dataList.add((User) user2.clone()) ;
        
        
//        dataList2.addAll(dataList);
        
        dataList2 = Collections.unmodifiableList(dataList);
        
        user2.setUsername("5");
        
        for (User user : dataList2) {
			System.out.println(user.getUsername());
		}
    }


}
