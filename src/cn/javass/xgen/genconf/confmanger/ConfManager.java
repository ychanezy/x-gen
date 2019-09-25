package cn.javass.xgen.genconf.confmanger;

import java.util.HashMap;
import java.util.Map;

import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.implementors.ModuleGenConfImplementor;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.genconf.vo.NeedGenModel;

//���� ����ȥ��ȡ�������ݣ���������������
public class ConfManager {
	//////ʵ�ֳɵ���
	private static ConfManager manager = null;
	private ConfManager(GenConfImplementor provider){
		readConf(provider);
	}
	
	public static ConfManager getInstance(GenConfImplementor provider){
		if(manager == null){
			manager = new ConfManager(provider);
		}
		return manager;
	}
	/////������Ҫ���������	
	private GenConfModel genConf = new GenConfModel();
	private Map<String,ModuleConfModel> mapModuleConf = new HashMap<String,ModuleConfModel>();
	
	
	public GenConfModel getGenConf() {
		return this.genConf;
	}

	public Map<String, ModuleConfModel> getMapModuleConf() {
		return this.mapModuleConf;
	}
	
	
	
	private void readConf(GenConfImplementor provider){
		//����ȥ������ ��ȡ��������
		readGenConf(provider);
		for(NeedGenModel ngm : genConf.getNeedGens()){
			readOneModuleGenConf(ngm);
		}
	}
	
	private void readOneModuleGenConf(NeedGenModel ngm){
		ModuleConfModel mcm = new ModuleConfModel();
		
		String providerClassName = this.genConf.getThemeById(ngm.getTheme()).getMapProviders().get(ngm.getProvider());
		
		ModuleGenConfImplementor userGenConfImpl = null;
		
		try{
			userGenConfImpl =  (ModuleGenConfImplementor)Class.forName(providerClassName).newInstance();
		}catch(Exception err){
			err.printStackTrace();
		}
		
		mcm = userGenConfImpl.getBaseModuleConfModel(ngm.getMapParams());
		mcm.setUseTheme(ngm.getTheme());
		
		mcm.setMapNeedGendTypes(userGenConfImpl.getMapNeedGenTypes(ngm.getMapParams()));
		
		//����etendsyӦ�÷ŵ����������ĺ��棬��Ϊ����ж�̬�����Ļ�����Ҫʹ��������ֵ
		mcm.setMapExtends(userGenConfImpl.getMapExtends(genConf,ngm.getMapParams()));
		
		//���õ���������
		this.mapModuleConf.put(mcm.getModuleId(), mcm);
	}
	
	private void readGenConf(GenConfImplementor provider){
		//Ȼ��� ��ȡ������������ ���õ������ϣ���������
		genConf.setNeedGens(provider.getNeedGens());
		genConf.setThemes(provider.getThemes());
		genConf.setMapConstants(provider.getMapConstants());
	}	
}
