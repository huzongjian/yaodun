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
 * Summary : 用法
 * 
 * 
 */
@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name = "basic_USAGE")
public class Usage extends SignDomain {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long usageId;
	@Column(name = "NAME_")
	private String name;

	@Column(name = "symbol_")
	private String symbol;

	public String getName() {
		return name;
	}

	/**
	 * 名称
	 * 
	 * 
	 * @since 2013-7-27
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	/**
	 * 助记
	 * 
	 * 
	 * @since 2013-7-27
	 * @param symbol
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Usage() {
		super();
	}

	public Usage(Long id) {
		super(id);
	}

	public Long getUsageId() {
		return usageId;
	}

	public void setUsageId(Long usageId) {
		this.usageId = usageId;
	}

}
