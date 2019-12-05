package com.swing.widget;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//演示单选按钮的用法
public class TestRadioButton {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试单选按钮的窗口"); // 创建一个窗口对象
		frame.setSize(450, 130); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

		JLabel label = new JLabel("这里查看点餐结果"); // 创建一个标签
		Font font = new Font("中号", Font.PLAIN, 16);
		label.setFont(font); // 设置标签的文本字体及其大小
		JPanel panelCenter = new JPanel(); // 创建中央面板
		panelCenter.add(label); // 在中央面板上添加标签
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		JPanel panelTop = new JPanel(); // 创建顶部面板
		JRadioButton rb1 = new JRadioButton("鱼香肉丝饭", false); // 创建单选按钮，并且默认未选中
		rb1.setFont(font); // 设置单选按钮的文本字体及其大小
		JRadioButton rb2 = new JRadioButton("香菇滑鸡饭", true); // 创建单选按钮，并且默认已选中
		rb2.setFont(font); // 设置单选按钮的文本字体及其大小
		JRadioButton rb3 = new JRadioButton("黑椒牛排饭", false); // 创建单选按钮，并且默认未选中
		rb3.setFont(font); // 设置单选按钮的文本字体及其大小
		panelTop.add(rb1); // 在顶部面板上添加单选按钮
		panelTop.add(rb2); // 在顶部面板上添加单选按钮
		panelTop.add(rb3); // 在顶部面板上添加单选按钮
		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）
		
		ButtonGroup group = new ButtonGroup(); // 创建一个按钮小组
		group.add(rb1); // 把单选按钮1加入到按钮小组
		group.add(rb2); // 把单选按钮2加入到按钮小组
		group.add(rb3); // 把单选按钮3加入到按钮小组

		rb1.addItemListener(new ItemListener() { // 给单选按钮添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 单选按钮被选中
				label.setText("您点了" + rb1.getText()); // 在标签上显示当前选中的单选按钮文本
			}
		});

		rb2.addItemListener(new ItemListener() { // 给单选按钮添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 单选按钮被选中
				label.setText("您点了" + rb2.getText()); // 在标签上显示当前选中的单选按钮文本
			}
		});

		rb3.addItemListener(new ItemListener() { // 给单选按钮添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 单选按钮被选中
				label.setText("您点了" + rb3.getText()); // 在标签上显示当前选中的单选按钮文本
			}
		});
		
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
