package cn.javass.xgen.template.visitors;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

public class TemplateElement {
	private ModuleConfModel moduleConf;

	public TemplateElement(ModuleConfModel moduleConf){
		this.moduleConf = moduleConf;
	}
	/**
	 * ���ܷ��ʵķ�����Ҳ����Ԥ���ĵ���ͨ��
	 * @param v
	 * @return
	 */
	public Object accept(Visitor v){
		return v.visitTemplateElement(this); //this�������
	}
	
	public ModuleConfModel getModuleConf() {
		return moduleConf;
	}
	
}
