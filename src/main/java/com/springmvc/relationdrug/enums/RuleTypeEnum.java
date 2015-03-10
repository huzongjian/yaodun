package com.springmvc.relationdrug.enums;

import com.springmvc.base.util.EnumRestrictor;

/**
 * 
 * Summary : 检测规则类型
 * <p>
 * <ul>
 * <li>0.multiDrugsMathupChecker("配伍禁忌检测 ")</li>
 * <li>1.dayNum4UseChecker("用药天数检测")</li>
 * <li>2.dosageChecker("用量检测")</li>
 * <li>3.frequencyChecker("频次检测")</li>
 * <li>4.usageChecker("用法检测")</li>
 * <li>5.repeatDrugChecker("重复用药检测")</li>
 * <li>6.skinTestOrNotChecker("皮试检测")</li>
 * <li>7.specialGroupsChecker("特殊人群检测")</li>
 * <li>8.drugForbidDiseaseChecker("疾病禁用药品检测")</li>
 * <li>9.drugSuitDiseaseChecker("疾病适用药品检测")</li>
 * 
 * </ul>
 * </p>
 * 
 * 
 * @since 2013-7-27
 */
public enum RuleTypeEnum implements EnumRestrictor{

	multiDrugsMathupChecker("配伍禁忌检测",0), dayNum4UseChecker("用药天数检测",1), dosageChecker(
			"用量检测",2), frequencyChecker("频次检测",3), usageChecker("用法检测",4), repeatDrugChecker(
			"重复用药检测",5), skinTestOrNotChecker("皮试检测",6), specialGroupsChecker(
			"特殊人群检测",7), drugForbidDiseaseChecker("疾病禁用药品检测",8), drugSuitDiseaseChecker(
			"疾病适用药品检测",9),doctorAdvice("药师建议",100);

	private String text;
	private Integer value;
	

	private RuleTypeEnum(String text, Integer value) {
		this.text = text;
		this.value = value;
	}

	@Override
	public String toString() {
		return this.text;
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
}
