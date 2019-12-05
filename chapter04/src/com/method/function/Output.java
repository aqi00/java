package com.method.function;

//演示如何返回方法的输出参数
public class Output {

	public static void main(String[] args) {
		// 下面的printNsquareRoot方法打印指定数字的N次方根
		printNsquareRoot(2, 2); // 求数字2的2次方根，即对2开平方
		// 下面的getNsquareRoot方法返回指定数字的N次方根
		double number1 = 3;
		int n1 = 2;
		double nsquareRoot = getNsquareRoot(number1, n1);
		System.out.println(number1+"的"+n1+"次方根="+nsquareRoot);
		// 下面的getNsquareRootArray方法返回指定数字的N次方根数组
		double number2 = 3;
		int n2 = 2;
		double[] rootArray = getNsquareRootArray(number2, n2);
		for (double root : rootArray) {
			System.out.println(number2+"的"+n2+"次方根="+root);
		}
	}
	
	// 不返回任何数据，也就是不存在输出参数，则返回值类型填void。
	// printNsquareRoot方法用于打印指定数字的N次方根
	private static void printNsquareRoot(double number, int n) {
		if (n <= 0) {
			System.out.println("n必须为自然数");
			return; // 不带任何参数直接返回，return语句表示该方法的剩余代码都不予执行
		} else if (n%2==0 && number<0) {
			System.out.println("不能对负数开偶次方根");
			return; // 不带任何参数直接返回，return语句表示该方法的剩余代码都不予执行
		}
		// 下面利用牛顿迭代法求数字的N次方根
		double nsquareRoot = number;
		for (int i=0; i<n*2; i++) { // 只需迭代2n次，即可求得较为精确的方根
			double slope = n * Math.pow(nsquareRoot, n-1); // 求导数，即切线的斜率
			nsquareRoot = nsquareRoot - (Math.pow(nsquareRoot, n)-number)/slope;
		}
		System.out.println(number+"的"+n+"次方根="+nsquareRoot);
		//return; // 如果方法的返回值类型为void，则方法末尾的return语句可加可不加
	}
	
	// 只返回一个数值，则返回值类型填该数值的变量类型。
	// getNsquareRoot方法用于计算并返回指定数字的N次方根
	private static double getNsquareRoot(double number, int n) {
		if (n <= 0) {
			System.out.println("n必须为自然数");
			return 0; // 输入参数非法，则默认返回0
		} else if (n%2==0 && number<0) {
			System.out.println("不能对负数开偶次方根");
			return 0; // 输入参数非法，则默认返回0
		}
		// 下面利用牛顿迭代法求数字的N次方根
		double nsquareRoot = number;
		for (int i=0; i<n*2; i++) { // 只需迭代2n次，即可求得较为精确的方根
			double slope = n * Math.pow(nsquareRoot, n-1); // 求导数，即切线的斜率
			nsquareRoot = nsquareRoot - (Math.pow(nsquareRoot, n)-number)/slope;
		}
		return nsquareRoot; // return后面跟着要返回的变量名称，该变量的类型与返回值类型保持一致
	}

	// 需要返回多个数值（包括0个、1个、2个以及更多），则返回值类型可以填这些数值的数组类型。
	// getNsquareRootArray方法用于计算并返回指定数字的N次方根数组（比如2和-2都是4的平方根）
	private static double[] getNsquareRootArray(double number, int n) {
		if (n <= 0) {
			System.out.println("n必须为自然数");
			return new double[]{}; // 输入参数非法，则默认返回一个空的双精度数组
		} else if (n%2==0 && number<0) {
			System.out.println("不能对负数开偶次方根");
			return new double[]{}; // 输入参数非法，则默认返回一个空的双精度数组
		}
		// 下面利用牛顿迭代法求数字的N次方根
		double nsquareRoot = number;
		for (int i=0; i<n*2; i++) { // 只需迭代2n次，即可求得较为精确的方根
			double slope = n * Math.pow(nsquareRoot, n-1); // 求导数，即切线的斜率
			nsquareRoot = nsquareRoot - (Math.pow(nsquareRoot, n)-number)/slope;
		}
		double[] rootArray; // 声明一个方根数组
		if (n%2 == 0) { // 求偶次方根，则方根有正值和负值两个数值
			rootArray = new double[]{nsquareRoot, -nsquareRoot};
		} else { // 求奇次方根，则方根只会有一个数值
			rootArray = new double[]{nsquareRoot};
		}
		return rootArray; // return后面跟着rootArray，其变量类型与返回值类型一样都是双精度数组
	}
	
}
