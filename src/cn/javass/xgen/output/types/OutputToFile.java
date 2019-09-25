package cn.javass.xgen.output.types;

import java.util.Observable;

import cn.javass.xgen.mediator.CoreMediator;
import cn.javass.xgen.output.GenOutputEbi;
import cn.javass.xgen.output.types.outputtofile.AbstractFactory;
import cn.javass.xgen.output.types.outputtofile.plaintxt.PlainTxtFactory;

public class OutputToFile implements GenOutputEbi {

	@Override
	public void update(Observable o, Object obj) {
		//1：首先要得到要输出的内容
		String content = ""+obj;
		
		//2：根据配置来创建输出文件的文件夹
		AbstractFactory af = new PlainTxtFactory();
		
		af.createGenOutPathPackages().genPackages(
				CoreMediator.getInstance().getObserverModuleConf(o),
				CoreMediator.getInstance().getObserverGenTypeId(o));
		
		//3：输出成文件到这个文件夹中去
		//3.1:得到文件的路径和文件名
		String outPathAndFileName =  af.createGenOutPathPackages().getOutPathAndFileName(
				CoreMediator.getInstance().getObserverModuleConf(o),
				CoreMediator.getInstance().getObserverGenTypeId(o));
		//3.2:使用outter对象输出
		af.createOutter().writeContent(outPathAndFileName, content);
	}
}
