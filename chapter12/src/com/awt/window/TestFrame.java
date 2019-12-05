package com.awt.window;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//演示如何显示窗口
public class TestFrame {

	public static void main(String[] args) {
		final Frame frame = new Frame("测试窗口"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(400, 200); // 必须设置宽高，否则没有窗体
		//frame.setTitle("222"); // 设置窗口标题，或者在Frame的构造方法中直接填写标题文字也行
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		//frame.setResizable(false); // 禁止调整窗口大小。默认允许调整窗口尺寸
		frame.setBackground(Color.GREEN); // 设置窗口背景色。默认白色
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
