package com.wch.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wch.utils.BeanUtils;

public class SQLQuery implements Query {
	private String sql;

	private PreparedStatement ps;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public SQLQuery(Statement ps, String sql) {
		this.sql = sql;
		this.statement = ps;
	}

	private Statement statement;

	private ResultSet rs;

	/**
	 * 确认返回的值是list map
	 */
	private String returnType = Session.ALIAS_TO_ENTITY_MAP;

	/**
	 * 当查询出来的东西给实体赋值
	 */
	private Class<?> entity = null;

	/**
	 * 返回查询到的结果集
	 */
	public ResultSet getResultSet() throws SQLException {
		if(rs != null) {
			return rs;
		}else if (statement == null) {
			throw new SQLException("没有预编译sql");
		} else if (rs == null || rs.isClosed()) {
			rs = statement.executeQuery(sql); // executeQuery();
		}
		return rs;
	}

	/**
	 * 返回查询到的结果集
	 */
	public ResultSet getPreparedResultSet() throws SQLException {
		if(rs!=null) {
			return rs;
		}else if (ps == null) {
			throw new SQLException("没有预编译sql");
		} else if (rs == null || rs.isClosed()) {
			rs = ps.executeQuery();
		}
		return rs;
	}

	/**
	 * 查出的列的String 数组转成大写
	 */
	public String[] getAliasUpper() throws SQLException {
		getResultSet();
		int rs_col = getColsNo();
		String[] paramName = new String[rs_col];
		for (int i = 0; i < rs_col; i++) {
			// 是选择的字段另取得名称
			paramName[i] = rs.getMetaData().getColumnLabel(i + 1).toUpperCase();
		}
		return paramName;
	}

	/**
	 * 返回结果集中查出的列的String 数组
	 */
	public String[] getAlias() throws SQLException {
		getResultSet();
		int rs_col = getColsNo();
		String[] paramName = new String[rs_col];
		for (int i = 0; i < rs_col; i++) {
			// 是选择的字段另取得名称
			paramName[i] = rs.getMetaData().getColumnLabel(i + 1);
		}
		return paramName;
	}

	/**
	 * 
	 * @return 返回查询出的结果列的数量
	 * @throws SQLException
	 */
	private int getColsNo() throws SQLException {
		ResultSetMetaData rsmd = getResultSet().getMetaData();
		return rsmd.getColumnCount();
	}

	/**
	 * 以对象数组的形式返回
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getValues() throws SQLException {
		getResultSet();
		List valueList = new ArrayList();
		while (rs.next()) {
			Object[] objects = new Object[getColsNo()];
			for (int j = 0; j < getColsNo(); j++) {
				objects[j] = rs.getObject(j + 1);
			}
			if (getColsNo() == 1) {
				valueList.add(objects[0]);
			} else {
				valueList.add(objects);
			}
		}
		if (rs != null)
			rs.close();
		return valueList;
	}

	/**
	 * 以list<Map<String,Object>> 返回从数据库中查出的东西
	 */
	@SuppressWarnings("rawtypes")
	public List list() throws SQLException {
		getResultSet();
		String sReturnType = getResultTransformer();
		if (sReturnType.equals(Session.ALIAS_TO_LIST)) {
			return getValues();
		}
		List<Map<String, Object>> valueList = new ArrayList<Map<String, Object>>();
		List<Object> entityList = new ArrayList<Object>();
		int iColNo = getColsNo();
		String[] alias = getAlias();
		Class<?> entityClass = getEntity();
		while (rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int j = 0; j < iColNo; j++) {
				map.put(alias[j], rs.getObject(alias[j]));
			}
			if (entityClass != null) {
				try {
					entityList.add(BeanUtils.mapToBean(map, entityClass));
				} catch (InstantiationException e) {
					throw new SQLException(e.getMessage());
				} catch (IllegalAccessException e) {
					throw new SQLException(e.getMessage());
				} catch (IllegalArgumentException e) {
					throw new SQLException(e.getMessage());
				} catch (InvocationTargetException e) {
					throw new SQLException(e.getMessage());
				}
			} else if (entityClass == null) {
				if (sReturnType.equals(Session.ALIAS_TO_ENTITY_MAP)) {
					valueList.add(map);
				}
			}

		}

		if (rs != null)
			rs.close();
		if (entityClass != null) {
			return entityList;
		} else if (sReturnType.equals(Session.ALIAS_TO_ENTITY_MAP)) {
			return valueList;
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	private Class<?> getEntity() {
		return entity;
	}

	/**
	 * 
	 * @return
	 */
	private String getResultTransformer() {
		return returnType;
	}
	/**
	 * 
	 * @return
	 */
	public Query setResultTransformer(Alias alias ) {
		  this.returnType = alias.getValue();
		  return this;
	}
	/**
	 * 以Object[]返回唯一的行值
	 */
	public Object[] getUniqueRow() throws SQLException {
		getResultSet();
		Object[] objects = new Object[getColsNo()];
		if (rs.next()) {
			for (int j = 0; j < getColsNo(); j++) {
				objects[j] = rs.getObject(j + 1);
			}
		}
		if (rs != null)
			rs.close();
		return objects;
	}

	/**
	 * Map<String,Object> 的形式返回一行值
	 */
	public Map<String, Object> getUniqueMapRow() throws SQLException {
		getResultSet();
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs.next()) {
			for (int j = 0; j < getColsNo(); j++) {
				map.put(getAlias()[j], rs.getObject(getAlias()[j]));
			}
		}
		if (rs != null)
			rs.close();
		return map;
	}

	public Query addEntity(Class<?> clazz) {
		this.entity = clazz;
		return this;
	}

	
	public Object uniqueResult() throws SQLException {
		getResultSet();
		Object temp = null;
		if(rs.next()) {
			temp = rs.getObject(1);
		}
		if (rs != null)
			rs.close();
		return temp;
	}
	
	public Object uniqueResult(String columnName) throws SQLException {
		getResultSet();
		Object temp = null;
		if(rs.next()) {
			temp = rs.getObject(columnName);
		}
		if (rs != null)
			rs.close();
		return temp;
	}
	
	public Object uniqueResult(int colIndex) throws SQLException {
		getResultSet();
		Object temp = null;
		if(rs.next()) {
			temp = rs.getObject(colIndex);
		}
		if (rs != null)
			rs.close();
		return temp;
	}
	
	public Integer count() throws SQLException {
      return (Integer) uniqueResult(1);
	}
}
