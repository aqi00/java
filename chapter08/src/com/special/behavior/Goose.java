package com.special.behavior;

//定义一个实现了接口Behavior的鹅类。注意鹅类需要实现Behavior接口的所有抽象方法
public class Goose extends Bird implements Behavior {

	public Goose(String name, int sexType) {
		super(name, sexType);
	}

	// 实现了接口的fly方法
	@Override
	public void fly() {
		System.out.println("鹅飞不高，也飞不远。");
	}

	// 实现了接口的swim方法
	@Override
	public void swim() {
		System.out.println("鹅，鹅，鹅，曲项向天歌。白毛浮绿水，红掌拨清波。");
	}

	// 实现了接口的run方法
	@Override
	public void run() {
		System.out.println("槛外萧声轻荡漾，沙间鹅步满蹒跚。");
	}

}
