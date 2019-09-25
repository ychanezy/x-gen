package cn.javass.xgen.geninvocation.decorator;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

public class ReadTemplateContent extends GenDecorator{

	public ReadTemplateContent(GenComponent component) {
		super(component);
	}
	
	@Override
	public Object operation(ModuleConfModel moduleConf, String genTypeId,
			Object obj) {
		//ע�⣺ͨ������Ϊװ������װ�ĵ�һ����Ҳ���� ���ں˵��Ǹ����������治����װ����
		
		//��ģ�����ģ�������ȡ�����ģ������
		
		//Ȼ�󷵻��������
		return CoreMediator.getInstance().getTemplateContent(moduleConf, genTypeId);
	}

}
