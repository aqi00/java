package com.network.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//演示利用第三方库dom4j来解析XML串
public class TestDom4j {
	//private static String CHARSET = "gbk"; // 中文领域的GBK编码
	private static String CHARSET = "UTF-8"; // 世界通行的UTF8编码

	public static void main(String[] arg) {
		String xml = getTestXML(); // 获得一个测试用的xml字符串
		System.out.println("xml="+xml);
		//testParserByDom4j(xml); // 通过dom4j解析xml串
		testPrintXmlAttr(xml); // 通过dom4j解析xml串中的节点属性
	}

	// 获得一个测试用的xml字符串
	private static String getTestXML() {
		String xml = "<?xml version=\"1.0\" encoding=\"" + CHARSET + "\"?>"
				+ "<order>"
				+ "  <user_info>"
				+ "	<name type=\"string\">思无邪</name>"
				+ "	<address type=\"string\">桃花岛水帘洞123号</address>"
				+ "	<phone type=\"string\">15960238696</phone>"
				+ "  </user_info>"
				+ "  <goods_list>"
				+ "	<goods_item>"
				+ "	  <goods_name type=\"string\">Mate30</goods_name>"
				+ "	  <goods_number type=\"int\">1</goods_number>"
				+ "	  <goods_price type=\"double\">8888</goods_price>"
				+ "	</goods_item>"
				+ "	<goods_item>"
				+ "	  <goods_name type=\"string\">格力中央空调</goods_name>"
				+ "	  <goods_number type=\"int\">1</goods_number>"
				+ "	  <goods_price type=\"double\">58000</goods_price>"
				+ "	</goods_item>"
				+ "	<goods_item>"
				+ "	  <goods_name type=\"string\">红蜻蜓皮鞋</goods_name>"
				+ "	  <goods_number type=\"int\">3</goods_number>"
				+ "	  <goods_price type=\"double\">999</goods_price>"
				+ "	</goods_item>"
				+ "  </goods_list>"
				+ "</order>";
		
		return xml;
	}
	
	// 通过dom4j解析xml串
	private static GoodsOrder testParserByDom4j(String xml) {
		GoodsOrder order = new GoodsOrder(); // 创建一个购物订单对象
		SAXReader reader = new SAXReader(); // 创建SAXReader阅读器对象
		// 根据字符串构建字节数组输入流
		try (InputStream is = new ByteArrayInputStream(xml.getBytes(CHARSET))) {
			Document document = reader.read(is); // 命令阅读器从输入流中读取文档对象
			Element root = document.getRootElement(); // 获得文档对象的根节点
			Element user_info = root.element("user_info"); // 获取根节点下面名叫user_info的节点
			// 获取user_info节点下面名叫name的节点值
			order.user_info.name = user_info.element("name").getText();
			// 获取user_info节点下面名叫address的节点值
			order.user_info.address = user_info.element("address").getText();
			// 获取user_info节点下面名叫phone的节点值
			order.user_info.phone = user_info.element("phone").getText();
			System.out.println(String.format("用户信息如下：姓名=%s，地址=%s，手机号=%s", 
					order.user_info.name, order.user_info.address, order.user_info.phone));
			// 获取根节点下面名叫goods_list的节点清单
			List<Element> goods_list = root.element("goods_list").elements();
			for (int i=0; i<goods_list.size(); i++) { // 遍历商品节点清单
				Element goods_item = goods_list.get(i);
				GoodsItem item = new GoodsItem(); // 创建一项商品对象
				// 获取当前商品项节点下面名叫goods_name的节点值
				item.goods_name = goods_item.element("goods_name").getText();
				// 获取当前商品项节点下面名叫goods_number的节点值
				item.goods_number = Integer.parseInt(goods_item.element("goods_number").getText());
				// 获取当前商品项节点下面名叫goods_price的节点值
				item.goods_price = Double.parseDouble(goods_item.element("goods_price").getText());
				System.out.println(String.format("第%d个商品：名称=%s，数量=%d，价格=%f", 
						i+1, item.goods_name, item.goods_number, item.goods_price));
				order.goods_list.add(item); // 往商品清单中添加指定商品对象
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order; // 返回解析后的购物订单对象
	}

	// 通过dom4j解析xml串中的节点属性
	private static void testPrintXmlAttr(String xml) {
		SAXReader reader = new SAXReader(); // 创建SAXReader阅读器对象
		// 根据字符串构建字节数组输入流
		try (InputStream is = new ByteArrayInputStream(xml.getBytes(CHARSET))) {
			Document document = reader.read(is); // 命令阅读器从输入流中读取文档对象
			Element root = document.getRootElement(); // 获得文档对象的根节点
			// 获取根节点下面名叫user_info的节点
			Element user_info = root.element("user_info");
			// 打印user_info节点的name子节点的type属性值
			printValueAndAttr(user_info, "name", "type");
			// 打印user_info节点的address子节点的type属性值
			printValueAndAttr(user_info, "address", "type");
			// 打印user_info节点的phone子节点的type属性值
			printValueAndAttr(user_info, "phone", "type");
			// 获取根节点下面名叫goods_list的节点清单
			List<Element> goods_list = root.element("goods_list").elements();
			if (goods_list != null) {
				for (Element goods_item : goods_list) { // 遍历商品节点清单
					GoodsItem item = new GoodsItem();
					// 打印当前商品项节点的goods_name子节点的type属性值
					printValueAndAttr(goods_item, "goods_name", "type");
					// 打印当前商品项节点的goods_number子节点的type属性值
					printValueAndAttr(goods_item, "goods_number", "type");
					// 打印当前商品项节点的goods_price子节点的type属性值
					printValueAndAttr(goods_item, "goods_price", "type");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 打印指定节点名称的指定属性值
	private static void printValueAndAttr(Element parent, String node_name, String attr_name) {
		Element element = parent.element(node_name); // 获取父节点下面指定名称的子节点
		String node_value = element.getText(); // 获得子节点的节点值
		String attr_value = "";
		// 根据属性名称获取子节点的对应属性对象
		Attribute attr = element.attribute(attr_name);
		if (attr != null) {
			attr_value = attr.getText(); // 获取该属性的属性值
		}
		// 打印子节点的详细信息，包括节点名称、节点值、属性名称、属性值
		System.out.println(String.format("节点名称=%s, 节点值=%s, 属性名称=%s, 属性值=%s", 
				node_name, node_value, attr_name, attr_value));
	}
	
}
