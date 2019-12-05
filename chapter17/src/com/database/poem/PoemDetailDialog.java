package com.database.poem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.Utils;
import com.database.properties.PropertiesUtil;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

//定义诗歌详情对话框
public class PoemDetailDialog {
	private Stage parent; // 当前程序的舞台
	private String id; // 诗歌记录的唯一标识
	private Label labelDetail; // 详情标签

	private static String driver_class; // 数据库的驱动类。从配置文件中读取
	private static String dbUrl; // 数据库的连接地址。从配置文件中读取
	private static String dbUserName; // 数据库的用户名。从配置文件中读取
	private static String dbPassword; // 数据库的密码。从配置文件中读取
	static {
		// 获取当前程序的本地路径。内部兼容了是否打成jar包的两种情况
		String class_path = Utils.getClassPath();
		// 以下拼接数据库配置文件db.properties的完整路径
		String config_path = String.format("%s/%s", class_path, "db.properties");
		// 根据指定的配置文件创建属性工具
		PropertiesUtil prop = new PropertiesUtil(config_path);
		driver_class = prop.readString("jdbc.connection.driver_class", ""); // 读取数据库的驱动
		dbUrl = prop.readString("jdbc.connection.url", ""); // 读取数据库的连接地址
		dbUserName = prop.readString("jdbc.connection.username", ""); // 读取数据库的用户名
		dbPassword = prop.readString("jdbc.connection.password", ""); // 读取数据库的密码
	}
	
	public PoemDetailDialog(Stage parent, String id) {
		this.parent = parent;
		this.id = id;
	}

	// 初始化界面
	private void init() {
		Stage stage = new Stage(); // 创建一个舞台
		stage.setTitle("诗歌详情"); // 设置舞台的标题
		stage.initOwner(parent); // 对话框永远在前面
		stage.initModality(Modality.WINDOW_MODAL); // 必须关闭对话框后才能操作其他的

		VBox vbox = new VBox(); // 创建一个垂直箱子
		vbox.setPrefSize(580, 420); // 设置垂直箱子的推荐宽高

		labelDetail = new Label(); // 创建一个标签
		labelDetail.setFont(Font.font("KaiTi", 30)); // 设置标签的字体
		labelDetail.setMinHeight(410); // 设置标签的最小高度
		labelDetail.setMinWidth(540); // 设置标签的最小宽度
		labelDetail.setAlignment(Pos.CENTER); // 设置标签的对齐方式
		labelDetail.setPadding(new Insets(20, 0, 0, 40)); // 设置标签的四周间距
		ScrollPane scroll = new ScrollPane(); // 创建一个滚动窗格
		scroll.setContent(labelDetail); // 把标签作为滚动窗格的内容
		scroll.prefHeightProperty().bind(vbox.heightProperty()); // 让滚动面板与垂直箱子等高
		vbox.getChildren().addAll(scroll); // 把滚动窗格添加进垂直箱子
		
		stage.setScene(new Scene(vbox)); // 设置舞台的场景
		stage.setResizable(true); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

	// 加载诗歌信息
	private void loadPoem(String id) {
		// 拼接SQL查询语句，查询指定编号的诗歌记录
		String sql = "select * from poem where id="+id;
		// try语句分别获取数据库连接、创建该连接的报告、通过报告执行查询语句
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) { // 循环遍历查询语句的结果集
				// 以下获取并组装诗歌详情，然后显示在界面上
				String detail = String.format("%s\n%s（%s）\n\n%s", 
						rs.getString("title"), rs.getString("author"),
						rs.getString("dynasty"), rs.getString("content"));
				labelDetail.setText(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 显示当前对话框
	public void show() {
		init(); // 初始化界面
		loadPoem(id); // 加载诗歌信息
	}

}
