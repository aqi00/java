package com.donghan.test;

public class Number {

	public static void main(String[] args) {
		int x = 8; // 8对应的二进制数为00001000
		int y = x << 2; // 00001000左移两位后变成00100000，左移结果转成十进制则为32
		int z = x >> 2; // 00001000右移两位后变成00000010，右移结果转成十进制则为2
		System.out.println("原始数字x="+x);
		System.out.println("x左移两位后="+y);
		System.out.println("x右移两位后="+z);
	}
}
