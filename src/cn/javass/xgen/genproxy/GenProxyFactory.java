package cn.javass.xgen.genproxy;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.geninvocation.GenInvocation;
import cn.javass.xgen.mediator.CoreMediator;

public class GenProxyFactory {
	private GenProxyFactory(){
		
	}
	
	public static GenInvocation createGenProxy(String needGenType,ModuleConfModel moduleConf){
		//��������ִ��generate�Ķ���Ҳ���Ǳ��������������Ĭ��ʹ�ñ��ص�
		GenInvocation invocation = CoreMediator.getInstance().getDefaultGenInvocation(needGenType, moduleConf);
		
		//�����������
		return new DefaultProxy(invocation);
	}
}
