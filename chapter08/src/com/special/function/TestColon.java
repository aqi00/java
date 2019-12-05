package com.special.function;

import java.util.Arrays;
import java.util.Comparator;

//演示方法引用的几种场景
public class TestColon {

	public static void main(String[] args) {
		testSelect(); // 在挑选符合条件的数组元素时，可采取方法引用
		testCompare(); // 在对字符串数组排序时，也可采取方法引用
		testStatic(); // 演示静态方法的方法引用
		testInstance(); // 演示实例方法的方法引用
	}

	// 在挑选符合条件的数组元素时，可采取方法引用
	private static void testSelect() {
		// 原始的字符串数组
		String[] strArray = { "Hello", "world", "What", "is", "The", "Wether", "today", "" };
		String[] resultArray; // 筛选后的字符串数组
		// 采取匿名内部类方式筛选字符串数组
		resultArray = StringUtil.select(strArray, new StringFilter() {
			@Override
			public boolean isMatch(String str) {
				return str.contains("e"); // 是否包含字母e
			}
		});
		// 采取Lambda表达式来筛选字符串数组
		resultArray = StringUtil.select(strArray, (str) -> str.contains("e"));
		print(resultArray, "contains方法");
		resultArray = StringUtil.select(strArray, (str) -> str.indexOf("e") >= 0);
		resultArray = StringUtil.select(strArray, (str) -> str.isEmpty());
		// 采取双冒号的方法引用来筛选字符串数组。只挑选空串
		resultArray = StringUtil.select(strArray, String::isEmpty);
		print(resultArray, "isEmpty方法");
		// 被引用的方法存在输入参数，则将该参数挪到挑选方法select2的后面。只挑选包含字母o的串
		resultArray = StringUtil.select2(strArray, String::contains, "o");
		print(resultArray, "contains方法");
		// 被引用的方法换成了startsWith。只挑选以字母W开头的串
		resultArray = StringUtil.select2(strArray, String::startsWith, "W");
		print(resultArray, "startsWith方法");
		// 被引用的方法换成了endsWith。只挑选以字母y结尾的串
		resultArray = StringUtil.select2(strArray, String::endsWith, "y");
		print(resultArray, "endsWith方法");
		// 如需对字符串筛选更复杂的条件，可利用matches方法通过正则表达式来校验
		resultArray = StringUtil.select2(strArray, String::matches,
				"[wW][a-zA-Z]*");
		print(resultArray, "matches方法");
	}

	// 打印排序后的数组元素遍历结果
	private static void print(String[] strArray, String explain) {
		String desc = explain + "的挑选结果为：";
		for (String item : strArray) {
			desc = desc + item + ", ";
		}
		System.out.println(desc);
	}

	// 在对字符串数组排序时，也可采取方法引用
	private static void testCompare() {
		String[] strArray = { "Hello", "world", "What", "is", "The", "Wether",
				"today" };
		// 采取匿名内部类方式对字符串数组进行默认的排序操作
		Arrays.sort(strArray, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		// 采取Lambda表达式对字符串数组进行默认的排序操作
		Arrays.sort(strArray, (o1, o2) -> o1.compareTo(o2));
		// 因为compareTo前后的两个变量都是数组的字符串元素，
		// 所以可直接简写为该方法的引用形式，反正编译器晓得该怎么调用
		Arrays.sort(strArray, String::compareTo);
		print(strArray, "字符串数组按首字母拼写顺序");
		// Arrays.sort(strArray, (s1,s2) -> s1.compareToIgnoreCase(s2));
		// 把compareTo方法换成compareToIgnoreCase方法，表示首字母不区分大小写
		Arrays.sort(strArray, String::compareToIgnoreCase);
		print(strArray, "字符串数组按首字母不区分大小写");
	}

	// 演示静态方法的方法引用
	private static void testStatic() {
		double result;
		// 采取匿名内部类方式对两个操作数做指定运算（求较大值）
		result = Arithmetic.calculate(new Calculator() {
			@Override
			public double operate(double x, double y) {
				return Math.max(x, y);
			}
		}, 3, 2);
		// 采取Lambda表达式对两个操作数做指定运算（求较大值）
		result = Arithmetic.calculate((x, y) -> Math.max(x, y), 3, 2);
		result = Arithmetic.calculate(Math::max, 3, 2); // 静态方法引用，求两数的较大值
		System.out.println("两数的较大值=" + result);
		result = Arithmetic.calculate(Math::min, 3, 2); // 静态方法引用，求两数的较小值
		System.out.println("两数的较小值=" + result);
		result = Arithmetic.calculate(Math::pow, 3, 2); // 静态方法引用，求某数的n次方
		System.out.println("两数之乘方=" + result);
		result = Arithmetic.calculate(Double::sum, 3, 2); // 静态方法引用，求两数之和求两数之和
		System.out.println("两数之和=" + result);
	}

	// 演示实例方法的方法引用
	private static void testInstance() {
		MathUtil math = new MathUtil(); // 创建一个数学工具的实例
		double result;
		result = Arithmetic.calculate(math::add, 3, 2); // 实例方法引用，求两数之和
		System.out.println("两数之和=" + result);
		result = Arithmetic.calculate(math::minus, 3, 2); // 实例方法引用，求两数之差
		System.out.println("两数之差=" + result);
		result = Arithmetic.calculate(math::multiply, 3, 2); // 实例方法引用，求两数之积
		System.out.println("两数之积=" + result);
		result = Arithmetic.calculate(math::divide, 3, 2); // 实例方法引用，求两数之商
		System.out.println("两数之商=" + result);
		result = Arithmetic.calculate(math::remainder, 3, 2); // 实例方法引用，求两数之余
		System.out.println("两数之余=" + result);
		result = Arithmetic.calculate(math::max, 3, 2); // 实例方法引用，求两数的较大值
		System.out.println("两数的较大值=" + result);
		result = Arithmetic.calculate(math::min, 3, 2); // 实例方法引用，求两数的较小值
		System.out.println("两数的较小值=" + result);
		result = Arithmetic.calculate(math::pow, 3, 2); // 实例方法引用，求某数的n次方
		System.out.println("两数之乘方=" + result);
		result = Arithmetic.calculate(math::sqrt, 3, 2); // 实例方法引用，求某数的n次方根
		System.out.println("两数之开方=" + result);
	}

}
