package com.control.process;

import java.util.Scanner;

// 演示else语句的用法
public class Condition3 {

	public static void main(String[] args) {
		System.out.println("凉风有信，秋月无边。打二字");
		System.out.println("获取“凉风有信”的谜底请按1，获取“秋月无边”的谜底请按2");
		Scanner scan = new Scanner(System.in); // 从控制台接收输入文本
		int seq = scan.nextInt(); // nextInt方法表示接收一个整数，以回车键结尾
		if (seq == 1) { // 条件式子为真时，进入if分支处理
			System.out.println("凉风有信的谜底是“讽”");
		} else { // 否则（条件式子为假），进入else分支处理
			System.out.println("秋月无边的谜底是“二”");
		}
	}
}
