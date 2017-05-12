package com.wch.pojo.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class School {
	private int id ;
	private String  scName ;
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
	 * @return the scName
	 */
	public String getScName() {
		return scName;
	}
	/**
	 * @param scName the scName to set
	 */
	public void setScName(String scName) {
		this.scName = scName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.DEFAULT_STYLE);
	}
}
