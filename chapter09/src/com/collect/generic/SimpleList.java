package com.collect.generic;

import java.util.List;

//定义简单的泛型清单。
//类名后面添加“<T>”，表示该类的内部代码中，所有的T类型都为外部需要时再指定的泛型。
//如果泛型不止一个，则用逗号隔开，比如两个泛型可用“<T, R>”。
public class SimpleList<T> {
	private List<T> list; // 清单。注意清单元素的数据类型为泛型T
	private String delimiter = ","; // 拼接字符串的分隔符

	// 构造方法，传入要保存的清单数据
	public SimpleList(List<T> list) {
		this.list = list;
	}
	
	// 获取当前保存的清单数据
	public List<T> getData() {
		return this.list;
	}

	// 设置分隔符
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	// 把清单元素通过分隔符拼接成一个字符串
	public String toString() {
		String result = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i > 0) {
					result = result + delimiter;
				}
				result = result + list.get(i).toString();
			}
		}
		return result;
	}

	// 获取长度最大的清单元素。注意这里的返回数据为泛型T
	public T getMaxLengthItem() {
		if (list == null || list.size() <= 0) {
			return null;
		}
		T t = list.get(0); // 利用T声明了一个泛型变量t
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).toString().length() > t.toString().length()) {
				t = list.get(i); // 把较长的元素保存到变量t
			}
		}
		return t;
	}

	// 获取长度最短的清单元素。注意这里的返回数据为泛型T
	public T getMinLengthItem() {
		if (list == null || list.size() <= 0) {
			return null;
		}
		T t = list.get(0); // 利用T声明了一个泛型变量t
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).toString().length() < t.toString().length()) {
				t = list.get(i); // 把较短的元素保存到变量t
			}
		}
		return t;
	}

}
