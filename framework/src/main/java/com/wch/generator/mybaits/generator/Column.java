/**
 * 
 */
package com.wch.generator.mybaits.generator;

import lombok.Data;

/**
 * @author wch
 * @date 2017年5月12日 下午11:32:25
 */
@Data
public class Column {
	
	private String field;
	private String type;
	private String collation;   
	private String nil; // null
	private String key;
	private String def; //default
	private String extra;
	private String privileges;                   
	private String comment;

}
