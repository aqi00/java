package com.collect.container;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

//演示集合Set的用法
public class TestSet {

	public static void main(String[] arg) {
		testHashSetSimple(); // 演示哈希集合HashSet如何存取系统自带的数据类型
		testTreeSetSimple(); // 演示二叉集合TreeSet如何存取系统自带的数据类型
		testHashSetPhone(); // 演示哈希集合HashSet如何存取程序员自定义的数据类型
		testTreeSetPhone(); // 演示二叉集合TreeSet如何存取程序员自定义的数据类型
	}

	// 演示哈希集合HashSet如何存取系统自带的数据类型
	private static void testHashSetSimple() {
		// HashSet是无序集合
		HashSet<String> set = new HashSet<String>(); // 创建字符串类型的哈希集合
		set.add("hello"); // 往集合中添加一个元素
		set.add("world"); // 往集合中添加一个元素
		set.add("how"); // 往集合中添加一个元素
		set.add("are"); // 往集合中添加一个元素
		set.add("you"); // 往集合中添加一个元素
		System.out.println("set.size()=" + set.size());
		// 第一种遍历方式：简化的for循环同样适用于数组和容器
		for (String hash_item : set) {
			System.out.println("hash_item=" + hash_item);
		}
		// 第二种遍历方式：利用迭代器循环遍历集合。
		// 迭代器又称指示器，其作用类似于数据库的游标、C语言的指针
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) { // 迭代器后方是否存在元素
			String hash_iterator = (String) iterator.next(); // 获取迭代器后方的元素
			System.out.println("hash_iterator=" + hash_iterator);
		}
		// 第三种遍历方式：使用forEach方法夹带Lambda表达式进行遍历
		set.forEach(hash_each -> System.out.println("hash_each=" + hash_each));
		// set.clear(); // 清空集合
		// set.contains(o); // 判断集合是否包含指定元素
		// set.isEmpty(); // 判断集合是否为空
		// set.remove(o); // 从集合中删除指定元素
		// set.size(); // 获取集合的大小（即所包含元素的个数）
	}

	// 演示二叉集合TreeSet如何存取系统自带的数据类型
	private static void testTreeSetSimple() {
		// TreeSet是有序集合
		TreeSet<String> set = new TreeSet<String>(); // 创建字符串类型的二叉集合
		set.add("hello"); // 往集合中添加一个元素
		set.add("world"); // 往集合中添加一个元素
		set.add("how"); // 往集合中添加一个元素
		set.add("are"); // 往集合中添加一个元素
		set.add("you"); // 往集合中添加一个元素
		System.out.println("set.size()=" + set.size());
		// 第一种遍历方式：简化的for循环同样适用于数组和容器
		for (String tree_item : set) {
			System.out.println("tree_item=" + tree_item);
		}
		// 第二种遍历方式：利用迭代器循环遍历集合。
		// 迭代器又称指示器，其作用类似于数据库的游标、C语言的指针
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) { // 迭代器后方是否存在元素
			// 获取迭代器后方的元素
			String tree_iterator = (String) iterator.next();
			System.out.println("tree_iterator=" + tree_iterator);
		}
		// 第三种遍历方式：使用forEach方法夹带Lambda表达式
		set.forEach(tree_each -> System.out.println("tree_each=" + tree_each));
	}

	// 演示哈希集合HashSet如何存取程序员自定义的数据类型
	private static void testHashSetPhone() {
		// HashSet是无序集合
		HashSet<MobilePhoneHash> set = new HashSet<MobilePhoneHash>();
		set.add(new MobilePhoneHash("华为", 5000));
		set.add(new MobilePhoneHash("小米", 2000));
		set.add(new MobilePhoneHash("OPPO", 4000));
		set.add(new MobilePhoneHash("vivo", 1000));
		set.add(new MobilePhoneHash("vivo", 1000));
		// 简化的for循环同样适用于数组和容器
		for (MobilePhoneHash hash_item : set) {
			System.out.println("hash_item brand=" + hash_item.getBrand()
					+ ", price=" + hash_item.getPrice());
		}
	}

	// 演示二叉集合TreeSet如何存取程序员自定义的数据类型
	private static void testTreeSetPhone() {
		// TreeSet是有序集合
		TreeSet<MobilePhoneTree> set = new TreeSet<MobilePhoneTree>();
		set.add(new MobilePhoneTree("华为", 5000));
		set.add(new MobilePhoneTree("小米", 2000));
		set.add(new MobilePhoneTree("OPPO", 4000));
		set.add(new MobilePhoneTree("vivo", 1000));
		set.add(new MobilePhoneTree("vivo", 1000));
		// 简化的for循环同样适用于数组和容器
		for (MobilePhoneTree tree_item : set) {
			System.out.println("tree_item brand=" + tree_item.getBrand()
					+ ", price=" + tree_item.getPrice());
		}
	}

}
