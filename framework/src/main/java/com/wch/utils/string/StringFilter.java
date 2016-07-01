
package com.wch.utils.string;


public interface StringFilter {
	/**
	 * Accept.
	 * 
	 * @param content the content
	 * @param regExp the reg exp
	 * @return the string
	 */
	public abstract boolean accept(StringBuilder stringBuilder,String temp);

}
