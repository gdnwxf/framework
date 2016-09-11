package com.wch.test.javassist;
/**
 * <pre>
 * 清单 6. 将一个 long 储存到一个 int 中
	[dennis]$ java -cp javassist.jar:. JassistTiming StringBuilder buildString
	Interceptor method body:
	{
	int start = System.currentTimeMillis();
	java.lang.String result = buildString$impl($$);
	System.out.println("Call to method buildString took " +
	 (System.currentTimeMillis()-start) + " ms.");
	return result;
	}
 * 
 *  清单7. 将一个 String 储存到一个 int 中
	[dennis]$ java -cp javassist.jar:. JassistTiming StringBuilder buildString
	Interceptor method body:
	{
	long start = System.currentTimeMillis();
	int result = buildString$impl($$);
	System.out.println("Call to method buildString took " +
	 (System.currentTimeMillis()-start) + " ms.");
	return result;
	}
	Added timing to method StringBuilder.buildString
	[dennis]$ java StringBuilder 1000 2000 4000 8000 16000
	Exception in thread "main" java.lang.VerifyError:
	 (class: StringBuilder, method: buildString signature:
	 (I)Ljava/lang/String;) Expecting to find integer on stack
 * 
 * </pre>
 * 
 * @author wch
 *
 */
public class StringBuilder
{
    private String buildString(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (char)(i%26 + 'a');
        }
        return result;
    }
    
    public static void main(String[] argv) {
        StringBuilder inst = new StringBuilder();
        for (int i = 0; i < argv.length; i++) {
            String result = inst.buildString(Integer.parseInt(argv[i]));
            System.out.println("Constructed string of length " +
                result.length());
        }
    }
}