package com.awt.window;

import java.awt.Frame;

//演示一个最简单的窗口
public class TestSimple {

	public static void main(String[] args) {
		Frame frame = new Frame(); // 创建一个窗口对象
		frame.setVisible(true); // 必须设置为true，否则看不见
	}
}
