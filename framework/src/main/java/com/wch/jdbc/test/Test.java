package com.wch.jdbc.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.mysql.jdbc.ConnectionImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXAConnection;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;
import com.wch.jdbc.Query;
import com.wch.jdbc.Session;
import com.wch.jdbc.SessionFactory;
import com.wch.jdbc.Transaction;

public class Test {

	
	@org.junit.Test
	public void test01() throws SQLException{
		Session session = SessionFactory.getInstance().openSession();
		Transaction transaction1 = session.getTransaction();
		Session session1 = SessionFactory.getInstance("jdbcConfig2.properties").openSession();
		Transaction transaction2 = session1.getTransaction();
		Query createQuery = session.createQuery("select count(1) from tuser");
		System.out.println(createQuery.uniqueResult());
		Query createQuery2 = session1.createQuery("select count(1) from ctxneg");
		System.out.println(createQuery2.uniqueResult());
		 DataSource dataSource1 = new MysqlDataSource();
		 Connection connection = dataSource1.getConnection("", "");
		 XAResource xaResource1 = new MysqlXAConnection((ConnectionImpl) connection, true);
		Xid xid1 = new MysqlXid("".getBytes(), "".getBytes(), 100);
		Xid xid2 = new MysqlXid("".getBytes(), "".getBytes(), 100);
		
		
	}
	
	
	@org.junit.Test
	public void test02() throws Exception {
		
		for (int i = 0; i < 10; ++i) {
			System.out.println(i);
		}
	}
}
