package com.string.string;

import java.math.BigDecimal;
import java.math.BigInteger;

//演示如何给字符串变量赋值，以及如何把字符串变量转换成其它变量类型
public class StrAssign {

	public static void main(String[] arg) {
		// 给字符串变量赋值的四种方式
		// 第一种方式：用双引号把字符串括起来
		String fromQuote = "Hello";
		System.out.println("fromQuote=" + fromQuote);
		// 第二种方式：使用String的valueOf方法把数值、布尔、字符、字符数组等变量转换为字符串
		String fromValueOf = String.valueOf(111);
		System.out.println("fromValueOf=" + fromValueOf);
		// 第三种方式：对于字符数组来说，还能通过new关键字创建字符串变量
		char[] array = { 'A', 'B', 'C' };
		String fromArray = new String(array);
		System.out.println("fromArray=" + fromArray);
		// 第四种方式：对于基本变量类型（数组除外）来说，也可以利用加号连接基本变量和空串
		// 注意，数值变量之间的加号为算术上的相加运算，而字符串之间的加号为两个字符串的合并操作
		String fromPlus = true + "";
		System.out.println("fromPlus=" + fromPlus);

		String number = "13456";
		Integer packInt = Integer.parseInt(number); // 将字符串变量转换成包装整型变量
		System.out.println("packInt=" + packInt);
		Long packLong = Long.parseLong(number); // 将字符串变量转换成包装长整型变量
		System.out.println("packLong=" + packLong);
		Float packFloat = Float.parseFloat(number); // 将字符串变量转换成包装浮点型变量
		System.out.println("packFloat=" + packFloat);
		Double packDouble = Double.parseDouble(number); // 将字符串变量转换成包装双精度型变量
		System.out.println("packDouble=" + packDouble);
		String zhen = "true";
		Boolean packBoolean = Boolean.parseBoolean(zhen); // 将字符串变量转换成包装布尔型变量
		System.out.println("packBoolean=" + packBoolean);
		char[] numberArray = number.toCharArray(); // 将字符串转换成字符数组
		for (char item : numberArray) { // 遍历并打印字符数组中的各元素
			System.out.println("item=" + item);
		}

		String bigNumber = "134567890134567890134567890";
		BigInteger bigInt = new BigInteger(bigNumber); // 将字符串变量转换成大整数变量
		System.out.println("bigInt=" + bigInt);
		BigDecimal bigDec = new BigDecimal(bigNumber); // 将字符串变量转换成大小数变量
		System.out.println("bigDec=" + bigDec);

		// 调用toString方法即可将包装变量和大数字变量转换为字符串
		System.out.println("packInt toString=" + packInt.toString());
		System.out.println("packLong toString=" + packLong.toString());
		System.out.println("packFloat toString=" + packFloat.toString());
		System.out.println("packDouble toString=" + packDouble.toString());
		System.out.println("packBoolean toString=" + packBoolean.toString());
		System.out.println("bigInt toString=" + bigInt.toString());
		System.out.println("bigDec toString=" + bigDec.toString());
	}
}
