package com.method.pack;

//以Integer为例，演示包装变量之间的运算
public class PackOperation {

	public static void main(String[] args) {
		// 数值的包装变量允许直接使用四则运算符“+”“-”“*”“/”“%”
		Integer five = Integer.valueOf(5);
		Integer three = Integer.valueOf(3);
		Integer plus = five + three;
		System.out.println("plus="+plus);
		Integer minus = five - three;
		System.out.println("minus="+minus);
		Integer multiply = five * three;
		System.out.println("multiply="+multiply);
		Integer divide = five / three;
		System.out.println("divide="+divide);
		Integer remainer = five % three;
		System.out.println("remainer="+remainer);
		
		Integer oneInteger = 1;
		boolean equalResult = oneInteger.equals(2); // 包装变量的equals方法相当于关系运算符“==”
		System.out.println("equalResult="+equalResult);
		Integer ten1=10, ten2=10; // 准备演示两个包装变量之间的==运算
		boolean equalTen = (ten1==ten2); // 当变量值小于128时，==运算侥幸得手
		System.out.println("equalTen="+equalTen);
		Integer thousand1=1000, thousand2=1000; // 准备演示两个包装变量之间的==运算
		boolean equalThousand = (thousand1==thousand2); // 当变量值大于128时，==运算不幸失手
		System.out.println("equalThousand="+equalThousand);

		int a = 7, b = 8;
		int sum = Integer.sum(a, b); // 数值包装类型的sum方法相当于算术运算符“+”
		System.out.println("sum="+sum);
		int max = Integer.max(a, b); // 数值包装类型的max方法用来求两个数字的较大值
		System.out.println("max="+max);
		int min = Integer.min(a, b); // 数值包装类型的min方法用来求两个数字的较小值
		System.out.println("min="+min);
		// 数值包装类型的compare方法用来比较两个数字的大小，
		// 二者相等则返回0，前者较小则返回-1，后者较小则返回1
		int compareResult = Integer.compare(a, b);
		System.out.println("compareResult="+compareResult);
	}

}
