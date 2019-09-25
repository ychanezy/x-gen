package cn.javass.xgen.genconf;

import cn.javass.xgen.genconf.implementors.GenConfImplementor;

public class GenConfFactory {
	private GenConfFactory(){
		
	}
	
	public static GenConfEbi createGenConfEbi(GenConfImplementor provider){
		return GenConfEbo.getInstance(provider);
	}
	/**
	 * 创建访问核心配置的接口对象,前提是确保已经获取了配置数据,这个方法才能正确执行
	 * @return
	 */
	public static GenConfEbi createGenConfEbi(){
		return createGenConfEbi(null);
	}
}
