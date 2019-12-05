package com.string;

import java.util.Arrays;

//演示查找字符串的几种算法
public class KmpSearch {

	public static void main(String[] arg) {
		String src_cn = "吃葡萄不吐葡萄皮，不吃葡萄倒吐葡萄皮。";
		String dest_cn = "葡萄";
		int[] posArray = findString(src_cn, dest_cn);
		for (int pos : posArray) {
			System.out.println("pos = " + pos);
		}
		String src_en = "BBBBBBABCVVVABCDEABCFABCDEYYYYYYABCDEYYYYYABCDEABCFABCDEGGG";
		String dest_en = "ABCDEABCFABCDE";
		// 利用BF算法查找字符串
		int[] posArrayByBF = findStringByBF(src_en, dest_en);
		for (int pos : posArrayByBF) {
			System.out.println("pos by BF = " + pos);
		}
		// 利用KMP算法查找字符串
		int[] posArrayByKMP = findStringByKMP(src_en, dest_en);
		for (int pos : posArrayByKMP) {
			System.out.println("pos by KMP = " + pos);
		}
	}

	// 利用字符串变量的indexOf方法依次找到子串分布的各处位置
	private static int[] findString(String src, String dest) {
		int[] posArray = new int[0]; // 子串所处的位置数组
		int pos = 0; // 查找位置
		for (int count = 1; pos >= 0; count++) {
			pos = src.indexOf(dest, pos); // 从上次位置开始查找子串
			if (pos < 0) { // 未找到子串，退出循环
				break;
			}
			posArray = Arrays.copyOf(posArray, count); // 位置数组长度加一
			posArray[count - 1] = pos; // 填入刚发现的子串位置
			pos += dest.length(); // 把查找位置往后移动
		}
		return posArray;
	}

	// 利用BF算法查找字符串
	private static int[] findStringByBF(String src, String dest) {
		int total_count = 0;
		char[] srcArray = src.toCharArray(); // 把源串转为字符数组
		char[] destArray = dest.toCharArray(); // 把目标子串转为字符数组
		int[] posArray = new int[0]; // 目标串所处的位置数组
		int count = 0; // 目标串匹配的成功次数
		// 以下对源串与目标串展开逐位的字符比较
		loop: for (int i = 0; i < srcArray.length; i++, total_count++) {
			for (int j = 0; j < destArray.length; j++, total_count++) {
				if (srcArray[i + j] != destArray[j]) { // 目标串匹配失败
					continue loop; // 继续源串的下一位置比较
				} else if (j == destArray.length - 1) { // 目标串匹配成功
					count++;
					posArray = Arrays.copyOf(posArray, count); // 位置数组长度加一
					posArray[count - 1] = i; // 填入刚发现的目标串位置
				}
			}
		}
		System.out.println("BF算法的总比较次数 = " + total_count);
		return posArray;
	}

	// 利用KMP算法查找字符串
	private static int[] findStringByKMP(String src, String dest) {
		int total_count = 0;
		char[] srcArray = src.toCharArray(); // 把源串转为字符数组
		char[] destArray = dest.toCharArray(); // 把目标子串转为字符数组
		// 创建与目标串等长的匹配标志串
		int[] matchArray = new int[destArray.length];
		Arrays.fill(matchArray, 0); // 给标志串填0初始化
		// 首先遍历一次目标串，填充标志串中的匹配位置
		for (int i = 0; i < destArray.length; i++) {
			for (int j = i + 1; j < destArray.length; j++, total_count++) {
				if (destArray[i] == destArray[j]) {
					matchArray[j] = matchArray[j - 1] + 1;
				}
			}
		}
		for (int match : matchArray) {
			System.out.print(match+" ");
		}
		System.out.println();
		System.out.println("KMP算法的关键词比较次数 = " + total_count);
		// for (int match : matchArray) {
		// System.out.println("match = "+match);
		// }
		// 然后再到源串中查找目标串
		int[] posArray = new int[0]; // 目标串所处的位置数组
		int count = 0; // 目标串匹配的成功次数
		loop: for (int i = 0; i < srcArray.length; i++, total_count++) {
			for (int j = 0; j < destArray.length; j++, total_count++) {
				if (srcArray[i + j] != destArray[j]) { // 目标串匹配失败
					// 如果j等于0，表示该位置一开始就不匹配，此时按照普通流程后移一位再判断。
					// 如果j大于0，表示目标串存在部分匹配，此时根据匹配标志串来决定后移的位数。
					if (j > 0) {
						i--;
						i = i + j - matchArray[j];
					}
					continue loop;
				} else if (j == destArray.length - 1) { // 已找到完全匹配的目标串
					count++;
					posArray = Arrays.copyOf(posArray, count); // 位置数组长度加一
					posArray[count - 1] = i; // 填入刚发现的目标串位置
				}
			}
		}
		System.out.println("KMP算法的总比较次数 = " + total_count);
		return posArray;
	}
}
