package com.awt.window;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//演示各种布局的效果
public class TestLayout {

	public static void main(String[] args) {
		final Frame frame = new Frame("测试各种布局"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(400, 250); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null); // 将窗口居中。若无该方法，窗口将位于屏幕左上角

		//frame.add(getFlowLayout()); // 在窗口上添加流式布局
		frame.add(getGridLayout()); // 在窗口上添加网格布局
		//frame.add(getBorderLayout()); // 在窗口上添加边界布局

		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

	// 获取流式布局
	private static Panel getFlowLayout() {
		Panel panel = new Panel(); // 创建一个面板
		panel.add(getButton("第一个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第二个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第三个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第四个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第五个按钮")); // 在面板上添加一个按钮

		FlowLayout layout = new FlowLayout(); // 创建一个流式布局
		layout.setAlignment(FlowLayout.LEFT); // 设置对齐方式为靠左对齐
		layout.setHgap(20); // 设置水平方向上的空白距离
		layout.setVgap(50); // 设置垂直方向上的空白距离
		panel.setLayout(layout); // 指定面板采用流式布局
		return panel;
	}

	// 获取网格布局
	private static Panel getGridLayout() {
		Panel panel = new Panel(); // 创建一个面板
		panel.add(getButton("第一个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第二个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第三个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第四个按钮")); // 在面板上添加一个按钮
		panel.add(getButton("第五个按钮")); // 在面板上添加一个按钮

		GridLayout layout = new GridLayout(5, 1); // 创建一个网格布局，有五行一列
		layout.setRows(5); // 设置行数为5
		layout.setColumns(1); // 设置列数为1
		panel.setLayout(layout); // 指定面板采用网格布局
		return panel;
	}

	// 获取边界布局
	private static Panel getBorderLayout() {
		Panel panel = new Panel(); // 创建一个面板
		panel.setLayout(new BorderLayout()); // 指定面板采用边界布局
		panel.add(getButton("东边的按钮"), BorderLayout.EAST); // 在面板的东边（右侧）添加按钮
		panel.add(getButton("西边的按钮"), BorderLayout.WEST); // 在面板的西边（左侧）添加按钮
		panel.add(getButton("北边的按钮"), BorderLayout.NORTH); // 在面板的北边（上方）添加按钮
		panel.add(getButton("南边的按钮"), BorderLayout.SOUTH); // 在面板的南边（下方）添加按钮
		panel.add(getButton("中间的按钮"), BorderLayout.CENTER); // 在面板的中间位置添加按钮
		return panel;
	}

	private static Button getButton(String text) {
		Button button = new Button(text);
		button.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		return button;
	}

}
