package com.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

//演示文件通道的用法
public class TestChannel {
	private static String mFileName = "E:/test/aah.txt";

	public static void main(String[] arg) {
		createWriteChannel(); // 创建可写的文件通道
		createReadChannel(); // 创建可读的文件通道
		writeChannel(); // 通过文件通道写入文件
		readChannel(); // 通过文件通道读取文件
	}

	// 创建可写的文件通道
	private static void createWriteChannel() {
		try {
			// 第一种方式：根据文件输出流获得可写的文件通道
			FileChannel channel = new FileOutputStream(mFileName).getChannel();
			// 第二种方式：根据随机访问文件获得可写的文件通道
			//FileChannel channel = new RandomAccessFile(mFileName,"rw").getChannel();
//			channel.isOpen(); // 判断文件通道是否打开
//			channel.size(); // 获取文件通道的大小（即文件长度）
//			channel.truncate(10); // 截断文件大小到指定长度
			channel.close(); // 关闭文件通道
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 创建可读的文件通道
	private static void createReadChannel() {
		try {
			// 第一种方式：根据文件输入流获得可读的文件通道
			FileChannel channel = new FileInputStream(mFileName).getChannel();
			// 第二种方式：根据随机访问文件获得可读的文件通道
			//FileChannel channel = new RandomAccessFile(mFileName, "r").getChannel();
			channel.close(); // 关闭文件通道
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 通过文件通道写入文件
	private static void writeChannel() {
		String str = "春眠不觉晓，处处闻啼鸟。\n夜来风雨声，花落知多少。";
		// 根据文件输出流获得可写的文件通道。注意文件通道支持try(...)的自动关闭操作
		try (FileChannel channel = new FileOutputStream(mFileName).getChannel()) {
			ByteBuffer buffer = ByteBuffer.wrap(str.getBytes()); // 生成字符串对应的字节缓存对象
			channel.write(buffer); // 往文件通道写入字节缓存
			//channel.force(true); // 强制写入磁盘，相当于输出流的flush方法
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 通过文件通道读取文件
	private static void readChannel() {
		// 根据文件输入流获得可读的文件通道。注意文件通道支持try(...)的自动关闭操作
		try (FileChannel channel = new FileInputStream(mFileName).getChannel()) {
			int size = (int) channel.size(); // 获取文件通道的大小（即文件长度）
			ByteBuffer buffer = ByteBuffer.allocateDirect(size); // 分配指定大小的字节缓存
			channel.read(buffer); // 把文件通道中的数据读到字节缓存
			buffer.flip(); // 把缓冲区切换到读模式。从缓冲区读取数据之前，必须先调用flip方法
			byte[] bytes = new byte[size]; // 创建与文件大小相同长度的字节数组
			buffer.get(bytes); // 把字节缓存中的数据取到字节数组
			String content = new String(bytes); // 把字节数组转换为字符串
			System.out.println("content="+content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
