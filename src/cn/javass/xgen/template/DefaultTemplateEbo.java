package cn.javass.xgen.template;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.template.flyweight.TemplateFlyweight;
import cn.javass.xgen.template.flyweight.TemplateFlyweightFactory;

public class DefaultTemplateEbo implements TemplateEbi{
	/**
	 * ģ�����õ�����model
	 */
	private ModuleConfModel moduleConf;
	/**
	 * ��Ҫ���ɵ����ͱ�ʶ
	 */
	private String genTypeId;
	/**
	 * ��ǰ��ģ�崦�������
	 */
	private Object nowContent;
	
	private TemplateFlyweight flyweight = null;
	
	public DefaultTemplateEbo(ModuleConfModel moduleConf,String genTypeId){
		this.moduleConf = moduleConf;
		this.genTypeId = genTypeId;
		//��ʼ��
		flyweight = TemplateFlyweightFactory.getInstance().getTemplateFlyweight(moduleConf, this.genTypeId);
		nowContent = flyweight.getRawContent();
	}
	
	
	@Override
	public Object replaceProperties() {
		this.nowContent = flyweight.replaceProperties(moduleConf, this.nowContent);
		return this;				
	}

	@Override
	public Object replaceMethods() {
		this.nowContent = flyweight.replaceMethods(moduleConf, this.nowContent);
		return this;
	}

	@Override
	public Object getNowContent() {
		return this.nowContent;
	}

}
