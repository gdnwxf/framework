package com.wch.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import com.wch.utils.Assert;
import com.wch.utils.GException;


public class FileUtilsX {

	/**
	 * Cycle file path.
	 *
	 * @param file the file
	 * @param fileHandle the file handle
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void cycleFilePath(File file, FileFilterX filterX, FileHandle fileHandle) throws IOException {
		cycleFilePath(file, filterX, null, false, fileHandle);
	}

	/**
	 * Cycle file path.
	 *
	 * @param file the file
	 * @param fileHandle the file handle
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void cycleFilePath(File file, FileHandle fileHandle) throws IOException {
		cycleFilePath(file, null, fileHandle);
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
	 * @throws IOException 
	 */
	public static void batchRenameFile(File sourceFile, FileNameModi fileNameModi) throws IOException {
		cycleFilePath(sourceFile, fileNameModi);
	}

	/**
	 * Cycle modify file.
	 * 
	 * @param file the file
	 * @param lineText the line text
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void cycleModifyFile(File file, LineText lineText) throws IOException {
		cycleFilePath(file, null, lineText, true, null);
	}

	/**
	 * Cycle modify file.
	 *
	 * @param file the file
	 * @param filterX the filter x
	 * @param lineText the line text
	 * @param isModify the is modify
	 * @param isHandleFile the is handle file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void cycleFilePath(File file, FileFilterX filterX, LineText lineText, boolean isModify, FileHandle fileHandle) throws IOException {
		File[] listFiles = filterX == null ? file.listFiles() : file.listFiles(filterX);
		if (file.isFile() || listFiles.length == 0) {
			if (isModify) {
				modifyFile(file, lineText, true);
			}
			if (fileHandle != null) {
				fileHandle.handleFile(file);
			}
			return;
		}
		for (int i = 0; i < listFiles.length; i++) {
			cycleFilePath(listFiles[i], filterX, lineText, isModify, fileHandle);
		}

	}

	/**
	 * Read file to str.
	 * 
	 * @param file the file
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String readFileToStr(File file) throws IOException {
		return modifyFile(file, null, false);
	}

	/**
	 * Modify file.
	 * 
	 * @param file the file
	 * @param lineText the line text
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String readFileToStr(File file, LineText lineText) throws IOException {
		return modifyFile(file, lineText, false);
	}

	/**
	 * Modify file.
	 * 
	 * @param file the file
	 * @param lineText the line text
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String modifyFile(File file, LineText lineText) throws IOException {
		return modifyFile(file, lineText, true);
	}

	/**
	 * Modify file.
	 *
	 * @param file the file
	 * @param lineText the line text
	 * @param isModify the is modify
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String modifyFile(File file, LineText lineText, boolean isModify) throws IOException {
		Assert.notNull(file);
		StringBuilder builder = null;
		if (file.exists() && file.isFile()) {
			if (file.canWrite()) {
				FileReader fileReader = null;
				BufferedReader bufferedReader = null;
				try {
					builder = new StringBuilder();
					fileReader = new FileReader(file);
					bufferedReader = new BufferedReader(fileReader);
					String temp = null;
					while (((temp = bufferedReader.readLine()) != null)) {
						if (lineText != null) {
							lineText.modifyLine(builder, temp);
							builder.append(lineText.modifyLine(temp));
						} else {
							builder.append(temp + "\r\n");
						}
					}
				} finally {
					bufferedReader.close();
					fileReader.close();
				}
				// 修改后的内容写入到文件
				if (isModify) {
					FileWriter fileWriter = null;
					try {
						fileWriter = new FileWriter(file);
						fileWriter.write(builder.toString());
					} finally {
						fileWriter.close();
					}
				}
			}
		} else {
			throw new IOException("check  this file is file or can write");
		}
		return builder == null || builder.length() == 0 ? null : builder.toString();

	}

	// -----------------------------------------------------------------------
	/**
	 * Opens a {@link FileInputStream} for the specified file, providing better error messages than simply calling <code>new FileInputStream(file)</code>.
	 * <p>
	 * At the end of the method either the stream will be successfully opened, or an exception will have been thrown.
	 * <p>
	 * An exception is thrown if the file does not exist. An exception is thrown if the file object exists but is a directory. An exception is thrown if the file exists but cannot be read.
	 * 
	 * @param file the file to open for input, must not be <code>null</code>
	 * @return a new {@link FileInputStream} for the specified file
	 * @throws IOException if the file cannot be read
	 * @since Commons IO 1.3
	 */
	public static FileInputStream openInputStream(File file) throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new IOException("File '" + file + "' exists but is a directory");
			}
			if (file.canRead() == false) {
				throw new IOException("File '" + file + "' cannot be read");
			}
		} else {
			throw new FileNotFoundException("File '" + file + "' does not exist");
		}
		return new FileInputStream(file);
	}

	/**
	 * Returns an Iterator for the lines in a <code>File</code>.
	 * <p>
	 * This method opens an <code>InputStream</code> for the file. When you have finished with the iterator you should close the stream to free internal resources. This can be done by calling the
	 * {@link LineIterator#close()} or {@link LineIterator#closeQuietly(LineIterator)} method.
	 * <p>
	 * The recommended usage pattern is:
	 * 
	 * <pre>
	 * LineIterator it = FileUtils.lineIterator(file, &quot;UTF-8&quot;);
	 * try {
	 * 	while (it.hasNext()) {
	 * 		String line = it.nextLine();
	 * 		// / do something with line
	 * 	}
	 * } finally {
	 * 	LineIterator.closeQuietly(iterator);
	 * }
	 * </pre>
	 * <p>
	 * If an exception occurs during the creation of the iterator, the underlying stream is closed.
	 * 
	 * @param file the file to open for input, must not be <code>null</code>
	 * @param encoding the encoding to use, <code>null</code> means platform default
	 * @return an Iterator of the lines in the file, never <code>null</code>
	 * @throws IOException in case of an I/O error (file closed)
	 * @since Commons IO 1.2
	 */
	public static LineIterator lineIterator(File file, String encoding) throws IOException {
		InputStream in = null;
		try {
			in = openInputStream(file);
			return IOUtils.lineIterator(in, encoding);
		} catch (IOException ex) {
			IOUtils.closeQuietly(in);
			throw ex;
		} catch (RuntimeException ex) {
			IOUtils.closeQuietly(in);
			throw ex;
		}
	}

	/**
	 * Returns an Iterator for the lines in a <code>File</code> using the default encoding for the VM.
	 * 
	 * @param file the file to open for input, must not be <code>null</code>
	 * @return an Iterator of the lines in the file, never <code>null</code>
	 * @throws IOException in case of an I/O error (file closed)
	 * @see #lineIterator(File, String)
	 * @since Commons IO 1.3
	 */
	public static LineIterator lineIterator(File file) throws IOException {
		return lineIterator(file, null);
	}

	/**
	 * Copy file.
	 *
	 * @param src the src
	 * @param target the target
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void copyFile(File src, File target) throws IOException {
		Assert.notNull(src);
		Assert.notNull(target);
		if (!src.exists()) {
			throw new GException(" sourceFile file is not exists ");
		}

		if (!target.exists()) {
			target.getParentFile().mkdirs();
			target.createNewFile();
		}

		FileInputStream fis = null;
		FileChannel fisChannel = null;
		FileOutputStream fos = null;
		FileChannel fosChannel = null;
		try {
			fis = new FileInputStream(src);
			fisChannel = fis.getChannel();
			fos = new FileOutputStream(target);
			fosChannel = fis.getChannel();
			fosChannel.transferFrom(fisChannel, 0, fisChannel.size());
		} finally {
			if (fosChannel != null)
				fosChannel.close();
			if (fisChannel != null)
				fisChannel.close();
			if (fos != null)
				fos.close();
			if (fis != null)
				fis.close();
		}

	}

	/**
	 * Copy file.
	 *
	 * @param src the src
	 * @param target the target
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void copyFileWithMapByteBuffer(File src, File target) throws IOException, SecurityException {
		Assert.notNull(src);
		Assert.notNull(target);
		if (!src.exists()) {
			throw new GException(" sourceFile file is not exists ");
		}

		if (!target.exists()) {
			target.getParentFile().mkdirs();
			target.createNewFile();
		}

		FileInputStream fis = null;
		FileChannel fisChannel = null;
		FileOutputStream fos = null;
		FileChannel fosChannel = null;
		try {
			fis = new FileInputStream(src);
			fisChannel = fis.getChannel();
			fos = new FileOutputStream(target);
			fosChannel = fis.getChannel();
			final MappedByteBuffer mbb = fisChannel.map(FileChannel.MapMode.READ_ONLY, 0, fisChannel.size());
			fosChannel.write(mbb);
			mbb.flip(); // 清空缓存
			// 清楚MappedByteBuffer占用文件
			/**
			 * AccessController.doPrivileged(new PrivilegedAction() { public Object run() { try { Method getCleanerMethod = mbb.getClass().getMethod("cleaner", new Class[0]);
			 * getCleanerMethod.setAccessible(true); sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(mbb, new Object[0]); cleaner.clean(); } catch (Exception e) {
			 * e.printStackTrace(); } return null; } });
			 */

			/**
			 * try { Method m = FileChannelImpl.class.getDeclaredMethod("unmap", MappedByteBuffer.class); m.setAccessible(true); m.invoke(FileChannelImpl.class, mbb); } catch (Exception e) { throw new
			 * IOException(e.getMessage()); }
			 */
		} finally {
			if (fosChannel != null)
				fosChannel.close();
			if (fisChannel != null)
				fisChannel.close();
			if (fos != null)
				fos.close();
			if (fis != null)
				fis.close();

		}
	}

	public void writeStrToFile(String str, File file) throws IOException {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(str);
		} finally {
			if (writer != null)
				writer.close();
		}

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

}
