package com.springmvc.relationdrug.pojo;

import javax.persistence.Entity;

import com.springmvc.base.util.JsonValue;
import com.springmvc.relationdrug.enums.GradeEnum;
import com.springmvc.relationdrug.enums.RuleTypeEnum;

/**
 * 
 * Summary : 处方校验报告单明细
 * 
 * 
 */
@Entity
public class ReportDetail {

	/**
	 * 处方良度
	 */
	@JsonValue
	private GradeEnum grade =GradeEnum.NORMAL;

	/**
	 * 备注
	 */
	@JsonValue
	private String remark;
	@JsonValue
	private String fDA;
	@JsonValue
	private RuleTypeEnum ruleType;
	public String getFDA() {
		return fDA;
	}

	public void setfDA(String fDA) {
		this.fDA = fDA;
	}

	public GradeEnum getGrade() {
		return grade;
	}

	public void setGrade(GradeEnum grade) {
		this.grade = grade;
	}

	public void setRuleType(RuleTypeEnum ruleType) {
		this.ruleType = ruleType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public RuleTypeEnum getRuleType() {
		return ruleType;
	}

	public ReportDetail(RuleTypeEnum ruleType) {
		this.ruleType = ruleType;
	}

}
