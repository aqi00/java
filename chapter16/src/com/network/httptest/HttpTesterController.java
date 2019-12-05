package com.network.httptest;

import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.network.http.StreamUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

//HTTP测试工具的界面控制器
public class HttpTesterController implements Initializable {
	@FXML private Label labelUrl; // http调用地址的标签
	@FXML private TextField fieldUrl; // http调用地址的输入框
	@FXML private Button btnStart; // 发送请求按钮
	@FXML private TextArea areaRequest; // 请求报文的输入框
	@FXML private TextArea areaResponse; // 应答报文的输入框
	
	private static Logger logger; // 声明一个日志管理器对象
	static { // 静态代码块在启动程序的时候就会自动执行
		String work_dir = System.getProperty("user.dir"); // 获取用户程序的当前目录
		work_dir += "/logs/"; // 日志文件放在程序目录的logs子目录下
		File file = new File(work_dir); // 创建指定路径的文件对象
		if (!file.exists()) { // 如果该文件/目录不存在
			file.mkdirs(); // 创建该目录
		}
		System.setProperty("WORKDIR", work_dir); // 设置环境变量WORKDIR的取值
		logger = Logger.getLogger(HttpTesterController.class); // 获取当前类的日志管理器
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { // 界面打开后的初始化操作
		labelUrl.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		fieldUrl.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		btnStart.setFont(Font.font("NSimSun", 20)); // 设置按钮的字体
		areaRequest.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		areaResponse.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		btnStart.setOnAction(e -> { // 设置按钮的单击事件
				String callUrl = fieldUrl.getText(); // 获取http调用地址
				String body = areaRequest.getText(); // 获取请求报文
				// 对指定url发起POST调用，并获取返回的应答报文
				String response = testCallPost(callUrl, body);
				areaResponse.setText(response); // 设置输入框的文本
		});
	}

	// 对指定url发起POST调用
	private String testCallPost(String callUrl, String body) {
		try {
			URL url = new URL(callUrl); // 根据网址字符串构建URL对象
			// 打开URL对象的网络连接，并返回HttpURLConnection连接对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST"); // 设置请求方式为POST调用
			conn.setRequestProperty("Content-Type", "application/json"); // 请求报文为json格式
			conn.setDoOutput(true); // 准备让连接执行输出操作。默认为false，POST方式需要设置为true
			conn.connect(); // 开始连接
			OutputStream os = conn.getOutputStream(); // 从连接对象中获取输出流
			logger.debug("调用地址："+callUrl); // 打印HTTP接口的调用地址
			logger.debug("请求报文："+body); // 打印HTTP调用的请求报文
			os.write(body.getBytes()); // 往输出流写入请求报文
			// 对输入流中的数据解压和字符编码，得到原始的应答字符串
			String content = StreamUtil.getUnzipString(conn);
			logger.debug("应答报文："+content); // 打印HTTP调用的应答报文
			conn.disconnect(); // 断开连接
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
	
}
