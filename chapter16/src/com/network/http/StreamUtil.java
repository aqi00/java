package com.network.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

//HTTP数据解析用到的工具类
public class StreamUtil {

	// 把输入流中的数据转换为字符串。该方法在处理大量数据时有问题，因为available方法返回的是int类型，最大只能表达到65536
	public static String isToString(InputStream is) throws IOException {
		byte[] bytes = new byte[is.available()]; // 创建临时存放的字节数组
		is.read(bytes); // 从输入流中读取字节数组
		return  new String(bytes); // 把字节数组转换为字符串并返回
	}

	// 把输入流中的数据转换为字符串。处理大量数据时需要使用本方法
	public static String isToStringForLarge(InputStream is) {
		String result = "";
		// 创建一个字节数组的输出流对象
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			int i = -1;
			while ((i = is.read()) != -1) { // 循环读取输入流中的字节数据
				baos.write(i); // 把字节数据写入字节数组输出流
			}
			result = baos.toString(); // 把字节数组输出流转换为字符串
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; // 返回转换后的字符串
	}

	// 把输入流中的数据按照指定字符编码转换为字符串。该方法在处理大量数据时有问题，最大只能表达到65536
	public static String isToString(InputStream is, String charset) throws IOException {
		byte[] bytes = new byte[is.available()]; // 创建临时存放的字节数组
		is.read(bytes); // 从输入流中读取字节数组
		return  new String(bytes, charset); // 把字节数组按照指定的字符编码转换为字符串并返回
	}

	// 把输入流中的数据按照指定字符编码转换为字符串。处理大量数据需要使用本方法
	public static String isToStringForLarge(InputStream is, String charset) {
		String result = "";
		// 创建一个字节数组的输出流对象
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			int i = -1;
			while ((i = is.read()) != -1) { // 循环读取输入流中的字节数据
				baos.write(i); // 把字节数据写入字节数组输出流
			}
			byte[] data = baos.toByteArray(); // 把字节数组输出流转换为字节数组
			result = new String(data, charset); // 将字节数组按照指定的字符编码生成字符串
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; // 返回转换后的字符串
	}

	// 从HTTP连接中获取已解压且重新编码后的应答报文
	public static String getUnzipString(HttpURLConnection conn) throws IOException {
		String contentType = conn.getContentType(); // 获取应答报文的内容类型（包括字符编码）
		String charset = "UTF-8"; // 默认的字符编码为UTF-8
		if (contentType != null) {
			if (contentType.toLowerCase().contains("charset=gbk")) { // 应答报文采用gbk编码
				charset = "GBK"; // 字符编码改为GBK
			} else if (contentType.toLowerCase().contains("charset=gb2312")) { // 采用gb2312编码
				charset = "GB2312"; // 字符编码改为GB2312
			}
		}
		String contentEncoding = conn.getContentEncoding(); // 获取应答报文的压缩方式
		InputStream is = conn.getInputStream(); // 获取HTTP连接的输入流对象
		String result = "";
		if (contentEncoding != null && contentEncoding.contains("gzip")) { // 应答报文使用了gzip压缩
			// 根据输入流对象构建压缩输入流
			try (GZIPInputStream gis = new GZIPInputStream(is)) {
				// 把压缩输入流中的数据按照指定字符编码转换为字符串
				result = isToStringForLarge(gis, charset);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 把输入流中的数据按照指定字符编码转换为字符串
			result = isToStringForLarge(is, charset);
		}
		return result; // 返回处理后的应答报文
	}
	
}
