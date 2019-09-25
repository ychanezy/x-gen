package cn.javass.xgen.genconf.implementors.dynamicparse;

import java.util.Map;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;

public class PropertyReplaceStrategy implements ParseStrategy{

	@Override
	public String parseDynamicContent(GenConfModel gm,
			Map<String, ExtendConfModel> mapEcms, String expr) {
		
		String retStr = "";
		ExtendConfModel ecm = mapEcms.get(expr);
		if(ecm != null){
			retStr = ecm.getValue();
		}
		
		return retStr;
	}

}
