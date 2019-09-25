package cn.javass.xgen.genconf.implementors;

import java.util.Map;

import cn.javass.xgen.genconf.vo.GenTypeModel;

public interface ThemeImplementer {
	/**
	 * 根据theme Id 和在核心框架里面注册theme时配置的相应的参数，来获取theme中定义的能生成的功能类型
	 * @param themeId theme Id 
	 * @param params 在核心框架里面注册theme时配置的相应的参数
	 * @return theme中定义的能生成的功能类型
	 */
	public Map<String,GenTypeModel> getMapGenTypes(String themeId,Map<String,String> params);
	
	public Map<String,String> getMapGenOutTypes(String themeId,Map<String,String> params);
	
	public Map<String,String> getMapProviders(String themeId,Map<String,String> params);
}
