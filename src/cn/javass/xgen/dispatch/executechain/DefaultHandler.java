package cn.javass.xgen.dispatch.executechain;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

public class DefaultHandler extends GenHandler {
	/**
	 * 需要生成的标识
	 */
	private String needGenType = "";
	
	public DefaultHandler(String needGenType){
		this.needGenType = needGenType;
	}
	
	public void handleRequest(ModuleConfModel mcm){
		//1：本职责对象要实现的功能    ：  继续调用 真正 实现 生成功能的模块
		CoreMediator.getInstance().needProxyGen(needGenType,mcm);
		
		//2：交给父类，继续职责链的后续处理
		super.handleRequest(mcm);
	}
}
