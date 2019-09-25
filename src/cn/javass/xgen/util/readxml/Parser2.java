package cn.javass.xgen.util.readxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Parser2 {
	//定义常量
	private static final String  BACKLASH = "/";
	private static final String  DOT = ".";
	private static final String  DOLLAR = "$";
	
	private static final String  OPEN_BRACKET = "[";
	private static final String  CLOSE_BRACKET = "]";
	/**
	 * 按照分解的先后顺序的元素名称
	 */
	private static List<String> listEle = null;
	
	private Parser2(){
		
	}
	
	public static ReadXmlExpression parse(String expr){
		listEle = new ArrayList<String>();
		
		//第一大步：分解表达式，得到需要解析的元素名称和该元素对应的解析模型
		Map<String,ParseModel> mapPath = parseMapPath(expr);
		//第二大步：根据元素对应的解析模型 --〉转换成相应的解释器对象
		List<ReadXmlExpression> list = mapPath2Expression(mapPath);
		//第三大步：按照先后顺序组成成为抽象语法树	
		ReadXmlExpression retTree = buildTree(list);
		
		
		return retTree;
	}
///////////////////	第一大步//////////////////////////////////////
	/**
	 * 按照从左到右的顺序来解析表达式，得到相应的元素名称和该元素对应的解析模型
	 * @param expr
	 * @return
	 */
	private static Map<String,ParseModel> parseMapPath(String expr){
		//root/a/b/c.name
		Map<String,ParseModel> mapPath = new HashMap<String,ParseModel>();
		
		StringTokenizer tokenizer = new StringTokenizer(expr,BACKLASH);
		while(tokenizer.hasMoreTokens()){
			String onePath = tokenizer.nextToken();
			if(tokenizer.hasMoreTokens()){
				//还有下一个，说明不是结尾
				setParsePath(onePath,false,false,mapPath);
			}else{
				//说明到结尾了
				int dotIndex = onePath.indexOf(DOT);
				if(dotIndex > 0 ){
					//说明是属性结尾
					String eleName = onePath.substring(0,dotIndex);
					String propName = onePath.substring(dotIndex+1);
					
					//设置属性前面的元素
					setParsePath(eleName,false,false,mapPath);
					//设置属性
					setParsePath(propName,true,true,mapPath);
				}else{
					//说明是元素结尾
					setParsePath(onePath,true,false,mapPath);
				}
				//已经解析到结尾了，就退出了
				break;
			}
		}
		return mapPath;
	}
	
	private static void setParsePath(String eleName,boolean end,boolean propertyValue,Map<String,ParseModel> mapPath){
		ParseModel pm = new ParseModel();
		pm.setEnd(end);
		pm.setPropertyValue(propertyValue);
		
		pm.setSingleValue( !(eleName.indexOf(DOLLAR) > 0) );
		
		//去掉$
		eleName = eleName.replace(DOLLAR,"");
		
		int tempBegin = 0;
		int tempEnd = 0;		
		if( (tempBegin=eleName.indexOf(OPEN_BRACKET)) > 0){
			tempEnd = eleName.indexOf(CLOSE_BRACKET);
			
			pm.setCondition(eleName.substring(tempBegin+1,tempEnd));
			
			eleName = eleName.substring(0,tempBegin);
		}
		
		pm.setEleName(eleName);
		
		mapPath.put(eleName, pm);
		
		listEle.add(eleName);
	}
	
///////////////////	第二大步//////////////////////////////////////
	/**
	 * 根据元素对应的解析模型 --〉转换成相应的解释器对象
	 * @param mapPath
	 * @return
	 */
	private static List<ReadXmlExpression> mapPath2Expression(Map<String,ParseModel> mapPath){
		//一定要按照分解的先后顺序来转换成相应的解释器对象
		List<ReadXmlExpression> list = new ArrayList<ReadXmlExpression>();
		for(String key : listEle){
			ParseModel pm = mapPath.get(key);
			ReadXmlExpression obj = parseModel2ReadXmlExpression(pm);
			
			list.add(obj);
		}
		
		return list;
	}
	
	private static ReadXmlExpression parseModel2ReadXmlExpression(ParseModel pm ){
		ReadXmlExpression obj = null;
		if(!pm.isEnd()){
			if(pm.isSingleValue()){
				obj = new ElementExpression(pm.getEleName(), pm.getCondition());
			}else{
				obj = new ElementsExpression(pm.getEleName(), pm.getCondition());
			}
		}else{
			if(pm.isPropertyValue()){
				if(pm.isSingleValue()){
					obj = new PropertyTerminalExpression(pm.getEleName());
				}else{
					obj = new PropertysTerminalExpression(pm.getEleName());
				}
			}else{
				if(pm.isSingleValue()){
					obj = new ElementTerminalExpression(pm.getEleName(), pm.getCondition());
				}else{
					obj = new ElementsTerminalExpression(pm.getEleName(), pm.getCondition());
				}
			}
		}		
		return obj;
	}
///////////////////	第三大步	//////////////////////////////////////
	/**
	 * 按照先后顺序组成成为抽象语法树	
	 * @param listExpression
	 * @return
	 */
	private static ReadXmlExpression buildTree(List<ReadXmlExpression> listExpression){
		//第一个对象，根对象，也就是返回去的对象
		ReadXmlExpression retRe = null;
		//用来临时记录上一个元素，就是父元素
		ReadXmlExpression preRe = null;
		
		for(ReadXmlExpression re : listExpression){
			if(preRe == null){
				preRe = re;
				retRe = re;
			}else{
				//把当前元素添加到父元素的下面，同时把自己设置成父元素
				if(preRe instanceof ElementExpression){
					ElementExpression ele = (ElementExpression)preRe;
					ele.addEle(re);
					
					preRe = re;
				}else if(preRe instanceof ElementsExpression){
					ElementsExpression eles = (ElementsExpression)preRe;
					eles.addEle(re);
					
					preRe = re;
				}
			}
		}
		
		return retRe;
	}
}
