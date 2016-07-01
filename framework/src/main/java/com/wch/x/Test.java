
 
package com.wch.x;


 
public class Test {
 
	public static void main(String[] args) throws ClassNotFoundException {
//		ClassLoaderTest loaderTest = new ClassLoaderTest();
		Class.forName("com.wch.x.ClassLoaderTest");
		 MapContainer.print();
	}
}
