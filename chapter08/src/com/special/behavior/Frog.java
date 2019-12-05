package com.special.behavior;

//定义一个实现了接口Behavior和Behavior2的蛙类。类只能继承一个，但接口可以实现多个
public class Frog implements Behavior, Behavior2 {

	// 实现了Behavior2接口的jump方法
	@Override
	public void jump() {
		System.out.println("青蛙跳跃的技能叫做“蛙跳”");
	}

	// 实现了Behavior接口的fly方法。因为青蛙不会飞，所以fly方法留空
	@Override
	public void fly() {}

	// 实现了Behavior接口的swim方法
	@Override
	public void swim() {
		System.out.println("青蛙游泳的技能叫做“蛙泳”");
	}

	// 实现了Behavior接口的run方法。因为青蛙不会跑，所以fly方法留空
	@Override
	public void run() {}

}
