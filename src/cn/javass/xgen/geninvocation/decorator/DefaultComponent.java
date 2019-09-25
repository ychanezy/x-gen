package cn.javass.xgen.geninvocation.decorator;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

public class DefaultComponent extends GenComponent{

	@Override
	public Object operation(ModuleConfModel moduleConf, String genTypeId,
			Object obj) {
		//目前是把所有的功能步骤分散到具体的装饰器对象里面去实现
		//因此这里什么都不用做
		return obj;
	}
}
