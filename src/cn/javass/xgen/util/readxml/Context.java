package cn.javass.xgen.util.readxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * ������,��Ž�������Ҫ��һЩȫ����Ϣ
 */
public class Context {
	
	private Document document = null;
	/**
	 * ��һ�α�����Ķ�����ڵ��Ԫ��
	 */
	private List<Element> preEles = new ArrayList<Element>();
	/**
	 * ����������Contextʵ��
	 */
	private static Map<String,Context> mapCtx = new HashMap<String,Context>();
	
	private Context(Document document){
		this.document = document;
	}
	
	public static Context getInstance(String filePathName) throws Exception{
		Context c = mapCtx.get(filePathName);
		if(c == null){
			Document document = XmlUtil.getDocument(filePathName);
			c = new Context(document);
			
			mapCtx.put(filePathName, c);
		}
		//�ȳ�ʼ��һ��
		c.init();
		
		return c;
	}
	public void init(){
		preEles = new ArrayList<Element>();
	}
	
	public List<Element> getPreEles(){
		return this.preEles;
	}
	public void setPreEles(List<Element> preEles){
		this.preEles = preEles;
	}
	public Document getDocument(){
		return this.document;
	}
	
	public List<Element> getNowEles(Element pEle,String eleName){
		List<Element> nowEles = new ArrayList<Element>();
		NodeList tempList = pEle.getChildNodes();
		for(int i=0;i<tempList.getLength();i++){
			if(tempList.item(i) instanceof Element){
				Element ele = (Element)tempList.item(i);
				if(ele.getTagName().equals(eleName)){
					nowEles.add(ele);
				}
			}
		}		
		return nowEles;
	}
	/**
	 * Ŀǰֻ��ʵ���� �հ���Ԫ�ص����Ե���ĳ������ֵ
	 * @param ele
	 * @param condition
	 * @return
	 */
	public boolean judgeCondition(Element ele,String condition){
		if(condition==null || condition.trim().length()==0){
			return true;
		}
		
		String ss[] = condition.split("=");
		if(ss[1]!=null && ss[1].equals(ele.getAttribute(ss[0]))){
			return true;
		}
		
		return false;
	}
	
}
