package cn.javass.xgen.genconf;

import cn.javass.xgen.genconf.implementors.GenConfImplementor;

public class GenConfFactory {
	private GenConfFactory(){
		
	}
	
	public static GenConfEbi createGenConfEbi(GenConfImplementor provider){
		return GenConfEbo.getInstance(provider);
	}
	/**
	 * �������ʺ������õĽӿڶ���,ǰ����ȷ���Ѿ���ȡ����������,�������������ȷִ��
	 * @return
	 */
	public static GenConfEbi createGenConfEbi(){
		return createGenConfEbi(null);
	}
}
