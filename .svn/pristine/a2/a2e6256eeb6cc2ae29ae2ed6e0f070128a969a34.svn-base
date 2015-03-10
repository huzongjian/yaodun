package com.springmvc.relationdrug.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 药品药品适用于疾病表
 */
@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name = "basic_drug_suit_disease")
public class DrugSuitDisease {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long drugSuitDiseaseId;
	@Column(name = "disease_name_")
	private String diseaseName; // 疾病名称
	@Column(name = "disease_icd10_")
	private String diseaseIcd10; // 疾病的ICD-10码
	@Column(name = "cpath_name_")
	private String cpathName; // 临床路径药物类别名称
	@Column(name = "matchup_name_")
	private String matchupName;
	@Column(name = "bmddrug_id_")
	private String bmddrugId; // 药品类别编码
	@Column(name = "drug_name_")
	private String drugName; // 药品类别名称
	@Column(name = "quantity_")
	private long quantity;
	@Column(name = "fee_")
	private long fee;
	@Column(name = "necessary_flag_")
	private long necessaryFlag;
	@Column(name = "use_days_")
	private long useDays;
	@Column(name = "matchup_num_")
	private long matchupNum;

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public Long getDrugSuitDiseaseId() {
		return drugSuitDiseaseId;
	}

	public void setDrugSuitDiseaseId(Long drugSuitDiseaseId) {
		this.drugSuitDiseaseId = drugSuitDiseaseId;
	}

	public String getMatchupName() {
		return matchupName;
	}

	public void setMatchupName(String matchupName) {
		this.matchupName = matchupName;
	}

	public String getCpathName() {
		return cpathName;
	}

	public void setCpathName(String cpathName) {
		this.cpathName = cpathName;
	}

	public String getBmddrugId() {
		return bmddrugId;
	}

	public void setBmddrugId(String bmddrugId) {
		this.bmddrugId = bmddrugId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

	public long getNecessaryFlag() {
		return necessaryFlag;
	}

	public void setNecessaryFlag(long necessaryFlag) {
		this.necessaryFlag = necessaryFlag;
	}

	public long getUseDays() {
		return useDays;
	}

	public void setUseDays(long useDays) {
		this.useDays = useDays;
	}

	public long getMatchupNum() {
		return matchupNum;
	}

	public void setMatchupNum(long matchupNum) {
		this.matchupNum = matchupNum;
	}

	public String getDiseaseIcd10() {
		return diseaseIcd10;
	}

	public void setDiseaseIcd10(String diseaseIcd10) {
		this.diseaseIcd10 = diseaseIcd10;
	}

}
