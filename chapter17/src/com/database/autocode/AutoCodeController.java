package com.database.autocode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.database.ToastUtil;
import com.database.Utils;
import com.database.properties.PropertiesUtil;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//代码生成工具的界面控制器
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AutoCodeController implements Initializable {
	@FXML private FlowPane fpRoot; // 根布局
	@FXML private Label labelDatabase; // 数据库加载的标签
	@FXML private ComboBox<String> dbTable; // 数据库表的下拉框
	@FXML private Button btnExport; // 导出源码按钮
	@FXML private TableView<Column> tableInfo; // 各列信息的表格

//	private static String driver_class = "com.mysql.cj.jdbc.Driver"; // 数据库的驱动类
//	private static String dbUrl = "jdbc:mysql://localhost:3306/study?serverTimezone=GMT%2B8"; // 数据库的连接地址
//	private static String dbUserName = "root"; // 数据库的用户名
//	private static String dbPassword = "222@@@wwwWWW"; // 数据库的密码
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
		try {
			Class.forName(driver_class); // 加载指定的数据库驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { // 界面打开后的初始化操作
		labelDatabase.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		dbTable.setStyle("-fx-font: 20 \"NSimSun\";"); // 设置下拉框的字体
		btnExport.setFont(Font.font("NSimSun", 20)); // 设置按钮的字体
		tableInfo.setStyle("-fx-font: 20 \"NSimSun\";"); // 设置表格视图的字体
		// 设置下拉框的选择监听器
		dbTable.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					// 选择某张表之后的操作，主要是查询该表的所有字段，并在表格视图中显示各字段信息
					choose(dbTable.getSelectionModel().getSelectedIndex());
				});
		btnExport.setOnAction(e -> openFileDialog()); // 设置按钮的单击事件
		initTable(); // 初始化表格视图
		initComboBox(); // 初始化下拉框
	}
	
	// 初始化表格视图
	private void initTable() {
		TableColumn firstColumn = new TableColumn("字段名称"); // 创建一个表格列
		firstColumn.setMinWidth(140); // 设置列的最小宽度
		// 设置该列取值对应的属性名称。此处名称列要展示Column元素的name属性值
		firstColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn secondColumn = new TableColumn("字段类型"); // 创建一个表格列
		secondColumn.setMinWidth(140); // 设置列的最小宽度
		// 设置该列取值对应的属性名称。此处类型列要展示Column元素的type属性值
		secondColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		TableColumn thirdColumn = new TableColumn("字段说明"); // 创建一个表格列
		thirdColumn.setMinWidth(218); // 设置列的最小宽度
		// 设置该列取值对应的属性名称。此处说明列要展示Column元素的comment属性值
		thirdColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
		// 把几个标题列一齐添加到表格视图
		tableInfo.getColumns().addAll(firstColumn, secondColumn, thirdColumn);
	}
	
	private String mDatabaseName; // 数据库名称
	private String mTableName; // 当前表的名称
	private String mTableDesc; // 当前表的说明
	private List<Column> mColumnList = new ArrayList<Column>(); // 表的字段清单
	
	// 初始化下拉框
	private void initComboBox() {
		List<String> tableList = new ArrayList<String>(); // 声明一个表格清单
		// 拼接SQL查询语句，从当前数据库中查出所有表格
		String sql = "select * from information_schema.tables where table_schema = '%s'";
		// try语句分别获取数据库连接、创建该连接的报告、通过报告执行查询语句
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(String.format(sql, conn.getCatalog()))) {
			mDatabaseName = conn.getCatalog(); // 获取当前连接的数据库名称
			labelDatabase.setText("已连上数据库"+mDatabaseName+"，请选择要导出源码的表格");
			while (rs.next()) { // 循环遍历查询语句的结果集
				String table_name = rs.getString("table_name"); // 获取table_name字段的值
				String table_comment = rs.getString("table_comment"); // 获取table_comment字段的值
				String tableItem = String.format("%s(%s)", table_name, table_comment);
				tableList.add(tableItem); // 把查到的表格信息添加到表格清单
				//System.out.println("table_name="+table_name+", table_comment="+table_comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 把清单对象转换为JavaFX控件能够识别的数据对象
		ObservableList<String> obList = FXCollections.observableArrayList(tableList);
		dbTable.setItems(obList); // 设置下拉框的数据来源
		dbTable.getSelectionModel().select(0); // 设置下拉框默认选中第1项
	}
	
	// 选择某张表之后的操作，主要是查询该表的所有字段，并在表格视图中显示各字段信息
	private void choose(int seq) {
		mColumnList.clear(); // 清空字段清单
		// 获取当前选中数据表的完整名称（含名称与说明）
		String fullName = dbTable.getSelectionModel().getSelectedItem().toString();
		mTableName = fullName.substring(0, fullName.indexOf("("));
		mTableDesc = fullName.substring(fullName.indexOf("(")+1, fullName.indexOf(")"));
		System.out.println("mTableName="+mTableName+", mTableDesc="+mTableDesc);
		// 拼接SQL查询语句，查询指定表格的所有字段信息
		String sql = "select column_name,data_type,column_comment from information_schema.columns "
				+ "where table_schema = '%s' and table_name = '%s' order by ordinal_position asc";
		// try语句分别获取数据库连接、创建该连接的报告、通过报告执行查询语句
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(String.format(sql, conn.getCatalog(), mTableName))) {
			while (rs.next()) { // 循环遍历查询语句的结果集
				String name = rs.getString("column_name").toLowerCase(); // 获取字段名称
				String type = rs.getString("data_type").toLowerCase(); // 获取字段类型
				String comment = rs.getString("column_comment"); // 获取字段说明
				mColumnList.add(new Column(name, type, comment)); // 把字段信息添加到字段清单
				//System.out.println("name="+name+", type="+data_type+", comment="+comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 把清单对象转换为JavaFX控件能够识别的数据对象
		ObservableList<Column> obList = FXCollections.observableArrayList(mColumnList);
		tableInfo.setItems(obList); // 设置表格视图的数据来源
	}

	// 打开文件保存对话框
	private void openFileDialog() {
		FileChooser chooser = new FileChooser(); // 创建一个文件对话框
		chooser.setTitle("保存文件"); // 设置文件对话框的标题
		chooser.setInitialDirectory(new File("E:\\")); // 设置文件对话框的初始目录
		// 创建一个文件类型过滤器
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JAVA代码(*.java)", "*.java");
		// 给文件对话框添加文件类型过滤器
		chooser.getExtensionFilters().add(filter);
		Stage stage = (Stage) fpRoot.getScene().getWindow();
		File file = chooser.showSaveDialog(stage); // 显示文件保存对话框
		if (file == null) { // 文件对象为空，表示没有选择任何文件
			ToastUtil.show("未选择任何文件");
		} else { // 文件对象非空，表示选择了某个文件
			generateCodeFile(file); // 生成代码文件
			ToastUtil.show("已生成JAVA代码，路径为："+file.getAbsolutePath());
		}
	}

	// 生成代码文件
	private void generateCodeFile(File file) {
		String begin = String.format("\nimport java.util.Date;\n\n//%s\npublic class %s {\n", 
				mTableDesc, convertCase(mTableName));
		String end = "\n}\n";
		// 根据指定文件路径构建文件输出流对象，然后据此构建缓存输出流对象
		try (FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			bos.write(begin.getBytes()); // 把字节数组写入缓存输出流
			for (Column column : mColumnList) { // 遍历字段清单，拼接实体类的属性代码
				String field = String.format("	private %s %s; // %s\n", 
						getFieldType(column.getType()), column.getName(), column.getComment());
				bos.write(field.getBytes()); // 把字节数组写入缓存输出流
			}
			for (Column column : mColumnList) { // 遍历字段清单，拼接实体类的方法代码
				String method = String.format("\n	public void set%1$s(%2$s %3$s) {\n		this.%3$s = %3$s;\n	}"
						+ "\n	public %2$s get%1$s() {\n		return this.%3$s;\n	}", 
						convertCase(column.getName()), getFieldType(column.getType()), column.getName());
				bos.write(method.getBytes()); // 把字节数组写入缓存输出流
			}
			bos.write(end.getBytes()); // 把字节数组写入缓存输出流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 把字符串的首字母转为大写
	private String convertCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	// 把数据库的字段类型转换为Java的变量类型
	private String getFieldType(String data_type) {
		String field_type = data_type; // 数据库与Java的整型刚好都叫int
		if (field_type.contains("date")) { // 数据库的日期类型
			field_type = "Date"; // Java的日期类型
		} else if (field_type.contains("varchar")) { // 数据库的变长字符类型
			field_type = "String"; // Java的字符串类型
		}
		return field_type;
	}
	
}
