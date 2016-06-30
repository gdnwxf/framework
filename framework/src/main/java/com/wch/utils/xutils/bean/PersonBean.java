/**
 * 
 */
package com.wch.utils.xutils.bean;

/**
 * @author Administrator
 *
 */
public class PersonBean {

	private int id;
	private String name ;
	private int age ;
	private String mN = "------" ;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
