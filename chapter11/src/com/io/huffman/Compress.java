package com.io.huffman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.LinkedList;

//压缩的工具类
public class Compress {
	public int[] lengthArray = new int[256]; // 各编码的长度数组
	public String[] codeArray = new String[256]; // 各编码的二进制数组
	public LinkedList<HuffmNode> tree = new LinkedList<HuffmNode>(); // 各编码组成的哈夫曼树

	// 初始化
	public Compress() {
		Arrays.fill(lengthArray, 0);
		Arrays.fill(codeArray, "");
	}

	// 统计次数
	private void countTimes(String path) {
		// 构造文件输入流
		try (FileInputStream fis = new FileInputStream(path)) {
			// 读取文件
			int value = fis.read();
			while (value != -1) {
				lengthArray[value]++;
				value = fis.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 构造哈夫曼树
	private HuffmNode createTree() {
		// 将次数作为权值构造森林
		for (int i = 0; i < lengthArray.length; i++) {
			if (lengthArray[i] != 0) {
				HuffmNode node = new HuffmNode(lengthArray[i], i);
				// 将构造好的节点加入到容器中的正确位置
				tree.add(getIndex(node), node);
			}
		}

		// 将森林（容器中的各个节点）构造成哈夫曼树
		while (tree.size() > 1) {
			// 获取容器中第一个元素（权值最小的节点）
			HuffmNode first = tree.removeFirst();
			// 获取中新的第一个元素，原来的第一个元素已经被移除了（权值次小的节点）
			HuffmNode second = tree.removeFirst();
			// 将权值最小的两个节点构造成父节点
			HuffmNode father = new HuffmNode(first.getData() + second.getData(), -1);
			father.setLeft(first);
			father.setRight(second);
			// 将父节点加入到容器中的正确位置
			tree.add(getIndex(father), father);
		}
		return tree.getFirst(); // 返回整颗树的根节点
	}

	// 利用前序遍历获取编码表
	private void getHuffmCode(HuffmNode root, String code) {
		if (root.getLeft() != null) { // 往左走，哈夫曼编码加0
			getHuffmCode(root.getLeft(), code + "0");
		}
		if (root.getRight() != null) { // 往右走，哈夫曼编码加1
			getHuffmCode(root.getRight(), code + "1");
		}
		// 如果是叶子节点，返回该叶子节点的哈夫曼编码
		if (root.getLeft() == null && root.getRight() == null) {
			System.out.println("ASCII码"+root.getIndex()+"的编码为："+code);
			codeArray[root.getIndex()] = code;
		}
	}
	
	private void compressFile(String srcPath, String destPath) {
		// 构建文件输出流
		try (FileOutputStream fos = new FileOutputStream(destPath);
				FileInputStream fis = new FileInputStream(srcPath);) {
			// 将整个哈夫曼编码以及每个编码的长度写入文件，也就是写入编码字典
			String code = "";
			for (int i = 0; i < 256; i++) {
				fos.write(codeArray[i].length());
				code += codeArray[i];
			}
			// 把哈夫曼编码写入文件
			// System.out.println("code="+code);
			String strForCode = "";
			int codeForFile;
			while (code.length() >= 8) {
				strForCode = code.substring(0, 8);
				codeForFile = changeStringToInt(strForCode);
				fos.write(codeForFile);
				code = code.substring(8);
			}
			// 处理最后一个不为8的数
			int last = 8 - code.length();
			for (int i = 0; i < last; i++) {
				code += "0";
			}
			strForCode = code.substring(0, 8);
			codeForFile = changeStringToInt(strForCode);
			fos.write(codeForFile);

			// 读原始文件，并将对应的哈夫曼编码串接成字符串
			int data = fis.read();
			String contentWithCode = "";
			while (data != -1) {
				contentWithCode += codeArray[data];
				data = fis.read();
			}
			//System.out.println(contentWithCode);

			String subStr = "";
			int recodeData;
			// 将字符串按照8位分割，每8位对应一个字节
			while (contentWithCode.length() >= 8) {
				subStr = contentWithCode.substring(0, 8);
				recodeData = changeStringToInt(subStr);
				fos.write(recodeData);
				contentWithCode = contentWithCode.substring(8);
			}
			// 最后不够8位添0
			int lastAppend = 8 - contentWithCode.length();
			for (int i = 0; i < lastAppend; i++) {
				contentWithCode += "0";
			}
			subStr = contentWithCode.substring(0, 8);
			recodeData = changeStringToInt(subStr);
			fos.write(recodeData);
			// 压缩后不够补0，lastAppend表示用于凑数的0的数量
			fos.write(lastAppend);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 压缩文件
	public void compress(String srcPath, String destPath) {
		// 统计文件中0-255出现的次数
		countTimes(srcPath);
		// 构造哈夫曼树，并得到根节点
		HuffmNode root = createTree();
		// 得到哈夫曼编码
		getHuffmCode(root, "");
		// 按照码表压缩文件
		compressFile(srcPath, destPath);
	}

	// 插入元素位置的索引
	public int getIndex(HuffmNode node) {
		for (int i = 0; i < tree.size(); i++) {
			if (node.getData() <= tree.get(i).getData()) {
				return i;
			}
		}
		return tree.size();
	}

	// 将二进制编码的字符串转换成整数
	public int changeStringToInt(String s) {
		int v1 = (s.charAt(0) - 48) * 128;
		int v2 = (s.charAt(1) - 48) * 64;
		int v3 = (s.charAt(2) - 48) * 32;
		int v4 = (s.charAt(3) - 48) * 16;
		int v5 = (s.charAt(4) - 48) * 8;
		int v6 = (s.charAt(5) - 48) * 4;
		int v7 = (s.charAt(6) - 48) * 2;
		int v8 = (s.charAt(7) - 48) * 1;
		return v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8;
	}
}
