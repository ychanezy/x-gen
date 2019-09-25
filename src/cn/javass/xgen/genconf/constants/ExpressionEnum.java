package cn.javass.xgen.genconf.constants;

public enum ExpressionEnum {
	dot("."),separator("/"),dollar("$"),openBracket("["),
	closeBracket("]"),equal("="),comma(","),xml("xml"),xmlFilePre("cn/javass/xgenconfxml/")
	,themeXmlName("ThemeConf.xml"),location("Location"),fileName("fileName"),
	propBeginStr("${"),propEndStr("}"),methodBeginStr("$["),methodEndStr("]"),
	template("template")
	;
	
	private String expr;
	private ExpressionEnum(String expr){
		this.expr = expr;
	}
	
	public String getExpr(){
		return this.expr;
	}
}
