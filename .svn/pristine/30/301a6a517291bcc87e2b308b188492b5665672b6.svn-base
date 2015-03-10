package com.springmvc.relationdrug.enums;

import com.springmvc.base.util.EnumRestrictor;

/**
 * 
 * Summary : 配伍禁忌程度
 * 
 * 
 */
public enum ValueEnum implements EnumRestrictor {

	/*
	 * multiDrugsMathupChecker("配伍禁忌检测 "), dayNum4UseChecker("用药天数检测"),
	 * dosageChecker( "用量检测"), frequencyChecker("频次检测"), usageChecker("用法检测"),
	 * repeatDrugChecker( "重复用药检测"), skinTestOrNotChecker("皮试检测"),
	 * specialGroupsChecker( "特殊人群检测"), drugForbidDiseaseChecker("疾病禁用药品检测")
	 * ,drugSuitDiseaseChecker("疾病适用药品检测");
	 */

	drug("", 1), st_test("使用前须做皮试", 2), ISOLATED("单独使用", 3), CARE_2_USE("慎用", 4), BAN_2_USE(
			"禁用", 6), DILUTE_2_USE("稀释后可配伍", 7);

	private String text;
	private Integer value;

	ValueEnum(String text, Integer value) {
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
