package com.awt.window;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//演示多个控件在窗口上的默认布局
public class TestFlow {

	public static void main(String[] args) {
		final Frame frame = new Frame("测试默认布局"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(400, 120); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null); // 将窗口居中。若无该方法，窗口将位于屏幕左上角

		Panel panel = new Panel(); // 创建一个面板
		panel.add(getButton("第一个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第二个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第三个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第四个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第五个按钮")); // 在面板上添加一个按钮
		frame.add(panel); // 在窗口上添加面板
		
		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}
	
	private static Button getButton(String text) {
		Button button = new Button(text);
		button.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		return button;
	}

}
