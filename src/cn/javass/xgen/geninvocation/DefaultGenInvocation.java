package cn.javass.xgen.geninvocation;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.geninvocation.state.DefaultBeginState;
import cn.javass.xgen.geninvocation.state.State;

public class DefaultGenInvocation extends java.util.Observable implements GenInvocation{
	/**
	 * 持有一个状态对象
	 */
	private State state = null;
	/**
	 * 具体要生成的类型标识
	 */
	private String needGenType = "";

	/**
	 * 当前被generate模块的配置数据
	 */
	private ModuleConfModel moduleConf;
	/**
	 * 存放多个解析过程中的临时内容
	 */
	private Object tempContent =  null;
	
	
	public DefaultGenInvocation(String needGenType,ModuleConfModel moduleConf){
		this.needGenType = needGenType;
		this.moduleConf = moduleConf;
	}
	/**
	 * 执行工作，在每一个State完成自己的工作之后调用
	 */
	public void doWork(){
		this.state.doWork(this);
	}
	
	
	@Override
	public void executeGen() {
		//设置generate调用流程开始需要执行的状态
		state = new DefaultBeginState();
		//让状态执行工作
		state.doWork(this);
		
		//第一大步：按照一定流程调用相应的生成实现，获取生成的内容
		//第二大步：把生成的内容输出出去
	}
	/**
	 * 通知内容已经生成好了，可以触发联动了
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
