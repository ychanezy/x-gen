package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.constants.GenConfEnum;

public abstract class CommonBuilder<T> {
	/**
	 * 用来获取 分步骤拼接的字符串，也就是最终产品
	 */
	protected abstract StringBuffer getBuilderBuffer();
	/**
	 * 获取实际的构建器对象
	 * @return
	 */
	protected abstract T getBuilderClassInstance();
			
	/**
	 * 拼接 “.” , 支持连缀
	 * @return
	 */
	public T addDot(){
		getBuilderBuffer().append(ExpressionEnum.dot.getExpr());
		return getBuilderClassInstance();
	}
	
	/**
	 * 拼接 “/” , 支持连缀
	 * @return
	 */
	public T addSeparator(){
		getBuilderBuffer().append(ExpressionEnum.separator.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * 拼接 “$” , 支持连缀
	 * @return
	 */
	public T addDollar(){
		getBuilderBuffer().append(ExpressionEnum.dollar.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * 拼接 “[” , 支持连缀
	 * @return
	 */
	public T addOpenBracket(){
		getBuilderBuffer().append(ExpressionEnum.openBracket.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * 拼接 “]” , 支持连缀
	 * @return
	 */
	public T addCloseBracket(){
		getBuilderBuffer().append(ExpressionEnum.closeBracket.getExpr());
		return getBuilderClassInstance();
	}
	
	/**
	 * 拼接 “=” , 支持连缀
	 * @return
	 */
	public T addEqual(){
		getBuilderBuffer().append(ExpressionEnum.equal.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * 拼接 “,” , 支持连缀
	 * @return
	 */
	public T addComma(){
		getBuilderBuffer().append(ExpressionEnum.comma.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * 拼接 “xml” , 支持连缀
	 * @return
	 */
	public T addXml(){
		getBuilderBuffer().append(ExpressionEnum.xml.getExpr());
		return getBuilderClassInstance();
	}
	
	/**
	 * 拼接 “xmlFilePre” , 支持连缀
	 * @return
	 */
	public T addXmlFilePre(){
		getBuilderBuffer().append(ExpressionEnum.xmlFilePre.getExpr());
		return getBuilderClassInstance();
	}
	/**
	 * 拼接 “id” , 支持连缀
	 * @return
	 */
	public T addId(){
		getBuilderBuffer().append(GenConfEnum.id);
		return getBuilderClassInstance();
	}
	
	/**
	 * 拼接  传入的值 , 支持连缀
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
