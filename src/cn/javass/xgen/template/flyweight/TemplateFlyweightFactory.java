package cn.javass.xgen.template.flyweight;

import java.util.HashMap;
import java.util.Map;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;
import cn.javass.xgen.util.file.FileHelper;

public class TemplateFlyweightFactory {
	//ͨ��ʵ�ֳ�Ϊ����
	private static TemplateFlyweightFactory factory = new TemplateFlyweightFactory();
	private TemplateFlyweightFactory(){
		
	}	
	public static TemplateFlyweightFactory getInstance(){
		return factory;
	}
	//������Ԫ����
	Map<String,DefaultTemplate> mapTemplate = new HashMap<String,DefaultTemplate>();
	
	public DefaultTemplate getTemplateFlyweight(String templatePath){
		//���ջ����д��
		Object obj = mapTemplate.get(templatePath);
		if(obj==null){
			DefaultTemplate dt = new DefaultTemplate(FileHelper.readFile(templatePath));
			mapTemplate.put(templatePath, dt);
			return dt;
		}else{
			return (DefaultTemplate)obj;
		}
	}
	
	public DefaultTemplate getTemplateFlyweight(ModuleConfModel moduleConf,String genTypeId){
		//����ƴ��ģ���ļ���λ��
		String mbPathFile = CoreMediator.getInstance().getThemeMbPathFile(moduleConf, genTypeId);
		String themePath = CoreMediator.getInstance().getThemePath(moduleConf);
		
		String templatePath = themePath+ExpressionEnum.separator.getExpr()
		+ExpressionEnum.template.getExpr()
		+ExpressionEnum.separator.getExpr()
		+mbPathFile	;
		return getTemplateFlyweight(templatePath);
	}
}
