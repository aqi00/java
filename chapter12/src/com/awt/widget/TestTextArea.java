package com.awt.widget;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//演示多行输入框的用法
public class TestTextArea {

	public static void main(String[] args) {
		final Frame frame = new Frame("测试多行输入框"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(400, 130); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角

		Panel panel = new Panel(); // 创建一个面板

		Label label = new Label("请输入评价内容"); // 创建一个文字标签
		label.setFont(new Font("", Font.PLAIN, 16)); // 设置标签的字体大小
		panel.add(label); // 在面板上添加文字标签

		TextArea area = new TextArea(); // 创建一个多行输入框
		area.setEditable(true); // 设置输入框允许编辑
		area.setColumns(20); // 设置输入框的长度为20个字符
		area.setRows(3); // 设置输入框的高度为3行字符
		area.setFont(new Font("", Font.PLAIN, 16)); // 设置输入框的字体大小
		panel.add(area); // 在面板上添加多行输入框

		frame.add(panel); // 在窗口上添加面板

		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
