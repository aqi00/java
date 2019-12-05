package com.addition.exception;

//定义一个数组越界异常。异常类必须由Exception派生而来
public class ArrayOutOfException extends Exception {
	private static final long serialVersionUID = -1L;

	public ArrayOutOfException(String message) {
		super(message);
	}

}
