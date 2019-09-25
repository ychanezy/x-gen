package cn.javass.xgen.dispatch.executechain;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

public class DefaultHandler extends GenHandler {
	/**
	 * ��Ҫ���ɵı�ʶ
	 */
	private String needGenType = "";
	
	public DefaultHandler(String needGenType){
		this.needGenType = needGenType;
	}
	
	public void handleRequest(ModuleConfModel mcm){
		//1����ְ�����Ҫʵ�ֵĹ���    ��  �������� ���� ʵ�� ���ɹ��ܵ�ģ��
		CoreMediator.getInstance().needProxyGen(needGenType,mcm);
		
		//2���������࣬����ְ�����ĺ�������
		super.handleRequest(mcm);
	}
}
