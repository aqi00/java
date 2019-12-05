package com.string.regular;

//演示如何通过正则串分割字符串
public class RegexSplit {

	public static void main(String[] arg) {
		// 通过逗号分割字符串
		splitByComma();
		// 通过空格分割字符串
		splitBySpace();
		// 通过点号分割字符串
		splitByDot();
		// 通过竖线分割字符串
		splitByLine();
		// 利用竖线同时指定多个串来分割字符串
		splitByMixture();
		// 通过算术的加减乘除符号来分割字符串
		splitByArith();
		// 通过圆括号及其内部数字来分割字符串
		splitByBracket();
		// 通过特殊符号的加号来分割字符串
		splitWithPlus();
		// 通过特殊符号的星号来分割字符串
		splitWithStar();
		// 通过大小写字母来分割字符串
		splitWithLetter();
		// 通过特殊符号的点号来分割字符串
		splitWithDot();
	}

	// 通过逗号分割字符串
	private static void splitByComma() {
		String commaStr = "123,456,789";
		String[] commaArray = commaStr.split(","); // 利用split方法指定按照逗号切割字符串
		for (String item : commaArray) { // 遍历并打印分割后的字符串数组元素
			System.out.println("comma item = " + item);
		}
	}

	// 通过空格分割字符串
	private static void splitBySpace() {
		String spaceStr = "123 456 789";
		String[] spaceArray = spaceStr.split(" "); // 利用split方法指定按照空格切割字符串
		for (String item : spaceArray) { // 遍历并打印分割后的字符串数组元素
			System.out.println("space item = " + item);
		}
	}

	// 通过点号分割字符串
	private static void splitByDot() {
		String dotStr = "123.456.789";
		// split(".")无法得到分割后的字符串数组
		// String[] dotArray = dotStr.split(".");
		// 点号是正则串的保留字符，需要转义（在点号前面加两个反斜杆）
		String[] dotArray = dotStr.split("\\.");
		for (String item : dotArray) { // 遍历并打印分割后的字符串数组元素
			System.out.println("dot item = " + item);
		}
	}

	// 通过竖线分割字符串
	private static void splitByLine() {
		String lineStr = "123|456|789";
		// split("|")分割得到的字符串数组，每个数组元素只有一个字符，类似于toCharArray的结果
		// String[] lineArray = lineStr.split("|");
		// 竖线是正则串的保留字符，需要转义（在竖线前面加两个反斜杆）
		String[] lineArray = lineStr.split("\\|");
		for (String item : lineArray) { // 遍历并打印分割后的字符串数组元素
			System.out.println("line item = " + item);
		}
	}

	// 利用竖线同时指定多个串来分割字符串
	private static void splitByMixture() {
		String mixtureStr = "123,456 789";
		// 正则串里的竖线表示“或”，竖线左边和右边的字符都可以用来分割字符串
		String[] mixtureArray = mixtureStr.split(",| ");
		for (String item : mixtureArray) { // 遍历并打印分割后的字符串数组元素
			System.out.println("mixture item = " + item);
		}
	}

	// 通过算术的加减乘除符号来分割字符串
	private static void splitByArith() {
		String arithStr = "123+456*789-123/456%789";
		// 正则串里的加号、星号、横线都要转义，加减乘除符号之间通过竖线隔开
		String[] arithArray = arithStr.split("\\+|\\*|\\-|/|%");
		for (String item : arithArray) { // 遍历并打印分割后的字符串数组元素
			System.out.println("arith item = " + item);
		}
	}

	// 通过圆括号及其内部数字来分割字符串
	private static void splitByBracket() {
		String bracketStr = "(1)123;(2)456;(3)789;";
		// 圆括号也是正则串的保留字符，0到9这九个数字使用竖线隔开
		// String[] bracketArray =
		// bracketStr.split("\\((0|1|2|3|4|5|6|7|8|9)\\)");
		// 利用方括号聚集一群字符，表示这些字符之间是“或”的关系，故而可省略竖线
		// String[] bracketArray = bracketStr.split("\\([0123456789]\\)");
		// 连续的数字可使用横线连接首尾数字，例如“0-9”表示从0到9之间的所有数字
		// String[] bracketArray = bracketStr.split("\\([0-9]\\)");
		// 利用“\\d”即可表达0到9的数字，后面的{1}表示1位数字，依此类推{3}表示三位数字
		// String[] bracketArray = bracketStr.split("\\(\\d{1}\\)");
		// “\\d”默认就是1位数字，此时后面的{1}可直接略去
		String[] bracketArray = bracketStr.split("\\(\\d\\)");
		for (String item : bracketArray) { // 遍历并打印分割后的字符串数组元素
			System.out.println("bracket item = " + item);
		}
	}

	// 通过特殊符号的加号来分割字符串
	private static void splitWithPlus() {
		String bracketStr = "(1)123;(2)456;(13)789;";
		// 正则串里的加号表示可以有1到多个前面的字符
		String[] bracketArray = bracketStr.split("\\(\\d+\\)");
		for (String item : bracketArray) { // 遍历并打印分割后的字符串数组元素
			System.out.println("plus item = " + item);
		}
	}

	// 通过特殊符号的星号来分割字符串
	private static void splitWithStar() {
		String bracketStr = "()123;(2)456;(13)789;";
		// 正则串里的星号表示可以有0到多个前面的字符
		String[] bracketArray = bracketStr.split("\\(\\d*\\)");
		for (String item : bracketArray) { // 遍历并打印分割后的字符串数组元素
			System.out.println("star item = " + item);
		}
	}

	// 通过大小写字母来分割字符串
	private static void splitWithLetter() {
		String bracketStr = "(a)123;(B)456;(c)789;";
		// 在正则串中表达小写字母和大写字母
		String[] bracketArray = bracketStr.split("\\([a-zA-Z]\\)");
		for (String item : bracketArray) { // 遍历并打印分割后的字符串数组元素
			System.out.println("letter item = " + item);
		}
	}

	// 通过特殊符号的点号来分割字符串
	private static void splitWithDot() {
		String bracketStr = "(1)123;(B)456;(%)789;";
		// 正则串里的点号表示除了回车\r和换行\n以外的其它字符
		String[] bracketArray = bracketStr.split("\\(.\\)");
		for (String item : bracketArray) { // 遍历并打印分割后的字符串数组元素
			System.out.println("dot item = " + item);
		}
	}

}
