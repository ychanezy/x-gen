package cn.javass.xgen.genconf;

import java.util.Map;

import cn.javass.xgen.genconf.confmanger.ConfManager;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.GenTypeModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

//���� ��� ���ù���ģ���ҵ����
public class GenConfEbo implements GenConfEbi{
	private static GenConfEbo ebo = null;
	
	private  GenConfEbo(GenConfImplementor provider){
		this.provider = provider;
		
	}
	public static GenConfEbi getInstance(GenConfImplementor provider){
		if(ebo == null){
			if(provider == null){
				throw new IllegalArgumentException("��һ�δ������ö���ʱ��provider����Ϊ��");
			}
			ebo = new GenConfEbo(provider);
		}
		return ebo;
	}
	/**
	 * ���� ��ȡ���Ŀ���������ݵ� ����ʵ�ֽӿڶ���
	 */
	private GenConfImplementor provider = null;
	
	@Override
	public GenConfModel getGenConf() {
		return ConfManager.getInstance(provider).getGenConf();
	}

	@Override
	public Map<String, ModuleConfModel> getMapModuleConf() {
		return ConfManager.getInstance(provider).getMapModuleConf();
	}
	@Override
	public GenTypeModel getThemeGenType(ModuleConfModel moduleConf,
			String needGenTypeId) {
		return getGenConf().getThemeById(moduleConf.getUseTheme()).getMapGenTypes().get(needGenTypeId);
	}
	@Override
	public String getThemeGenOutTypeClass(ModuleConfModel moduleConf,
			String needGenOutTypeId) {
		return this.getGenConf().getThemeById(moduleConf.getUseTheme()).getMapGenOutTypes().get(needGenOutTypeId);
	}
}
