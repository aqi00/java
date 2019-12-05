package com.string.string;

//演示如何使用String类型的常见方法
public class StrMethod {

	public static void main(String[] arg) {
		String hello = "Hello World. 你好世界。";
		boolean isEmpty = hello.isEmpty(); // isEmpty方法判断该字符串是否为空串
		System.out.println("是否为空 = " + isEmpty);
		boolean equals = hello.equals("你好"); // equals方法判断该字符串是否与目标串相等
		System.out.println("是否等于你好 = " + equals);
		boolean startsWith = hello.startsWith("Hello"); // startsWith方法判断该字符串是否以目标串开头
		System.out.println("是否以Hello开头 = " + startsWith);
		boolean endsWith = hello.endsWith("World"); // endsWith方法判断该字符串是否以目标串结尾
		System.out.println("是否以World结尾 = " + endsWith);
		boolean contains = hello.contains("or"); // contains方法判断该字符串是否包含了目标串
		System.out.println("是否包含or = " + contains);

		int char_length = hello.length(); // length方法返回该字符串的字符数
		System.out.println("字符数 = " + char_length);
		int byte_length = hello.getBytes().length; // getBytes方法返回该字符串对应的字节数组
		System.out.println("字节数 = " + byte_length);
		char first = hello.charAt(0); // charAt方法返回该字符串在指定位置的字符
		System.out.println("首字符 = " + first);
		int index = hello.indexOf("l"); // indexOf方法返回目标串在源串中第一次找到的位置
		System.out.println("首次找到l的位置 = " + index);
		int lastIndex = hello.lastIndexOf("l"); // lastIndexOf方法返回目标串在源串中最后一次找到的位置
		System.out.println("最后找到l的位置 = " + lastIndex);
		
		String lowerCase = hello.toLowerCase(); // toLowerCase方法返回转换为小写字母的字符串
		System.out.println("转换为小写字母 = " + lowerCase);
		String upperCase = hello.toUpperCase(); // toUpperCase方法返回转换为大写字母的字符串
		System.out.println("转换为大写字母 = " + upperCase);
		String trim = hello.trim(); // trim方法返回去掉首尾空格后的字符串
		System.out.println("去掉首尾空格 = " + trim);
		String concat = hello.concat("Fine, thank you."); // concat方法返回在末尾添加目标串后的字符串
		System.out.println("追加了目标串 = " + concat);
		// 只有一个输入参数的substring方法，从指定位置一直截取到源串的末尾
		String subToEnd = hello.substring(6);
		System.out.println("从第六位截取到末尾 = " + subToEnd);
		// 有两个输入参数的substring方法，返回从开始位置到结束位置中间截取的子串
		String subToCustom = hello.substring(6, 9);
		System.out.println("从第六位截取到第九位 = " + subToCustom);
		String replace = hello.replace("l", "L"); // replace方法返回目标串替换后的字符串
		System.out.println("把l替换为L = " + replace);
	}

}
