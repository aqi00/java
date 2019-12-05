package com.addition.exception;

//定义一个数组为空异常。异常类必须由Exception派生而来
public class ArrayIsNullException extends Exception {
	private static final long serialVersionUID = -1L;

	public ArrayIsNullException(String message) {
		super(message);
	}

}
