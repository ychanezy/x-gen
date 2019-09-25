package cn.javass.xgen.util.readxml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

public class ElementExpression extends ReadXmlExpression{
	/**
	 * ������¼��ϵ���ReadXmlExpressionԪ��
	 */
	private List<ReadXmlExpression> eles = new ArrayList<ReadXmlExpression>();
	

	public void setEles(List<ReadXmlExpression> eles) {
		this.eles = eles;
	}
	/**
	 * Ԫ�ص�����
	 */
	private String eleName = "";
	/**
	 * �жϵ�����
	 */
	private String condition = "";
	
	public ElementExpression(String eleName,String condition){
		this.eleName = eleName;
		this.condition = condition;
	}
	public List<ReadXmlExpression> getEles() {
		return eles;
	}
	
	public void addEle(ReadXmlExpression ele){
		this.eles.add(ele);
	}
	public boolean removeEle(ReadXmlExpression ele){
		this.eles.remove(ele);
		return true;
	}
	
	public void removeAllEles(){
		this.eles.clear();
	}
	
	@Override
	public String[] interpret(Context ctx) {
		//1��ά�� ���� �ڵ��¼
		//1.1��ȡ����Ԫ��
		List<Element> pEles = ctx.getPreEles();
		Element ele = null;
		
		List<Element> nowEles = new ArrayList<Element>(); 
		
		if(pEles.size()==0){
			//1.2�жϸ�Ԫ���Ƿ���ڣ���������ڣ���ʾ�Ǹ�Ԫ��
			ele = ctx.getDocument().getDocumentElement();
			pEles.add(ele);
			
			ctx.setPreEles(pEles);
		}else{
			//1.3��������ڣ���ô��Ӧ�� �ҵ���ǰ���ʽ����Ӧ��Ԫ�أ������Ԫ�����óɸ����ڵ�
			for(Element pEle : pEles){
				nowEles.addAll(ctx.getNowEles(pEle, eleName));
				if(nowEles.size()>0){
					//�ҵ�һ����ֹͣ
					break;
				}
			}
			
			if(nowEles.size() > 0 && ctx.judgeCondition(nowEles.get(0), condition)){
				List<Element> tempList = new ArrayList<Element>();
				tempList.add(nowEles.get(0));
				
				ctx.setPreEles(tempList);
			}
		}
		//2��ѭ��������Ԫ��		
		String ss[] = null;
		
		for(ReadXmlExpression tempEle : eles){
			ss = tempEle.interpret(ctx);
		}
		
		return ss;
	}
	@Override
	public Object clone(){
		ElementExpression obj = null;
		try{
			obj = (ElementExpression)super.clone();
			
			List<ReadXmlExpression> objEles = new ArrayList<ReadXmlExpression>();
			
			for(ReadXmlExpression re : eles){
				objEles.add((ReadXmlExpression)re.clone());
			}
			obj.setEles(objEles);
		}catch(Exception err){
			err.printStackTrace();
		}
		
		return obj;
	}
}
