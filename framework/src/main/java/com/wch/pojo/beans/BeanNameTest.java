/**
 * 
 */
package com.wch.pojo.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author Administrator
 *
 */
public class BeanNameTest {

	public static void main(String[] args) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		   BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
		   PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		   for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			   System.out.println(propertyDescriptor.getName());
			
		}
		   
		   
		   UserInfo userInfo = new UserInfo();
		   DepartInfo departInfo = new DepartInfo();
		   userInfo.setDepartInfo(departInfo);
//		   PropertyDescriptor propDesc=new PropertyDescriptor("departInfo",UserInfo.class);
//		   Method writeMethod = propDesc.getWriteMethod();
//		   writeMethod.invoke(userInfo, new Object[] {222});
//		   if(userInfo.getDepartInfo() != null)
//		   {
//			   System.out.println(userInfo.getDepartInfo().getId());
//		   }
//		   System.out.println(userInfo.getDepartInfo());
		   
		   
		   
		   BeanUtils.setProperty(departInfo, "id", 12);
		   System.out.println(userInfo.getDepartInfo().getId());
	}
	
}


class BeanInfoTest {
	
	
	private String getepath  ;//  -->    getGetepath()
	private String getEpath  ;//  -->    getGetEpath()
	private String epath     ;//  -->    getEpath()     
	private String ePath     ;//  -->    getePath()    // 首字母不用大写
	private String Epath     ;//  -->    getEpath()    // 和epath的getter方法是一样的
	private String EPath     ;//  -->    getEPath()
	                         ;//
	private boolean isenable ;//  -->    isIsenable()
	private boolean isEnable ;//  -->    isEnable()    // 不是把首字母大写并在前面加is，其结果和enable的getter方法相同
	private boolean enable   ;//  -->    isEnable()
	private boolean eNable   ;//  -->    iseNable()    // 首字母不用大写
	private boolean Enable   ;//  -->    isEnable()    // 和enable的getter方法相同
	private boolean ENable   ;//  -->    isENable()    //
	/**
	 * @return the isEnable
	 */
	public boolean isEnable() {
		return isEnable;
	}
	/**
	 * @param isEnable the isEnable to set
	 */
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	 
}


class  UserInfo {
	
	private int id ;
	private String name;
	
	private DepartInfo departInfo;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the departInfo
	 */
	public DepartInfo getDepartInfo() {
		return departInfo;
	}
	/**
	 * @param departInfo the departInfo to set
	 */
	public void setDepartInfo(DepartInfo departInfo) {
		this.departInfo = departInfo;
	}
	
	
}

  
