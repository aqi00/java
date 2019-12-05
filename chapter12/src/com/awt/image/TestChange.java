package com.awt.image;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//演示几种常见的图像加工操作
public class TestChange {

	public static void main(String[] args) throws IOException {
		final Frame frame = new Frame("测试图片加工"); // 创建一个窗口对象

		frame.addWindowListener(new WindowAdapter() { // 为窗口注册监听器，实现窗口关闭功能
			public void windowClosing(WindowEvent e) { // 单击了窗口右上角的叉号按钮
				frame.dispose(); // 关闭窗口
			}
		});

		frame.setSize(520, 340); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setLayout(new BorderLayout()); // 设置窗口的布局为边界布局

		ImageView imageView = new ImageView(); // 创建一个图像视图
		// 把输入流中的图片数据读到缓存图像
		BufferedImage origin = ImageIO.read(TestChange.class.getResourceAsStream("apple.png"));
		System.out.println("getWidth=" + origin.getWidth() + ", getHeight=" + origin.getHeight());
		imageView.setSize(origin.getWidth(), origin.getHeight()); // 设置图像视图的宽高
		imageView.setImage(origin); // 设置图像视图的缓存图像
		Panel panelCenter = new Panel(); // 创建中央面板
		panelCenter.add(imageView); // 在中央面板上添加图像视图
		frame.add(panelCenter, BorderLayout.CENTER); // 把中央面板添加到窗口的中间位置

		Panel panelTop = new Panel(); // 创建顶部面板

		Button originButton = new Button("原始图片"); // 创建一个按钮
		originButton.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		originButton.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 将图像视图的尺寸设置为原图像的宽高
				imageView.setSize(origin.getWidth(), origin.getHeight());
				imageView.setImage(origin); // 设置图像视图的缓存图像
			}
		});
		panelTop.add(originButton); // 在顶部面板上添加按钮

		Button rotateButton = new Button("旋转图片"); // 创建一个按钮
		rotateButton.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		rotateButton.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 将图像视图的尺寸设置为原图像的宽高
				imageView.setSize(origin.getWidth(), origin.getHeight());
				// 获得顺时针旋转90度后的新图像
				BufferedImage newImage = ImageUtil.rotateImage(origin, 90);
				imageView.setImage(newImage); // 设置图像视图的缓存图像
			}
		});
		panelTop.add(rotateButton); // 在顶部面板上添加按钮

		Button resizeButton = new Button("缩放图片"); // 创建一个按钮
		resizeButton.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		resizeButton.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				double ratio = 0.75; // 缩放比率
				// 将图像视图的尺寸设置为缩放后的宽高
				imageView.setSize((int)(origin.getWidth()*ratio), (int)(origin.getHeight()*ratio));
				// 获得缩放后的新图像
				BufferedImage newImage = ImageUtil.resizeImage(origin, ratio);
				imageView.setImage(newImage); // 设置图像视图的缓存图像
			}
		});
		panelTop.add(resizeButton); // 在顶部面板上添加按钮

		Button translateButton = new Button("平移图片"); // 创建一个按钮
		translateButton.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		translateButton.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 将图像视图的尺寸设置为原图像的宽高
				imageView.setSize(origin.getWidth(), origin.getHeight());
				// 获得平移后的新图像
				BufferedImage newImage = ImageUtil.translateImage(origin,
						origin.getWidth() / 2, origin.getHeight() / 2);
				imageView.setImage(newImage); // 设置图像视图的缓存图像
			}
		});
		panelTop.add(translateButton); // 在顶部面板上添加按钮

		Button clipButton = new Button("裁剪图片"); // 创建一个按钮
		clipButton.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		clipButton.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				double ratio = 0.75; // 裁剪比率
				// 将图像视图的尺寸设置为裁剪后的宽高
				imageView.setSize((int)(origin.getWidth()*ratio), (int)(origin.getHeight()*ratio));
				// 获得裁剪后的新图像
				BufferedImage newImage = ImageUtil.clipImage(origin, ratio);
				imageView.setImage(newImage); // 设置图像视图的缓存图像
			}
		});
		panelTop.add(clipButton); // 在顶部面板上添加按钮

		Button flipButton = new Button("翻转图片"); // 创建一个按钮
		flipButton.setFont(new Font("", Font.PLAIN, 16)); // 设置按钮的字体大小
		flipButton.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				// 将图像视图的尺寸设置为原图像的宽高
				imageView.setSize(origin.getWidth(), origin.getHeight());
				// 获得水平翻转后的新图像
				BufferedImage newImage = ImageUtil.flipImage(origin);
				imageView.setImage(newImage); // 设置图像视图的缓存图像
			}
		});
		panelTop.add(flipButton); // 在顶部面板上添加按钮

		frame.add(panelTop, BorderLayout.NORTH); // 把顶部面板添加到窗口的北边（上方）

		// setVisible方法放到最后面，这样才能显示所有控件
		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
