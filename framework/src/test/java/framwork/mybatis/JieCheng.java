package framwork.mybatis;


public class JieCheng {

	
	public static void main(String[] args) {
		System.out.println(jc(2000));
	}
	
	public static Integer jc(int  a) {
		if(a==1) {
			return a*a ; 
		}
		
		return a*a + jc(a-1);
	}
}
