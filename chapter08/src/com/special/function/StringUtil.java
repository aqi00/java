package com.special.function;

import java.util.Arrays;

//定义字符串工具类
public class StringUtil {

	// 根据过滤器StringFilter从字符串数组挑选符合条件的元素，并重组成新数组返回。
	// 其中StringFilter只校验完整的字符串。
	public static String[] select(String[] originArray, StringFilter filter) {
		int count = 0;
		String[] resultArray = new String[0];
		for (String str : originArray) { // 遍历所有字符串
			if (filter.isMatch(str)) { // 符合过滤条件
				count++;
				resultArray = Arrays.copyOf(resultArray, count); // 数组容量增大一个
				resultArray[count-1] = str; // 往数组末尾填入刚才找到的字符串
			}
		}
		return resultArray;
	}

	// 根据过滤器StringFilter2从字符串数组挑选符合条件的元素，并重组成新数组返回。
	// 其中StringFilter2根据标记串校验字符串。
	public static String[] select2(String[] originArray, StringFilter2 filter, String sign) {
		int count = 0;
		String[] resultArray = new String[0];
		for (String str : originArray) { // 遍历所有字符串
			if (filter.isMatch(str, sign)) { // 符合过滤条件
				count++;
				resultArray = Arrays.copyOf(resultArray, count); // 数组容量增大一个
				resultArray[count - 1] = str; // 往数组末尾填入刚才找到的字符串
			}
		}
		return resultArray;
	}

}
