package com.springmvc.relationdrug.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 重复用药实体
 */
@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name = "basic_REPEAT_DRUG_LIMIT")
public class RepeatDrugLimit {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long repeatDrugLimitId;
	@Column(name = "check_limit_")
	private String checkLimit;// 限制

	public String getCheckLimit() {
		return checkLimit;
	}

	/**
	 * 限制
	 * 
	 * @since 2013-8-6
	 */
	public void setCheckLimit(String checkLimit) {
		this.checkLimit = checkLimit;
	}

	public Long getRepeatDrugLimitId() {
		return repeatDrugLimitId;
	}

	public void setRepeatDrugLimitId(Long repeatDrugLimitId) {
		this.repeatDrugLimitId = repeatDrugLimitId;
	}

}
