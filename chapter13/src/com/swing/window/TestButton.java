package com.swing.window;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//演示按钮的用法
public class TestButton {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试按钮的窗口"); // 创建一个窗口对象
		frame.setSize(400, 100); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序
		
		JPanel panel = new JPanel(); // 创建一个面板

		JButton button = new JButton("点我"); // JButton无需另外设置文件的字符编码
		// button.setText("点我"); // 设置按钮的文本。JButton的setLabel方法已经废弃
		// 设置空间大小要用setPreferredSize，因为setSize不管用
		button.setPreferredSize(new Dimension(200, 30)); // 设置按钮的推荐宽高
		button.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				button.setText(getNowTime() + " 单击了按钮"); // 设置按钮的文本
			}
		});
		button.setFont(new Font("中号", Font.PLAIN, 16)); // 设置按钮文字的字体与大小
		panel.add(button); // 在面板上添加按钮

		frame.add(panel); // 在窗口上添加面板
		
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

	// 获取当前的时间字符串
	public static String getNowTime() {
		// 创建一个日期格式化的工具
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		// 将当前时间按照指定格式输出格式化后的时间字符串
		return sdf.format(new Date());
	}

}
