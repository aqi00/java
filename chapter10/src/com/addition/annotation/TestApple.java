package com.addition.annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//演示如何把注解技术应用到空指针校验当中
public class TestApple {
	
	public static void main(String[] args) {
		testSingle(); // 通过注解检查某个对象内部字段的空指针
		testListByFor(); // 通过简单的for循环来检查清单中的空指针
		testListByForWithNullCheck(); // 通过改进的for循环来检查清单中的空指针
		testListByStreamWithNullCheck(); // 联合运用多项高端技术的流式处理来检查清单中的空指针
	}
	
	// 通过注解检查某个对象内部字段的空指针
	private static void testSingle() {
		Apple apple = new Apple("苹果", null, null, null);
		// NullCheck的isValid方法通过注解与反射技术来校验空指针
		boolean isValid = NullCheck.isValid(apple);
		System.out.println("非空检查结果为="+isValid);
	}

	// 通过简单的for循环来检查清单中的空指针，威力指数如同子弹
	private static void testListByFor() {
		ArrayList<Apple> list = getAppleList();
		// 常规的for循环校验，对每个对象及其每个属性都进行空指针判断
		getRedAppleByFor(list);
	}

	// 通过改进的for循环来检查清单中的空指针，威力指数如同炮弹
	private static void testListByForWithNullCheck() {
		ArrayList<Apple> list = getAppleList();
		// 把for循环内部的空指针校验改为通过注解校验
		getRedAppleByForWithNullCheck(list);
	}

	// 联合运用多项高端技术的流式处理来检查清单中的空指针，威力指数如同核弹
	private static void testListByStreamWithNullCheck() {
		ArrayList<Apple> list = getAppleList();
		// 联合运用Optional校验、流式处理，以及注解校验
		getRedAppleByStreamWithNullCheck(list);
	}

	// 获取默认的苹果清单
	private static ArrayList<Apple> getAppleList() {
		ArrayList<Apple> appleList = new ArrayList<Apple>();
			appleList.add(new Apple("红苹果", "RED", 150d, 10d));
			appleList.add(new Apple("大苹果", "green", 250d, 10d));
			appleList.add(new Apple(null, "red", 300d, 10d));
			appleList.add(new Apple("大苹果", "yellow", 200d, 10d));
			appleList.add(new Apple("红苹果", "green", 100d, 10d));
			appleList.add(new Apple("大苹果", "Red", null, 10d));
			appleList.add(null);
			appleList.add(new Apple("大苹果", "Red", 350d, 10d));
			appleList.add(new Apple("红苹果", "red", 300d, 10d));
			appleList.add(new Apple("红苹果", "green", 100d, 10d));
		return appleList;
	}

	// 常规的for循环校验，对每个对象及其每个属性都判断空指针
	private static void getRedAppleByFor(List<Apple> list) {
		List<Apple> redAppleList = new ArrayList<Apple>();
		if (list != null) { // 判断清单非空
			for (Apple item : list) {
				// 对每个字段依次判断空指针
				if (item!=null && item.getName()!=null && item.getColor()!=null 
						&& item.getWeight()!=null && item.getPrice()!=null) {
					if (item.isRedApple()) { // 判断是否为红苹果
						redAppleList.add(item);
					}
				}
			}
		}
		System.out.println("常规的For循环校验之后的红苹果清单=" + redAppleList.toString());
	}

	// 把for循环内部的空指针校验改为通过注解校验
	private static void getRedAppleByForWithNullCheck(List<Apple> list) {
		List<Apple> redAppleList = new ArrayList<Apple>();
		if (list != null) { // 判断清单非空
			for (Apple item : list) {
				// NullCheck的isValid方法通过注解与反射技术来校验空指针
				if (NullCheck.isValid(item)) {
					if (item.isRedApple()) { // 判断是否为红苹果
						redAppleList.add(item);
					}
				}
			}
		}
		System.out.println("For循环，非空校验之后的红苹果清单=" + redAppleList.toString());
	}

	// 联合运用Optional校验、流式处理，以及注解校验
	private static void getRedAppleByStreamWithNullCheck(List<Apple> list) {
		List<Apple> redAppleList = new ArrayList<Apple>();
		// ifPresent表示list非空时候的处理
		Optional.ofNullable(list).ifPresent(apples -> {
			// 从原始清单中筛选出红苹果清单。注意“NullCheck::isValid”为静态方法引用的写法
			redAppleList.addAll(apples.stream().filter(NullCheck::isValid).filter(Apple::isRedApple).collect(Collectors.toList()));
		});
		System.out.println("流式处理，非空校验之后的红苹果清单=" + redAppleList.toString());
	}
	
}
