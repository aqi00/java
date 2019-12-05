package com.javafx.widget;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//演示两种文件对话框
public class TestChooser extends Application {

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

	@Override
	public void start(Stage stage) { // 应用程序开始运行
		stage.setTitle("测试文件对话框"); // 设置舞台的标题
		BorderPane borderPane = new BorderPane(); // 创建一个边界窗格
		Scene scene = new Scene(borderPane, 420, 100, Color.WHITE); // 创建一个场景

		FlowPane flowPane = new FlowPane(); // 创建一个流式窗格
		flowPane.setAlignment(Pos.CENTER); // 设置流式窗格的对齐方式

		Label label = new Label("这里查看文件选择结果"); // 创建一个标签
		label.setPrefSize(400, 70); // 设置标签的推荐宽高
		label.setFont(Font.font("NSimSun", 16)); // 设置标签文本的字体及大小
		label.setAlignment(Pos.CENTER); // 设置标签的对齐方式
		label.setWrapText(true); // 设置标签文本是否支持自动换行。true表示支持，false表示不支持
		borderPane.setCenter(label); // 把标签放到边界窗格的中央

		Button btn1 = new Button("文件打开对话框"); // 创建一个按钮
		btn1.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		btn1.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				FileChooser chooser = new FileChooser(); // 创建一个文件对话框
				chooser.setTitle("打开文件"); // 设置文件对话框的标题
				chooser.setInitialDirectory(new File("E:\\")); // 设置文件对话框的初始目录
				// 给文件对话框添加多个文件类型的过滤器
				chooser.getExtensionFilters().addAll(
						new FileChooser.ExtensionFilter("所有文件", "*.*"),
						new FileChooser.ExtensionFilter("所有图片", "*.jpg", "*.gif", "*.bmp", "*.png"));
				// 显示文件打开对话框，且该对话框支持同时选择多个文件
				//List<File> files = chooser.showOpenMultipleDialog(stage);
				File file = chooser.showOpenDialog(stage); // 显示文件打开对话框
				if (file == null) { // 文件对象为空，表示没有选择任何文件
					label.setText("未选择任何文件");
				} else { // 文件对象非空，表示选择了某个文件
					label.setText("准备打开的文件路径是："+file.getAbsolutePath());
				}
			}
		});
		flowPane.getChildren().add(btn1); // 往流式窗格上添加按钮

		Button btn2 = new Button("文件保存对话框"); // 创建一个按钮
		btn2.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		btn2.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				FileChooser chooser = new FileChooser(); // 创建一个文件对话框
				chooser.setTitle("保存文件"); // 设置文件对话框的标题
				chooser.setInitialDirectory(new File("E:\\")); // 设置文件对话框的初始目录
				// 创建一个文件类型过滤器
				FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("文本文件(*.txt)", "*.txt");
				// 给文件对话框添加文件类型过滤器
				chooser.getExtensionFilters().add(filter);
				File file = chooser.showSaveDialog(stage); // 显示文件保存对话框
				if (file == null) { // 文件对象为空，表示没有选择任何文件
					label.setText("未选择任何文件");
				} else { // 文件对象非空，表示选择了某个文件
					label.setText("准备保存的文件路径是："+file.getAbsolutePath());
				}
			}
		});
		flowPane.getChildren().add(btn2); // 往流式窗格上添加按钮

		borderPane.setTop(flowPane); //把流式窗格放到边界窗格的上方
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

}
