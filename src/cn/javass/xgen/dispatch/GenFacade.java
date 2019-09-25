package cn.javass.xgen.dispatch;

import cn.javass.xgen.dispatch.command.CmdInvoker;
import cn.javass.xgen.dispatch.command.DefaultCommand;
import cn.javass.xgen.dispatch.command.GenCommand;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

public class GenFacade {
	//单例
	//工具类的实现
	
	/**
	 * 防止客户端无谓的创建实例
	 */
	private GenFacade(){
		
	}
	
	/**
	 * 提供给外部实用的方法，客户端通过这个方法来请求生成器运行，
	 * 生成所需要的配置数据按照默认的配置来
	 */
	public static void generate() {
		generate(CoreMediator.getInstance().getDefaultGenConfProvider());
	}

	/**
	 *提供给外部实用的方法，客户端通过这个方法来请求生成器运行，
	 * 生成所需要的配置数据  从 传入的获取配置数据的实现 中获得
	 * @param provider 获取配置数据的具体实现对象
	 */
	public static void generate(GenConfImplementor provider) {
		//循环生成在核心框架配置里面配置的需要生成的模块
		for(ModuleConfModel mcm : CoreMediator.getInstance().getNeedGenModuleConf(provider)){
			//具体的去生成每一个模块
			genOneModule(mcm);
		}
	}
	/**
	 * 具体的去生成每一个模块
	 * @param mcm
	 */
	private static void genOneModule(ModuleConfModel mcm){
		//发出一个命令，让x-gen按照配置去生成相应的内容
		//1：创建命令对象
		GenCommand cmd = new DefaultCommand(mcm);
		//2：创建invoker
		CmdInvoker invoker = new CmdInvoker();
		
		invoker.setCmd(cmd);
		
		//3：通过invoker执行命令
		invoker.doCommand();
	}
}
