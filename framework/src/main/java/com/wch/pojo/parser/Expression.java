package com.wch.pojo.parser;

/**
 * Created by wch on 2016/5/15.
 */
public class Expression {

	public static final int EQUALTO = 0x0;
	public static final int NOTEQUALTO = 0x1;
	public static final int GREATEROREQUAL = 0x2;
	public static final int GREATERTHAN = 0x3;
	public static final int LESSOREQUAL = 0x4;
	public static final int LESSTHAN = 0x5;
	public static final int BETWEEN = 0x6;
	public static final int NOTBETWEEN = 0x7;
	public static final int IN = 0x8;
	public static final int NOTIN = 0x9;
	public static final int LIKE = 0x10;
	public static final int NOTLIKE = 0x11;
	public static final int ISNULL = 0x12;
	public static final int NOTNULL = 0x13;
	public static final int AND = 0x14;
	public static final int OR = 0x15;
	public static final int NOT = 0x16;
	public static final int CLAUSE = 0x17;
	public static final int LEFTLIKE = 0x18;
	public static final int RIGHTLIKE = 0x19;
	private static final String[] SQL = new String[20];

	protected static final String andJoin = "and";
	protected static final String orJoin = "or";

	static {
		SQL[0] = "=";
		SQL[1] = "!=";
		SQL[2] = ">=";
		SQL[3] = ">";
		SQL[4] = "<=";
		SQL[5] = "<";
		SQL[6] = "between";
		SQL[7] = "not between";
		SQL[8] = "in";
		SQL[9] = "not in";
		SQL[10] = "like";
		SQL[18] = "like";
		SQL[19] = "like";
		SQL[11] = "not like";
		SQL[12] = "is null";
		SQL[13] = "is not null";
		SQL[14] = "and";
		SQL[15] = "or";
		SQL[16] = "not";
		SQL[17] = "";
	}

	private Expression() {
	}

	private Expression(String filed, Object value) {
		this.field = filed;
		this.value = value;
	}

	public static Expression addEqualTo(final String field, final Object value) {
		return new EqualTo(field, value);
	}

	private String field;
	private Object value;
	private String operate;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	private static class EqualTo extends Expression {
		public EqualTo(String filed, Object value) {
			super( filed,value );
			setOperate(SQL[EQUALTO]);
		}
	}

}
