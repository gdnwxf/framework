/**
 * 
 */
package com.wch.utils.xutils.bean;

/**
 * @author Administrator
 *
 */
public class PersonBean {

	private Integer id;
	private String name ;
	private Integer age ;
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
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
