package com.string.character;

//演示字符类型、字符数组的用法，以及特殊字符的转义处理
public class TypeChar {

	public static void main(String[] arg) {
		char a = 'A'; // 声明一个字符变量，并对其赋值
		System.out.println("a=" + a);
		char tian = '田'; // 字符包括英文字符，也包括中文字符
		System.out.println("tian=" + tian);
		char one = '1'; // 字符还包括数字字符，以及标点符号
		System.out.println("one=" + one);
		char[] array = new char[]{'A', 'B', 'C'}; // 声明一个字符数组，并对其初始化
		//char[] array = { 'A', 'B', 'C' }; // 简化之后的字符数组初始化操作
		for (char item : array) { // 遍历并打印字符数组中的每个字符
			System.out.println("item=" + item);
		}
		// 下列是特殊字符的转义表达形式
		char tab = '\t'; // 制表符的转义符为\t
		System.out.println("tab=" + tab);
		char enter = '\r'; // 回车符的转义符为\r
		System.out.println("enter=" + enter);
		char line = '\n'; // 换行符的转义符为\n
		System.out.println("line=" + line);
		char singleQuote = '\''; // 单引号的转义符为\'
		System.out.println("singleQuote=" + singleQuote);
		char doubleQuote = '\"'; // 双引号的转义符为\"
		System.out.println("doubleQuote=" + doubleQuote);
		char reverseTilt = '\\'; // 反斜杆的转义符为\\
		System.out.println("reverseTilt=" + reverseTilt);
	}
}
