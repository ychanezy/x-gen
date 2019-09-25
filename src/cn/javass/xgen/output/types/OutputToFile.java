package cn.javass.xgen.output.types;

import java.util.Observable;

import cn.javass.xgen.mediator.CoreMediator;
import cn.javass.xgen.output.GenOutputEbi;
import cn.javass.xgen.output.types.outputtofile.AbstractFactory;
import cn.javass.xgen.output.types.outputtofile.plaintxt.PlainTxtFactory;

public class OutputToFile implements GenOutputEbi {

	@Override
	public void update(Observable o, Object obj) {
		//1������Ҫ�õ�Ҫ���������
		String content = ""+obj;
		
		//2��������������������ļ����ļ���
		AbstractFactory af = new PlainTxtFactory();
		
		af.createGenOutPathPackages().genPackages(
				CoreMediator.getInstance().getObserverModuleConf(o),
				CoreMediator.getInstance().getObserverGenTypeId(o));
		
		//3��������ļ�������ļ�����ȥ
		//3.1:�õ��ļ���·�����ļ���
		String outPathAndFileName =  af.createGenOutPathPackages().getOutPathAndFileName(
				CoreMediator.getInstance().getObserverModuleConf(o),
				CoreMediator.getInstance().getObserverGenTypeId(o));
		//3.2:ʹ��outter�������
		af.createOutter().writeContent(outPathAndFileName, content);
	}
}
