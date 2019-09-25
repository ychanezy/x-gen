package cn.javass.xgen.output.types;

import java.util.Observable;

import cn.javass.xgen.output.GenOutputEbi;

public class OutputToConsole implements GenOutputEbi {

	@Override
	public void update(Observable o, Object obj) {
		String content = ""+obj;
		
		System.out.println("now Content="+content);
	}

}
