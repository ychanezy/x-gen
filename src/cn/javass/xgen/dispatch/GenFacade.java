package cn.javass.xgen.dispatch;

import cn.javass.xgen.dispatch.command.CmdInvoker;
import cn.javass.xgen.dispatch.command.DefaultCommand;
import cn.javass.xgen.dispatch.command.GenCommand;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

public class GenFacade {
	//����
	//�������ʵ��
	
	/**
	 * ��ֹ�ͻ�����ν�Ĵ���ʵ��
	 */
	private GenFacade(){
		
	}
	
	/**
	 * �ṩ���ⲿʵ�õķ������ͻ���ͨ������������������������У�
	 * ��������Ҫ���������ݰ���Ĭ�ϵ�������
	 */
	public static void generate() {
		generate(CoreMediator.getInstance().getDefaultGenConfProvider());
	}

	/**
	 *�ṩ���ⲿʵ�õķ������ͻ���ͨ������������������������У�
	 * ��������Ҫ����������  �� ����Ļ�ȡ�������ݵ�ʵ�� �л��
	 * @param provider ��ȡ�������ݵľ���ʵ�ֶ���
	 */
	public static void generate(GenConfImplementor provider) {
		//ѭ�������ں��Ŀ�������������õ���Ҫ���ɵ�ģ��
		for(ModuleConfModel mcm : CoreMediator.getInstance().getNeedGenModuleConf(provider)){
			//�����ȥ����ÿһ��ģ��
			genOneModule(mcm);
		}
	}
	/**
	 * �����ȥ����ÿһ��ģ��
	 * @param mcm
	 */
	private static void genOneModule(ModuleConfModel mcm){
		//����һ�������x-gen��������ȥ������Ӧ������
		//1�������������
		GenCommand cmd = new DefaultCommand(mcm);
		//2������invoker
		CmdInvoker invoker = new CmdInvoker();
		
		invoker.setCmd(cmd);
		
		//3��ͨ��invokerִ������
		invoker.doCommand();
	}
}
