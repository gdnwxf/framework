/**
 * 
 */
package com.wch.utils.xutils;

import static org.junit.Assert.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.wch.utils.xutils.bean.DepartInfo;
import com.wch.utils.xutils.bean.PersonBean;
import com.wch.utils.xutils.bean.School;

/**
 * @author Administrator
 *
 */
public class MapUtilsX {
     
  
    private final static String DOMAIN_SPLIT = ".";


	// Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean  
    public static void transMap2Bean2(Map<String, Object> map, Object obj) {  
        if (map == null || obj == null) {  
            return;  
        }  
        try {  
            org.apache.commons.beanutils.BeanUtils.populate(obj, map);  
        } catch (Exception e) {  
            System.out.println("transMap2Bean2 Error " + e);  
        }  
    }  
  
    public static void transMap2Bean(Map<String, Object> map, Object obj) {  
  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                if (map.containsKey(key)) {  
                    Object value = map.get(key);  
                    // 得到property对应的setter方法  
                    Method setter = property.getWriteMethod();  
                    setter.invoke(obj, value);  
                }  
  
            }  
  
        } catch (Exception e) {  
            System.out.println("transMap2Bean Error " + e);  
        }  
  
        return;  
  
    }  
  
    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
    public static Map<String, Object> transBean2Map(Object obj) {  
        if(obj == null){  
            return null;  
        }          
        ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<String, Object>(); 
        StringBuilder builder = new StringBuilder(""); 
        try {  
        	transBean2Map(obj, concurrentMap, builder);
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
       return concurrentMap;
    }  
    
    private static void transBean2Map(Object obj, ConcurrentMap<String, Object> concurrentMap ,StringBuilder builder ) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	if(TypeUtilX.isBaseType(obj)) 
    	{
    		return; 
    	}
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
    	for (PropertyDescriptor property : propertyDescriptors) {
    		   String key = property.getName();  
    		   Method getter = property.getReadMethod();  
               Object value = getter.invoke(obj , new Object[0]);  
               if(value instanceof Class<?>) {
            	   continue;
               }
               if(value == null) {
            	    concurrentMap.put( new StringBuilder(builder).append(key).toString(), value);
               }else if(TypeUtilX.isBaseArray(value)){
           			concurrentMap.put( new StringBuilder(builder).append(key).toString(), value );
			   }else if(TypeUtilX.isBaseType(value)){
            		concurrentMap.put( new StringBuilder(builder).append(key).toString(), value );
			   }else {
				transBean2Map(value, concurrentMap, new StringBuilder(builder).append(key).append(DOMAIN_SPLIT));
        	   }
		}
    	
    }
    
    
    @Test
    public void testTest() throws Exception {

        PersonBean person = new PersonBean();  
        
        DepartInfo departInfo = new DepartInfo();
        departInfo.setId(55);
        departInfo.setPartName("研发部");
        
        
        School school = new  School();
        
        school.setId(323213);
        school.setScName("镇海中学");
		departInfo.setSchool(school );
        
        person.setAge(12);
        person.setmN("mn");
        person.setName("zhangsan");
        person.setDepartInfo(departInfo);
    	System.out.println(BeanUtils.describe(person));
		
	}

    @Test
	public void testHashMap() throws Exception {

        PersonBean person = new PersonBean();  
        
        DepartInfo departInfo = new DepartInfo();
        departInfo.setId(55);
        departInfo.setPartName("研发部");
        
        
        School school = new  School();
        
        school.setId(323213);
        school.setScName("镇海中学");
		departInfo.setSchool(school );
        
        person.setAge(12);
        person.setmN("mn");
        person.setName("zhangsan");
        person.setDepartInfo(departInfo);
		
        System.out.println(transBean2Map(person));
	}
    
    
    @Test
	public void testMap() throws Exception {
    	Map<String ,Object> data = new HashMap<String ,Object>();
    	data.put("usreID", "321321");
    	data.put("uSReID", "dxsadsa");
    	System.out.println(data);
	}
}
