package com.network.http;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//演示HttpURLConnection的用法，包括GET调用、POST调用、文件下载和文件上传
public class TestUrlConnection {

	public static void main(String[] arg) {
		//testCallGet("http://www.weather.com.cn/data/sk/101010100.html"); // 查询北京的实时天气
		//testCallGet("http://www.weather.com.cn/data/cityinfo/101010100.html"); // 查询北京的当天天气
		//testCallGet("https://hq.sinajs.cn/list=s_sh000001"); // 查询上证指数
		//testDownload("E:/", "https://img-blog.csdnimg.cn/2018112123554364.png");
		//testCallPost("http://localhost/NetServer/checkUpdate", 
		//		"{\"package_list\":[{\"package_name\":\"com.qiyi.video\"}]}");
		//testUpload("E:/bliss.jpg", "http://localhost/NetServer/uploadServlet");
		testCallPost("http://localhost:8080/test", "你好吗？");
	}
	
	// 对指定url发起GET调用
	private static void testCallGet(String callUrl) {
		try {
			URL url = new URL(callUrl); // 根据网址字符串构建URL对象
			// 打开URL对象的网络连接，并返回HttpURLConnection连接对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); // 设置请求方式为GET调用
			conn.setConnectTimeout(5000); // 设置连接的超时时间，单位毫秒
			conn.setReadTimeout(5000); // 设置读取应答数据的超时时间，单位毫秒
			//conn.setRequestProperty("Accept", "*/*"); // 设置连接的请求属性
			// 设置用户代理，包括操作系统版本、浏览器版本等等
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
			conn.connect(); // 开始连接
			// 打印HTTP调用的应答内容长度、内容类型、压缩方式
			System.out.println( String.format("应答内容长度=%d, 内容类型=%s, 压缩方式=%s", 
					conn.getContentLength(), conn.getContentType(), conn.getContentEncoding()) );
			// 从输入流中获取默认的字符串数据，既不支持gzip解压，也不支持GBK编码
			//String content = StreamUtil.isToString(conn.getInputStream());
			// 对输入流中的数据解压和字符编码，得到原始的应答字符串
			String content = StreamUtil.getUnzipString(conn);
			// 打印HTTP调用的应答状态码和应答报文
			System.out.println( String.format("应答状态码=%d, 应答报文=%s", 
					conn.getResponseCode(), content) );
			conn.disconnect(); // 断开连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 从指定url下载文件到本地
	private static void testDownload(String filePath, String downloadUrl) {
		// 从下载地址中获取文件名
		String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
		fileName = fileName.replace("?", "").replace("*", "").replace("/", "").replace("\\", "")
				.replace(":", "").replace("|", "").replace("<", "").replace(">", "");
		String fullPath = filePath + "/" + fileName; // 把本地目录与文件名拼接成为本地文件的完整路径
		// 根据指定路径构建文件输出流对象
		try (FileOutputStream fos = new FileOutputStream(fullPath)) {
			URL url = new URL(downloadUrl); // 根据网址字符串构建URL对象
			// 打开URL对象的网络连接，并返回HttpURLConnection连接对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); // 设置请求方式为GET调用
			conn.connect(); // 开始连接
			InputStream is = conn.getInputStream(); // 从连接对象中获取输入流
			// 以下把输入流中的数据写入本地文件
			byte[] data = new byte[1024];
			int len = 0;
			while((len = is.read(data)) > 0){
				fos.write(data, 0, len);
			}
			// 打印HTTP下载的文件大小、内容类型、压缩方式
			System.out.println( String.format("文件大小=%dK, 内容类型=%s, 压缩方式=%s", 
					conn.getContentLength()/1024, conn.getContentType(), conn.getContentEncoding()) );
			// 打印HTTP下载的应答状态码和文件保存路径
			System.out.println( String.format("应答状态码=%d, 文件保存路径=%s", 
					conn.getResponseCode(), fullPath) );
			conn.disconnect(); // 断开连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 对指定url发起POST调用
	private static void testCallPost(String callUrl, String body) {
		try {
			URL url = new URL(callUrl); // 根据网址字符串构建URL对象
			// 打开URL对象的网络连接，并返回HttpURLConnection连接对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST"); // 设置请求方式为POST调用
			//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 请求报文为url格式
			//conn.setRequestProperty("Content-Type", "application/xml"); // 请求报文为xml格式
			conn.setRequestProperty("Content-Type", "application/json"); // 请求报文为json格式
			//conn.setRequestProperty("Accept-Encoding", "identity"); // 不允许应答报文使用压缩，默认
			conn.setRequestProperty("Accept-Encoding", "gzip"); // 允许应答报文采用GZIP压缩
			conn.setDoOutput(true); // 准备让连接执行输出操作。默认为false，POST方式需要设置为true
			//conn.setDoInput(true); // 准备让连接执行输入操作。默认为true
			conn.connect(); // 开始连接
			OutputStream os = conn.getOutputStream(); // 从连接对象中获取输出流
			System.out.println("请求报文="+body);
			os.write(body.getBytes()); // 往输出流写入请求报文
			// 打印HTTP调用的应答内容长度、内容类型、压缩方式
			System.out.println( String.format("应答内容长度=%s, 内容类型=%s, 压缩方式=%s", 
					conn.getHeaderField("Content-Length"), conn.getHeaderField("Content-Type"),
					conn.getHeaderField("Content-Encoding")) );
			// 对输入流中的数据解压和字符编码，得到原始的应答字符串
			String content = StreamUtil.getUnzipString(conn);
			// 打印HTTP调用的应答状态码和应答报文
			System.out.println( String.format("应答状态码=%d, 应答报文=%s", 
					conn.getResponseCode(), content) );
			conn.disconnect(); // 断开连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 把本地文件上传给指定url
	private static void testUpload(String filePath, String uploadUrl) {
		// 从本地文件路径获取文件名
		String fileName = filePath.substring(filePath.lastIndexOf("/"));
		String end = "\r\n"; // 结束字符串
		String hyphens = "--"; // 连接字符串
		String boundary = "WUm4580jbtwfJhNp7zi1djFEO3wNNm"; // 边界字符串
		try (FileInputStream fis = new FileInputStream(filePath)) {
			URL url = new URL(uploadUrl); // 根据网址字符串构建URL对象
			// 打开URL对象的网络连接，并返回HttpURLConnection连接对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true); // 准备让连接执行输出操作。默认为false，POST方式需要设置为true
			//conn.setDoInput(true); // 准备让连接执行输入操作。默认为true
			conn.setRequestMethod("POST"); // 设置请求方式为POST调用
			// 连接过程要保持活跃
			conn.setRequestProperty("Connection", "Keep-Alive");
			// 请求报文要求分段传输，并且各段之间以边界字符串隔开
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			
			// 根据连接对象的输出流构建数据输出流
			DataOutputStream ds = new DataOutputStream(conn.getOutputStream());
			// 以下写入请求报文的头部
			ds.writeBytes(hyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data; "
					+ "name=\"file\";filename=\"" + fileName + "\"" + end);
			ds.writeBytes(end);
			// 以下写入请求报文的主体
			byte[] buffer = new byte[1024];
			int length;
			// 先将文件数据写入到缓冲区，再将缓冲数据写入输出流
			while ((length = fis.read(buffer)) != -1) {
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			// 以下写入请求报文的尾部
			ds.writeBytes(hyphens + boundary + hyphens + end);
			ds.close(); // 关闭数据输出流
			// 打印HTTP调用的应答内容长度、内容类型、压缩方式
			System.out.println( String.format("应答内容长度=%s, 内容类型=%s, 压缩方式=%s", 
					conn.getHeaderField("Content-Length"), conn.getHeaderField("Content-Type"),
					conn.getHeaderField("Content-Encoding")) );
			// 对输入流中的数据解压和字符编码，得到原始的应答字符串
			String content = StreamUtil.getUnzipString(conn);
			// 打印HTTP上传的应答状态码和应答报文
			System.out.println( String.format("应答状态码=%d, 应答报文=%s", 
					conn.getResponseCode(), content) );
			conn.disconnect(); // 断开连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
