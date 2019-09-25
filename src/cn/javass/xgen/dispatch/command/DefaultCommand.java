package cn.javass.xgen.dispatch.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javass.xgen.dispatch.executechain.DefaultHandler;
import cn.javass.xgen.dispatch.executechain.GenHandler;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

public class DefaultCommand implements GenCommand{
	/**
	 * 具体要生成的模块的配置 model
	 */
	private ModuleConfModel moduleConf;
	
	public DefaultCommand(ModuleConfModel moduleConf){
		this.moduleConf = moduleConf;
	}
	@Override
	public void execute() {
		//应该调用后续的模块去实现真正的功能
		
		//根据配置来动态组装 职责链，也就是按照生成的配置数据来组合需要生成的功能 
		List<String> listNeedGenTypes = new ArrayList<String>();
		//1：先把配置中配置的需要生成的功能的类型获取到，并放入List里面
		for(String s : moduleConf.getMapNeedGendTypes().keySet()){
			listNeedGenTypes.add(s);
		}
		
		//2：把list里面的数据转换到一个map中，key就是一个顺序值，value就是需要生成的类型的 处理器
		Map<Integer,GenHandler> mapHandler = new HashMap<Integer,GenHandler>();
		for(int i=0;i<listNeedGenTypes.size();i++){
			mapHandler.put(i+1, new DefaultHandler(listNeedGenTypes.get(i)));
		}
		//3：把map里面的数据按照顺序获取出来，构建职责链
		GenHandler h1 = mapHandler.get(1);
		for(int i=1;i<mapHandler.values().size();i++){
			mapHandler.get(i).setSuccessor(mapHandler.get(i+1));
		}
		
		//4：执行请求
		h1.handleRequest(moduleConf);
	}
}
