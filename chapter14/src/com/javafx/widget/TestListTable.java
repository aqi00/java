package com.javafx.widget;

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//演示列表与表格
public class TestListTable extends Application {
	private Font font = Font.font("NSimSun", 16); // 创建一个字体对象

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

	@Override
	public void start(Stage stage) { // 应用程序开始运行
		stage.setTitle("测试列表与表格"); // 设置舞台的标题
		BorderPane borderPane = new BorderPane(); // 创建一个边界窗格
		Scene scene = new Scene(borderPane, 400, 250, Color.WHITE); // 创建一个场景

		FlowPane flowPane = new FlowPane(); // 创建一个流式窗格
		flowPane.setAlignment(Pos.CENTER); // 设置流式窗格的对齐方式

		Button btn1 = new Button("显示列表"); // 创建一个按钮
		btn1.setFont(font); // 设置按钮的字体
		btn1.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				showListView(borderPane);// 显示列表视图
			}
		});
		flowPane.getChildren().add(btn1); // 往流式窗格上添加按钮

		Button btn2 = new Button("显示表格"); // 创建一个按钮
		btn2.setFont(font); // 设置按钮的字体
		btn2.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				showTableView(borderPane); // 显示表格视图
			}
		});
		flowPane.getChildren().add(btn2); // 往流式窗格上添加按钮

		borderPane.setTop(flowPane); //把流式窗格放到边界窗格的上方
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

	// 显示列表视图
	private void showListView(BorderPane borderPane) {
		VBox vbox = new VBox(); // 创建一个垂直箱子
		vbox.setAlignment(Pos.CENTER); // 设置垂直箱子的对齐方式
		
		// 初始化快餐列表
		List<String> snackList = Arrays.asList("鱼香肉丝饭", "香菇滑鸡饭", "黑椒牛排饭",
				"梅菜扣肉饭", "糖醋里脊饭", "红烧排骨饭", "台式卤肉饭");
		// 把清单对象转换为JavaFX控件能够识别的数据对象
		ObservableList<String> obList = FXCollections.observableArrayList(snackList);
		ListView<String> listView = new ListView<String>(obList); // 依据指定数据创建列表视图
		//listView.setItems(obList); // 设置列表视图的数据来源
		listView.setPrefSize(400, 180); // 设置列表视图的推荐宽高
		// 设置列表视图的字体
		listView.setStyle(String.format("-fx-font: %f \"%s\";", font.getSize(), font.getFamily()));
		Label label = new Label("这里查看点餐结果"); // 创建一个标签
		label.setPrefSize(300, 40); // 设置标签的推荐宽高
		label.setFont(font); // 设置标签的字体
		label.setAlignment(Pos.CENTER); // 设置标签的对齐方式
		label.setWrapText(true); // 设置标签文本是否支持自动换行
		vbox.getChildren().addAll(listView, label); // 把列表和标签一起加到垂直箱子上
		
		// 设置列表视图的选择监听器
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String old_str, String new_str) {
				// getSelectedIndex方法获得选中项的序号，getSelectedItem方法获得选中项的对象
				String desc = String.format("您点了第%d项，快餐名称是%s", 
						listView.getSelectionModel().getSelectedIndex(), 
						listView.getSelectionModel().getSelectedItem().toString());
				label.setText(desc); // 在标签上显示当前选中的文本项
			}
		});
		borderPane.setCenter(vbox); // 把垂直箱子放到边界窗格的中央
	}

	// 显示表格视图
	private void showTableView(BorderPane borderPane) {
		VBox vbox = new VBox(); // 创建一个垂直箱子
		vbox.setAlignment(Pos.CENTER); // 设置垂直箱子的对齐方式

		// 创建表格的内容清单
		List<Snack> snackList = Arrays.asList(
				new Snack("1", "鱼香肉丝饭", "16"),
				new Snack("2", "香菇滑鸡饭", "18"),
				new Snack("3", "黑椒牛排饭", "20"),
				new Snack("4", "梅菜扣肉饭", "17"),
				new Snack("5", "糖醋里脊饭", "19"),
				new Snack("6", "红烧排骨饭", "17"),
				new Snack("7", "台式卤肉饭", "15"));
		// 把清单对象转换为JavaFX控件能够识别的数据对象
		ObservableList<Snack> obList = FXCollections.observableArrayList(snackList);
		TableView<Snack> tableView = new TableView<Snack>(obList); // 依据指定数据创建表格视图
		//tableView.setItems(obList); // 设置表格视图的数据来源
		tableView.setPrefSize(400, 210); // 设置表格视图的推荐宽高
		// 设置表格视图的字体
		tableView.setStyle(String.format("-fx-font: %f \"%s\";", font.getSize(), font.getFamily()));
		
		TableColumn firstColumn = new TableColumn("序号"); // 创建一个表格列
		firstColumn.setMinWidth(100); // 设置列的最小宽度
		// 设置该列取值对应的属性名称。此处序号列要展示Snack元素的xuhao属性值
		firstColumn.setCellValueFactory(new PropertyValueFactory<>("xuhao"));
		TableColumn secondColumn = new TableColumn("快餐名称"); // 创建一个表格列
		secondColumn.setMinWidth(200); // 设置列的最小宽度
		// 设置该列取值对应的属性名称。此处名称列要展示Snack元素的name属性值
		secondColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn thirdColumn = new TableColumn("快餐价格"); // 创建一个表格列
		thirdColumn.setMinWidth(110); // 设置列的最小宽度
		// 设置该列取值对应的属性名称。此处价格列要展示Snack元素的price属性值
		thirdColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		// 把几个标题列一齐添加到表格视图
		tableView.getColumns().addAll(firstColumn, secondColumn, thirdColumn);

		vbox.getChildren().add(tableView); // 把表格加到垂直箱子上
		borderPane.setCenter(vbox); // 把垂直箱子放到边界窗格的中央
	}
	
}
