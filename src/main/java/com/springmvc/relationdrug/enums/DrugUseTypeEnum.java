package com.springmvc.relationdrug.enums;

/**
 * 药物使用类型
 */
public enum DrugUseTypeEnum {
	/**
	 * 禁用
	 */
	TABOO_J("j"),
	/**
	 * 提示
	 */
	TABOO_T("t"),
	/**
	 * 慎用
	 */
	TABOO_S("s"),
	/**
	 * 无禁忌
	 */
	TABOO_W("w"),
	/**
	 * 大于
	 */
	SIGN_GREATER(">"),
	/**
	 * 大于
	 */
	SIGN_GREATER2("＞");

	private String text;

	DrugUseTypeEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
