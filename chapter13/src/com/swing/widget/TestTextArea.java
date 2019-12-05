package com.swing.widget;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//演示多行输入框的用法
public class TestTextArea {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试多行输入框的窗口"); // 创建一个窗口对象
		frame.setSize(400, 130); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

		JPanel panel = new JPanel(); // 创建一个面板

		JTextArea area = new JTextArea(); // 创建一个多行输入框
		area.setPreferredSize(new Dimension(180, 30)); // 设置输入框的推荐宽高
		area.setFont(new Font("中号", Font.PLAIN, 16)); // 设置输入框的文本字体及其大小
		area.setEditable(true); // 设置输入框允许编辑
		area.setColumns(14); // 设置输入框的长度为14个字符
		area.setRows(3); // 设置输入框的高度为3行字符
		area.setLineWrap(true); // 设置每行是否允许折叠。为true的话，输入字符超过每行宽度就会自动换行

		panel.add(area); // 在面板上添加多行输入框
		frame.add(panel); // 在窗口上添加面板

		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
