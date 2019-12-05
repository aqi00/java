package com.swing.login;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//登录进去的程序主界面
public class MainWindow {

	// 显示程序主界面
	public static void show() {
		JFrame frame = new JFrame("主界面"); // 创建一个窗口对象
		frame.setSize(400, 200); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

		JPanel panel = new JPanel(); // 创建一个面板

		JLabel label = new JLabel("恭喜您登录成功！"); // 创建一个标签
		label.setPreferredSize(new Dimension(350, 120)); // 设置标签的推荐宽高
		label.setFont(new Font("楷体", Font.PLAIN, 40)); // 设置标签文字的字体与大小
		panel.add(label); // 在面板上添加标签
		
		frame.add(panel); // 在窗口上添加面板

		frame.setVisible(true); // 必须设置为true，否则看不见
	}
}
