package com.collect.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

//把LinkedHashMap改造成为LRU（最久未使用）的数据结构。使用了泛型类
public class LruMap<K, V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = -1L;

	private int maxSize = 6; // 最大容量

	public LruMap(int maxSize) {
		// LinkedHashMap构造方法的三个输入参数说明：
		// initialCapacity 初始容量
		// loadFactor 加载因子，一般是0.75f
		// accessOrder 排序规则。false 基于插入时间；true 基于访问时间
		super(0, 0.75f, true);
		if (maxSize > 0) { // 最大容量必须为自然数
			this.maxSize = maxSize;
		}
	}

	// 重写removeEldestEntry方法，当LRU中的元素多于上限时，删除最久未使用的元素
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		if (size() > maxSize) { // 队列中的元素个数超过了最大容量
			return true; // 返回true表示允许移除最久未使用的元素
		}
		return false;
	}

}
