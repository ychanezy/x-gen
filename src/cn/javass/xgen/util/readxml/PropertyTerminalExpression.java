package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

public class PropertyTerminalExpression extends ReadXmlExpression{
	/**
	 * 属性名字
	 */
	private String propName;
	
	public PropertyTerminalExpression(String propName){
		this.propName = propName;
	}
	
	@Override
	public String[] interpret(Context ctx) {
		String[] ss = new String[1];
		//1：获取父元素
		Element pEle = ctx.getPreEles().get(0);
		//2：直接取该元素的属性的值
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
