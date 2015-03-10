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
@Table(name = "b_own_knowledge")
public class OwnKnowledge {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	@JsonValue
	private Long ownDoctorId;
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "userid_")
	@JsonValue
	private User user;
	@ManyToOne(targetEntity = Knowledge.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "knowledgeid_")
	@JsonValue
	private Knowledge knowledge;
	@Column(name = "createtime_")
	@JsonValue
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	public Long getOwnDoctorId() {
		return ownDoctorId;
	}
	public void setOwnDoctorId(Long ownDoctorId) {
		this.ownDoctorId = ownDoctorId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Knowledge getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public OwnKnowledge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OwnKnowledge(Long ownDoctorId, User user, Knowledge knowledge, Date createtime) {
		super();
		this.ownDoctorId = ownDoctorId;
		this.user = user;
		this.knowledge = knowledge;
		this.createtime = createtime;
	}

}