package com.springmvc.relationdrug.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 疾病禁用药品表
 */
@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name = "basic_drug_forbid_disease")
public class DrugDisease {
	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long drugDiseaseId;
	@Column(name = "first_level_")
	private String firstLevel; // 药品分类，一大类
	@Column(name = "second_level_")
	private String secondLevel; // 药品分类，二大类
	@Column(name = "third_level_")
	private String thirdLevel; // 药品分类，三大类
	@Column(name = "fourth_level_")
	private String fourthLevel; // 药品分类，四大类
	@Column(name = "fifth_level_")
	private String fifthLevel;// 药品分类，五大类
	@Column(name = "six_level_")
	private String sixLevel; // 药品分类，六大类
	@Column(name = "bmddrug_id_")
	private String drugId; // 药口编号
	@Column(name = "drug_name_")
	private String drugName; // 药品名称
	@Column(name = "suit_icd10_")
	private String suitIcd10; // 药品可用于疾病icd10编号
	@Column(name = "suit_diseaseName_")
	private String suitDiseaseName; // 药品可用于疾病名称
	@Column(name = "forbid_icd10_")
	private String forbidIcd10; // 禁止使用此药品icd10
	@Column(name = "forbid_diseaseName_")
	private String forbidDiseaseName; // 禁止使用此药品疾病名称
	@Column(name = "suit_level_")
	private long suitLevel; // 药品可用于疾病编码等级
	@Column(name = "forbid_level_")
	private long forbidLevel; // 药品禁用于疾病编码等级

	public Long getDrugDiseaseId() {
		return drugDiseaseId;
	}

	public void setDrugDiseaseId(Long drugDiseaseId) {
		this.drugDiseaseId = drugDiseaseId;
	}

	public String getFirstLevel() {
		return firstLevel;
	}

	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}

	public String getSecondLevel() {
		return secondLevel;
	}

	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}

	public String getThirdLevel() {
		return thirdLevel;
	}

	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}

	public String getFourthLevel() {
		return fourthLevel;
	}

	public void setFourthLevel(String fourthLevel) {
		this.fourthLevel = fourthLevel;
	}

	public String getFifthLevel() {
		return fifthLevel;
	}

	public void setFifthLevel(String fifthLevel) {
		this.fifthLevel = fifthLevel;
	}

	public String getSixLevel() {
		return sixLevel;
	}

	public void setSixLevel(String sixLevel) {
		this.sixLevel = sixLevel;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getSuitIcd10() {
		return suitIcd10;
	}

	public void setSuitIcd10(String suitIcd10) {
		this.suitIcd10 = suitIcd10;
	}

	public String getSuitDiseaseName() {
		return suitDiseaseName;
	}

	public void setSuitDiseaseName(String suitDiseaseName) {
		this.suitDiseaseName = suitDiseaseName;
	}

	public String getForbidIcd10() {
		return forbidIcd10;
	}

	public void setForbidIcd10(String forbidIcd10) {
		this.forbidIcd10 = forbidIcd10;
	}

	public String getForbidDiseaseName() {
		return forbidDiseaseName;
	}

	public void setForbidDiseaseName(String forbidDiseaseName) {
		this.forbidDiseaseName = forbidDiseaseName;
	}

	public long getSuitLevel() {
		return suitLevel;
	}

	public void setSuitLevel(long suitLevel) {
		this.suitLevel = suitLevel;
	}

	public long getForbidLevel() {
		return forbidLevel;
	}

	public void setForbidLevel(long forbidLevel) {
		this.forbidLevel = forbidLevel;
	}

}
