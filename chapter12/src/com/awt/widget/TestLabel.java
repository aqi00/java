package com.awt.widget;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//演示文本标签的用法
public class TestLabel {

	public static void main(String[] args) {
		final Frame frame = new Frame("测试文本标签"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(550, 150); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setLayout(new BorderLayout()); // 设置窗口的布局为边界布局

		Label label = new Label("这里查看文字效果"); // 创建一个文本标签
		// label.setText(""); // 设置文本标签的文字内容
		label.setAlignment(Label.CENTER); // 设置文本标签的对齐方式
		// 设置空间大小要用setPreferredSize，因为setSize不管用
		label.setPreferredSize(new Dimension(300, 50)); // 设置文本标签的推荐宽高
		Panel panelCenter = new Panel(); // 创建中央面板
		panelCenter.add(label); // 在中央面板上添加文本标签
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		Panel panelTop = new Panel(); // 创建顶部面板
		Button btn1 = new Button("背景黄色"); // 创建一个按钮
		btn1.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		btn1.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				label.setBackground(Color.YELLOW); // 设置文本标签的背景颜色
			}
		});
		panelTop.add(btn1); // 在顶部面板上添加按钮

		Button btn2 = new Button("前景红色"); // 创建一个按钮
		btn2.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		btn2.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				label.setForeground(Color.RED); // 设置文本标签的前景颜色（即文字颜色）
			}
		});
		panelTop.add(btn2); // 在顶部面板上添加按钮

		Button btn3 = new Button("背景紫色"); // 创建一个按钮
		btn3.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		btn3.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				Color purple = getColor(255, 0, 255); // 红光与蓝光混合就变成了紫光
				label.setBackground(purple); // 把标签背景设置为紫色
			}
		});
		panelTop.add(btn3); // 在顶部面板上添加按钮

		Button btn4 = new Button("大号斜体"); // 创建一个按钮
		btn4.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		btn4.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 创建一个30号大小且为斜体的字体对象
				Font italic_big = new Font("大号斜体", Font.ITALIC, 30);
				label.setFont(italic_big); // 设置文本标签的字体及大小
			}
		});
		panelTop.add(btn4); // 在顶部面板上添加按钮

		Button btn5 = new Button("中号粗体"); // 创建一个按钮
		btn5.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		btn5.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 创建一个20号大小且为粗体的字体对象
				Font bold_middle = new Font("中号粗体", Font.BOLD, 20);
				label.setFont(bold_middle); // 设置文本标签的字体及大小
			}
		});
		panelTop.add(btn5); // 在顶部面板上添加按钮

		Button btn6 = new Button("恢复原状"); // 创建一个按钮
		btn6.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		btn6.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				label.setBackground(Color.WHITE); // 设置文本标签的背景颜色
				label.setForeground(Color.BLACK); // 设置文本标签的前景颜色
				label.setFont(new Font(null, Font.PLAIN, 12)); // 设置文本标签的字体及颜色
			}
		});
		panelTop.add(btn6); // 在顶部面板上添加按钮

		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）

		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}
	
	// 使用RGB数值获得AWT的颜色实例
	private static Color getColor(int red, int green, int blue) {
		float[] hsbs = Color.RGBtoHSB(red, green, blue, null); // 把RGB色值转换为HSB色值数组
		Color color = Color.getHSBColor(hsbs[0], hsbs[1], hsbs[2]); // 利用HSB色值获得AWT的颜色实例
		return color;
	}

}
