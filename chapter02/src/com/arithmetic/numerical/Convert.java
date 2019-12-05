package com.arithmetic.numerical;

//演示基本变量类型之间的转换
public class Convert {

	public static void main(String[] args) {
		int changjiang = 6397; // 长江的长度为6397千米
		System.out.println("changjiang=" + changjiang);
		int longRiver = changjiang; // 把一个整型变量赋值给另一个整型变量
		System.out.println("longRiver=" + longRiver);
		
//		long changjiang = 6397;
//		// 若把长整型变量直接赋值给整型变量，编译器会提示错误
//		//int longRiver = changjiang; // 把长整型变量赋值给整型变量，注意编译器会报错
//		// 不同类型的变量相互赋值，需要在原变量前面添加“(新类型)”表示强制转换类型
//		int longRiver = (int) changjiang; // 把长整型数强制转成整型数
		
		long worldPopulation = 7444443881L; // 截至2018年元旦，世界人口大约有74亿
		System.out.println("worldPopulation=" + worldPopulation);
		// 把长整型数赋值给整型数会丢失前四个字节
		int shijierenkou = (int) worldPopulation; // 把长整型数强制转成整型数
		System.out.println("shijierenkou=" + shijierenkou);
		
		// 3.1415926是中国古代数学家祖冲之求得的圆周率数值，又称祖率
		double zulv = 3.1415926;
		System.out.println("zulv=" + zulv);
		// 把双精度数赋值给浮点数会丢失数值精度
		float pai = (float) zulv; // 把双精度数强制转成浮点数
		System.out.println("pai=" + pai);
		double jiage = 9.9; // 某商品定价为9.9元
		System.out.println("jiage=" + jiage);
		// 把小数赋值给整型变量，会直接去掉小数点后面部分，不会四舍五入
		int price = (int) jiage; // 把双精度数强制转成整型数
		System.out.println("price=" + price);
	}
}
