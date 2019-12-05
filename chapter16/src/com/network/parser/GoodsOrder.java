package com.network.parser;

import java.util.ArrayList;
import java.util.List;

//定义一次商品订单信息
public class GoodsOrder {
	public UserInfo user_info = new UserInfo(); // 用户信息
	public List<GoodsItem> goods_list = new ArrayList<GoodsItem>(); // 购买的商品清单
}
