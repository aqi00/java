package com.addition.exception;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//演示如何预防常见的异常与错误
public class TestCheck {

	public static void main(String[] args) throws ParseException {
		testDivideByDecimal(); // 测试算术异常：商是无限循环小数
		// testArrayByIndex(); // 测试越界异常：下标超出数组范围
		// testStringByNull(); // 测试空指针异常：对象不存在
		// testConvertLyList(); // 测试类型转换异常：原始数据与目标类型不匹配
		// testUnlimitedString(); // 测试内存溢出错误：程序需要的内存超过了最大的堆内存配置
		// testUnlimitedRecursion(); // 测试栈溢出错误：程序占用的栈空间超过了配置的栈内存大小
	}

	// 测试算术异常：商是无限循环小数
	// java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
	private static void testDivideByDecimal() {
		BigDecimal one = BigDecimal.valueOf(1);
		BigDecimal three = BigDecimal.valueOf(3);
		// 大小数的除法运算，小数点后面保留64位，其中最后一位做四舍五入
		BigDecimal result = one.divide(three, 64, BigDecimal.ROUND_HALF_UP);
		System.out.println("sqrt result=" + result);
	}

	// 测试越界异常：下标超出数组范围
	// java.lang.ArrayIndexOutOfBoundsException: 3
	private static void testArrayByIndex() {
		int[] array = { 1, 2, 3 };
		// 在根据下标获取数组元素之前，先判断该下标是否落在数组范围之内
		if (array.length > 3) {
			int item = array[3];
			System.out.println("array item=" + item);
		} else {
			System.out.println("array's length isn't more than 3");
		}
	}

	// 测试空指针异常：对象不存在
	// java.lang.NullPointerException
	private static void testStringByNull() {
		String str = null;
		// 在跟某位对象约会之前，先打个电话问问有没空，否则牵肠挂肚空欢喜一场
		if (str != null) {
			int length = str.length();
			System.out.println("str length=" + length);
		} else {
			System.out.println("str is null");
		}
	}

	// 测试类型转换异常：原始数据与目标类型不匹配
	// java.lang.ClassCastException: java.util.Arrays$ArrayList cannot be cast to java.util.ArrayList
	private static void testConvertLyList() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		// 在做强制类型转换之前，先把它的底细摸清楚。正所谓“知己知彼，百战不殆”
		if (list instanceof ArrayList<?>) {
			ArrayList<Integer> arrays = (ArrayList<Integer>) list;
			System.out.println("arrays size=" + arrays.size());
		} else {
			System.out.println("arrays is not belong to ArrayList");
		}
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
		String result = "";
		try { // 开始小心翼翼地尝试，随时准备捕捉异常
			result = getUnlimitedString(append); // 递归调用自身方法
		} catch (OutOfMemoryError e) { // 捕捉到了内存溢出错误
			System.out.println("str length=" + str.length());
			e.printStackTrace(); // 打印出错时的栈轨迹信息
			// eclipse.ini里面的Xms参数表示JVM初始化堆内存大小，Xmx参数表示JVM最大的堆内存大小，
			// 每个char占用两个字节，那么字符串对象占用的内存大小约等于：它的长度*2字节。
		}
		return result;
	}

	// 测试栈溢出错误：程序占用的栈空间超过了配置的栈内存大小
	// java.lang.StackOverflowError
	private static void testUnlimitedRecursion() {
		recursionAction(); // 用于递归动作的方法
	}

	private static int recursion_count = 0; // 递归次数

	// 用于递归动作的方法
	private static void recursionAction() {
		System.out.println("recursionAction");
		try { // 开始小心翼翼地尝试，随时准备捕捉异常
			recursion_count++;
			recursionAction(); // 递归调用自身方法
		} catch (StackOverflowError e) { // 捕捉到了栈溢出错误
			System.out.println("recursion_count=" + recursion_count);
			e.printStackTrace(); // 打印出错时的栈轨迹信息
			// Java程序运行时的Xss参数表示配置的栈大小。
			// 栈里面保存着每次方法调用时的局部变量与参数，以便调用结束时继续后面的代码逻辑。
			// 如果出现递归调用的情况，那么栈中保存的调用现场数据将不断增长；
			// 要是没有及时退出递归，迟早会把整个栈空间撑爆，也就是发生所谓的栈溢出错误。
		}
	}

}
