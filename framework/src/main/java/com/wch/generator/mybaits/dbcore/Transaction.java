package com.wch.generator.mybaits.dbcore;


public interface Transaction {

	void begin();
	void start();
	void commit() throws IllegalStateException;
	void rollback();
	
}
