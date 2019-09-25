package cn.javass.xgen.util.readxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Parser2 {
	//���峣��
	private static final String  BACKLASH = "/";
	private static final String  DOT = ".";
	private static final String  DOLLAR = "$";
	
	private static final String  OPEN_BRACKET = "[";
	private static final String  CLOSE_BRACKET = "]";
	/**
	 * ���շֽ���Ⱥ�˳���Ԫ������
	 */
	private static List<String> listEle = null;
	
	private Parser2(){
		
	}
	
	public static ReadXmlExpression parse(String expr){
		listEle = new ArrayList<String>();
		
		//��һ�󲽣��ֽ���ʽ���õ���Ҫ������Ԫ�����ƺ͸�Ԫ�ض�Ӧ�Ľ���ģ��
		Map<String,ParseModel> mapPath = parseMapPath(expr);
		//�ڶ��󲽣�����Ԫ�ض�Ӧ�Ľ���ģ�� --��ת������Ӧ�Ľ���������
		List<ReadXmlExpression> list = mapPath2Expression(mapPath);
		//�����󲽣������Ⱥ�˳����ɳ�Ϊ�����﷨��	
		ReadXmlExpression retTree = buildTree(list);
		
		
		return retTree;
	}
///////////////////	��һ��//////////////////////////////////////
	/**
	 * ���մ����ҵ�˳�����������ʽ���õ���Ӧ��Ԫ�����ƺ͸�Ԫ�ض�Ӧ�Ľ���ģ��
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
				//������һ����˵�����ǽ�β
				setParsePath(onePath,false,false,mapPath);
			}else{
				//˵������β��
				int dotIndex = onePath.indexOf(DOT);
				if(dotIndex > 0 ){
					//˵�������Խ�β
					String eleName = onePath.substring(0,dotIndex);
					String propName = onePath.substring(dotIndex+1);
					
					//��������ǰ���Ԫ��
					setParsePath(eleName,false,false,mapPath);
					//��������
					setParsePath(propName,true,true,mapPath);
				}else{
					//˵����Ԫ�ؽ�β
					setParsePath(onePath,true,false,mapPath);
				}
				//�Ѿ���������β�ˣ����˳���
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
		
		//ȥ��$
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
	
///////////////////	�ڶ���//////////////////////////////////////
	/**
	 * ����Ԫ�ض�Ӧ�Ľ���ģ�� --��ת������Ӧ�Ľ���������
	 * @param mapPath
	 * @return
	 */
	private static List<ReadXmlExpression> mapPath2Expression(Map<String,ParseModel> mapPath){
		//һ��Ҫ���շֽ���Ⱥ�˳����ת������Ӧ�Ľ���������
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
///////////////////	������	//////////////////////////////////////
	/**
	 * �����Ⱥ�˳����ɳ�Ϊ�����﷨��	
	 * @param listExpression
	 * @return
	 */
	private static ReadXmlExpression buildTree(List<ReadXmlExpression> listExpression){
		//��һ�����󣬸�����Ҳ���Ƿ���ȥ�Ķ���
		ReadXmlExpression retRe = null;
		//������ʱ��¼��һ��Ԫ�أ����Ǹ�Ԫ��
		ReadXmlExpression preRe = null;
		
		for(ReadXmlExpression re : listExpression){
			if(preRe == null){
				preRe = re;
				retRe = re;
			}else{
				//�ѵ�ǰԪ����ӵ���Ԫ�ص����棬ͬʱ���Լ����óɸ�Ԫ��
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
