
package framwork.mybatis;


public class JieCheng2 {

	
	public static void main(String[] args) {
		System.out.println(jc(5));
	}
	
	public static Integer jc(int  a) {
		if(a==1) {
			return a ; 
		}
		
		return a * jc(a-1);
	}
}
