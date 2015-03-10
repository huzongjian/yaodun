package com.springmvc.relationdrug.enums;

import com.springmvc.base.util.EnumRestrictor;

/**
 * 
 * Summary : 药物作用类型
 * 
 * 
 */
public enum DrugTypeEnum implements EnumRestrictor {

	MENTAL("精神", 1), NARCOSE("麻醉", 2), POISON("毒性", 3);
	private String text;
	private Integer value;

	DrugTypeEnum(String text, Integer value) {
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
