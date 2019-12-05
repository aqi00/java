package com.awt.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//图像加工的工具类
public class ImageUtil {

	// 旋转图像。输入参数依次为：原图像、旋转角度
	public static BufferedImage rotateImage(BufferedImage origin, int rotateDegree) {
		int width = origin.getWidth(); // 获取原图像的宽度
		int height = origin.getHeight(); // 获取原图像的高度
		int imageType = origin.getType(); // 获取原图像的颜色类型
		// 创建与原图像同样尺寸的新图像
		BufferedImage newImage = new BufferedImage(width, height, imageType);
		Graphics2D graphics2d = newImage.createGraphics(); // 创建并获取新图像的画笔
		// 以原图像的中点为圆心，将画布按逆时针旋转若干角度
		graphics2d.rotate(Math.toRadians(rotateDegree), width / 2, height / 2);
		// 使用新图像的画笔绘制原图像，也就是把原图像画到新图像上
		graphics2d.drawImage(origin, 0, 0, null);
		return newImage; // 返回加工后的新图像
	}

	// 缩放图像。输入参数依次为：原图像、缩放的比率
	public static BufferedImage resizeImage(BufferedImage origin, double ratio) {
		int width = origin.getWidth(); // 获取原图像的宽度
		int height = origin.getHeight(); // 获取原图像的高度
		int imageType = origin.getType(); // 获取原图像的颜色类型
		// 创建尺寸大小为缩放宽高的新图像
		BufferedImage newImage = new BufferedImage((int)(width*ratio), (int)(height*ratio), imageType);
		Graphics2D graphics2d = newImage.createGraphics(); // 创建并获取新图像的画笔
		graphics2d.scale(ratio, ratio); // 把画布的宽高分别缩放到指定比例
		// 使用新图像的画笔绘制原图像，也就是把原图像画到新图像上
		graphics2d.drawImage(origin, 0, 0, null);
		return newImage; // 返回加工后的新图像
	}

	// 平移图像。输入参数依次为：原图像、水平方向上的平移距离、垂直方向上的平移距离
	public static BufferedImage translateImage(BufferedImage origin, int translateX, int translateY) {
		int width = origin.getWidth(); // 获取原图像的宽度
		int height = origin.getHeight(); // 获取原图像的高度
		int imageType = origin.getType(); // 获取原图像的颜色类型
		// 创建与原图像同样尺寸的新图像
		BufferedImage newImage = new BufferedImage(width, height, imageType);
		Graphics2D graphics2d = newImage.createGraphics(); // 创建并获取新图像的画笔
		graphics2d.translate(translateX, translateY); // 把画笔移动到指定的坐标点
		// 使用新图像的画笔绘制原图像，也就是把原图像画到新图像上
		graphics2d.drawImage(origin, 0, 0, null);
		return newImage; // 返回加工后的新图像
	}

	// 裁剪图像。输入参数依次为：原图像、裁剪的比率
	public static BufferedImage clipImage(BufferedImage origin, double ratio) {
		int width = origin.getWidth(); // 获取原图像的宽度
		int height = origin.getHeight(); // 获取原图像的高度
		int imageType = origin.getType(); // 获取原图像的颜色类型
		// 创建尺寸大小为裁剪比例的新图像
		BufferedImage newImage = new BufferedImage((int)(width*ratio), (int)(height*ratio), imageType);
		Graphics2D graphics2d = newImage.createGraphics(); // 创建并获取新图像的画笔
		// 把画笔的绘图范围裁剪到从左上角到右下角的指定区域，
		// 其中左上角的坐标为(0,0)，右下角的坐标为(width*ratio,height*ratio)
		graphics2d.clipRect(0, 0, (int)(width*ratio), (int)(height*ratio));
		// 使用新图像的画笔绘制原图像，也就是把原图像画到新图像上
		graphics2d.drawImage(origin, 0, 0, null);
		return newImage; // 返回加工后的新图像
	}

	// 水平翻转图像。输入参数依次为：原图像
	public static BufferedImage flipImage(BufferedImage origin) {
		int width = origin.getWidth(); // 获取原图像的宽度
		int height = origin.getHeight(); // 获取原图像的高度
		int imageType = origin.getType(); // 获取原图像的颜色类型
		// 创建与原图像同样尺寸的新图像
		BufferedImage newImage = new BufferedImage(width, height, imageType);
		Graphics2D graphics2d = newImage.createGraphics(); // 创建并获取新图像的画笔
		// 使用新图像的画笔在目标位置绘制指定尺寸的原图像
		// 其中目标区域的左上角坐标为(0,0)，右下角坐标为(width,height)
		// 对于水平翻转的情况，原图像的起始坐标为(width,0)，终止坐标为(0,height)
		graphics2d.drawImage(origin, 0, 0, width, height, width, 0, 0, height, null);
		// 对于垂直翻转的情况，原图像的起始坐标为(0,height)，终止坐标为(width,0)
		//graphics2d.drawImage(origin, 0, 0, width, height, 0, height, width, 0, null);
		return newImage; // 返回加工后的新图像
	}

}
