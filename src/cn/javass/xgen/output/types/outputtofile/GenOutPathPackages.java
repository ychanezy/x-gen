package cn.javass.xgen.output.types.outputtofile;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

public interface GenOutPathPackages {
	/**
	 * ������������������������ļ���
	 * @param moduleConf
	 * @param genTypeId
	 * @return
	 */
	public boolean genPackages(ModuleConfModel moduleConf,String genTypeId);
	
	/**
	 * ��ȡ������������ļ��� ·�����ļ���
	 * @param moduleConf
	 * @param genTypeId
	 * @return
	 */
	public String getOutPathAndFileName(ModuleConfModel moduleConf,String genTypeId);	
}
