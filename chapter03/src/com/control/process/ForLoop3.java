package com.control.process;

import java.util.Scanner;

// 演示for语句在圆括号内全部留空的写法
public class ForLoop3 {

	public static void main(String[] args) {
		System.out.println("长夜漫漫，无心睡眠，请给他设定一个睡醒的年限");
		Scanner scan = new Scanner(System.in); // 从控制台接收输入文本
		int limit = scan.nextInt(); // nextInt方法表示接收一个整数，以回车键结尾
		int year = 0; // 把式子A挪到整个循环的前面
		for (;;) { // for语句后面的三个位置全部留空
			System.out.println("已经过去的年份：" + year);
			if (year >= limit) { // 这里判断能否跳出循环
				System.out.println("他足足睡了这么多年：" + year);
				break; // 跳出循环。即跳到for循环的右花括号之后
			} else {
				year++; // 把式子C挪到continue之前
				continue; // 继续下一次循环。此时先执行year++，再执行循环内部语句
			}
		}
	}
}
