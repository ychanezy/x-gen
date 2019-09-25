package cn.javass.xgen.geninvocation;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.geninvocation.decorator.DefaultComponent;
import cn.javass.xgen.geninvocation.decorator.GenComponent;
import cn.javass.xgen.geninvocation.decorator.ReadTemplateContent;
import cn.javass.xgen.geninvocation.decorator.ReplaceMethods;
import cn.javass.xgen.geninvocation.decorator.ReplaceProperty;

public abstract class BaseGenAction {
	/**
	 * ģ�巽����ִ��ÿ�����ܵľ���generate����
	 * @param moduleConf
	 * @return
	 */
	public Object generate(ModuleConfModel moduleConf){
		//1���õ�������װgenerate���ݵĶ���
		Object obj = initObject();
		//2��ִ�о���generate֮ǰҪִ�еĹ���
		Object before = this.beforeAction(moduleConf); 
		if(before != null){
			obj = this.executeDecorators(moduleConf, obj, (GenComponent)before);
		}
		beforeAction(moduleConf);
		//3��ִ��action�Ĺ���
		obj = execute(moduleConf,obj);
		//4��ִ�о���generate֮��Ҫִ�еĹ���
		Object after = this.afterAction(moduleConf); 
		if(after != null){
			obj = this.executeDecorators(moduleConf, obj, (GenComponent)after);
		}
		
		return obj;
	}
	
	/////////////////////////////////
	/**
	 * ԭ�������һ��������������ʼ����װgenerate�������ݵĶ���
	 * @return
	 */
	public abstract Object initObject();
	/**
	 * ���Ӳ�������ִ��action֮ǰҪʵ�ֵĹ���
	 * @param moduleConf
	 */
	public GenComponent beforeAction(ModuleConfModel moduleConf){
		//		
		return null;
	}
	/**
	 * ԭ�������ִ��Action�� generate����
	 * @param moduleConf
	 * @param obj
	 * @return
	 */
	public abstract Object execute(ModuleConfModel moduleConf,Object obj);
	/**
	 * ���Ӳ�������ִ��action֮��Ҫʵ�ֵĹ���
	 * @param moduleConf
	 */
	public GenComponent afterAction(ModuleConfModel moduleConf){
		//
		return null;
	}
	
	////////////////////////////////
	/**
	 * ִ��Action��װ��������
	 * @param moduleConf
	 * @param obj
	 * @param gc
	 * @return
	 */
	public abstract Object executeDecorators(ModuleConfModel moduleConf,Object obj,GenComponent gc);
	
	/**
	 * �ṩ������Ĺ���������ȱʡִ��action����ǰ�� װ��������
	 * @param moduleConf
	 * @return
	 */
	public GenComponent defaultBeforeAction(ModuleConfModel moduleConf){
		GenComponent gc = new DefaultComponent();
		//1����ȡ��Ӧ��ģ���ļ�
		GenComponent d1 = new ReadTemplateContent(gc);
		//2���ֽ�ģ���ļ�������Ҫ�滻�����ԣ���moduleConf����ȡֵ�滻��ȥ
		GenComponent d2 = new ReplaceProperty(d1);
		
		GenComponent d3 = new ReplaceMethods(d2);
		
		return d3;
	}
}
