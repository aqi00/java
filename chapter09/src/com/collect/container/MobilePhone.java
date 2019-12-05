package com.collect.container;

//定义一个手机类
public class MobilePhone {

	private String brand; // 手机品牌
	private Integer price; // 手机价格

	public MobilePhone(String brand, int price) {
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

}
