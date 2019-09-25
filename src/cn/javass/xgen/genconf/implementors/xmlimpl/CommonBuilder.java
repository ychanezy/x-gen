package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.constants.GenConfEnum;

public abstract class CommonBuilder<T> {
	/**
	 * ������ȡ �ֲ���ƴ�ӵ��ַ�����Ҳ�������ղ�Ʒ
	 */
	protected abstract StringBuffer getBuilderBuffer();
	/**
	 * ��ȡʵ�ʵĹ���������
	 * @return
	 */
	protected abstract T getBuilderClassInstance();
			
	/**
	 * ƴ�� ��.�� , ֧����׺
	 * @return
	 */
	public T addDot(){
		getBuilderBuffer().append(ExpressionEnum.dot.getExpr());
		return getBuilderClassInstance();
	}
	
	/**
	 * ƴ�� ��/�� , ֧����׺
	 * @return
	 */
	public T addSeparator(){
		getBuilderBuffer().append(ExpressionEnum.separator.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * ƴ�� ��$�� , ֧����׺
	 * @return
	 */
	public T addDollar(){
		getBuilderBuffer().append(ExpressionEnum.dollar.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * ƴ�� ��[�� , ֧����׺
	 * @return
	 */
	public T addOpenBracket(){
		getBuilderBuffer().append(ExpressionEnum.openBracket.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * ƴ�� ��]�� , ֧����׺
	 * @return
	 */
	public T addCloseBracket(){
		getBuilderBuffer().append(ExpressionEnum.closeBracket.getExpr());
		return getBuilderClassInstance();
	}
	
	/**
	 * ƴ�� ��=�� , ֧����׺
	 * @return
	 */
	public T addEqual(){
		getBuilderBuffer().append(ExpressionEnum.equal.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * ƴ�� ��,�� , ֧����׺
	 * @return
	 */
	public T addComma(){
		getBuilderBuffer().append(ExpressionEnum.comma.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * ƴ�� ��xml�� , ֧����׺
	 * @return
	 */
	public T addXml(){
		getBuilderBuffer().append(ExpressionEnum.xml.getExpr());
		return getBuilderClassInstance();
	}
	
	/**
	 * ƴ�� ��xmlFilePre�� , ֧����׺
	 * @return
	 */
	public T addXmlFilePre(){
		getBuilderBuffer().append(ExpressionEnum.xmlFilePre.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * ƴ�� ��id�� , ֧����׺
	 * @return
	 */
	public T addId(){
		getBuilderBuffer().append(GenConfEnum.id);
		return getBuilderClassInstance();
	}
	
	/**
	 * ƴ��  �����ֵ , ֧����׺
	 * @return
	 */
	public T addOtherValue(String value){
		getBuilderBuffer().append(value);
		return getBuilderClassInstance();
	}
	
	
	
	public String build(){
		return getBuilderBuffer().toString();
	}
}
