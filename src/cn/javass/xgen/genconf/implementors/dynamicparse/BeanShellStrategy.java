package cn.javass.xgen.genconf.implementors.dynamicparse;

import java.util.Map;

import bsh.Interpreter;
import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;

public class BeanShellStrategy implements ParseStrategy{

	@Override
	public String parseDynamicContent(GenConfModel gm,
			Map<String, ExtendConfModel> mapEcms, String expr) {
		Interpreter interpreter = new Interpreter();
		String retStr = "";
		try {
			//设置要传入的参数
			interpreter.set("gm",gm);
			interpreter.set("mapEcms", mapEcms);
			
			//执行
			interpreter.eval("retValue="+expr);
			
			//获取返回值
			retStr = interpreter.get("retValue").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retStr;
	}
}
