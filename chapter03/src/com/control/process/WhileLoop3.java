package com.control.process;

import java.util.Scanner;

// 演示while语句内部break-continue的用法
public class WhileLoop3 {

	public static void main(String[] args) {
		System.out.println("长夜漫漫，无心睡眠，请给他设定一个睡醒的年限");
		Scanner scan = new Scanner(System.in); // 从控制台接收输入文本
		int limit = scan.nextInt(); // nextInt方法表示接收一个整数，以回车键结尾
		int year = 0;
		while (true) { // 当条件满足时，在循环内部持续处理
			System.out.println("已经过去的年份：" + year);
			if (year < limit) { // 这里判断能否跳出循环
				year++;
				continue; // 继续下一次循环
			} else {
				break; // 跳出循环。即跳到while循环的右花括号之后
			}
		}
		System.out.println("他足足睡了这么多年：" + year);
	}
}
