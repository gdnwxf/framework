package com.wch.jdbc;


public enum Alias {
	ALIAS_TO_ENTITY_MAP("map"),

	ALIAS_TO_LIST("list");

	/**
	 */
	Alias(String value) {
		this.value = value;
	}

	private String value;

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
