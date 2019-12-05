package com.swing.senior;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

//演示表格的用法
public class TestTable {

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试表格的窗口"); // 创建一个窗口对象
		//frame.setSize(450, 280); // 必须设置宽高，否则没有窗体
		frame.setSize(450, 360); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

		// 创建表格的标题数组
		String[] heads = new String[]{"序号", "快餐名称", "快餐价格"};
		// 创建表格的内容数组
		Object[][] values = new Object[][] {
				{"1", "鱼香肉丝饭", "16"},
				{"2", "香菇滑鸡饭", "18"},
				{"3", "黑椒牛排饭", "20"},
				{"4", "梅菜扣肉饭", "17"},
				{"5", "糖醋里脊饭", "19"},
				{"6", "红烧排骨饭", "17"},
				{"7", "台式卤肉饭", "15"},
		};
		// 根据内容数组和标题数组，创建默认的表格模型
		DefaultTableModel model = new DefaultTableModel(values, heads);
		JTable table = new JTable(model); // 根据模型创建表格
//		JTable table = new JTable(); // 创建一个空表格
//		table.setModel(model); // 设置表格的模型
		Font font = new Font("中号", Font.PLAIN, 16);
		table.setFont(font); // 设置表格内容的文本字体
		table.setGridColor(Color.BLUE); // 设置网格线的颜色
//		table.setShowGrid(true); // 是否显示网格线。默认显示
//		table.setShowHorizontalLines(true); // 是否显示水平的分隔线。默认显示
//		table.setShowVerticalLines(true); // 是否显示垂直的分隔线。默认显示
		table.setRowHeight(40); // 设置每行的高度
		table.setEnabled(false); // 是否允许编辑。默认允许
		
		// 创建默认的表格单元渲染器
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		// 设置渲染器在水平方向的对齐方式。默认靠左对齐
		render.setHorizontalAlignment(JLabel.CENTER);
		// 设置渲染器在垂直方向的对齐方式。默认垂直居中
		render.setVerticalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, render); // 设置表格的默认渲染器
		
		JTableHeader header = table.getTableHeader(); // 获得表格的头部（即标题行）
		header.setFont(font); // 设置标题行的文本字体
		header.setResizingAllowed(false); // 是否允许通过拖动改变标题各列的宽度。默认允许
		header.setReorderingAllowed(false); // 是否允许通过拖动改变列与列之间的顺序。默认允许
		
		// 获得表格的列模型
//		TableColumnModel columnModel = table.getColumnModel();
//		for (int i=0; i<columnModel.getColumnCount(); i++) { // 遍历各列模型
//			TableColumn column = columnModel.getColumn(i); // 获取指定位置的列对象
//			// 设置该列的推荐宽度。只有在关闭自动调整的模式之下，设置每列的宽度才会生效
//			column.setPreferredWidth(100);
//		}
		// 因为下面添加滚动条的时候，滚动条已经关联了JTable，所以这里不必单独添加表格
		//panel.add(table); // 在面板上添加表格

		// 设置自动调整大小的模式。如需展示水平滚动条，则要设置为关闭自动调整
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// 第一种绑定方式：创建一个滚动条，在构造方法中填入表格对象
		JScrollPane scroll = new JScrollPane(table);
		// 第二种绑定方式：调用setViewportView方法设置滚动条关联的控件
		//scroll.setViewportView(table);
		// 第三种绑定方式：通过滚动条对象的视图口岸的add方法添加表格对象
		//scroll.getViewport().add(table);
		// 设置垂直滚动条的显示策略
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// 设置水平直滚动条的显示策略
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		frame.getContentPane().add(scroll); // 在窗口的内容面板上添加包含表格的滚动条
		//frame.add(scroll); // 在窗口上添加包含表格的滚动条

		frame.setVisible(true); // 必须设置为true，否则看不见
	}
	
}
