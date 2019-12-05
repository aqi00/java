package com.swing.senior;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

//演示文件对话框的用法
public class TestChooser {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试文件对话框的窗口"); // 创建一个窗口对象
		frame.setSize(400, 160); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

		JLabel label = new JLabel("这里查看文件对话框的选择结果"); // 创建一个标签
		Font font = new Font("中号", Font.PLAIN, 16);
		label.setFont(font); // 设置标签的文本字体及其大小
		label.setPreferredSize(new Dimension(360, 60)); // 设置按钮的推荐宽高
		JPanel panelCenter = new JPanel(); // 创建中央面板
		panelCenter.add(label); // 在中央面板上添加标签
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		JPanel panelTop = new JPanel(); // 创建顶部面板
		JButton btnOpenFile = new JButton("打开文件对话框"); // 创建一个按钮
		btnOpenFile.setFont(font); // 设置按钮文字的字体与大小
		panelTop.add(btnOpenFile); // 在顶部面板上添加按钮
		JButton btnSaveFile = new JButton("保存文件对话框"); // 创建一个按钮
		btnSaveFile.setFont(font); // 设置按钮文字的字体与大小
		panelTop.add(btnSaveFile); // 在顶部面板上添加按钮
		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）

		JFileChooser chooser = new JFileChooser(); // 创建一个文件对话框
		//chooser.setFont(font); // 无法通过setFont方法一次性设置文件对话框的内部字体
		setComponentFont(chooser, font); // 设置对话框的内部字体
		chooser.setCurrentDirectory(new File("E:/")); // 设置文件对话框的当前目录
//		chooser.setDialogTitle("这是什么"); // 设置文件对话框左上角的标题文字
		chooser.setFileFilter(new FileFilter() { // 设置文件对话框的文件过滤器
			// 判断当前文件是否满足过滤条件，只有满足条件的才会显示在对话框中
			@Override
			public boolean accept(File file) {
				// 目录满足条件，扩展名为txt的文件也满足条件
				return file.isDirectory() || file.getName().toLowerCase().endsWith(".txt");
			}

			@Override
			public String getDescription() { // 获取过滤器的描述
				return "*.txt(文本文件)";
			}
		});
		// 设置文件的挑选模式
//		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // 只显示文件（实际测试发现也会显示目录）
//		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // 只显示目录
//		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 显示文件与目录
		
		btnOpenFile.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 设置文件对话框的类型，这里的对话框准备打开文件
				chooser.setDialogType(JFileChooser.OPEN_DIALOG);
				//chooser.setApproveButtonText("打开文件"); // 设置确定按钮的文本
				int result = chooser.showOpenDialog(frame); // 显示文件打开的对话框
				if (result == JFileChooser.APPROVE_OPTION) { // 单击了确定按钮
					File file = chooser.getSelectedFile(); // 获取在文件对话框中选择的文件
					label.setText("<html>准备打开的文件路径为：" + file.getAbsolutePath() + "</html>");
				} else { // 未单击确定按钮
					label.setText("取消打开文件");
				}
			}
		});

		btnSaveFile.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 设置文件对话框的类型，这里的对话框准备保存文件
				chooser.setDialogType(JFileChooser.SAVE_DIALOG);
				//chooser.setApproveButtonText("保存文件"); // 设置确定按钮的文本
				int result = chooser.showSaveDialog(frame); // 显示文件保存的对话框
				if (result == JFileChooser.APPROVE_OPTION) { // 单击了确定按钮
					File file = chooser.getSelectedFile(); // 获取在文件对话框中选择的文件
					label.setText("<html>准备保存的文件路径为：" + file.getAbsolutePath() + "</html>");
				} else { // 未单击确定按钮
					label.setText("取消保存文件");
				}
			}
		});
		
		frame.setVisible(true); // 必须设置为true，否则看不见
	}
	
	// 设置对话框的内部字体。第一个参数需要传入文件对话框的实例
	private static void setComponentFont(Component component, Font font) {
		component.setFont(font); // 设置当前组件的字体
		if (component instanceof Container) { // 如果该组件是容器
			Container container = (Container) component; // 把该组件强制转为容器
			int count = container.getComponentCount(); // 获取容器内部的组件数量
			for (int i = 0; i < count; i++) { // 遍历该容器的所有组件
				// 给每个组件再设置一遍内部字体
				setComponentFont(container.getComponent(i), font);
			}
		}
	}
	
}
