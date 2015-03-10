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

import com.springmvc.base.util.JsonValue;

@Entity
@Table(name = "b_User")
public class User {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	@JsonValue
	private Long userId;
	@Column(name = "userName_")
	@JsonValue
	private String userName;
	@Column(name = "Email_")
	@JsonValue
	private String email;
	@Column(name = "PassWord_")
	private String password;
	@Column(name = "weight_")
	@JsonValue
	private String weight;
	@Column(name = "height_")
	@JsonValue
	private String height;
	@Column(name = "birthday_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	@Column(name = "gender_")
	@JsonValue
	private String gender;
	@Column(name = "telephone_")
	@JsonValue
	private String telephone;
	@Column(name = "liver_")
	@JsonValue
	private String liver;
	@Column(name = "gestationTime_")
	@JsonValue
	private String gestationTime;
	@Column(name = "gestation_")
	@JsonValue
	private String gestation;
	@Column(name = "breastFeeding_")
	@JsonValue
	private String breastFeeding;
	@Column(name = "kidney_")
	@JsonValue
	private String kidney;
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "createby_")
	private User createby;
	@Column(name = "CREATETIME_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "modifyby_")
	private User modifyby;
	@Column(name = "MOdifyTIME_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifytime;
	@Column(name = "userType_")
	@JsonValue
	private String userType;
	@Column(name = "introduction_")
	@JsonValue
	private String inrtoduction;
	@Column(name = "img_")
	@JsonValue
	private String img;
	

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public User() {
		
	}

	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}





	public String getInrtoduction() {
		return inrtoduction;
	}





	public void setInrtoduction(String inrtoduction) {
		this.inrtoduction = inrtoduction;
	}

	public User(Long id) {
		this.userId = id;
	}

	public User(Long id, String userName, String email, String password,
			String weight, String height, Date birthday, String gender,
			String telephone, String liver, String gestationTime,
			String gestation, String breastFeeding, String kidney,
			User createby, Date createtime, User modifyby, Date modifytime,
			String userType, String inrtoduction,String img) {
		super();
		this.userId = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.weight = weight;
		this.height = height;
		this.birthday = birthday;
		this.gender = gender;
		this.telephone = telephone;
		this.liver = liver;
		this.gestationTime = gestationTime;
		this.gestation = gestation;
		this.breastFeeding = breastFeeding;
		this.kidney = kidney;
		this.createby = createby;
		this.createtime = createtime;
		this.modifyby = modifyby;
		this.modifytime = modifytime;
		this.userType = userType;
		this.inrtoduction = inrtoduction;
		this.img =img;
	}





	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getLiver() {
		return liver;
	}


	public void setLiver(String liver) {
		this.liver = liver;
	}


	public String getGestationTime() {
		return gestationTime;
	}


	public void setGestationTime(String gestationTime) {
		this.gestationTime = gestationTime;
	}


	public String getGestation() {
		return gestation;
	}


	public void setGestation(String gestation) {
		this.gestation = gestation;
	}


	public String getBreastFeeding() {
		return breastFeeding;
	}


	public void setBreastFeeding(String breastFeeding) {
		this.breastFeeding = breastFeeding;
	}


	public String getKidney() {
		return kidney;
	}


	public void setKidney(String kidney) {
		this.kidney = kidney;
	}


	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getCreateby() {
		return createby;
	}

	public void setCreateby(User createby) {
		this.createby = createby;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public User getModifyby() {
		return modifyby;
	}

	public void setModifyby(User modifyby) {
		this.modifyby = modifyby;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

}