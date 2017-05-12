/**
 * 
 */
package com.wch.pojo.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Administrator
 *
 */
public class PersonBean {

	
	private String name ;
	private int age ;
	private String mN ;
	private DepartInfo departInfo;
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
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the mN
	 */
	public String getmN() {
		return mN;
	}
	/**
	 * @param mN the mN to set
	 */
	public void setmN(String mN) {
		this.mN = mN;
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
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.DEFAULT_STYLE);
	}
}
