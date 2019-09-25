package cn.javass.xgen.geninvocation;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.geninvocation.state.DefaultBeginState;
import cn.javass.xgen.geninvocation.state.State;

public class DefaultGenInvocation extends java.util.Observable implements GenInvocation{
	/**
	 * ����һ��״̬����
	 */
	private State state = null;
	/**
	 * ����Ҫ���ɵ����ͱ�ʶ
	 */
	private String needGenType = "";

	/**
	 * ��ǰ��generateģ�����������
	 */
	private ModuleConfModel moduleConf;
	/**
	 * ��Ŷ�����������е���ʱ����
	 */
	private Object tempContent =  null;
	
	
	public DefaultGenInvocation(String needGenType,ModuleConfModel moduleConf){
		this.needGenType = needGenType;
		this.moduleConf = moduleConf;
	}
	/**
	 * ִ�й�������ÿһ��State����Լ��Ĺ���֮�����
	 */
	public void doWork(){
		this.state.doWork(this);
	}
	
	
	@Override
	public void executeGen() {
		//����generate�������̿�ʼ��Ҫִ�е�״̬
		state = new DefaultBeginState();
		//��״ִ̬�й���
		state.doWork(this);
		
		//��һ�󲽣�����һ�����̵�����Ӧ������ʵ�֣���ȡ���ɵ�����
		//�ڶ��󲽣������ɵ����������ȥ
	}
	/**
	 * ֪ͨ�����Ѿ����ɺ��ˣ����Դ���������
	 * @param obj
	 */
	public void setContentOver(Object obj){
		this.setChanged();
		this.notifyObservers(obj);
	}
	
	
	public String getNeedGenType() {
		return needGenType;
	}
	public ModuleConfModel getModuleConf() {
		return moduleConf;
	}
	public void setState(State state){
		this.state = state;
	}
	public Object getTempContent() {
		return tempContent;
	}
	public void setTempContent(Object tempContent) {
		this.tempContent = tempContent;
	}
}
