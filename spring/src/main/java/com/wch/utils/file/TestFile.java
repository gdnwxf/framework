

package com.wch.utils.file;

import java.io.File;

import org.junit.Test;


public class TestFile {

	@Test
	public void testFileNameModi() throws Exception {
		FileUtils.batchRenameFile(new File("d://test"), new FileNameModi() {
			Integer aInteger = 10;

			/**
			 * @param file
			 * @return
			 * @see com.wch.utils.file.FileNameModi#modiFileName(java.io.File)
			 */
			@Override
			public String modiFileName(File file) {
				String name = file.getName();
				if (name.contains("newFile")) {
					FileUtils.renameTo(file, "newFile" + --aInteger + ".txt");
				}

				return name;
			}
		});

	}

}
