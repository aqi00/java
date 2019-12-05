package com.io.bio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

//演示缓存输入输出流的用法
public class TestBufferedStream {
	private static String mSrcName = "E:/test/aaf.txt"; // 源文件
	private static String mDestName = "E:/test/aaf_copy.txt"; // 目标文件
	
	public static void main(String[] arg) {
		writeBuffer(); // 利用缓存输出流写入文件
		readBuffer(); // 利用缓存输入流读取文件
		copyFile(); // 通过缓存输入输出流复制文件
	}

	// 利用缓存输出流写入文件
	private static void writeBuffer() {
		String str = "白日依山尽，黄河入海流。\n欲穷千里目，更上一层楼。";
		// 根据指定文件路径构建文件输出流对象，然后据此构建缓存输出流对象
		try (FileOutputStream fos = new FileOutputStream(mSrcName);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			bos.write(str.getBytes()); // 把字节数组写入缓存输出流
			//bos.flush(); // 立即写入磁盘。如果不立即写入，最后调用close方法时也会写入
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 利用缓存输入流读取文件
	private static void readBuffer() {
		// 根据指定文件路径构建文件输入流对象，然后据此构建缓存输入流对象
		try (FileInputStream fis = new FileInputStream(mSrcName);
				BufferedInputStream bis = new BufferedInputStream(fis)) {
			// 分配长度为文件大小的字节数组。available方法返回当前位置后面的剩余部分大小
			byte[] bytes = new byte[bis.available()];
			bis.read(bytes); // 从缓存输入流中读取字节数组
			// 缓存输入流的mark和reset用法类似于BufferedReader的同名方法
			//bis.mark(bis.available()); // 在当前位置做个标记
			//bis.reset(); // 重置输入流指针，令其回到上次标记的位置
			String content = new String(bytes); // 把字节数组转换为字符串
			System.out.println("content="+content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 利用缓存输入和输出流复制文件
	private static void copyFile() {
		// 分别构建缓存输入流对象和缓存输出流对象
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(mSrcName));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(mDestName))) {
			// 分配长度为文件大小的字节数组。available方法返回当前位置后面的剩余部分大小
			byte[] bytes = new byte[bis.available()];
			bis.read(bytes); // 从缓存输入流中读取字节数组
			bos.write(bytes); // 把字节数组写入缓存输出流
			//bos.flush(); // 立即写入磁盘。如果不立即写入，最后调用close方法时也会写入
			System.out.println("文件复制完成，源文件大小="+bytes.length+"，新文件大小="+bytes.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
