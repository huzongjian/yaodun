package com.springmvc.relationdrug.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.springmvc.relationdrug.pojo.SignDomain;

/**
 * 
 * @ClassName: CompatTaboo
 * @Description: 配伍禁忌实体
 * 
 */
@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name = "basic_compat_taboo")
public class CompatTaboo extends SignDomain {
	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long compatTabooId;
	@Column(name = "drug_name_")
	private String drugName;// 药品名称
	@Column(name = "category_")
	private String category;// 分类
	@Column(name = "order_no_")
	private String orderNo;// 序号
	@Column(name = "symbol_")
	private String symbol;// 助记符

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Long getCompatTabooId() {
		return compatTabooId;
	}

	public void setCompatTabooId(Long compatTabooId) {
		this.compatTabooId = compatTabooId;
	}

}
