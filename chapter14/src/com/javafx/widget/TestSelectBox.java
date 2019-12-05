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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//演示几种选择框
public class TestSelectBox extends Application {
	private Font font = Font.font("NSimSun", 16); // 创建一个字体对象

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

	@Override
	public void start(Stage stage) { // 应用程序开始运行
		stage.setTitle("测试各类选择框"); // 设置舞台的标题
		BorderPane borderPane = new BorderPane(); // 创建一个边界窗格
		Scene scene = new Scene(borderPane, 400, 150, Color.WHITE); // 创建一个场景

		FlowPane flowPane = new FlowPane(); // 创建一个流式窗格
		flowPane.setAlignment(Pos.CENTER); // 设置流式窗格的对齐方式

		Button btn1 = new Button("复选框"); // 创建一个按钮
		btn1.setFont(font); // 设置按钮的字体
		btn1.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				getCheckBox(borderPane);// 获取复选框的界面
			}
		});
		flowPane.getChildren().add(btn1); // 往流式窗格上添加按钮

		Button btn2 = new Button("单选按钮"); // 创建一个按钮
		btn2.setFont(font); // 设置按钮的字体
		btn2.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				getRadioButton(borderPane); // 获取单选按钮的界面
			}
		});
		flowPane.getChildren().add(btn2); // 往流式窗格上添加按钮

		Button btn3 = new Button("下拉框"); // 创建一个按钮
		btn3.setFont(font); // 设置按钮的字体
		btn3.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				getComboBox(borderPane);// 获取下拉框的界面
			}
		});
		flowPane.getChildren().add(btn3); // 往流式窗格上添加按钮

		borderPane.setTop(flowPane); //把流式窗格放到边界窗格的上方
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}
	
	// 获取复选框的界面
	private void getCheckBox(BorderPane borderPane) {
		VBox vbox = new VBox(); // 创建一个垂直箱子
		vbox.setAlignment(Pos.CENTER); // 设置垂直箱子的对齐方式
		HBox hbox = new HBox(); // 创建一个水平箱子
		hbox.setAlignment(Pos.CENTER); // 设置水平箱子的对齐方式

		CheckBox ck1 = new CheckBox("麻婆豆腐"); // 创建一个复选框
		ck1.setFont(font); // 设置复选框的文本字体及其大小
		CheckBox ck3 = new CheckBox("清蒸桂花鱼"); // 创建一个复选框
		ck3.setFont(font); // 设置复选框的文本字体及其大小
		CheckBox ck2 = new CheckBox("香辣小龙虾"); // 创建一个复选框
		ck2.setFont(font); // 设置复选框的文本字体及其大小
		hbox.getChildren().addAll(ck1, ck2, ck3); // 把三个复选框一起加到水平箱子上
		CheckBox[] boxArray = new CheckBox[]{ck1, ck2, ck3}; // 构建复选框数组
		
		Label label = new Label("这里查看菜单详情"); // 创建一个标签
		label.setPrefSize(300, 60); // 设置标签的推荐宽高
		label.setFont(font); // 设置标签的字体
		label.setAlignment(Pos.CENTER); // 设置标签的对齐方式
		label.setWrapText(true); // 设置标签文本是否支持自动换行。true表示支持，false表示不支持
		vbox.getChildren().addAll(hbox, label); // 把水平箱子和标签一起加到垂直箱子上

		ck1.selectedProperty().addListener(new ChangeListener<Boolean>() { // 设置复选框的勾选监听器
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				// 拼接并显示当前的勾选结果，以及已经勾选的菜肴
				label.setText(String.format("您%s了%s。当前已点菜肴包括：%s",
						(ck1.isSelected() ? "点" : "取消"), ck1.getText(), getCheckedItem(boxArray)));
			}
		});
		ck2.selectedProperty().addListener(new ChangeListener<Boolean>() { // 设置复选框的勾选监听器
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				// 拼接并显示当前的勾选结果，以及已经勾选的菜肴
				label.setText(String.format("您%s了%s。当前已点菜肴包括：%s",
						(ck2.isSelected() ? "点" : "取消"), ck2.getText(), getCheckedItem(boxArray)));
			}
		});
		ck3.selectedProperty().addListener(new ChangeListener<Boolean>() { // 设置复选框的勾选监听器
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldv, Boolean newv) {
				// 拼接并显示当前的勾选结果，以及已经勾选的菜肴
				label.setText(String.format("您%s了%s。当前已点菜肴包括：%s",
						(ck3.isSelected() ? "点" : "取消"), ck3.getText(), getCheckedItem(boxArray)));
			}
		});
		
		borderPane.setCenter(vbox); // 把垂直箱子放到边界窗格的中央
	}

	// 获取已经选定的菜单
	private String getCheckedItem(CheckBox[] boxArray) {
		String itemDesc = "";
		for (CheckBox box : boxArray) { // 遍历复选框数组
			if (box.isSelected() == true) { // 复选框被选中了
				if (itemDesc.length() > 0) {
					itemDesc = itemDesc + "、";
				}
				itemDesc = itemDesc + box.getText(); // 菜单添加选定的菜肴
			}
		}
		return itemDesc;
	}

	// 获取单选按钮的界面
	private void getRadioButton(BorderPane borderPane) {
		VBox vbox = new VBox(); // 创建一个垂直箱子
		vbox.setAlignment(Pos.CENTER); // 设置垂直箱子的对齐方式
		HBox hbox = new HBox(); // 创建一个水平箱子
		hbox.setAlignment(Pos.CENTER); // 设置水平箱子的对齐方式

		RadioButton rb1 = new RadioButton("鱼香肉丝饭"); // 创建一个单选按钮
		rb1.setFont(font); // 设置单选按钮的文本字体及其大小
		rb1.setSelected(true); // 设置按钮是否选中
		RadioButton rb2 = new RadioButton("香菇滑鸡饭"); // 创建一个单选按钮
		rb2.setFont(font); // 设置单选按钮的文本字体及其大小
		RadioButton rb3 = new RadioButton("黑椒牛排饭"); // 创建一个单选按钮
		rb3.setFont(font); // 设置单选按钮的文本字体及其大小
		hbox.getChildren().addAll(rb1, rb2, rb3); // 把三个单选按钮一起加到水平箱子上
		
		ToggleGroup group = new ToggleGroup(); // 创建一个按钮小组
		rb1.setToggleGroup(group); // 把单选按钮1加入到按钮小组
		rb2.setToggleGroup(group); // 把单选按钮2加入到按钮小组
		rb3.setToggleGroup(group); // 把单选按钮3加入到按钮小组
		
		Label label = new Label("这里查看点餐结果"); // 创建一个标签
		label.setPrefSize(300, 60); // 设置标签的推荐宽高
		label.setFont(font); // 设置标签的字体
		label.setAlignment(Pos.CENTER); // 设置标签的对齐方式
		label.setWrapText(true); // 设置标签文本是否支持自动换行
		vbox.getChildren().addAll(hbox, label); // 把水平箱子和标签一起加到垂直箱子上

		// 设置单选组合的单击监听器
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle oldt, Toggle newt) {
				// 在标签上显示当前选中的单选按钮文本
				label.setText("您点了" + ((RadioButton)newt).getText());
			}
		});

		borderPane.setCenter(vbox); // 把垂直箱子放到边界窗格的中央
	}

	// 获取下拉框的界面
	private void getComboBox(BorderPane borderPane) {
		VBox vbox = new VBox(); // 创建一个垂直箱子
		vbox.setAlignment(Pos.CENTER); // 设置垂直箱子的对齐方式

		// 初始化快餐列表
		List<String> snackList = Arrays.asList("鱼香肉丝饭", "香菇滑鸡饭", "黑椒牛排饭",
				"梅菜扣肉饭", "糖醋里脊饭", "红烧排骨饭", "台式卤肉饭");
		// 把清单对象转换为JavaFX控件能够识别的数据对象
		ObservableList<String> obList = FXCollections.observableArrayList(snackList);
		ComboBox<String> comboBox = new ComboBox<String>(obList); // 依据指定数据创建下拉框
		//comboBox.setItems(obList); // 设置下拉框的数据来源
		comboBox.getSelectionModel().select(0); // 设置下拉框默认选中第1项
		// 设置下拉框的字体
		comboBox.setStyle(String.format("-fx-font: %f \"%s\";", font.getSize(), font.getFamily()));
		comboBox.setEditable(false); // 设置下拉框能否编辑。默认不允许编辑
		
		Label label = new Label("这里查看点餐结果"); // 创建一个标签
		label.setPrefSize(300, 60); // 设置标签的推荐宽高
		label.setFont(font); // 设置标签的字体
		label.setAlignment(Pos.CENTER); // 设置标签的对齐方式
		label.setWrapText(true); // 设置标签文本是否支持自动换行
		vbox.getChildren().addAll(comboBox, label); // 把水平箱子和标签一起加到垂直箱子上
		
		// 设置下拉框的选择监听器
		comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String old_str, String new_str) {
				// getSelectedIndex方法获得选中项的序号，getSelectedItem方法获得选中项的对象
				String desc = String.format("您点了第%d项，快餐名称是%s", 
						comboBox.getSelectionModel().getSelectedIndex(), 
						comboBox.getSelectionModel().getSelectedItem().toString());
				label.setText(desc); // 在标签上显示当前选中的文本项
			}
		});

		borderPane.setCenter(vbox); // 把垂直箱子放到边界窗格的中央
	}

}
