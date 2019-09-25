package cn.javass.xgen.genproxy;

import cn.javass.xgen.geninvocation.GenInvocation;

/**
 * ���ɴ�����ʵ��һ�����ڷַ����Ⱥ� �������� ֮��Ķ���ĸ��Ӳ㡣
 * Ŀ�ľ���Ϊ�˽����Ը�����Ҫ�л���ͬ�� ����ʵ�֡�
 */
public class DefaultProxy implements GenInvocation {
	/**
	 * ���е�����ִ��generate�Ķ���
	 */
	private GenInvocation invocation = null;
	
	public DefaultProxy(GenInvocation invocation){
		this.invocation = invocation;
	}
	
	@Override
	public void executeGen() {
		//�������������invocation
		this.invocation.executeGen();
	}
}
