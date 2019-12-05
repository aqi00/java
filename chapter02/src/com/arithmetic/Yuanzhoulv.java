package com.arithmetic;

//演示利用割圆术计算圆周率π的过程
public class Yuanzhoulv {
	
	public static void main(String[] arg) {
		// 假设圆的半径r=1，则直径d=2，内接正六边形的边长=1
		int r = 1; // 半径
		long edgeNumber = 6L; // 从正六边形开始内接圆
		double edgeLength = r; // 内接正n边形的边长（初始值为正六边形的边长）
		double π = edgeLength * edgeNumber / (2*r); // 圆周率
		System.out.println("正n边形的边数="+edgeNumber+"，圆周率="+π);
		double gou; // 直角三角形中长度最短的边，称作“勾”
		double gu; // 直角三角形中长度居中的边，称作“股”

		// 以下计算内接圆的正十二边形
		edgeNumber = edgeNumber*2; // 正n边形的边数乘二
		gou = edgeLength/2.0; // 计算勾
		gu = r - Math.sqrt(Math.pow(r,2) - Math.pow(gou,2)); // 计算股
		// 通过勾股定理求斜边长（即勾三股四弦五），斜边长也是新正n边形的边长
		edgeLength = Math.sqrt(Math.pow(gou,2) + Math.pow(gu,2));
		// 正n边形的周长除以直径，即可得到近似的圆周率数值
		π = edgeLength * edgeNumber / (2*r);
		System.out.println("正n边形的边数="+edgeNumber+"，圆周率="+π);

		// 以下计算内接圆的正二十四边形
		edgeNumber = edgeNumber*2; // 正n边形的边数乘二
		gou = edgeLength/2.0; // 计算勾
		gu = r - Math.sqrt(Math.pow(r,2) - Math.pow(gou,2)); // 计算股
		// 通过勾股定理求斜边长（即勾三股四弦五），斜边长也是新正n边形的边长
		edgeLength = Math.sqrt(Math.pow(gou,2) + Math.pow(gu,2));
		// 正n边形的周长除以直径，即可得到近似的圆周率数值
		π = edgeLength * edgeNumber / (2*r);
		System.out.println("正n边形的边数="+edgeNumber+"，圆周率="+π);

		// 以下计算内接圆的正四十八边形
		edgeNumber = edgeNumber*2; // 正n边形的边数乘二
		gou = edgeLength/2.0; // 计算勾
		gu = r - Math.sqrt(Math.pow(r,2) - Math.pow(gou,2)); // 计算股
		// 通过勾股定理求斜边长（即勾三股四弦五），斜边长也是新正n边形的边长
		edgeLength = Math.sqrt(Math.pow(gou,2) + Math.pow(gu,2));
		// 正n边形的周长除以直径，即可得到近似的圆周率数值
		π = edgeLength * edgeNumber / (2*r);
		System.out.println("正n边形的边数="+edgeNumber+"，圆周率="+π);

		// 以下计算内接圆的正九十六边形
		edgeNumber = edgeNumber*2; // 正n边形的边数乘二
		gou = edgeLength/2.0; // 计算勾
		gu = r - Math.sqrt(Math.pow(r,2) - Math.pow(gou,2)); // 计算股
		// 通过勾股定理求斜边长（即勾三股四弦五），斜边长也是新正n边形的边长
		edgeLength = Math.sqrt(Math.pow(gou,2) + Math.pow(gu,2));
		// 正n边形的周长除以直径，即可得到近似的圆周率数值
		π = edgeLength * edgeNumber / (2*r);
		System.out.println("正n边形的边数="+edgeNumber+"，圆周率="+π);

		// 以下计算内接圆的正一百九十二边形
		edgeNumber = edgeNumber*2; // 正n边形的边数乘二
		gou = edgeLength/2.0; // 计算勾
		gu = r - Math.sqrt(Math.pow(r,2) - Math.pow(gou,2)); // 计算股
		// 通过勾股定理求斜边长（即勾三股四弦五），斜边长也是新正n边形的边长
		edgeLength = Math.sqrt(Math.pow(gou,2) + Math.pow(gu,2));
		// 正n边形的周长除以直径，即可得到近似的圆周率数值
		π = edgeLength * edgeNumber / (2*r);
		System.out.println("正n边形的边数="+edgeNumber+"，圆周率="+π);

		// 以下计算内接圆的正三百八十四边形
		edgeNumber = edgeNumber*2; // 正n边形的边数乘二
		gou = edgeLength/2.0; // 计算勾
		gu = r - Math.sqrt(Math.pow(r,2) - Math.pow(gou,2)); // 计算股
		// 通过勾股定理求斜边长（即勾三股四弦五），斜边长也是新正n边形的边长
		edgeLength = Math.sqrt(Math.pow(gou,2) + Math.pow(gu,2));
		// 正n边形的周长除以直径，即可得到近似的圆周率数值
		π = edgeLength * edgeNumber / (2*r);
		System.out.println("正n边形的边数="+edgeNumber+"，圆周率="+π);

		// 以下计算内接圆的正七百六十八边形
		edgeNumber = edgeNumber*2; // 正n边形的边数乘二
		gou = edgeLength/2.0; // 计算勾
		gu = r - Math.sqrt(Math.pow(r,2) - Math.pow(gou,2)); // 计算股
		// 通过勾股定理求斜边长（即勾三股四弦五），斜边长也是新正n边形的边长
		edgeLength = Math.sqrt(Math.pow(gou,2) + Math.pow(gu,2));
		// 正n边形的周长除以直径，即可得到近似的圆周率数值
		π = edgeLength * edgeNumber / (2*r);
		System.out.println("正n边形的边数="+edgeNumber+"，圆周率="+π);

		// 以下计算内接圆的正一千五百三十六边形
		edgeNumber = edgeNumber*2; // 正n边形的边数乘二
		gou = edgeLength/2.0; // 计算勾
		gu = r - Math.sqrt(Math.pow(r,2) - Math.pow(gou,2)); // 计算股
		// 通过勾股定理求斜边长（即勾三股四弦五），斜边长也是新正n边形的边长
		edgeLength = Math.sqrt(Math.pow(gou,2) + Math.pow(gu,2));
		// 正n边形的周长除以直径，即可得到近似的圆周率数值
		π = edgeLength * edgeNumber / (2*r);
		System.out.println("正n边形的边数="+edgeNumber+"，圆周率="+π);

		// 以下计算内接圆的正三千零七十二边形
		edgeNumber = edgeNumber*2; // 正n边形的边数乘二
		gou = edgeLength/2.0; // 计算勾
		gu = r - Math.sqrt(Math.pow(r,2) - Math.pow(gou,2)); // 计算股
		// 通过勾股定理求斜边长（即勾三股四弦五），斜边长也是新正n边形的边长
		edgeLength = Math.sqrt(Math.pow(gou,2) + Math.pow(gu,2));
		// 正n边形的周长除以直径，即可得到近似的圆周率数值
		π = edgeLength * edgeNumber / (2*r);
		System.out.println("正n边形的边数="+edgeNumber+"，圆周率="+π);

	}

}
