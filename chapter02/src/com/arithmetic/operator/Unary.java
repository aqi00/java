package com.arithmetic.operator;

//演示一元运算符的用法
public class Unary {

	public static void main(String[] args) {
		int x = 3;
		System.out.println("初始 x=" + x);
		x++; // 等同于x=x+1或者x+=1
		System.out.println("自增1 x=" + x);
		x--; // 等同于x=x-1或者x-=1
		System.out.println("自减1 x=" + x);
		// 没有“**”这个运算符，求平方还是按照常规写法
		x *= x; // 也可以写成x = x*x
		System.out.println("求平方 x=" + x);
		// “//”已经被用作注释标记了，求倒数也得按照常规写法，而且整数的倒数只能是小数
		double y = 1.0 / x; // 注意这里的1.0/x，由于x是整型数，因此1/x无法求得小数
		System.out.println("求倒数 y=" + y);
		x = -x; // 等同于x=0-x
		System.out.println("负数 x=" + x);
		x = +x; // 等同于x=0+x
		System.out.println("正数 x=" + x);
		int y1 = 7;
		int z1 = y1++; // 后加加操作的优先级较低
		/*
		 * int z1 = y1++; 上面语句在执行时会分解成两个步骤：先执行对z1的赋值操作，再执行对y1的自增操作。 int z1 = y1;
		 * y1 = y1+1;
		 */
		System.out.println("z1=" + z1);
		int y2 = 7;
		int z2 = ++y2; // 前加加操作的优先级较高
		/*
		 * int z2 = ++y2; 上面语句在执行时会分解成两个步骤：先执行对y1的自增操作，再执行对z1的赋值操作。 y2 = y2+1;
		 * int z2 = y2;
		 */
		System.out.println("z2=" + z2);
		// 脑筋急转弯，猜猜下面的z3为何值？
		int z3 = ++z1 + z2++;
		System.out.println("z3=" + z3);
	}
}
