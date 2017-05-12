/**
 * 
 */
package com.wch.pojo.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Class Filter.
 *
 * @author wch
 * @param <T> the generic type
 */
public class Filter<T> {

	/** The clazz. */
	private Class<T> clazz;
	
	/** The expression list. */
	private List<Expression> expressionList;
	
	/** The orders. */
	private List<OrderBy> orders;
	
	/** The distinct. */
	private boolean distinct;
	
	/** The head sql. */
	private String headSql;


	/**
	 * Instantiates a new filter.
	 *
	 * @param clazz the clazz
	 */
	public Filter(Class<T> clazz) {
		this.clazz = clazz;
		this.expressionList = new ArrayList<Expression>();
	}

	/**
	 * Instantiates a new filter.
	 *
	 * @param clazz the clazz
	 * @param argsMap the args map
	 */
	public Filter(Class<T> clazz, Map<String, Object> argsMap) {
		super();
		this.clazz = clazz;
		this.expressionList = new ArrayList<Expression>();
	}

	/**
	 * Adds the equal to.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the filter
	 */
	public Filter<T> addEqualTo(String field, Object value) {
		expressionList.add(Expression.addEqualTo(field, value));
		return this;
	}

	/**
	 * Adds the slect sql.
	 *
	 * @param headSql the head sql
	 * @return the filter
	 */
	public Filter<T> addSlectSql(String headSql) {
		this.headSql = headSql;
		return  this;
	}

	/**
	 * 添加索引的 表达式    中间的值不能为空.
	 *
	 * @param list the list
	 * @return the filter
	 */
	public Filter<T> addIndex(List<Expression> list) {
		int size = list.size();
		int a = 0 ;
		do {
			expressionList.add(a,list.get(a));
			a++;
		} while (a == size);
		return this;
	}

	/**
	 * Adds the order by.
	 *
	 * @param key the key
	 * @param acending the acending
	 * @return the filter
	 */
	public Filter<T> addOrderBy(String key ,boolean acending ) {
		orders.add(new OrderBy(key,acending));
		return this;
	}
	
	/**
	 * Sets the distinct.
	 */
	public void setDistinct() {
		this.distinct = true;
	}

}

class OrderBy {
	private String orderBy;
	private boolean acending;


	public OrderBy(String orderBy , boolean acending) {
		this.acending = acending;
		this.orderBy = orderBy;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAcending() {
		return acending;
	}

	public void setAcending(boolean acending) {
		this.acending = acending;
	}
}
