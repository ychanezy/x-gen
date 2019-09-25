package cn.javass.xgen.geninvocation.decorator;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

public abstract class GenDecorator extends GenComponent{
	/**
	 * �����������
	 */
	protected GenComponent component;

	public GenDecorator(GenComponent component){
		this.component = component;
	}
	@Override
	public Object operation(ModuleConfModel moduleConf, String genTypeId,
			Object obj) {
		//ȱʡʵ�֣�ת���������һ��������������
		return this.component.operation(moduleConf, genTypeId, obj);
	}
}
