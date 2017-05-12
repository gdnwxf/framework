package com.wch.test.beancopier;

import java.util.Properties;

import org.springframework.cglib.beans.BeanCopier;

import com.wch.pojo.beans.DepartInfo;
import com.wch.pojo.beans.PersonBean;
import com.wch.pojo.beans.School;

public class BeanCopierTest {
	
	
		
		public static void main(String[] args) {
			Properties properties = new Properties();
			PersonBean isCopyedClass = new PersonBean();
			System.out.println(isCopyedClass);
			PersonBean personBean = new PersonBean();
			DepartInfo departInfo = new DepartInfo();
			School school = new School();
			school.setId(32);
			school.setScName("第2中学!!!");
			departInfo.setSchool(school);
			personBean.setDepartInfo(departInfo);
			properties.setProperty("departInfo.school.scName", "你好的");
			BeanCopier create = BeanCopier.create(Properties.class, PersonBean.class, false);
			create.copy(properties, isCopyedClass, null);
			
			System.out.println(isCopyedClass);
		}
}



