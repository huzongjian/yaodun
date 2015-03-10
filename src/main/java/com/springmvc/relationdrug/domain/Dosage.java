package com.springmvc.relationdrug.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * Summary : 用量
 * 
 * 
 */
@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name = "basic_DRUG_DOSAGE")
public class Dosage {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long dosageId;
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ManyToOne(targetEntity = BasicDrugCheck.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "BASIC_ID_")
	private BasicDrugCheck basicDrugCheck;
	@ManyToOne(targetEntity = Usage.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "USAGE_")
	private Usage usage;

	// 剂型
	@Column(name = "DRUG_FORM_")
	private String form;
	// 单位
	@Column(name = "UNIT_")
	private String unit;
	// 最小用量
	@Column(name = "MIN_DOSAGE_")
	private Double minDosage;;
	// 最大用量
	@Column(name = "MAX_DOSAGE_")
	private Double maxDosage;
	// 单次最大用量
	@Column(name = "ONCE_MAX_DOSAGE_")
	private Double maxDosagePerOnce;

	/**
	 * 用法
	 */

	/**
	 * 频次
	 */
	@Column(name = "FREQUENCY_")
	private String frequency;

	/**
	 * 药物编码
	 */
	@Column(name = "DRUG_CODE_")
	private String drugCode;

	@Column(name = "FLAG_")
	private String flag;

	public BasicDrugCheck getBasicDrugCheck() {
		return basicDrugCheck;
	}

	public void setBasicDrugCheck(BasicDrugCheck basicDrugCheck) {
		this.basicDrugCheck = basicDrugCheck;
	}

	public String getForm() {
		return form;
	}

	/**
	 * 剂型
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param form
	 */
	public void setForm(String form) {
		this.form = form;
	}

	public Long getDosageId() {
		return dosageId;
	}

	public void setDosageId(Long dosageId) {
		this.dosageId = dosageId;
	}

	public String getUnit() {
		return unit;
	}

	/**
	 * 单位
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getMinDosage() {
		return minDosage;
	}

	/**
	 * 最小用量
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param minDosage
	 */
	public void setMinDosage(Double minDosage) {
		this.minDosage = minDosage;
	}

	public Double getMaxDosage() {
		return maxDosage;
	}

	/**
	 * 最大用量
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param maxDosage
	 */
	public void setMaxDosage(Double maxDosage) {
		this.maxDosage = maxDosage;
	}

	public Double getMaxDosagePerOnce() {
		return maxDosagePerOnce;
	}

	/**
	 * 单词最大用量
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param maxDosagePerOnce
	 */
	public void setMaxDosagePerOnce(Double maxDosagePerOnce) {
		this.maxDosagePerOnce = maxDosagePerOnce;
	}

	public Usage getUsage() {
		return usage;
	}

	/**
	 * 用法
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param usage
	 */
	public void setUsage(Usage usage) {
		this.usage = usage;
	}

	public String getFrequency() {
		return frequency;
	}

	/**
	 * 频次
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param frequency
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFlag() {
		return flag;
	}

	/**
	 * 标记 1：乘以体重 2：乘以体表面积 （3位 为1、2 的任意组合）
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return 药物编码
	 */
	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

}
