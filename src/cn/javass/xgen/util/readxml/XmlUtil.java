package cn.javass.xgen.util.readxml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XmlUtil {
	private XmlUtil(){
		
	}
	
	public static Document getDocument(String filePathName)throws Exception{
//		System.out.println("filePathName=="+filePathName);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.parse(XmlUtil.class.getClassLoader().getResourceAsStream(filePathName));
		
		doc.normalize();
		
		return doc;
	}
}
