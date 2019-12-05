package com.io.huffman;

import java.io.FileInputStream;
import java.io.FileOutputStream;

//解压的工具类
public class Decompress {
	public int[] lengthArray = new int[256]; // 各编码的长度数组
	public String[] codeArray = new String[256]; // 各编码的二进制数组

	// 解压思路：1、读取文件里面的码表；2、得到码表；3、读取数据；4、还原数据
	public void decompress(String srcpath, String destpath) {
		try (FileInputStream fis = new FileInputStream(srcpath);
				FileOutputStream fos = new FileOutputStream(destpath);) {
			int singleLength; // 单个字符的编码长度
			int totalLength = 0; // 码表的总长度
			String code = "";
			// 还原码表
			for (int i = 0; i < lengthArray.length; i++) {
				singleLength = fis.read();
				lengthArray[i] = singleLength;
				if (singleLength != 0) {
					System.out.println("i="+i+",char="+((char)i)+",singleLength="+singleLength);
				}
				totalLength += lengthArray[i];
			}
			System.out.println("totalLength="+totalLength);

			// 将总长度除以8得到字节个数
			int len = totalLength / 8;
			// 如果不是8的倍数，则字节个数加1（对应压缩补0的情况）
			if ((totalLength) % 8 != 0) {
				len++;
			}
			// 读取哈夫曼编码
			for (int i = 0; i < len; i++) {
				// 把读到的整数转换成二进制
				code += changeIntToString(fis.read());
			}
			System.out.println("哈夫曼编码："+code);

			for (int i = 0; i < codeArray.length; i++) {
				// 如果第i个位置不为0 ，则说明第i个位置存储有哈夫曼编码
				if (lengthArray[i] != 0) {
					// 将得到的一串哈夫曼编码按照长度分割
					String sub = code.substring(0, lengthArray[i]);
					codeArray[i] = sub;
					code = code.substring(lengthArray[i]);
				} else { // 为0则没有对应的哈夫曼编码
					codeArray[i] = "";
				}
			}

			// 读取压缩的文件内容
			String codeContent = "";
			while (fis.available() > 1) {
				codeContent += changeIntToString(fis.read());
			}
			// 读取最后一个数字
			singleLength = fis.read();
			// 去掉最后补的0
			codeContent = codeContent.substring(0, codeContent.length() - singleLength);

			for (int i = 0; i < codeContent.length(); i++) {
				// 不断拉长tempCode，总会找到对应的编码
				String tempCode = codeContent.substring(0, i + 1);
				for (int j = 0; j < codeArray.length; j++) {
					if (codeArray[j].equals(tempCode)) {
						// System.out.println("截取的字符串："+tempCode);
						fos.write(j);
						codeContent = codeContent.substring(i + 1);
						// System.out.println("截取后剩余编码长度："+codeContent.length());
						i = -1; // 码表找到编码，则重置i
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 将整数转换成二进制编码的字符串
	public String changeIntToString(int value) {
		String s = "";
		for (int i = 0; i < 8; i++) {
			s = value % 2 + s;
			value = value / 2;
		}
		return s;
	}

}
