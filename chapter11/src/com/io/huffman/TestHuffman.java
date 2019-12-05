package com.io.huffman;

//演示如何利用哈夫曼算法来压缩文件
public class TestHuffman {
	
	public static void main(String[] args) {
		testCompress(); // 测试文件压缩的过程
		testDecompress(); // 测试文件解压的过程
	}
	
	// 测试文件压缩的过程
	private static void testCompress() {
		// 创建压缩工具
		Compress compress = new Compress();
		// 压缩文件
		compress.compress("E:/test/aaa.java", "E:/test/aaa.hfm");
		System.out.println("Compress Done");
	}
	
	// 测试文件解压的过程
	private static void testDecompress() {
		// 创建解压工具
		Decompress decompress = new Decompress();
		// 解压已压缩的目标文件
		decompress.decompress("E:/test/aaa.hfm", "E:/test/aaa2.java");
		System.out.println("Decompress Done");
	}
 
}
