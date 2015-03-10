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
 * 用药冲突记录表实
 */
@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name = "basic_CONFLICT_FROM_DRUGS")
public class ConflictFromDrugs {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	private Long conflictFromDrugsId;
	@Column(name = "type_")
	private String conflictType; // 冲突类型
	@ManyToOne(targetEntity = CompatTaboo.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "taboo_id1_")
	private CompatTaboo compatTaboo1; // 药品1Id
	@ManyToOne(targetEntity = CompatTaboo.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "taboo_id2_")
	private CompatTaboo compatTaboo2; // 药品2Id

	public Long getConflictFromDrugsId() {
		return conflictFromDrugsId;
	}

	public void setConflictFromDrugsId(Long conflictFromDrugsId) {
		this.conflictFromDrugsId = conflictFromDrugsId;
	}

	public String getConflictType() {
		return conflictType;
	}

	public void setConflictType(String conflictType) {
		this.conflictType = conflictType;
	}

	public CompatTaboo getCompatTaboo1() {
		return compatTaboo1;
	}

	public void setCompatTaboo1(CompatTaboo compatTaboo1) {
		this.compatTaboo1 = compatTaboo1;
	}

	public CompatTaboo getCompatTaboo2() {
		return compatTaboo2;
	}

	public void setCompatTaboo2(CompatTaboo compatTaboo2) {
		this.compatTaboo2 = compatTaboo2;
	}

}
