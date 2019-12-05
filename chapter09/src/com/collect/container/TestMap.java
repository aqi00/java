package com.collect.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;

//演示映射Map的用法
public class TestMap {

	public static void main(String[] arg) {
		testHashMap(); // 演示哈希图HashMap的用法
		testTreeMap(); // 演示红黑树TreeMap的用法
	}

	// 演示哈希图HashMap的用法
	private static void testHashMap() {
		// 创建一个哈希映射，该映射的键名为String类型，键值为MobilePhone类型
		HashMap<String, MobilePhone> map = new HashMap<String, MobilePhone>();
		map.put("米8", new MobilePhone("小米", 3000));
		map.put("Mate20", new MobilePhone("华为", 6000));
		map.put("荣耀10", new MobilePhone("荣耀", 2000));
		map.put("红米6", new MobilePhone("红米", 1000));
		map.put("OPPO R17", new MobilePhone("OPPO", 2800));
		// 第一种遍历方式：显式指针，即使用迭代器
		Set<Map.Entry<String, MobilePhone>> entry_set = map.entrySet();
		Iterator<Map.Entry<String, MobilePhone>> iterator = entry_set.iterator();
		while (iterator.hasNext()) { // 迭代器还有效时就持续遍历
			// 注意这里要先把入口取出来，这样才能分别getKey和getValue
			Map.Entry<String, MobilePhone> iterator_item = iterator.next();
			String key = iterator_item.getKey(); // 获取该键值对的键名
			MobilePhone value = iterator_item.getValue(); // 获取该键值对的键值
			System.out.println(String.format(
					"iterator_item key=%s, value=%s %d", key, value.getBrand(),
					value.getPrice()));
		}
		// 第二种遍历方式：隐式指针，即使用for循环
		for (Map.Entry<String, MobilePhone> for_item : map.entrySet()) {
			String key = for_item.getKey(); // 获取该键值对的键名
			MobilePhone value = for_item.getValue(); // 获取该键值对的键值
			System.out.println(String.format("for_item key=%s, value=%s %d",
					key, value.getBrand(), value.getPrice()));
		}
		// 第三种遍历方式：先获得键名的集合，再通过键名集合遍历整个映射
		Set<String> key_set = map.keySet(); // 注意：HashMap的keySet方法返回的是无序集合
		for (String key : key_set) {
			MobilePhone value = map.get(key); // 通过键名获取该键值对的键值
			System.out.println(String.format("set_item key=%s, value=%s %d",
					key, value.getBrand(), value.getPrice()));
		}
		// 第四种遍历方式：使用forEach方法夹带Lambda表达式
		map.forEach((key, value) -> System.out.println(String.format(
				"each_item key=%s, value=%s %d", key, value.getBrand(),
				value.getPrice())));
		checkSetType(key_set);
	}

	// 演示红黑树TreeMap的用法
	private static void testTreeMap() {
		// 创建一个红黑树映射，该映射的键名为String类型，键值为MobilePhone类型
		TreeMap<String, MobilePhone> map = new TreeMap<String, MobilePhone>();
		map.put("米8", new MobilePhone("小米", 3000));
		map.put("Mate20", new MobilePhone("华为", 6000));
		map.put("荣耀10", new MobilePhone("荣耀", 2000));
		map.put("红米6", new MobilePhone("红米", 1000));
		map.put("OPPO R17", new MobilePhone("OPPO", 2800));
		// 第三种遍历方式：先获得键名的集合，再通过键名集合遍历整个映射
		// 注意：TreeMap的keySet方法返回的是有序集合
		Set<String> key_set = map.keySet();
		for (String key : key_set) {
			// 获取该键值对的键值
			MobilePhone value = map.get(key);
			System.out.println(String.format("set_item key=%s, value=%s %d",
					key, value.getBrand(), value.getPrice()));
		}
		checkSetType(key_set);
	}

	// 检查某集合是有序还是无序的
	private static void checkSetType(Set<String> set) {
		if (set instanceof NavigableSet) {
			System.out.println("该集合为有序集合");
		} else {
			System.out.println("该集合为无序集合");
		}
	}

}
