package com.awt.window;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

//演示按钮的用法
public class TestButton {

	public static void main(String[] args) {
		final Frame frame = new Frame("测试按钮"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(400, 100); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null); // 将窗口居中。若无该方法，窗口将位于屏幕左上角

		Panel panel = new Panel(); // 创建一个面板
		
		Button button = new Button("点我"); // 创建一个按钮
		button.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		// Button上面的中文会显示乱码，解决办法如下：
		// 选择Run As中的Run Configurations，在Arguments标签下的VM arguments中添加-Dfile.encoding=gbk
		// button.setLabel("点我"); // 设置按钮的文本
		// 设置空间大小要用setPreferredSize方法，因为setSize方法不管用
		button.setPreferredSize(new Dimension(200, 30)); // 设置按钮的推荐宽高
		button.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				button.setLabel(getNowTime() + " 单击了按钮"); // 设置按钮的文本
			}
		});

		panel.add(button); // 在面板上添加按钮
		frame.add(panel); // 在窗口上添加面板

		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

	// 获取当前的时间字符串
	public static String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // 创建一个日期格式化的工具
		return sdf.format(new Date()); // 将当前时间按照指定格式输出格式化后的时间字符串
	}

}
