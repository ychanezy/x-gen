package cn.javass.xgen.template.flyweight;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.template.visitors.TemplateElement;
import cn.javass.xgen.template.visitors.Visitor;

public class DefaultTemplate implements TemplateFlyweight{
	/**
	 * ģ���ԭʼ����
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
		//1������Ҫ��ȡ����Ӧ��ģ���ԭʼ����
		String templateContent = (String)content;
		int begin = templateContent.indexOf(ExpressionEnum.methodBeginStr.getExpr());
		if(begin >=0){
			int end = begin + ExpressionEnum.methodBeginStr.getExpr().length()
					  + templateContent.substring(begin + ExpressionEnum.methodBeginStr.getExpr().length())
					  .indexOf(ExpressionEnum.methodEndStr.getExpr());
			//2���ֽ�ģ���ԭʼ���ݣ��õ���Ҫ�滻����� method
			String className = templateContent.substring(begin + ExpressionEnum.methodBeginStr.getExpr().length(),end);
			//3������method��ȡ�����ֵ
			String methodValue = ""+callMethodVisitor(className,moduleConf);
			
			//4�������ֵ�滻��ģ�嵱�е���Ӧλ��ȥ
			templateContent = templateContent.replace(ExpressionEnum.methodBeginStr.getExpr()
					+className+ExpressionEnum.methodEndStr.getExpr()
					, methodValue);
			
			//5��һֱ�滻��ģ����������û�п��滻�������ˣ���ô�ʹ������
			//�ݹ�
			templateContent = ""+nowReplaceMethods(moduleConf,templateContent);
		}
		return templateContent;		
	}
	
	private Object callMethodVisitor(String className,ModuleConfModel moduleConf){
		Object ret = null;
		try {
			Visitor v =(Visitor)Class.forName(className).newInstance();
			
			TemplateElement element = new TemplateElement(moduleConf);
			//���ܷ����߷���
			ret = element.accept(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	
	private Object nowReplaceProperties(ModuleConfModel moduleConf, Object content){
		//1������Ҫ��ȡ����Ӧ��ģ���ԭʼ����
		String templateContent = (String)content;
		
		int begin = templateContent.indexOf(ExpressionEnum.propBeginStr.getExpr());
		if(begin >=0){
			int end = begin + ExpressionEnum.propBeginStr.getExpr().length()
					  + templateContent.substring(begin + ExpressionEnum.propBeginStr.getExpr().length())
					  .indexOf(ExpressionEnum.propEndStr.getExpr());
			//2���ֽ�ģ���ԭʼ���ݣ��õ���Ҫ�滻����� property
			String prop = templateContent.substring(begin + ExpressionEnum.propBeginStr.getExpr().length(),end);
			//3����ModuleConfModel�õ� ��Ӧ�� property��ֵ
			String propValue = moduleConf.getMapExtends().get(prop).getValue();
			
			//4�������ֵ�滻��ģ�嵱�е���Ӧλ��ȥ
			templateContent = templateContent.replace(ExpressionEnum.propBeginStr.getExpr()
					+prop+ExpressionEnum.propEndStr.getExpr()
					, propValue);
			
			//5��һֱ�滻��ģ����������û�п��滻�������ˣ���ô�ʹ������
			//�ݹ�
			templateContent = ""+nowReplaceProperties(moduleConf,templateContent);
			
		}
		
		return templateContent;
	}
}
