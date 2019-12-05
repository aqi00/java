package com.control.process;

import java.util.Scanner;

// 演示“else if”的用法
public class Multipath {

	public static void main(String[] args) {
		System.out.println("凉风有信，秋月无边。打二字");
		System.out.println("获取“凉风有信”的谜底请按1，获取“秋月无边”的谜底请按2");
		Scanner scan = new Scanner(System.in); // 从控制台接收输入文本
		int seq = scan.nextInt(); // nextInt方法表示接收一个整数，以回车键结尾
		if (seq == 1) { // 条件式子1为真时，进入第一个if分支处理
			System.out.println("凉风有信的谜底是“讽”");
		} else if (seq == 2) { // 否则的话，继续判断条件式子2为真时，进入第二个if分支处理
			System.out.println("秋月无边的谜底是“二”");
		} else { // 否则（前面的判断条件都不满足），进入else分支处理
			System.out.println("您的按键有误");
		}
	}
}
