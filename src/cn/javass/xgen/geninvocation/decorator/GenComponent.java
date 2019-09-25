package cn.javass.xgen.geninvocation.decorator;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

public abstract class GenComponent {
	/**
	 * 执行generate的功能
	 * @param moduleConf
	 * @param genTypeId
	 * @param obj
	 * @return
	 */
	public abstract Object operation(ModuleConfModel moduleConf,String genTypeId,Object obj);
}
