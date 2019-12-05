package com.special.behavior;

//定义实现了扩展接口的鹅类
public class ExpandGoose extends Bird implements ExpandBehavior {

	public ExpandGoose(String name, int sexType) {
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

	// 根据产地和祖先拼接并打印该动物的描述文字
	public void show(String place, String ancestor) {
		// getOrigin是来自扩展接口ExpandBehavior的默认方法，可以在实现类中直接使用
		String desc = getOrigin(place, getName(), ancestor);
		System.out.println(desc);
	}

}
