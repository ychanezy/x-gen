package cn.javass.xgen.util.readxml;

import java.util.List;

import org.w3c.dom.Element;

public class PropertysTerminalExpression extends ReadXmlExpression {
	/**
	 * 属性名字
	 */
	private String propName;
	
	public PropertysTerminalExpression(String propName){
		this.propName = propName;
	}
	@Override
	public String[] interpret(Context ctx) {
		//1：获取父元素
		List<Element> pEles = ctx.getPreEles();
		//2：直接取该元素的属性的值
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
