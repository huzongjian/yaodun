package com.springmvc.relationdrug.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.springmvc.relationdrug.enums.STDegreeEnum;
import com.springmvc.relationdrug.pojo.SignDomain;

/**
 * 
 * @ClassName: SkinTest
 * @Description: 皮试检测实体
 * 
 */
@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name = "basic_skintest")
public class SkinTest extends SignDomain {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long skinTestId;
	@Column(name = "solubility_")
	private String solubility;// 皮试药液溶度
	@Column(name = "way_dose_")
	private String wayDose;// 给药方法与剂量
	@Column(name = "requirement_")
	@Enumerated(EnumType.ORDINAL)
	private STDegreeEnum requirement;// 皮试要求

	public Long getSkinTestId() {
		return skinTestId;
	}

	public void setSkinTestId(Long skinTestId) {
		this.skinTestId = skinTestId;
	}

	public String getSolubility() {
		return solubility;
	}

	public void setSolubility(String solubility) {
		this.solubility = solubility;
	}

	public String getWayDose() {
		return wayDose;
	}

	public void setWayDose(String wayDose) {
		this.wayDose = wayDose;
	}

	public STDegreeEnum getRequirement() {
		return requirement;
	}

	public void setRequirement(STDegreeEnum requirement) {
		this.requirement = requirement;
	}

}
