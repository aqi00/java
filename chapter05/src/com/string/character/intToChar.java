package com.string.character;

//演示字符型与整型变量之间的相互转换
public class intToChar {

	public static void main(String[] arg) {
		charToInt();
		intToChar();
		printCapital();
		// printCapital2();
	}

	// 字符允许直接赋值给整型变量
	private static void charToInt() {
		int a = 'A'; // 把一个字符赋值给整型变量
		System.out.println("int a=" + a);
		int tian = '田'; // 把一个字符赋值给整型变量
		System.out.println("int tian=" + tian);
	}

	// 0-65535之间的整数允许直接赋值给字符变量。字符类型占两个字节
	private static void intToChar() {
		char a = 65; // 把一个数字赋值给字符变量
		System.out.println("char a=" + a);
		char tian = 30000; // 把一个数字赋值给字符变量
		System.out.println("char tian=" + tian);
		// 以汉字为主的东亚象形文字（中日韩）占据了从0x3000到0x9FFF之间的编码
		char begin = 0x3000;
		System.out.println("chinese begin=" + begin);
		char end = 0x9FFF;
		System.out.println("chinese end=" + end);
		char max = 65535; // 字符型可表达的范围是0-65535
		System.out.println("char max=" + max);
	}

	// 字符变量允许跟整数直接加减乘除
	private static void printCapital() {
		char a = 'A';
		for (int i = 0; i < 26; i++) { // 英语的大写字母总共有26个
			// 因为不确定a+i之和是否超出0-65535的范围，所有需要强制转换成字符类型
			char capital = (char) (a + i);
			System.out.println("capital=" + capital);
		}
	}

	private static void printCapital2() {
		int a = 65;
		for (int i = 0; i < 26; i++) {
			char capital = (char) (a + i);
			System.out.println("capital=" + capital);
		}
	}

}
