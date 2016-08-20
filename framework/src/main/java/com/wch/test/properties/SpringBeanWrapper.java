package com.wch.test.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.wch.bean.DozerBeanUtil;
import com.wch.pojo.beans.PersonBean;

public class SpringBeanWrapper {
	public static void main(String[] args) {
		PersonBean personBean = new PersonBean();
		BeanWrapperImpl beanWrapper = new BeanWrapperImpl(personBean);
		Map<String, Object> map  = new HashMap<String ,Object>();;
		
		map.put("departInfo.school.id", 1);
		map.put("departInfo.school.scName", "镇海中学");
		beanWrapper.setAutoGrowNestedPaths(true);
		beanWrapper.setPropertyValues(map);
		System.out.println(personBean);
		
		
		DozerBeanUtil dBeanUtil = new DozerBeanUtil();
	}

}
