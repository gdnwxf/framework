/**
 * 
 */
package com.wch.utils.xutils;

import static org.junit.Assert.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.enterprise.inject.New;
import javax.resource.NotSupportedException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.sun.mail.auth.Ntlm;
import com.wch.core.ClassUtils;
import com.wch.utils.Assert;
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
  
    @SuppressWarnings("unchecked")
	public static <T> T transMap2Bean(Map<String, Object> map, Class<T> t) throws Exception{
    	Assert.notNull(map);
    	Object obj = null;
    	if(Modifier.isAbstract(t.getModifiers())) {
    		throw new NotSupportedException("抽象类型暂时不支持 !!!") ;
    	}else if(t.isAnnotation()) {
    		throw new NotSupportedException("接口类型暂时不支持 !!!") ;
    	}else if(t.isInterface()) {
    		throw new NotSupportedException("接口类型暂时不支持 !!!") ;
    	}else if(t.isArray()) {
    		throw new NotSupportedException("数组的类型暂时不支持 !!!") ;
    	}else {
			obj = t.newInstance();
		}
    	
		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			Set<String> keySet = map.keySet();
			if (!keySet.isEmpty()) {
				for (String key : keySet) {
					setNestProperties(obj, key, map.get(key), t);
				}
			} 
		} finally {
			lock.unlock();
		}
		return (T) obj;  
    }  
    
    
    private static void setNestProperties(Object source, String key, Object value ,Class<?> t) throws InstantiationException, IllegalAccessException, IntrospectionException, IllegalArgumentException, InvocationTargetException {
    	Assert.notNull(key , "用于赋值的属性是不能为空的");
    	Assert.notNull(value , "用于赋值的值是不能为空的");
    	int index = 0;
    	boolean isBaseType = false;
    	if(TypeUtilX.isBaseType(t) || TypeUtilX.isBaseArray(t))
		{
			isBaseType = true;
		}
    	while (!isBaseType && index < key.length()-1) {
    	
    		char charTemp = key.charAt(index);
    		switch (charTemp) {
			case  '.':
				String prefix  = key.substring(0, index) ;
				String next = key.substring(index+1);
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(prefix, t);
//				ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
//				parameterDescriptor.setName(prefix);
//				MethodDescriptor methodDescriptor = new MethodDescriptor(propertyDescriptor.getWriteMethod(), new ParameterDescriptor[] {parameterDescriptor});
				Class<?> propertyType = propertyDescriptor.getPropertyType();
				Object newInstance = propertyType.newInstance();
				setNestProperties(newInstance, next, value, propertyType) ;
				propertyDescriptor.getWriteMethod().invoke(source, newInstance);
				return;
			default:
				break;
			}
			
    		index ++;
		}
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(key, t);
		propertyDescriptor.getWriteMethod().invoke(source, value) ;
	}

	private static String getNextPropertyBeanName(String param) {
    	Assert.notNull(param , "用于赋值的属性是不能为空的");
    	int index = 0;
    	while (index < param.length()-1) {
    		char charTemp = param.charAt(index);
    		switch (charTemp) {
			case  '.':
				String prefix  = param.substring(0, index) ;
				String next = param.substring(index+1);
				return prefix;

			default:
				break;
			}
			
    		index ++;
		}
    	
    	return param;
	}

	@Test
	public void test05() throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("name", "321321321-----");
		transMap2Bean(map, PersonBean.class);
		
	}
  
    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
    public static Map<String, Object> transBean2Map(Object obj) {  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>(); 
        StringBuilder builder = new StringBuilder(""); 
        try {  
        	transBean2Map(obj, map, builder);
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
       return map;
    }  
    
    private static void transBean2Map(Object obj, Map<String, Object> map ,StringBuilder builder ) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
//            	    map.put( new StringBuilder(builder).append(key).toString(), null);
            	   continue;
               }else if(TypeUtilX.isBaseArray(value)){
           			map.put( new StringBuilder(builder).append(key).toString(), value );
			   }else if(TypeUtilX.isBaseType(value)){
            		map.put( new StringBuilder(builder).append(key).toString(), value );
			   }else {
				transBean2Map(value, map, new StringBuilder(builder).append(key).append(DOMAIN_SPLIT));
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
//        person.setmN("mn");
        person.setName("zhangsan");
        person.setDepartInfo(departInfo);
    	System.out.println(BeanUtils.describe(person));
		
	}

	public static void main(String [] args) throws Exception {

        PersonBean person = new PersonBean();  
        
        DepartInfo departInfo = new DepartInfo();
        departInfo.setId(55);
        departInfo.setPartName("研发部");
        
        
        School school = new  School();
        
        school.setId(323213);
        school.setScName("镇海中学");
		departInfo.setSchool(school );
        
        person.setAge(12);
//        person.setmN("mn");
        person.setName("zhangsan");
        person.setDepartInfo(departInfo);
        Map<String, Object> transBean2Map = transBean2Map(person);
        
        PersonBean personBean = transMap2Bean(transBean2Map, PersonBean.class);
        System.out.println(personBean);
	}
    
    
    @Test
	public void testMap() throws Exception {
    	Map<String ,Object> data = new HashMap<String ,Object>();
    	data.put("usreID", "321321");
    	data.put("uSReID", "dxsadsa");
    	System.out.println(data);
	}
}
