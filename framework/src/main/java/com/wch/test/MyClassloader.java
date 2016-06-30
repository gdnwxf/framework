package com.wch.test;

public class MyClassloader extends ClassLoader {

	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		return this.loadClass(name,true);
	}
	
	 protected Class<?> loadClass(String name, boolean resolve)
		        throws ClassNotFoundException
		    {
		        synchronized (getClassLoadingLock(name)) {
		            // First, check if the class has already been loaded
		            Class<?> c = null;
		            if (c == null) {
		                long t0 = System.nanoTime();
		                try {
		                	
		                	   ClassLoader parent = super.getParent();
					 
		                    if (parent != null) {
		                        c =  parent.loadClass(name);
		                    } else {
//		                        c = findBootstrapClassOrNull(name);
//		                        if (!checkName(name)) return null;
//
//		                        return findBootstrapClass(name);
		                    }
		                } catch (ClassNotFoundException e) {
		                    // ClassNotFoundException thrown if class not found
		                    // from the non-null parent class loader
		                }

		                if (c == null) {
		                    // If still not found, then invoke findClass in order
		                    // to find the class.
		                    long t1 = System.nanoTime();
		                    c = findClass(name);

		                    // this is the defining class loader; record the stats
		                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
		                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
		                    sun.misc.PerfCounter.getFindClasses().increment();
		                }
		            }
		            
		            return c;
		        }
		    }
	 
}
