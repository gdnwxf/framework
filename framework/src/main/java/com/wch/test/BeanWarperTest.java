/**
 * 
 */
package com.wch.test;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.wch.annotation.advance.test.UserInfo;

/**
 * @author wch
 *
 */
public class BeanWarperTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		BeanWrapper brp = new BeanWrapperImpl(UserInfo.class);
		PropertyDescriptor p = brp.getPropertyDescriptor("name");
		System.out.println(p.getName());
		Object a = Class.forName(p.getPropertyType().getName()).newInstance();
		brp.setPropertyValue(p.getName(), a);
		brp = new BeanWrapperImpl(a);
		System.out.println(brp);

	}
}
