package com.wch.generator.mybaits;

import java.io.Serializable;

import lombok.Data;

@Data
public class DescTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6228646591968173537L;
	private String field    ;      
	private String type     ;     
	private String nil     ;
	private String key      ;
	private String def  ;         
	private String extra    ;
	
}
