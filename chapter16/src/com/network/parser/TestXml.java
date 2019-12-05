package com.network.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//演示解析XML串的两种方式：DOM和SAX
public class TestXml {
	//private static String CHARSET = "gbk"; // 中文领域的GBK编码
	private static String CHARSET = "UTF-8"; // 世界通行的UTF8编码

	public static void main(String[] arg) {
		String xml = getTestXML(); // 获得一个测试用的xml字符串
		testParserByDOM(xml); // 以DOM方式解析XML串
		//testParserBySAX(xml); // 以SAX方式解析XML串
	}

	// 获得一个测试用的xml字符串
	private static String getTestXML() {
		String xml = "<?xml version=\"1.0\" encoding=\"" + CHARSET + "\"?>"
				+ "<order>"
				+ "  <user_info>"
				+ "    <name>思无邪</name>"
				+ "    <address>桃花岛水帘洞123号</address>"
				+ "    <phone>15960238696</phone>"
				+ "  </user_info>"
				+ "  <goods_list>"
				+ "    <goods_item>"
				+ "      <goods_name>Mate30</goods_name>"
				+ "      <goods_number>1</goods_number>"
				+ "      <goods_price>8888</goods_price>"
				+ "    </goods_item>"
				+ "    <goods_item>"
				+ "      <goods_name>格力中央空调</goods_name>"
				+ "      <goods_number>1</goods_number>"
				+ "      <goods_price>58000</goods_price>"
				+ "    </goods_item>"
				+ "    <goods_item>"
				+ "      <goods_name>红蜻蜓皮鞋</goods_name>"
				+ "      <goods_number>3</goods_number>"
				+ "      <goods_price>999</goods_price>"
				+ "    </goods_item>"
				+ "  </goods_list>"
				+ "</order>";
		
		return xml;
	}
	
	// 以DOM方式解析XML串
	private static void testParserByDOM(String xml) {
		GoodsOrder order = new GoodsOrder();
		// 根据字符串构建字节数组输入流
		try (InputStream is = new ByteArrayInputStream(xml.getBytes(CHARSET))) {
			// 创建文档建造工厂的实例
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        // 创建一个文档建造器
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 命令文档建造器解析输入流对象，解析结果放在Document文档对象中
	        Document document = builder.parse(is);
	        // 获取文档对象的根节点
	        Element root = document.getDocumentElement();
	        // 根据节点名称获取该名称的所有节点
	        NodeList name_list = root.getElementsByTagName("name");
	        NodeList address_list = root.getElementsByTagName("address");
	        NodeList phone_list = root.getElementsByTagName("phone");
	        // 获取节点清单的第一个元素值。直接调用node.getNodeValue()不会返回该节点的值
	        order.user_info.name = name_list.item(0).getFirstChild().getNodeValue();
	        order.user_info.address = address_list.item(0).getFirstChild().getNodeValue();
	        order.user_info.phone = phone_list.item(0).getFirstChild().getNodeValue();
			System.out.println("user_info.name="+order.user_info.name);
			System.out.println("user_info.address="+order.user_info.address);
			System.out.println("user_info.phone="+order.user_info.phone);
	        // 根据节点名称获取该名称的所有节点
	        NodeList goods_name_list = root.getElementsByTagName("goods_name");
	        NodeList goods_number_list = root.getElementsByTagName("goods_number");
	        NodeList goods_price_list = root.getElementsByTagName("goods_price");
//	        System.out.println("getLength="+goods_name_list.getLength());
	        for (int i=0; i<goods_name_list.getLength(); i++) {
				GoodsItem goods_item = new GoodsItem();
		        // 获取节点清单中下标为i的元素值
				goods_item.goods_name = goods_name_list.item(i).getFirstChild().getNodeValue();
				goods_item.goods_number = Integer.parseInt(goods_number_list.item(0).getFirstChild().getNodeValue());
				goods_item.goods_price = Double.parseDouble(goods_price_list.item(0).getFirstChild().getNodeValue());
	    		System.out.println("goods_item.goods_name="+goods_item.goods_name);
	    		System.out.println("goods_item.goods_number="+goods_item.goods_number);
	    		System.out.println("goods_item.goods_price="+goods_item.goods_price);
	    		order.goods_list.add(goods_item);
	        }
	        
//	        NodeList nodelist = root.getElementsByTagName("goods_list");
//			Node node = nodelist.item(0);
//			NodeList goods_list = node.getChildNodes();
//    		System.out.println("getNodeName="+node.getNodeName()+", getNodeValue="+node.getFirstChild().getNodeValue());
//			System.out.println("getLength="+goods_list.getLength());
//	        for (int i=0; i<goods_list.getLength(); i++) {
//	    		Node item = goods_list.item(i);
//	    		if (item.getNodeType() == Node.ELEMENT_NODE) {
//		    		NodeList goods_item = node.getChildNodes();
//			        for (int j=0; j<goods_item.getLength(); j++) {
//			    		Node sub_item = goods_item.item(j);
//			    		if (sub_item.getNodeType() == Node.ELEMENT_NODE) {
//				    		NodeList child_list = sub_item.getChildNodes();
//					        for (int k=0; k<child_list.getLength(); k++) {
//					    		Node child = child_list.item(k);
//					    		if (child.getNodeType() == Node.ELEMENT_NODE) {
//						    		System.out.println("i="+i+",j="+j+",k="+k);
//						    		System.out.println("child getNodeName="+child.getNodeName()+", getNodeValue="+child.getFirstChild().getNodeValue());
//					    		}
//					        }
//			    		}
//			        }
//	    		}
//	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 以SAX方式解析XML串
	private static void testParserBySAX(String xml) {
		GoodsOrder order = new GoodsOrder();
		// 根据字符串构建字节数组输入流
		try (InputStream is = new ByteArrayInputStream(xml.getBytes(CHARSET))) {
			// 创建一个SAX解析器
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			// 创建一个SAX处理器
			XmlSax saxHandler = new XmlSax();
			// 命令SAX解析器按照SAX处理器制定的规则来解析输入流对象
			parser.parse(is, saxHandler);
			// 从SAX处理器获取解析之后的节点映射对象
			Map<String,String> item_map = saxHandler.getItemMap();
	        order.user_info.name = item_map.get("name");
	        order.user_info.address = item_map.get("address");
	        order.user_info.phone = item_map.get("phone");
			System.out.println("user_info.name="+order.user_info.name);
			System.out.println("user_info.address="+order.user_info.address);
			System.out.println("user_info.phone="+order.user_info.phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
