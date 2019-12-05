package com.collect.algorithm;

import java.util.LinkedList;

//把LinkedList改进成为FIFO（先进先出）的数据结构。使用了泛型类
public class FifoList<T> extends LinkedList<T> {
	private static final long serialVersionUID = -1L;

	private int maxSize = 6; // 最大容量

	public FifoList(int maxSize) {
		super();
		if (maxSize > 0) { // 最大容量必须为自然数
			this.maxSize = maxSize;
		}
	}

	// 给新的小伙伴排队
	public boolean add(T new_item) {
		addLast(new_item); // 把新的小伙伴加到队列末尾
		return true;
	}

	// 给新的小伙伴排队，加到队列末尾
	public void addLast(T new_item) {
		for (T item : this) { // 已在队列中的小伙伴无需处理
			if (item.equals(new_item)) { // 队列中已存在该小伙伴
				return; // 无需处理已存在的小伙伴，直接返回
			}
		}
		if (this.size() >= this.maxSize) { // 超过了最大容量
			this.removeFirst(); // 移除双端队列开头的小伙伴
		}
		super.addLast(new_item); // 往双端队列末尾插入新的小伙伴
	}

}
