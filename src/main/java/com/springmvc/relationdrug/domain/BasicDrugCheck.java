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
@Table(name = "BASIC_DRUG_CHECK")
public class BasicDrugCheck extends SignDomain {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long basicDrugCheckId;
	@Column(name = "classify_one_")
	private String classifyOne;// 一级分类
	@Column(name = "classify_two_")
	private String classifyTwo;// 二级分类
	@Column(name = "classify_three_")
	private String classifyThree;// 三级分类
	@Column(name = "classify_four_")
	private String classifyFour;// 四级分类
	@Column(name = "classify_five_")
	private String classifyFive;// 五级分类
	@Column(name = "drug_code_")
	private String drugCode;// 药品编码
	@Column(name = "ab_class_")
	private String abClass;// 甲乙类
	@Column(name = "drug_zh_name_")
	private String drugZhName;// 药品中文名称
	@Column(name = "drug_form_")
	private String drugForm;// 剂型
	@Column(name = "drug_name_")
	private String drugName;// 药品名称
	@Column(name = "rule_unit_")
	private String ruleUnit;// 规格单位
	@Column(name = "taboo_4_gestate_")
	private String taboo4Gestate;// 妊娠期禁忌
	@Column(name = "taboo_4_suckling_")
	private String taboo4Suckling;// 哺乳期禁忌
	@Column(name = "taboo_4_liver_")
	private String taboo4Liver;// 肝功能 禁忌
	@Column(name = "taboo_4_cliver_")
	private String taboo4Cliver;// 重度肝功能
	@Column(name = "taboo_4_kidney_")
	private String taboo4Kidney;// 肾功能禁忌
	@Column(name = "taboo_4_ckidney_")
	private String taboo4Ckidney;// 重度肾功能禁忌
	@Column(name = "taboo_4_child_")
	private String taboo4Child;// 儿童禁忌
	@Column(name = "taboo_4_old_")
	private String taboo4Old;// 老人禁忌
	@Column(name = "basic_taboo_")
	private String basicTaboo;// 基础禁忌
	// 医生 级别限制（保留）
	@Column(name = "excess_")
	private String excess;// 麻醉
	@ManyToOne(targetEntity = CompatTaboo.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "taboo_id_")
	private CompatTaboo compatTaboo; // 配伍禁忌

	@ManyToOne(targetEntity = SkinTest.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "st_id_")
	private SkinTest skinTest;// 皮试
	@Column(name = "is_state_bdrug_")
	private Boolean isStateBasicDrug; // 是否国家基本药物
	@Column(name = "symbol_")
	private String symbol;// 助记符
	@Column(name = "drug_Category_")
	private String drugCategory;// 药品类型// DrugCateGoryEnum
	@Column(name = "drug_type_")
	private String drugType;// 药物作用类型//DrugTypeEnum
	@Column(name = "specification_")
	private String specification;// 规格(合理用药规格)
	
	@Column(name="FDA_grading_")
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
	@Column(name="taboo_Ckidney_reason_")
	private String tabooCkidneyReason;
	  
	  
	  
	  
	
	
	
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

	public String getTabooCkidneyReason() {
		return tabooCkidneyReason;
	}

	public void setTabooCkidneyReason(String tabooCkidneyReason) {
		this.tabooCkidneyReason = tabooCkidneyReason;
	}

