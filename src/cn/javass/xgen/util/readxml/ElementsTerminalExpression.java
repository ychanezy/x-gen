package cn.javass.xgen.util.readxml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;

public class ElementsTerminalExpression extends ReadXmlExpression{
	/**
	 * Ԫ�ص�����
	 */
	private String eleName = "";
	/**
	 * �жϵ�����
	 */
	private String condition = "";
	
	public ElementsTerminalExpression(String eleName,String condition){
		this.eleName = eleName;
		this.condition = condition;
	}
	
	@Override
	public String[] interpret(Context ctx) {
		//1����ȡ���Լ��� ��� Ԫ��
		List<Element> pEles = ctx.getPreEles();
		//��ȡ��ǰԪ�أ����
		List<Element> nowEles = new ArrayList<Element>();
		
		for(Element pEle : pEles){
			nowEles.addAll(ctx.getNowEles(pEle, eleName));
		}
		//2���ж���ЩԪ���Ƿ���������
		Iterator<Element> it = nowEles.iterator();
		while(it.hasNext()){
			Element ele = it.next();
			if(!ctx.judgeCondition(ele, condition)){
				it.remove();
			}
		}		
		
		//3����ȡ��ЩԪ�ص�ֵ
		String [] ss = new String[nowEles.size()];
		
		for(int i=0;i<ss.length;i++){
			Element ele = nowEles.get(i);
			if(ele.getFirstChild()!=null){
				ss[i] = ele.getFirstChild().getNodeValue();
			}else{
				ss[i] = "";
			}
		}		
		
		return ss;
	}
	@Override
	public Object clone(){
		Object obj = null;
		try{
			obj = super.clone();
		}catch(Exception err){
			err.printStackTrace();
		}
		
		return obj;
	}
}
