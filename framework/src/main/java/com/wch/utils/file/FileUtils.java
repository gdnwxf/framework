
package com.wch.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import com.wch.utils.Assert;
import com.wch.utils.GException;
import com.wch.utils.log.Glog;
import com.wch.utils.string.StringFilter;


public class FileUtils {

	/**
	 * Scan flie.
	 * 
	 * @param fileName the file name
	 */
	public static void scanFlie(String fileName) {
		scanFile(new File(fileName), (FileFilter) null);
	}

	/**
	 * Scan flie.
	 * 
	 * @param file the file
	 * @param fileFilter the file filter
	 */
	public static void scanFile(File file, FileFilter fileFilter) {
		File[] files = null;
		if (fileFilter == null) {
			files = file.listFiles();
		} else {
			files = file.listFiles(fileFilter);
		}
		if (files == null) {
			return;
		}
		for (File tempFile : files) {
			scanFile(tempFile, fileFilter);
		}
	}

	/**
	 * Scan file.
	 * 
	 * @param file the file
	 * @param fileNameFilter the file name filter
	 */
	public static void scanFile(File file, FilenameFilter fileNameFilter) {
		File[] files = null;
		if (fileNameFilter == null) {
			files = file.listFiles();
		} else {
			files = file.listFiles(fileNameFilter);
		}
		if (files == null) {
			return;
		}
		for (File tempFile : files) {
			scanFile(tempFile, fileNameFilter);
		}
	}

	/**
	 * Copy file.
	 * 
	 * @param sourceFile the source file
	 * @param targetFile the target file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		Assert.notNull(sourceFile);
		Assert.notNull(targetFile);
		if (!sourceFile.exists()) {
			throw new GException(" sourceFile file is not exists ");
		}
		FileReader fileReader = new FileReader(sourceFile);
		if (!targetFile.exists()) {
			targetFile.getParentFile().mkdirs();
			targetFile.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(targetFile);
		char[] buffer = new char[1024 * 1024];
		@SuppressWarnings("unused")
		int temp = 0;
		while ((temp = fileReader.read(buffer)) != -1) {
			fileWriter.write(buffer);
		}
		fileWriter.flush();
		fileReader.close();
		fileWriter.close();
	}

	/**
	 * Write to file.
	 * 
	 * @param stringBuilder the string builder
	 * @param charset the charset
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeToFile(StringBuilder stringBuilder, String charset, File file, boolean forceWrite) throws IOException {
		if (file.isFile()) {
			if (!forceWrite) {
				throw new GException(" The file is already exist ");
			}
		} else if (file.isDirectory()) {
			throw new GException(" The file is directory ");
		} else {
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
		}
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(new String(stringBuilder.toString().getBytes(), charset).toString());
		fileWriter.flush();
		fileWriter.close();
	}

	/**
	 * Write to file.
	 * 
	 * @param stringBuilder the string builder
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeToFile(StringBuilder stringBuilder, File file, boolean forceWrite) throws IOException {
		writeToFile(stringBuilder, "UTF-8", file, forceWrite);
	}

	/**
	 * Write to file.
	 * 
	 * @param content the content
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeToFile(String content, File file, boolean forceWrite) throws IOException {
		writeToFile(new StringBuilder(content), "UTF-8", file, forceWrite);
	}

	/**
	 * Write to file.
	 * 
	 * @param content the content
	 * @param filePath the file path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeToFile(String content, String filePath, boolean forceWrite) throws IOException {
		writeToFile(new StringBuilder(content), "UTF-8", new File(filePath), forceWrite);
	}

	/**
	 * Rename to.
	 * 
	 * @param sourceFile the source file
	 * @param renameName the rename name
	 */
	public static void renameTo(File sourceFile, String renameName) {
		sourceFile.renameTo(new File(sourceFile.getParent() + File.separator + renameName));
	}
	/**
	 * Rename to.
	 * 
	 * @param sourceFile the source file
	 * @param renameName the rename name
	 */
	public static void renameTo(File sourceFile, File targetFile) {
		sourceFile.renameTo(targetFile);
	}
	
	/**
	 * Rename to.
	 *
	 * @param sourceFile the source file
	 * @param fileNameModi the file name modi
	 */
	public static void batchRenameFile(File sourceFile, FileNameModi fileNameModi) {
		scanFile(sourceFile, fileNameModi);
	}
	
	
	/**
	 * Read file.
	 * 
	 * @param file the file
	 * @param stringFilter the string filter
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String readFileToString(File file) throws IOException {
		Assert.notNull(file);
		if (!file.exists() || !file.isFile()) {
			throw new GException("  The file is not exist or the file is directory   ");
		}
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			StringBuilder stringBuilder = new StringBuilder("");
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String temp = null;
			while ((temp = bufferedReader.readLine()) != null) {
				stringBuilder.append(temp);
				stringBuilder.append("\r\n");
			}
			return stringBuilder.toString();
		} finally {
			bufferedReader.close();
			fileReader.close();
		}
	}

	/**
	 * Read file.
	 * 
	 * @param file the file
	 * @param stringFilter the string filter
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String readFileToString(File file, StringFilter stringFilter) throws IOException {
		Assert.notNull(file);
		if (!file.exists() || !file.isFile()) {
			throw new GException("  The file is not exist or the file is directory   ");
		}
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			StringBuilder stringBuilder = new StringBuilder("");
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String temp = null;
			while ((temp = bufferedReader.readLine()) != null) {
				if (!stringFilter.accept(stringBuilder, temp)) {
					continue;
				}
			}
			return stringBuilder.toString();
		} finally {
			bufferedReader.close();
			fileReader.close();
		}
	}

	/**
	 * Read file.
	 * 
	 * @param file the file
	 * @param stringFilter the string filter
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String readFileToStringWithRuturn(File file, StringFilter stringFilter) throws IOException {
		Assert.notNull(file);
		if (!file.exists() || !file.isFile()) {
			throw new GException("  The file is not exist or the file is directory   ");
		}
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			StringBuilder stringBuilder = new StringBuilder("");
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String temp = null;
			while ((temp = bufferedReader.readLine()) != null) {
				if (!stringFilter.accept(stringBuilder, temp)) {
					continue;
				} else {
					break;
				}
			}
			return stringBuilder.toString();
		} finally {
			bufferedReader.close();
			fileReader.close();
		}
	}

	public static void main(String[] args) throws IOException {
		String xx = readFileToString(new File("d:\\我的电脑安装软件.txt"), new StringFilter() {
			public boolean accept(StringBuilder stringBuilder, String temp) {
				stringBuilder.append(temp);
				stringBuilder.append("\n");
				return true;
			}

		});

		Glog.info(" ------------------------ ");
		Glog.info(xx);
	}
}
