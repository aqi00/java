package com.network.socket;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

//演示轻量级HTTP服务器的用法
public class TestHttpServer {

	public static void main(String[] args) {
		startServer(); // 启动HTTP服务器，监听来自客户端的请求
	}

	// 启动HTTP服务器，监听来自客户端的请求
	public static void startServer() {
		try {
			// 创建HTTP服务的提供器
			HttpServerProvider provider = HttpServerProvider.provider();
			// 创建HTTP服务器的对象，其中指定了监听8080端口，且允许同时接受10个请求
			HttpServer server = provider.createHttpServer(new InetSocketAddress(8080), 10);
			// 创建HTTP服务的上下文。第一个参数代表url的后面部分，第二个参数代表接收请求的处理器
			server.createContext("/test", new MyHttpHandler());
			server.setExecutor(null); // 设置处理HTTP请求的线程池。为null表示只有一个线程
			server.start(); // 启动HTTP服务器
			System.out.println("HTTP服务器已启动");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 自定义的HTTP处理器
	private static class MyHttpHandler implements HttpHandler {
		public void handle(HttpExchange exchange) {
			String response = "我很好，你呢？"; // HTTP应答串
			byte[] byteResp = response.getBytes(); // 将字符串转为字节数组
			try (InputStream is = exchange.getRequestBody(); // 获得HTTP交互的输入流
					// 创建一个字节数组的输出流对象
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					// 获得HTTP交互的输出流
					OutputStream os = exchange.getResponseBody();) {
				int i = -1;
				while ((i = is.read()) != -1) { // 循环读取输入流中的字节数据
					baos.write(i); // 把字节数据写入字节数组输出流
				}
				String request = baos.toString(); // 把字节数组输出流转换为字符串
				System.out.println("收到请求内容：" + request);
				// 设置HTTP调用的响应头，第一个参数代表状态码（200为成功），第二个参数代表应答内容的字节数
				exchange.sendResponseHeaders(200, byteResp.length);
				os.write(byteResp); // 往输出流写入应答报文
				System.out.println("返回应答内容：" + response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			exchange.close(); // 关闭HTTP交互对象
		}
	}
}
