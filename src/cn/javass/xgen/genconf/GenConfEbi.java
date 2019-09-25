package cn.javass.xgen.genconf;

import java.util.Map;

import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.GenTypeModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

public interface GenConfEbi {
	/**
	 * ��ȡx-gen���Ŀ����������Ҫ����������model
	 * @return ���Ŀ����������Ҫ����������model
	 */
	public GenConfModel getGenConf();
	/**
	 * ��ȡ��Ҫ���ɵ�����ģ�������
	 * @return ����������Ҫ���ɵ�����ģ����������ݵ�Map��key-ģ���id��value-��Ӧ��ģ����������ݵ�model
	 */
	public Map<String,ModuleConfModel> getMapModuleConf();
	
	
	///////////////////////////////////�ṩ���Ѻ÷���
	/**
	 * ������Ҫ���ɵ�ģ�����ú�theme�е��������͵ı�ţ�����ȡ��Ӧ��theme�е��������͵�model
	 * @param moduleConf
	 * @param needGenTypeId
	 * @return
	 */
	public GenTypeModel getThemeGenType(ModuleConfModel moduleConf,String needGenTypeId);
	/**
	 * ������Ҫ���ɵ�ģ�����ú�theme�е�������͵ı�ţ�����ȡ��Ӧ��������� �� �ඨ��
	 * @param moduleConf
	 * @param needGenOutTypeId
	 * @return
	 */
	public String getThemeGenOutTypeClass(ModuleConfModel moduleConf,String genOutTypeId);
}
