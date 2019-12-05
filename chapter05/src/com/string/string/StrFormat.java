package com.string.string;

import java.math.BigDecimal;
import java.math.RoundingMode;

//演示如何进行字符串格式化，以及通过格式化截取指定小数位
public class StrFormat {

	public static void main(String[] arg) {
		// 往字符串填入另一个字符串
		String fromString = String.format("格式化子串的字符串：%s", "Hello");
		System.out.println("fromString=" + fromString);
		// 往字符串填入字符
		String fromChar = String.format("格式化字符的字符串：%c", 'A');
		System.out.println("fromChar=" + fromChar);
		// 往字符串填入布尔值
		String fromBoolean = String.format("格式化布尔值的字符串：%b", false);
		System.out.println("fromBoolean=" + fromBoolean);
		// 往字符串填入十进制整数
		String fromInt = String.format("格式化整型数的字符串：%d", 255);
		System.out.println("fromInt=" + fromInt);
		// 往字符串填入十六进制数
		String fromOct = String.format("格式化十六进制数的字符串：%o", 255);
		System.out.println("fromOct=" + fromOct);
		// 往字符串填入八进制数
		String fromHex = String.format("格式化八进制数的字符串：%x", 255);
		System.out.println("fromHex=" + fromHex);
		// 往字符串填入浮点数
		String fromFloat = String.format("格式化浮点数的字符串：%f", 3.14);
		System.out.println("fromFloat=" + fromFloat);
		// 格式化字符串的时候，同时填充多个变量
		String manyVariable = String.format(
				"以下字符串包括了多个变量值：%s，%c，%b，%d，%o，%x，%f", "Hello", 'A', false, 255,
				255, 255, 3.14);
		System.out.println("manyVariable=" + manyVariable);

		// 注意，双精度数若是通过%f格式化双精度数，则会强制转成浮点数
		String fromDouble = String.format("双精度数格式化后丢失精度的字符串：%f", 3.1415926);
		System.out.println("fromDouble=" + fromDouble);
		// 因此，格式化双精度数之时，需要指定小数点后面的保留位数
		String fromDecimal = String.format("格式化双精度数的字符串：%.8f", 3.1415926);
		System.out.println("fromDecimal=" + fromDecimal);
		// 对整数分配固定长度，该整数默认右对齐、左补空格
		String fromLenth = String.format("格式化固定长度（默认右对齐）的整数字符串：(%8d)", 255);
		System.out.println("fromLenth=" + fromLenth);
		// 对整数分配固定长度，且该整数左对齐、右补空格
		String fromLeft = String.format("格式化固定长度且左对齐的整数字符串：(%-8d)", 255);
		System.out.println("fromLeft=" + fromLeft);
		// 对整数分配固定长度，该整数默认右对齐、左补0
		String fromZero = String.format("格式化固定长度且左补0的整数字符串：(%08d)", 255);
		System.out.println("fromZero=" + fromZero);

		// 字符串格式化的时候，可能出现某个变量被多次填入的情况
		String fromRepeat1 = String.format("重要的事情说三遍：%s，%s，%s", "别迟到", "别迟到",
				"别迟到");
		System.out.println("fromRepeat1=" + fromRepeat1);
		// 重复填入某个变量值，可利用“%数字$”的形式，其中“数字$”表示这里取后面的第几个变量值
		String fromRepeat2 = String.format("重要的事情说三遍：%1$s，%1$s，%1$s", "别迟到");
		System.out.println("fromRepeat2=" + fromRepeat2);

		double normalDecimal = 19.895;
		// 保留双精度数的小数点后面两位
		String normalResult = formatWithDouble(normalDecimal, 2);
		System.out.println("normalResult=" + normalResult);
		BigDecimal bigDecimal = new BigDecimal("123456789012345678.901");
		// 保留大小数的小数点后面两位
		String bigResult = formatWithBigDecimal(bigDecimal, 2);
		System.out.println("bigResult=" + bigResult);
	}

	// 对双精度类型的变量截取小数位，多余部分的数字默认四舍五入
	public static String formatWithDouble(double value, int digit) {
		// 先根据小数位数构建格式化标记串。两个百分号%%可转义为一个百分符号%
		String format = String.format("%%.%df", digit);
		// 再依据该标记将具体数字格式化为字符串
		String result = String.format(format, value);
		return result;
	}

	// 对大小数类型的变量截取小数位，可指定多余部分数字的舍入规则
	public static String formatWithBigDecimal(BigDecimal value, int digit) {
		// 大小数类型的setScale方法需要指定明确的舍入规则，其中HALF_UP表示四舍五入
		BigDecimal result = value.setScale(digit, RoundingMode.HALF_UP);
		return result.toString();
	}

}
