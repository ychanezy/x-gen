package cn.javass.xgen.genconf.implementors;

import java.util.List;
import java.util.Map;

import cn.javass.xgen.genconf.vo.NeedGenModel;
import cn.javass.xgen.genconf.vo.ThemeModel;
/**
 * ��ȡ���Ŀ���������ݵĽӿ�
 */
public interface GenConfImplementor {
	/**
	 * ��ȡ���Ŀ��������ע�����Ҫ���ɵ�ģ�����������
	 * @return
	 */
	public List<NeedGenModel> getNeedGens();
	/**
	 * ��ȡ���Ŀ��������ע����ⲿ�������������
	 * @return
	 */
	public List<ThemeModel> getThemes();
	/**
	 * ��ȡ���Ŀ��������ע��Ķ���Ĺ�������
	 * @return
	 */
	public Map<String,String> getMapConstants();
	
}
