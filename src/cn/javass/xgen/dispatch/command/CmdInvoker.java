package cn.javass.xgen.dispatch.command;

public class CmdInvoker {
	/**
	 * 持有的命令对象
	 */
	private GenCommand cmd = null;
	/**
	 * 设置需要持有的命令对象
	 * @param cmd
	 */
	public void setCmd(GenCommand cmd){
		this.cmd = cmd;
	}
	/**
	 * 提供给客户端使用的方法，让客户来请求执行命令
	 */
	public void doCommand(){
		this.cmd.execute();
	}
}
