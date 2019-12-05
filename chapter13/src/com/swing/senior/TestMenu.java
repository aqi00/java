package com.swing.senior;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

//演示菜单的用法
public class TestMenu {
	private static JLabel label = new JLabel(); // 创建一个标签

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试菜单的窗口"); // 创建一个窗口对象
		frame.setSize(400, 200); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

		JPanel panelCenter = new JPanel(); // 创建中央面板

		Font font = new Font("中号", Font.PLAIN, 16);
		label.setText("这里查看菜单项的单击结果"); // 设置标签的文本
		label.setFont(new Font("中号", Font.PLAIN, 16)); // 设置标签文字的字体与大小
		panelCenter.add(label); // 在中央面板上添加标签
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		JMenuBar menuBar = new JMenuBar(); // 创建一个菜单栏
		JMenu fileMenu = new JMenu("文件"); // 创建一个菜单
		JMenu editMenu = new JMenu("编辑"); // 创建一个菜单
		JMenu helpMenu = new JMenu("帮助"); // 创建一个菜单
		// 以下创建各个菜单项，并分配每个菜单项的动作命令
		JMenuItem newItem = new JMenuItem("新建"); // 创建一个菜单项
		newItem.setActionCommand("New"); // 设置菜单项的动作命令
		JMenuItem openItem = new JMenuItem("打开"); // 创建一个菜单项
		openItem.setActionCommand("Open"); // 设置菜单项的动作命令
		JMenuItem saveItem = new JMenuItem("保存"); // 创建一个菜单项
		saveItem.setActionCommand("Save"); // 设置菜单项的动作命令
		JMenuItem exitItem = new JMenuItem("退出"); // 创建一个菜单项
		exitItem.setActionCommand("Exit"); // 设置菜单项的动作命令
		JMenuItem cutItem = new JMenuItem("剪切"); // 创建一个菜单项
		cutItem.setActionCommand("Cut"); // 设置菜单项的动作命令
		JMenuItem copyItem = new JMenuItem("复制"); // 创建一个菜单项
		copyItem.setActionCommand("Copy"); // 设置菜单项的动作命令
		JMenuItem pasteItem = new JMenuItem("粘贴"); // 创建一个菜单项
		pasteItem.setActionCommand("Paste"); // 设置菜单项的动作命令
		JMenuItem aboutItem = new JMenuItem("关于"); // 创建一个菜单项
		aboutItem.setActionCommand("About"); // 设置菜单项的动作命令
		// 以下把每个菜单项添加到对应的菜单上
		fileMenu.add(newItem); // 在菜单上添加菜单项
		fileMenu.add(openItem); // 在菜单上添加菜单项
		fileMenu.add(saveItem); // 在菜单上添加菜单项
		fileMenu.addSeparator(); // 在菜单上添加分隔线
		fileMenu.add(exitItem); // 在菜单上添加菜单项
		editMenu.add(copyItem); // 在菜单上添加菜单项
		editMenu.add(pasteItem); // 在菜单上添加菜单项
		helpMenu.add(aboutItem); // 在菜单上添加菜单项
		// 以下把每个菜单都添加到菜单栏上
		menuBar.add(fileMenu); // 在菜单栏上添加菜单
		menuBar.add(editMenu); // 在菜单栏上添加菜单
		menuBar.add(helpMenu); // 在菜单栏上添加菜单
		frame.setJMenuBar(menuBar); // 在窗口上添加菜单栏
		setMenuAction(menuBar, font); // // 设置菜单项的外观及其动作事件
		
		frame.setVisible(true); // 必须设置为true，否则看不见
	}
	
	// 设置菜单项的外观及其动作事件
	private static void setMenuAction(JMenuBar bar, Font font) {
		for (int i = 0; i < bar.getMenuCount(); i++) { // 遍历菜单栏下的所有菜单
			JMenu menu = bar.getMenu(i); // 获取指定位置的菜单
			menu.setFont(font); // 设置菜单按钮的文本字体
			menu.setPreferredSize(new Dimension(60, 30)); // 设置菜单按钮的推荐宽高
			for (int j = 0; j < menu.getItemCount(); j++) { // 遍历菜单下的所有菜单项
				JMenuItem item = menu.getItem(j); // 获取指定位置的菜单项
				if (item != null) { // 菜单项非空。这个判断是为了避开菜单项之间的分隔线
					item.setFont(font); // 设置菜单项的文本字体
					item.setPreferredSize(new Dimension(120, 28)); // 设置菜单项的推荐宽高
					item.addActionListener(new ActionListener() { // 给菜单项注册单击监听器
						public void actionPerformed(ActionEvent e) { // 发生了单击事件
							// 调用getActionCommand方法获取单击事件的动作命令，并展示在标签上
							label.setText("您单击了菜单项：" + e.getActionCommand());
						}
					});
				}
			}
		}
	}
	
}
