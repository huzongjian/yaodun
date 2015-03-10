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
@Table(name = "basic_drugname_relationship")
public class BasicDrugRelationship {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long basicDrugRelationShipId;
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ManyToOne(targetEntity = BasicDrugData.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "basic_drug_id_")
	private BasicDrugData basicDrugData;
	@Column(name = "common_name_")
	private String commonName;
	@Column(name = "product_name_")
	private String productName;
	@Column(name = "commodity_name_")
	private String commodityName;
	@Column(name = "product_name_symbol_")
	private String productNameSymbol;
	@Column(name = "commodity_name_symbol_")
	private String commodityNameSymbol;

	public Long getBasicDrugRelationShipId() {
		return basicDrugRelationShipId;
	}

	public void setBasicDrugRelationShipId(Long basicDrugRelationShipId) {
		this.basicDrugRelationShipId = basicDrugRelationShipId;
	}

	public BasicDrugData getBasicDrugCheck() {
		return basicDrugData;
	}

	public void setBasicDrugCheck(BasicDrugData basicDrugData) {
		this.basicDrugData = basicDrugData;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getProductNameSymbol() {
		return productNameSymbol;
	}

	public void setProductNameSymbol(String productNameSymbol) {
		this.productNameSymbol = productNameSymbol;
	}

	public String getCommodityNameSymbol() {
		return commodityNameSymbol;
	}

	public void setCommodityNameSymbol(String commodityNameSymbol) {
		this.commodityNameSymbol = commodityNameSymbol;
	}

	public BasicDrugRelationship(Long basicDrugRelationShipId, BasicDrugData basicDrugData,
			String commonName, String productName, String commodityName,
			String productNameSymbol, String commodityNameSymbol) {
		super();
		this.basicDrugRelationShipId = basicDrugRelationShipId;
		this.basicDrugData = basicDrugData;
		this.commonName = commonName;
		this.productName = productName;
		this.commodityName = commodityName;
		this.productNameSymbol = productNameSymbol;
		this.commodityNameSymbol = commodityNameSymbol;
	}

	public BasicDrugRelationship() {
		super();
	}

}
