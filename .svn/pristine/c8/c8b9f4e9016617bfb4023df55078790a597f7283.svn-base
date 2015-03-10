package com.springmvc.relationdrug.enums;

import com.springmvc.base.util.EnumRestrictor;

/**
 * 
 * Summary : 药物种类
 * 
 * 
 */
public enum DrugCateGoryEnum implements EnumRestrictor {

	default_null("默认", 0), west_drug("西药", 1), medicine_drug("中成药", 2), nation_drug(
			"民族药", 3), chinese_drug("中药饮片", 4), hospital_drug("院内制剂", 5);
	private String text;
	private Integer value;

	DrugCateGoryEnum(String text, Integer value) {
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
