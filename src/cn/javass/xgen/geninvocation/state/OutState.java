package cn.javass.xgen.geninvocation.state;

import cn.javass.xgen.geninvocation.DefaultGenInvocation;
import cn.javass.xgen.mediator.CoreMediator;
import cn.javass.xgen.template.TemplateEbi;

public class OutState implements State{

	@Override
	public void doWork(DefaultGenInvocation ctx) {
		//�����������ȥ
		
		//1��ע��۲��ߣ���Ҫ����Ҫ��������͵��ɹ۲��ߣ���ע�ᵽobserable����
		CoreMediator.getInstance().registerObservers(ctx);
		
		//2��֪ͨ�۲���
		ctx.setContentOver(((TemplateEbi)ctx.getTempContent()).getNowContent());
		
		//���ú���state������û��
		
//		System.out.println("over Content==="+((DefaultTemplateEbo)ctx.getTempContent()).getNowContent());
	}
}
