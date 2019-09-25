package cn.javass.xgen.geninvocation;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.geninvocation.decorator.DefaultComponent;
import cn.javass.xgen.geninvocation.decorator.GenComponent;
import cn.javass.xgen.geninvocation.decorator.ReadTemplateContent;
import cn.javass.xgen.geninvocation.decorator.ReplaceMethods;
import cn.javass.xgen.geninvocation.decorator.ReplaceProperty;

public abstract class BaseGenAction {
	/**
	 * 模板方法，执行每个功能的具体generate过程
	 * @param moduleConf
	 * @return
	 */
	public Object generate(ModuleConfModel moduleConf){
		//1：得到用来封装generate内容的对象
		Object obj = initObject();
		//2：执行具体generate之前要执行的功能
		Object before = this.beforeAction(moduleConf); 
		if(before != null){
			obj = this.executeDecorators(moduleConf, obj, (GenComponent)before);
		}
		beforeAction(moduleConf);
		//3：执行action的功能
		obj = execute(moduleConf,obj);
		//4：执行具体generate之后要执行的功能
		Object after = this.afterAction(moduleConf); 
		if(after != null){
			obj = this.executeDecorators(moduleConf, obj, (GenComponent)after);
		}
		
		return obj;
	}
	
	/////////////////////////////////
	/**
	 * 原语操作，一个工厂方法，初始化封装generate生成内容的对象
	 * @return
	 */
	public abstract Object initObject();
	/**
	 * 钩子操作，在执行action之前要实现的功能
	 * @param moduleConf
	 */
	public GenComponent beforeAction(ModuleConfModel moduleConf){
		//		
		return null;
	}
	/**
	 * 原语操作，执行Action的 generate方法
	 * @param moduleConf
	 * @param obj
	 * @return
	 */
	public abstract Object execute(ModuleConfModel moduleConf,Object obj);
	/**
	 * 钩子操作，在执行action之后要实现的功能
	 * @param moduleConf
	 */
	public GenComponent afterAction(ModuleConfModel moduleConf){
		//
		return null;
	}
	
	////////////////////////////////
	/**
	 * 执行Action的装饰器对象
	 * @param moduleConf
	 * @param obj
	 * @param gc
	 * @return
	 */
	public abstract Object executeDecorators(ModuleConfModel moduleConf,Object obj,GenComponent gc);
	
	/**
	 * 提供给子类的公共方法，缺省执行action操作前的 装饰器对象
	 * @param moduleConf
	 * @return
	 */
	public GenComponent defaultBeforeAction(ModuleConfModel moduleConf){
		GenComponent gc = new DefaultComponent();
		//1：读取相应的模板文件
		GenComponent d1 = new ReadTemplateContent(gc);
		//2：分解模板文件里面需要替换的属性，从moduleConf里面取值替换过去
		GenComponent d2 = new ReplaceProperty(d1);
		
		GenComponent d3 = new ReplaceMethods(d2);
		
		return d3;
	}
}
