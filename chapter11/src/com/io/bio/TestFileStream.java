package com.io.bio;

import java.io.FileInputStream;
import java.io.FileOutputStream;

//演示文件输入输出流的用法
public class TestFileStream {
	private static String mFileName = "E:/test/aae.txt";
	
	public static void main(String[] arg) {
		writeFile(); // 利用文件输出流写入文件
		readFile(); // 利用文件输入流读取文件
	}
	
	// 利用文件输出流写入文件。注意FileOutputStream处理的是字节信息
	private static void writeFile() {
		String str = "白日依山尽，黄河入海流。\n欲穷千里目，更上一层楼。";
		// 根据指定路径构建文件输出流对象
		try (FileOutputStream fos = new FileOutputStream(mFileName)) {
			fos.write(str.getBytes()); // 把字节数组写入文件输出流
			// 在try(...)里面创建的IO流，程序用完会自动关闭，所以下面的close方法不必显式调用
			//fos.close(); // 关闭文件输出流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 利用文件输入流读取文件
	private static void readFile() {
		// 根据指定路径构建文件输入流对象
		try (FileInputStream fis = new FileInputStream(mFileName)) {
			// 分配长度为文件大小的字节数组。available方法返回当前位置后面的剩余部分大小
			byte[] bytes = new byte[fis.available()];
			//fis.skip(3); // 字节流的skip方法跳过的是字节数
			fis.read(bytes); // 从文件输入流中读取字节数组
			String content = new String(bytes); // 把字节数组转换为字符串
			System.out.println("content="+content);
			// 在try(...)里面创建的的IO流，程序用完会自动关闭，所以下面的close方法不必显式调用
			//fis.close(); // 关闭文件输入流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
