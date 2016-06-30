package com.wch.utils.date;

import java.util.List;


public interface InputStringFilter {
	/**
	 * @param inputList
	 * @param temp
	 * @return
	 */
	public abstract boolean handle(List<String> inputList, String temp);

}
