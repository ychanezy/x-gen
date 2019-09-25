package cn.javass.xgen.output.types.outputtofile.plaintxt;

import cn.javass.xgen.output.types.outputtofile.AbstractFactory;
import cn.javass.xgen.output.types.outputtofile.GenOutPathPackages;
import cn.javass.xgen.output.types.outputtofile.Outter;

public class PlainTxtFactory implements AbstractFactory{
	
	@Override
	public GenOutPathPackages createGenOutPathPackages() {
		return new GenOutPathPackageImpl();
	}

	@Override
	public Outter createOutter() {
		return new OutterImpl();
	}

}
