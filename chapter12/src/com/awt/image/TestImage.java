package com.awt.image;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//演示如何展示图片
public class TestImage {

	public static void main(String[] args) {
		final Frame frame = new Frame("测试图片"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(400, 300); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角

		Panel panel = new Panel(); // 创建一个面板

		ImageView imageView = new ImageView(); // 创建一个自定义的图像视图
		imageView.setSize(320, 240); // 设置图像视图的宽高
		imageView.setImagePath("E:/apple.png");  // 在图像视图上显示指定路径的图片
		//imageView.setImageStream(TestImage.class.getResourceAsStream("apple.png")); // 在图像视图上显示当前代码位置的图片
		panel.add(imageView); // 在面板上添加图像视图

		frame.add(panel); // 在窗口上添加面板

		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
