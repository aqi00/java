package com.collect.generic;

import java.util.Arrays;
import java.util.List;

//演示如何使用自定义的泛型方法
public class TestFunction {

	public static void main(String[] arg) {
		testNumber(); // 演示泛型变量的用法
		testString(); // 演示泛型方法的用法
	}

	// 演示泛型变量的用法
	private static void testNumber() {
		Integer oneInt = 1;
		Float oneFloat = 1.0f;
		boolean equalsSimple = oneInt.equals(oneFloat);
		System.out.println("equalsSimple=" + equalsSimple);
		// 通过Number基类比较两个数值变量是否相等
		boolean equalsNumber = equalsNumber(oneInt, oneFloat);
		System.out.println("equalsNumber=" + equalsNumber);
		// 通过泛型变量比较两个数值变量是否相等
		boolean equalsGeneric = equalsGeneric(oneInt, oneFloat);
		System.out.println("equalsGeneric=" + equalsGeneric);
	}

	// 通过Number基类比较两个数值变量是否相等
	public static boolean equalsNumber(Number n1, Number n2) {
		return n1.doubleValue() == n2.doubleValue();
	}

	// 通过泛型变量比较两个数值变量是否相等。利用尖括号包裹泛型的派生操作
	public static <T extends Number> boolean equalsGeneric(T t1, T t2) {
		return t1.doubleValue() == t2.doubleValue();
	}

	// 演示泛型方法的用法
	private static void testString() {
		Double[] doubleArray = new Double[] { 1.1, 2D, 3.1415926, 11.11 };
		// System.out.println("doubleArray="+doubleArray);
		// System.out.println("doubleArray.toString()="+doubleArray.toString());
		System.out.println("Arrays.toString=" + Arrays.toString(doubleArray));
		System.out.println("objectsToString=" + objectsToString(doubleArray));
		System.out.println("arraysToString=" + arraysToString(doubleArray));
		List<Double> doubleList = Arrays.asList(1.1, 2D, 3.1415926, 11.11);
		// System.out.println("doubleList="+doubleList);
		System.out.println("doubleList.toString()=" + doubleList.toString());
		System.out.println("listToString=" + listToString(doubleList));
		System.out.println("listToStringByQuestion="
				+ listToStringByQuestion(doubleList));
	}

	// 把对象数组里的各个元素拼接成字符串
	public static String objectsToString(Object[] array) {
		String result = "";
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) { // 遍历数组里的所有元素
				if (i > 0) {
					result = result + " | "; // 各元素之间以竖线连接
				}
				result = result + array[i].toString();
			}
		}
		return result;
	}

	// 把泛型数组里的各个元素拼接成字符串。<T> 等同于 <T extends Object>
	// public static <T extends Object> String arraysToString(T[] array) {
	public static <T> String arraysToString(T[] array) {
		String result = "";
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) { // 遍历数组里的所有元素
				if (i > 0) {
					result = result + " | "; // 各元素之间以竖线连接
				}
				result = result + array[i].toString();
			}
		}
		return result;
	}

	// 把List清单里的各个元素拼接成字符串，此处使用了泛型
	public static <T> String listToString(List<T> list) {
		String result = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) { // 遍历清单里的所有元素
				if (i > 0) {
					result = result + " | "; // 各元素之间以竖线连接
				}
				result = result + list.get(i).toString();
			}
		}
		return result;
	}

	// 把List清单里的各个元素拼接成字符串，此处使用了问号表示不确定类型
	// 问号与泛型的区别有：
	// 1、问号只能表示已有的实例，本身不能创建实例。而泛型T既可表示已有的实例，也可给自身创建实例，如“T t;”
	// 2、问号只可用作输入参数，不可用作输出参数。而泛型T用于二者皆可。
	// 3、使用了问号的容器实例，只允许调用get方法，不允许调用add方法。而泛型容器不存在方法调用的限制
	public static String listToStringByQuestion(List<?> list) {
		String result = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) { // 遍历清单里的所有元素
				if (i > 0) {
					result = result + " | "; // 各元素之间以竖线连接
				}
				result = result + list.get(i).toString();
			}
		}
		return result;
	}

}
