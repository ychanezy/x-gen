package cn.javass.xgen.genconf.implementors;

import java.util.List;
import java.util.Map;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

public interface ModuleGenConfImplementor {
	/**
	 * 根据核心框架里面注册的需要生成模块的配置参数，来获取相应的需要生成模块的配置数据model，数据只有基本的部分
	 * @param param 在核心框架里面注册的需要生成模块的配置参数
	 * @return 相应的需要生成模块的配置数据model，数据只有基本的部分
	 */
	public ModuleConfModel getBaseModuleConfModel(Map<String,String> param);
	/**
	 * 根据核心框架里面注册的需要生成模块的配置参数，来获取需要生成模块中配置的需要生成的功能类型
	 * @param param 在核心框架里面注册的需要生成模块的配置参数
	 * @return
	 */
	public Map<String,List<String>> getMapNeedGenTypes(Map<String,String> param);
	
	public Map<String,ExtendConfModel> getMapExtends(GenConfModel gm,Map<String,String> param);
}
