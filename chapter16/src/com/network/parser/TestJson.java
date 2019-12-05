package com.network.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

//演示json串的解析与生成
public class TestJson {

	public static void main(String[] arg) {
		String json = getTestJSON(); // 获得一个测试用的json字符串
		//System.out.println("json="+json);
		testParserJson(json); // 把json字符串解析到对应的实体对象
		GoodsOrder order = jsonToObject(json); // 把json串直接映射到某个类，也就是自动解析成该类的对象
//		objectToJson(order); // 把某个对象直接转换为对应的json串
//		testGenerateJson(order); // 根据购物订单对象逐步拼接生成json字符串
	}

	// 获得一个测试用的json字符串
	private static String getTestJSON() {
		String json = "{"
				+ "	\"user_info\":{"
				+ "		\"name\":\"思无邪\","
				+ "		\"address\":\"桃花岛水帘洞123号\","
				+ "		\"phone\":\"15960238696\""
				+ "	},"
				+ "	\"goods_list\":["
				+ "		{"
				+ "			\"goods_name\":\"Mate30\","
				+ "			\"goods_number\":1,"
				+ "			\"goods_price\":8888"
				+ "		},"
				+ "		{"
				+ "			\"goods_name\":\"格力中央空调\","
				+ "			\"goods_number\":1,"
				+ "			\"goods_price\":58000"
				+ "		},"
				+ "		{"
				+ "			\"goods_name\":\"红蜻蜓皮鞋\","
				+ "			\"goods_number\":3,"
				+ "			\"goods_price\":999"
				+ "		}"
				+ "	]}";
		return json;
	}

	// 把json字符串解析到对应的实体对象
	private static GoodsOrder testParserJson(String json) {
		GoodsOrder order = new GoodsOrder(); // 创建一个购物订单对象
		JSONObject object = JSONObject.parseObject(json); // 根据json串获得JSONObject对象
		// 从JSONObject对象中获取键名为user_info的用户信息json对象
		JSONObject user_info = object.getJSONObject("user_info");
		// 从用户信息json对象中获取键名为name的字符串
		order.user_info.name = user_info.getString("name");
		// 从用户信息json对象中获取键名为address的字符串
		order.user_info.address = user_info.getString("address");
		// 从用户信息json对象中获取键名为phone的字符串
		order.user_info.phone = user_info.getString("phone");
		System.out.println(String.format("用户信息如下：姓名=%s，地址=%s，手机号=%s", 
				order.user_info.name, order.user_info.address, order.user_info.phone));
		// 从JSONObject对象中获取键名为goods_list的商品信息json数组
		JSONArray goods_list = object.getJSONArray("goods_list");
		for (int i=0; i<goods_list.size(); i++) { // 遍历商品信息数组
			GoodsItem item = new GoodsItem(); // 创建一项商品对象
			// 从json数组获取下标为i的商品json对象
			JSONObject goods_item = (JSONObject) goods_list.get(i);
			// 从商品json对象中获取键名为goods_name的字符串
			item.goods_name = goods_item.getString("goods_name");
			// 从商品json对象中获取键名为goods_number的整型数
			item.goods_number = goods_item.getIntValue("goods_number");
			// 从商品json对象中获取键名为goods_price的双精度数
			item.goods_price = goods_item.getDoubleValue("goods_price");
			System.out.println(String.format("第%d个商品：名称=%s，数量=%d，价格=%f", 
					i+1, item.goods_name, item.goods_number, item.goods_price));
			order.goods_list.add(item); // 往商品清单中添加指定商品对象
		}
		return order; // 返回解析后的购物订单对象
	}
	
	// 把json串直接映射到某个类，也就是自动解析成该类的对象
	private static GoodsOrder jsonToObject(String json) {
		JSONObject object = JSONObject.parseObject(json); // 根据json串获得JSONObject对象
		// 把JSONObject对象中的信息一一转成购物订单信息
		GoodsOrder order = (GoodsOrder) JSONObject.toJavaObject(object, GoodsOrder.class);
		System.out.println(String.format("用户信息如下：姓名=%s，地址=%s，手机号=%s", 
				order.user_info.name, order.user_info.address, order.user_info.phone));
		for (int i=0; i<order.goods_list.size(); i++) { // 遍历解析后的各项商品信息
			GoodsItem item = order.goods_list.get(0);
			System.out.println(String.format("第%d个商品：名称=%s，数量=%d，价格=%f", 
					i+1, item.goods_name, item.goods_number, item.goods_price));
		}
		return order; // 返回解析后的购物订单对象
	}
	
	// 把某个对象直接转换为对应的json串
	private static String objectToJson(GoodsOrder order) {
		String json = JSONObject.toJSONString(order); // 把购物订单对象转换成json字符串
		System.out.println("json="+json);
		return json; // 返回转换后的json字符串
	}

	// 根据购物订单对象逐步拼接生成json字符串
	private static String testGenerateJson(GoodsOrder order) {
		JSONObject object = new JSONObject(); // 创建一个准备保存购物订单的JSONObject对象
		JSONObject user_info = new JSONObject(); // 创建一个准备保存用户信息的JSONObject对象
		// 往用户信息json对象中添加键名为name的姓名信息
		user_info.put("name", order.user_info.name);
		// 往用户信息json对象中添加键名为address的地址信息
		user_info.put("address", order.user_info.address);
		// 往用户信息json对象中添加键名为phone的号码信息
		user_info.put("phone", order.user_info.phone);
		object.put("user_info", user_info); // 往购物订单json对象中添加键名为user_info的用户信息
		JSONArray goods_list = new JSONArray(); // 创建一个准备保存商品项的JSONArray数组
		for (GoodsItem item : order.goods_list) { // 遍历购物订单里的各项商品
			// 创建一个准备保存商品信息的JSONObject对象
			JSONObject goods_item = new JSONObject();
			// 往商品信息json对象中添加键名为goods_name的名称信息
			goods_item.put("goods_name", item.goods_name);
			// 往商品信息json对象中添加键名为goods_number的数量信息
			goods_item.put("goods_number", item.goods_number);
			// 往商品信息json对象中添加键名为goods_price的价格信息
			goods_item.put("goods_price", item.goods_price);
			goods_list.add(item); // 往json数组中添加JSONObject对象
		}
		object.put("goods_list", goods_list); // 往购物订单json对象添加键名为goods_list的商品项信息
		String json = object.toJSONString(); // 把JSONObject对象转换为json字符串
		System.out.println("generate json="+json);
		return json; // 返回转换后的json字符串
	}
	
}
