package cn.javass.xgen.genconf.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * x-gen���Ŀ�����ö�Ӧ������model 
 */
public class GenConfModel {
	/**
	 * ����ע��Ķ�� �û���Ҫ���ɵ�ģ���model
	 */
	private List<NeedGenModel> needGens = new ArrayList<NeedGenModel>();
	/**
	 * ����ע��Ķ���ⲿ�����model
	 */
	private List<ThemeModel> themes = new ArrayList<ThemeModel>();
	/**
	 * ����ͨ�õ� ��������ļ���
	 */
	private Map<String,String> mapConstants = new HashMap<String,String>();
	
	public List<NeedGenModel> getNeedGens() {
		return needGens;
	}
	public void setNeedGens(List<NeedGenModel> needGens) {
		this.needGens = needGens;
	}
	public List<ThemeModel> getThemes() {
		return themes;
	}
	public void setThemes(List<ThemeModel> themes) {
		this.themes = themes;
	}
	public Map<String, String> getMapConstants() {
		return mapConstants;
	}
	public void setMapConstants(Map<String, String> mapConstants) {
		this.mapConstants = mapConstants;
	}
	
	
	public ThemeModel getThemeById(String themeId){
		for(ThemeModel tm : this.themes ){
			if(tm.getId().equals(themeId)){
				return tm;
			}
		}
		return new ThemeModel();
	}
	@Override
	public String toString() {
		return "GenConfModel [needGens=" + needGens + ", themes=" + themes
				+ ", mapConstants=" + mapConstants + "]";
	}
	
}
