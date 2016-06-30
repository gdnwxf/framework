package com.wch.utils;

import java.util.Map;

public interface QueryDao {

	Object queryUniqueBySql(String querySql, Map<String, Object> map);

	void executeSql(String insertSql, Object[] objects);

  
}
