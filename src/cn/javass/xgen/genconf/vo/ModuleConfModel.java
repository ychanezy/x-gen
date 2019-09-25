package cn.javass.xgen.genconf.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleConfModel {
	/**
	 * 用户需要生成的模块的标识
	 */
	private String moduleId = "";
	/**
	 * 用户需要生成这个模块所使用的外部主题的标识
	 */
	private String useTheme = "";
	/**
	 * 用户需要生成的具体功能，key-需要生成的功能的标识，value-该功能生成后的多种输出类型的标识的集会
	 */
	private Map<String,List<String>> mapNeedGendTypes = new HashMap<String,List<String>>();
	/**
	 * 模块生成所需要的扩展数据，key-数据的id，value-对应的扩展数据的model
	 */
	private Map<String,ExtendConfModel> mapExtends = new HashMap<String,ExtendConfModel>();
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getUseTheme() {
		return useTheme;
	}
	public void setUseTheme(String useTheme) {
		this.useTheme = useTheme;
	}
	public Map<String, List<String>> getMapNeedGendTypes() {
		return mapNeedGendTypes;
	}
	public void setMapNeedGendTypes(Map<String, List<String>> mapNeedGendTypes) {
		this.mapNeedGendTypes = mapNeedGendTypes;
	}
	public Map<String, ExtendConfModel> getMapExtends() {
		return mapExtends;
	}
	public void setMapExtends(Map<String, ExtendConfModel> mapExtends) {
		this.mapExtends = mapExtends;
	}
	@Override
	public String toString() {
		return "ModuleConfModel [moduleId=" + moduleId + ", useTheme="
				+ useTheme + ", mapNeedGendTypes=" + mapNeedGendTypes
				+ ", mapExtends=" + mapExtends + "]";
	}
	
	
}
