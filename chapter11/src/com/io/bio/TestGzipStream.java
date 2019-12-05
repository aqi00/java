package com.io.bio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

//演示压缩输入输出流的用法
public class TestGzipStream {
	private static String mFileName = "E:/test/aag.txt";
	
	public static void main(String[] arg) {
		writeZipFile(); // 往文件写入压缩后的数据
		readZipFile(); // 从压缩文件中读取解压后的数据
	}
	
	// 往文件写入压缩后的数据
	private static void writeZipFile() {
		String str = "白日依山尽，黄河入海流。\n欲穷千里目，更上一层楼。";
		// 根据指定文件路径构建文件输出流对象
		try (FileOutputStream fos = new FileOutputStream(mFileName)) {
			byte[] zip_bytes = compress(str); // 从字符串获得压缩后的字节数组
			fos.write(zip_bytes); // 把字节数组写入文件输出流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 从压缩文件中读取解压后的数据
	private static void readZipFile() {
		// 根据指定文件路径构建文件输入流对象
		try (FileInputStream fis = new FileInputStream(mFileName)) {
			// 分配长度为文件大小的字节数组。available方法返回当前未读取的大小
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes); // 从文件输入流中读取字节数组
			String content = uncompress(bytes); // 从压缩字节数组获得解压后的字符串
			System.out.println("content="+content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 从字符串获得压缩后的字节数组
	private static byte[] compress(String str) {
		if (str==null || str.length()<=0) {
			return null;
		}
		byte[] zip_bytes = null; // 声明压缩数据的字节数组
		// 先构建字节数组输出流，再据此构建压缩输出流
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				GZIPOutputStream gos = new GZIPOutputStream(baos);) {
			gos.write(str.getBytes()); // 往压缩输出流写入字节数组
			gos.finish(); // 结束写入操作
			zip_bytes = baos.toByteArray(); // 从字节数组输出流中获取字节数组信息
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zip_bytes;
	}

	// 从压缩字节数组获得解压后的字符串
	private static String uncompress(byte[] bytes) {
		if (bytes==null || bytes.length<=0) {
			return null;
		}
		byte[] unzip_bytes = null; // 声明解压数据的字节数组
		// 分别构建字节数组输出流，以及字节数组输入流，并根据字节数组输入流构建压缩输入流
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
				GZIPInputStream gis = new GZIPInputStream(bais);) {
			byte[] buffer = new byte[1024];
			while (true) {
				// 从压缩输入流中读取数据到字节数组，并返回读到的数据长度
				int length = gis.read(buffer);
				if (length < 0) { // 未读到数据，表示已经读完了
					break;
				}
				baos.write(buffer); // 往字节数组输出流写入字节数组
			}
			unzip_bytes = baos.toByteArray(); // 从字节数组输出流中获取字节数组信息
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(unzip_bytes); // 把字节数组转换为字符串，并返回该字符串
	}

}
