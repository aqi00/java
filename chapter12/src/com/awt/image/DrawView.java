package com.awt.image;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

//定义一个绘画视图
public class DrawView extends Component {
	private static final long serialVersionUID = 1L;
	public final static int LINE = 1; // 画线段
	public final static int RECT = 2; // 画矩形
	public final static int ROUND_RECT = 3; // 画圆角矩形
	public final static int OVAL = 4; // 画椭圆
	public final static int ARC = 5; // 画圆弧
	public final static int TEXT = 6; // 画文本

	private int mDrawType = LINE; // 绘图类型
	private boolean isFilled = false; // 是否填充

	// 设置绘图类型
	public void setDrawType(int draw_type) {
		mDrawType = draw_type;
		repaint(); // 重新绘图，此时会接着执行paint方法
	}

	// 设置是否填充
	public void setFilled(boolean is_filled) {
		isFilled = is_filled;
		repaint(); // 重新绘图，此时会接着执行paint方法
	}

	@Override
	public void paint(Graphics g) { // 绘制控件的方法
		g.translate(0, 0);
		if (mDrawType == LINE) {
			g.setColor(Color.BLACK); // 设置画笔的颜色为黑色
			// 在指定坐标的(x1,y1)与(x2,y2)两点之间画条线段
			g.drawLine(0, 0, getWidth(), getHeight());
		} else if (mDrawType == RECT) {
			g.setColor(Color.RED); // 设置画笔的颜色为红色
			if (isFilled) {
				// 以坐标点(x,y)为左上角，绘制指定宽高的矩形区域
				g.fillRect(10, 10, getWidth() - 20, getHeight() - 20);
			} else {
				// 以坐标点(x,y)为左上角，绘制指定宽高的矩形边框
				g.drawRect(10, 10, getWidth() - 20, getHeight() - 20);
			}
		} else if (mDrawType == ROUND_RECT) {
			g.setColor(Color.GREEN); // 设置画笔的颜色为绿色
			if (isFilled) {
				// 以坐标点(x,y)为左上角，绘制指定宽高和指定圆角的圆角矩形区域
				g.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 50, 50);
			} else {
				// 以坐标点(x,y)为左上角，绘制指定宽高和指定圆角的圆角矩形边框
				g.drawRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 50, 50);
			}
		} else if (mDrawType == OVAL) {
			g.setColor(Color.BLUE); // 设置画笔的颜色为蓝色
			if (isFilled) {
				// 以坐标点(x,y)为外切矩形的左上角，绘制指定横纵半径的椭圆区域
				g.fillOval(10, 10, getWidth() - 20, getHeight() - 20);
			} else {
				// 以坐标点(x,y)为外切矩形的左上角，绘制指定横纵半径的椭圆轮廓
				g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
			}
		} else if (mDrawType == ARC) {
			g.setColor(Color.ORANGE); // 设置画笔的颜色为橙色
			if (isFilled) {
				// 以坐标点(x,y)为外切矩形的左上角，绘制指定横纵半径和指定角度的扇形
				g.fillArc(10, 10, getWidth() - 20, getHeight() - 20, 0, 90);
			} else {
				// 以坐标点(x,y)为外切矩形的左上角，绘制指定横纵半径和指定角度的圆弧
				g.drawArc(10, 10, getWidth() - 20, getHeight() - 20, 0, 90);
			}
		} else if (mDrawType == TEXT) {
			g.translate(0, 50); // 平移画笔至坐标点(x,y)
			g.setColor(Color.BLACK); // 设置画笔的颜色为黑色
			g.setFont(new Font("KaiTi", Font.BOLD, 70)); // 设置画笔的字体
			g.drawString("春天花会开", 20, 50); // 在当前位置的横纵偏移距离处绘制文本
		}
	}

	@Override
	public Dimension getPreferredSize() { // 获取控件的推荐宽高
		if (getWidth() > 0 && getHeight() > 0) { // 有指定宽高
			return new Dimension(getWidth(), getHeight()); // 返回setSize方法指定的宽高
		} else { // 未指定宽高
			return new Dimension(400, 400); // 返回默认的宽高
		}
	}
}
