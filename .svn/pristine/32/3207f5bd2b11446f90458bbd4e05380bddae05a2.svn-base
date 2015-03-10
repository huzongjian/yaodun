package com.springmvc.relationdrug.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Summary :
 * 
 * 
 */
@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name = "basic_verify_main")
public class VerifyRecord {

	@Id
	@Column(name = "id_")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long verifyRecordId;

	@Column(name = "prescribe_code_")
	private String prescribeCode;

	@Column(name = "doctor_no_")
	private String medicalerNo;

	@Column(name = "verify_time_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date verifyTime;

	public Long getVerifyRecordId() {
		return verifyRecordId;
	}

	public void setVerifyRecordId(Long verifyRecordId) {
		this.verifyRecordId = verifyRecordId;
	}

	public String getPrescribeCode() {
		return prescribeCode;
	}

	public void setPrescribeCode(String prescribeCode) {
		this.prescribeCode = prescribeCode;
	}

	public String getMedicalerNo() {
		return medicalerNo;
	}

	public void setMedicalerNo(String medicalerNo) {
		this.medicalerNo = medicalerNo;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

}
