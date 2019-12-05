package com.network.parser;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//演示通过SAX方式解析XML串
public class XmlSax extends DefaultHandler {
	private String mTagName = ""; // 节点名称
	// 保存节点名称与节点值的映射对象
	private Map<String,String> item_map = new HashMap<String,String>();
	
	// 获取解析之后的节点映射对象
	public Map<String,String> getItemMap() {
		return item_map;
	}
	
	@Override
	public void startDocument() throws SAXException {}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		mTagName = qName; // 开始解析某个节点时，先保存该节点的名称
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (mTagName!=null && mTagName.length()>0) {
			String value = new String(ch, start, length);
			value = value.trim(); // 去掉字符串末尾的空格
			if (value!=null && value.length()>0) {
				// 往节点映射对象中添加新的节点信息
				item_map.put(mTagName, value);
				//System.out.println("mTagName="+mTagName+",value="+value);
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {}
	
}
