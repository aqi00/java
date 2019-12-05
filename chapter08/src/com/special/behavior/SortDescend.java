package com.special.behavior;

import java.util.Comparator;

//定义一个整型数组的降序比较器
public class SortDescend implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		// return Integer.compare(o1, o2); // 默认的参数顺序是升序
		return Integer.compare(o2, o1); // 倒过来的参数顺序变成了降序
	}

}
