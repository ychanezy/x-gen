package cn.javass.xgen.output.types.outputtofile.plaintxt;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;
import cn.javass.xgen.output.types.outputtofile.GenOutPathPackages;
import cn.javass.xgen.util.file.FileHelper;

public class GenOutPathPackageImpl implements GenOutPathPackages{

	@Override
	public boolean genPackages(ModuleConfModel moduleConf, String genTypeId) {
		
		FileHelper.genDir(this.getDirPath(moduleConf, genTypeId));
		
		return true;
	}
	private String getDirPath(ModuleConfModel moduleConf, String genTypeId){
		String codeOutPath = moduleConf.getMapExtends().get("codeOutPath").getValue();
		
		String relativePath = CoreMediator.getInstance().getGenTypeParams(moduleConf, genTypeId).get("relativePath");
		String packagePath = moduleConf.getMapExtends().get("modulePackge").getValue();
		
		String dirPackages = codeOutPath+ExpressionEnum.dot.getExpr()+
				packagePath + ExpressionEnum.dot.getExpr()+relativePath;
		String packages = dirPackages.replace(ExpressionEnum.dot.getExpr(), ExpressionEnum.separator.getExpr());
		
		return packages;
	}

	@Override
	public String getOutPathAndFileName(ModuleConfModel moduleConf,
			String genTypeId) {
		
		String preName = CoreMediator.getInstance().getGenTypeParams(moduleConf, genTypeId).get("preGenFileName");
		String afterName = CoreMediator.getInstance().getGenTypeParams(moduleConf, genTypeId).get("afterGenFileName");
		
		
		String moduleNameSuperCase = moduleConf.getMapExtends().get("moduleNameSuperCase").getValue();
		
		String retName = this.getDirPath(moduleConf, genTypeId)
				+ExpressionEnum.separator.getExpr()
				+preName
				+moduleNameSuperCase
				+afterName;
				
		return retName;
	}

}
