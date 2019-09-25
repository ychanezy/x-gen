package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

public class PropertyTerminalExpression extends ReadXmlExpression{
	/**
	 * ��������
	 */
	private String propName;
	
	public PropertyTerminalExpression(String propName){
		this.propName = propName;
	}
	
	@Override
	public String[] interpret(Context ctx) {
		String[] ss = new String[1];
		//1����ȡ��Ԫ��
		Element pEle = ctx.getPreEles().get(0);
		//2��ֱ��ȡ��Ԫ�ص����Ե�ֵ
		ss[0] = pEle.getAttribute(propName);
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
