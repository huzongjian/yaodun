package com.springmvc.relationdrug.pojo;

import java.util.Date;

import javax.persistence.Entity;

import com.springmvc.base.util.JsonValue;

/**
 * 
 * Summary : 患者信息
 * 
 * 
 */
@Entity
public class PatientInfo {

	/**
	 * 流水号
	 */
	@JsonValue
	private String serialNo;

	/**
	 * 患者姓名
	 */
	@JsonValue
	private String name;
	/**
	 * 性别
	 */
	@JsonValue
	private GenderEnum gender;
	/**
	 * 生日
	 */
	@JsonValue
	private Date birthday;

	/**
	 * 就诊类型
	 */
	@JsonValue
	private CureTypeEnum cureType;

	/**
	 * 身高
	 */
	@JsonValue
	private double height;

	/**
	 * 体表面积
	 */
	@JsonValue
	private double surarea;

	/**
	 * 体重
	 */
	@JsonValue
	private double weight;

	/**
	 * 是否是妊娠期妇女
	 */
	@JsonValue
	private boolean isGestate;
	/**
	 * 是否是哺乳期妇女
	 */
	@JsonValue
	private boolean isSuckling;
	/**
	 * 是否是肝功能不全
	 */
	@JsonValue
	private boolean isLiver;
	/**
	 * 重度肝功能不全
	 */
	@JsonValue
	private boolean isCliver;

	/**
	 * 肾衰竭者的肾小球滤过率，0表示没有这个数据
	 */
	@JsonValue
	private long gfr;
	/**
	 * 妊娠时间
	 */
	@JsonValue
	private long gestateTime;
	/**
	 * 哺乳时间
	 */
	@JsonValue
	private long suckleTime;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public CureTypeEnum getCureType() {
		return cureType;
	}

	public void setCureType(CureTypeEnum cureType) {
		this.cureType = cureType;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getSurarea() {
		return surarea;
	}

	public void setSurarea(double surarea) {
		this.surarea = surarea;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isGestate() {
		return isGestate;
	}

	public void setGestate(boolean isGestate) {
		this.isGestate = isGestate;
	}

	public boolean isSuckling() {
		return isSuckling;
	}

	public void setSuckling(boolean isSuckling) {
		this.isSuckling = isSuckling;
	}

	public boolean isLiver() {
		return isLiver;
	}

	public void setLiver(boolean isLiver) {
		this.isLiver = isLiver;
	}

	public boolean isCliver() {
		return isCliver;
	}

	public void setCliver(boolean isCliver) {
		this.isCliver = isCliver;
	}

	public long getGfr() {
		return gfr;
	}

	public void setGfr(long gfr) {
		this.gfr = gfr;
	}

	public long getGestateTime() {
		return gestateTime;
	}

	public void setGestateTime(long gestateTime) {
		this.gestateTime = gestateTime;
	}

	public long getSuckleTime() {
		return suckleTime;
	}

	public void setSuckleTime(long suckleTime) {
		this.suckleTime = suckleTime;
	}

	/**
	 * 
	 * Summary : 性别
	 * 
	 * 
	 * @author Lineshow Email:lineshow7@gmail.com
	 * 
	 * @since 2013-7-22
	 */
	public enum GenderEnum {
		MALE("男"), FEMALE("女");

		private String text;

		GenderEnum(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}
	}

	/**
	 * 
	 * Summary : 诊疗类型
	 * 
	 * 
	 * @author Lineshow Email:lineshow7@gmail.com
	 * 
	 * @since 2013-7-22
	 */
	public enum CureTypeEnum {
		OUT_PATIENT("门诊"), BE_IN_HOSPITAL("住院");

		private String text;

		CureTypeEnum(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}

	}

}
