package com.addition.exception;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//演示一些异常的发生场景
public class TestExcept {

	public static void main(String[] args) {
		//testDivideByZero(); // 测试算术异常：除数为0
		//testDivideByDecimal(); // 测试算术异常：商是无限循环小数
		//testArrayByIndex(); // 测试越界异常：下标超出数组范围
		//testListByIndex(); // 测试越界异常：索引超出清单范围
		//testStringByFormat(); // 测试格式异常：字符串格式非法
		testDateByFormat(); // 测试格式异常：日期格式非法
		//testStringByNull(); // 测试空指针异常：对象不存在
		//testConvertByList(); // 测试类型转换异常：原始数据与目标类型不匹配
	}

	// 测试算术异常：除数为0
	// java.lang.ArithmeticException: / by zero
	private static void testDivideByZero() {
		int one = 1;
		int zero = 0;
		int result = one / zero;
		System.out.println("divide result=" + result);
	}

	// 测试算术异常：商是无限循环小数
	// java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
	private static void testDivideByDecimal() {
		BigDecimal one = BigDecimal.valueOf(1);
		BigDecimal three = BigDecimal.valueOf(3);
		BigDecimal result = one.divide(three);
		System.out.println("sqrt result=" + result);
	}

	// 测试越界异常：下标超出数组范围
	// java.lang.ArrayIndexOutOfBoundsException: 3
	private static void testArrayByIndex() {
		int[] array = { 1, 2, 3 };
		int item = array[3];
		System.out.println("array item=" + item);
	}

	// 测试越界异常：索引超出清单范围
	// java.lang.ArrayIndexOutOfBoundsException: 5
	private static void testListByIndex() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Integer item = list.get(5);
		System.out.println("list item=" + item);
	}

	// 测试格式异常：字符串格式非法
	// java.util.IllegalFormatConversionException: d != java.lang.String
	private static void testStringByFormat() {
		String str = String.format("%d", "Hello");
		System.out.println("str=" + str);
	}

	// 测试格式异常：日期格式非法
	// java.lang.IllegalArgumentException: Illegal pattern character 'I'
	private static void testDateByFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mi:ss");
		String strDate = sdf.format(new Date());
		System.out.println("strDate=" + strDate);
	}

	// 测试空指针异常：对象不存在
	// java.lang.NullPointerException
	private static void testStringByNull() {
		String str = null;
		int length = str.length();
		System.out.println("str length=" + length);
	}

	// 测试类型转换异常：原始数据与目标类型不匹配
	// java.lang.ClassCastException: java.util.Arrays$ArrayList cannot be cast to java.util.ArrayList
	private static void testConvertByList() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		ArrayList<Integer> arrays = (ArrayList<Integer>) list;
		System.out.println("arrays size=" + arrays.size());
	}

}
