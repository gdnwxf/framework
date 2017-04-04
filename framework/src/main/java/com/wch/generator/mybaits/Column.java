package com.wch.generator.mybaits;

import java.io.Serializable;

import lombok.Data;

@Data
public class Column implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String TABLE_CATALOG			;
	private String TABLE_SCHEMA             ;
	private String TABLE_NAME               ;
	private String COLUMN_NAME              ;
	private Long ORDINAL_POSITION           ;
	private String COLUMN_DEFAULT           ;
	private String IS_NULLABLE              ;
	private String DATA_TYPE                ;
	private Long CHARACTER_MAXIMUM_LENGTH   ;
	private Long CHARACTER_OCTET_LENGTH     ;
	private Long NUMERIC_PRECISION          ;
	private Long NUMERIC_SCALE              ;
	private Long DATETIME_PRECISION         ;
	private String CHARACTER_SET_NAME       ;
	private String COLLATION_NAME           ;
	private String COLUMN_TYPE              ;
	private String COLUMN_KEY               ;
	private String EXTRA                    ;
	private String PRIVILEGES               ;
	private String COLUMN_COMMENT           ;

}
