package cn.javass.xgen.geninvocation.state;

import cn.javass.xgen.geninvocation.DefaultGenInvocation;
import cn.javass.xgen.mediator.CoreMediator;
import cn.javass.xgen.template.TemplateEbi;

public class OutState implements State{

	@Override
	public void doWork(DefaultGenInvocation ctx) {
		//把内容输出出去
		
		//1：注册观察者，需要把需要输出的类型当成观察者，并注册到obserable里面
		CoreMediator.getInstance().registerObservers(ctx);
		
		//2：通知观察者
		ctx.setContentOver(((TemplateEbi)ctx.getTempContent()).getNowContent());
		
		//设置后续state，现在没有
		
//		System.out.println("over Content==="+((DefaultTemplateEbo)ctx.getTempContent()).getNowContent());
	}
}
