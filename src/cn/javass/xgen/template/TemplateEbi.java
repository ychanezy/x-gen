package cn.javass.xgen.template;

public interface TemplateEbi {
	/**
	 * ����Ĭ�ϵ��﷨ȥ�滻ģ���е����Ա���
	 * @return
	 */
	public Object replaceProperties();
	/**
	 * ����Ĭ�ϵ��﷨ȥ���з��������ѽ���滻��ģ���С�
	 * ʹ�÷�������ʵ��
	 * @return
	 */
	public Object replaceMethods();
	/**
	 * ��ȡģ�嵱ǰ�����ݣ����ܲ���ԭʼ���ݣ�Ҳ���ܲ������յ����ݣ�
	 * ���Ǿ�������ĵ�ǰ������
	 * @return
	 */
	public Object getNowContent();
	
}
