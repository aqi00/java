package com.concurrent.mouse;

import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

//打地鼠游戏的界面控制器
public class HitMouseController implements Initializable {
	@FXML private Label labelTime; // 计时标签
	@FXML private Button btnStart; // 开始按钮
	@FXML private Label labelCount; // 击中数量的标签
	@FXML private GridPane gpGrass; // 一大片草地的网格窗格
	
	private Button[][] btnArray = new Button[4][5]; // 地洞按钮的二维数组

	// 获得地洞按钮
	private Button getHoleView() {
		Button btn = new Button(); // 创建一个按钮
		btn.setPadding(new Insets(0, 0, 0, 0)); // 设置按钮的四周空白
		btn.setGraphic(new ImageView(imageHole)); // 设置按钮的图像
		return btn;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) { // 界面打开后的初始化操作
		// 下面初始化每个地洞按钮，并设置每个地洞按钮的单击事件
		for (int i = 0; i < btnArray.length; i++) {
			for (int j = 0; j < btnArray[i].length; j++) {
				btnArray[i][j] = getHoleView(); // 获取一个地洞按钮
				Button view = btnArray[i][j];
				gpGrass.add(view, j, i + 1); // 把地洞按钮添加进草地网格
				int x = i, y = j;
				// 设置地洞按钮的单击事件。单击地洞表示抡起锤子打地鼠，默认打空洞
				view.setOnAction(e -> doAction(x, y, TYPE_HOLE_HIT));
			}
		}
		labelTime.setFont(Font.font("KaiTi", 25)); // 设置标签的字体
		btnStart.setFont(Font.font("KaiTi", 25)); // 设置按钮的字体
		labelCount.setFont(Font.font("KaiTi", 25)); // 设置标签的字体
		btnStart.setOnAction(e -> { // 单击了开始游戏按钮
			isRunning = !isRunning;
			if (isRunning) { // 游戏开始
				btnStart.setText("停止游戏");
				hitCount = 0; // 击中次数清零
				timeCount = 0; // 时间计数清零
				beginTime = new Date().getTime(); // 获得开始时间
				new MouseThread(0).start(); // 启动第一个地鼠线程
				new MouseThread(timeUnit * 1).start(); // 启动第二个地鼠线程
				new MouseThread(timeUnit * 2).start(); // 启动第三个地鼠线程
			} else { // 游戏结束
				btnStart.setText("开始游戏");
			}
		});
	}

	private boolean isRunning = false; // 游戏是否运行
	private long beginTime; // 开始时间
	private int timeCount = 0; // 时间计数
	private int hitCount = 0; // 击中次数
	private int timeUnit = 1000; // 时间单位。1000毫秒为1秒
	// 定义一个地洞按钮数组对应的倒计时数组，二维数组分配四行五列
	private int[][] timeArray = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

	// 定义地鼠线程
	private class MouseThread extends Thread {
		private int mDelay; // 延迟间隔

		public MouseThread(int delay) {
			mDelay = delay;
		}

		public void run() {
			try {
				sleep(mDelay); // 每个线程的启动延时都不同，方便错开执行
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (isRunning) { // 游戏正在运行
				int i = 0, j = 0; // 网格的横纵坐标
				while (true) { // 这只地鼠总想伺机钻出地洞
					i = new Random().nextInt(btnArray.length);
					j = new Random().nextInt(btnArray[0].length);
					if (timeArray[i][j] == 0) { // 该地洞没有地鼠了
						doAction(i, j, TYPE_MOUSE); // 新的地鼠钻出地洞
						break;
					}
				}
				long nowTime = new Date().getTime();
				timeCount = (int) ((nowTime - beginTime) / 1000);
				try {
					sleep((timeUnit - 100) * 3); // 地鼠出洞后正在发呆，快揍它
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 地洞发生了变化
	private synchronized void doAction(int i, int j, int type) {
		timeArray[i][j] = 3; // 地鼠会在地洞上方呆上3秒
		Button btn = btnArray[i][j];
		if (type == TYPE_HOLE_HIT) { // 捶打空的地洞
			showView(btn, imageHoleHit); // 显示捶打地洞的图像
			timeSchedule(i, j); // 地洞上方的锤子开始倒计时
		} else if (type == TYPE_MOUSE) { // 地鼠钻出地洞
			showView(btn, imageMouse); // 显示地鼠的图像
			timeSchedule(i, j); // 地洞上方的锤子开始倒计时
			btn.setOnAction(e -> { // 注册该地洞的单击事件
				doAction(i, j, TYPE_MOUSE_HIT); // 打中该地洞的地鼠
				hitCount++; // 击中次数加一
			});
		} else if (type == TYPE_MOUSE_HIT) { // 捶打地鼠
			showView(btn, imageMouseHit); // 显示捶打地鼠的图像
			btn.setOnAction(null); // 注销该地洞的单击事件
		}
	}

	// 倒计时的时间调度
	private void timeSchedule(int i, int j) {
		Button btn = btnArray[i][j];
		Timer timer = new Timer(); // 创建一个定时器
		timer.schedule(new TimerTask() { // 定时器每秒调度一次
			public void run() {
				timeArray[i][j]--; // 倒计时减一
				if (timeArray[i][j] <= 0) { // 倒计时结束
					showView(btn, imageHole); // 显示空的地洞
					btn.setOnAction(e -> { // 注册该地洞的单击事件
						doAction(i, j, TYPE_HOLE_HIT); // 捶打空地洞
					});
					timer.cancel(); // 取消定时器
				}
			}
		}, 0, timeUnit);
	}

	// 显示地洞的图像
	private void showView(Button btn, Image image) {
		// 定义一个JavaFX任务，任务的call方法不能操作界面，succeeded方法才能操作界面
		Task task = new Task<Void>() {

			// call方法里面的线程非主线程，不能操作界面
			protected Void call() throws Exception {
				return null;
			}

			// succeeded方法里面的线程是主线程，可以操作界面
			protected void succeeded() {
				super.succeeded();
				btn.setGraphic(new ImageView(image)); // 设置按钮的图像
				labelCount.setText(String.format("击中%d只老鼠", hitCount));
				labelTime.setText(String.format("%02d:%02d", timeCount / 60, timeCount % 60));
			}
		};
		task.run(); // 启动JavaFX任务
	}

	private final static int TYPE_HOLE = 1; // 地洞类型
	private final static int TYPE_MOUSE = 2; // 地鼠类型
	private final static int TYPE_MOUSE_HIT = 3; // 捶打地鼠的类型
	private final static int TYPE_HOLE_HIT = 4; // 捶打地洞的类型
	private static Image imageHole; // 地洞图像
	private static Image imageMouse; // 地鼠图像
	private static Image imageMouseHit; // 捶打地鼠的图像
	private static Image imageHoleHit; // 捶打地洞的图像

	static {
		imageHole = new Image(HitMouseController.class.getResourceAsStream("hole.png"));
		imageMouse = new Image(HitMouseController.class.getResourceAsStream("mouse.png"));
		imageMouseHit = new Image(HitMouseController.class.getResourceAsStream("mouse_hit.png"));
		imageHoleHit = new Image(HitMouseController.class.getResourceAsStream("hole_hit.png"));
	}

}
