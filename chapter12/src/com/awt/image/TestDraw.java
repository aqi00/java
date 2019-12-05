package com.awt.image;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//演示如何在窗口上绘图
public class TestDraw {
	private static DrawView draw = new DrawView(); // 创建一个绘画视图

	public static void main(String[] args) {
		final Frame frame = new Frame("测试绘图"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(460, 300); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setLayout(new BorderLayout()); // 设置窗口的布局为边界布局

		Panel panelTop = new Panel(); // 创建顶部面板
		panelTop.add(getButton("画线段", DrawView.LINE)); // 在顶部面板上添加画线段按钮
		panelTop.add(getButton("画矩形", DrawView.RECT)); // 在顶部面板上添加画矩形按钮
		panelTop.add(getButton("画圆角矩形", DrawView.ROUND_RECT)); // 在顶部面板上添加画圆角矩形按钮
		panelTop.add(getButton("画椭圆", DrawView.OVAL)); // 在顶部面板上添加画椭圆按钮
		panelTop.add(getButton("画圆弧", DrawView.ARC)); // 在顶部面板上添加画圆弧按钮
		panelTop.add(getButton("画文本", DrawView.TEXT)); // 在顶部面板上添加画文本按钮
		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）

		Panel panelCenter = new Panel(); // 创建中央面板
		draw.setSize(400, 180); // 设置绘画视图的宽高
		panelCenter.add(draw); // 在中央面板上添加绘画视图
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		Panel panelBottom = new Panel(); // 创建底部面板
		Checkbox ck1 = new Checkbox("绘图时填充区域内部"); // 创建一个复选框
		ck1.setFont(new Font("", Font.PLAIN, 16)); // 设置复选框的字体大小
		ck1.addItemListener(new ItemListener() { // 给复选框添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 复选框的状态发生变化
				// 根据复选框的选中状态，设置绘画视图的填充标志
				draw.setFilled(e.getStateChange() == 1 ? true : false);
			}
		});
		panelBottom.add(ck1); // 在底部面板上添加复选框
		frame.add(panelBottom, BorderLayout.SOUTH); // 把底部面板添加到窗口的南边（下方）

		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

	// 获取指定名称与绘图类型的按钮
	private static Button getButton(String text, int draw_type) {
		Button button = new Button(text); // 创建一个按钮
		button.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		button.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				draw.setDrawType(draw_type); // 设置绘画视图的绘图类型
			}
		});
		return button;
	}

}
