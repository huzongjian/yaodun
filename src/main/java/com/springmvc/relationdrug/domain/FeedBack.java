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
@Table(name = "b_feedback")
public class FeedBack {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 7778580245662724252L;
	@Id
	@Column(name = "id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	@JsonValue
	private Long feedBackId;
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "userid_")
	@JsonValue
	private User user;
	@Column(name = "description_")
	@JsonValue
	private String description;
	@Column(name = "createtime_")
	@JsonValue
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	public Long getFeedBackId() {
		return feedBackId;
	}
	public void setFeedBackId(Long feedBackId) {
		this.feedBackId = feedBackId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FeedBack(Long feedBackId, User user, String description, Date createtime) {
		super();
		this.feedBackId = feedBackId;
		this.user = user;
		this.description = description;
		this.createtime = createtime;
	}
	public FeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}

}
