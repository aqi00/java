package com.network.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.network.PrintUtils;

//定义一个文本发送任务
public class SendText implements Runnable {
	// 以下为Socket服务器的IP和端口，根据实际情况修改
	private static final String SOCKET_IP = "192.168.1.8";
	private static final int TEXT_PORT = 51000; // 文本传输专用端口
	private BufferedReader mReader; // 声明一个缓存读取器对象
	private PrintStream mWriter; // 声明一个打印流对象
	private String mRequest = ""; // 待发送的文本内容

	@Override
	public void run() {
		Socket socket = new Socket(); // 创建一个套接字对象
		try {
			// 命令套接字连接指定地址的指定端口，超时时间为3秒
			socket.connect(new InetSocketAddress(SOCKET_IP, TEXT_PORT), 3000);
			// 根据套接字的输入流构建缓存读取器
			mReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 根据套接字的输出流构建打印流对象
			mWriter = new PrintStream(socket.getOutputStream());
//			new RecvThread().start(); // 启动一条子线程从服务器读取文本消息
			// 利用Lambda表达式简化Runnable代码。启动一条子线程从服务器读取文本消息
			new Thread(() -> handleRecv()).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 发送文本消息
	public void sendText(String text) {
		mRequest = text;
//		new SendThread().start(); // 启动一条子线程向服务器发送文本消息
		// 利用Lambda表达式简化Runnable代码。启动一条子线程向服务器发送文本消息
		new Thread(() -> handleSend(text)).start();
	}

	// 处理文本发送事件。为了避免多线程并发产生冲突，这里添加了synchronized使之成为同步方法
	private synchronized void handleSend(String text) {
		PrintUtils.print("向服务器发送消息："+text);
		try {
			mWriter.println(text); // 往打印流对象中写入文本消息
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 处理文本接收事件。为了避免多线程并发产生冲突，这里添加了synchronized使之成为同步方法
	private synchronized void handleRecv() {
		try {
			String response;
			// 持续从服务器读取文本消息
			while ((response = mReader.readLine()) != null) {
				PrintUtils.print("服务器返回消息："+response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 定义一个消息发送线程
	private class SendThread extends Thread {
		@Override
		public void run() {
			handleSend(mRequest); // 处理文本发送事件
		}
	}

	// 定义一个消息接收线程
	private class RecvThread extends Thread {
		@Override
		public void run() {
			handleRecv(); // 处理文本接收事件
		}
	}
}
