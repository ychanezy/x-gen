package cn.javass.xgen.util.readxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Parser {
	//���峣��
	private static final String  BACKLASH = "/";
	private static final String  DOT = ".";
	private static final String  DOLLAR = "$";
	
	private static final String  OPEN_BRACKET = "[";
	private static final String  CLOSE_BRACKET = "]";
	/**
	 * ���շֽ���Ⱥ�˳���Ԫ������Ӧ��·��
	 */
	private static List<String> listElePath = null;
	
	private Parser(){
		
	}
	////////////////////////����¼����-begin//////////////////////
	private static class MementoImpl implements ParseMemento{
		private Map<String,ReadXmlExpression> mapRe = new HashMap<String,ReadXmlExpression>();
		public MementoImpl(Map<String,ReadXmlExpression> mapRe){
			this.mapRe = mapRe;
		}
		public Map<String,ReadXmlExpression> getMapRe(){
			return mapRe;
		}
	}
	
	/**
	 * ���ݴ���ı��ʽ��ͨ����������ϳ�Ϊһ�������﷨��
	 * @param expr Ҫ�����ı��ʽ
	 * @return ���ʽ��Ӧ�ĳ����﷨��
	 */
	public static ReadXmlExpression parse(String expr){
//		System.out.println("now use memento");
		ReadXmlExpression retObj = null;
		//1��Ӧ�û�ȡ����¼����
		ParseMemento memento = ParseCaretaker.getInstance().retriveMemento();
		//2���ӱ���¼��ȡ������
		Map<String,ReadXmlExpression> mapRe = null;
		if(memento == null){
			mapRe = new HashMap<String,ReadXmlExpression>();
		}else{
			mapRe = ((MementoImpl)memento).getMapRe();
		}
		
		//3���ӻ��������ҵ������ͬ�� string�����ⲿ�־Ͳ��ý�����
		String notParseExpr = searchMaxLongEquals(expr,mapRe);
		//4����ȡʣ�µ���Ҫ�����Ĳ���
		String needParseExpr = "";
		if(notParseExpr.trim().length() == 0){
			needParseExpr = expr;
		}else{
			if(notParseExpr.length() < expr.length()){
				needParseExpr = expr.substring(notParseExpr.length()+1);
			}else{
				needParseExpr = "";
			}
		}
		//5����������ʣ�µ���Ҫ������ string,���������ֵĳ����﷨���ϲ�����
		if(needParseExpr.trim().length()>0){ 
			retObj = parse2(needParseExpr,notParseExpr,mapRe);
		}else{
			retObj = mapRe.get(notParseExpr);
		}
		
	    //6���������ˣ����������� ����¼
		ParseCaretaker.getInstance().saveMemento(new MementoImpl(mapRe));
		
		return retObj;
	}
	/**
	 * ��ȡ����Ѿ����������ַ���
	 * @param expr
	 * @param mapRe
	 * @return
	 */
	private static String searchMaxLongEquals(String expr,Map<String,ReadXmlExpression> mapRe){
		boolean flag = mapRe.containsKey(expr);
		while(!flag){
			int lastIndex = expr.lastIndexOf(BACKLASH);
			if(lastIndex > 0){
				expr = expr.substring(0,lastIndex);
				
				flag = mapRe.containsKey(expr+BACKLASH);
			}else{
				expr = "";
				flag = true;
			}
		}
		
		return expr;
	}
	
	////////////////////////����¼���� -end ///////////////////////
	
	private static ReadXmlExpression parse2(String needParseExpr,String notParseExpr,Map<String,ReadXmlExpression> mapRe){
		listElePath = new ArrayList<String>();
		
		//��һ�󲽣��ֽ���ʽ���õ���Ҫ������Ԫ�����ƺ͸�Ԫ�ض�Ӧ�Ľ���ģ��
		Map<String,ParseModel> mapPath = parseMapPath(needParseExpr);
		//�ڶ��󲽣�����Ԫ�ض�Ӧ�Ľ���ģ�� --��ת������Ӧ�Ľ���������
		Map<String,ReadXmlExpression> mapPathAndRe = mapPath2Expression(mapPath);
		//�����󲽣������Ⱥ�˳����ɳ�Ϊ�����﷨��	
		ReadXmlExpression prefixRE = mapRe.get(notParseExpr+BACKLASH);
		//Ϊ��ʹ�ö�������У�����Ա���¼���������Ӱ�죬����Ӧ�� cloneһ�ݹ�ȥ��
		if(prefixRE!=null){
			prefixRE = (ReadXmlExpression)mapRe.get(notParseExpr+BACKLASH).clone();
		}
		
		ReadXmlExpression retTree = buildTree(notParseExpr,prefixRE,mapPathAndRe,mapRe);
		
		
		return retTree;
	}
///////////////////	��һ��//////////////////////////////////////
	/**
	 * ���մ����ҵ�˳�����������ʽ���õ���Ӧ��Ԫ�ص�·�� �͸�Ԫ�ض�Ӧ�Ľ���ģ��
	 * @param expr
	 * @return
	 */
	private static Map<String,ParseModel> parseMapPath(String expr){
		//root/a/b/c.name
		Map<String,ParseModel> mapPath = new HashMap<String,ParseModel>();

		//�Ӹ���ʼ��ǰ׺·��
		StringBuffer pathBuffer = new StringBuffer();
		
		StringTokenizer tokenizer = new StringTokenizer(expr,BACKLASH);
		while(tokenizer.hasMoreTokens()){
			String onePath = tokenizer.nextToken();
			if(tokenizer.hasMoreTokens()){
				//������һ����˵�����ǽ�β
				//����·��
				pathBuffer.append(onePath+BACKLASH);
				
				setParsePath(pathBuffer,onePath,false,false,mapPath);
			}else{
				//˵������β��
				int dotIndex = onePath.indexOf(DOT);
				if(dotIndex > 0 ){
					//˵�������Խ�β
					String eleName = onePath.substring(0,dotIndex);
					String propName = onePath.substring(dotIndex+1);
					
					//����·��
					pathBuffer.append(eleName+DOT);				
					//��������ǰ���Ԫ��
					setParsePath(pathBuffer,eleName,false,false,mapPath);
					
					//����·��
					pathBuffer.append(propName);	
					//��������
					setParsePath(pathBuffer,propName,true,true,mapPath);
				}else{
					//˵����Ԫ�ؽ�β
					pathBuffer.append(onePath);
					
					setParsePath(pathBuffer,onePath,true,false,mapPath);
				}
				//�Ѿ���������β�ˣ����˳���
				break;
			}
		}
		return mapPath;
	}
	
	private static void setParsePath(StringBuffer buffer,String eleName,boolean end,boolean propertyValue,Map<String,ParseModel> mapPath){
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
		
		mapPath.put(buffer.toString(), pm);
		
		listElePath.add(buffer.toString());
	}
	
///////////////////	�ڶ���//////////////////////////////////////
	/**
	 * ����Ԫ�ض�Ӧ�Ľ���ģ�� --��ת������Ӧ�Ľ���������
	 * @param mapPath
	 * @return
	 */
	private static Map<String,ReadXmlExpression> mapPath2Expression(Map<String,ParseModel> mapPath){
		//һ��Ҫ���շֽ���Ⱥ�˳����ת������Ӧ�Ľ���������
		Map<String,ReadXmlExpression> map = new HashMap<String,ReadXmlExpression>();
		for(String key : listElePath){
			ParseModel pm = mapPath.get(key);
			ReadXmlExpression obj = parseModel2ReadXmlExpression(pm);
			
			map.put(key, obj);
		}
		
		return map;
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
	private static ReadXmlExpression buildTree(String prefixStr,ReadXmlExpression prefixRe , 
			Map<String,ReadXmlExpression> mapPathAndRe,Map<String,ReadXmlExpression> mapRe){
		//��һ�����󣬸�����Ҳ���Ƿ���ȥ�Ķ���
		ReadXmlExpression retRe = prefixRe;
		//������ʱ��¼��һ��Ԫ�أ����Ǹ�Ԫ��
		ReadXmlExpression preRe = getLastRE(prefixRe);
		
		for(String path : listElePath){
			ReadXmlExpression re = mapPathAndRe.get(path);
			
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
			//ÿ������һ���µ� ���������󣬾�Ӧ����ӵ��������棬Ӧ���ǰ�retRe ��¡һ��
			if(prefixStr != null && prefixStr.trim().length()>0){
				mapRe.put(prefixStr+BACKLASH+path, (ReadXmlExpression)retRe.clone());
			}else{
				mapRe.put(path, (ReadXmlExpression)retRe.clone());
			}			
		}
		
		return retRe;
	}
	/**
	 * ��ȡ�Ѿ��������Ķ����������һ��Ԫ�ض���
	 * @param prefixRe
	 * @return
	 */
	private static ReadXmlExpression getLastRE(ReadXmlExpression prefixRe){
		ReadXmlExpression lastRe = prefixRe;
		boolean flag = true;
		//  a/b/c/d
		while(flag){
			if(lastRe instanceof ElementExpression){
				if(((ElementExpression)lastRe).getEles().size() > 0 ){
					//�滻����Ԫ�أ� Ȼ�� ������  ѭ��ʵ���� �ݹ�
					lastRe = ((ElementExpression)lastRe).getEles().get(0);
					if(lastRe instanceof ElementExpression){
						flag = ((ElementExpression)lastRe).getEles().size() > 0;
					}else if(lastRe instanceof ElementsExpression){
						flag = ((ElementsExpression)lastRe).getEles().size() > 0;
					}else{
						flag = false;
					}					
				}else{
					flag = false;
				}
			}else if(lastRe instanceof ElementsExpression){
				if(((ElementsExpression)lastRe).getEles().size() > 0 ){
					lastRe = ((ElementsExpression)lastRe).getEles().get(0);
					if(lastRe instanceof ElementExpression){
						flag = ((ElementExpression)lastRe).getEles().size() > 0;
					}else if(lastRe instanceof ElementsExpression){
						flag = ((ElementsExpression)lastRe).getEles().size() > 0;
					}else{
						flag = false;
					}		
				}else{
					flag = false;
				}
			}else{
				flag = false;
			}
		}
		return lastRe;
	}
}
