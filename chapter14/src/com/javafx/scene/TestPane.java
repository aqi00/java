package com.javafx.scene;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//演示JavaFX的布局窗格
//JavaFX程序的入口类继承自Application
public class TestPane extends Application {

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

	@Override
	public void start(Stage stage) { // 应用程序开始运行
		stage.setTitle("测试各种窗格"); // 设置舞台的标题
		
		Scene scene = getFlowPane(); // 获取采用流式窗格的场景
		//Scene scene = getGridPane(); // 获取采用网格窗格的场景
		//Scene scene = getBorderPane(); // 获取采用边界窗格的场景

		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

	// 获取采用流式窗格的场景
	private static Scene getFlowPane() {
		FlowPane pane = new FlowPane(); // 创建一个流式窗格
		pane.setAlignment(Pos.CENTER_LEFT); // 设置对齐方式为靠左对齐
		// pane.setHgap(20); // 设置水平方向上的空白距离
		// pane.setVgap(50); // 设置垂直方向上的空白距离
		pane.getChildren().add(new Button("第一个按钮")); // 在窗格上添加一个按钮
		pane.getChildren().add(new Button("第二个按钮")); // 在窗格上添加一个按钮
		pane.getChildren().add(new Button("第三个按钮")); // 在窗格上添加一个按钮
		pane.getChildren().add(new Button("第四个按钮")); // 在窗格上添加一个按钮
		pane.getChildren().add(new Button("第五个按钮")); // 在窗格上添加一个按钮
		Scene scene = new Scene(pane, 400, 150); // 创建一个采用流式窗格的场景
		return scene;
	}

	// 获取采用网格窗格的场景
	private static Scene getGridPane() {
		GridPane pane = new GridPane(); // 创建一个网格窗格
		pane.setAlignment(Pos.CENTER); // 设置对齐方式为居中对齐
		pane.add(new Button("第一个按钮"), 1, 0); // 在窗格的第0行第1列添加一个按钮
		pane.add(new Button("第二个按钮"), 1, 1); // 在窗格的第1行第1列添加一个按钮
		pane.add(new Button("第三个按钮"), 1, 2); // 在窗格的第2行第1列添加一个按钮
		pane.add(new Button("第四个按钮"), 1, 3); // 在窗格的第3行第1列添加一个按钮
		pane.add(new Button("第五个按钮"), 1, 4); // 在窗格的第4行第1列添加一个按钮
		Scene scene = new Scene(pane, 400, 150); // 创建一个采用网格窗格的场景
		return scene;
	}

	// 获取采用边界窗格的场景
	private static Scene getBorderPane() {
		// 放在各方向上的节点，它们的默认对齐方式分别如下：上边: Pos.TOP_LEFT，
		// 下边: Pos.BOTTOM_LEFT，左边: Pos.TOP_LEFT，右边: Pos.TOP_RIGHT，中央: Pos.CENTER
		BorderPane pane = new BorderPane(); // 创建一个边界窗格
		pane.setTop(new Button("上方的按钮")); // 在窗格的上方添加按钮
		pane.setBottom(new Button("下方的按钮")); // 在窗格的下方添加按钮
		pane.setLeft(new Button("左边的按钮")); // 在窗格的左边添加按钮
		pane.setRight(new Button("右边的按钮")); // 在窗格的右边添加按钮
		pane.setCenter(new Button("中间的按钮")); // 在窗格的中间位置添加按钮
		Scene scene = new Scene(pane, 400, 150); // 创建一个采用边界窗格的场景
		return scene;
	}

}
