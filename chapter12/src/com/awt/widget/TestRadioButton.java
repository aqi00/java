package com.awt.widget;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//演示单选按钮的用法
public class TestRadioButton {

	public static void main(String[] args) {
		final Frame frame = new Frame("测试单选按钮"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(400, 130); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角

		Panel panelTop = new Panel(); // 创建顶部面板
		Label label = new Label("这里查看单选结果"); // 创建一个文本标签
		label.setFont(new Font("", Font.PLAIN, 16)); // 设置标签的字体大小
		CheckboxGroup group = new CheckboxGroup(); // 创建一个选择框的小组

		// 创建一个加入了小组的单选框，并且默认未选中
		Checkbox ck1 = new Checkbox("鱼香肉丝饭", group, false);
		ck1.setFont(new Font("", Font.PLAIN, 16)); // 设置单选框的字体大小
		ck1.addItemListener(new ItemListener() { // 给单选框添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 单选框被选中
				label.setText("您点了" + ck1.getLabel()); // 在文字标签上显示当前选中的单选框标签
			}
		});
		panelTop.add(ck1); // 在顶部面板上添加单选框

		// 创建一个加入了小组的单选框，并且默认已选中
		Checkbox ck2 = new Checkbox("香菇滑鸡饭", group, true);
		ck2.setFont(new Font("", Font.PLAIN, 16)); // 设置单选框的字体大小
		ck2.addItemListener(new ItemListener() { // 给单选框添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 单选框被选中
				label.setText("您点了" + ck2.getLabel()); // 在文字标签上显示当前选中的单选框标签
			}
		});
		panelTop.add(ck2); // 在顶部面板上添加单选框

		// 创建一个加入了小组的单选框，并且默认未选中
		Checkbox ck3 = new Checkbox("黑椒牛排饭", group, false);
		ck3.setFont(new Font("", Font.PLAIN, 16)); // 设置单选框的字体大小
		ck3.addItemListener(new ItemListener() { // 给单选框添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 单选框被选中
				label.setText("您点了" + ck3.getLabel()); // 在文字标签上显示当前选中的单选框标签
			}
		});
		panelTop.add(ck3); // 在顶部面板上添加单选框

		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）

		Panel panelCenter = new Panel(); // 创建中央面板
		panelCenter.add(label); // 在中央面板上添加文本标签
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
