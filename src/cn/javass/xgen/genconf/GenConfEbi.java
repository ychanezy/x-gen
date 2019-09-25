package cn.javass.xgen.genconf;

import java.util.Map;

import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.GenTypeModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

public interface GenConfEbi {
	/**
	 * 获取x-gen核心框架运行所需要的配置数据model
	 * @return 核心框架运行所需要的配置数据model
	 */
	public GenConfModel getGenConf();
	/**
	 * 获取需要生成的所有模块的配置
	 * @return 包含所有需要生成的所有模块的配置数据的Map，key-模块的id，value-相应的模块的配置数据的model
	 */
	public Map<String,ModuleConfModel> getMapModuleConf();
	
	
	///////////////////////////////////提供点友好方法
	/**
	 * 根据需要生成的模块配置和theme中的生成类型的编号，来获取相应的theme中的生成类型的model
	 * @param moduleConf
	 * @param needGenTypeId
	 * @return
	 */
	public GenTypeModel getThemeGenType(ModuleConfModel moduleConf,String needGenTypeId);
	/**
	 * 根据需要生成的模块配置和theme中的输出类型的编号，来获取相应的输出类型 的 类定义
	 * @param moduleConf
	 * @param needGenOutTypeId
	 * @return
	 */
	public String getThemeGenOutTypeClass(ModuleConfModel moduleConf,String genOutTypeId);
}
