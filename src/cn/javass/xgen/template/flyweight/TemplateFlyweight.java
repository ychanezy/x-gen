package cn.javass.xgen.template.flyweight;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

public interface TemplateFlyweight {
	/**
	 * �������ú�Ĭ�ϵ��﷨���滻ģ���е�����ֵ
	 * @param moduleConf
	 * @param content ��Ϊ��δ���� �������
	 * @return ����������ģ������
	 */
	public Object replaceProperties(ModuleConfModel moduleConf,Object content);
	/**
	 * �������ú�Ĭ�ϵ��﷨�����з��������滻ģ���е���Ӧλ�õ�ֵ
	 * @param moduleConf
	 * @param content ��Ϊ��δ���� �������
	 * @return ����������ģ������
	 */
	public Object replaceMethods(ModuleConfModel moduleConf,Object content);
	
	/**
	 * ��ȡԭʼ��ģ���ļ�������
	 * @return
	 */
	public Object getRawContent();
}
