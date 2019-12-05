package com.swing.window;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

//演示面板的用法
public class TestPanel {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试面板的窗口"); // 创建一个窗口对象
		frame.setSize(400, 200); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序
		
		JPanel panel = new JPanel(); // 创建一个面板
		panel.setBackground(Color.GREEN); // 设置面板的背景
		frame.add(panel); // 在窗口上添加面板
		
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
