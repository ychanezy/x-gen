package cn.javass.xgen.geninvocation.decorator;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

public class ReadTemplateContent extends GenDecorator{

	public ReadTemplateContent(GenComponent component) {
		super(component);
	}
	
	@Override
	public Object operation(ModuleConfModel moduleConf, String genTypeId,
			Object obj) {
		//注意：通常会作为装饰器组装的第一个，也就是 最内核的那个，它的里面不再有装饰器
		
		//从模板管理模块里面获取具体的模板内容
		
		//然后返回这个内容
		return CoreMediator.getInstance().getTemplateContent(moduleConf, genTypeId);
	}

}
