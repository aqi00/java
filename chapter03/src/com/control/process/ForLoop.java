package com.control.process;

import java.util.Scanner;

// 演示for语句的用法
public class ForLoop {

	public static void main(String[] args) {
		System.out.println("长夜漫漫，无心睡眠，请给他设定一个睡醒的年限");
		Scanner scan = new Scanner(System.in); // 从控制台接收输入文本
		int limit = scan.nextInt(); // nextInt方法表示接收一个整数，以回车键结尾
		int year;
		// for (式子A; 式子B; 式子C;)的三个式子A、B、C说明如下：
		// 式子A在首次进入循环时执行；
		// 式子B是循环的判断条件，B成立时继续循环，不成立时退出循环；
		// 式子C在开始下一次循环之前执行。注意，每次循环结束之后，先执行式子C，再判断式子B
		for (year = 0; year < limit; year++) {
			System.out.println("已经过去的年份：" + year);
		}
		// 上面的for循环等价于下面的while循环
//		year = 0;
//		if (year < limit) {
//			while (true) { // 开始循环处理
//				System.out.println("已经过去的年份：" + year);
//				year++;
//				if (year < limit) { // 年份未达到条件，继续循环
//					continue;
//				} else { // 年份已达到条件，退出循环
//					break;
//				}
//			}
//		}
		System.out.println("他足足睡了这么多年：" + year);
	}
}
