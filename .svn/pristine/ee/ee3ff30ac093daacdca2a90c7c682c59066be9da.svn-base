package com.springmvc.relationdrug.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="basic_drug_type")
public class DrugType {
	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long drugTypeId;
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
	@ManyToOne(targetEntity = BasicDrugData.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "drug_id_")
	private BasicDrugData basicDrugData; 
	@Column(name = "drug_name_")
	private String drugName;
	public Long getDrugTypeId() {
		return drugTypeId;
	}
	public void setDrugTypeId(Long drugTypeId) {
		this.drugTypeId = drugTypeId;
	}
	public String getClassifyOne() {
		return classifyOne;
	}
	public void setClassifyOne(String classifyOne) {
		this.classifyOne = classifyOne;
	}
	public String getClassifyTwo() {
		return classifyTwo;
	}
	public void setClassifyTwo(String classifyTwo) {
		this.classifyTwo = classifyTwo;
	}
	public String getClassifyThree() {
		return classifyThree;
	}
	public void setClassifyThree(String classifyThree) {
		this.classifyThree = classifyThree;
	}
	public String getClassifyFour() {
		return classifyFour;
	}
	public void setClassifyFour(String classifyFour) {
		this.classifyFour = classifyFour;
	}
	public String getClassifyFive() {
		return classifyFive;
	}
	public void setClassifyFive(String classifyFive) {
		this.classifyFive = classifyFive;
	}
	public BasicDrugData getBasicDrugData() {
		return basicDrugData;
	}
	public void setBasicDrugData(BasicDrugData basicDrugData) {
		this.basicDrugData = basicDrugData;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public DrugType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DrugType(Long drugTypeId, String classifyOne, String classifyTwo,
			String classifyThree, String classifyFour, String classifyFive,
			BasicDrugData basicDrugData, String drugName) {
		super();
		this.drugTypeId = drugTypeId;
		this.classifyOne = classifyOne;
		this.classifyTwo = classifyTwo;
		this.classifyThree = classifyThree;
		this.classifyFour = classifyFour;
		this.classifyFive = classifyFive;
		this.basicDrugData = basicDrugData;
		this.drugName = drugName;
	}
	
	
}
