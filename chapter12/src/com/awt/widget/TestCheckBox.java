package com.awt.widget;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//演示复选框的用法
public class TestCheckBox {

	public static void main(String[] args) {
		final Frame frame = new Frame("测试复选框"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(450, 160); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setLayout(new BorderLayout()); // 设置窗口的布局为边界布局

		Label labelCenter = new Label("这里查看勾选结果"); // 创建一个文本标签
		labelCenter.setFont(new Font("", Font.PLAIN, 16)); // 设置标签的字体大小
		Panel panelCenter = new Panel(); // 创建中央面板
		panelCenter.add(labelCenter); // 在中央面板上添加文本标签
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置
		
		Label labelBottom = new Label("这里查看点的菜单"); // 创建一个文本标签
		labelBottom.setFont(new Font("", Font.PLAIN, 16)); // 设置标签的字体大小
		labelBottom.setPreferredSize(new Dimension(420, 30)); // 设置文本标签的推荐宽高
		Panel panelBottom = new Panel(); // 创建底部面板
		panelBottom.add(labelBottom); // 在底部面板上添加文本标签
		frame.add(panelBottom, BorderLayout.SOUTH); // 把底部面板添加到窗口的南边（下方）

		Panel panelTop = new Panel(); // 创建顶部面板
		Checkbox ck1 = new Checkbox("麻婆豆腐"); // 创建一个复选框
		ck1.setFont(new Font("", Font.PLAIN, 16)); // 设置复选框的字体大小
		Checkbox ck2 = new Checkbox("香辣小龙虾"); // 创建一个复选框
		ck2.setFont(new Font("", Font.PLAIN, 16)); // 设置复选框的字体大小
		Checkbox ck3 = new Checkbox("清蒸桂花鱼"); // 创建一个复选框
		ck3.setFont(new Font("", Font.PLAIN, 16)); // 设置复选框的字体大小
		panelTop.add(ck1); // 在顶部面板上添加复选框
		panelTop.add(ck2); // 在顶部面板上添加复选框
		panelTop.add(ck3); // 在顶部面板上添加复选框
		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）
		Checkbox[] boxArray = new Checkbox[]{ck1, ck2, ck3}; // 构建复选框数组
		
		ck1.addItemListener(new ItemListener() { // 给复选框添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 复选框的状态发生变化
				// getStateChange方法用于获取复选框的当前状态。1为勾选，0为取消勾选
				labelCenter.setText(String.format("您%s了%s",
						(e.getStateChange() == 1 ? "点" : "取消"), ck1.getLabel()));
				labelBottom.setText("当前已点菜肴包括：" + getCheckedItem(boxArray));
			}
		});

		ck2.addItemListener(new ItemListener() { // 给复选框添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 复选框的状态发生变化
				// getStateChange方法用于获取复选框的当前状态。1为勾选，0为取消勾选
				labelCenter.setText(String.format("您%s了%s",
						(e.getStateChange() == 1 ? "点" : "取消"), ck2.getLabel()));
				labelBottom.setText("当前已点菜肴包括：" + getCheckedItem(boxArray));
			}
		});

		ck3.addItemListener(new ItemListener() { // 给复选框添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 复选框的状态发生变化
				// getStateChange方法用于获取复选框的当前状态。1为勾选，0为取消勾选
				labelCenter.setText(String.format("您%s了%s",
						(e.getStateChange() == 1 ? "点" : "取消"), ck3.getLabel()));
				labelBottom.setText("当前已点菜肴包括：" + getCheckedItem(boxArray));
			}
		});

		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}
	
	// 获取已经选定的菜单
	private static String getCheckedItem(Checkbox[] boxArray) {
		String itemDesc = "";
		for (Checkbox box : boxArray) { // 遍历复选框数组
			if (box.getState() == true) { // 复选框被选中了
				if (itemDesc.length() > 0) {
					itemDesc = itemDesc + "、";
				}
				itemDesc = itemDesc + box.getLabel(); // 菜单添加选定的菜肴
			}
		}
		return itemDesc;
	}

}
