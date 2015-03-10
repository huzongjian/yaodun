package com.springmvc.relationdrug.enums;

import com.springmvc.base.util.EnumRestrictor;

/**
 * 
 * Summary : 药物作用类型
 * 
 * 
 */
public enum STDegreeEnum implements EnumRestrictor {

	RECOMMEND("建议", 0), MUST("必须", 1);
	private String text;
	private Integer value;

	STDegreeEnum(String text, Integer value) {
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.text;
	}
}
