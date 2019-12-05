package com.swing.window;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//演示如何显示图像
public class TestImage {

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("测试图像的窗口"); // 创建一个窗口对象
		frame.setSize(450, 350); // 必须设置宽高，否则没有窗体
		//frame.setSize(450, 130); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序
		
		JPanel panelCenter = new JPanel(); // 创建中央面板

		Font font = new Font("中号", Font.PLAIN, 16);
		JLabel label = new JLabel(); // 创建一个标签
		label.setFont(font); // 设置标签文字的字体与大小
		// 把图片文件读到缓存图像
		//BufferedImage image = ImageIO.read(new File("E:/apple.png"));
		// 把输入流中的图片数据读到缓存图像
		//BufferedImage image = ImageIO.read(TestImage.class.getResourceAsStream("apple.png"));
		//ImageIcon icon = new ImageIcon(image); // 创建一个图标
		ImageIcon icon = new ImageIcon("E:/apple.png"); // 创建一个指定路径的图标
//		URL url = new URL("file:///E:/apple.png"); // 创建一个本地路径的URL对象
//		ImageIcon icon = new ImageIcon(url); // 创建一个指定URL的图标
//		URL url = new URL("https://profile.csdnimg.cn/C/1/5/1_aqi00"); // 创建一个网络地址的URL对象
//		ImageIcon icon = new ImageIcon(url); // 创建一个来自网络图片的图标
		label.setIcon(icon); // 设置标签的图标（图标在文字左边）
		label.setIconTextGap(10); // 设置图标与文字之间的间隔大小
		panelCenter.add(label); // 在中央面板上添加标签
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		JPanel JPanelTop = new JPanel(); // 创建顶部面板
		JButton btn1 = new JButton("只有图标"); // 创建一个按钮
		btn1.setFont(font); // 设置按钮文字的字体与大小
		btn1.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				label.setIcon(icon); // 设置标签的图标
				label.setText(null); // 设置标签的文本为空，此时不显示文本
			}
		});
		JPanelTop.add(btn1); // 在顶部面板上添加按钮

		JButton btn2 = new JButton("有图标有文字"); // 创建一个按钮
		btn2.setFont(font); // 设置按钮文字的字体与大小
		btn2.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				label.setIcon(icon); // 设置标签的图标（图标在文字左边）
				label.setText("这是一个苹果"); // 设置标签的文本
			}
		});
		JPanelTop.add(btn2); // 在顶部面板上添加按钮
		
		JButton btn3 = new JButton("只有文字"); // 创建一个按钮
		btn3.setFont(font); // 设置按钮文字的字体与大小
		btn3.addActionListener(new ActionListener() { // 给按钮注册单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				label.setIcon(null); // 设置标签的图标为空，此时不显示图像
				label.setText("这是一个苹果"); // 设置标签的文本
			}
		});
		JPanelTop.add(btn3); // 在顶部面板上添加按钮

		frame.add(JPanelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）
		
		frame.setVisible(true); // 必须设置为true，否则看不见
	}
	
}
