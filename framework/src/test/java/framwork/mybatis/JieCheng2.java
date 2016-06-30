
package framwork.mybatis;


 /**
  *  
  * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
  * @version $Id$   
  * @since 2.0
  */
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
