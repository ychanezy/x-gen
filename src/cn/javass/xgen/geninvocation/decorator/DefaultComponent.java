package cn.javass.xgen.geninvocation.decorator;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

public class DefaultComponent extends GenComponent{

	@Override
	public Object operation(ModuleConfModel moduleConf, String genTypeId,
			Object obj) {
		//Ŀǰ�ǰ����еĹ��ܲ����ɢ�������װ������������ȥʵ��
		//�������ʲô��������
		return obj;
	}
}
