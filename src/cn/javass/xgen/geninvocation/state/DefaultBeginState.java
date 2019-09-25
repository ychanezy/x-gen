package cn.javass.xgen.geninvocation.state;

import cn.javass.xgen.geninvocation.DefaultGenInvocation;

public class DefaultBeginState implements State{

	@Override
	public void doWork(DefaultGenInvocation ctx) {
		//这里并不去实现真正的功能，只是用来设置第一个State
		ctx.setState(new GenState());
		ctx.doWork();
	}

}
