package com.collect.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

//演示清单List的用法
public class TestList {

	public static void main(String[] arg) {
		testArrayList(); // 演示ArrayList列表（又称动态数组）的用法
		testLinkedList(); // 演示LinkedList链表（又称双端队列）的用法
	}

	// 演示ArrayList列表（又称动态数组）的用法
	private static void testArrayList() {
		// 创建一个列表（动态数组），其元素为MobilePhone类型
		ArrayList<MobilePhone> list = new ArrayList<MobilePhone>();
		list.add(new MobilePhone("华为", 5000)); // 第一个添加的元素，默认分配序号为0
		list.add(new MobilePhone("小米", 2000)); // 第二个添加的元素，默认分配序号为1
		list.add(new MobilePhone("OPPO", 4000)); // 第三个添加的元素，默认分配序号为2
		list.add(new MobilePhone("vivo", 1000)); // 第四个添加的元素，默认分配序号为3
		list.add(new MobilePhone("vivo", 1000)); // 第五个添加的元素，默认分配序号为4
		// 第一种遍历方式：简化的for循环同样适用于数组和容器
		for (MobilePhone for_item : list) {
			System.out.println(String.format("for_item:%s %d",
					for_item.getBrand(), for_item.getPrice()));
		}
		// 第二种遍历方式：利用迭代器循环遍历列表。
		// 迭代器又称指示器，其作用类似于数据库的游标、C语言的指针
		Iterator<MobilePhone> iterator = list.iterator();
		while (iterator.hasNext()) { // 迭代器后方是否存在元素
			// 获取迭代器后方的元素
			MobilePhone iterator_item = (MobilePhone) iterator.next();
			System.out.println(String.format("iterator_item:%s %d",
					iterator_item.getBrand(), iterator_item.getPrice()));
		}
		// 第三种遍历方式：与数组通过下标访问相似，列表通过索引获取指定位置的元素
		for (int i = 0; i < list.size(); i++) {
			MobilePhone index_item = list.get(i); // 获取指定下标位置的元素
			System.out.println(String.format("index_item:%s %d",
					index_item.getBrand(), index_item.getPrice()));
			// list.add(index, element); // 在指定位置添加元素
			// list.set(index, element); // 修改指定位置的元素
			// list.remove(index); // 移除指定位置的元素
			// list.subList(fromIndex, toIndex); // 从指定的开始位置和结束位置中间提取子列表
		}
		// 第四种遍历方式：使用forEach方法夹带Lambda表达式
		list.forEach(each_item -> System.out.println(String.format(
				"each_item:%s %d", each_item.getBrand(), each_item.getPrice())));
	}

	// 演示LinkedList链表（又称双端队列）的用法
	private static void testLinkedList() {
		// 创建一个链表（双端队列），其元素为MobilePhone类型
		LinkedList<MobilePhone> list = new LinkedList<MobilePhone>();
		list.add(new MobilePhone("华为", 5000));
		list.add(new MobilePhone("小米", 2000));
		list.add(new MobilePhone("OPPO", 4000));
		list.add(new MobilePhone("vivo", 1000));
		// 以下方法扩展了清单List的功能
		// list.addFirst(e); // 添加到清单开头
		// list.addLast(e); // 添加到清单末尾
		// list.removeFirst(); // 删除清单开头的元素
		// list.removeLast(); // 删除清单末尾的元素
		// list.getFirst(); // 获取清单开头的元素
		// list.getLast(); // 获取清单末尾的元素
		// 以下方法实现了队列Queue的功能
		// list.offer(e); // 添加到队列末尾
		// list.offerFirst(e); // 添加到队列开头
		// list.offerLast(e); // 添加到队列末尾
		// list.peek(); // 获取队列开头的元素
		// list.peekFirst(); // 获取队列开头的元素
		// list.peekLast(); // 获取队列末尾的元素
		// list.poll(); // 删除队列开头的元素
		// list.pollFirst(); // 删除队列开头的元素
		// list.pollLast(); // 删除队列末尾的元素
		// 以下方法实现了栈Stack的功能
		// list.pop(); // 队列开头的元素出栈，相当于方法removeFirst和pollFirst
		// list.push(e); // 新元素入栈，相当于方法addFirst和offerFirst
		// 第一种遍历方式：简化的for循环同样适用于数组和容器
		for (MobilePhone link_item : list) {
			System.out.println(String.format("link_item:%s %d",
					link_item.getBrand(), link_item.getPrice()));
		}
	}

}
