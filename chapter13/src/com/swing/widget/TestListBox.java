package com.swing.widget;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//演示列表框的用法
public class TestListBox {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试列表框的窗口"); // 创建一个窗口对象
		frame.setSize(400, 250); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

		JLabel labelBottom = new JLabel("这里查看列表框的选择结果"); // 创建一个标签
		Font font = new Font("中号", Font.PLAIN, 16);
		labelBottom.setFont(font); // 设置标签的文本字体及其大小
		JPanel panelBottom = new JPanel(); // 创建底部面板
		panelBottom.add(labelBottom); // 在底部面板上添加标签
		frame.add(panelBottom, BorderLayout.SOUTH); // 把底部面板添加到窗口的南边（下方）
		
		JLabel labelCenter = new JLabel("这里查看已经点的快餐"); // 创建一个标签
		labelCenter.setFont(font); // 设置标签的文本字体及其大小
		JPanel panelCenter = new JPanel(); // 创建中央面板
		panelCenter.add(labelCenter); // 在中央面板上添加标签
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		JPanel panelLeft = new JPanel(); // 创建左边面板
		// 创建一个列表框模型
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addElement("鱼香肉丝饭"); // 往列表模型中添加元素
		listModel.addElement("香菇滑鸡饭"); // 往列表模型中添加元素
		listModel.addElement("黑椒牛排饭"); // 往列表模型中添加元素
		listModel.addElement("梅菜扣肉饭"); // 往列表模型中添加元素
		listModel.addElement("糖醋里脊饭"); // 往列表模型中添加元素
		listModel.addElement("红烧排骨饭"); // 往列表模型中添加元素
		listModel.addElement("台式卤肉饭"); // 往列表模型中添加元素
		JList<String> listBox = new JList<String>(listModel); // 创建一个列表框
		listBox.setFont(font);
		panelLeft.add(listBox); // 在顶部面板上添加列表框
		frame.add(panelLeft, BorderLayout.WEST); // 把顶部面板添加到窗口的西边（左侧）
		
		listBox.addListSelectionListener(new ListSelectionListener() { // 给列表框添加一个单击监听器
			public void valueChanged(ListSelectionEvent arg0) { // 列表框被选择
				// 获取列表框内选中项的序号及其描述
				// getSelectedIndex方法可获得选中项的序号，getSelectedValue方法可获得选中项的值
				String desc = String.format("您点了第%d项，快餐名称是%s", 
						listBox.getSelectedIndex(), listBox.getSelectedValue());
				labelBottom.setText(desc); // 在标签上显示当前选中的文本项
				String total = "<html>您已选择的快餐列表如下：<br>";
				// 获取列表框内的所有选择项，并拼接html格式的描述串
				for (String str : listBox.getSelectedValuesList()) {
					total = String.format("%s<center>%s</center>", total, str);
				}
				total += "</html>";
				labelCenter.setText(total); // 在标签上显示所有选中的文本项
			}
		});

		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
