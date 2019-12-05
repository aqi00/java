package com.addition.exception;

//演示一些错误的发生场景
public class TestError {

	public static void main(String[] args) {
		testUnlimitedString(); // 内存溢出错误：程序需要的内存超过了最大的堆内存配置
		//testUnlimitedRecursion(); // 栈溢出错误：程序占用的栈空间超过了配置的栈内存大小
	}

	// 测试内存溢出错误：程序需要的内存超过了最大的堆内存配置
	// java.lang.OutOfMemoryError: Java heap space
	private static void testUnlimitedString() {
		String str = "Hello world";
		String result = getUnlimitedString(str); // 获取无限大小的字符串
		System.out.println("result=" + result.toString());
	}

	// 获取无限大小的字符串
	private static String getUnlimitedString(String str) {
		System.out.println("getUnlimitedString");
		String append = String.format("%s+%s", str, str);
		return getUnlimitedString(append);
	}

	// 测试栈溢出错误：程序占用的栈空间超过了配置的栈内存大小
	// java.lang.StackOverflowError
	private static void testUnlimitedRecursion() {
		recursionAction(); // 用于递归动作的方法
	}

	// 用于递归动作的方法
	public static void recursionAction() {
		System.out.println("recursionAction");
		recursionAction();
	}

}
