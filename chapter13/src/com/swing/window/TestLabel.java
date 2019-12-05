package com.swing.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//演示标签的用法
public class TestLabel {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试标签的窗口"); // 创建一个窗口对象
		frame.setSize(450, 200); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序
		
		JPanel panelCenter = new JPanel(); // 创建中央面板
		
		Font fontMiddle = new Font("中号", Font.PLAIN, 16);
		JLabel label = new JLabel(); // JLabel无需另外设置文件的字符编码
		label.setPreferredSize(new Dimension(350, 100)); // 设置标签的推荐宽高
		// 中文字体支持：楷体、隶书、宋体、仿宋、黑体、幼圆
		label.setFont(new Font("楷体", Font.PLAIN, 25)); // 设置标签文字的字体与大小
		// 设置标签的文本。注意换行符\n不管用
		label.setText("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
		label.setHorizontalAlignment(JLabel.LEFT); // 设置标签文字在水平方向的对齐方式
		label.setVerticalAlignment(JLabel.CENTER); // 设置标签文字在垂直方向的对齐方式
		label.setOpaque(true); // 设置标签的背景为不透明
		label.setBackground(Color.WHITE); // 设置标签的背景色
		panelCenter.add(label); // 在中央面板上添加标签

		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		JPanel JPanelTop = new JPanel(); // 创建顶部面板
		JButton btn1 = new JButton("不换行"); // 创建一个按钮
		btn1.setFont(fontMiddle); // 设置按钮文字的字体与大小
		btn1.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 设置按钮的文本。换行符\n不管用
				label.setText("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
			}
		});
		JPanelTop.add(btn1); // 在顶部面板上添加按钮

		JButton btn2 = new JButton("自动换行"); // 创建一个按钮
		btn2.setFont(fontMiddle); // 设置按钮文字的字体与大小
		btn2.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 设置按钮的文本(自动换行)
				label.setText("<html>床前明月光，疑是地上霜。举头望明月，低头思故乡。</html>");
			}
		});
		JPanelTop.add(btn2); // 在顶部面板上添加按钮

		JButton btn3 = new JButton("手动换行"); // 创建一个按钮
		btn3.setFont(fontMiddle); // 设置按钮文字的字体与大小
		btn3.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 设置按钮的文本(手动换行)
				label.setText("<html>床前明月光，疑是地上霜。<br>举头望明月，低头思故乡。</html>");
			}
		});
		JPanelTop.add(btn3); // 在顶部面板上添加按钮

		JButton btn4 = new JButton("多彩文字"); // 创建一个按钮
		btn4.setFont(fontMiddle); // 设置按钮文字的字体与大小
		btn4.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 设置按钮的文本(分段设置不同的文字颜色)
				label.setText("<html><font color='red'>床前明月光，</font><b>疑是地上霜。</b><br><font color='yellow'>举头望明月，</font><i>低头思故乡。</i></html>");
			}
		});
		JPanelTop.add(btn4); // 在顶部面板上添加按钮

		frame.add(JPanelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）
		
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
