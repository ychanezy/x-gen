package cn.javass.xgen.geninvocation.state;

import cn.javass.xgen.geninvocation.DefaultGenInvocation;
/**
 * ������״̬�ӿ�
 */
public interface State {
	/**
	 * ִ��״̬����Ӧ�Ĺ��ܴ���
	 * @param ctx ������ʵ������
	 */
	public void doWork(DefaultGenInvocation ctx);
}
