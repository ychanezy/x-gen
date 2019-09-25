package cn.javass.xgen.util.readxml;

import java.util.List;

import org.w3c.dom.Element;

public class ElementTerminalExpression extends ReadXmlExpression{
	/**
	 * Ԫ�ص�����
	 */
	private String eleName = "";
	/**
	 * �жϵ�����
	 */
	private String condition = "";
	
	public ElementTerminalExpression(String eleName,String condition){
		this.eleName = eleName;
		this.condition = condition;
	}
	
	@Override
	public String[] interpret(Context ctx) {
		//1����ȡ���Լ����Ԫ��
		//1.1��Ҫ��ȡ����Ԫ��
		List<Element> pEles = ctx.getPreEles();
		//1.2���ݸ�Ԫ�غ��Լ�Ԫ�ص����֣����ҵ��Լ����Ԫ��
		Element ele = null;
		
		if(pEles.size() == 0){
			//˵���Ǹ�Ԫ��
			ele = ctx.getDocument().getDocumentElement();
		}else{
			ele = ctx.getNowEles(pEles.get(0), eleName).get(0);
		}
		
		//2���ж����Ԫ���Ƿ���������
		if(!ctx.judgeCondition(ele, condition)){
			return new String[0];
		}
		
		//3����ȡ���Ԫ�ص�ֵ
		String[] ss = new String[1];
		if(ele.getFirstChild()!=null){
			ss[0] = ele.getFirstChild().getNodeValue();
		}else{
			ss[0] = "";
		}
		
		return ss;
	}
	@Override
	public Object clone(){
		ElementTerminalExpression obj = null;
		try{
			obj = (ElementTerminalExpression)super.clone();
		}catch(Exception err){
			err.printStackTrace();
		}
		
		return obj;
	}

}
