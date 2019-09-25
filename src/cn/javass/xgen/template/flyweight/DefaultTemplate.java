package cn.javass.xgen.template.flyweight;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.template.visitors.TemplateElement;
import cn.javass.xgen.template.visitors.Visitor;

public class DefaultTemplate implements TemplateFlyweight{
	/**
	 * 模板的原始内容
	 */
	private String rawContent = "";
	
	DefaultTemplate(String rawContent){
		this.rawContent = rawContent;
	}
	
	@Override
	public Object replaceProperties(ModuleConfModel moduleConf, Object content) {
		return this.nowReplaceProperties(moduleConf, content);
	}

	@Override
	public Object replaceMethods(ModuleConfModel moduleConf, Object content) {
		return this.nowReplaceMethods(moduleConf, content);
	}

	@Override
	public Object getRawContent() {
		return this.rawContent;
	}

	//////////////////////////////////////
	private Object nowReplaceMethods(ModuleConfModel moduleConf, Object content){
		//1：首先要读取到相应的模板的原始内容
		String templateContent = (String)content;
		int begin = templateContent.indexOf(ExpressionEnum.methodBeginStr.getExpr());
		if(begin >=0){
			int end = begin + ExpressionEnum.methodBeginStr.getExpr().length()
					  + templateContent.substring(begin + ExpressionEnum.methodBeginStr.getExpr().length())
					  .indexOf(ExpressionEnum.methodEndStr.getExpr());
			//2：分解模板的原始内容，得到需要替换处理的 method
			String className = templateContent.substring(begin + ExpressionEnum.methodBeginStr.getExpr().length(),end);
			//3：运行method获取到结果值
			String methodValue = ""+callMethodVisitor(className,moduleConf);
			
			//4：把这个值替换到模板当中的相应位置去
			templateContent = templateContent.replace(ExpressionEnum.methodBeginStr.getExpr()
					+className+ExpressionEnum.methodEndStr.getExpr()
					, methodValue);
			
			//5：一直替换到模板内容里面没有可替换的内容了，那么就处理好了
			//递归
			templateContent = ""+nowReplaceMethods(moduleConf,templateContent);
		}
		return templateContent;		
	}
	
	private Object callMethodVisitor(String className,ModuleConfModel moduleConf){
		Object ret = null;
		try {
			Visitor v =(Visitor)Class.forName(className).newInstance();
			
			TemplateElement element = new TemplateElement(moduleConf);
			//接受访问者访问
			ret = element.accept(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	
	private Object nowReplaceProperties(ModuleConfModel moduleConf, Object content){
		//1：首先要读取到相应的模板的原始内容
		String templateContent = (String)content;
		
		int begin = templateContent.indexOf(ExpressionEnum.propBeginStr.getExpr());
		if(begin >=0){
			int end = begin + ExpressionEnum.propBeginStr.getExpr().length()
					  + templateContent.substring(begin + ExpressionEnum.propBeginStr.getExpr().length())
					  .indexOf(ExpressionEnum.propEndStr.getExpr());
			//2：分解模板的原始内容，得到需要替换处理的 property
			String prop = templateContent.substring(begin + ExpressionEnum.propBeginStr.getExpr().length(),end);
			//3：从ModuleConfModel得到 相应的 property的值
			String propValue = moduleConf.getMapExtends().get(prop).getValue();
			
			//4：把这个值替换到模板当中的相应位置去
			templateContent = templateContent.replace(ExpressionEnum.propBeginStr.getExpr()
					+prop+ExpressionEnum.propEndStr.getExpr()
					, propValue);
			
			//5：一直替换到模板内容里面没有可替换的内容了，那么就处理好了
			//递归
			templateContent = ""+nowReplaceProperties(moduleConf,templateContent);
			
		}
		
		return templateContent;
	}
}
