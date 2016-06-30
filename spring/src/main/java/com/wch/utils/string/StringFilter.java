package com.wch.utils.string;

/**
 * 
 * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
 * @version $Id: StringFilter.java 25 2015-10-08 09:28:08Z wch $
 * @since 2.0
 */
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
