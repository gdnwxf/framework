package com.wch.utils;

public class GException extends RuntimeException {

	/**    */
	private static final long serialVersionUID = 1L;

	/**
	  */
	public GException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public GException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public GException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public GException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
