package com.io.nio;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

//演示路径Path与Files工具的用法
public class TestFiles {
	private static String mFileName = "E:/test/aah.txt";
	private static String mDirName = "F:/StudioProjects/Kotlin/hello";
	
	public static void main(String[] arg) {
		testPath(); // 演示Path类的用法
		testFiles(); // 演示Files工具的用法
		openChannelFromPath(); // 通过Path打开文件通道
		selectFilesByList(); // 演示如何使用list方法
		selectFilesByWalk(); // 演示如何使用walk方法
	}
	
	// 演示Path类的用法
	private static void testPath() {
		Path path = Paths.get(mDirName); // 根据指定的文件路径字符串获得对应的Path对象
		boolean endsWith = path.endsWith("txt"); // 判断当前路径是否以指定字符串结尾
		System.out.println("endsWith="+endsWith);
		boolean startsWith = path.startsWith("txt"); // 判断当前路径是否以指定字符串开头
		System.out.println("startsWith="+startsWith);
		File file = path.toFile(); // 把Path对象转换为File对象
		String pathName = path.toString(); // 把Path对象转换为路径字符串
		System.out.println("pathName="+pathName);
		// 拼接文件路径，在当前路径的末尾添加指定字符串，并返回新的文件路径
		Path newPath = path.resolve(Paths.get("network"));
		System.out.println("newPath="+newPath);
		//path.getParent(); // 获取上级目录的Path对象
	}
	
	// 演示Files工具的用法
	private static void testFiles() {
//		Files.exists(path); // 判断该路径是否存在
//		Files.isDirectory(path); // 判断该路径是否为目录
//		Files.isExecutable(path); // 判断该路径是否允许执行
//		Files.isHidden(path); // 判断该路径是否隐藏
//		Files.isReadable(path); // 判断该路径是否可读
//		Files.isWritable(path); // 判断该路径是否可写
		
//		Files.copy(source, target); // 把文件从源路径复制到目标路径
//		Files.createDirectory(dir); // 如果该路径是个目录，就创建新目录
//		Files.createFile(path); // 如果该路径是个文件，就创建新文件
//		Files.delete(path); // 如果该路径是文件或者空目录，就把它删掉。如果该路径不存在或者目录非空，就扔出异常
//		Files.deleteIfExists(path); // 如果该路径是文件或者空目录，就把它删掉（路径不存在也不报错）。但若目录非空，还是扔出异常
//		Files.move(source, target); // 把文件从源路径移动到目标路径
//		Files.size(path); // 获取该路径的文件大小。如果该路径是文件，则返回文件大小；如果该路径是目录，则返回目录基本信息的大小，而非整个目录的大小。
		
		// Java8之后增加
//		Files.readAllLines(path); // 获取该文件的所有内容行，返回的是字符串清单
//		Files.lines(path); // 获取该文件的所有内容行，返回的是字符串流
//		Files.list(dir); // 获取该目录下的所有文件与目录，但不包括子目录的下级内容，返回的是路径流
//		Files.walk(start, maxDepth); // 获取该目录下的所有文件与目录，且包括指定深度子目录的下级内容，返回的是路径流
	}
	
	// 通过Path打开文件通道
	private static void openChannelFromPath() {
		try {
			Path path = Paths.get(mFileName); // 根据指定的文件路径字符串获得对应的Path对象
			// 创建文件通道的第三种方式：通过Path打开文件的只读通道。open方法不加选项参数的话，默认是只读权限
			FileChannel readChannel = FileChannel.open(path, StandardOpenOption.READ);
			readChannel.close(); // 关闭读通道
			// 创建文件通道的第三种方式：通过Path打开文件的写入通道。
			// open方法的第二个参数指定了文件以只读方式还是以可写方式打开
			FileChannel writeChannel = FileChannel.open(path, StandardOpenOption.WRITE);
			writeChannel.close(); // 关闭写通道
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据文件路径获取Path对象。如果指定路径的文件不存在，就创建一个新文件。
	private static Path getPath(String filename) {
		Path path = Paths.get(filename); // 根据指定的文件路径字符串获得对应的Path对象
		if (!Files.exists(path)) { // 该文件路径并不存在
			try {
				Files.createFile(path); // 在该路径创建新文件
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	// 演示如何使用list方法
	private static void selectFilesByList() {
		Path path = Paths.get(mDirName); // 根据指定的文件路径字符串获得对应的Path对象
		try {
			// 计算该目录下（不包含子目录）的所有文件与目录的总数
			long listCount = Files.list(path).count();
			System.out.println("listCount="+listCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 演示如何使用walk方法
	private static void selectFilesByWalk() {
		try {
			Path path = Paths.get(mDirName); // 根据指定的文件路径字符串获得对应的Path对象
			// 遍历该目录以及深度在五之内的子目录，计算其下所有文件与目录的总数
			long count = Files.walk(path, 5).count();
			System.out.println("count="+count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Path path = Paths.get(mDirName); // 根据指定的文件路径字符串获得对应的Path对象
			// 遍历该目录以及深度在五之内的子目录，并返回其下所有目录的路径名称清单
			List<String> dirs = Files.walk(path, 5)
					//.filter(it -> it.toFile().isDirectory()) // 只挑选目录（Lambda表达式）
					.filter(Files::isDirectory) // 只挑选目录（方法引用）
					.map(it -> it.toString()) // 获取目录的路径名称
					.collect(Collectors.toList()); // 返回清单格式
			System.out.println("dirs="+dirs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Path path = Paths.get(mDirName); // 根据指定的文件路径字符串获得对应的Path对象
			// 遍历该目录以及深度在五之内的子目录，并返回其下所有png文件的路径名称清单
			List<String> pngs = Files.walk(path, 5)
					.filter(it -> it.toFile().isFile()) // 只挑选文件
					.filter(it -> it.endsWith(".png")) // 挑出扩展名为png的文件
					.map(it -> it.toString()) // 获取目录的路径名称
					.collect(Collectors.toList()); // 返回清单格式
			System.out.println("pngs="+pngs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
