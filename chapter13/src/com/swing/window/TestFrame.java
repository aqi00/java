package com.swing.window;

import javax.swing.JFrame;

//演示框架的用法
public class TestFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试框架的窗口"); // 创建一个窗口对象
		frame.setSize(400, 200); // 必须设置宽高，否则没有窗体
		//frame.setTitle("222"); // 设置窗口标题，否则窗口无标题
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		//frame.setResizable(false); // 禁止调整窗口大小。默认允许调整窗口尺寸
		//frame.setBackground(Color.GREEN); // JFrame不能直接设置背景色
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序
		frame.setVisible(true); // 必须设置为true，否则看不见
	}
	
}
