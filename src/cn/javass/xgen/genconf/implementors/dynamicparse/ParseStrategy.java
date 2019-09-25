package cn.javass.xgen.genconf.implementors.dynamicparse;

import java.util.Map;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;
/**
 * ����ģ����������Ҫ��̬�������ַ����Ĳ��Խӿ�
 */
public interface ParseStrategy {
	/**
	 * ������̬���ݣ�����������ַ���
	 * @param gm  ��������ֵ
	 * @param mapEcms ��������ֵ
	 * @param expr ��Ҫ��̬�������ֵ��ַ���
	 * @return ������̬������� �����������ַ���
	 */
	public String parseDynamicContent(GenConfModel gm,Map<String,ExtendConfModel> mapEcms,String expr);
}
