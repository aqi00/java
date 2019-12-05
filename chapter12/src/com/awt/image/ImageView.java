package com.awt.image;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

//定义一个显示图片用的图像视图
public class ImageView extends Component {
	private static final long serialVersionUID = 1L;
	private BufferedImage image; // 声明一个缓存图像

	// 设置图片路径
	public void setImagePath(String path) {
		try {
			image = ImageIO.read(new File(path)); // 把指定路径的图片文件读到缓存图像
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 设置图片的输入流
	public void setImageStream(InputStream is) {
		try {
			image = ImageIO.read(is); // 把输入流中的图片数据读到缓存图像
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 设置缓存图像
	public void setImage(BufferedImage image) {
		this.image = image;
		repaint(); // 重新绘图，此时会接着执行paint方法
	}

	@Override
	public void paint(Graphics g) { // 绘制控件的方法
		if (image != null) {
			if (getWidth() > 0 && getHeight() > 0) { // 有指定宽高
				g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // 按指定宽高绘制图像
			} else { // 未指定宽高
				g.drawImage(image, 0, 0, null); // 按原尺寸绘制图像
			}
		}
	}

	@Override
	public Dimension getPreferredSize() { // 获取控件的推荐宽高
		if (image != null) {
			if (getWidth() > 0 && getHeight() > 0) { // 有指定宽高
				return new Dimension(getWidth(), getHeight()); // 返回setSize方法指定的宽高
			} else { // 未指定宽高
				return new Dimension(image.getWidth(), image.getHeight()); // 返回图像的宽高
			}
		} else {
			return new Dimension(0, 0); // 无图像则隐藏控件
		}
	}
}
