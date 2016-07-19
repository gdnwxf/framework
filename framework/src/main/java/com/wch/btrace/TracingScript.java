package com.wch.btrace;
/* BTrace Script Template */
import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TracingScript {
    	/* put your code here */
 @TLS static RuntimeException currentException;
 
     @OnMethod(
        clazz="java.lang.NullPointerException",
        method="<init>"
    )
    public static void onthrow(@Self NullPointerException self) {
        currentException = self;
    }

    @OnMethod(
        clazz="java.lang.NullPointerException",
        method="<init>"
    )
    public static void onthrow1(@Self NullPointerException self, String s) {
        currentException = self;
    }

   @OnMethod(
    clazz = "java.lang.NullPointerException",
    method= "<init>",
    location = @Location(Kind.RETURN)//函数返回的时候执行，如果不填，则在函数开始的时候执行
   )
      public static void onthrow2() {
         
        println(currentException);
    }

}