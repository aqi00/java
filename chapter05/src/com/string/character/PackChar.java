package com.string.character;

//演示包装字符类型Character的用法
public class PackChar {

	public static void main(String[] arg) {
		Character character = 'A'; // 声明一个包装字符变量
		System.out.println("character=" + character);
		char value = character.charValue(); // 把包装字符变量转换成基本字符变量
		System.out.println("value=" + value);
		// Character类型与char类型的变量之间允许直接赋值，靠的是“自动装箱”和“自动拆箱”
		Character plusResult = (char) (character + 1);
		System.out.println("plusResult=" + plusResult);

		Character letter = 'A'; // 声明一个包装字符变量
		// 下面是Character常用的字符处理方法
		boolean isDigit = Character.isDigit(letter); // isDigit方法判断字符是否为数字
		System.out.println("isDigit=" + isDigit);
		boolean isLetter = Character.isLetter(letter); // isLetter方法判断字符是否为字母
		System.out.println("isLetter=" + isLetter);
		boolean isLowerCase = Character.isLowerCase(letter); // isLowerCase方法判断字符是否为小写
		System.out.println("isLowerCase=" + isLowerCase);
		boolean isUpperCase = Character.isUpperCase(letter); // isUpperCase方法判断字符是否为大写
		System.out.println("isUpperCase=" + isUpperCase);
		Character line = '\n'; // 声明一个包装字符变量
		boolean isSpaceChar = Character.isSpaceChar(line); // isSpaceChar方法判断字符是否为空格
		System.out.println("isSpaceChar=" + isSpaceChar);
		// isWhitespace方法判断字符是否为空白(非数字非字母非标点，包括空格、制表、回车、换行等)
		boolean isWhitespace = Character.isWhitespace(line);
		System.out.println("isWhitespace=" + isWhitespace);
		char lowerCase = Character.toLowerCase(letter); // toLowerCase方法把字符转换为大写
		System.out.println("lowerCase=" + lowerCase);
		char upperCase = Character.toUpperCase(letter); // toUpperCase方法把字符转换为小写
		System.out.println("upperCase=" + upperCase);
	}
}
