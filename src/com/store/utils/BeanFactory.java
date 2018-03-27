package com.store.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class BeanFactory {
	/*
	 * 通过id获取一个实现类
	 */
	public static Object getBean(String id) {
		
		
		try {
			//1.获取配置文件
			//1.1获取document对象
			Document doc = new SAXReader().read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));
			//1.2获取指定的bean对象   xpath
			Element ele = (Element) doc.selectSingleNode("//bean[@id='"+id+"']");
			//1.3获取bean对象的class属性
			String value = ele.attributeValue("class");
			//2.反射，获取实例
			return Class.forName(value).newInstance();
		} catch (Exception e) {
			e.printStackTrace();	
		}	
		return null;
	}
}
