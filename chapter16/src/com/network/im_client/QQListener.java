package com.network.im_client;

//定义QQ事件监听器，用于服务端的回调处理。这是一个函数式接口，调用时可写成Lambda表达式
@FunctionalInterface
public interface QQListener {
	public void doEvent(String content);
}
