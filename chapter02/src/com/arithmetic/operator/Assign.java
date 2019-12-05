package com.arithmetic.operator;

//演示赋值运算符的用法
public class Assign {

	public static void main(String[] args) {
		// 日常生活中的加法例子是：1+1=2，运算结果在右边。但Java编程中是把运算结果放在左边的
		int x = 1 + 1;
		System.out.println("初始值 x=" + x);
		// 注意这里的等号是赋值操作，并非代数方程式里面的等号，否则x=x+7将会求得0=7的荒诞结果
		// x = x+7;
		// 对变量做加法运算后，假如相加之和仍然保存在原变量，那么可按如下格式使用运算符“+=”
		x += 7; // 该行代码等同于 x = x+7;
		System.out.println("相加之和 x=" + x);
		// 运算符“-=”的作用类似“+=”，即把相减之差保存到原变量中
		x -= 7; // 该行代码等同于 x = x-7;
		System.out.println("相减之差 x=" + x);
		// 若要将相乘之积保存到原变量中，则可使用运算符“*=”
		x *= 7; // 该行代码等同于 x = x*7;
		System.out.println("相乘之积 x=" + x);
		// 若要将相除之商保存到原变量中，则可使用运算符“/=”
		x /= 7; // 该行代码等同于 x = x/7;
		System.out.println("相除之商 x=" + x);
		// 若要将相除之余保存到原变量中，则可使用运算符“%=”
		x %= 7; // 该行代码等同于 x = x%7;
		System.out.println("相除之余 x=" + x);
		// 若要将按位左移结果保存到原变量中，则可使用运算符“<<=”
		x <<= 2; // 该行代码等同于 x = x << 2;
		System.out.println("x按位左移两位=" + x);
		// 若要将按位右移结果保存到原变量中，则可使用运算符“>>=”
		x >>= 2; // 该行代码等同于 x = x >> 2;
		System.out.println("x按位右移两位=" + x);
	}
}
