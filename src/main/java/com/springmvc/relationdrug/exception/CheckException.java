package com.springmvc.relationdrug.exception;

/***
 * 
 * Summary : 检测时异常
 * 
 */
public class CheckException extends Exception {
	private static final long serialVersionUID = 3257067322535951532L;

	public CheckException() {
		super();
	}

	public CheckException(String paramString, Throwable paramThrowable) {
		super(paramString, paramThrowable);
	}

	public CheckException(String paramString) {
		super(paramString);
	}

	public CheckException(Throwable paramThrowable) {
		super(paramThrowable);
	}

}
