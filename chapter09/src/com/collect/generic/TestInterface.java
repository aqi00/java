package com.collect.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

//演示如何使用系统自带的泛型接口
public class TestInterface {

	public static void main(String[] arg) {
		testPredicate(); // 测试系统自带的断言接口Predicate
		testConsumer(); // 测试系统自带的消费接口Consumer
		testPredicateAndConsumer(); // 联合测试断言接口Predicate和消费接口Consumer
		testFunction(); // 测试系统自带的函数接口Function
	}

	// 获取默认的苹果清单
	private static List<Apple> getAppleList() {
		// 数组工具Arrays的asList方法可以把一系列元素直接赋值给清单对象
		List<Apple> appleList = Arrays.asList(
						new Apple("红苹果", "RED", 150d, 10d), 
						new Apple("大苹果", "green", 250d, 10d),
						new Apple("红苹果", "red", 300d, 10d), 
						new Apple("大苹果", "yellow", 200d, 10d), 
						new Apple("红苹果", "green", 100d, 10d), 
						new Apple("大苹果", "Red", 250d, 10d));
		return appleList;
	}

	// 测试系统自带的断言接口Predicate
	private static void testPredicate() {
		List<Apple> appleList = getAppleList();
		List<Apple> redAppleList;
		// 第一种调用方式：匿名内部类实现Predicate。挑出所有的红苹果
		redAppleList = filterByPredicate(appleList, new Predicate<Apple>() {
			@Override
			public boolean test(Apple t) {
				return t.isRedApple();
			}
		});
		// 第二种调用方式：Lambda表达式实现Predicate
		redAppleList = filterByPredicate(appleList, t -> t.isRedApple());
		// 第三种调用方式：通过方法引用实现Predicate
		redAppleList = filterByPredicate(appleList, Apple::isRedApple);
		System.out.println("红苹果清单：" + redAppleList.toString());
		// Lambda表达式实现Predicate。挑出所有重量大于半斤的苹果
		List<Apple> heavyAppleList = filterByPredicate(appleList, t -> t.getWeight() >= 250);
		System.out.println("重苹果清单：" + heavyAppleList.toString());
	}

	// 测试系统自带的消费接口Consumer
	private static void testConsumer() {
		List<Apple> appleList = getAppleList();
		// 第一种调用方式：匿名内部类实现Consumer。在苹果名称后面加上“好吃”二字
		modifyByConsumer(appleList, new Consumer<Apple>() {
			@Override
			public void accept(Apple t) {
				t.setName(t.getName() + "好吃");
			}
		});
		// 第二种调用方式：Lambda表达式实现Consumer
		modifyByConsumer(appleList, t -> t.setName(t.getName() + "好吃"));
		System.out.println("好吃的苹果清单" + appleList.toString());
	}

	// 联合测试断言接口Predicate和消费接口Consumer
	private static void testPredicateAndConsumer() {
		List<Apple> appleList = getAppleList();
		// 如果是红苹果，就涨价五成
		selectAndModify(appleList, t -> t.isRedApple(), t -> t.setPrice(t.getPrice() * 1.5));
		// 如果重量大于半斤，再涨价五成
		selectAndModify(appleList, t -> t.getWeight() >= 250, t -> t.setPrice(t.getPrice() * 1.5));
		System.out.println("涨价后的苹果清单：" + appleList.toString());
	}

	// 测试系统自带的函数接口Function
	private static void testFunction() {
		List<Apple> appleList = getAppleList();
		List<Apple> appleRecentList;
		// 第一种调用方式：匿名内部类实现Function。把涨价后的苹果放到新的清单之中
		appleRecentList = recycleByFunction(appleList,
				new Function<Apple, Apple>() {
					@Override
					public Apple apply(Apple t) {
						Apple apple = new Apple(t.getName(), t.getColor(), t.getWeight(), t.getPrice());
						if (apple.isRedApple()) { // 如果是红苹果，就涨价五成
							apple.setPrice(apple.getPrice() * 1.5);
						}
						if (apple.getWeight() >= 250) { // 如果重量大于半斤，再涨价五成
							apple.setPrice(apple.getPrice() * 1.5);
						}
						return apple;
					}
				});
		// 第二种调用方式：Lambda表达式实现Function
		appleRecentList = recycleByFunction(appleList, t -> {
					Apple apple = new Apple(t.getName(), t.getColor(), t.getWeight(), t.getPrice());
					if (apple.isRedApple()) { // 如果是红苹果，就涨价五成
						apple.setPrice(apple.getPrice() * 1.5);
					}
					if (apple.getWeight() >= 250) { // 如果重量大于半斤，再涨价五成
						apple.setPrice(apple.getPrice() * 1.5);
					}
					return apple;
				});
		System.out.println("涨价后的新苹果清单：" + appleRecentList.toString());
		// 返回的清单类型可能与原清单类型不同，比如只返回苹果名称
		List<String> colorList = recycleByFunction(appleList, 
				t -> t.getName() + "(" + t.getColor() + ")");
		System.out.println("带颜色的苹果名称清单：" + colorList.toString());
	}

	// 利用系统自带的断言接口Predicate，过滤某个清单里的元素
	private static <T> List<T> filterByPredicate(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<T>();
		for (T t : list) {
			if (p.test(t)) { // 如果满足断言的测试条件，就把该元素添加到新的清单
				result.add(t);
			}
		}
		return result;
	}

	// 利用系统自带的消费接口Consumer，修改某个清单里的元素
	private static <T> void modifyByConsumer(List<T> list, Consumer<T> c) {
		for (T t : list) {
			// 根据输入的消费指令接受变更，下面的t既是输入参数，又允许修改。
			c.accept(t); // 如果t是String类型，那么accept方法不能真正修改字符串
		}
	}

	// 联合运用Predicate和Consumer，可筛选出某些元素并给它们整容
	private static <T> void selectAndModify(List<T> list, Predicate<T> p, Consumer<T> c) {
		for (T t : list) {
			if (p.test(t)) { // 如果满足断言的条件要求，
				c.accept(t); // 就把该元素送去美容院整容。
			}
		}
	}

	// 利用系统自带的函数接口Function，把所有元素处理后加到新的清单里面
	private static <T, R> List<R> recycleByFunction(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<R>();
		for (T t : list) {
			R r = f.apply(t); // 把原始材料t加工一番后输出成品r
			result.add(r); // 把成品r添加到新的清单
		}
		return result;
	}

}
