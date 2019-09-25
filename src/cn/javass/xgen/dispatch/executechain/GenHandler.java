package cn.javass.xgen.dispatch.executechain;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

public abstract class GenHandler {
	/**
	 * ���к�̵�ְ�����
	 */
	protected GenHandler successor;

	public void setSuccessor(GenHandler successor) {
		this.successor = successor;
	}	
	/**
	 * ȱʡʵ�ִ�������ķ���
	 * @param mcm
	 */
	public void handleRequest(ModuleConfModel mcm){
		//����������Լ���ְ��Χ���Ǿ��ж��Ƿ��к�̵�ְ�����
		//����У���ת���������̵�ְ�����
		if(this.successor != null){
			this.successor.handleRequest(mcm);
		}
	}	
}
