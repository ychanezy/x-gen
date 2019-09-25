package cn.javass.xgen.genconf.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleConfModel {
	/**
	 * �û���Ҫ���ɵ�ģ��ı�ʶ
	 */
	private String moduleId = "";
	/**
	 * �û���Ҫ�������ģ����ʹ�õ��ⲿ����ı�ʶ
	 */
	private String useTheme = "";
	/**
	 * �û���Ҫ���ɵľ��幦�ܣ�key-��Ҫ���ɵĹ��ܵı�ʶ��value-�ù������ɺ�Ķ���������͵ı�ʶ�ļ���
	 */
	private Map<String,List<String>> mapNeedGendTypes = new HashMap<String,List<String>>();
	/**
	 * ģ����������Ҫ����չ���ݣ�key-���ݵ�id��value-��Ӧ����չ���ݵ�model
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
