package com.springmvc.relationdrug.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;

import com.springmvc.base.util.JsonValue;

/**
 * 
 * Summary : 处方基本信息
 * 
 * 
 */
@Entity
public class PrescribeBasicInfo {

	/**
	 * 处方编号
	 */
	@JsonValue
	private String prescribeNo;

	/**
	 * 疾病编码
	 */
	@JsonValue
	private String diseaseCode;

	/**
	 * 疾病名称
	 */
	@JsonValue
	private String diseaseName;

	/**
	 * 医生工号
	 */
	@JsonValue
	private String medicalerNo;

	/**
	 * 医生姓名
	 */
	@JsonValue
	private String medicalerName;

	/**
	 * 医生职称
	 */
	@JsonValue
	private TitleEnum medicalerTitle;

	/**
	 * 科室编号
	 */
	@JsonValue
	private String divisionCode;

	/**
	 * 科室名称
	 */
	@JsonValue
	private String divisionName;

	/**
	 * 创建时间
	 */
	@JsonValue
	private Date createTime;

	/**
	 * 处方总金额
	 */
	@JsonValue
	private BigDecimal totalAmount;

	/**
	 * 
	 * Summary : 医生级别
	 * 
	 * 
	 * @author Lineshow Email:lineshow7@gmail.com
	 * 
	 * @since 2013-7-25
	 */
	public enum TitleEnum {
		Pharmacist("药师"), MainPharmacist("主管药师"), viceHeadPharmacist("副主任药师"), HeadPharmacist(
				"主任药师"), StayMedicaler("住院医师"), MainMedicaler("主治医师"), ViceHeadMedicaler(
				"副主任医师"), HeadMedicaler("主任医师");

		private String text;

		TitleEnum(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}
	}

	public String getPrescribeNo() {
		return prescribeNo;
	}

	/**
	 * 处方编号
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-25
	 * @param prescribeNo
	 */
	public void setPrescribeNo(String prescribeNo) {
		this.prescribeNo = prescribeNo;
	}

	public String getDiseaseCode() {
		return diseaseCode;
	}

	/**
	 * 疾病编码
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-25
	 * @param diseaseCode
	 */
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	/**
	 * 疾病名称
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-25
	 * @param diseaseName
	 */
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getMedicalerNo() {
		return medicalerNo;
	}

	/**
	 * 医生工号
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-25
	 * @param medicalerNo
	 */
	public void setMedicalerNo(String medicalerNo) {
		this.medicalerNo = medicalerNo;
	}

	public String getMedicalerName() {
		return medicalerName;
	}

	/**
	 * 医生姓名
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-25
	 * @param medicalerName
	 */
	public void setMedicalerName(String medicalerName) {
		this.medicalerName = medicalerName;
	}

	public TitleEnum getMedicalerTitle() {
		return medicalerTitle;
	}

	/**
	 * 医生职称
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-25
	 * @param medicalerTitle
	 */
	public void setMedicalerTitle(TitleEnum medicalerTitle) {
		this.medicalerTitle = medicalerTitle;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	/**
	 * 科室编码
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-25
	 * @param divisionCode
	 */
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getDivisionName() {
		return divisionName;
	}

	/**
	 * 科室名称
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-25
	 * @param divisionName
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建名称
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-25
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 处方总金额
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-7-25
	 * @param totalAmount
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}
