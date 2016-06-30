
package com.wch.utils.file;

import java.io.File;
import java.io.FileFilter;



public class FileFilterX implements FileFilter {
	
	/**
	 * Accept.
	 *
	 * @param pathname the pathname
	 * @return true, if successful
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File pathname) {
		return true;
	}
	 
}
