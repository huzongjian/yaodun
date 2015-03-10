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
@Table(name = "b_own_doctor")
public class OwnDoctor {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	@JsonValue
	private Long ownDoctorid;
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "userid_")
	@JsonValue
	private User user;
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "doctorid_")
	@JsonValue
	private User doctor;
	@Column(name = "createtime_")
	@JsonValue
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	public Long getOwnDoctorid() {
		return ownDoctorid;
	}
	public void setOwnDoctorid(Long id) {
		this.ownDoctorid = ownDoctorid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getDoctor() {
		return doctor;
	}
	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public OwnDoctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OwnDoctor(Long ownDoctorid, User user, User doctor, Date createtime) {
		super();
		this.ownDoctorid = ownDoctorid;
		this.user = user;
		this.doctor = doctor;
		this.createtime = createtime;
	}
	

}