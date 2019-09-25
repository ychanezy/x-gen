package cn.javass.xgen.genconf.implementors;

import java.util.Map;

import cn.javass.xgen.genconf.vo.GenTypeModel;

public interface ThemeImplementer {
	/**
	 * ����theme Id ���ں��Ŀ������ע��themeʱ���õ���Ӧ�Ĳ���������ȡtheme�ж���������ɵĹ�������
	 * @param themeId theme Id 
	 * @param params �ں��Ŀ������ע��themeʱ���õ���Ӧ�Ĳ���
	 * @return theme�ж���������ɵĹ�������
	 */
	public Map<String,GenTypeModel> getMapGenTypes(String themeId,Map<String,String> params);
	
	public Map<String,String> getMapGenOutTypes(String themeId,Map<String,String> params);
	
	public Map<String,String> getMapProviders(String themeId,Map<String,String> params);
}
