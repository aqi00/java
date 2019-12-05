package com.control.process;

import java.util.Scanner;

// 演示switch-case-default的用法
public class Multipath2 {

	public static void main(String[] args) {
		System.out.println("凉风有信，秋月无边。打二字");
		System.out.println("获取“凉风有信”的谜底请按1，获取“秋月无边”的谜底请按2");
		Scanner scan = new Scanner(System.in); // 从控制台接收输入文本
		int seq = scan.nextInt(); // nextInt方法表示接收一个整数，以回车键结尾
		// switch允许判断某个变量的多个取值，并分别进行单独处理
		switch (seq) {
		case 1: // seq值为1时进入该分支
			System.out.println("凉风有信的谜底是“讽”");
			break; // 跳出多路分支。即跳到switch分支的右花括号之后
		case 2: // seq值为2时进入该分支
			System.out.println("秋月无边的谜底是“二”");
			break; // 跳出多路分支。即跳到switch分支的右花括号之后
		default: // seq值为其它时进入该分支
			System.out.println("您的按键有误");
			break; // 跳出多路分支。即跳到switch分支的右花括号之后
		}
		System.out.println("猜谜结束");
	}
}
