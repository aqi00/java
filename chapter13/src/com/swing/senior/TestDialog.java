package com.swing.senior;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

//演示基本对话框的用法
public class TestDialog {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试基本对话框的窗口"); // 创建一个窗口对象
		frame.setSize(500, 130); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

		JLabel label = new JLabel("这里查看对话框选择结果"); // 创建一个标签
		Font font = new Font("中号", Font.PLAIN, 16);
		label.setFont(font); // 设置标签的文本字体及其大小
		JPanel panelCenter = new JPanel(); // 创建中央面板
		panelCenter.add(label); // 在中央面板上添加标签
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		JPanel panelTop = new JPanel(); // 创建顶部面板
		JButton btnMessage = new JButton("消息对话框"); // 创建一个按钮
		btnMessage.setFont(font); // 设置按钮文字的字体与大小
		panelTop.add(btnMessage); // 在顶部面板上添加按钮
		JButton btnConfirm = new JButton("确认对话框"); // 创建一个按钮
		btnConfirm.setFont(font); // 设置按钮文字的字体与大小
		panelTop.add(btnConfirm); // 在顶部面板上添加按钮
		JButton btnInput = new JButton("输入对话框"); // 创建一个按钮
		btnInput.setFont(font); // 设置按钮文字的字体与大小
		panelTop.add(btnInput); // 在顶部面板上添加按钮
		JButton btnOption = new JButton("下拉对话框"); // 创建一个按钮
		btnOption.setFont(font); // 设置按钮文字的字体与大小
		panelTop.add(btnOption); // 在顶部面板上添加按钮
		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）
		
		UIManager.put("Button.font", new FontUIResource(font)); // 设置对话框内部按钮的展示效果
		UIManager.put("Label.font", new FontUIResource(font)); // 设置对话框内部标签的展示效果
		UIManager.put("TextField.font", new FontUIResource(font)); // 设置对话框内部输入框的展示效果
		UIManager.put("ComboBox.font", new FontUIResource(font)); // 设置对话框内部下拉框的展示效果

		btnMessage.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 显示消息对话框。消息对话框只有一个确定按钮
				JOptionPane.showMessageDialog(frame, "系统即将关机，请赶紧保存文件", 
						"致命错误", JOptionPane.ERROR_MESSAGE);
				// 图标类型说明如下
				//JOptionPane.PLAIN_MESSAGE 无图标
				//JOptionPane.INFORMATION_MESSAGE 灰圈信息图标
				//JOptionPane.QUESTION_MESSAGE 方框问号图标
				//JOptionPane.WARNING_MESSAGE 三角感叹图标
				//JOptionPane.ERROR_MESSAGE 红圈红叉图标
			}
		});

		btnConfirm.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 显示确认对话框。它有“是”、“否”、“取消”三个按钮，返回值表示哪个按钮被单击了
				// 该对话框不支持类型QUESTION_MESSAGE，且固定显示问号图标
				int result = JOptionPane.showConfirmDialog(frame, "尊敬的用户，你真的要卸载我吗？", 
						"温馨提示", JOptionPane.INFORMATION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) { // 单击了“是”按钮
					label.setText("您选择了“是”按钮。虽然依依不舍，但是只能离开了");
				} else if (result == JOptionPane.NO_OPTION) { // 单击了“否”按钮
					label.setText("您选择了“否”按钮。让我再陪你三百六十五个日夜");
				} else if (result == JOptionPane.CANCEL_OPTION) { // 单击了“取消”按钮
					label.setText("您选择了“取消”按钮。感谢你依然如昨的不变情怀");
				}
			}
		});

		btnInput.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 显示输入对话框。输入对话框有“确认”、“取消”两个按钮，该对话框返回输入框内的文本
				String result = JOptionPane.showInputDialog(frame, "请输入您要查询的商品名称：", 
						"搜索一下", JOptionPane.QUESTION_MESSAGE);
				label.setText("您输入的商品名称是："+result);
			}
		});

		btnOption.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				Object[] options = new Object[]{"鱼香肉丝饭", "香菇滑鸡饭", "黑椒牛排饭"};
				// 显示下拉对话框。下拉对话框需要传入选项数组以及默认选项，该对话框返回下拉框的选择项
				Object result = JOptionPane.showInputDialog(frame, "请选择盒饭名称", 
						"吃饭啦", JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				label.setText("您点的盒饭是："+result);
			}
		});

		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
