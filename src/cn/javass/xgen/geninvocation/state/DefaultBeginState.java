package cn.javass.xgen.geninvocation.state;

import cn.javass.xgen.geninvocation.DefaultGenInvocation;

public class DefaultBeginState implements State{

	@Override
	public void doWork(DefaultGenInvocation ctx) {
		//���ﲢ��ȥʵ�������Ĺ��ܣ�ֻ���������õ�һ��State
		ctx.setState(new GenState());
		ctx.doWork();
	}

}
