package com.collect.handle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.collect.generic.Apple;

//演示如何使用Stream流对容器进行更高级的处理操作
public class TestStream {

	public static void main(String[] arg) {
		System.out.println("原始的苹果清单=" + getAppleList().toString());
		// stream、parallelStream这两个是开始指令，
		// collect、forEach、count这三个是结束指令，
		// 除了开始指令和结束指令以外，其它都是中间指令。
		// 挑出红苹果清单
		List<Apple> redAppleList = getAppleList().stream() // 串行处理
				//.parallelStream() // 并行处理
				.filter(Apple::isRedApple) // 过滤条件。专门挑选红苹果
				.sorted(Comparator.comparing(Apple::getWeight)) // 按苹果重量升序排列
				//.sorted(Comparator.comparing(Apple::getWeight).reversed()) // 按苹果重量降序排列
				.limit(3) // 只取前几条数据
				.distinct() // 去掉重复记录
				.collect(Collectors.toList()) // 返回一串清单
				;
		System.out.println("红苹果清单=" + redAppleList.toString());
		// 挑出去重后的苹果名称清单
		List<String> allNameList = getAppleList().stream() // 串行处理
				.map(Apple::getName) // 映射成新的数据类型
				.distinct() // 去掉重复记录
				.collect(Collectors.toList()) // 返回一串清单
				;
		System.out.println("苹果名称去重后的清单=" + allNameList.toString());
		// 按照名称统计红苹果的分组个数
		Map<String, Long> redStatisticCount = getAppleList().stream() // 串行处理
				.filter(Apple::isRedApple) // 过滤条件。专门挑选红苹果
				.collect(Collectors.groupingBy(Apple::getName, Collectors.counting())) // 返回分组计数
				;
		System.out.println("红苹果分组计数=" + redStatisticCount.toString());
		// 按照名称统计红苹果的分组总价
		Map<String, Double> redPriceSum = getAppleList().stream() // 串行处理
				.filter(Apple::isRedApple) // 过滤条件。专门挑选红苹果
				.collect(Collectors.groupingBy(Apple::getName, Collectors.summingDouble(Apple::getPrice))) // 返回分组并对某字段求和
				;
		System.out.println("红苹果分组总价=" + redPriceSum.toString());
		// 统计红苹果的总数
		long redCount = getAppleList().stream() // 串行处理
				.filter(Apple::isRedApple) // 过滤条件。专门挑选红苹果
				.count() // 统计记录个数
				;
		System.out.println("红苹果总数=" + redCount);
		// 对每个红苹果依次处理
		getAppleList().stream() // 串行处理
				.filter(Apple::isRedApple) // 过滤条件。专门挑选红苹果
				.forEach(s -> System.out.println("当前颜色为"+s.getColor())) // 逐条操作
				;
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

}
