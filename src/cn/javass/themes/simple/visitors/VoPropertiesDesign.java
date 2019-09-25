package cn.javass.themes.simple.visitors;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.template.visitors.TemplateElement;
import cn.javass.xgen.template.visitors.Visitor;

public class VoPropertiesDesign implements Visitor {

	@Override
	public Object visitTemplateElement(TemplateElement element) {
		ModuleConfModel moduleConf = element.getModuleConf();
		return genProperties(moduleConf,"voFields","voFieldsTypes");
	}
	private static String genProperties(ModuleConfModel moduleConf,String fieldsName,String fieldsTypesName){
		String [] voFields = moduleConf.getMapExtends().get(fieldsName).getValues();		
		String [] voFieldsTypes = moduleConf.getMapExtends().get(fieldsTypesName).getValues();
		
		StringBuffer buffer = new StringBuffer("");
		for(int i=0;i<voFields.length;i++){
			buffer.append("private "+voFieldsTypes[i]+" "+voFields[i]+";\n\t");
		}
		
		return buffer.toString();
	}

}