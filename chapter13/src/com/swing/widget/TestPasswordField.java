package com.swing.widget;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

//演示密码输入框的用法
public class TestPasswordField {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试密码输入框的窗口"); // 创建一个窗口对象
		frame.setSize(400, 90); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

		JPanel panel = new JPanel(); // 创建一个面板

		JPasswordField passwordField = new JPasswordField(); // 创建一个密码框
		passwordField.setFont(new Font("中号", Font.PLAIN, 16)); // 设置密码框的文本字体及其大小
		passwordField.setEditable(true); // 设置密码框允许编辑
		passwordField.setColumns(6); // 设置密码框的长度为11个字符
		//passwordField.setEchoChar('*'); // 设置密码框的回显字符。默认的回显字符为圆点

		panel.add(passwordField); // 在面板上添加密码框
		frame.add(panel); // 在窗口上添加面板

		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
