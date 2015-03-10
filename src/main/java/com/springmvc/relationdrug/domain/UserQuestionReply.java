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
@Table(name = "b_User_question_reply")
public class UserQuestionReply {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	@JsonValue
	private Long userQuestionReplyId;
	@ManyToOne(targetEntity = UserQuestion.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "questionid_")
	private UserQuestion userQuestion;
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "userid_")
	private User user;
	@Column(name = "createtime_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	@Column(name = "reply_")
	@JsonValue
	private String reply;
	@ManyToOne(targetEntity = UserQuestionReply.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentReplyid_")
	private UserQuestionReply userQuestionReply;
	public Long getUserQuestionReplyId() {
		return userQuestionReplyId;
	}
	public void setUserQuestionReplyId(Long userQuestionReplyId) {
		this.userQuestionReplyId = userQuestionReplyId;
	}
	public UserQuestion getUserQuestion() {
		return userQuestion;
	}
	public void setUserQuestion(UserQuestion userQuestion) {
		this.userQuestion = userQuestion;
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
	public UserQuestionReply getUserQuestionReply() {
		return userQuestionReply;
	}
	public void setUserQuestionReply(UserQuestionReply userQuestionReply) {
		this.userQuestionReply = userQuestionReply;
	}
	public UserQuestionReply(Long userQuestionReplyId, UserQuestion userQuestion, User user,
			Date createtime, String reply, UserQuestionReply userQuestionReply) {
		super();
		this.userQuestionReplyId = userQuestionReplyId;
		this.userQuestion = userQuestion;
		this.user = user;
		this.createtime = createtime;
		this.reply = reply;
		this.userQuestionReply = userQuestionReply;
	}
	public UserQuestionReply() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}