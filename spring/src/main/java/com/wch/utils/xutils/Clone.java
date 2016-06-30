package com.wch.utils.xutils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.wch.utils.clazz.ClassUtils;

/**
 * 
 * @author wch
 *
 */
public class Clone {

	/**
	 * 深克隆对象
	 * @param t
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public  static <T> T deepClone(T t) throws ClassNotFoundException, IOException {
		//判断其是否实现了Serializable 
		if (ClassUtils.isImSerializable(t.getClass())) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(30);
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(t);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		}
		throw  new IOException("你要实现克隆的类未实现Serializable 无法克隆 ");
	}
}
