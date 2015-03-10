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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.springmvc.base.util.JsonValue;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name="b_thirdparty")
public class ThirdParty {
	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	@JsonValue
	private Long thirdPartyId;
	@Column(name = "openId_")
	@JsonValue
	private String openId;// 药品名称
	@Column(name = "source_")
	@JsonValue
	private String source;// 分类
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId_")
	private User userId;// 序号
	public ThirdParty(Long thirdPartyId, String openId, String source,
			User userId) {
		super();
		this.thirdPartyId = thirdPartyId;
		this.openId = openId;
		this.source = source;
		this.userId = userId;
	}
	public Long getThirdPartyId() {
		return thirdPartyId;
	}
	public void setThirdPartyId(Long thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public ThirdParty() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
