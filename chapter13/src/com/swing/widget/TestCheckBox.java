package com.swing.widget;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//演示复选框的用法
public class TestCheckBox {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试复选框的窗口"); // 创建一个窗口对象
		frame.setSize(450, 160); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

		JLabel labelCenter = new JLabel("这里查看勾选结果"); // 创建一个标签
		Font font = new Font("中号", Font.PLAIN, 16);
		labelCenter.setFont(font); // 设置标签的文本字体及其大小
		JPanel panelCenter = new JPanel(); // 创建中央面板
		panelCenter.add(labelCenter); // 在中央面板上添加标签
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置
		
		JLabel labelBottom = new JLabel("这里查看点的菜单"); // 创建一个标签
		labelBottom.setFont(font); // 设置标签的文本字体及其大小
		labelBottom.setPreferredSize(new Dimension(420, 30)); // 设置标签的推荐宽高
		JPanel panelBottom = new JPanel(); // 创建底部面板
		panelBottom.add(labelBottom); // 在底部面板上添加标签
		frame.add(panelBottom, BorderLayout.SOUTH); // 把底部面板添加到窗口的南边（下方）

		JPanel panelTop = new JPanel(); // 创建顶部面板
		JCheckBox ck1 = new JCheckBox("麻婆豆腐"); // 创建一个复选框
		ck1.setFont(font); // 设置复选框的文本字体及其大小
		JCheckBox ck3 = new JCheckBox("清蒸桂花鱼"); // 创建一个复选框
		ck3.setFont(font); // 设置复选框的文本字体及其大小
		JCheckBox ck2 = new JCheckBox("香辣小龙虾"); // 创建一个复选框
		ck2.setFont(font); // 设置复选框的文本字体及其大小
		panelTop.add(ck1); // 在顶部面板上添加复选框
		panelTop.add(ck2); // 在顶部面板上添加复选框
		panelTop.add(ck3); // 在顶部面板上添加复选框
		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）
		JCheckBox[] boxArray = new JCheckBox[]{ck1, ck2, ck3}; // 构建复选框数组
		
		ck1.addItemListener(new ItemListener() { // 给复选框添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 复选框的状态发生变化
				// getStateChange方法用于获取复选框的当前状态。1为勾选，0为取消勾选
				labelCenter.setText(String.format("您%s了%s",
						(e.getStateChange() == 1 ? "点" : "取消"), ck1.getText()));
				labelBottom.setText("当前已点菜肴包括：" + getCheckedItem(boxArray));
			}
		});

		ck2.addItemListener(new ItemListener() { // 给复选框添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 复选框的状态发生变化
				// getStateChange方法用于获取复选框的当前状态。1为勾选，0为取消勾选
				labelCenter.setText(String.format("您%s了%s",
						(e.getStateChange() == 1 ? "点" : "取消"), ck2.getText()));
				labelBottom.setText("当前已点菜肴包括：" + getCheckedItem(boxArray));
			}
		});

		ck3.addItemListener(new ItemListener() { // 给复选框添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 复选框的状态发生变化
				// getStateChange方法用于获取复选框的当前状态。1为勾选，0为取消勾选
				labelCenter.setText(String.format("您%s了%s",
						(e.getStateChange() == 1 ? "点" : "取消"), ck3.getText()));
				labelBottom.setText("当前已点菜肴包括：" + getCheckedItem(boxArray));
			}
		});

		frame.setVisible(true); // 必须设置为true，否则看不见
	}

	// 获取已经选定的菜单
	private static String getCheckedItem(JCheckBox[] boxArray) {
		String itemDesc = "";
		for (JCheckBox box : boxArray) { // 遍历复选框数组
			if (box.isSelected() == true) { // 复选框被选中了
				if (itemDesc.length() > 0) {
					itemDesc = itemDesc + "、";
				}
				itemDesc = itemDesc + box.getText(); // 菜单添加选定的菜肴
			}
		}
		return itemDesc;
	}

}
