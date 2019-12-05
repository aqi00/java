package com.network.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//演示通过DOM方式解析XML串
public class XmlDom {

	public static String parser(String src) {
		String desc = "";
		InputStream inStream = new ByteArrayInputStream(src.getBytes());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
	        Document document = builder.parse(inStream);
	        Element root = document.getDocumentElement();
			desc = String.format("%sDom解析的根节点为：%s。其中\n", desc, root.getTagName());
	        Node node = root.getFirstChild();
	        do {
	        	String node_name = node.getNodeName();
		        if (node_name.equals("list") != true) {
					desc = String.format("%s\t节点%s的值为：%s\n", desc, node_name, node.getFirstChild().getNodeValue());
		        } else {
					desc = String.format("%s\t数组%s的元素列表如下\n", desc, node_name);
			        NodeList itemList = root.getElementsByTagName("item");
			        for (int i=0; i<itemList.getLength(); i++) {
			        	Node item = itemList.item(i);
			        	if (item.getNodeType() == Node.ELEMENT_NODE) {
				        	NamedNodeMap attr_map = item.getAttributes();
				        	Node attr = attr_map.item(0);
							desc = String.format("%s\t\t元素%s的属性名称为：%s，属性值为：%s\n", 
									desc, item.getNodeName(), attr.getNodeName(), attr.getNodeValue());
					        Node item_node = item.getFirstChild();
					        do {
								desc = String.format("%s\t\t\t节点%s的值为：%s\n", 
										desc, item_node.getNodeName(), item_node.getFirstChild().getNodeValue());
					        	item_node = item_node.getNextSibling();
					        } while (item_node != null);
			        	}
			        }
		        }
	        	node = node.getNextSibling();
	        } while (node != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return desc;
	}
	
}
