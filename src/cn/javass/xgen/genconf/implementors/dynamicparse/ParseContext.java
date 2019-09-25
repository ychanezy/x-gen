package cn.javass.xgen.genconf.implementors.dynamicparse;

import java.util.Map;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;

public class ParseContext {
	
	public Map<String,ExtendConfModel> parse(GenConfModel gm,Map<String,ExtendConfModel> mapEcms){
		//����ֻ֧�������﷨�����ѡ������㷨�Ĺ����ǿ��Թ̶��ģ����Ǿ���������ѡ��
		//Ŀǰ��Լ����ֻ�� Module���õ�ExtendsConf�������ʹ�ñ���
		//���${}������һ�������ĵ��ʣ��Ǿ�ʹ������ֱ���滻�������ʹ��beanshell
		
		for(String key : mapEcms.keySet()){
			ExtendConfModel ecm = mapEcms.get(key);
			
			ecm.setValue(this.parseOne(gm, mapEcms, ecm.getValue()));
			
			mapEcms.put(key, ecm);
		}
		return mapEcms;
	}
	private String parseOne(GenConfModel gm,Map<String,ExtendConfModel> mapEcms,String value){
		//1:�����ж��Ƿ���${�����û�У��ǾͲ��ý���
		if(value != null && value.indexOf("${") >= 0){
			//2�������${����ô�������ֵȡ����
			int begin = value.indexOf("${");
			int end = begin + "${".length()+value.substring(begin + "${".length()).indexOf("}");
			
			String prop = value.substring(begin+"${".length(),end);
			
			
			//3:���ж�${}������һ�������ĵ��ʣ��Ǿ�ʹ������ֱ���滻�������ʹ��beanshell
			ParseStrategy ps = null;
			if(isWord(prop)){
				ps = new PropertyReplaceStrategy();
			}else{
				ps = new BeanShellStrategy();
			}
			//�õ�������Ķ�̬���ֵ�����
			String tempStr = ps.parseDynamicContent(gm, mapEcms, prop);			
			
			//Ȼ����⶯̬�Ĳ��������滻�ص� value��
			value = value.replace("${"+prop+"}", tempStr);
			
			//�����ݹ�
			value = parseOne(gm,mapEcms,value);
		}		
		return value;
	}
	
	private boolean isWord(String s){
		char cs[] = s.toCharArray();
		for(char c : cs){
			if(
					(c>='a' && c<='z')
					||
					(c>='A' && c<='Z')
			){
				//˵������ĸ
			}else{
				//ֻҪ��һ��������ĸ
				return false;
			}
		}
		return true;
	}
	
}
