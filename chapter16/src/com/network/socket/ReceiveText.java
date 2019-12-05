package com.network.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.network.PrintUtils;

//定义一个文本接收任务
public class ReceiveText implements Runnable {
	private static final int TEXT_PORT = 51000; // 文本传输专用端口

	@Override
	public void run() {
		PrintUtils.print("接收文本的Socket服务已启动");
		try {
			// 创建一个服务端套接字，用于监听客户端Socket的连接请求
			ServerSocket server = new ServerSocket(TEXT_PORT);
			while (true) { // 持续侦听客户端的连接
				// 收到了某个客户端的Socket连接请求，并获得该客户端的套接字对象
				Socket socket = server.accept();
				// 启动一个服务线程负责与该客户端的交互操作
				new Thread(new ServerTask(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 定义一个伺候任务，好生招待这位顾客
	private class ServerTask implements Runnable {
		private Socket mSocket; // 声明一个套接字对象
		private BufferedReader mReader; // 声明一个缓存读取器对象

		public ServerTask(Socket socket) throws IOException {
			mSocket = socket;
			// 根据套接字的输入流构建缓存读取器
			mReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
		}

		@Override
		public void run() {
			try {
				String request;
				// 循环不断地从Socket中读取客户端发送过来的文本消息
				while ((request = mReader.readLine()) != null) {
					PrintUtils.print("收到客户端消息：" + request);
					// 根据套接字的输出流构建打印流对象
					PrintStream ps = new PrintStream(mSocket.getOutputStream());
					String response = "hi，很高兴认识你";
					PrintUtils.print("服务端返回消息：" + response);
					ps.println(response); // 往打印流对象中写入文本消息
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
