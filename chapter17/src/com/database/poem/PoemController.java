package com.database.poem;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.database.ToastUtil;
import com.database.Utils;
import com.database.properties.PropertiesUtil;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

//代码生成工具的界面控制器
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PoemController implements Initializable {
	@FXML private VBox vbRoot; // 根布局
	@FXML private Label labelTitle; // 标题的标签
	@FXML private TextField fieldTitle; // 标题的输入框
	@FXML private Label labelAuthor; // 作者的标签
	@FXML private TextField fieldAuthor; // 作者的输入框
	@FXML private Label labelDynasty; // 朝代的标签
	@FXML private ComboBox<String> dbDynasty; // 朝代的下拉框
	@FXML private Label labelContent; // 内容的标签
	@FXML private TextField fieldContent; // 内容的输入框
	@FXML private Button btnSearch; // 搜索按钮
	@FXML private Button btnAdd; // 增加按钮
	@FXML private Label labelCount; // 搜索结果的标签
	@FXML private TableView<PoemColumn> tablePoem; // 诗歌信息的表格

	private Stage stage; // 当前程序的舞台
	private List<PoemColumn> mPoemList = new ArrayList<PoemColumn>(); // 表格的诗歌清单

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
		labelTitle.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		fieldTitle.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		labelAuthor.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		fieldAuthor.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		labelDynasty.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		dbDynasty.setStyle("-fx-font: 20 \"NSimSun\";"); // 设置下拉框的字体
		labelContent.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		fieldContent.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		btnSearch.setFont(Font.font("NSimSun", 20)); // 设置按钮的字体
		btnAdd.setFont(Font.font("NSimSun", 20)); // 设置按钮的字体
		labelCount.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		tablePoem.setStyle("-fx-font: 20 \"NSimSun\";"); // 设置表格视图的字体
		btnSearch.setOnAction(e -> searchPoem()); // 设置按钮的单击事件
		btnAdd.setOnAction(e -> showEditDailog("增加诗歌", "")); // 设置按钮的单击事件
		Timer timer = new Timer(); // 创建一个定时器
		// 延迟50毫秒后启动定时任务，避免导入操作耗时太久致使界面出不来
		timer.schedule(new ImportTask(), 50);
	}

	// 定义一个初始化诗歌导入的定时任务
	private class ImportTask extends TimerTask {
		@Override
		public void run() {
			// 定义一个JavaFX任务，任务的call方法不能操作界面，succeeded方法才能操作界面
			Task task = new Task<Void>() {
				// call方法里面的线程非主线程，不能操作界面
				protected Void call() throws Exception {
					importPoem(); // 导入诗歌的初始记录
					return null;
				}

				// succeeded方法里面的线程是主线程，可以操作界面
				protected void succeeded() {
					super.succeeded();
					initComboBox(); // 初始化下拉框
					initTable(); // 初始化表格视图
					searchPoem(); // 执行查询操作
				}
			};
			task.run(); // 启动JavaFX任务
		}
	}
	
	// 显示诗歌编辑对话框
	private void showEditDailog(String hint, String id) {
		stage = (Stage) vbRoot.getScene().getWindow(); // 获得当前布局所在的舞台
		PoemEditDialog dialog = new PoemEditDialog(stage, hint, id); // 创建诗歌编辑对话框
		dialog.show(); // 显示对话框
	}
	
	// 导入诗歌的初始记录
	private void importPoem() {
		// try语句分别获取数据库连接、创建该连接的报告、通过报告执行查询语句
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement()) {
			String createSQL = "create table poem ("
					+ "	id INT NOT NULL AUTO_INCREMENT comment '序号',"
					+ "	title VARCHAR(64) NOT NULL comment '标题',"
					+ "	author VARCHAR(16) NOT NULL comment '作者',"
					+ "	dynasty VARCHAR(18) NOT NULL comment '朝代',"
					+ "	content VARCHAR(512) NOT NULL comment '内容',"
					+ "	PRIMARY KEY (id))"
					+ "comment = '古代诗歌表'";
			stmt.executeUpdate(createSQL); // 执行处理语句
			// 获取当前程序的本地路径。内部兼容了是否打成jar包的两种情况
			String class_path = Utils.getClassPath();
			// 以下拼接诗歌选集poem.txt的完整路径
			String file_path = String.format("%s/%s", class_path, "poem.txt");
			// 根据指定路径构建文件输入流对象
			try (FileInputStream fis = new FileInputStream(file_path)) {
				// 分配长度为文件大小的字节数组。available方法返回当前位置后面的剩余部分大小
				byte[] bytes = new byte[fis.available()];
				fis.read(bytes); // 从文件输入流中读取字节数组
				String content = new String(bytes); // 把字节数组转换为字符串
				String[] lines = content.split("\n"); // 每行都是一首诗歌
				for (String line : lines) {
					String[] items = line.split("\\|"); // 标题、作者、朝代、内容等字段以竖线分隔
					if (items.length >= 4) { // 四个字段都齐全的时候才插表
						String insertSQL = String.format("insert into poem(title,author,dynasty,content)"
								+ " values('%s','%s','%s','%s')", 
								items[0], items[1], items[2], items[3]);
						stmt.executeUpdate(insertSQL); // 执行处理语句
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 初始化表格视图
	private void initTable() {
		// 把几个标题列一齐添加到表格视图
		tablePoem.getColumns().addAll(
				getInfoColumn("标题", "title", 350), 
				getInfoColumn("作者", "author", 100), 
				getInfoColumn("标题", "dynasty", 100), 
				getOperationColumn("编辑", 100), 
				getOperationColumn("删除", 100));
		// 设置表格列的伸展策略。CONSTRAINED_RESIZE_POLICY表示跟随表格自动伸展
		tablePoem.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		// 设置表格的选择监听器
		tablePoem.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends PoemColumn> observable, PoemColumn oldValue, PoemColumn newValue) -> {
					// 显示诗歌详情对话框
					showPoemDetail(tablePoem.getSelectionModel().getSelectedIndex());
				});
	}
	
	// 显示诗歌详情对话框
	private void showPoemDetail(int index) {
		String id = tablePoem.getItems().get(index).getId(); // 获取当前诗歌的编号
		stage = (Stage) vbRoot.getScene().getWindow(); // 获得当前布局所在的舞台
		PoemDetailDialog dialog = new PoemDetailDialog(stage, id); // 创建诗歌详情对话框
		dialog.show(); // 显示对话框
	}
	
	// 初始化朝代下拉框
	private void initComboBox() {
		int totalCount = 0;
		LinkedList<String> dynastyList = new LinkedList<String>(); // 声明一个朝代队列
		// 拼接SQL查询语句，从当前数据库中查出所有朝代及其诗歌数量
		String sql = "select dynasty,count(*) count from poem group by dynasty";
		// try语句分别获取数据库连接、创建该连接的报告、通过报告执行查询语句
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) { // 循环遍历查询语句的结果集
				String dynasty = rs.getString("dynasty"); // 获取朝代
				int count = rs.getInt("count"); // 获取该朝代的诗歌数量
				String dynastyItem = String.format("%s(%d首)", dynasty, count);
				dynastyList.add(dynastyItem); // 把查到的朝代信息添加到朝代队列
				totalCount += count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String dynastyItem = String.format("%s(%d首)", "所有", totalCount);
		dynastyList.addFirst(dynastyItem); // 把“所有”这项添加到队列开头
		// 把清单对象转换为JavaFX控件能够识别的数据对象
		ObservableList<String> obList = FXCollections.observableArrayList(dynastyList);
		dbDynasty.setItems(obList); // 设置下拉框的数据来源
		dbDynasty.getSelectionModel().select(0); // 设置下拉框默认选中第1项
	}
	
	// 查询符合条件的诗歌清单
	private void searchPoem() {
		mPoemList.clear(); // 清空诗歌清单
		String titleText = fieldTitle.getText(); // 获取标题条件
		String authorText = fieldAuthor.getText(); // 获取作者条件
		String contentText = fieldContent.getText(); // 获取内容条件
		// 获取查询条件的朝代名称
		String fullDynasty = dbDynasty.getSelectionModel().getSelectedItem().toString();
		String dynastyText = fullDynasty.split("\\(")[0];
		if (dynastyText.equals("所有")) { // 查询范围包括所有朝代，等同不判断朝代
			dynastyText = "";
		}
		// 拼接SQL查询语句，查询指定表格的所有字段信息
		String sql = "select * from poem where 1=1";
		if (titleText.length() > 0) { // 查询条件中包含诗歌标题。%%经过格式化转义后为%
			sql = String.format("%s and title like '%%%s%%'", sql, titleText);
		}
		if (authorText.length() > 0) { // 查询条件中包含诗歌作者
			sql = String.format("%s and author='%s'", sql, authorText);
		}
		if (dynastyText.length() > 0) { // 查询条件中包含诗歌朝代
			sql = String.format("%s and dynasty='%s'", sql, dynastyText);
		}
		if (contentText.length() > 0) { // 查询条件中包含诗歌内容。%%经过格式化转义后为%
			sql = String.format("%s and content like '%%%s%%'", sql, contentText);
		}
		// try语句分别获取数据库连接、创建该连接的报告、通过报告执行查询语句
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) { // 循环遍历查询语句的结果集
				String id = rs.getString("id"); // 获取编号
				String title = rs.getString("title"); // 获取标题
				String author = rs.getString("author"); // 获取作者
				String dynasty = rs.getString("dynasty"); // 获取朝代
				mPoemList.add(new PoemColumn(id, title, author, dynasty)); // 把诗歌添加到诗歌清单
				//System.out.println("title="+title+", author="+author+", dynasty="+dynasty);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		labelCount.setText(String.format("找到%d首诗歌", mPoemList.size()));
		// 把清单对象转换为JavaFX控件能够识别的数据对象
		ObservableList<PoemColumn> obList = FXCollections.observableArrayList(mPoemList);
		tablePoem.setItems(obList); // 设置表格视图的数据来源
		tablePoem.refresh(); // 刷新表格视图
		tablePoem.scrollTo(0); // 滚动到第一行
	}
	
	// 获取文本信息的表格列
	private TableColumn getInfoColumn(String text, String property, int width) {
		TableColumn column = new TableColumn(text); // 创建一个表格列
		column.setMinWidth(width); // 设置列的最小宽度
		column.setStyle("-fx-alignment: center-left;");
		// 设置该列取值对应的属性名称
		column.setCellValueFactory(new PropertyValueFactory<>(property));
		return column;
	}
	
	// 获取变更操作的表格列
	private TableColumn getOperationColumn(String text, int width) {
		TableColumn<PoemColumn, Boolean> column = new TableColumn<PoemColumn, Boolean>(text); // 创建一个表格列
		column.setMinWidth(width); // 设置列的最小宽度
		// 设置单元值的工厂
		column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PoemColumn, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PoemColumn, Boolean> features) {
						return new SimpleBooleanProperty(features.getValue() != null);
					}
				});
		// 设置单元的工厂
		column.setCellFactory(new Callback<TableColumn<PoemColumn, Boolean>, TableCell<PoemColumn, Boolean>>() {
					@Override
					public TableCell<PoemColumn, Boolean> call(TableColumn<PoemColumn, Boolean> personBooleanTableColumn) {
						return new OperationCell(tablePoem, text);
					}
				});
		return column;
	}

	// 定义变更操作的单元类
	private class OperationCell extends TableCell<PoemColumn, Boolean> {
		final Button button = new Button();
		final StackPane paddedButton = new StackPane();

		OperationCell(final TableView table, String text) {
			button.setText(text);
			paddedButton.setPadding(new Insets(3));
			paddedButton.getChildren().add(button);
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					if (text.equals("编辑")) { // 单击了编辑按钮
						editPoem(getTableRow().getIndex()); // 执行编辑操作
					} else { // 单击了删除按钮
						showConfirmDialog(getTableRow().getIndex()); // 显示确认对话框
					}
				}
			});
		}

		@Override
		protected void updateItem(Boolean item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				setGraphic(paddedButton);
			} else {
				setGraphic(null);
			}
		}
	}
	
	// 执行编辑操作
	private void editPoem(int index) {
		String id = tablePoem.getItems().get(index).getId(); // 获取当前诗歌的编号
		showEditDailog("编辑诗歌", id); // 显示诗歌编辑对话框
	}
	
	// 显示确认对话框
	private void showConfirmDialog(int index) {
		PoemColumn poem = tablePoem.getItems().get(index);
		String contentText = String.format("是否要删除%s的《%s》？", poem.getAuthor(), poem.getTitle());
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // 创建一个确认对话框
		alert.setTitle("请确认"); // 设置对话框的标题
		// 设置对话框的内容文本
		alert.setContentText(contentText);
		// 显示对话框，并等待按钮返回
		Optional<ButtonType> buttonType = alert.showAndWait();
		//System.out.println("getButtonData="+buttonType.get().getButtonData().toString());
		// 判断返回的按钮类型是确定还是取消，再据此分别进一步处理
		if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) { // 单击了确定按钮OK_DONE
			deletePoem(index); // 执行删除操作
		} else { // 单击了取消按钮CANCEL_CLOSE
		}
	}
	
	// 执行删除操作
	private void deletePoem(int index) {
		PoemColumn poem = tablePoem.getItems().get(index);
		String sql = "delete from poem where id=?";
		// try语句分别获取数据库连接、创建该连接的报告、通过报告执行查询语句
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, poem.getId()); // 给查询条件传入诗歌编号
			stmt.executeUpdate(); // 执行删除语句
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ToastUtil.show(String.format("已删除%s的《%s》", poem.getAuthor(), poem.getTitle()));
		tablePoem.getItems().remove(index); // 移除被删除的诗歌记录
		tablePoem.refresh(); // 刷新表格视图
	}

}
