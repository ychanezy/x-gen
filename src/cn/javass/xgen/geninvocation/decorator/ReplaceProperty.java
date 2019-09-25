package cn.javass.xgen.geninvocation.decorator;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

public class ReplaceProperty extends GenDecorator{

	public ReplaceProperty(GenComponent component) {
		super(component);
	}
	@Override
	public Object operation(ModuleConfModel moduleConf, String genTypeId,
			Object obj) {
		//��Ҫ�����һ��������ɵ�����
		obj = this.component.operation(moduleConf, genTypeId, obj);
		
		//��Ҫ��ģ�����ݽ��зֽ�
		//1���ҵ���Ҫ�滻�ı���
		//2���ҵ�������Ӧ����������
		//3�������滻
		
		//����Щ����ʵ�ֵ�ģ���������
		
		return CoreMediator.getInstance().templateReplaceProperties(obj);
	}

}
