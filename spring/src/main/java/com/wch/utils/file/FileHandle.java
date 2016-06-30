
 
package com.wch.utils.file;

import java.io.File;


 
public abstract class FileHandle {
   
	/**
	 * Handle file.
	 *
	 * @param file the file
	 */
	public void handleFile(File file) {
		System.out.println(file);
	}
}
