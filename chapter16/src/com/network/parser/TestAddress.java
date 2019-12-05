package com.network.parser;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;

//演示url地址的格式校验与文本转义
public class TestAddress {

	public static void main(String[] arg) {
		testUrl("https://news.cn");
		testUrl("http://hq.sinajs.cn:8080/list=s_sh000001");
		testUrl("https://img-blog.csdnimg.cn/2018112123554364.png");
		testUrl("http://yx12.fjjcjy.com/Public/Control/GetValidateCode?time=123");
		testUrl("https://item.jd.com/12481514.html#none");
		
//		testHost("news.cn");
//		testHost("220.181.12.208");
//		testHost("pop3.163.com");
//		testHost("news.hello");
		
		// https://www.baidu.com/s?wd=这是什么声音，请求参数里的参数值需要进行编码转义使之符合URL格式规范
//		String encodeHanzi = testEncoder("这是什么声音");
//		String decodeHanzi = testDecoder(encodeHanzi);
//		String encodeFuhao = testEncoder("\t\n\r !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~");
//		String decodeFuhao = testDecoder(encodeFuhao);
		testDecoder("a%20b%2Ac%2Dd%2Ee%5Ff");
//		testEncoder("你 好");
	}
	
	// 测试某个网址字符串的格式
	private static void testUrl(String address) {
		try {
			URL url = new URL(address); // 根据网址字符串构建URL对象
			System.out.println("address : "+address);
			String protocol = url.getProtocol(); // 获取URL对象采用的网络协议
			System.out.println("	protocol : "+protocol);
			String host = url.getHost(); // 获取URL对象的域名（主机名称）
			System.out.println("	host : "+host);
			int defaultPort = url.getDefaultPort(); // 获取URL对象的默认端口
			System.out.println("	defaultPort : "+defaultPort);
			String path = url.getPath(); // 获取URL对象的路径（不包括域名）
			System.out.println("	path : "+path);
			int port = url.getPort(); // 获取URL对象的指定端口（若不显示指定则返回-1）
			System.out.println("	port : "+port);
			String authority = url.getAuthority(); // 获取URL对象的授权部门（由域名和指定端口组成）
			System.out.println("	authority : "+authority);
			String ref = url.getRef(); // 获取URL对象的引用位置
			System.out.println("	ref : "+ref);
			String query = url.getQuery(); // 获取URL对象的请求参数
			System.out.println("	query : "+query);
			String filename = url.getFile(); // 获取URL对象的文件名（由路径和请求参数组成）
			System.out.println("	filename : "+filename);
			// 打开URL对象的网络连接，并返回HttpURLConnection连接对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试域名的可用信息。返回true表示域名合法，返回false表示域名非法
	private static boolean testHost(String host) {
		try {
			// 根据域名或IP获得对应的网络地址对象
			InetAddress inet = InetAddress.getByName(host);
			System.out.println("host = "+host);
			// 获取网络地址对象的IP地址
			String hostAddress = inet.getHostAddress();
			System.out.println("	hostAddress = "+hostAddress);
			// 获取网络地址对象的域名
			String hostName = inet.getHostName();
			System.out.println("	hostName = "+hostName);
			// 检查对方主机是否能连得上。但该方法不可靠，因为可能由于存在防火墙导致返回false
			boolean isReachable = inet.isReachable(3000);
			System.out.println("	isReachable = "+isReachable);
		} catch (UnknownHostException e) {
			// 如果host字符串并非合法的域名/IP，则getByName方法会扔出“未知的域名异常”
			e.printStackTrace();
			return false; // 返回false表示该字符串不是合法的域名/IP
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true; // 返回true表示该字符串是合法的域名/IP
	}
	
	// 对原始字符串进行URL编码
	private static String testEncoder(String origin) {
		System.out.println("原始字符串 : "+origin);
		String encoded = URLEncoder.encode(origin);  // 获得URL编码后的转义字符串
		System.out.println("转义字符串 : "+encoded);
		return encoded;
	}

	// 对转义字符串进行URL解码
	private static String testDecoder(String encoded) {
		System.out.println("转义字符串 : "+encoded);
		String origin = URLDecoder.decode(encoded);  // 获得URL解码后的原始字符串
		System.out.println("原始字符串 : "+origin);
		return origin;
	}

}
