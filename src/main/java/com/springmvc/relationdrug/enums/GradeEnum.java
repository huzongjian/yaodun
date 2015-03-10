package com.springmvc.relationdrug.enums;

import com.springmvc.base.util.EnumRestrictor;

/**
 * 
 * Summary : 配伍禁忌程度
 * 
 * @since 2013-8-1
 */
public enum GradeEnum implements EnumRestrictor {
	BAN("禁用", 0), CAUTION("慎用", 1), WARN("提示", 2), NORMAL("正常", 3),
	ADVICE("药师建议", 4);

	private String text;
	private Integer value;

	GradeEnum(String text, Integer value) {
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
