package com.donghan.huainan.hefei; // 东汉帝国淮南郡合肥县

import java.util.Scanner; // 导入系统自带的Scanner工具

public class Hefei {
	
	public static void main(String[] args) {
		System.out.print("这里是张辽镇守的合肥城，吴国谁来挑战？");
		Scanner scan = new Scanner(System.in); // 从控制台接收输入文本
		/* nextLine方法表示接收一行文字，以回车键结尾 */
		System.out.println("吴国前来挑战的将领是："+scan.nextLine());
	}

}
