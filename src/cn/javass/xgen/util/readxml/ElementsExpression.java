package cn.javass.xgen.util.readxml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;

public class ElementsExpression extends ReadXmlExpression {
	
	/**
	 * 用来记录组合的子ReadXmlExpression元素
	 */
	private List<ReadXmlExpression> eles = new ArrayList<ReadXmlExpression>();
	public void setEles(List<ReadXmlExpression> eles) {
		this.eles = eles;
	}


	public List<ReadXmlExpression> getEles() {
		return eles;
	}

	/**
	 * 元素的名称
	 */
	private String eleName = "";
	/**
	 * 判断的条件
	 */
	private String condition = "";
	
	public ElementsExpression(String eleName,String condition){
		this.eleName = eleName;
		this.condition = condition;
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
		//1：维护 父级 节点记录
		List<Element> pEles = ctx.getPreEles();
		//获取当前元素，多个
		List<Element> nowEles = new ArrayList<Element>();
		
		for(Element pEle : pEles){
			nowEles.addAll(ctx.getNowEles(pEle, eleName));
		}
		//判断条件
		Iterator<Element> it = nowEles.iterator();
		while(it.hasNext()){
			Element ele = it.next();
			if(!ctx.judgeCondition(ele, condition)){
				it.remove();
			}
		}
		//设置父节点
		ctx.setPreEles(nowEles);
		
		//2：循环解释子元素
		String ss[] = null;
		
		for(ReadXmlExpression tempEle : eles){
			ss = tempEle.interpret(ctx);
		}
		
		return ss;
	}
	@Override
	public Object clone(){
		ElementsExpression obj = null;
		try{
			obj = (ElementsExpression)super.clone();
			
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
