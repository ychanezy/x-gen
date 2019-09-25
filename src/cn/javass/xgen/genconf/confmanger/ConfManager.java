package cn.javass.xgen.genconf.confmanger;

import java.util.HashMap;
import java.util.Map;

import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.implementors.ModuleGenConfImplementor;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.genconf.vo.NeedGenModel;

//负责 真正去获取配置数据，并缓存配置数据
public class ConfManager {
	//////实现成单例
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
	/////定义需要缓存的数据	
	private GenConfModel genConf = new GenConfModel();
	private Map<String,ModuleConfModel> mapModuleConf = new HashMap<String,ModuleConfModel>();
	
	
	public GenConfModel getGenConf() {
		return this.genConf;
	}

	public Map<String, ModuleConfModel> getMapModuleConf() {
		return this.mapModuleConf;
	}
	
	
	
	private void readConf(GenConfImplementor provider){
		//这里去真正的 获取配置数据
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
		
		//解析etendsy应该放到其他解析的后面，因为如果有动态解析的话，需要使用其它的值
		mcm.setMapExtends(userGenConfImpl.getMapExtends(genConf,ngm.getMapParams()));
		
		//设置到缓存里面
		this.mapModuleConf.put(mcm.getModuleId(), mcm);
	}
	
	private void readGenConf(GenConfImplementor provider){
		//然后把 获取到的配置数据 设置到属性上，缓存下来
		genConf.setNeedGens(provider.getNeedGens());
		genConf.setThemes(provider.getThemes());
		genConf.setMapConstants(provider.getMapConstants());
	}	
}
