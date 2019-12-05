package com.network.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.Authenticator;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

//演示Java11新增的HttpClient用法
public class TestHttpClient {

	public static void main(String[] arg) {
		//testCallGet("https://hq.sinajs.cn/list=s_sh000001");
		//testCallPost("http://localhost:8080/NetServer/checkUpdate",
		//		"{\"package_list\":[{\"package_name\":\"com.qiyi.video\"}]}");
		//testSyncDownload("E:/", "https://img-blog.csdnimg.cn/2018112123554364.png");
		//testAsynDownload("E:/", "https://img-blog.csdnimg.cn/2018112123554364.png");
		//testSyncUpload("E:/bliss.jpg", "http://localhost:8080/NetServer/uploadServlet");
		testAsynUpload("E:/bliss.jpg", "http://localhost:8080/NetServer/uploadServlet");
	}

	// 对指定url发起GET调用
	private static void testCallGet(String url) {
//		// 创建一个自定义的HTTP客户端对象
//		HttpClient client = HttpClient.newBuilder()
//				.version(Version.HTTP_1_1) // 遵循HTTP协议的1.1版本
//				.followRedirects(Redirect.NORMAL) // 正常的重定向
//				.connectTimeout(Duration.ofMillis(5000)) // 连接的超时时间为5秒
//				.authenticator(Authenticator.getDefault()) // 默认的身份认证
//				.build(); // 根据建造器构建HTTP客户端对象
//		// 创建一个自定义的HTTP请求对象
//		HttpRequest request = HttpRequest.newBuilder()
//				.GET() // 调用方式为GET
//				.uri(URI.create(url)) // 待调用的url地址
//				.header("Accept-Language", "zh-CN") // 设置头部参数，中文文本
//				.timeout(Duration.ofMillis(5000)) // 请求的超时时间为5秒
//				.build(); // 根据建造器构建HTTP请求对象
		HttpClient client = HttpClient.newHttpClient(); // 创建默认的HTTP客户端对象
		// 创建默认的HTTP请求对象（默认GET调用）
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
		try {
			// 客户端传递请求信息，且返回字符串形式的应答报文
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			HttpHeaders headers = response.headers(); // 获取应答的所有头部属性
			// 打印HTTP调用的应答内容长度、内容类型、压缩方式
			System.out.println( String.format("应答内容长度=%s, 内容类型=%s, 压缩方式=%s",
					headers.firstValue("Content-Length").orElse(null),
					headers.firstValue("Content-Type").orElse(null),
					headers.firstValue("Content-Encoding").orElse(null)) );
			// 打印HTTP调用的应答状态码和应答报文
			System.out.println( String.format("应答状态码=%d, 应答报文=%s",
					response.statusCode(), response.body()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 对指定url发起POST调用
	private static void testCallPost(String url, String body) {
		System.out.println("请求报文="+body);
		HttpClient client = HttpClient.newHttpClient(); // 创建默认的HTTP客户端对象
		// 创建一个自定义的HTTP请求对象
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)) // 待调用的url地址
				.POST(BodyPublishers.ofString(body)) // 调用方式为POST，且请求报文为字符串
				.header("Content-Type", "application/json") // 设置头部参数，内容类型为json
				.build(); // 根据建造器构建HTTP请求对象
		try {
			// 客户端传递请求信息，且返回字符串形式的应答报文
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			// 打印HTTP调用的应答状态码和应答报文
			System.out.println( String.format("应答状态码=%d, 应答报文=%s",
					response.statusCode(), response.body()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 从指定url下载文件到本地（同步方式）
	private static void testSyncDownload(String path, String downloadUrl) {
		// 从下载地址中获取文件名
		String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
		HttpClient client = HttpClient.newHttpClient(); // 创建默认的HTTP客户端对象
		// 创建默认的HTTP请求对象（默认GET调用）
		HttpRequest request = HttpRequest.newBuilder(URI.create(downloadUrl)).build();
		try {
			// 客户端传递请求信息，且返回文件形式的应答报文
			HttpResponse<Path> response = client.send(request,
					BodyHandlers.ofFile(Paths.get(path + fileName)));
			HttpHeaders headers = response.headers(); // 获取应答的所有头部属性
			// 打印HTTP下载的应答内容长度、内容类型、压缩方式
			System.out.println( String.format("应答内容长度=%s, 内容类型=%s, 压缩方式=%s",
					headers.firstValue("Content-Length").orElse(null),
					headers.firstValue("Content-Type").orElse(null),
					headers.firstValue("Content-Encoding").orElse(null)) );
			// 打印HTTP下载的应答状态码和应答报文
			System.out.println( String.format("应答状态码=%d, 文件路径=%s",
					response.statusCode(), response.body().toString()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 从指定url下载文件到本地（异步方式）
	private static void testAsynDownload(String path, String downloadUrl) {
		// 从下载地址中获取文件名
		String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
		HttpClient client = HttpClient.newHttpClient(); // 创建默认的HTTP客户端对象
		// 创建默认的HTTP请求对象（默认GET调用）
		HttpRequest request = HttpRequest.newBuilder(URI.create(downloadUrl)).build();
		try {
			// 异步方式调用。sendAsync返回值类型为CompletableFuture<HttpResponse<T>>
			CompletableFuture<Path> result = client
					// 客户端发送异步请求，且返回文件形式的应答报文
					.sendAsync(request, BodyHandlers.ofFile(Paths.get(path + fileName)))
					// 把CompletableFuture<HttpResponse<T>>类型映射为CompletableFuture<Path>类型
					.thenApply(HttpResponse::body);
			// 打印下载完的本地文件路径
			System.out.println("下载完的本地文件路径="+result.get().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 把本地文件上传给指定url（同步方式）
	private static void testSyncUpload(String filename, String uploadUrl) {
		HttpClient client = HttpClient.newHttpClient(); // 创建默认的HTTP客户端对象
		// 官方的HttpClient并没有提供类似WebClient那种现成的BodyInserters.fromMultipartData方法，因此这里需要自己转换
		// Apache推出的HttpClient的下载页面是 http://hc.apache.org/downloads.cgi
		// 根据指定文件创建二进制形式的文件体对象
		FileBody fileBody = new FileBody(new File(filename), ContentType.DEFAULT_BINARY);
		String boundary = "WUm4580jbtwfJhNp7zi1djFEO3wNNm"; // 边界字符串
		// 创建用于网络传输的HTTP实体对象
		HttpEntity entity = MultipartEntityBuilder.create() // 分段实体
				.addPart("file", fileBody) // 添加文件体
				.setBoundary(boundary) // 设置边界字符串
				.build();
		// 创建字节数组输出流
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			entity.writeTo(baos); // 把HTTP实体对象写入字节数组输出流
			// 创建一个自定义的HTTP请求对象
			HttpRequest request = HttpRequest.newBuilder(URI.create(uploadUrl)) // 待上传的url地址
					// 设置头部参数，要求分段传输，并且各段之间以边界字符串隔开
					.header("Content-Type", "multipart/form-data; boundary=" + boundary)
					// 调用方式为POST，且请求报文为字节数组
					.POST(BodyPublishers.ofByteArray(baos.toByteArray())).build();
					//.POST(BodyPublishers.ofFile(Paths.get(filename))).build(); // 不能用
			// 客户端传递请求信息，且返回字符串形式的应答报文
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			// 打印HTTP上传的应答状态码和应答报文
			System.out.println( String.format("应答状态码=%d, 应答报文=%s",
					response.statusCode(), response.body()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 把本地文件上传给指定url（异步方式）
	private static void testAsynUpload(String filename, String uploadUrl) {
		HttpClient client = HttpClient.newHttpClient(); // 创建默认的HTTP客户端对象
		// 官方的HttpClient并没有提供类似WebClient那种现成的BodyInserters.fromMultipartData方法，因此这里需要自己转换
		// Apache推出的HttpClient的下载页面是 http://hc.apache.org/downloads.cgi
		// 根据指定文件创建二进制形式的文件体对象
		FileBody fileBody = new FileBody(new File(filename), ContentType.DEFAULT_BINARY);
		String boundary = "WUm4580jbtwfJhNp7zi1djFEO3wNNm"; // 边界字符串
		// 创建用于网络传输的HTTP实体对象
		HttpEntity entity = MultipartEntityBuilder.create() // 分段实体
				.addPart("file", fileBody) // 添加文件体
				.setBoundary(boundary) // 设置边界字符串
				.build();
		// 创建字节数组输出流
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			entity.writeTo(baos); // 把HTTP实体对象写入字节数组输出流
			// 创建一个自定义的HTTP请求对象
			HttpRequest request = HttpRequest.newBuilder(URI.create(uploadUrl)) // 待上传的url地址
					// 设置头部参数，要求分段传输，并且各段之间以边界字符串隔开
					.header("Content-Type", "multipart/form-data; boundary=" + boundary)
					// 调用方式为POST，且请求报文为字节数组
					.POST(BodyPublishers.ofByteArray(baos.toByteArray())).build();
			// 异步方式调用。sendAsync返回值类型为CompletableFuture<HttpResponse<T>>
			CompletableFuture<String> result = client
					// 客户端发送异步请求，且返回字符串形式的应答报文
					.sendAsync(request, BodyHandlers.ofString())
					// 把CompletableFuture<HttpResponse<T>>类型映射为CompletableFuture<Path>类型
					.thenApply(HttpResponse::body);
			System.out.println("文件上传的应答报文="+result.get()); // 打印上传完的应答报文内容
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
