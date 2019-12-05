package com.network.socket;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.network.PrintUtils;

//定义一个文件发送任务
public class SendFile implements Runnable {
	// 以下为Socket服务器的IP和端口，根据实际情况修改
	private static final String SOCKET_IP = "192.168.1.8";
	private static final int FILE_PORT = 52000; // 文件传输专用端口
	private String mFilePath; // 待发送的文件路径
	
	public SendFile(String filePath) {
		mFilePath = filePath;
	}

	@Override
	public void run() {
		PrintUtils.print("向服务器发送文件：" + mFilePath);
		// 创建一个套接字对象。同时根据指定路径构建文件输入流对象
		try (Socket socket = new Socket();
				FileInputStream fis = new FileInputStream(mFilePath)) {
			// 命令套接字连接指定地址的指定端口，超时时间为3秒
			socket.connect(new InetSocketAddress(SOCKET_IP, FILE_PORT), 3000);
			OutputStream writer = socket.getOutputStream(); // 获取套接字对象的输出流
			long totalLength = fis.available(); // 文件的总长度
			int tempLength = 0; // 每次发送的数据长度
			double sendedLength = 0; // 已发送的数据长度
			byte[] data = new byte[1024 * 8]; // 每次发送数据的字节数组
			// 以下从文件中循环读取数据
			while ((tempLength = fis.read(data, 0, data.length)) > 0) {
				writer.write(data, 0, tempLength); // 往Socket连接中写入数据
				sendedLength += tempLength; // 累加已发送的数据长度
				// 计算已发送数据的百分比，并打印当前的传输进度
				String ratio = "" + (sendedLength / totalLength * 100);
				PrintUtils.print(mFilePath+"已传输：" + ratio.substring(0, 4) + "%");
			}
			PrintUtils.print(mFilePath+" 文件发送完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
