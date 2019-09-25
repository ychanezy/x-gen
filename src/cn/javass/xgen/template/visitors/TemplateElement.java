package cn.javass.xgen.template.visitors;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

public class TemplateElement {
	private ModuleConfModel moduleConf;

	public TemplateElement(ModuleConfModel moduleConf){
		this.moduleConf = moduleConf;
	}
	/**
	 * 接受访问的方法，也就是预留的调用通道
	 * @param v
	 * @return
	 */
	public Object accept(Visitor v){
		return v.visitTemplateElement(this); //this代表参数
	}
	
	public ModuleConfModel getModuleConf() {
		return moduleConf;
	}
	
}
