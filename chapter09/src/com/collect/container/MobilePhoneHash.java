package com.collect.container;

//定义用于哈希集合HashSet的手机类
public class MobilePhoneHash {

	private String brand; // 手机品牌
	private Integer price; // 手机价格

	public MobilePhoneHash(String brand, int price) {
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

	// hashCode方法计算出来的哈希值对应于该对象的保存位置
	@Override
	public int hashCode() {
		return brand.hashCode() + price.hashCode();
	}

	// 同一个存储位置上可能有多个对象（哈希值恰好相等），
	// 此时系统自动调用equals方法判断是否存在相同的对象。
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MobilePhoneHash)) {
			return false;
		}
		MobilePhoneHash other = (MobilePhoneHash) obj;
		// 手机品牌和手机价格都相等，才算是这两个手机相等
		boolean equals = this.brand.equals(other.brand)
				&& this.price.equals(other.price);
		return equals;
	}

}
