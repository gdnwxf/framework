package com.wch.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

@SuppressWarnings("hiding")
public class AccessInvocationHandler<T, RequiredRoles> implements InvocationHandler {
	
	//
    final T accessObj;
    
    public AccessInvocationHandler(T accessObj) {
        this.accessObj = accessObj;
    }
    
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//		RequiredRoles annotation = method.getAnnotation(RequiredRoles.class); 
//        if (annotation != null) {
//            String[] roles = annotation.value();
//            System.out.println(Arrays.toString(roles));
//            //  String role = AccessControl.getCurrentRole();
//            /* if (!Arrays.asList(roles).contains(role)) {
//                throw new AccessControlException("The user is not allowed to invoke this method.");
//            }*/
//        }
        return method.invoke(accessObj, args);
    } 
} 
