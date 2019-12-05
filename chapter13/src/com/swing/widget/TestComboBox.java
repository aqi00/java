package com.swing.widget;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//演示下拉框的用法
public class TestComboBox {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试下拉框的窗口"); // 创建一个窗口对象
		frame.setSize(400, 130); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

		JLabel label = new JLabel("这里查看下拉框的选择结果"); // 创建一个标签
		Font font = new Font("中号", Font.PLAIN, 16);
		label.setFont(font); // 设置标签的文本字体及其大小
		JPanel panelCenter = new JPanel(); // 创建中央面板
		panelCenter.add(label); // 在中央面板上添加标签
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		JPanel panelTop = new JPanel(); // 创建顶部面板
		// 创建一个下拉框模型
		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>();
		comboModel.addElement("鱼香肉丝饭"); // 往下拉模型中添加元素
		comboModel.addElement("香菇滑鸡饭"); // 往下拉模型中添加元素
		comboModel.addElement("黑椒牛排饭"); // 往下拉模型中添加元素
		comboModel.addElement("梅菜扣肉饭"); // 往下拉模型中添加元素
		comboModel.addElement("糖醋里脊饭"); // 往下拉模型中添加元素
		comboModel.addElement("红烧排骨饭"); // 往下拉模型中添加元素
		comboModel.addElement("台式卤肉饭"); // 往下拉模型中添加元素
		JComboBox<String> comboBox = new JComboBox<String>(comboModel); // 创建一个下拉框
		comboBox.setFont(font); // 设置下拉框的字体
		comboBox.setEditable(false); // 设置下拉框能否编辑。默认不允许编辑
		panelTop.add(comboBox); // 在顶部面板上添加下拉框
		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）
		
		comboBox.addItemListener(new ItemListener() { // 给下拉框添加一个单击监听器
			public void itemStateChanged(ItemEvent e) { // 下拉框被选择
				// 获取下拉框内选中项的序号及其描述。
				// getSelectedIndex方法可获得选中项的序号，getSelectedItem方法可获得选中项的对象
				String desc = String.format("您点了第%d项，快餐名称是%s", 
						comboBox.getSelectedIndex(), comboBox.getSelectedItem().toString());
				label.setText(desc); // 在标签上显示当前选中的文本项
			}
		});

		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
