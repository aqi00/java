package com.awt.verifycode;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//演示如何生成验证码图片
public class TestVerifyCode {

	public static void main(String[] args) {
		final Frame frame = new Frame("测试验证码"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(400, 150); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setLayout(new BorderLayout()); // 设置窗口的布局为边界布局
		
		CodeView codeView = new CodeView(); // 创建一个验证码视图
		codeView.setSize(200, 50); // 设置验证码视图的宽高
		codeView.setDisturbType(CodeView.DOT); // 设置验证码视图的干扰类型
		Panel panelCenter = new Panel(); // 创建中央面板
		panelCenter.add(codeView); // 在中央面板上添加验证码视图
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		Panel panelTop = new Panel(); // 创建顶部面板

		Button lineButton = new Button("使用干扰线"); // 创建一个按钮
		lineButton.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		lineButton.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				codeView.setDisturbType(CodeView.LINE); // 将验证码视图的干扰类型设置为干扰线
			}
		});
		panelTop.add(lineButton); // 在顶部面板上添加按钮

		Button dotButton = new Button("使用干扰点"); // 创建一个按钮
		dotButton.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		dotButton.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				codeView.setDisturbType(CodeView.DOT); // 将验证码视图的干扰类型设置为干扰点
			}
		});
		panelTop.add(dotButton); // 在顶部面板上添加按钮

		Button saveButton = new Button("保存验证码"); // 创建一个按钮
		saveButton.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		saveButton.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 创建验证码图片的文件对象
				File imageFile = new File("E:\\"+codeView.getCodeNumber()+".jpg");
				System.out.println("file_path="+imageFile.getAbsolutePath());
				try {
					// 把验证码视图展示的图像保存到图片文件中
					ImageIO.write(codeView.getCodeImage(), "jpg", imageFile);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		panelTop.add(saveButton); // 在顶部面板上添加按钮

		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）
		
		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
