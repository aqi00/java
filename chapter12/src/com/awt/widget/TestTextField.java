package com.awt.widget;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//演示单行输入框的用法
public class TestTextField {
	private static TextField field = new TextField(); // 创建一个单行输入框

	public static void main(String[] args) {
		final Frame frame = new Frame("测试单行输入框"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(400, 120); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null); // 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setLayout(new BorderLayout()); // 设置窗口的布局为边界布局

		Panel panelTop = new Panel(); // 创建顶部面板
		Panel panelCenter = new Panel(); // 创建中央面板

		Label label = new Label("请输入11位手机号码"); // 创建一个文本标签
		label.setFont(new Font("", Font.PLAIN, 16)); // 设置标签的字体大小
		label.setPreferredSize(new Dimension(150, 20)); // 设置文本标签的推荐宽高
		label.setAlignment(Label.RIGHT); // 设置文本标签的内部文字靠右对齐
		field.setFont(new Font("", Font.PLAIN, 16)); // 设置输入框的字体大小
		// TextField没有setAlignment方法
		field.setColumns(11); // 设置输入框的长度为11个字符
		field.setEditable(true); // 设置输入框允许编辑

		Button btn1 = new Button("输入明文"); // 创建一个按钮
		btn1.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		btn1.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				label.setText("请输入11位手机号码"); // 设置文本标签的文字内容
				panelCenter.remove(field); // 从中央面板上移除输入框
				// TextField未提供回显字符的取消功能
				field = new TextField(); // 创建一个单行输入框
				field.setFont(new Font("", Font.PLAIN, 16)); // 设置输入框的字体大小
				field.setColumns(11); // 设置输入框的长度为11个字符
				panelCenter.add(field); // 在中央面板上添加输入框
				frame.setVisible(true); // 把最新的界面显示到窗口上
			}
		});
		panelTop.add(btn1); // 在顶部面板上添加按钮

		Button btn2 = new Button("输入密码"); // 创建一个按钮
		btn2.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		btn2.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				label.setText("请输入六位密码"); // 设置文本标签的文字内容
				panelCenter.remove(field); // 从中央面板上移除输入框
				field = new TextField(); // 创建一个单行输入框
				field.setFont(new Font("", Font.PLAIN, 16)); // 设置输入框的字体大小
				field.setColumns(6); // 设置输入框的长度为6个字符
				field.setEchoChar('*'); // 设置输入框的回显字符为星号
				panelCenter.add(field); // 在中央面板上添加输入框
				frame.setVisible(true); // 把最新的界面显示到窗口上
			}
		});
		panelTop.add(btn2); // 在顶部面板上添加按钮

		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）

		panelCenter.add(label); // 在中央面板上添加文本标签
		panelCenter.add(field); // 在中央面板上添加单行输入框
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
