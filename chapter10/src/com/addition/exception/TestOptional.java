package com.addition.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

//演示Optional在空指针判断中的运用
public class TestOptional {

	public static void main(String[] args) {
		// testNormalList(); // 测试正常的清单
		// testNullList(); // 测试空的清单
		testListWithNullItem(); // 测试包含某个空元素的清单
		// testListWithNullColor(); // 测试清单里面，某元素的颜色字段为空
	}

	// 测试正常的清单
	private static void testNormalList() {
		getRedAppleWithStream(getAppleList());
	}

	// 测试空的清单
	private static void testNullList() {
		// getRedAppleWithStream(null);
		getRedAppleWithOptionalThree(null);
	}

	// 测试包含某个空元素的清单
	private static void testListWithNullItem() {
		ArrayList<Apple> list = getAppleList();
		list.set(0, null);
		// getRedAppleWithStream(list);
		// getRedAppleWithNull(list);
		// getRedAppleWithOptionalTwo(list);
		getRedAppleWithOptionalThree(list);
	}

	// 测试清单里面，某元素的颜色字段为空
	private static void testListWithNullColor() {
		ArrayList<Apple> list = getAppleList();
		Apple item = list.get(0);
		item.setColor(null);
		list.set(0, item);
		// getRedAppleWithStream(list);
		// getRedAppleWithNull(list);
		// getRedAppleWithOptionalOne(list);
		getRedAppleWithOptionalThree(list);
	}

	// 获取默认的苹果清单
	private static ArrayList<Apple> getAppleList() {
		ArrayList<Apple> appleList = new ArrayList<Apple>();
		appleList.add(new Apple("红苹果", "RED", 150d, 10d));
		appleList.add(new Apple("大苹果", "green", 250d, 10d));
		appleList.add(new Apple("红苹果", "red", 300d, 10d));
		appleList.add(new Apple("大苹果", "yellow", 200d, 10d));
		appleList.add(new Apple("红苹果", "green", 100d, 10d));
		appleList.add(new Apple("大苹果", "Red", 250d, 10d));
		return appleList;
	}

	// 通过简单的for循环挑出红苹果清单
	private static void getRedAppleWithFor(List<Apple> list) {
		List<Apple> redAppleList = new ArrayList<Apple>();
		for (Apple apple : list) { // 遍历现有的苹果清单
			if (apple.isRedApple()) { // 判断是否为红苹果
				redAppleList.add(apple);
			}
		}
		System.out.println("for循环 红苹果清单=" + redAppleList.toString());
	}

	// 通过流式处理挑出红苹果清单
	private static void getRedAppleWithStream(List<Apple> list) {
		// 挑出红苹果清单
		List<Apple> redAppleList = list.stream() // 串行处理
				.filter(Apple::isRedApple) // 过滤条件。专门挑选红苹果
				.collect(Collectors.toList()) // 返回一串清单
		;
		System.out.println("流式处理 红苹果清单=" + redAppleList.toString());
	}

	// 在for循环的内外添加必要的空指针校验
	private static void getRedAppleWithNull(List<Apple> list) {
		List<Apple> redAppleList = new ArrayList<Apple>();
		if (list != null) { // 判断清单非空
			for (Apple item : list) { // 遍历现有的苹果清单
				if (item != null) { // 判断该记录非空
					if (item.getColor() != null) { // 判断颜色字段非空
						if (item.isRedApple()) { // 判断是否为红苹果
							redAppleList.add(item);
						}
					}
				}
			}
		}
		System.out.println("加空指针判断 红苹果清单=" + redAppleList.toString());
	}

	// 把for循环的内部代码改写为Optional校验方式
	private static void getRedAppleWithOptionalOne(List<Apple> list) {
		List<Apple> redAppleList = new ArrayList<Apple>();
		if (list != null) { // 判断清单非空
			for (Apple item : list) { // 遍历现有的苹果清单
				if (Optional.ofNullable(item) // 构造一个可空对象
						.map(apple -> apple.isRedApple()) // map指定了item非空时候的取值
						.orElse(false)) { // orElse指定了item为空时候的取值
					redAppleList.add(item);
				}
			}
		}
		System.out.println("Optional1判断 红苹果清单=" + redAppleList.toString());
	}

	// 把清单的非空判断代码改写为Optional校验方式
	private static void getRedAppleWithOptionalTwo(List<Apple> list) {
		List<Apple> redAppleList = new ArrayList<Apple>();
		Optional.ofNullable(list) // 构造一个可空对象
			.ifPresent( // ifPresent指定了list非空时候的处理
				apples -> {
					apples.stream().forEach( // 对苹果清单进行流式处理
							item -> {
								if (Optional.ofNullable(item) // 构造一个可空对象
										.map(apple -> apple.isRedApple()) // map指定了item非空时候的取值
										.orElse(false)) { // orElse指定了item为空时的取值
									redAppleList.add(item);
								}
							});
				});
		System.out.println("Optional2判断 红苹果清单=" + redAppleList.toString());
	}

	// 联合运用Optional校验和流式处理
	private static void getRedAppleWithOptionalThree(List<Apple> list) {
		List<Apple> redAppleList = new ArrayList<Apple>();
		Optional.ofNullable(list) // 构造一个可空对象
		.ifPresent(apples -> { // ifPresent指定了list非空时候的处理
					// 从原始清单中筛选出红苹果清单
					redAppleList.addAll(apples.stream()
								.filter(a -> a != null) // 只挑选非空元素
								.filter(Apple::isRedApple) // 只挑选红苹果
								.collect(Collectors.toList())); // 返回结果清单
					});
		System.out.println("Optional3判断 红苹果清单=" + redAppleList.toString());
	}

}
