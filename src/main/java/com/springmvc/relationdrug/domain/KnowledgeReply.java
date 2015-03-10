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
@Table(name = "b_knowledge_reply")
public class KnowledgeReply {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	@JsonValue
	private Long knowledgeReplyId;
	@ManyToOne(targetEntity = Knowledge.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "knowledgeid_")
	private Knowledge knowledge;
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "userid_")
	private User user;
	@Column(name = "createtime_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	@Column(name = "reply_")
	@JsonValue
	private String reply;
	@ManyToOne(targetEntity = KnowledgeReply.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentReplyid_")
	private KnowledgeReply knowledgeReply;

	public Long getKnowledgeReplyId() {
		return knowledgeReplyId;
	}

	public void setKnowledgeReplyId(Long knowledgeReplyId) {
		this.knowledgeReplyId = knowledgeReplyId;
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public KnowledgeReply getKnowledgeReply() {
		return knowledgeReply;
	}

	public void setKnowledgeReply(KnowledgeReply knowledgeReply) {
		this.knowledgeReply = knowledgeReply;
	}

	public KnowledgeReply(Long knowledgeReplyId, Knowledge knowledge, User user,
			Date createtime, String reply, KnowledgeReply knowledgeReply) {
		super();
		this.knowledgeReplyId = knowledgeReplyId;
		this.knowledge = knowledge;
		this.user = user;
		this.createtime = createtime;
		this.reply = reply;
		this.knowledgeReply = knowledgeReply;
	}

	public KnowledgeReply() {
		super();
	}

}