	public BasicDrugCheck(String drugCode, String drugForm, String drugName,
			String ruleUnit, String specification) {
		super();
		this.drugCode = drugCode;
		this.drugForm = drugForm;
		this.drugName = drugName;
		this.ruleUnit = ruleUnit;
		this.specification = specification;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getDrugCategory() {
		return drugCategory;
	}

	public void setDrugCategory(String drugCategory) {
		this.drugCategory = drugCategory;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	/**
	 * 药品种类
	 * 
	 * @param drugCategory
	 */

	public String getSymbol() {
		return symbol;
	}

	/**
	 * 助记符
	 * 
	 * @param symbol
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTaboo4Cliver() {
		return taboo4Cliver;
	}

	/**
	 * 重度肝功能障碍
	 * 
	 * @param taboo4Cliver
	 */
	public void setTaboo4Cliver(String taboo4Cliver) {
		this.taboo4Cliver = taboo4Cliver;
	}

	public String getClassifyOne() {
		return classifyOne;
	}

	/**
	 * 一级分类
	 * 
	 * @param classifyOne
	 */
	public void setClassifyOne(String classifyOne) {
		this.classifyOne = classifyOne;
	}

	public String getClassifyTwo() {
		return classifyTwo;
	}

	/**
	 * 二级分类
	 * 
	 * @param classifyTwo
	 */
	public void setClassifyTwo(String classifyTwo) {
		this.classifyTwo = classifyTwo;
	}

	public String getClassifyThree() {
		return classifyThree;
	}

	public Long getBasicDrugCheckId() {
		return basicDrugCheckId;
	}

	public void setBasicDrugCheckId(Long basicDrugCheckId) {
		this.basicDrugCheckId = basicDrugCheckId;
	}

	/**
	 * 三级分类
	 * 
	 * @param classifyThree
	 */
	public void setClassifyThree(String classifyThree) {
		this.classifyThree = classifyThree;
	}

	public String getClassifyFour() {
		return classifyFour;
	}

	/**
	 * 四级分类
	 * 
	 * @param classifyFour
	 */
	public void setClassifyFour(String classifyFour) {
		this.classifyFour = classifyFour;
	}

	public String getClassifyFive() {
		return classifyFive;
	}

	/**
	 * 五级分类
	 * 
	 * @param classifyFive
	 */
	public void setClassifyFive(String classifyFive) {
		this.classifyFive = classifyFive;
	}

	public String getDrugCode() {
		return drugCode;
	}

	/**
	 * 药品编码
	 * 
	 * @param drugCode
	 */
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getAbClass() {
		return abClass;
	}

	/**
	 * 甲乙类
	 * 
	 * @param abClass
	 */
	public void setAbClass(String abClass) {
		this.abClass = abClass;
	}

	public String getDrugZhName() {
		return drugZhName;
	}

	/**
	 * 中文名称
	 * 
	 * @param drugZhName
	 */
	public void setDrugZhName(String drugZhName) {
		this.drugZhName = drugZhName;
	}

	public String getDrugForm() {
		return drugForm;
	}

	/**
	 * 剂型
	 * 
	 * @param drugForm
	 */
	public void setDrugForm(String drugForm) {
		this.drugForm = drugForm;
	}

	public String getDrugName() {
		return drugName;
	}

	/**
	 * 药品名称
	 * 
	 * @param drugName
	 */
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getRuleUnit() {
		return ruleUnit;
	}

	/**
	 * 规格单位
	 * 
	 * @param ruleUnit
	 */
	public void setRuleUnit(String ruleUnit) {
		this.ruleUnit = ruleUnit;
	}

	public String getTaboo4Gestate() {
		return taboo4Gestate;
	}

	/**
	 * 妊娠期禁忌
	 * 
	 * @param taboo4Gestate
	 */
	public void setTaboo4Gestate(String taboo4Gestate) {
		this.taboo4Gestate = taboo4Gestate;
	}

	public String getTaboo4Suckling() {
		return taboo4Suckling;
	}

	/**
	 * 哺乳期禁忌
	 * 
	 * @param taboo4Suckling
	 */
	public void setTaboo4Suckling(String taboo4Suckling) {
		this.taboo4Suckling = taboo4Suckling;
	}

	public String getTaboo4Liver() {
		return taboo4Liver;
	}

	/**
	 * 肝功能禁忌
	 * 
	 * @param taboo4Liver
	 */
	public void setTaboo4Liver(String taboo4Liver) {
		this.taboo4Liver = taboo4Liver;
	}

	public String getTaboo4Kidney() {
		return taboo4Kidney;
	}

	/**
	 * 肾功能禁忌
	 * 
	 * @param taboo4Kidney
	 */
	public void setTaboo4Kidney(String taboo4Kidney) {
		this.taboo4Kidney = taboo4Kidney;
	}

	public String getTaboo4Ckidney() {
		return taboo4Ckidney;
	}

	/**
	 * 重度肾功能禁忌
	 * 
	 * @param taboo4Ckidney
	 */
	public void setTaboo4Ckidney(String taboo4Ckidney) {
		this.taboo4Ckidney = taboo4Ckidney;
	}

	public String getTaboo4Child() {
		return taboo4Child;
	}

	/**
	 * 儿童禁忌
	 * 
	 * @param taboo4Child
	 */
	public void setTaboo4Child(String taboo4Child) {
		this.taboo4Child = taboo4Child;
	}

	public String getTaboo4Old() {
		return taboo4Old;
	}

	/**
	 * 老人禁忌
	 * 
	 * @param taboo4Old
	 */
	public void setTaboo4Old(String taboo4Old) {
		this.taboo4Old = taboo4Old;
	}

	public String getBasicTaboo() {
		return basicTaboo;
	}

	/**
	 * 基础禁忌
	 * 
	 * @param basicTaboo
	 */
	public void setBasicTaboo(String basicTaboo) {
		this.basicTaboo = basicTaboo;
	}

	public String getExcess() {
		return excess;
	}

	/**
	 * 
	 * @param excess
	 */
	public void setExcess(String excess) {
		this.excess = excess;
	}

	public CompatTaboo getCompatTaboo() {
		return compatTaboo;
	}

	/**
	 * 配伍禁忌
	 * 
	 * @param compatTaboo
	 */
	public void setCompatTaboo(CompatTaboo compatTaboo) {
		this.compatTaboo = compatTaboo;
	}

	public SkinTest getSkinTest() {
		return skinTest;
	}

	/**
	 * 皮试与否
	 * 
	 * @param skinTest
	 */
	public void setSkinTest(SkinTest skinTest) {
		this.skinTest = skinTest;
	}

	/**
	 * 药物作用类型
	 * 
	 * @param drugType
	 */

	/**
	 * 是否国家基本药物
	 * 
	 * 
	 * @author Lineshow
	 * @since 2013-9-2
	 * @return
	 */
	public Boolean getIsStateBasicDrug() {
		return isStateBasicDrug;
	}

	public void setIsStateBasicDrug(Boolean isStateBasicDrug) {
		this.isStateBasicDrug = isStateBasicDrug;
	}

	public BasicDrugCheck() {
		super();
	}

	public BasicDrugCheck(Long id) {
		super();
	}

	public Object clone() {
		BasicDrugCheck basicDrugCheck = null;
		try {
			basicDrugCheck = (BasicDrugCheck) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return basicDrugCheck;
	}

}
