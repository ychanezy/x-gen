package cn.javass.themes.simple.actions;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.geninvocation.BaseGenAction;
import cn.javass.xgen.geninvocation.decorator.GenComponent;

public class GenBusinessEbiAction extends BaseGenAction {

	@Override
	public Object initObject() {
		return "";
	}

	@Override
	public Object execute(ModuleConfModel moduleConf, Object obj) {
		//��Ҫ��ɵĹ��������̶�
		//1����ȡ��Ӧ��ģ���ļ�
		//2���ֽ�ģ���ļ�������Ҫ�滻�����ԣ���moduleConf����ȡֵ�滻��ȥ
		//3���ֽ�ģ���ļ�������Ҫִ�еķ������������һ����ÿ�����ܶ���Ҫ��
		
		
		return obj;
	}

	@Override
	public Object executeDecorators(ModuleConfModel moduleConf, Object obj,
			GenComponent gc) {
		//ע�⣺���ﴫ�� genTypeId�����theme�����е�GenType��idֵһ��
		return gc.operation(moduleConf, "GenBusinessEbi", obj);
	}
	@Override
	public GenComponent beforeAction(ModuleConfModel moduleConf){
		return super.defaultBeforeAction(moduleConf);
	}
	
}