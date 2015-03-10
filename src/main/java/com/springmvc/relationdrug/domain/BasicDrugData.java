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

import com.springmvc.relationdrug.pojo.SignDomain;

/**
 * 
 * @ClassName: BasicDrugCheck
 * @Description: 合理用药检测基本表
 * @date 2013-7-15 下午4:11:10
 * 
 */

@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name = "BASIC_DRUG_Data")
public class BasicDrugData extends SignDomain {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long id;
	@Column(name = "drug_code_")
	private String drugCode;// 药品编码	
	@Column(name = "drug_name_")
	private String drugName;// 药品名称
	@Column(name = "taboo_gestate_")
	private String tabooGestate;// 妊娠期禁忌
	@Column(name = "taboo_suckling_")
	private String tabooSuckling;// 哺乳期禁忌
	@Column(name = "taboo_liver_")
	private String tabooLiver;// 肝功能 禁忌
	@Column(name = "taboo_cliver_")
	private String tabooCliver;// 重度肝功能
	@Column(name = "taboo_kidney_")
	private String tabooKidney;// 肾功能禁忌
	@Column(name = "taboo_ckidney_")
	private String tabooCkidney;// 重度肾功能禁忌
	@Column(name = "taboo_child_")
	private String tabooChild;// 儿童禁忌
	@Column(name = "taboo_old_")
	private String tabooOld;// 老人禁忌
	@Column(name = "taboo_disease_")
	private String tabooDisease;// 基础禁忌	
	@ManyToOne(targetEntity = SkinTest.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "skinTest_")
	private SkinTest skinTest;// 皮试
	@Column(name = "symbol_")
	private String symbol;// 助记符
	@Column(name = "drug_type_")
	private String drugType;// 药物作用类型//DrugTypeEnum
	
	@Column(name="FDA_grade_")
	private String fDA;
	@Column(name="taboo_gestate_reason_")
	private String tabooGestateReason;
	@Column(name="taboo_suckling_reason_")
	private String tabooSucklingReason;
	@Column(name="taboo_child_reason_")
	private String tabooChildReason;
	@Column(name="taboo_old_reason_")
	private String tabooOldReason;
	@Column(name="taboo_liver_reason_")
	private String tabooLiverReason;
	@Column(name="taboo_kindney_reason_")
	private String tabookidneyReason;
	@Column(name="incompatibility_code_")
	private String incompatibilityCode;//配伍禁忌编号
	@Column(name="Antibiotic_type_")
	private String AntibioticType;//抗生素分级
	
	
	@Column(name="contraindication_")
	private String contraindication;//禁忌症
	  
	public BasicDrugData(Long basicDrugDataId, String drugCode,
			String drugName, String tabooGestate, String tabooSuckling,
			String tabooLiver, String tabooCliver, String tabooKidney,
			String tabooCkidney, String tabooChild, String tabooOld,
			String basicTaboo, SkinTest skinTest, String symbol,
			String drugType, String fDA, String tabooGestateReason,
			String tabooSucklingReason, String tabooChildReason,
			String tabooOldReason, String tabooLiverReason,
			String tabooCkidneyReason, String incompatibilityCode,
			String antibioticType, String contraindication) {
		super();
		this.id = basicDrugDataId;
		this.drugCode = drugCode;
		this.drugName = drugName;
		this.tabooGestate = tabooGestate;
		this.tabooSuckling = tabooSuckling;
		this.tabooLiver = tabooLiver;
		this.tabooCliver = tabooCliver;
		this.tabooKidney = tabooKidney;
		this.tabooCkidney = tabooCkidney;
		this.tabooChild = tabooChild;
		this.tabooOld = tabooOld;
		this.tabooDisease = basicTaboo;
		this.skinTest = skinTest;
		this.symbol = symbol;
		this.drugType = drugType;
		this.fDA = fDA;
		this.tabooGestateReason = tabooGestateReason;
		this.tabooSucklingReason = tabooSucklingReason;
		this.tabooChildReason = tabooChildReason;
		this.tabooOldReason = tabooOldReason;
		this.tabooLiverReason = tabooLiverReason;
		this.tabookidneyReason = tabooCkidneyReason;
		this.incompatibilityCode = incompatibilityCode;
		AntibioticType = antibioticType;
		this.contraindication = contraindication;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long basicDrugDataId) {
		this.id = basicDrugDataId;
	}

	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getTabooGestate() {
		return tabooGestate;
	}

