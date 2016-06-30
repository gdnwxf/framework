package com.wch.mybatis.utils;

import java.lang.reflect.Field;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
 
/**
 * The Class SqlSessionFactoryBeanExt.
 */
public class SqlSessionFactoryBeanExt extends SqlSessionFactoryBean {
 
    /** The sql session factory. */
    private SqlSessionFactory sqlSessionFactory;
 
    /**
     * Set value.
     *
     * @param name the name
     * @param value the value
     */
    public void setValue(String name, Object value) {
        try {
            Field field = SqlSessionFactoryBean.class.getDeclaredField(name);
            field.setAccessible(true);
            field.set(this, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    
    /**
     * @return
     * @throws Exception
     * @see org.mybatis.spring.SqlSessionFactoryBean#getObject()
     */
    public SqlSessionFactory getObject() throws Exception {
        if (this.sqlSessionFactory == null) {
            sqlSessionFactory = buildSqlSessionFactory();
            setValue("sqlSessionFactory", sqlSessionFactory);
        }
        return this.sqlSessionFactory;
    }
 
    
    /**
     * @throws Exception
     * @see org.mybatis.spring.SqlSessionFactoryBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
    }
 
}
