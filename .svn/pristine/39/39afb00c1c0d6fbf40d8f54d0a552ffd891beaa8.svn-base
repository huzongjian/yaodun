package com.springmvc.relationdrug.enums;

import com.springmvc.base.util.EnumRestrictor;

/**
 * 
 * Summary : 配伍禁忌程度
 * 
 * 
 */
public enum ConflictTypeEnum implements EnumRestrictor {
	UN_SUITED("不宜配伍", 1), TABOO("忌/慎配伍", 2), ISOLATED("单独使用", 3), CARE_2_USE(
			"慎用", 4), BAN_2_USE("禁用", 6), DILUTE_2_USE("稀释后可配伍", 7);

	private String text;
	private Integer value;

	ConflictTypeEnum(String text, Integer value) {
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
