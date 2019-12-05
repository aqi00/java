package com.database.poem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.ToastUtil;
import com.database.Utils;
import com.database.properties.PropertiesUtil;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

//定义诗歌编辑对话框
public class PoemEditDialog {
	private Stage parent; // 当前程序的舞台
	private String id; // 诗歌记录的唯一标识
	private String title; // 窗口标题
	private TextField fieldTitle; // 诗歌标题的输入框
	private TextField fieldAuthor; // 诗歌作者的输入框
	private TextField fieldDynasty; // 诗歌朝代的输入框
	private TextArea fieldContent; // 诗歌内容的输入框

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
	
	public PoemEditDialog(Stage parent, String title, String id) {
		this.parent = parent;
		this.title = title;
		this.id = id;
	}
	
	// 初始化界面
	private void init() {
		Stage stage = new Stage(); // 创建一个舞台
		stage.setTitle(title); // 设置舞台的标题
		stage.initOwner(parent); // 对话框永远在前面
		stage.initModality(Modality.WINDOW_MODAL); // 必须关闭对话框后才能操作其他的

		VBox vbox = new VBox(); // 创建一个垂直箱子
		vbox.setPrefSize(580, 400); // 设置垂直箱子的推荐宽高
		vbox.setPadding(new Insets(10, 10, 10, 10)); // 设置垂直箱子的四周间距
		
		HBox hTitle = new HBox(); // 创建一个水平箱子
		hTitle.setPadding(new Insets(0, 0, 10, 0)); // 设置水平箱子的四周间距
		Label labelTitle = new Label("请输入诗歌标题："); // 创建一个标签
		labelTitle.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		labelTitle.setPrefSize(180, 50); // 设置标签的推荐宽高
		fieldTitle = new TextField(); // 创建一个输入框
		fieldTitle.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		fieldTitle.setPrefSize(400, 50); // 设置输入框的推荐宽高
		hTitle.getChildren().addAll(labelTitle, fieldTitle); // 把标签和输入框一起添加进水平箱子

		HBox hAuthor = new HBox(); // 创建一个水平箱子
		hAuthor.setPadding(new Insets(0, 0, 10, 0)); // 设置水平箱子的四周间距
		Label labelAuthor = new Label("请输入诗歌作者："); // 创建一个标签
		labelAuthor.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		labelAuthor.setPrefSize(180, 50); // 设置标签的推荐宽高
		fieldAuthor = new TextField(); // 创建一个输入框
		fieldAuthor.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		fieldAuthor.setPrefSize(400, 50); // 设置输入框的推荐宽高
		hAuthor.getChildren().addAll(labelAuthor, fieldAuthor); // 把标签和输入框一起添加进水平箱子

		HBox hDynasty = new HBox(); // 创建一个水平箱子
		hDynasty.setPadding(new Insets(0, 0, 10, 0)); // 设置水平箱子的四周间距
		Label labelDynasty = new Label("请输入诗歌朝代："); // 创建一个标签
		labelDynasty.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		labelDynasty.setPrefSize(180, 50); // 设置标签的推荐宽高
		fieldDynasty = new TextField(); // 创建一个输入框
		fieldDynasty.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		fieldDynasty.setPrefSize(400, 50); // 设置输入框的推荐宽高
		hDynasty.getChildren().addAll(labelDynasty, fieldDynasty); // 把标签和输入框一起添加进水平箱子

		HBox hContent = new HBox(); // 创建一个水平箱子
		hContent.setPadding(new Insets(0, 0, 10, 0)); // 设置水平箱子的四周间距
		Label labelContent = new Label("请输入诗歌内容："); // 创建一个标签
		labelContent.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		labelContent.setPrefSize(180, 50); // 设置标签的推荐宽高
		fieldContent = new TextArea(); // 创建一个输入框
		fieldContent.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		fieldContent.setPrefSize(400, 210); // 设置输入框的推荐宽高
		hContent.getChildren().addAll(labelContent, fieldContent); // 把标签和输入框一起添加进水平箱子

		HBox hAdd = new HBox(); // 创建一个水平箱子
		Button btnAdd = new Button("保存"); // 创建一个按钮
		btnAdd.setFont(Font.font("NSimSun", 20)); // 设置按钮的字体
		btnAdd.setPrefSize(580, 50); // 设置按钮的推荐宽高
		btnAdd.setOnAction(e -> savePoem(id)); // 设置按钮的单击事件
		hAdd.getChildren().addAll(btnAdd); // 把按钮添加进水平箱子
		
		vbox.getChildren().addAll(hTitle, hAuthor, hDynasty, hContent, hAdd); // 把各行的水平箱子一起添加进垂直箱子
		
		stage.setScene(new Scene(vbox)); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}
	
	// 加载诗歌信息
	private void loadPoem(String id) {
		// 拼接SQL查询语句，从当前数据库中查出所有表格
		String sql = "select * from poem where id="+id;
		// try语句分别获取数据库连接、创建该连接的报告、通过报告执行查询语句
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) { // 循环遍历查询语句的结果集
				fieldTitle.setText(rs.getString("title")); // 获取并显示诗歌标题
				fieldAuthor.setText(rs.getString("author")); // 获取并显示诗歌作者
				fieldDynasty.setText(rs.getString("dynasty")); // 获取并显示诗歌朝代
				fieldContent.setText(rs.getString("content")); // 获取并显示诗歌内容
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 保存诗歌记录（支持插入操作与更新操作）
	private void savePoem(String id) {
		// 拼接记录插入语句
		String insertSQL = String.format("insert into poem(title, author, dynasty, content)"
				+ " values('%s', '%s', '%s', '%s')",
				fieldTitle.getText(), fieldAuthor.getText(), fieldDynasty.getText(), fieldContent.getText());
		// 拼接记录更新语句
		String updateSQL = String.format("update poem set title='%s', author='%s',"
				+ " dynasty='%s', content='%s' where id=?",
				fieldTitle.getText(), fieldAuthor.getText(), fieldDynasty.getText(), fieldContent.getText());
		// try语句分别获取数据库连接、创建该连接的报告（用于插入）、创建预报告（用于更新）
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
			if (id==null || id.equals("")) { // 编号为空，表示这是一条新记录
				stmt.executeUpdate(insertSQL); // 执行插入语句
			} else { // 编号非空，表示这是一条已有的记录
				pstmt.setString(1, id); // 给查询条件传入诗歌编号
				pstmt.executeUpdate(); // 执行更新语句
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ToastUtil.show(String.format("已保存%s的《%s》", fieldAuthor.getText(), fieldTitle.getText()));
	}

	// 显示当前对话框
	public void show() {
		init(); // 初始化界面
		if (id!=null && id.length() > 0) {
			loadPoem(id); // 加载诗歌信息
		}
	}

}
