package cn.javass.xgen.util.readxml;

public class ParseModel {
	/**
	 * Ԫ�ص�����
	 */
	private String eleName;
	/**
	 * �Ƿ�����ֵ����������ֵ����Ԫ�ص�ֵ
	 */
	private boolean propertyValue;
	/**
	 * �Ƿ��ս��
	 */
	private boolean end;
	/**
	 * �Ƿ񵥸�ֵ
	 */
	private boolean singleValue;
	/**
	 * �������ʽ
	 */
	private String condition;
	public String getEleName() {
		return eleName;
	}
	public void setEleName(String eleName) {
		this.eleName = eleName;
	}
	public boolean isPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(boolean propertyValue) {
		this.propertyValue = propertyValue;
	}
	public boolean isEnd() {
		return end;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	public boolean isSingleValue() {
		return singleValue;
	}
	public void setSingleValue(boolean singleValue) {
		this.singleValue = singleValue;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	@Override
	public String toString() {
		return "ParseModel [eleName=" + eleName + ", propertyValue="
				+ propertyValue + ", end=" + end + ", singleValue="
				+ singleValue + ", condition=" + condition + "]";
	}
	
	
	
}
