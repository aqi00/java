package com.network.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import com.network.PrintUtils;

//定义一个文件接收任务
public class ReceiveFile implements Runnable {
	private static final int FILE_PORT = 52000; // 文件传输专用端口

	@Override
	public void run() {
		PrintUtils.print("接收文件的Socket服务已启动");
		try {
			// 创建一个服务端套接字，用于监听客户端Socket的连接请求
			ServerSocket server = new ServerSocket(FILE_PORT);
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
		
		public ServerTask(Socket socket) throws IOException {
			mSocket = socket;
		}

		@Override
		public void run() {
			PrintUtils.print("开始接收文件");
			int random = new Random().nextInt(1000); // 生成随机数
			String file_path = "E:/" + random + ".jpg"; // 本地临时保存的文件
			// 根据指定的临时路径构建文件输出流对象
			try (FileOutputStream fos = new FileOutputStream(file_path)) {
				InputStream reader = mSocket.getInputStream(); // 获取套接字对象的输入流
				int tempLength = 0; // 每次接收的数据长度
				byte[] data = new byte[1024 * 8]; // 每次接收数据的字节数组
				// 以下从Socket连接中循环接收数据
				while ((tempLength = reader.read(data, 0, data.length)) > 0) {
					fos.write(data, 0, tempLength); // 把接收到的数据写入文件
				}
				// 注意客户端的Socket要先调用close方法，服务端才会退出上面的循环
				mSocket.close(); // 关闭套接字连接
				PrintUtils.print(file_path+" 文件接收完毕");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
