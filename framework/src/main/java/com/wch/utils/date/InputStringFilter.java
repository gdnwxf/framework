
package com.wch.utils.date;

import java.util.List;



public interface InputStringFilter {
	/**
	 * TODO 请在此处添加注释
	 * @param inputList
	 * @param temp
	 * @return
	 */
	public abstract boolean handle(List<String> inputList, String temp);

}
