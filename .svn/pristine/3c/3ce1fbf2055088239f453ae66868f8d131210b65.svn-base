package com.springmvc.relationdrug.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.springmvc.relationdrug.enums.GradeEnum;
import com.springmvc.relationdrug.enums.RuleTypeEnum;

/**
 * 
 * Summary :
 * 
 * 
 */
@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name = "BASIC_DRUG_CHECK")
public class VerifyRecordDetail {
	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long verifyRecordDetailId;
	@ManyToOne(targetEntity = CompatTaboo.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "main_id_")
	private VerifyRecord verifyRecord;
	@Column(name = "grade_")
	@Enumerated(EnumType.ORDINAL)
	private GradeEnum grade;
	@Column(name = "remark_")
	private String remark;
	@Column(name = "rule_type_")
	@Enumerated(EnumType.ORDINAL)
	private RuleTypeEnum ruleType;

	public Long getVerifyRecordDetailId() {
		return verifyRecordDetailId;
	}

	public void setVerifyRecordDetailId(Long verifyRecordDetailId) {
		this.verifyRecordDetailId = verifyRecordDetailId;
	}

	public VerifyRecord getVerifyRecord() {
		return verifyRecord;
	}

	public void setVerifyRecord(VerifyRecord verifyRecord) {
		this.verifyRecord = verifyRecord;
	}

	public GradeEnum getGrade() {
		return grade;
	}

	public void setGrade(GradeEnum grade) {
		this.grade = grade;
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

	public void setRuleType(RuleTypeEnum ruleType) {
		this.ruleType = ruleType;
	}
}
