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
public class DepartInfo {

	
	private  int id;
	private String partName;
	
	private School school;
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
	 * @return the partName
	 */
	public String getPartName() {
		return partName;
	}
	/**
	 * @param partName the partName to set
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}
	/**
	 * @return the school
	 */
	public School getSchool() {
		return school;
	}
	/**
	 * @param school the school to set
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.DEFAULT_STYLE);
	}
}
