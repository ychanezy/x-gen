package cn.javass.xgen.geninvocation.state;

import cn.javass.xgen.geninvocation.DefaultGenInvocation;
/**
 * 公共的状态接口
 */
public interface State {
	/**
	 * 执行状态所对应的功能处理
	 * @param ctx 上下文实例对象
	 */
	public void doWork(DefaultGenInvocation ctx);
}
