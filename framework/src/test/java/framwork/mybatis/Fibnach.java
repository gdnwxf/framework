package framwork.mybatis;

public class Fibnach {

	public static void main(String[] args) {
		
		for (int i = 1; i < 100; i++) {
			System.out.print(fi(i) + " ");
		}
	}
	
	public static Integer fi(int a) {
		if(a==1 || a== 2) {
			return 1 ;
		}
		return fi(a-1)+fi(a-2);
	}
}
