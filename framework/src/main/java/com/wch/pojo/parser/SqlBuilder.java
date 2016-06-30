/**
 *
 */
package com.wch.pojo.parser;

import java.util.List;

/**
 * @author wch
 *
 */
public class SqlBuilder  {

	private static StringBuffer buffer = new StringBuffer();

	private Filter filter;

	private List<Parser> parserList;

	private String tableNames;


	 public SqlBuilder(Filter filter, List<Parser> parserList) {
		 this.filter  = filter;
		 this.parserList  = parserList;
	 }


	public SqlBuilder() {
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}
}
