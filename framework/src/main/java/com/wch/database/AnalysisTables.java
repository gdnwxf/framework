package com.wch.database;

import com.wch.jdbc.Alias;
import com.wch.jdbc.Query;
import com.wch.jdbc.Session;
import com.wch.jdbc.SessionFactory;

import java.sql.SQLException;
import java.util.List;

public class AnalysisTables {
   
	public static void main(String[] args) throws SQLException {
		Session session = SessionFactory.getInstance("jdbcConfig.properties").openSession();
		Query query = session.createQuery("select a.* from user_info a ");
		List<?> values = query.setResultTransformer(Alias.ALIAS_TO_LIST).list();
		for (Object object : values) {
			System.out.println(object);
		}

	}
	
}
