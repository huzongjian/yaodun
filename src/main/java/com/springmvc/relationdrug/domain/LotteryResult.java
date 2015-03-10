package com.springmvc.relationdrug.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_Result")
public class LotteryResult {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "iebmImageGenerator", strategy = "increment")
	@GeneratedValue(generator = "iebmImageGenerator")
	private Long id;
	@Column(name = "FirstNum_")
	private Integer firstNum;
	@Column(name = "SecondNum_")
	private Integer secondNum;
	@Column(name = "ThirdNum_")
	private Integer thirdNum;
	@Column(name = "FourNum_")
	private Integer fourNum;
	@Column(name = "FiveNum_")
	private Integer fiveNum;
	@Column(name = "SixNum_")
	private Integer sixNum;
	@Column(name = "SpecialNum_")
	private Integer userName;

	@Column(name = "LotteryTime_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lotteryTime;

	@ManyToOne(targetEntity = LotteryResult.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "createby_")
	private LotteryResult createby;

	@Column(name = "CREATETIME_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	@ManyToOne(targetEntity = LotteryResult.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "modifyby_")
	private LotteryResult modifyby;
	@Column(name = "MOdifyTIME_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifytime;

	/** default constructor */
	public LotteryResult() {
	}

	public LotteryResult(Long id, Integer firstNum, Integer secondNum,
			Integer thirdNum, Integer fourNum, Integer fiveNum, Integer sixNum,
			Integer userName, Date lotteryTime, LotteryResult createby,
			Date createtime, LotteryResult modifyby, Date modifytime) {
		super();
		this.id = id;
		this.firstNum = firstNum;
		this.secondNum = secondNum;
		this.thirdNum = thirdNum;
		this.fourNum = fourNum;
		this.fiveNum = fiveNum;
		this.sixNum = sixNum;
		this.userName = userName;
		this.lotteryTime = lotteryTime;
		this.createby = createby;
		this.createtime = createtime;
		this.modifyby = modifyby;
		this.modifytime = modifytime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(Integer firstNum) {
		this.firstNum = firstNum;
	}

	public Integer getSecondNum() {
		return secondNum;
	}

	public void setSecondNum(Integer secondNum) {
		this.secondNum = secondNum;
	}

	public Integer getThirdNum() {
		return thirdNum;
	}

	public void setThirdNum(Integer thirdNum) {
		this.thirdNum = thirdNum;
	}

	public Integer getFourNum() {
		return fourNum;
	}

	public void setFourNum(Integer fourNum) {
		this.fourNum = fourNum;
	}

	public Integer getFiveNum() {
		return fiveNum;
	}

	public void setFiveNum(Integer fiveNum) {
		this.fiveNum = fiveNum;
	}

	public Integer getSixNum() {
		return sixNum;
	}

	public void setSixNum(Integer sixNum) {
		this.sixNum = sixNum;
	}

	public Integer getUserName() {
		return userName;
	}

	public void setUserName(Integer userName) {
		this.userName = userName;
	}

	public Date getLotteryTime() {
		return lotteryTime;
	}

	public void setLotteryTime(Date lotteryTime) {
		this.lotteryTime = lotteryTime;
	}

	public LotteryResult getCreateby() {
		return createby;
	}

	public void setCreateby(LotteryResult createby) {
		this.createby = createby;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public LotteryResult getModifyby() {
		return modifyby;
	}

	public void setModifyby(LotteryResult modifyby) {
		this.modifyby = modifyby;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

}