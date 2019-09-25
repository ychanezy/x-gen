package cn.javass.xgen.genconf.implementors;

import java.util.List;
import java.util.Map;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

public interface ModuleGenConfImplementor {
	/**
	 * ���ݺ��Ŀ������ע�����Ҫ����ģ������ò���������ȡ��Ӧ����Ҫ����ģ�����������model������ֻ�л����Ĳ���
	 * @param param �ں��Ŀ������ע�����Ҫ����ģ������ò���
	 * @return ��Ӧ����Ҫ����ģ�����������model������ֻ�л����Ĳ���
	 */
	public ModuleConfModel getBaseModuleConfModel(Map<String,String> param);
	/**
	 * ���ݺ��Ŀ������ע�����Ҫ����ģ������ò���������ȡ��Ҫ����ģ�������õ���Ҫ���ɵĹ�������
	 * @param param �ں��Ŀ������ע�����Ҫ����ģ������ò���
	 * @return
	 */
	public Map<String,List<String>> getMapNeedGenTypes(Map<String,String> param);
	
	public Map<String,ExtendConfModel> getMapExtends(GenConfModel gm,Map<String,String> param);
}
