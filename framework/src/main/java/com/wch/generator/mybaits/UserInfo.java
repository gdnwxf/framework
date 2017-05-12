package com.wch.generator.mybaits;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5439885642755893775L;
	private Long id;
	private String name; 
}
