
 
package com.wch.x;



 
public class ClassLoaderTest    {
	  
	/**
	 */
	public ClassLoaderTest() {
		System.out.println("构造方法");
	}
	  
	  static
	  {
	    try
	    {
	    	MapContainer.registerDriver("32132132131");
	    }
	    catch (Exception E)
	    {
	      throw new RuntimeException("Can't register driver!");
	    }
	  }

}
