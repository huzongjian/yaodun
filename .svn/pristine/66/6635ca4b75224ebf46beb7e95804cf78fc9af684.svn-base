package com.springmvc.relationdrug.pojo;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.persistence.Entity;

import com.springmvc.base.util.JsonValue;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.domain.BasicDrugData;
import com.springmvc.relationdrug.domain.Frequency;
import com.springmvc.relationdrug.domain.Usage;
import com.springmvc.relationdrug.enums.AdviceTypeEnum;

/**
 * 
 * Summary : 接口药物信息载体
 * 
 * 
 */
@Entity
public class PrescribeCell {

	@Resource
	@JsonValue
	private BasicDrugCheck basicDrugCheck;
	@Resource
	private BasicDrugData basicDrugData;
	@JsonValue
	private Usage usage;
	@JsonValue
	private String drugName;
	@JsonValue
	private double dosage;
	@JsonValue
	private Frequency frequency;
	@JsonValue
	private String unit;
	@JsonValue
	private Integer daynum;
	@JsonValue
	private BigDecimal fee;
	@JsonValue
	private Boolean skinTested;
	@JsonValue
	private AdviceTypeEnum adviceType;
	@JsonValue
	private Integer groupNo;
	@JsonValue
	private String drugType;


	public Usage getUsage() {
		return usage;
	}

	public BasicDrugData getBasicDrugData() {
		return basicDrugData;
	}

	public void setBasicDrugData(BasicDrugData basicDrugData) {
		this.basicDrugData = basicDrugData;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
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

	public double getDosage() {
		return dosage;
	}

	/**
	 * 用量
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param dosage
	 */
	public void setDosage(double dosage) {
		this.dosage = dosage;
	}

	public Frequency getFrequency() {
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
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
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

	public Integer getDaynum() {
		return daynum;
	}

	/**
	 * 天数
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param daynum
	 */
	public void setDaynum(Integer daynum) {
		this.daynum = daynum;
	}

	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * 药品费用
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param fee
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Boolean getSkinTested() {
		return skinTested;
	}

	/**
	 * 是否皮试
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param skinTested
	 */
	public void setSkinTested(Boolean skinTested) {
		this.skinTested = skinTested;
	}

	public AdviceTypeEnum getAdviceType() {
		return adviceType;
	}

	/**
	 * 长嘱/临嘱
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param adviceType
	 */
	public void setAdviceType(AdviceTypeEnum adviceType) {
		this.adviceType = adviceType;
	}

	public Integer getGroupNo() {
		return groupNo;
	}

	/**
	 * 组号
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-27
	 * @param groupNo
	 */
	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}

	public BasicDrugCheck getBasicDrugCheck() {
		return basicDrugCheck;
	}

	/**
	 * 药物基类
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-8-8
	 * @param basicDrugCheck
	 */
	public void setBasicDrugCheck(BasicDrugCheck basicDrugCheck) {
		this.basicDrugCheck = basicDrugCheck;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

}
