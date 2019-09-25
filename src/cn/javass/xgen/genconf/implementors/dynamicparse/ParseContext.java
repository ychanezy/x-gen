package cn.javass.xgen.genconf.implementors.dynamicparse;

import java.util.Map;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;

public class ParseContext {
	
	public Map<String,ExtendConfModel> parse(GenConfModel gm,Map<String,ExtendConfModel> mapEcms){
		//现在只支持两种语法，因此选择策略算法的过程是可以固定的，我们就这这里做选择
		//目前的约定是只在 Module配置的ExtendsConf里面才能使用变量
		//如果${}里面是一个单独的单词，那就使用属性直接替换，否则就使用beanshell
		
		for(String key : mapEcms.keySet()){
			ExtendConfModel ecm = mapEcms.get(key);
			
			ecm.setValue(this.parseOne(gm, mapEcms, ecm.getValue()));
			
			mapEcms.put(key, ecm);
		}
		return mapEcms;
	}
	private String parseOne(GenConfModel gm,Map<String,ExtendConfModel> mapEcms,String value){
		//1:首先判断是否有${，如果没有，那就不用解析
		if(value != null && value.indexOf("${") >= 0){
			//2：如果有${，那么把里面的值取出来
			int begin = value.indexOf("${");
			int end = begin + "${".length()+value.substring(begin + "${".length()).indexOf("}");
			
			String prop = value.substring(begin+"${".length(),end);
			
			
			//3:再判断${}里面是一个单独的单词，那就使用属性直接替换，否则就使用beanshell
			ParseStrategy ps = null;
			if(isWord(prop)){
				ps = new PropertyReplaceStrategy();
			}else{
				ps = new BeanShellStrategy();
			}
			//得到解析后的动态部分的数据
			String tempStr = ps.parseDynamicContent(gm, mapEcms, prop);			
			
			//然后把这动态的部分数据替换回到 value上
			value = value.replace("${"+prop+"}", tempStr);
			
			//继续递归
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
				//说明是字母
			}else{
				//只要有一个不是字母
				return false;
			}
		}
		return true;
	}
	
}
