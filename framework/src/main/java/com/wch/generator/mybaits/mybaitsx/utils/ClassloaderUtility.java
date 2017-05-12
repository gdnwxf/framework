package com.wch.generator.mybaits.mybaitsx.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.internal.util.messages.Messages;

public class ClassloaderUtility {
	public static ClassLoader getCustomClassloader(List<String> entries) {
		ArrayList urls = new ArrayList();
		if (entries != null) {
			Iterator parent = entries.iterator();

			while (parent.hasNext()) {
				String ucl = (String) parent.next();
				File file = new File(ucl);
				if (!file.exists()) {
					throw new RuntimeException(Messages.getString("RuntimeError.9", ucl));
				}

				try {
					urls.add(file.toURI().toURL());
				} catch (MalformedURLException arg5) {
					throw new RuntimeException(Messages.getString("RuntimeError.9", ucl));
				}
			}
		}

		ClassLoader parent1 = Thread.currentThread().getContextClassLoader();
		URLClassLoader ucl1 = new URLClassLoader((URL[]) urls.toArray(new URL[urls.size()]), parent1);
		return ucl1;
	}
}