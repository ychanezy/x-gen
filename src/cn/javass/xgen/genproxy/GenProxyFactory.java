package cn.javass.xgen.genproxy;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.geninvocation.GenInvocation;
import cn.javass.xgen.mediator.CoreMediator;

public class GenProxyFactory {
	private GenProxyFactory(){
		
	}
	
	public static GenInvocation createGenProxy(String needGenType,ModuleConfModel moduleConf){
		//创建真正执行generate的对象，也就是被代理对象，现在是默认使用本地的
		GenInvocation invocation = CoreMediator.getInstance().getDefaultGenInvocation(needGenType, moduleConf);
		
		//创建代理对象
		return new DefaultProxy(invocation);
	}
}
