/**
 * 
 */
package com.wch.generator.mybaits.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wch.generator.mybaits.dbcore.JdbcTemplate;
import com.wch.generator.mybaits.dbcore.Translate;

/**
 * @author wch
 * @date 2017年5月12日 下午8:02:09
 */
public class GeneratorManager {
	
	private JdbcTemplate  jdbcTemplate;
	
	public GeneratorManager(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public void getColumnsInfo(String [] tables , Map<String, List<Column>> columnMaps ) {
		if(tables != null)
		{
			for (String tableName : tables) {
				List<Column> columns = jdbcTemplate.selectList("show full fields from " + tableName,  new Translate<Column>() {

					@Override
					public Column translate(Map<String, Object> mapObject) {
						Column column = new Column();
						column.setCollation((String) mapObject.get("collation"));
						column.setType((String) mapObject.get("type"));
						column.setField((String) mapObject.get("field"));
						column.setNil((String) mapObject.get("null"));
						column.setKey((String) mapObject.get("key"));
						column.setDef((String) mapObject.get("default"));
						column.setExtra((String) mapObject.get("extra"));
						column.setPrivileges((String) mapObject.get("privileges"));
						column.setComment((String) mapObject.get("comment"));
						return column;
					}
				});
				columnMaps.put(tableName, columns);
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		GeneratorManager gm = new GeneratorManager(JdbcTemplate.defaultTemplate());
		
		Map<String, List<Column>> columnMaps = new HashMap<String,List<Column>>();
		gm.getColumnsInfo(new String[] {"user_info","employee"}, columnMaps);
		System.out.println(gm);
	}

}
