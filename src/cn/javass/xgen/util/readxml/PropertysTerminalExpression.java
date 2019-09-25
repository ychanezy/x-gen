package cn.javass.xgen.util.readxml;

import java.util.List;

import org.w3c.dom.Element;

public class PropertysTerminalExpression extends ReadXmlExpression {
	/**
	 * ��������
	 */
	private String propName;
	
	public PropertysTerminalExpression(String propName){
		this.propName = propName;
	}
	@Override
	public String[] interpret(Context ctx) {
		//1����ȡ��Ԫ��
		List<Element> pEles = ctx.getPreEles();
		//2��ֱ��ȡ��Ԫ�ص����Ե�ֵ
		String[] ss = new String[pEles.size()];
		for(int i=0;i<ss.length;i++){
			ss[i] = pEles.get(i).getAttribute(propName);
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
