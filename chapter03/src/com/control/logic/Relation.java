package com.control.logic;

//演示关系运算符的用法
public class Relation {

	public static void main(String[] args) {
		int seven = 7;
		int eight = 8;
		// 数学的等号“＝”对应Java的“==”符号
		boolean equal = seven == eight; // 结果为真表示等式成立，为假表示等式不成立
		System.out.println("equal=" + equal);
		// 数学的不等号“≠”对应Java的“!=”符号
		boolean not_equal = seven != eight; // 结果为真表示不等式成立，为假表示不等式不成立
		System.out.println("not_equal=" + not_equal);
		boolean greater = seven > eight; // 数学的大于号“＞”对应Java的“>”符号
		System.out.println("greater=" + greater);
		boolean less = seven < eight; // 数学的小于号“＜”对应Java的“<”符号
		System.out.println("less=" + less);
		boolean greater_and_equal = seven >= eight; // 数学的大等号“≥”对应Java的“>=”符号
		System.out.println("greater_and_equal=" + greater_and_equal);
		boolean less_and_equal = seven <= eight; // 数学的小等号“≤”对应Java的“<=”符号
		System.out.println("less_and_equal=" + less_and_equal);
	}
}
