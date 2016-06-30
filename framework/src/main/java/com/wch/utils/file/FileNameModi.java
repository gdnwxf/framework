package com.wch.utils.file;

import java.io.File;
import java.io.FileFilter;

/**
 * The Class FileNameModi.
 */
public abstract class FileNameModi extends FileHandle implements FileFilter {

	/**
	 * Accept.
	 *
	 * @param pathname the pathname
	 * @return true, if successful
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File pathname) {
		modiFileName(pathname);
		System.out.println(pathname.getName());
		return true;
	}

	/**
	 * Modi file name.
	 *
	 * @param file the file
	 * @return the string
	 */
	public String modiFileName(File file) {
		return null;
	}
}
