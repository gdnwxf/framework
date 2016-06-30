package com.wch.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wch.utils.date.DateUtils;
import com.wch.utils.string.StringUtils;

/**
 * 一个ID的类
 * @author GDNWXF
 * @date 2014年6月20日  下午11:09:55
 */
public class ID {
	
	private final static Integer DEFAULT_LENGTH = 24;
	
	/**
	 * 生成32位不带“-”的uuid
	 * @return
	 */
	public static String UUID(){
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 生成32位带“-”的uuid
	 * @return
	 */
	public static String UUID2(){
		return java.util.UUID.randomUUID().toString();
	}
	/**
	 * 从当前的数据库中找到最大的Integer的数据+1构成伪自增长的id值
	 */
	public synchronized static String primaryKey(Integer length) {
		if (length == null || length < DEFAULT_LENGTH) {
			length = DEFAULT_LENGTH;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmt3", "root", "root");
			String querySql = "select value from id where name = ?" ;
			String updateSql = "update id set value = ?  where name = ?";
			String insertSql = "insert into id(name,value) values(?,?)";
			PreparedStatement ps = getPreparedStatement(connection, querySql, new Object[] { length });
			ResultSet rs = ps.executeQuery();
			String result  = "";
			if (rs.next()) {
				  result = rs.getString("value");
			} else {
				// 将新的格式的id插入到数据库中
				String prefix = DateUtils.formartDate(new Date(), "yyyyMMdd");
				String suffix = "";
				for (int i = 0; i < length - 9; i++) {
					suffix += "0";
				}
				PreparedStatement insertPs = getPreparedStatement(connection, insertSql, new Object[] { length, prefix + suffix + "1" });
				insertPs.executeUpdate();
				return prefix + suffix + "1";
			}
			String currentDate = DateUtils.formartDate(new Date(), "yyyyMMdd");
			if (StringUtils.isEmpty(result)) {
				String suffix = "";
				for (int i = 0; i < length - 9; i++) {
					suffix += "0";
				}
				result = currentDate + suffix + "1";
				PreparedStatement insertPs = getPreparedStatement(connection, insertSql, new Object[] { length, result });
				insertPs.executeUpdate();
				return result;
				//
			}else {
				// 取出其中的数据
				String prefix = result.substring(0, 8);
				long suffixNo = Long.parseLong(result.substring(8));
				String suffix = "";
				// 如果prefix 比 currentDate 小
				if (prefix.compareTo(currentDate) < 0) {
					prefix = currentDate;
					for (int i = 0; i < length - 8; i++) {
						suffix += "0";
					}
					suffix = suffix + "1";
				} else if (prefix.compareTo(currentDate) == 0) { // 当id字段中的日期与当前日期相等时
					suffixNo = suffixNo + 1;
					int suffixLength = (suffixNo + "").length();
					if (suffixLength > length - 8) {
						try {
							throw new Exception("当天内主键超过了12位数");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					for (int i = 0; i < length - 8 - suffixLength; i++) {
						suffix += "0";
					}
					suffix = suffix + suffixNo;
				}
				result = prefix + suffix;
				PreparedStatement updatePs = getPreparedStatement(connection, updateSql, new Object[] { result, length });
				updatePs.executeUpdate();
				return result;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private static PreparedStatement getPreparedStatement(Connection connection, String sql, Object[] obj) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		if (obj != null && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
		}
		return ps;
	}

	/**
	 * 在当前的数据库中create table(name int not null primary key,value varchar(32));
	 * 
	 * @return
	 */
	public synchronized static String primaryKeyLocal(QueryDao queryDao, Integer length) {
		if (length == null || length < DEFAULT_LENGTH) {
			length = DEFAULT_LENGTH;
		}
		String querySql = "select value from id where name = :name";
		String updateSql = "update id set value = ?  where name = ?";
		String insertSql = "insert into id(name,value) values(?,?)";
		String result = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", length);
		String object = (String) queryDao.queryUniqueBySql(querySql, map);
		System.out.println(object + "dedededede");
		result = object;
		if (result == null) {
			// 将新的格式的id插入到数据库中
			String prefix = DateUtils.formartDate(new Date(), "yyyyMMdd");
			String suffix = "";
			for (int i = 0; i < length - 9; i++) {
				suffix += "0";
			}
			result = prefix + suffix + "1";
			queryDao.executeSql(insertSql, new Object[] { length, result });
			return result;
		}
		String currentDate = DateUtils.formartDate(new Date(), "yyyyMMdd");
		if (StringUtils.isEmpty(result)) {
			String suffix = "";
			for (int i = 0; i < length - 9; i++) {
				suffix += "0";
			}
			result = currentDate + suffix + "1";
			queryDao.executeSql(updateSql, new Object[]{result,length});
			return result;
			//
		} else {
			// 取出其中的数据
			String prefix = result.substring(0, 8);
			long suffixNo = Long.parseLong(result.substring(8));
			String suffix = "";
			// 如果prefix 比 currentDate 小
			if (prefix.compareTo(currentDate) < 0) {
				prefix = currentDate;
				for (int i = 0; i < length - 8; i++) {
					suffix += "0";
				}
				suffix = suffix + "1";
			} else if (prefix.compareTo(currentDate) == 0) { // 当id字段中的日期与当前日期相等时
				suffixNo = suffixNo + 1;
				int suffixLength = (suffixNo + "").length();
				for (int i = 0; i < length - 8 - suffixLength; i++) {
					suffix += "0";
				}
				suffix = suffix + suffixNo;
			}
			result = prefix + suffix;
			queryDao.executeSql(updateSql, new Object[]{result,length});
			return result;
		}

	}

	public static void main(String[] args) {
		// primaryKey(20);
		// System.out.println(Long.MAX_VALUE);
		// System.out.println("12".compareTo("12"));
		// System.out.println(Long.parseLong("00001"));
		System.out.println(primaryKey(9));
		// System.out.println(DateUtils.formartDate(new Date()));
	}

}
