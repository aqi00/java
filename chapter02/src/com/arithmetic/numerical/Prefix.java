package com.arithmetic.numerical;

//演示数字的前缀，主要是标明该数字的归属进制
public class Prefix {

	public static void main(String[] args) {
		int binary = 0b11; // 二进制数，0b也可以写成0B
		System.out.println("binary=" + binary);
		int octonary = 011; // 以0开头，后面非bB非xX的就是八进制数
		System.out.println("octonary=" + octonary);
		int hexadecimal = 0x11; // 十六进制数，0x也可以写成0X
		System.out.println("hexadecimal=" + hexadecimal);
		int hexLetter = 0xff; // 十六进制数不区分大小写，如ff也可以写成FF
		System.out.println("hexadecimal=" + hexLetter);
		int decimal = 11; // 没有任何前缀，则默认为十进制数
		System.out.println("decimal=" + decimal);
	}
}
