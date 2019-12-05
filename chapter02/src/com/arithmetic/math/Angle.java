package com.arithmetic.math;

//演示数学函数库中的三角函数用法
public class Angle {

	public static void main(String[] args) {
		double angle = 60; // 三角函数的角度
		// 弧度=该角度对应的弧长/半径。数学函数库Math专门提供了常量PI表示圆周率π的粗略值
		double radian = angle * Math.PI / 180;
		double sin = Math.sin(radian); // 求某弧度的正弦。求反正弦要调用asin方法
		System.out.println("sin=" + sin);
		double cos = Math.cos(radian); // 求某弧度的余弦。求反余弦要调用acos方法
		System.out.println("cos=" + cos);
		double tan = Math.tan(radian); // 求某弧度的正切。求反正切要调用atan方法
		System.out.println("tan=" + tan);
		// 求某弧度的余切。Math库未提供求余切值的方法，其实余切值就是正切值的倒数
		double ctan = 1.0 / tan;
		System.out.println("ctan=" + ctan);
	}

}
