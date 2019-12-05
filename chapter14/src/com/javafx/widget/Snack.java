package com.javafx.widget;

import javafx.beans.property.SimpleStringProperty;

//定义快餐类
public class Snack {
	private SimpleStringProperty xuhao; // 序号
	private SimpleStringProperty name; // 快餐名称
	private SimpleStringProperty price; // 快餐价格

	public Snack(String xuhao, String name, String price) {
		this.xuhao = new SimpleStringProperty(xuhao);
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleStringProperty(price);
	}

	public String getXuhao() { // 获取序号
		return xuhao.get();
	}

	public void setXuhao(String xuhao) { // 设置序号
		this.xuhao.set(xuhao);
	}

	public String getName() { // 获取快餐名称
		return name.get();
	}

	public void setName(String name) { // 设置快餐名称
		this.name.set(name);
	}

	public String getPrice() { // 获取快餐价格
		return price.get();
	}

	public void setPrice(String price) { // 设置快餐价格
		this.price.set(price);
	}
}
