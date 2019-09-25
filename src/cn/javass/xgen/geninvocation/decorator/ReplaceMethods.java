package cn.javass.xgen.geninvocation.decorator;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

public class ReplaceMethods extends GenDecorator {

	public ReplaceMethods(GenComponent component) {
		super(component);
	}
	@Override
	public Object operation(ModuleConfModel moduleConf, String genTypeId,
			Object obj) {
		//��Ҫ�����һ��������ɵ�����
		obj = this.component.operation(moduleConf, genTypeId, obj);
		
		
		return CoreMediator.getInstance().templateReplaceMethods(obj);
	}
}
