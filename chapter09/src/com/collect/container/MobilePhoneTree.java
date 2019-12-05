package com.collect.container;

//定义用于二叉集合TreeSet的手机类。二叉树需要实现接口Comparable
public class MobilePhoneTree implements Comparable<MobilePhoneTree> {

	private String brand; // 手机品牌
	private Integer price; // 手机价格

	public MobilePhoneTree(String brand, int price) {
		this.brand = brand;
		this.price = price;
	}

	// 获取手机品牌
	public String getBrand() {
		return this.brand;
	}

	// 获取手机价格
	public int getPrice() {
		return this.price;
	}

	// 二叉树除了检查是否相等，还要判断先后顺序。
	// 相等和先后顺序的校验结果从compareTo方法获得。
	@Override
	public int compareTo(MobilePhoneTree other) {
		if (this.price.compareTo(other.price) > 0) { // 当前价格较高
			return 1;
		} else if (this.price.compareTo(other.price) < 0) { // 当前价格较低
			return -1;
		} else { // 二者价格相等，再比较它们的品牌
			return this.brand.compareTo(other.brand);
		}
	}

}
