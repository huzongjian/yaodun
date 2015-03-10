package com.springmvc.relationdrug.enums;

/**
 * 
 * Summary : 嘱咐类型
 * 
 */
public enum AdviceTypeEnum {
	LONG_ADVICE("长嘱"), TEMP_ADVICE("临嘱");

	private String text;

	AdviceTypeEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
