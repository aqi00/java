package com.collect.algorithm;

//演示FIFO、LRU两种算法及其数据结构
public class TestQueue {

	public static void main(String[] args) {
		testFifo(); // 测试FIFO算法用到的数据结构
		testLru(); // 测试LRU算法用到的数据结构
	}

	// 测试FIFO算法（先进先出）用到的数据结构
	private static void testFifo() {
		// 声明一个容量为5的先进先出队列
		FifoList<Character> fifoList = new FifoList<Character>(5);
		//String str = "abcdefghijklfghlijf";
		String str = "先天下之忧而忧后天下之乐而乐天天快乐";
		for (int i = 0; i < str.length(); i++) {
			fifoList.add(str.charAt(i)); // 把字符加入先进先出队列
		}
		System.out.println("先进先出队列的大小为" + fifoList.size());
		System.out.println("先进先出队列的当前元素包括：" + fifoList);
	}

	// 测试LRU算法（最久未使用）用到的数据结构
	private static void testLru() {
		// 声明一个容量为5的最久未使用队列
		LruMap<Character, Integer> lruMap = new LruMap<Character, Integer>(5);
		//String str = "abcdefghijklfghlijf";
		String str = "先天下之忧而忧后天下之乐而乐天天快乐";
		for (int i = 0; i < str.length(); i++) {
			// 把字符加入最久未使用队列。其中键名为该字符，键值为序号
			lruMap.put(str.charAt(i), i);
		}
		System.out.println("最久未使用队列的大小为" + lruMap.size());
		System.out.println("最久未使用队列的当前元素包括：" + lruMap);
	}

}
