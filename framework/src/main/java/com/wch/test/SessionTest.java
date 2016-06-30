package com.wch.test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.wch.jdbc.Query;
import com.wch.jdbc.Session;
import com.wch.jdbc.SessionImpl;
import com.wch.pojo.User;

public class SessionTest {
	
	public static void main(String[] args) throws SQLException {
		 Session session = new SessionImpl();
		 Query query =  session.createQuery("select * from tuser");
		 System.out.println(Arrays.toString(query.getAlias()));
		 List<?> list1 =  query.addEntity(User.class).list();
		 for (int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));
		 }
		 Query query2 =  session.createQuery("select * from trole");
		 @SuppressWarnings("unchecked")
		List<Map<String, Object>> list = query2.list();
		 for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		 }
	}
}
