package com.control;

//演示“鸡兔同笼”问题的解题过程
public class Jitutonglong {

	public static void main(String[] arg) {
		// 今有雉兔同笼，上有三十五头，下有九十四足，问雉兔各几何？
		int chick, rabbit; // chick表示鸡的数量，rabbit表示兔子的数量
		int sum = 35; // 鸡和兔子加起来一共35只
		for (chick=0, rabbit=0; chick <= sum; chick++) { // 利用穷举法逐个尝试可能的鸡兔组合
			rabbit = sum - chick; // 计算兔子的数量
			if (chick * 2 + rabbit * 4 == 94) { // 满足鸡兔同笼的问题条件，则结束循环
				break;
			}
		}
		System.out.println("鸡的数量为" + chick + "，兔子的数量为" + rabbit);
	}
}
