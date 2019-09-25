package cn.javass.xgen.genproxy;

import cn.javass.xgen.geninvocation.GenInvocation;

/**
 * 生成代理，其实是一个介于分发调度和 具体生成 之间的额外的附加层。
 * 目的就是为了今后可以根据需要切换不同的 具体实现。
 */
public class DefaultProxy implements GenInvocation {
	/**
	 * 持有的真正执行generate的对象
	 */
	private GenInvocation invocation = null;
	
	public DefaultProxy(GenInvocation invocation){
		this.invocation = invocation;
	}
	
	@Override
	public void executeGen() {
		//代理访问真正的invocation
		this.invocation.executeGen();
	}
}
