package com.network.im_client;

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

//演示包含图标与文字的列表
public class TestListImage extends Application {
	private Font font = Font.font("NSimSun", 20); // 创建一个字体对象
	private static Image QQ_LOGO;
	static {
		QQ_LOGO = new Image(TestListImage.class.getResourceAsStream("qq_logo_30.png"));
	}

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

	@Override
	public void start(Stage stage) { // 应用程序开始运行
		stage.setTitle("测试带图标的列表"); // 设置舞台的标题
		BorderPane borderPane = new BorderPane(); // 创建一个边界窗格
		Scene scene = new Scene(borderPane, 400, 250, Color.WHITE); // 创建一个场景

		FlowPane flowPane = new FlowPane(); // 创建一个流式窗格
		flowPane.setAlignment(Pos.CENTER); // 设置流式窗格的对齐方式

		// 初始化快餐列表
		List<String> snackList = Arrays.asList("鱼香肉丝饭", "香菇滑鸡饭", "黑椒牛排饭",
				"梅菜扣肉饭", "糖醋里脊饭", "红烧排骨饭", "台式卤肉饭");
		// 把清单对象转换为JavaFX控件能够识别的数据对象
		ObservableList<String> obList = FXCollections.observableArrayList(snackList);
		ListView<String> listView = new ListView<String>(obList); // 依据指定数据创建列表视图
		// 设置列表视图的字体
		listView.setStyle(String.format("-fx-font: %f \"%s\";", font.getSize(), font.getFamily()));
		// 设置列表视图的单元工厂（每个列表单元将如何展示）
		listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> listView) {
				return new ListCell<String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (!empty) {
							// 每个单元都是标签Label，左图标右文字
							setGraphic(new ImageView(QQ_LOGO));
							setText(item);
						}
					}
				};
			}
		});
		// 设置列表视图的选择监听器
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String old_str, String new_str) {
				// 单击列表项后会走到这里
			}
		});
		borderPane.setCenter(listView); // 把列表视图添加到边界窗格的中央

		borderPane.setTop(flowPane); //把流式窗格放到边界窗格的上方
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

}