	public void setTabooGestate(String tabooGestate) {
		this.tabooGestate = tabooGestate;
	}

	public String getTabooSuckling() {
		return tabooSuckling;
	}

	public void setTabooSuckling(String tabooSuckling) {
		this.tabooSuckling = tabooSuckling;
	}

	public String getTabooLiver() {
		return tabooLiver;
	}

	public void setTabooLiver(String tabooLiver) {
		this.tabooLiver = tabooLiver;
	}

	public String getTabooCliver() {
		return tabooCliver;
	}

	public void setTabooCliver(String tabooCliver) {
		this.tabooCliver = tabooCliver;
	}

	public String getTabooKidney() {
		return tabooKidney;
	}

	public void setTabooKidney(String tabooKidney) {
		this.tabooKidney = tabooKidney;
	}

	public String getTabooCkidney() {
		return tabooCkidney;
	}

	public void setTabooCkidney(String tabooCkidney) {
		this.tabooCkidney = tabooCkidney;
	}

	public String getTabooChild() {
		return tabooChild;
	}

	public void setTabooChild(String tabooChild) {
		this.tabooChild = tabooChild;
	}

	public String getTabooOld() {
		return tabooOld;
	}

	public void setTabooOld(String tabooOld) {
		this.tabooOld = tabooOld;
	}

	public String getTabooDisease() {
		return tabooDisease;
	}

	public void setabooDisease(String basicTaboo) {
		this.tabooDisease = basicTaboo;
	}

	public SkinTest getSkinTest() {
		return skinTest;
	}

	public void setSkinTest(SkinTest skinTest) {
		this.skinTest = skinTest;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public String getfDA() {
		return fDA;
	}

	public void setfDA(String fDA) {
		this.fDA = fDA;
	}

	public String getTabooGestateReason() {
		return tabooGestateReason;
	}

	public void setTabooGestateReason(String tabooGestateReason) {
		this.tabooGestateReason = tabooGestateReason;
	}

	public String getTabooSucklingReason() {
		return tabooSucklingReason;
	}

	public void setTabooSucklingReason(String tabooSucklingReason) {
		this.tabooSucklingReason = tabooSucklingReason;
	}

	public String getTabooChildReason() {
		return tabooChildReason;
	}

	public void setTabooChildReason(String tabooChildReason) {
		this.tabooChildReason = tabooChildReason;
	}

	public String getTabooOldReason() {
		return tabooOldReason;
	}

	public void setTabooOldReason(String tabooOldReason) {
		this.tabooOldReason = tabooOldReason;
	}

	public String getTabooLiverReason() {
		return tabooLiverReason;
	}

	public void setTabooLiverReason(String tabooLiverReason) {
		this.tabooLiverReason = tabooLiverReason;
	}

	public String getTabookidneyReason() {
		return tabookidneyReason;
	}

	public void setTabookidneyReason(String tabooCkidneyReason) {
		this.tabookidneyReason = tabooCkidneyReason;
	}

	public String getIncompatibilityCode() {
		return incompatibilityCode;
	}

	public void setIncompatibilityCode(String incompatibilityCode) {
		this.incompatibilityCode = incompatibilityCode;
	}

	public String getAntibioticType() {
		return AntibioticType;
	}

	public void setAntibioticType(String antibioticType) {
		AntibioticType = antibioticType;
	}

	public String getContraindication() {
		return contraindication;
	}

	public void setContraindication(String contraindication) {
		this.contraindication = contraindication;
	}

	public BasicDrugData() {
		super();
	}

	

	public BasicDrugData(Long basicDrugDataId) {
		super();
		this.id = basicDrugDataId;
	}

	public Object clone() {
		BasicDrugData basicDrugCheck = null;
		try {
			basicDrugCheck = (BasicDrugData) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return basicDrugCheck;
	}

}
